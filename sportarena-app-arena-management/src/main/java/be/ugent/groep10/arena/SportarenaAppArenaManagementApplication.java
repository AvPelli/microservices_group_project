package be.ugent.groep10.arena;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import be.ugent.groep10.arena.adapters.messaging.ArenaGateway;
import be.ugent.groep10.arena.domain.ScheduleItem;
import be.ugent.groep10.arena.persistence.ScheduleItemRepository;

@SpringBootApplication
public class SportarenaAppArenaManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportarenaAppArenaManagementApplication.class, args);
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
	CommandLineRunner testGateway(ArenaGateway gateway, ScheduleItemRepository scheduleItemRepository) {
		return (args)->{
			
			//Stuur message naar topic "events"
			ScheduleItem newEvent = scheduleItemRepository.findByEventId("1").get(0);
			if(newEvent != null) {
				//No bean named "events" error : later uitzoeken
				//gateway.sendNewEvent(newEvent);
			}
		};
	}
}
