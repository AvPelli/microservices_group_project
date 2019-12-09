package be.ugent.groep10.gamblings.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import be.ugent.groep10.gamblings.domain.Bet;

@Repository
public interface BetRepository extends CrudRepository<Bet, Long>{
	
	public List<Bet> findBetsByPlacedByMember(long id);

	public List<Bet> findBetsByScheduleItemId(long id);
}
