package be.ugent.groep10.authorization;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
	
	@PreAuthorize("hasRole(@roles.ADMIN)")
	public boolean ensureAdmin() {
		System.out.println("ensureAdmin");
		return true;
	}

}
