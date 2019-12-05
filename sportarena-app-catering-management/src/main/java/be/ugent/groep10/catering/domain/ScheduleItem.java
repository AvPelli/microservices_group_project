package be.ugent.groep10.catering.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ScheduleItem {
	@Id @GeneratedValue
	private long id;
	
	private String eventId;
	private LocalDate startTime;
	private LocalDate endTime;
	private String description;
	private long seatOccupation;
	
	//Constructors
	public ScheduleItem() {
		
	}
	
	public ScheduleItem(String eventId, LocalDate startTime, LocalDate endTime, String description, long seatOccupation) {
		this.eventId = eventId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.description = description;
		this.seatOccupation = seatOccupation;
	}
	
	//Getters-Setters
	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
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
