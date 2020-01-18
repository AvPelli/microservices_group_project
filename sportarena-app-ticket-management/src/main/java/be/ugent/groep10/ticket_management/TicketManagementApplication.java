package be.ugent.groep10.ticket_management;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

import be.ugent.groep10.ticket_management.adapters.messaging.Channels;
import be.ugent.groep10.ticket_management.domain.Ticket;
import be.ugent.groep10.ticket_management.persistence.TicketRepository;

@SpringBootApplication
@EnableBinding(Channels.class)
public class TicketManagementApplication {

	Logger logger = LoggerFactory.getLogger(TicketManagementApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TicketManagementApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner clearDatabase(TicketRepository repository) {
		return (args) -> {
			logger.info("Clearing database...");
			repository.deleteAll();
		};
	}
	
	//@Bean
	public CommandLineRunner populateDatabase(TicketRepository repository) {
		return (args) -> {
			logger.info("Populating database with new data...");
			
			List<Ticket> tickets = new ArrayList<>();
			tickets.add(new Ticket("1", 1, 1, 1));
			tickets.add(new Ticket("1", 1, 1, 2));
			tickets.add(new Ticket("1", 1, 2, 1));
			tickets.add(new Ticket("1", 1, 1, 2));
			tickets.get(0).sellTicket();
			tickets.get(3).sellTicket();
			
			tickets.add(new Ticket("2", 1, 1, 1));
			tickets.add(new Ticket("2", 1, 1, 2));
			tickets.add(new Ticket("2", 1, 2, 1));
			tickets.add(new Ticket("2", 1, 1, 2));
			tickets.get(5).sellTicket();
			tickets.get(6).sellTicket();
		
			repository.saveAll(tickets);	
		};
	}
	
	//@Bean
	public CommandLineRunner testQueryMethods(TicketRepository repository) {
		return (args) -> {
			logger.info("Printing all tickets:");
			repository.findAll().forEach((ticket) -> logger.info(ticket.toString()));
			
			logger.info("Printing all tickets for sportevent 1:");
			repository.findBySportEventId("1").forEach((ticket) -> logger.info(ticket.toString()));
			
			logger.info("Printing all available tickets for sportevent 1:");
			repository.findBySportEventIdAndStatusIsNotSold("1").forEach((ticket) -> logger.info(ticket.toString()));
		
			logger.info("Printing all tickets for sportevent 2:");
			repository.findBySportEventId("2").forEach((ticket) -> logger.info(ticket.toString()));
			
			logger.info("Printing all available tickets for sportevent 2:");
			repository.findBySportEventIdAndStatusIsNotSold("2").forEach((ticket) -> logger.info(ticket.toString()));
			
		};
	}
}
