package be.ugent.groep10.arena.adapters.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.ugent.groep10.arena.SportarenaAppArenaManagementApplication;
import be.ugent.groep10.arena.adapters.messaging.ArenaGateway;
import be.ugent.groep10.arena.adapters.messaging.CreateGameRequest;
import be.ugent.groep10.arena.domain.Game;
import be.ugent.groep10.arena.domain.GameStatus;
import be.ugent.groep10.arena.domain.Score;
import be.ugent.groep10.arena.persistence.GameRepository;


@RestController
public class ArenaRestController {
	
	Logger logger = LoggerFactory.getLogger(ArenaRestController.class);
	
	private final GameRepository gameRepository;
	private final ArenaGateway gateway;
	
	@Autowired
	public ArenaRestController(GameRepository gameRepository, ArenaGateway gateway) {
		this.gameRepository = gameRepository;
		this.gateway = gateway;
	}
	
	@CrossOrigin
	@GetMapping("/games")
	public Iterable<Game> getAllGames(){
		return this.gameRepository.findAll();
	}
	
	
	@GetMapping("/games/{id}")
	public Game getGame(@PathVariable("id") String id){
		return this.gameRepository.findById(id).orElse(null);
	}
	
	@GetMapping("/games/sportclub/{sportclubId}")
	public Iterable<Game> getGamesFromSportclub(@PathVariable("sportclubId") String sportclubId){
		return this.gameRepository.findBySportclubId(sportclubId);
	}
	
	/*DUMMY*/
	@CrossOrigin
	@GetMapping("/games/status/{gameStatus}")
	public Iterable<Game> getGamesWithStatus(@PathVariable("gameStatus") String gameStatus){
		//TODO Exception handling
		GameStatus gs = GameStatus.valueOf(gameStatus.toUpperCase());
		return this.gameRepository.findByGameStatus(gs);
		
	}
	
	
	
	/* DUMMY
	@GetMapping("/schedule")
	public Iterable<Game> getSchedule(){
		return this.gameRepository.findAll(); 
	}
	*/
	
	
	

	@CrossOrigin
	@PostMapping("/games/create")
	public boolean createGame(@RequestBody Game game) {
		
		// TODO: Check if dateTimes are in the future
		// TODO: Check schedule first
		game.setGameStatus(GameStatus.PLANNED);
		Game game2 = new Game(game.getSportclubId(), game.getTeamA(), game.getTeamB(), "reclame", game.getDateTimeBegin(), game.getDateTimeEnd());
		this.gameRepository.save(game2);
		logger.info(game2.toString());
		gateway.createGame(new CreateGameRequest(game2.getId(), game2.getDateTimeBegin(), game2.getDateTimeEnd()));
		return true;
	}
	
	
	// Set the score of a game
	@PostMapping("/games/setscore/{id}")
	public Game setScore(@PathVariable("id") String id, @RequestBody Score score) {
		
		Game game = this.getGame(id);
		
		// TODO Only active games can be modified
		
		if(game != null) {
			game.setScore(score);
		}
		this.gameRepository.save(game);
		return this.getGame(id);
	}
	
	// Set the reclame for a game
	@PostMapping("/games/setreclame/{id}")
	public Game setReclame(@PathVariable("id") String id, @RequestBody String reclame) {
		
		Game game = this.getGame(id);
		
		// TODO Only active games can be modified
		
		if(game != null) {
			game.setReclame(reclame);
		}
		this.gameRepository.save(game);
		return this.getGame(id);
	}
	
}
