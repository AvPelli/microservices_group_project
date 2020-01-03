package be.ugent.groep10.authorization.Model;

import java.io.Serializable;
import java.time.LocalDate;

public class Member implements Serializable{
	
	private String id;
	private final String firstName;
	private final String lastName;
	private final LocalDate dateOfBirth;
	private final LocalDate registeredOn;
	private final String bankAccountNumber;
	
	@SuppressWarnings("unused")
	protected Member() {
		this.firstName = null;
		this.lastName = null;
		this.dateOfBirth = null;
		this.registeredOn = null;
		this.bankAccountNumber = null;
	}
	
	public Member(String id, String firstName, String lastName, LocalDate birthday, LocalDate registeredOn, String bankAccountNumber) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = birthday;
		this.registeredOn = registeredOn;
		this.bankAccountNumber = bankAccountNumber;
	}
	
	
	public String getId() {
		return id;
	}	
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public LocalDate getRegisteredOn() {
		return registeredOn;
	}
	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	@Override
	public String toString() {
		return "Member(" + id + ")[firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	

}
