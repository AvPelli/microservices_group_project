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
@RequestMapping("/games")
public class ArenaRestController {
	
	Logger logger = LoggerFactory.getLogger(ArenaRestController.class);
	
	private final GameRepository gameRepository;
	private final ArenaGateway gateway;
	
	@Autowired
	public ArenaRestController(GameRepository gameRepository, ArenaGateway gateway) {
		this.gameRepository = gameRepository;
		this.gateway = gateway;
	}

	/*DUMMY*/
	public Iterable<Game> getAllGames(){
		return this.gameRepository.findAll();
	}
	
	/*DUMMY*/
	@GetMapping("/{id}")
	public Game getGame(@PathVariable("id") String id){
		return this.gameRepository.findById(id).orElse(null);
	}
	
	/*DUMMY*/
	@GetMapping("sportclub/{sportclubId}")
	public Iterable<Game> getGamesFromSportclub(@PathVariable("sportclubId") String sportclubId){
		return this.gameRepository.findBySportclubId(sportclubId);
	}
	
	/*DUMMY*/
	@CrossOrigin
	@GetMapping("/status/{gameStatus}")
	public Iterable<Game> getGamesWithStatus(@PathVariable("gameStatus") String gameStatus){
		//TODO Exception handling
		GameStatus gs = GameStatus.valueOf(gameStatus.toUpperCase());
		return this.gameRepository.findByGameStatus(gs);
		
	}
	
	
	/*TODO: Give all available and unavailable dates
	@GetMapping("/schedule")
	public Iterable<Game> getSchedule(){
		return this.gameRepository.findAll(); // WRONG
	}
	*/
	
	
	@PostMapping()
	public Iterable<Game> addGame(@RequestBody Game game) {
		
		// TODO: Check if dateTimes are in the future
		// TODO: Check schedule first
		this.gameRepository.save(game);
		
		return this.gameRepository.findAll();
	}
	
	@PostMapping("/create")
	public boolean createGame(@RequestBody Game game) {
		
		// TODO: Check if dateTimes are in the future
		// TODO: Check schedule first
		game.setGameStatus(GameStatus.ACTIVE);
		this.gameRepository.save(game);
		logger.info(game.toString());
		gateway.createGame(new CreateGameRequest(game.getId(), game.getDateTimeBegin(), game.getDateTimeEnd()));
		return true;
	}
	
	
	// Set the score of a game
	@PostMapping("/setscore/{id}")
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
	@PostMapping("/setreclame/{id}")
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
