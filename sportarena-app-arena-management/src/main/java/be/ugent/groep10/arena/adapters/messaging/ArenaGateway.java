package be.ugent.groep10.arena.adapters.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import be.ugent.groep10.arena.domain.Game;

@MessagingGateway
public interface ArenaGateway {

	@Gateway(requestChannel = Channels.GAME_CREATED_EVENT)
	void createGame(Game game);
	
	@Gateway(requestChannel = Channels.GAME_ENDED_EVENT)
	void endGame(Game game);
}
