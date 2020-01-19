package be.ugent.groep10.gamblings.adapters;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import be.ugent.groep10.gamblings.adapters.messaging.CashOutResponse;
import be.ugent.groep10.gamblings.adapters.messaging.PaymentResponse;
import be.ugent.groep10.gamblings.domain.BettingService;
import be.ugent.groep10.gamblings.domain.CashOutListener;
import be.ugent.groep10.gamblings.domain.PayTokensListener;
import be.ugent.groep10.gamblings.domain.Wallet;
import be.ugent.groep10.gamblings.persistence.WalletRepository;

@RestController
public class WalletRESTController implements PayTokensListener, CashOutListener{

	private final WalletRepository walletRepository;
	private final Map<String, DeferredResult<String>> payTokensDeferredResults;
	private final Map<String, DeferredResult<String>> cashOutDeferredResults;
	private BettingService bettingService;
	
	@Autowired
	public WalletRESTController(WalletRepository walletRepository, BettingService bettingService) {
		this.walletRepository = walletRepository;
		this.bettingService = bettingService;
		this.payTokensDeferredResults = new HashMap<String, DeferredResult<String>>(20);
		this.cashOutDeferredResults = new HashMap<String, DeferredResult<String>>(20);
	}
	
	
	
	@GetMapping("/wallets")
	public Iterable<Wallet> getAllWallets(){
		return this.walletRepository.findAll();
	}
	
	@GetMapping("/wallets/{id}")
	public Wallet getWallet(@PathVariable("id") String id) {
		return this.walletRepository.findByOwnerId(id).orElse(null);
	}
	
	@PostMapping("/wallets")
	public Wallet addWallet(@RequestBody Wallet wallet) {
		this.walletRepository.save(wallet);
		return this.walletRepository.findByOwnerId(wallet.getOwnerId()).orElse(null);
	}
	
	@PostConstruct
	private void registerPayTokensListener() {
		bettingService.registerPayTokensListener(this);
	}
	
	
	@PostMapping("/purchase_tokens/{owner_id}")
	@ResponseBody
	public DeferredResult<String> purchaseTokens(@PathVariable("owner_id") String ownerId, @RequestParam double tokens) {
		DeferredResult<String> deferredResult = new DeferredResult<>(10000l);
		
		deferredResult.onTimeout(() -> {
			bettingService.payTokensTimeout(ownerId, tokens);
			deferredResult.setErrorResult("Request timeout occured.");
		});
		
		this.payTokensDeferredResults.put(ownerId, deferredResult);
		
		try {
			bettingService.payTokens(ownerId, tokens);
		} 
		catch (Exception e) {
			payTokensDeferredResults.remove(ownerId);
			deferredResult.setErrorResult("Failed to pay tokens.");
		}
		
		return deferredResult;	
	}
	
	@PostConstruct
	private void registerCashOutListener() {
		bettingService.registerCashOutListener(this);
	}

	@PostMapping("/cash_out")
	@ResponseBody
	public DeferredResult<String> cashOut(@RequestParam String ownerId, @RequestParam double tokens) {
		DeferredResult<String> deferredResult = new DeferredResult<>(10000l);
		
		deferredResult.onTimeout(() -> {
			bettingService.cashOutTimeout(ownerId, tokens);
			deferredResult.setErrorResult("Request timeout occured.");
		});
		
		this.cashOutDeferredResults.put(ownerId, deferredResult);
		
		try {
			bettingService.cashOut(ownerId, tokens);
		} 
		catch (Exception e) {
			cashOutDeferredResults.remove(ownerId);
			deferredResult.setErrorResult("Failed to cash out.");
		}
		
		return deferredResult;	
	}



	@Override
	public void onRegisterResult(CashOutResponse cashOutResponse) {
		DeferredResult<String> deferredResult = this.cashOutDeferredResults.remove(cashOutResponse.getMemberId());
		if (deferredResult != null && !deferredResult.isSetOrExpired()) {
			System.out.println("Setting result");
			bettingService.cashOutProcessResult(cashOutResponse);
			
			if(cashOutResponse.isSucceeded()) {
				deferredResult.setResult(cashOutResponse.getMemberId());
			} else {
				deferredResult.setErrorResult(cashOutResponse.getMemberId());
			}
			
		} else {
			System.out.println("defereredResult: " + deferredResult);
		}
		
	}



	@Override
	public void onRegisterResult(PaymentResponse paymentResponse) {
		DeferredResult<String> deferredResult = this.payTokensDeferredResults.remove(paymentResponse.getMemberId());
		if (deferredResult != null && !deferredResult.isSetOrExpired()) {
			System.out.println("Setting result");
			bettingService.payTokensProcessResult(paymentResponse);
			
			if(paymentResponse.isSucceeded()) {
				deferredResult.setResult(paymentResponse.getMemberId());
			} else {
				deferredResult.setErrorResult(paymentResponse.getMemberId());
			}
			
		} else {
			System.out.println("defereredResult: " + deferredResult);
		}
		
	}
	
	


}
