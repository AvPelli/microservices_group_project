package be.ugent.groep10.gamblings.adapters;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import be.ugent.groep10.gamblings.domain.BettableGame;
import be.ugent.groep10.gamblings.domain.BettingService;
import be.ugent.groep10.gamblings.domain.Prediction;
import be.ugent.groep10.gamblings.persistence.BetRepository;
import be.ugent.groep10.gamblings.persistence.BettableGameRepository;

@RestController
@RequestMapping("betting")
public class BettableGameRESTController {

	private final BettableGameRepository bettableGameRepository;
	private final BettingService bettingService;
	
	
	@Autowired
	public BettableGameRESTController(BettableGameRepository bettableGameRepository, BettingService bettingService) {
		this.bettableGameRepository = bettableGameRepository;
		this.bettingService = bettingService;
	}
	
	@GetMapping("/bettable_games")
	public Iterable<BettableGame> getBettableGames(){
		return bettableGameRepository.findAll();
	}
	
	@GetMapping("/predictions/{matchId}")
	public Iterable<Prediction> getAvailablePredictions(@PathVariable("matchId") String matchId){
		List<BettableGame> bettableGame = bettableGameRepository.findByMatchId(matchId);
		if(bettableGame.size() < 1) {
			return null;
		}
		return bettingService.generatePredictions(matchId);
	}
	
	
	

}
