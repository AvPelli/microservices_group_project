package be.ugent.groep10.ticket_management.persistence;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import be.ugent.groep10.ticket_management.domain.Ticket;

public interface TicketRepository extends MongoRepository<Ticket, String>{

	List<Ticket> findBySportEventId(int sportEventId);
	
	@Query("{ 'sportEventId' : ?0, 'status' : {$ne : 'SOLD'}}")
	List<Ticket> findBySportEventIdAndStatusIsNotSold(int sportEventId);
	
}
