package be.ugent.groep10.gamblings.persistence;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import be.ugent.groep10.gamblings.domain.BettableGame;

@Repository
@RepositoryRestResource(collectionResourceRel = "bettableGames", path = "bettableGames")
public interface BettableGameRepository extends JpaRepository<BettableGame, Long> {
	
	@Query("select b from bettablegame b where b.playedOn > CURRENT_DATE")
	List<BettableGame> findAvailableBets();

}
