package be.ugent.groep10.membermanagement.adapters.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {
	
	static final String CREATE_WALLET = "create_wallet";
	static final String WALLET_CREATED = "wallet_created";
	
	static final String REGISTER_MEMBER = "register_member";
	static final String REGISTER_MEMBER_TIMEOUT = "register_member_timeout";
	
	static final String REGISTER_RESULT = "register_result";
	
	//Betting service
	@Output(CREATE_WALLET)
	MessageChannel createWallet();
	@Output(REGISTER_RESULT)
	MessageChannel registerResult();
	
	
	@Input(WALLET_CREATED)
	SubscribableChannel processWalletResponse();
	@Input(REGISTER_MEMBER)
	SubscribableChannel processRegisterMember();
	@Input(REGISTER_MEMBER_TIMEOUT)
	SubscribableChannel processRegisterMemberTimeOut();
	

}
