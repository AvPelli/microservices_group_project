package be.ugent.groep10.arena;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import be.ugent.groep10.arena.domain.Game;
import be.ugent.groep10.arena.persistence.GameRepository;
import be.ugent.groep10.arena.adapters.messaging.Channels;

@SpringBootApplication
//@EnableBinding(Channels.class)
@EnableScheduling
public class SportarenaAppArenaManagementApplication {

	Logger logger = LoggerFactory.getLogger(SportarenaAppArenaManagementApplication.class);
			
	public static void main(String[] args) {
		SpringApplication.run(SportarenaAppArenaManagementApplication.class, args);
	}
	
	
	@Bean
	public CommandLineRunner populateDatabase(GameRepository repository) {
		return (args) -> {
			
			logger.info("Clearing database...");
			repository.deleteAll();
			
			logger.info("Populating with new data...");
			
			int year = 2020;
			int month = 1;
			int day = 4;
			int hour = 21;
			int minute = 14;
			Game[] games = { 
                    	new Game(
                    		"SC Rotum", "Japser", "Simeon", "x", 
                    		LocalDateTime.of(year, month, day, hour, minute, 00), 
                    		LocalDateTime.of(year, month, day, hour, minute + 1, 00)
                		),
                    	new Game(
                			"SC Rotum","Simeon", "Japser", "xx", 
        					LocalDateTime.of(year, month, day, hour, minute + 2, 00), 
        					LocalDateTime.of(year, month, day, hour, minute + 3, 00)
    					),
                    	new Game(
                			"SC Rum","Arthur", "Axel", "xxx", 
        					LocalDateTime.of(year, month, day, hour, minute + 4, 00), 
        					LocalDateTime.of(year, month, day, hour, minute + 5, 00)
    					),
                    	new Game(
                			"SC Rum","Japser", "Axel", "xxxx", 
        					LocalDateTime.of(year, month, day, hour, minute + 6, 00), 
        					LocalDateTime.of(year, month, day, hour, minute + 7, 00)
    					),
                    	new Game(
                			"SC Rotum","Arthur", "Simeon", "xxxxx", 
        					LocalDateTime.of(year, month, day, hour, minute + 8, 00), 
        					LocalDateTime.of(year, month, day, hour, minute + 9, 00)
    					)
                    
			};
			
			for(Game g : games) {
				repository.save(g);
			}
		};
	}
	
	@Bean
	public CommandLineRunner testQueryMethods(GameRepository repository) {
		return (args) -> {
			logger.info("Printing all matches:");
			repository.findAll().forEach((game) -> logger.info(game.toString()));
			
			
		};
	}
	
	/*
	@Bean
	public CommandLineRunner testArenaGateway(GameRepository repository, ArenaGateway gateway) {
		return (args) ->{
			
			Game game = repository.findAll().get(0);
			if(game != null) {
				gateway.createGame(game);
			}
			
			
		};
	}
	*/
	

}





















