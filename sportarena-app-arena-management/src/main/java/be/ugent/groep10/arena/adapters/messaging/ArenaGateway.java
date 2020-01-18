package be.ugent.groep10.arena.adapters.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import be.ugent.groep10.arena.domain.Game;
import be.ugent.groep10.arena.persistence.GameRepository;

@MessagingGateway
public interface ArenaGateway {

	@Gateway(requestChannel = Channels.GAME_CREATED_EVENT)
	void createGame(CreateGameRequest request);
	
	@Gateway(requestChannel = Channels.GAME_ENDED_EVENT)
	void endGame(EndGameRequest request);
	
}
