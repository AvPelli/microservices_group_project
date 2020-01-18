package be.ugent.groep10.catering.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import be.ugent.groep10.catering.domain.CateringCompany;

@RepositoryRestResource(collectionResourceRel = "companies", path = "companies") 
public interface CateringCompanyRepository 
	extends CrudRepository<CateringCompany, String>{

	List<CateringCompany> findByCompanyId(String companyId);
}
