package be.ugent.groep10.gamblings.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import be.ugent.groep10.gamblings.domain.Wallet;

@Repository
public interface WalletRepository extends MongoRepository<Wallet, Long>{
	
	Optional<Wallet> findByOwnerId(String id);

}
