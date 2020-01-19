package be.ugent.groep10.arena;

import java.time.LocalDateTime;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

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
import be.ugent.groep10.arena.adapters.messaging.ArenaGateway;
import be.ugent.groep10.arena.adapters.messaging.Channels;
import be.ugent.groep10.arena.adapters.messaging.CreateGameRequest;

@SpringBootApplication
@EnableBinding(Channels.class)
@EnableScheduling
public class SportarenaAppArenaManagementApplication {

	Logger logger = LoggerFactory.getLogger(SportarenaAppArenaManagementApplication.class);
			
	public static void main(String[] args) {
		SpringApplication.run(SportarenaAppArenaManagementApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner clearDatabase(GameRepository repository, ArenaGateway gateway) {
		return (args) -> {
			logger.info("Clearing database...");
			repository.deleteAll();
		}; 
	}
	
	@Bean
	public CommandLineRunner populateDatabase(GameRepository repository, ArenaGateway gateway) {
		return (args) -> {
			logger.info("Populating with new data...");
			
			int year = 2020;
			int month = 2;
			int day = 1;
			int hour = 20;
			int minute = 0;
			
			Game[] games = { 
	        	new Game(
	        		"FIFA", "Belgium", "Russia", "x", 
	        		LocalDateTime.of(year, month, day, hour, minute, 00), 
	        		LocalDateTime.of(year, month, day, hour + 2, minute, 00)
	    		),
	        	new Game(
	    			"FIFA", "Belgium", "Scotland", "xx", 
					LocalDateTime.of(year, month + 1, day, hour, minute, 00), 
					LocalDateTime.of(year, month + 1, day, hour + 2, minute, 00)
				),
	        	new Game(
	    			"FIFA","Belgium", "Cyprus", "xxx", 
					LocalDateTime.of(year, month + 2, day, hour, minute, 00), 
					LocalDateTime.of(year, month + 2, day, hour + 2, minute, 00)
				),
	        	new Game(
	    			"FIFA", "Belgium", "Kazachstan", "xxxx", 
					LocalDateTime.of(year, month + 3, day, hour, minute, 00), 
					LocalDateTime.of(year, month + 3, day, hour + 2, minute, 00)
				)
			};
			
			for(Game game : games) {
				repository.save(game);
				gateway.createGame(new CreateGameRequest(game.getId(), game.getDateTimeBegin(), game.getDateTimeEnd()));
			}
		};
	}
	
	//@Bean
	public CommandLineRunner testQueryMethods(GameRepository repository) {
		return (args) -> {
			logger.info("Printing all matches:");
			repository.findAll().forEach((game) -> logger.info(game.toString()));
			
			
		};
	}
	
}





















