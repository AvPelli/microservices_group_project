package be.ugent.groep10.staff.adapters.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

import be.ugent.groep10.staff.domain.Staff;
import be.ugent.groep10.staff.domain.StaffService;


@Service
public class StaffCommandHandler {
	
	private static Logger logger = LoggerFactory.getLogger(StaffCommandHandler.class);
	
	private final StaffService staffService;

	@Autowired
	public StaffCommandHandler(StaffService staffService) {
		this.staffService = staffService;
	}
	
	@StreamListener(Channels.REGISTER_STAFF)
	public void registerStaff(Staff staff) {
		logger.info("---------registerStaff-------");
		logger.info(staff.getId());
		staffService.createStaff(staff);
	}
	
	@StreamListener(Channels.REGISTER_STAFF_TIMEOUT)
	public void registerStaffTimeout(String staffId) {
		staffService.deleteStaff(staffId);
	}
	
}
