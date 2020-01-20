package be.ugent.groep10.gamblings.persistence;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import be.ugent.groep10.gamblings.domain.BettableGame;

@Repository
public interface BettableGameRepository extends MongoRepository<BettableGame, String> {
	
	List<BettableGame> findByMatchId(String matchId);

}
