package be.ugent.groep10.ticket_management.domain;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import be.ugent.groep10.ticket_management.persistence.TicketRepository;

@Service
public class TicketService {
	
	Logger logger = LoggerFactory.getLogger(TicketService.class);
	
	private static final int NUMBER_SECTIONS = 1;
	private static final int NUMBER_ROWS = 4;
	private static final int NUMBER_SEATS = 5;
	
	private final TicketRepository repository;

	public TicketService(TicketRepository repository) {
		this.repository = repository;
	}
	
	public void createTickets(String sportEventId) throws EventExistsException {
		List<Ticket> tickets = repository.findBySportEventId(sportEventId);
		if (tickets.isEmpty()) {
			for (int section = 1; section <= NUMBER_SECTIONS; section++) {
				for (int row = 1; row <= NUMBER_ROWS; row++) {
					for (int seat = 1; seat <= NUMBER_SEATS; seat++) {
						Ticket ticket = new Ticket(sportEventId, section, row, seat);
						repository.save(ticket);
						logger.info(ticket.toString());
					}
				}
			}
		}
		else {
			throw new EventExistsException(sportEventId);
		}
	}
	
	public void endGame(String sportEventId) {
		List<Ticket> tickets = repository.findBySportEventId(sportEventId);
		if (!tickets.isEmpty()) {
			for (Ticket ticket : tickets) {
				if (ticket.getStatus() == TicketStatus.AVAILABLE) {
					ticket.setStatus(TicketStatus.UNAVAILABLE);
				}
			}
		}
		repository.saveAll(tickets);
		for (Ticket ticket : tickets) {
			logger.info(ticket.toString());
		}
	}
	
}
