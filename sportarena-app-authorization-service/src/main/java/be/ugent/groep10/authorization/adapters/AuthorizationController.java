package be.ugent.groep10.authorization.adapters;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.okta.sdk.authc.credentials.TokenClientCredentials;
import com.okta.sdk.client.Client;
import com.okta.sdk.client.Clients;
import com.okta.sdk.resource.user.User;
import com.okta.sdk.resource.user.UserBuilder;

import be.ugent.groep10.authorization.Model.RegisterRequest;

@Controller
public class AuthorizationController {
	
	@RequestMapping("/")
	public String index() {
		return "home";
	}

	@RequestMapping("/authorization/register")
	public String register() {
		return "register";
	}	
	
	@PostMapping("/authorization/register")
	public String registerUser(@RequestBody RegisterRequest registerRequest) {
//		https://github.com/okta/okta-sdk-java/blob/master/README.md
		System.out.println("Hierzo");
		System.out.println(registerRequest);
		
		Client client = Clients.builder() // TODO: put this in some kind of config file
				.setOrgUrl("https://dev-883704.okta.com")
			    .setClientCredentials(new TokenClientCredentials("00mLipZ_DVh-Qwo82Gl90uOTNUTPsm1wTLeTGPI-XM"))
			    .build();
		
		User user = UserBuilder.instance()
				.setEmail(registerRequest.getEmailAddress())
				.setFirstName(registerRequest.getFirstName())
				.setLastName(registerRequest.getLastName())
				.setPassword("Password123".toCharArray())
				.buildAndCreate(client);
		
		System.out.println(user);
		
		return "home";
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
}
