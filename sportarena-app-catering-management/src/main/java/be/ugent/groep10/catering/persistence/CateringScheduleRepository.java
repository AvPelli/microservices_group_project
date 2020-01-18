package be.ugent.groep10.catering.persistence;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import be.ugent.groep10.catering.domain.CateringSchedule;

@RepositoryRestResource(collectionResourceRel = "items", path = "items") 
public interface CateringScheduleRepository 
	extends PagingAndSortingRepository<CateringSchedule, Integer>{
	
	List<CateringSchedule> findBySportEventId(String id);
}
