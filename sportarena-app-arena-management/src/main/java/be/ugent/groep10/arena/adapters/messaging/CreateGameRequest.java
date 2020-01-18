package be.ugent.groep10.arena.adapters.messaging;

import java.time.LocalDateTime;

public class CreateGameRequest {

	private String sportEventId;
	private LocalDateTime dateTimeBegin;
	private LocalDateTime dateTimeEnd;
	
	public CreateGameRequest() {}
	
	public CreateGameRequest(String sportEventId, LocalDateTime dateTimeBegin, LocalDateTime dateTimeEnd) {
		this.sportEventId = sportEventId;
		this.dateTimeBegin = dateTimeBegin;
		this.dateTimeEnd = dateTimeEnd;
	}

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
	
}
