package be.ugent.groep10.gamblings.adapters;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import be.ugent.groep10.gamblings.domain.Bet;
import be.ugent.groep10.gamblings.domain.BettableGame;
import be.ugent.groep10.gamblings.domain.BettingService;
import be.ugent.groep10.gamblings.domain.Prediction;
import be.ugent.groep10.gamblings.domain.Wallet;
import be.ugent.groep10.gamblings.persistence.BetRepository;
import be.ugent.groep10.gamblings.persistence.BettableGameRepository;

@RestController
@RequestMapping("betting")
public class BetRESTController {

	private final BetRepository betRepository;
	private final BettingService bettingService;
	
	
	@Autowired
	public BetRESTController(BetRepository betRepository, BettingService bettingService) {
		this.betRepository = betRepository;
		this.bettingService = bettingService;
	}
	
	@GetMapping("/bets/{ownerId}")
	public Iterable<Bet> getAvailablePredictions(@PathVariable("ownerId") String ownerId){
		return betRepository.findBetsByPlacedByMember(ownerId);
	}
	
	@PostMapping("/place_bet/{userId}/{matchId}/{tokens}")
	public Bet addBet(@RequestBody Prediction prediction, @PathVariable("userId") String ownerId,@PathVariable("matchId") String matchId, @PathVariable("tokens") double tokens) {
		return this.bettingService.addBet(matchId, ownerId, prediction, tokens);
	}

}
