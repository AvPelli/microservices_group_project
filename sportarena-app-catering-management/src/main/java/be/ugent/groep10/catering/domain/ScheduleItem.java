package be.ugent.groep10.catering.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ScheduleItem {
	//In tegenstelling tot de scheduleitem klasse in Arena Mgmt 
	//mag deze NIET autogenerated worden, anders wijken de ID's af van elkaar
	//naargelang de volgorde waarin de messages ontvangen worden
	@Id 
	private long id;
	
	private LocalDate startTime;
	private LocalDate endTime;
	private String description;
	private long seatOccupation;
	
	//Constructors
	public ScheduleItem() {
		
	}
	
	public ScheduleItem(long id, LocalDate startTime, LocalDate endTime, String description, long seatOccupation) {
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.description = description;
		this.seatOccupation = seatOccupation;
	}
	
	//Getters-Setters
	public long getId() {
		return this.id;
	}
	
	public LocalDate getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDate startTime) {
		this.startTime = startTime;
	}
	
	public LocalDate getEndTime() {
		return endTime;
	}
	
	public void setEndTime(LocalDate endTime) {
		this.endTime = endTime;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public long getSeatOccupation() {
		return seatOccupation;
	}

	public void setSeatOccupation(long seatOccupation) {
		this.seatOccupation = seatOccupation;
	}
}
