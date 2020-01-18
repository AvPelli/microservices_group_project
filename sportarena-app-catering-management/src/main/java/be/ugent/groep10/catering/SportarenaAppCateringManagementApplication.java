package be.ugent.groep10.catering;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

import be.ugent.groep10.catering.adapters.messaging.CateringCommandHandler;
import be.ugent.groep10.catering.adapters.messaging.Channels;
import be.ugent.groep10.catering.adapters.messaging.NewEventRequest;
import be.ugent.groep10.catering.adapters.messaging.SeatOccupationUpdate;
import be.ugent.groep10.catering.domain.CateringService;
import be.ugent.groep10.catering.domain.ScheduleItem;
import be.ugent.groep10.catering.persistence.ScheduleItemRepository;

@SpringBootApplication
@EnableBinding(Channels.class)
public class SportarenaAppCateringManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportarenaAppCateringManagementApplication.class, args);
	}

	@Bean
	CommandLineRunner populateDatabase(ScheduleItemRepository scheduleItemRepository) {
		return (args) ->{
			scheduleItemRepository.deleteAll();
			final ScheduleItem event1 = new ScheduleItem( "1","18/01/2020","19/01/2020","Feestje", 0);
			final ScheduleItem event2 = new ScheduleItem( "2","18/01/2020","19/01/2020","Feestje", 0);
			final ScheduleItem event3 = new ScheduleItem( "3","18/01/2020","19/01/2020","Feestje", 0);
			
			scheduleItemRepository.save(event1);
			scheduleItemRepository.save(event2);
			scheduleItemRepository.save(event3);
		};
	}
	
	@Bean
	public CommandLineRunner testQueries(ScheduleItemRepository scheduleItemRepository) {
		return (args) ->{
			System.out.println("Printing all booked stays...");
			scheduleItemRepository.findBySportEventId("1").forEach(System.out::println);
		};
	}
}
