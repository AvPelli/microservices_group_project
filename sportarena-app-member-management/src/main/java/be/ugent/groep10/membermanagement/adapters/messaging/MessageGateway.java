package be.ugent.groep10.membermanagement.adapters.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import be.ugent.groep10.membermanagement.domain.Member;

@MessagingGateway
public interface MessageGateway {

//	@Gateway(requestChannel = Channels.NEW_MEMBER)
//	public void newMember(Member member);
	
	@Gateway(requestChannel = Channels.CREATE_WALLET)
	public void createWallet(CreateWalletRequest createWalletRequest);
}
