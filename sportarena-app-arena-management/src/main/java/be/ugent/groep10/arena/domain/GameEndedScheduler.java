package be.ugent.groep10.arena.domain;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import be.ugent.groep10.arena.adapters.messaging.ArenaGateway;
import be.ugent.groep10.arena.adapters.messaging.EndGameRequest;
import be.ugent.groep10.arena.persistence.GameRepository;


@Component
public class GameEndedScheduler {

	Logger logger = LoggerFactory.getLogger(GameEndedScheduler.class);
	private GameRepository gameRepository;
	private ArenaGateway gateway;
	
	@Autowired
	public GameEndedScheduler(GameRepository gameRepository, ArenaGateway gateway) {
		this.gameRepository = gameRepository;
		this.gateway = gateway;
	}
	
	@Scheduled(cron = "*/8 * * * * *")
	public void checkForEndedGames() {
		logger.info("Check dateTimes...");
		
		LocalDateTime currentDateTime = LocalDateTime.now();
		
		// Active games that are finished AND score is set -> FinishedStatus + MongoDB-update + Message
		for(Game game : gameRepository.findByGameStatus(GameStatus.ACTIVE)) {
			if(game.getDateTimeEnd().isBefore(currentDateTime) && game.getScore().isSet()) {
				
				
				logger.info("GAME_END of " + game.toString());
				
				game.setGameStatus(GameStatus.FINISHED);
				
				gameRepository.save(game);
				
				gateway.endGame(new EndGameRequest(game.getId()));
				
			}
		}
		// Planned games that are starting -> ActiveStatus + MongoDB-update
		for(Game game : gameRepository.findByGameStatus(GameStatus.PLANNED)) {
			if(game.getDateTimeBegin().isBefore(currentDateTime)) {
				
				
				logger.info("GAME_START of " + game.toString());
				
				game.setGameStatus(GameStatus.ACTIVE);
				gameRepository.save(game);
				
			}
		}
		
	}

}
