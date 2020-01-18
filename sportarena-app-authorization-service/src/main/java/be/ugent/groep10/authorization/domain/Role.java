package be.ugent.groep10.authorization.domain;

import org.springframework.stereotype.Component;

@Component
public class Role {
	public static final String MEMBER = "Member";
	public static final String CATERINGSERVICE = "Cateringservice";
	public static final String STAFF = "Staff";
	public static final String CLUB = "Club";
}
