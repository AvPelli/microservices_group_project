package be.ugent.groep10.gamblings.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import be.ugent.groep10.gamblings.domain.Wallet;

@Repository
public interface WalletRepository extends CrudRepository<Wallet, Long>{

}
