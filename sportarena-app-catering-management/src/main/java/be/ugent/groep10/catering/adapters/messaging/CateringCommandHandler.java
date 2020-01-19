package be.ugent.groep10.catering.adapters.messaging;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import be.ugent.groep10.catering.domain.CateringCompany;
import be.ugent.groep10.catering.domain.CateringSchedule;
import be.ugent.groep10.catering.domain.CateringService;
import be.ugent.groep10.catering.persistence.CateringScheduleRepository;

/*
 * Verwerking kafka messages
 * 
 */
@Service
public class CateringCommandHandler {
	
	private static Logger logger = LoggerFactory.getLogger(CateringCommandHandler.class);
	private final CateringService cateringService;
	private final CateringScheduleRepository cateringScheduleRepository;
	
	@Autowired
	public CateringCommandHandler(CateringService cateringService, CateringScheduleRepository cateringScheduleRepository) {
		this.cateringService = cateringService;
		this.cateringScheduleRepository = cateringScheduleRepository;
	}
	
	/*
	 * Handle game created messages
	 */
	@StreamListener(Channels.GAME_CREATED_EVENT)
	public void newEventCreated(NewEventRequest request){
		logger.info("New game created : " + request.getSportEventId() + " from " + request.getDateTimeBegin().toString() 
				+ " to " + request.getDateTimeEnd().toString());
		
		CateringSchedule item = this.cateringService.findEvent(request.getSportEventId());
		if(item == null) {
			item = new CateringSchedule(request.getSportEventId(),request.getDateTimeBegin(),request.getDateTimeEnd(),"event",0);
			cateringService.insertNewSchedule(item);
		}
	}
	
	
	/*
	 * Handle seat update messages
	 */
	@StreamListener(Channels.UPDATE_OCCUPANCY)
	public void updateSeats(SeatOccupationUpdate seatUpdate) {
		logger.info("Seats updated for event :" + seatUpdate.getSportEventId() + ", new ammount of seats: " + seatUpdate.getOccupancy());
		List<CateringSchedule> items = this.cateringScheduleRepository.findBySportEventId(seatUpdate.getSportEventId());
		if(items != null) {
			CateringSchedule item = items.get(0);
			//Bereken nieuwe aantal trucks nodig
			item.setSeatOccupation(seatUpdate.getOccupancy());
			int trucks_needed = item.calculateFoodTrucks();
			item.setTotalFoodTrucksNeeded(trucks_needed);
			cateringScheduleRepository.save(item);
		}
	}
	
	/*
	 * REGISTRATION
	 */
	@StreamListener(Channels.REGISTER_CATERING)
	public void registerCatering(CateringCompany company) {
		logger.info("---------registerCatering-------");
		logger.info(company.toString());
		cateringService.insertNewCompany(company);
	}
	
	@StreamListener(Channels.REGISTER_CATERING_TIMEOUT)
	public void registerCateringTimeout(String companyId) {
		cateringService.deleteCompany(companyId);
	}
}
