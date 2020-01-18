package be.ugent.groep10.catering;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
import be.ugent.groep10.catering.domain.CateringSchedule;
import be.ugent.groep10.catering.domain.CateringService;
import be.ugent.groep10.catering.persistence.CateringScheduleRepository;

@SpringBootApplication
@EnableBinding(Channels.class)
public class SportarenaAppCateringManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportarenaAppCateringManagementApplication.class, args);
	}

	@Bean
	CommandLineRunner populateDatabase(CateringScheduleRepository cateringScheduleRepository) {
		return (args) ->{
			cateringScheduleRepository.deleteAll();
			int year = 2020;
			int month = 1;
			int day = 4;
			int hour = 21;
			int minute = 14;
			final CateringSchedule event1 = new CateringSchedule( "1",LocalDateTime.of(year, month, day, hour, minute, 00),
					LocalDateTime.of(year, month, day, hour, minute, 00),"Feestje", 0);
			final CateringSchedule event2 = new CateringSchedule( "2",LocalDateTime.of(year, month, day, hour, minute, 00),
					LocalDateTime.of(year, month, day, hour, minute, 00),"Feestje", 0);
			final CateringSchedule event3 = new CateringSchedule( "3",LocalDateTime.of(year, month, day, hour, minute, 00),
					LocalDateTime.of(year, month, day, hour, minute, 00),"Feestje", 0);
			
			cateringScheduleRepository.save(event1);
			cateringScheduleRepository.save(event2);
			cateringScheduleRepository.save(event3);
		};
	}
	
	@Bean
	public CommandLineRunner testQueries(CateringScheduleRepository cateringScheduleRepository) {
		return (args) ->{
			System.out.println("Printing all booked stays...");
			cateringScheduleRepository.findBySportEventId("1").forEach(System.out::println);
		};
	}
}
