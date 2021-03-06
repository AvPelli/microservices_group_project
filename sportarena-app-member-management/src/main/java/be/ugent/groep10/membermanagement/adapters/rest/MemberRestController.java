package be.ugent.groep10.membermanagement.adapters.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.ugent.groep10.membermanagement.domain.Member;
import be.ugent.groep10.membermanagement.domain.MemberService;
import be.ugent.groep10.membermanagement.persistence.MemberRepository;

@RestController
@RequestMapping("member")
public class MemberRestController {
	private final MemberRepository memberRepository;
	private final MemberService memberService;
	
	@Autowired
	public MemberRestController(MemberRepository memberRepository, MemberService memberService) {
		this.memberRepository = memberRepository;
		this.memberService = memberService;
	}
	
	@GetMapping
	public Iterable<Member> getAllMembers(){
		System.out.println("--------------------FindAllMembers!!!!--------------------");
		return this.memberRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Member getMember(@PathVariable("id") String id) {
		return this.memberRepository.findById(id).orElse(null);
	}
	
	@PostMapping()
	public Member addMember(@RequestBody Member member){
//		this.memberRepository.save(member);
//		return this.memberRepository.findById(member.getId()).orElse(null);
		Member newMember = memberService.createMember(member);
		return newMember;
		
	}
}
