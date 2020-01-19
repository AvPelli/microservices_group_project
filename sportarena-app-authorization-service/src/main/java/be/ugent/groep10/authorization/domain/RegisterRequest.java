package be.ugent.groep10.authorization.domain;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterRequest {
	
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String role;
	private LocalDate dateOfBirth;
	private String bankAccountNumber;
	private String zever; // not at all used, just for example with employeeform
	
	//For catering
	private String companyName;
	
	public RegisterRequest() {
	}


	public RegisterRequest(String firstName, String lastName, String emailAddress, String role, LocalDate dateOfBirth,
			String bankAccountNumber, String zever, String companyName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.role = role;
		this.dateOfBirth = dateOfBirth;
		this.bankAccountNumber = bankAccountNumber;
		this.zever = zever;
		this.companyName = companyName;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmailAddress() {
		return emailAddress;
	}


	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public String getBankAccountNumber() {
		return bankAccountNumber;
	}


	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}


	public String getZever() {
		return zever;
	}


	public void setZever(String zever) {
		this.zever = zever;
	}


	@Override
	public String toString() {
		return "RegisterRequest [firstName=" + firstName + ", lastName=" + lastName + ", emailAddress=" + emailAddress
				+ ", role=" + role + ", dateOfBirth=" + dateOfBirth + ", bankAccountNumber=" + bankAccountNumber
				+ ", zever=" + zever + "]";
	}


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	

}
