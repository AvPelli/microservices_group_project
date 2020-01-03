package be.ugent.groep10.membermanagement.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ugent.groep10.membermanagement.adapters.messaging.CreateWalletRequest;
import be.ugent.groep10.membermanagement.adapters.messaging.MessageGateway;
import be.ugent.groep10.membermanagement.persistence.MemberRepository;

@Service
public class MemberService {
	private final MemberRepository memberRepository;
	private final MessageGateway gateway;
	
	@Autowired
	public MemberService(MemberRepository memberRepository, MessageGateway gateway) {
		this.memberRepository = memberRepository;
		this.gateway = gateway;
	}
	
	public Member createMember(Member member) {
		member = memberRepository.save(member);
		CreateWalletRequest r = new CreateWalletRequest(member.getId(), 10);
		System.out.println("Creating member in service");
		System.out.println(r.toString());
		gateway.createWallet(r);
		return member;
	}
}
