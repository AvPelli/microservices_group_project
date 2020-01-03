package be.ugent.groep10.ticket_management.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Ticket {

	@Id
	private String id;
	private int sportEventId;
	private int section;
	private int row;
	private int seat;
	private TicketStatus status;
	
	public Ticket() { }
	
	public Ticket(int sportEventId, int section, int row, int seat) {
		this.sportEventId = sportEventId;
		this.section = section;
		this.row = row;
		this.seat = seat;
		this.status = TicketStatus.AVAILABLE;
	}
	
	public String getId() {
		return id;
	}
	
	public int getSportEventId() {
		return sportEventId;
	}
	
	public int getSection() {
		return section;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getSeat() {
		return seat;
	}

	public TicketStatus getStatus() {
		return status;
	}

	public void setStatus(TicketStatus status) {
		this.status = status;
	}
	
	public void sellTicket() {
		status = TicketStatus.SOLD;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", sportEventId=" + sportEventId + ", section=" + section + ", row=" + row
				+ ", seat=" + seat + ", status=" + status + "]";
	}
 	
}
