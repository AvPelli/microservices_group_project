package be.ugent.groep10.gamblings.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import be.ugent.groep10.gamblings.adapters.messaging.BettingCommandHandler;
import be.ugent.groep10.gamblings.adapters.messaging.CashoutRequest;
import be.ugent.groep10.gamblings.domain.Bet;
import be.ugent.groep10.gamblings.domain.BettableGame;
import be.ugent.groep10.gamblings.persistence.BetRepository;
import be.ugent.groep10.gamblings.persistence.BettableGameRepository;

@RestController
@RequestMapping("betting")
public class BettingRestController {
	private final BettableGameRepository bettableGameRepository;
	private final BetRepository betRepository;
	private final BettingCommandHandler bettingCommandHandler;
	
	@Autowired
	public BettingRestController(BettableGameRepository bettableGameRepository, 
			BetRepository betRepository,
			BettingCommandHandler bettingCommandHandler) {
		this.bettableGameRepository = bettableGameRepository;
		this.betRepository = betRepository;
		this.bettingCommandHandler = bettingCommandHandler;
	}
	
	@GetMapping("/available_bets")
	public Iterable<BettableGame> getAVailableBets(){
		return bettableGameRepository.findAvailableBets();
	}
	
	
	
	//Requests van API gateway
	@PostMapping("/cash_out")
	public void cashOut(@RequestParam long id, @RequestParam double tokens) {
		CashoutRequest request = new CashoutRequest(id, tokens);
		bettingCommandHandler.cashOut(request);
	}
	
	@PostMapping("/purchase_tokens")
	public void purchaseTokens() {
		
	}
}
