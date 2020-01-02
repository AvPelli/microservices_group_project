package be.ugent.groep10.gamblings.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.ugent.groep10.gamblings.domain.Bet;
import be.ugent.groep10.gamblings.domain.BettableGame;
import be.ugent.groep10.gamblings.persistence.BetRepository;
import be.ugent.groep10.gamblings.persistence.BettableGameRepository;

@RestController
@RequestMapping("betting")
public class BettingRestController {
	private final BettableGameRepository bettableGameRepository;
	private final BetRepository betRepository;
	
	@Autowired
	public BettingRestController(BettableGameRepository bettableGameRepository, BetRepository betRepository) {
		this.bettableGameRepository = bettableGameRepository;
		this.betRepository = betRepository;
	}
	
	@GetMapping("/available_bets")
	public Iterable<BettableGame> getAVailableBets(){
		return bettableGameRepository.findAvailableBets();
	}
	
	
}
