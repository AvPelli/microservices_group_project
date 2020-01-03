package be.ugent.groep10.ticket_management.domain;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import be.ugent.groep10.ticket_management.persistence.TicketRepository;

@Service
public class TicketService {
	
	Logger logger = LoggerFactory.getLogger(TicketService.class);
	
	private static final int NUMBER_SECTIONS = 4;
	private static final int NUMBER_ROWS = 10;
	private static final int NUMBER_SEATS = 25;
	
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
						repository.save(new Ticket(sportEventId, section, row, seat));
					}
				}
			}
		}
		else {
			throw new EventExistsException(sportEventId);
		}
	}
	
}
