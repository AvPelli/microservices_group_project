package be.ugent.groep10.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class SportarenaAppAuthorizationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportarenaAppAuthorizationServiceApplication.class, args);
	}

}
