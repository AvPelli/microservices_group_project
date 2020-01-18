package be.ugent.groep10.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

import be.ugent.groep10.authorization.adapters.messaging.Channels;


@SpringBootApplication
@EnableAutoConfiguration
@EnableBinding(Channels.class)
public class SportarenaAppAuthorizationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportarenaAppAuthorizationServiceApplication.class, args);
	}

}
