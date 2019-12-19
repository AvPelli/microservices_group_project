package be.ugent.groep10.membermanagement.adapters.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {
	
	static final String NEW_MEMBER = "new_member";
	static final String CREATED_WALLET = "created_wallet";
	
	
	@Output(NEW_MEMBER)
	MessageChannel newMember();
	
	@Input(CREATED_WALLET)
	SubscribableChannel processWalletResponse();

}
