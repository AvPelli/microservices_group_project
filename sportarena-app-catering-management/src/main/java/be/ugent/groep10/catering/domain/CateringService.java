package be.ugent.groep10.catering.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ugent.groep10.catering.adapters.messaging.MessageGateway;
import be.ugent.groep10.catering.adapters.messaging.RegisterResponse;
import be.ugent.groep10.catering.persistence.CateringCompanyRepository;
import be.ugent.groep10.catering.persistence.CateringScheduleRepository;

@Service
public class CateringService {
	private final MessageGateway gateway;
	private final CateringCompanyRepository cateringCompanyRepository;
	private final CateringScheduleRepository cateringScheduleRepository;
	
	@Autowired
	public CateringService(MessageGateway gateway, CateringCompanyRepository cateringCompanyRepository, CateringScheduleRepository cateringScheduleRepository) {
		this.gateway = gateway;
		this.cateringCompanyRepository = cateringCompanyRepository;
		this.cateringScheduleRepository = cateringScheduleRepository;
	}
	
	public CateringSchedule findEvent(String id) {
		final List<CateringSchedule> events = this.cateringScheduleRepository.findBySportEventId(id);
		if(events.isEmpty() || events.size() > 1) {
			return null;
		}
		else {
			return events.get(0);
		}
	}
	
	public CateringCompany insertNewCompany(CateringCompany company) {
		System.out.println("In de methode");
		if(cateringCompanyRepository.findById(company.getCompanyId()).isPresent()) {
			//Company is al geregistreerd
			this.gateway.registerResult(new RegisterResponse(company.getCompanyId(), false));
			return null;
		} else {
			CateringCompany newCompany = cateringCompanyRepository.save(company);
			if(newCompany==null) {
				//Company registratie gelukt
				this.gateway.registerResult(new RegisterResponse(company.getCompanyId(), true));
				return null;
			}
		}	
		return company;
	}
	
	public void insertNewSchedule(CateringSchedule scheduleItem) {
		//Update scheduleitem database
		cateringScheduleRepository.save(scheduleItem);
	}
	
	public void deleteCompany(String companyId) {
		cateringCompanyRepository.deleteById(companyId);
	}
}
