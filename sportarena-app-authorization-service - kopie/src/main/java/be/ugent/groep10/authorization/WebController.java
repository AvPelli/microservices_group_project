package be.ugent.groep10.authorization;



import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {
	
//	https://developer.okta.com/blog/2018/10/05/build-a-spring-boot-app-with-user-authentication#start-your-spring-boot-application
	
	
	@RequestMapping("/")
    public String index() {
		System.out.println("Home");
        return "home";
    }
	
	
	@RequestMapping("/register")
    public String register() {
        return "register";
    }
	
	@RequestMapping("/login")
    public String login() {
        return "login";
    }
	
	@RequestMapping("/restricted")
    public String securedPage() {
        return "restricted";
    }
	
	@RequestMapping("/admin")  
	@PreAuthorize("hasAuthority('Admin')")
	String admin() {  
	    return "login";  
	}
	
	// Used for showing OAuth info of the authenticated user
	@RequestMapping("/oauthinfo")  
    @ResponseBody  
    public String oauthUserInfo(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,  
                              @AuthenticationPrincipal OAuth2User oauth2User) {  
        return  
            "User Name: " + oauth2User.getName() + "<br/>" +  
            "User Authorities: " + oauth2User.getAuthorities() + "<br/>" +  
            "Client Name: " + authorizedClient.getClientRegistration().getClientName() + "<br/>" +  
            this.prettyPrintAttributes(oauth2User.getAttributes());  
    }  
	
	private String prettyPrintAttributes(Map<String, Object> attributes) {  
        String acc = "User Attributes: <br/><div style='padding-left:20px'>";  
        for (String key : attributes.keySet()){  
            Object value = attributes.get(key);  
            acc += "<div>"+key + ":&nbsp" + value.toString() + "</div>";  
        }  
        return acc + "</div>";  
    }  
    
}
