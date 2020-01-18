package be.ugent.groep10.catering.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ugent.groep10.catering.persistence.CateringScheduleRepository;

@Service
public class CateringService {
	private final CateringScheduleRepository cateringScheduleRepository;
	
	@Autowired
	public CateringService(CateringScheduleRepository cateringScheduleRepository) {
		this.cateringScheduleRepository = cateringScheduleRepository;
	}
	
	public CateringSchedule findEvent(String id) {
		final List<CateringSchedule> events = this.cateringScheduleRepository.findBySportEventId(id);
		if(events.isEmpty() || events.size() > 1) {
			return null;
		}
		else {
			return events.get(0);
		}
	}
	
	public void insertNewSchedule(CateringSchedule scheduleItem) {
		//Update scheduleitem database
		cateringScheduleRepository.save(scheduleItem);
		
		//Update cateringschedule database
		CateringSchedule newSchedule = new CateringSchedule(scheduleItem);
		cateringScheduleRepository.save(newSchedule);
	}
}
