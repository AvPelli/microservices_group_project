package be.ugent.groep10.catering;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

import be.ugent.groep10.catering.adapters.messaging.CateringReservationGateway;
import be.ugent.groep10.catering.adapters.messaging.Channels;
import be.ugent.groep10.catering.adapters.messaging.NewEventRequest;
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
			final ScheduleItem event1 = new ScheduleItem( "1",LocalDate.now().plus(2, ChronoUnit.DAYS),LocalDate.now().plus(3, ChronoUnit.DAYS),"Feestje", 0);
			final ScheduleItem event2 = new ScheduleItem( "2",LocalDate.now().plus(20, ChronoUnit.DAYS),LocalDate.now().minus(22, ChronoUnit.DAYS),"Feestje2", 0);
			final ScheduleItem event3 = new ScheduleItem( "3",LocalDate.now().plus(5, ChronoUnit.DAYS),LocalDate.now().plus(7, ChronoUnit.DAYS),"Feestje3", 100);
			final ScheduleItem event4 = new ScheduleItem( "4",LocalDate.now().plus(8, ChronoUnit.DAYS),LocalDate.now().plus(9, ChronoUnit.DAYS),"Feestje4", 100);
			final ScheduleItem event5 = new ScheduleItem( "5",LocalDate.now().plus(10, ChronoUnit.DAYS),LocalDate.now().plus(15, ChronoUnit.DAYS),"Feestje5",0);
			
			scheduleItemRepository.save(event1);
			scheduleItemRepository.save(event2);
			scheduleItemRepository.save(event3);
			scheduleItemRepository.save(event4);
			scheduleItemRepository.save(event5);
		};
	}
	
	@Bean
	public CommandLineRunner testQueries(ScheduleItemRepository scheduleItemRepository) {
		return (args) ->{
			System.out.println("Printing all booked stays...");
			scheduleItemRepository.findByEventId("1").forEach(System.out::println);
		};
	}
	
	@Bean
	CommandLineRunner testGateway(CateringReservationGateway gateway, ScheduleItemRepository scheduleItemRepository) {
		return (args)->{
			
			//Start kafka consumer met : bash kafka_listen.sh registered_events 
			ScheduleItem item = scheduleItemRepository.findByEventId("1").get(0);
			gateway.eventRegistered(item);
		};
	}
}
