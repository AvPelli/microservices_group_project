package be.ugent.groep10.authorization.domain;

import java.io.Serializable;
import java.time.LocalDate;

public class CateringCompany implements Serializable {
	
	private String companyId;
	private final String firstName;
	private final String lastName;
	private final String companyName;
	
	@SuppressWarnings("unused")
	protected CateringCompany() {
		this.firstName = null;
		this.lastName = null;
		this.companyName = null;
	}
	
	public CateringCompany(String companyId, String firstName, String lastName, String companyName) {
		this.companyId = companyId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.companyName = companyName;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
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
