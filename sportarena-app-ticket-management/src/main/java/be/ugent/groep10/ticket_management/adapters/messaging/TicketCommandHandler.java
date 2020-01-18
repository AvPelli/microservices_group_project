package be.ugent.groep10.ticket_management.adapters.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
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
	
	@StreamListener(Channels.GAME_CREATED_EVENT) 
	public void processCreateGameRequest(CreateGameRequest request) {
		logger.info("Request to create tickets for event " + request.getSportEventId());
		try {
			service.createTickets(request.getSportEventId());
			logger.info("Tickets created for event " + request.getSportEventId());
		}
		catch (EventExistsException e) {
			logger.info(e.getMessage());
		}
	}
	
	@StreamListener(Channels.GAME_ENDED_EVENT)
	public void processGameEndedRequest(EndGameRequest request) {
		String sportEventId = request.getSportEventId();
		service.endGame(sportEventId);
	}
	
}
