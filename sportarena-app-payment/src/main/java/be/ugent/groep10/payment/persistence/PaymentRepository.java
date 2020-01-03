package be.ugent.groep10.payment.persistence;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import be.ugent.groep10.payment.domain.Payment;

@RepositoryRestResource(collectionResourceRel = "transactions", path = "transactions") 
public interface PaymentRepository 
	extends PagingAndSortingRepository<Payment, Integer>{

}
