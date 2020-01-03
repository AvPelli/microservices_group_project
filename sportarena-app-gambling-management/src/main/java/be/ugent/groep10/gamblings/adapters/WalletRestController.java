package be.ugent.groep10.gamblings.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.ugent.groep10.gamblings.domain.Wallet;
import be.ugent.groep10.gamblings.persistence.WalletRepository;

@RestController
@RequestMapping("wallet")
public class WalletRestController {
	private final WalletRepository walletRepository;
	
	@Autowired
	public WalletRestController(WalletRepository walletRepository) {
		this.walletRepository = walletRepository;
	}
	
	@GetMapping
	public Iterable<Wallet> getAllWallets(){
		return this.walletRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Wallet getWallet(@PathVariable("id") String id) {
		return this.walletRepository.findByOwnerId(id).orElse(null);
	}
	
	@PostMapping()
	public Wallet addWallet(@RequestBody Wallet wallet) {
		this.walletRepository.save(wallet);
		return this.walletRepository.findByOwnerId(wallet.getOwnerId()).orElse(null);
	}
	
	@PutMapping("/purchase_tokens/{owner_id}/{amount}")
	public Wallet purchaseTokens(@PathVariable("owner_id") String ownerId, @PathVariable("amount") double amount) {
		Wallet current = walletRepository.findByOwnerId(ownerId).orElse(null);
		if(current == null)
			return null;
		current.setTokens(current.getTokens() + amount);
		return walletRepository.save(current);
	}
}
