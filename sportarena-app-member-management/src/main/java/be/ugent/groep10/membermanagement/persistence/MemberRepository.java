package be.ugent.groep10.membermanagement.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import be.ugent.groep10.membermanagement.domain.Member;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long>{
	
	//public Member findMemberById(int id);
	
	public List<Member> findMembersByLastName(String name);
	
}
