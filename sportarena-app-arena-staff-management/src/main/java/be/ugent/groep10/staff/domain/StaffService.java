package be.ugent.groep10.staff.domain;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import be.ugent.groep10.staff.adapters.messaging.MessageGateway;
import be.ugent.groep10.staff.adapters.messaging.RegisterResponse;
import be.ugent.groep10.staff.persistence.StaffRepository;

@Service
public class StaffService {
	private static Logger logger = LoggerFactory.getLogger(StaffService.class);
	
	private final StaffRepository staffRepository;
	private final MessageGateway gateway;
	
	private Map<String, Integer> pendingMembers;
	
	@Autowired
	public StaffService(StaffRepository memberRepository, MessageGateway gateway) {
		this.staffRepository = memberRepository;
		this.gateway = gateway;
		//this.pendingMembers = new HashMap<String, Integer>();
	}
	
	public Staff createStaff(Staff staff) {
		logger.info("Inserting new staff");
		if(staffRepository.findById(staff.getId()).isPresent()) {
			logger.info("staff " + staff.getId() + " already present: register result = false");
			this.gateway.registerResult(new RegisterResponse(staff.getId(), false));
		} else {
			Staff newStaff = staffRepository.save(staff);
			if(newStaff!=null) {
				logger.info("staff " + staff.getId()  + " is added to the database: register result = true");
				this.gateway.registerResult(new RegisterResponse(staff.getId(), true));
			}
			else {
				this.gateway.registerResult(new RegisterResponse(staff.getId(), false));
			}
		}
		return staff;
	}
	
	public void deleteStaff(String staffId) {
		staffRepository.deleteById(staffId);
	}
	
	/*Aanpassen voor staff?
	@Scheduled(cron = "* * * * * *")
	public void updatePendingMembers() {
		this.pendingMembers.keySet().forEach((key) -> {
			this.pendingMembers.put(key, this.pendingMembers.get(key) -1);
			if(this.pendingMembers.get(key)<=0)
				this.pendingMembers.remove(key);
		});
	}
	
	public boolean removePendingMember(String memberId) {
		return this.pendingMembers.remove(memberId) != null;
	}
	*/
}
