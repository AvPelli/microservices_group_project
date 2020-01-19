package be.ugent.groep10.staff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

import be.ugent.groep10.staff.adapters.messaging.Channels;


@SpringBootApplication
@EnableBinding(Channels.class)
public class SportarenaAppArenaStaffManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportarenaAppArenaStaffManagementApplication.class, args);
	}

}
