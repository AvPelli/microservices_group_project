package be.ugent.groep10.gateway;

import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SportarenaAppApiGatewayApplication {
	
	@Autowired
	private Environment env;

	public static void main(String[] args) {
		SpringApplication.run(SportarenaAppApiGatewayApplication.class, args);
	}
	
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		System.out.println("customRouteLocator");
//		for when using environments and building in docker container itself -> better to mount then to copy
//		String memberMgmt = env.getProperty("NAME_MEMBERMGMT", String.class, "localhost");
//		String authorizationService = env.getProperty("NAME_AUTHORIZATIONSERVICE", String.class, "localhost");
		String memberMgmt = "membermanagement"; //localhost for local testing
		String authorizationService = "authorization";
		System.out.println("memberMgmt= " + memberMgmt);
		
		
		System.out.println("Can connect to authorization:");
		
		RestTemplate restTemplate = new RestTemplate();
		
		//Member res = restTemplate.postForEntity("http://localhost:2227/member", entity, Member.class).getBody();
		String res = restTemplate.getForEntity("http://"+ authorizationService +":2228/", String.class).getBody();
		
		System.out.println(res);
		
		return builder.routes()
				// Patient service routes
				.route(r -> r.path("/").uri("http://"+ authorizationService +":2228/"))
				.route(r -> r.path("/member/**").uri("http://" + memberMgmt +":2227"))
				.route(r -> r.path("/betting/**").uri("http://bettingmanagement:2226"))
				.route(r -> r.path("/wallet/**").uri("http://bettingmanagement:2226"))
				.route(r -> r.path("/arena/**").uri("http://bettingmanagement:2223"))
				.route(r -> r.path("/catering/**").uri("http://cateringmanagement:2224"))
				.route(r -> r.path("/authorization/**").uri("http://"+ authorizationService + ":2228"))
				.route(r -> r.path("/oauthinfo").uri("http://"+ authorizationService + ":2228/outhinfo"))
				.route(r -> r.path("/games/**").uri("http://arenamanagement:2225"))
				.route(r -> r.path("/tickets/**").uri("http://ticketmanagement:2230"))
				.route(r -> r.path("/staff/**").uri("http://staffmanagement:2231"))
				
				// Reception service routes
//				.route(r -> r.host("*").and().path("/reception/**").uri("http://localhost:2223"))

				// it is also possible to give independent paths:
				// .route(r ->
				// r.host("*").and().path("/patients").uri("http://localhost:2222/patients") )

				.build();
	}

}
