package be.ugent.groep10.gamblings.persistence;

import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import be.ugent.groep10.gamblings.domain.Bet;

@Repository
public interface BetRepository extends MongoRepository<Bet, String>{
	
	public List<Bet> findBetsByPlacedByMember(String id);
	
	public List<Bet> findBetsByBettableGameId(String matchId);
	
	
}
