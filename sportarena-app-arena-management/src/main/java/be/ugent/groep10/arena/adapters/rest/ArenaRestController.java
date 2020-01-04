package be.ugent.groep10.arena.adapters.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.ugent.groep10.arena.domain.Game;
import be.ugent.groep10.arena.domain.GameStatus;
import be.ugent.groep10.arena.persistence.GameRepository;


@RestController
@RequestMapping("/games")
public class ArenaRestController {
	
private final GameRepository gameRepository;
	
	@Autowired
	public ArenaRestController(GameRepository gameRepository) {
		this.gameRepository = gameRepository;
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
	
	/*TODO: Check schedule first
	@PostMapping()
	public Iterable<Game> addGame(@RequestBody Game game) {
		this.gameRepository.save(game);
		return this.gameRepository.findAll();
	}
	*/
}
