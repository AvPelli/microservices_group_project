package be.ugent.groep10.catering.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CateringCompany {
	
	//Gebruik authorization ID
	@Id
	private String companyId;
	
	private String companyName;
	
	@Column(nullable = true)
	private String sportEventId;
	
	public CateringCompany() {
		
	}
	
	public CateringCompany(String companyId, String name, String sportEventId) {
		this.companyId = companyId;
		this.companyName = name;
		this.sportEventId = sportEventId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getName() {
		return companyName;
	}

	public void setName(String name) {
		this.companyName = name;
	}

	public String getSportEventId() {
		return sportEventId;
	}

	public void setSportEventId(String sportEventId) {
		this.sportEventId = sportEventId;
	}
}
