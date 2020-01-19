package be.ugent.groep10.staff.adapters.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {
	
	static final String REGISTER_STAFF = "register_staff";
	static final String REGISTER_STAFF_TIMEOUT = "register_staff_timeout";
	
	static final String REGISTER_RESULT = "register_result";
	
	@Output(REGISTER_RESULT)
	MessageChannel registerResult();
	
	@Input(REGISTER_STAFF)
	SubscribableChannel registerStaff();
	@Input(REGISTER_STAFF_TIMEOUT)
	SubscribableChannel registerStaffTimeout();
	

}
