package be.ugent.groep10.payment;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

import be.ugent.groep10.payment.adapters.messaging.Channels;


@SpringBootApplication
@EnableBinding(Channels.class)
public class SportarenaAppPaymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportarenaAppPaymentApplication.class, args);
	}

}
