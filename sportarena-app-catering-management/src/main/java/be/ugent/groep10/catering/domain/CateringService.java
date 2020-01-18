package be.ugent.groep10.catering.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ugent.groep10.catering.persistence.CateringScheduleRepository;
import be.ugent.groep10.catering.persistence.ScheduleItemRepository;

@Service
public class CateringService {
	private final ScheduleItemRepository scheduleItemRepository;
	private final CateringScheduleRepository cateringScheduleRepository;
	
	@Autowired
	public CateringService(ScheduleItemRepository scheduleItemRepository, 
			CateringScheduleRepository cateringScheduleRepository) {
		this.scheduleItemRepository = scheduleItemRepository;
		this.cateringScheduleRepository = cateringScheduleRepository;
	}
	
	public ScheduleItem findEvent(long id) {
		final List<ScheduleItem> events = this.scheduleItemRepository.findById(id);
		if(events.isEmpty() || events.size() > 1) {
			return null;
		}
		else {
			return events.get(0);
		}
	}
	
	public void insertNewSchedule(ScheduleItem scheduleItem) {
		//Update scheduleitem database
		scheduleItemRepository.save(scheduleItem);
		
		//Update cateringschedule database
		CateringSchedule newSchedule = new CateringSchedule(scheduleItem);
		cateringScheduleRepository.save(newSchedule);
	}
}
