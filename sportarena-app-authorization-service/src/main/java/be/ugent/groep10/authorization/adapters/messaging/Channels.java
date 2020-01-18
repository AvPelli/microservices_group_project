package be.ugent.groep10.authorization.adapters.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {
	
	static final String REGISTER_MEMBER = "register_member";
	static final String REGISTER_CLUB = "register_club";
	static final String REGISTER_STAFF = "register_staff";
	static final String REGISTER_CATERING = "register_catering";
	
	static final String REGISTER_MEMBER_TIMEOUT = "register_member_timeout";

	static final String REGISTER_RESULT = "register_result";
	
	
	@Output(REGISTER_MEMBER)
	MessageChannel registerMember();
	@Output(REGISTER_CLUB)
	MessageChannel registerClub();
	@Output(REGISTER_STAFF)
	MessageChannel registerStaff();
	@Output(REGISTER_CATERING)
	MessageChannel registerCatering();
	@Output(REGISTER_MEMBER_TIMEOUT)
	MessageChannel registerMemberTimeOut();

	
	@Input(REGISTER_RESULT)
	SubscribableChannel processRegisterResponse();
}
