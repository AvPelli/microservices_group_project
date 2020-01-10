package be.ugent.groep10.ticket_management.adapters.messaging;

public class UpdateOccupancyRequest {
	
	private String sportEventId;
	private int occupancy;
	
	public UpdateOccupancyRequest(String sportEventId, int occupancy) {
		this.sportEventId = sportEventId;
		this.occupancy = occupancy;
	}
	
	public UpdateOccupancyRequest() {
		
	}

	public String getSportEventId() {
		return sportEventId;
	}

	public void setSportEventId(String sportEventId) {
		this.sportEventId = sportEventId;
	}

	public int getOccupancy() {
		return occupancy;
	}

	public void setOccupancy(int occupancy) {
		this.occupancy = occupancy;
	}

}
