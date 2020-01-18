package be.ugent.groep10.authorization.adapters.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import be.ugent.groep10.authorization.domain.CateringCompany;
import be.ugent.groep10.authorization.domain.Member;


@MessagingGateway
public interface MessageGateway {
	
	@Gateway(requestChannel = Channels.REGISTER_MEMBER)
	public void registerMember(Member member);
	
	@Gateway(requestChannel = Channels.REGISTER_MEMBER_TIMEOUT)
	public void registerMemberTimeout(String userId);

	@Gateway(requestChannel = Channels.REGISTER_CATERING)
	public void registerCatering(CateringCompany catering);
	
	@Gateway(requestChannel = Channels.REGISTER_CATERING_TIMEOUT)
	public void registerCateringTimeout(String userId);
}
