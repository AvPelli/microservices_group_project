package be.ugent.groep10.catering.adapters.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import be.ugent.groep10.catering.domain.CateringService;
import be.ugent.groep10.catering.domain.ScheduleItem;

/*
 * Verwerking kafka messages
 * 
 */
@Service
public class CateringCommandHandler {
	
	private static Logger logger = LoggerFactory.getLogger(CateringCommandHandler.class);

	private final CateringService cateringService;
	
	@Autowired
	public CateringCommandHandler(CateringService cateringService) {
		this.cateringService = cateringService;
	}
	
	@StreamListener(Channels.NEW_EVENT)
	@SendTo(Channels.EVENT_REGISTERED)
	public NewEventResponse newEventCreated(NewEventRequest request){
		logger.info("New game created : " + request.getEventId() + " from " + request.getStart().toString() 
				+ " to " + request.getEinde().toString());
		
		final ScheduleItem item = this.cateringService.findEvent(request.getEventId());
		if(item != null) {
			return new NewEventResponse(request.getEventId(),true,"Event zit in de cateringservice database");
		} else {
			return new NewEventResponse(request.getEventId(), false, "Event kon niet toegevoegd worden");
		}
	}
}
