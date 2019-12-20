package be.ugent.groep10.catering.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CateringSchedule {
	@Id @GeneratedValue
	private long id;
	
	//Verwijzing naar ScheduleItem
	private long scheduleItemId;
	
	//Specifiek nodig voor catering
	private long seatOccupation; 
	private int availableFoodTrucks;
	private int totalFoodTrucksNeeded;
	
	
	//Constructors
	public CateringSchedule() {
		
	}
	
	public CateringSchedule(ScheduleItem event) {
		this.scheduleItemId = event.getId();
		this.seatOccupation = event.getSeatOccupation();
		this.totalFoodTrucksNeeded = this.calculateFoodTrucks();
		this.setAvailableFoodTrucks(this.totalFoodTrucksNeeded);
		
	}
	
	//Calculate amount of foodtrucks needed
	private int calculateFoodTrucks() {
		if(this.seatOccupation < 500) {
			return 5;
		}
		else {
			return 5 + (int)(this.seatOccupation-500)/1000;
		}
	}

	//Getters and setters
	public int getAvailableFoodTrucks() {
		return availableFoodTrucks;
	}

	public void setAvailableFoodTrucks(int availableFoodTrucks) {
		this.availableFoodTrucks = availableFoodTrucks;
	}
}
