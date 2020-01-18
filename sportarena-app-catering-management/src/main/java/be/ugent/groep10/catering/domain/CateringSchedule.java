package be.ugent.groep10.catering.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CateringSchedule {

	@Id 
	private String sportEventId;
	
	private LocalDateTime dateTimeBegin;
	private LocalDateTime dateTimeEnd;
	private String description;
	
	private int seatOccupation; 
	private int availableFoodTrucks;
	private int totalFoodTrucksNeeded;
	
	
	//Constructors
	public CateringSchedule() {
		
	}
	
	public CateringSchedule(CateringSchedule item) {
		this.sportEventId = item.getSportEventId();
		this.dateTimeBegin = item.getDateTimeBegin();
		this.dateTimeEnd = item.getDateTimeEnd();
		this.description = item.getDescription();
		this.seatOccupation = item.getSeatOccupation();
		this.availableFoodTrucks = item.getAvailableFoodTrucks();
		this.totalFoodTrucksNeeded = item.getTotalFoodTrucksNeeded();
	}
	
	public CateringSchedule(String sportEventId, LocalDateTime dateTimeBegin, LocalDateTime dateTimeEnd, String description,
			int seatOccupation) {
		this.sportEventId = sportEventId;
		this.dateTimeBegin = dateTimeBegin;
		this.dateTimeEnd = dateTimeEnd;
		this.description = description;
		this.seatOccupation = seatOccupation;
		this.totalFoodTrucksNeeded = this.calculateFoodTrucks();
		this.setAvailableFoodTrucks(this.totalFoodTrucksNeeded);
		
	}
	
	//Calculate amount of foodtrucks needed
	public int calculateFoodTrucks() {
		if(this.seatOccupation < 500) {
			return 5;
		}
		else {
			return 5 + (int)(this.seatOccupation-500)/1000;
		}
	}

	//Getters and setters
	public String getSportEventId() {
		return sportEventId;
	}

	public void setSportEventId(String sportEventId) {
		this.sportEventId = sportEventId;
	}

	public LocalDateTime getDateTimeBegin() {
		return dateTimeBegin;
	}

	public void setDateTimeBegin(LocalDateTime dateTimeBegin) {
		this.dateTimeBegin = dateTimeBegin;
	}

	public LocalDateTime getDateTimeEnd() {
		return dateTimeEnd;
	}

	public void setDateTimeEnd(LocalDateTime dateTimeEnd) {
		this.dateTimeEnd = dateTimeEnd;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSeatOccupation() {
		return seatOccupation;
	}

	public void setSeatOccupation(int seatOccupation) {
		this.seatOccupation = seatOccupation;
	}

	public int getAvailableFoodTrucks() {
		return availableFoodTrucks;
	}

	public void setAvailableFoodTrucks(int availableFoodTrucks) {
		this.availableFoodTrucks = availableFoodTrucks;
	}

	public int getTotalFoodTrucksNeeded() {
		return totalFoodTrucksNeeded;
	}

	public void setTotalFoodTrucksNeeded(int totalFoodTrucksNeeded) {
		this.totalFoodTrucksNeeded = totalFoodTrucksNeeded;
	}

}
