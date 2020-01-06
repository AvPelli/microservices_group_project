package be.ugent.groep10.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SportarenaAppApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportarenaAppApiGatewayApplication.class, args);
	}
	
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				// Patient service routes
				.route(r -> r.host("*").and().path("/").uri("http://localhost:2228/"))
				.route(r -> r.host("*").and().path("/member/**").uri("http://localhost:2227"))
				.route(r -> r.host("*").and().path("/betting/**").uri("http://localhost:2226"))
				.route(r -> r.host("*").and().path("/arena/**").uri("http://localhost:2223"))
				.route(r -> r.host("*").and().path("/catering/**").uri("http://localhost:2224"))
				.route(r -> r.host("*").and().path("/authorization/**").uri("http://localhost:2228"))
				.route(r -> r.host("*").and().path("/oauthinfo").uri("http://localhost:2228/outhinfo"))
				
				// Reception service routes
//				.route(r -> r.host("*").and().path("/reception/**").uri("http://localhost:2223"))

				// it is also possible to give independent paths:
				// .route(r ->
				// r.host("*").and().path("/patients").uri("http://localhost:2222/patients") )

				.build();
	}

}
