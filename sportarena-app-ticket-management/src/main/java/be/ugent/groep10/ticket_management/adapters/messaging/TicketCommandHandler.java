package be.ugent.groep10.ticket_management.adapters.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import be.ugent.groep10.ticket_management.domain.EventExistsException;
import be.ugent.groep10.ticket_management.domain.TicketService;

@Service
public class TicketCommandHandler {

	Logger logger = LoggerFactory.getLogger(TicketCommandHandler.class);
	
	private final TicketService service;
	
	@Autowired
	public TicketCommandHandler(TicketService service) {
		this.service = service;
	}
	
	@StreamListener(Channels.CREATE_TICKETS)
	@SendTo(Channels.TICKETS_CREATED)
	public TicketsResponse createGame(TicketsRequest request) {
		logger.info("Request to create tickets for event " + request.getSportEventId());
		try {
			service.createTickets(request.getSportEventId());
			logger.info("Tickets created for event " + request.getSportEventId());
			return new TicketsResponse(request.getSportEventId(), true);
		}
		catch (EventExistsException e) {
			logger.info(e.getMessage());
			return new TicketsResponse(request.getSportEventId(), false);
		}
	}
	
}
