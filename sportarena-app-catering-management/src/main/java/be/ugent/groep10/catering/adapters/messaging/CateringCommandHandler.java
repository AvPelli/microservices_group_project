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
	
	/*
	 * Handle game created messages
	 */
	@StreamListener(Channels.GAME_CREATED_EVENT)
	public void newEventCreated(NewEventRequest request){
		logger.info("New game created : " + request.getEventId() + " from " + request.getStartTime().toString() 
				+ " to " + request.getEndTime().toString());
		
		ScheduleItem item = this.cateringService.findEvent(request.getEventId());
		if(item == null) {
			item = new ScheduleItem(request.getEventId(),request.getStartTime(),request.getEndTime(),"event",0);
			cateringService.insertNewSchedule(item);
		}
	}
	
	
	/*
	 * Handle seat update messages
	 */
	@StreamListener(Channels.UPDATE_OCCUPANCY)
	public void updateSeats(SeatOccupationUpdate seatUpdate) {
		logger.info("Seats updated for event :" + seatUpdate.getSportEventId() + ", new ammount of seats: " + seatUpdate.getOccupancy());
	}
}
