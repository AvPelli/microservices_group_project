package be.ugent.groep10.catering.persistence;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import be.ugent.groep10.catering.domain.CateringSchedule;

@RepositoryRestResource(collectionResourceRel = "schedules", path = "schedules") 
public interface CateringScheduleRepository 
	extends PagingAndSortingRepository<CateringSchedule, Integer>{
	
	List<CateringSchedule> findBySportEventId(String id);
}
