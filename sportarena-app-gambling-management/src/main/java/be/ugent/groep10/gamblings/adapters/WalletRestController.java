package be.ugent.groep10.gamblings.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	public Wallet getWallet(@PathVariable("id") long id) {
		return this.walletRepository.findById(id).orElse(null);
	}
	
	@PostMapping()
	public Wallet addWallet(@RequestBody Wallet wallet) {
		this.walletRepository.save(wallet);
		return this.walletRepository.findById(wallet.getOwnerId()).orElse(null);
	}
}
