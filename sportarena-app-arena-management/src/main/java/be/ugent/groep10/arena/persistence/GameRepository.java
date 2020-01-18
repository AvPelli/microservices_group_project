package be.ugent.groep10.arena.persistence;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import be.ugent.groep10.arena.domain.Game;
import be.ugent.groep10.arena.domain.GameStatus;


@RepositoryRestResource(collectionResourceRel = "games", path = "games") 
public interface GameRepository extends MongoRepository<Game, String>{

	
	public List<Game> findBySportclubId(String sportclubId);
	
	public List<Game> findByGameStatus(GameStatus gameStatus);
	
	
}
