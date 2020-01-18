package be.ugent.groep10.ticket_management.adapters.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.ugent.groep10.ticket_management.adapters.messaging.MessageGateway;
import be.ugent.groep10.ticket_management.adapters.messaging.TicketCommandHandler;
import be.ugent.groep10.ticket_management.adapters.messaging.UpdateOccupancyRequest;
import be.ugent.groep10.ticket_management.domain.Ticket;
import be.ugent.groep10.ticket_management.domain.TicketStatus;
import be.ugent.groep10.ticket_management.persistence.TicketRepository;

@RestController
//@RequestMapping("tickets")
public class TicketRestController {
	
	Logger logger = LoggerFactory.getLogger(TicketRestController.class);
	
	private static final int TRESHOLD = 50;
	
	private final TicketRepository repository;
	private final MessageGateway gateway;
	
	@Autowired
	public TicketRestController(TicketRepository repository, MessageGateway gateway) {
		this.repository = repository;
		this.gateway = gateway;
	}
	
	@GetMapping("/buy/{id}")
	public boolean buyTickey(@PathVariable("id") String ticketId) {
		Ticket ticket = repository.findById(ticketId).get();
		if (ticket.getStatus() == TicketStatus.AVAILABLE) {
			ticket.setStatus(TicketStatus.SOLD);
			logger.info("Success: ticket " + ticketId + " sold");
			repository.save(ticket);
			int sold = repository.findBySportEventIdAndStatusIsSold(ticket.getSportEventId()).size();
			if (sold % TRESHOLD == 0) {
				UpdateOccupancyRequest request = new UpdateOccupancyRequest(ticket.getSportEventId(), sold);
				gateway.updateOccupancy(request);
			}
			return true;
		}
		else {
			logger.info("Error: ticket " + ticketId + " already sold");
			return false;
		}
	}
	
	@GetMapping("/sold/{sportEventId}")
	public List<Ticket> getTickets(@PathVariable("sportEventId") String sportEventId) {
		List<Ticket> tickets = repository.findBySportEventIdAndStatusIsSold(sportEventId);
		return tickets;
	}
	
	
	/*
	@GetMapping("/{sportEventId}")
	public List<Ticket> getTickets(@PathVariable("sportEventId") String sportEventId) {
		List<Ticket> tickets = repository.findBySportEventId(sportEventId);
		return tickets;
	}
	*/

}
