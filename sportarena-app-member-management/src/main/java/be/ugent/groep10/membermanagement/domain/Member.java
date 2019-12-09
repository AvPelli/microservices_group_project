package be.ugent.groep10.membermanagement.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Member {
	
	@Id @GeneratedValue
	private long id;
	private final String firstName;
	private final String lastName;
	private final LocalDate dateOfBirth;
	private final LocalDate registeredOn;
	
	@SuppressWarnings("unused")
	protected Member() {
		this.firstName = null;
		this.lastName = null;
		this.dateOfBirth = null;
		this.registeredOn = null;
	}
	
	public Member(String firstName, String lastName, LocalDate birthday, LocalDate registeredOn) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = birthday;
		this.registeredOn = registeredOn;
	}
	
	
	public long getId() {
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

	@Override
	public String toString() {
		return "Member(" + id + ")[firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	

}
