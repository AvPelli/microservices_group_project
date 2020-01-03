package be.ugent.groep10.gamblings.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import be.ugent.groep10.gamblings.domain.Bet;

@Repository
@RepositoryRestResource(collectionResourceRel = "bets", path = "bets")
public interface BetRepository extends JpaRepository<Bet, Long>{
	
	public List<Bet> findBetsByPlacedByMember(String id);

	//public List<Bet> findBetsByScheduleItemId(long id);
	
	public List<Bet> findBetsByBettableGameId(long id);
}
