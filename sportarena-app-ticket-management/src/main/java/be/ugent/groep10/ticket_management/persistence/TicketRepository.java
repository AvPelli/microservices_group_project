package be.ugent.groep10.ticket_management.persistence;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import be.ugent.groep10.ticket_management.domain.Ticket;

public interface TicketRepository extends MongoRepository<Ticket, String>{
	
	//Ticket findOneBySportEventId(String sportEventId);

	List<Ticket> findBySportEventId(String sportEventId);
	
	@Query("{ 'sportEventId' : ?0, 'status' : {$ne : 'SOLD'}}")
	List<Ticket> findBySportEventIdAndStatusIsNotSold(String sportEventId);
	
	@Query("{ 'sportEventId' : ?0, 'status' : 'SOLD'}")
	List<Ticket> findBySportEventIdAndStatusIsSold(String sportEventId);
	
}
