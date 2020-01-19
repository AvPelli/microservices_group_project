package be.ugent.groep10.authorization.adapters.rest;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.ModelAndView;

import com.okta.sdk.authc.credentials.TokenClientCredentials;
import com.okta.sdk.client.Client;
import com.okta.sdk.client.Clients;
import com.okta.sdk.resource.group.Group;
import com.okta.sdk.resource.group.GroupList;
import com.okta.sdk.resource.user.User;
import com.okta.sdk.resource.user.UserBuilder;

import be.ugent.groep10.authorization.adapters.messaging.RegisterResponse;
import be.ugent.groep10.authorization.domain.AuthorizationRegisterListener;
import be.ugent.groep10.authorization.domain.AuthorizationService;
import be.ugent.groep10.authorization.domain.Member;
import be.ugent.groep10.authorization.domain.RegisterRequest;
import be.ugent.groep10.authorization.domain.Role;
import net.minidev.json.JSONObject;

@Controller
public class AuthorizationRestController implements AuthorizationRegisterListener{
	
	private final Map<String, DeferredResult<String>> deferredResults;
	private AuthorizationService authorizationService;
	
	@Value("${frontend.url}")
	private String url;
	
	
	@Autowired
	private AuthorizationRestController(AuthorizationService authorizationService) {
		this.deferredResults = new HashMap<String, DeferredResult<String>>(20);
		this.authorizationService = authorizationService;
	}
	
	@PostConstruct
	private void registerListener() {
		authorizationService.registerAuthorizationListener(this);
	}
	
	@RequestMapping("/")
	public String index() {
		return "home";
	}

	
	@RequestMapping("/authorization/register")
	public ModelAndView register() {
		ModelAndView model = new ModelAndView();
		model.setViewName("register");
		model.addObject("urlValue", url);
		
		return model;
	}
	
	@PostMapping("/authorization/register")
	@ResponseBody
	public DeferredResult<String> registerUser(@RequestBody RegisterRequest registerRequest) {
//		https://github.com/okta/okta-sdk-java/blob/master/README.md
//		https://github.com/okta/okta-sdk-java/wiki/Creating-a-Client
		
		DeferredResult<String> deferredResult = new DeferredResult<>(10000l);
		
		User oktaUser = authorizationService.makeOktaUser(registerRequest);
		
		deferredResult.onTimeout(() -> {
			authorizationService.registrationTimeout(oktaUser, registerRequest.getRole());
			deferredResult.setErrorResult("Request timeout occured.");
		});
		
		this.deferredResults.put(oktaUser.getId(), deferredResult);
		
		try {
			this.authorizationService.register(oktaUser, registerRequest);
		} 
		catch (Exception e) {
			//TODO: controleer of removeOktaUser hier juist staat en lukt
			deferredResults.remove(oktaUser.getId());
			authorizationService.removeOktaUser(oktaUser.getId());
			deferredResult.setErrorResult("Failed to register user.");
		}
		
		return deferredResult;	
	}
	

//	@PostMapping("/authorization/register")
//	@ResponseBody
//	public String registerUser(@RequestBody RegisterRequest registerRequest) {
////		https://github.com/okta/okta-sdk-java/blob/master/README.md
////		https://github.com/okta/okta-sdk-java/wiki/Creating-a-Client
//
//		Client client = Clients.builder() // TODO: put this in some kind of config file
//				.setOrgUrl("https://dev-883704.okta.com")
//				.setClientCredentials(new TokenClientCredentials("00mLipZ_DVh-Qwo82Gl90uOTNUTPsm1wTLeTGPI-XM")).build();
//		
//		Group group = client.listGroups(registerRequest.getRole(),"","").single();
//
//		User user = UserBuilder.instance().setEmail(registerRequest.getEmailAddress())
//				.setFirstName(registerRequest.getFirstName()).setLastName(registerRequest.getLastName())
//				.setPassword("Password123".toCharArray())
//				.setGroups(Stream.of(group.getId()).collect(Collectors.toSet()))
//				.buildAndCreate(client);
//		
//		
//		
////		System.out.println(group.getId());
//		
//		switch(registerRequest.getRole()) {
//			case Role.MEMBER:
//				return addMember(user, registerRequest);
//			case Role.EMPLOYEE:
//				return "Not implemented";
//			case Role.CATERINGSERVICE:
//				return "Not implemented";
//			case Role.CLUB:
//				return "Not implemented";
//		}
//		
//		return "nietske jonge";
//		
//	}
	
	private void performResponse(String oktaUserId, boolean succeeded) {
		DeferredResult<String> deferredResult = this.deferredResults.remove(oktaUserId);
		if (deferredResult != null && !deferredResult.isSetOrExpired()) {
			System.out.println("Setting result");
			if(succeeded) {
				deferredResult.setResult(oktaUserId);
			} else {
				//TODO: vraag aan Simeon over exceptieklasse toevoegen volgens setErrorResult ofzo, hij had hier iets gelezen.
				deferredResult.setErrorResult(oktaUserId);
			}
			
		} else {
			System.out.println("defereredResult: " + deferredResult);
		}
	}

	// Used for showing OAuth info of the authenticated user
	@RequestMapping("/oauthinfo")
	@ResponseBody
	public String oauthUserInfo(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
			@AuthenticationPrincipal OAuth2User oauth2User) {
		System.out.println("oauthinfo");
		return "User Name: " + oauth2User.getName() + "<br/>" + "User Authorities: " + oauth2User.getAuthorities()
				+ "<br/>" + "Client Name: " + authorizedClient.getClientRegistration().getClientName() + "<br/>"
				+ this.prettyPrintAttributes(oauth2User.getAttributes());
	}

	private String prettyPrintAttributes(Map<String, Object> attributes) {
		String acc = "User Attributes: <br/><div style='padding-left:20px'>";
		for (String key : attributes.keySet()) {
			Object value = attributes.get(key);
			acc += "<div>" + key + ":&nbsp" + value.toString() + "</div>";
		}
		return acc + "</div>";
	}

	@Override
	public void onRegisterResult(RegisterResponse registerResponse) {
		this.performResponse(registerResponse.getUserId(), registerResponse.isSucceeded());
	}
}
