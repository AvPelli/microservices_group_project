package be.ugent.groep10.authorization;

import org.springframework.stereotype.Component;

@Component
public class Roles {
	public final String ADMIN = "Admin";
	public final String MEMBER = "Member";
	public final String CATERINGSERVICE = "Cateringservice";
	public final String WORKER = "Worker";
	public final String CLUB = "Club";
}
