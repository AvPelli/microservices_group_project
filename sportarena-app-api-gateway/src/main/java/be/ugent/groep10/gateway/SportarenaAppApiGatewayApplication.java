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
		
		return builder.routes()
				// Patient service routes
				.route(r -> r.path("/").uri("http://authorization:2228/"))
				.route(r -> r.path("/member/**").uri("http://membermanagement:2227"))
				.route(r -> r.path("/betting/**").uri("http://gamblingmanagement:2226"))
				.route(r -> r.path("/wallet/**").uri("http://gamblingmanagement:2226"))
				.route(r -> r.path("/arena/**").uri("http://bettingmanagement:2223"))
				.route(r -> r.path("/catering/**").uri("http://cateringmanagement:2224"))
				.route(r -> r.path("/authorization/**").uri("http://authorization:2228"))
				.route(r -> r.path("/oauthinfo").uri("http://authorization:2228/outhinfo"))
				.route(r -> r.path("/games/**").uri("http://arenamanagement:2225"))
				.route(r -> r.path("/tickets/**").uri("http://ticketmanagement:2230"))
				.route(r -> r.path("/staff/**").uri("http://staffmanagement:2231"))

				.build();
	}

}
