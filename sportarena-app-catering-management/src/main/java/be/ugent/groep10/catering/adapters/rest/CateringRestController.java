package be.ugent.groep10.catering.adapters.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.ugent.groep10.catering.domain.CateringCompany;
import be.ugent.groep10.catering.domain.CateringService;
import be.ugent.groep10.catering.persistence.CateringCompanyRepository;

@RestController
@RequestMapping("catering")
public class CateringRestController {
	private final CateringCompanyRepository cateringCompanyRepository;
	private final CateringService cateringService;
	
	@Autowired
	public CateringRestController(CateringCompanyRepository cateringCompanyRepository, CateringService cateringService) {
		this.cateringCompanyRepository = cateringCompanyRepository;
		this.cateringService = cateringService;
	}
	
	@GetMapping
	public Iterable<CateringCompany> getAllCateringCompanies(){
		System.out.println("--------------------FindAllCatering--------------------");
		return cateringCompanyRepository.findAll();
	}
	
	@PostMapping()
	public CateringCompany addCompany(@RequestBody CateringCompany company){
//		this.memberRepository.save(member);
//		return this.memberRepository.findById(member.getId()).orElse(null);
		CateringCompany newCompany = cateringService.insertNewCompany(company);
		return newCompany;
	}
}
