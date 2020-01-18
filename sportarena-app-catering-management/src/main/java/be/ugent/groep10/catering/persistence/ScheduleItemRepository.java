package be.ugent.groep10.catering.persistence;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import be.ugent.groep10.catering.domain.ScheduleItem;

@RepositoryRestResource(collectionResourceRel = "items", path = "items") 
public interface ScheduleItemRepository 
	extends PagingAndSortingRepository<ScheduleItem, Integer> {
	
	List<ScheduleItem> findByDateTimeBeginBetween(LocalDateTime startTime,LocalDateTime endTime); 
	
	List<ScheduleItem> findBySportEventId(String id);
}
