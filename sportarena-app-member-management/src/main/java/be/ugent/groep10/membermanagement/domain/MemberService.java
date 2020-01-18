package be.ugent.groep10.membermanagement.domain;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import be.ugent.groep10.membermanagement.adapters.messaging.CreateWalletRequest;
import be.ugent.groep10.membermanagement.adapters.messaging.MessageGateway;
import be.ugent.groep10.membermanagement.adapters.messaging.RegisterResponse;
import be.ugent.groep10.membermanagement.persistence.MemberRepository;

@Service
public class MemberService {
	private final MemberRepository memberRepository;
	private final MessageGateway gateway;
	
	private Map<String, Integer> pendingMembers;
	
	@Autowired
	public MemberService(MemberRepository memberRepository, MessageGateway gateway) {
		this.memberRepository = memberRepository;
		this.gateway = gateway;
		this.pendingMembers = new HashMap<String, Integer>();
	}
	
	public Member createMember(Member member) {
		if(memberRepository.findById(member.getId()).isPresent()) {
			this.gateway.registerResult(new RegisterResponse(member.getId(), false));
			return null;
		} else {
			Member newMember = memberRepository.save(member);
			if(newMember==null) {
				this.gateway.registerResult(new RegisterResponse(member.getId(), false));
				return null;
			}
			this.createWallet(newMember);
		}
		return member;
	}
	
	public void createWallet(Member member) {
		CreateWalletRequest r = new CreateWalletRequest(member.getId(), 10);
		this.pendingMembers.put(member.getId(), 5);
		gateway.createWallet(r);
	}
	
	public void proccesWalletResponse(CreateWalletRequest createWalletRequest) {
		if(createWalletRequest.getTokens()>=0 && removePendingMember(createWalletRequest.getOwnerId())) {
			this.gateway.registerResult(new RegisterResponse(createWalletRequest.getOwnerId(), true));
		} else {
			removePendingMember(createWalletRequest.getOwnerId());
			this.gateway.registerResult(new RegisterResponse(createWalletRequest.getOwnerId(), false));
		}
	}
	
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
	
	public void deleteMember(String memberId) {
		memberRepository.deleteById(memberId);
	}
}
