package be.ugent.groep10.catering.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ugent.groep10.catering.adapters.messaging.NewEventResponse;
import be.ugent.groep10.catering.persistence.ScheduleItemRepository;

@Service
public class CateringService {
	private final ScheduleItemRepository scheduleItemRepository;

	@Autowired
	public CateringService(ScheduleItemRepository scheduleItemRepository) {
		this.scheduleItemRepository = scheduleItemRepository;
	}
	
	public ScheduleItem findEvent(String id) {
		final List<ScheduleItem> events = this.scheduleItemRepository.findByEventId(id);
		if(events.isEmpty() || events.size() > 1) {
			return null;
		}
		else {
			return events.get(0);
		}
	}
	
	public NewEventResponse updateSchedule(ScheduleItem scheduleItem) {
		scheduleItemRepository.save(scheduleItem);
		return new NewEventResponse(scheduleItem.getEventId(), true, scheduleItem.getDescription());
	}
}
