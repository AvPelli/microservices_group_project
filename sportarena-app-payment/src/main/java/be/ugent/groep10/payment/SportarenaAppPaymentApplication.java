package be.ugent.groep10.payment;

import java.nio.channels.Channel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(Channel.class)
@SpringBootApplication
public class SportarenaAppPaymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportarenaAppPaymentApplication.class, args);
	}

}
