package be.ugent.groep10.membermanagement.adapters.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

import be.ugent.groep10.membermanagement.domain.MemberService;


@Service
public class MemberCommandHandler {
	
	private static Logger logger = LoggerFactory.getLogger(MemberCommandHandler.class);
	
	private final MemberService memberService;

	@Autowired
	public MemberCommandHandler(MemberService memberService) {
		this.memberService = memberService;
	}
	
//	@StreamListener(Channels.WALLET_CREATED)
//	public void 
	
	
	
}
