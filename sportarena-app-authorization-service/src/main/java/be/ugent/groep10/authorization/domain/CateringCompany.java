package be.ugent.groep10.authorization.domain;

import java.io.Serializable;
import java.time.LocalDate;

public class CateringCompany implements Serializable {
	
	private String id;
	private final String firstName;
	private final String lastName;
	private final String companyName;
	
	@SuppressWarnings("unused")
	protected CateringCompany() {
		this.firstName = null;
		this.lastName = null;
		this.companyName = null;
	}
	
	public CateringCompany(String id, String firstName, String lastName, String companyName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.companyName = companyName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getCompanyName() {
		return companyName;
	}

	
}
