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
	
	public CateringCompany(String companyId, String companyName, String sportEventId) {
		this.companyId = companyId;
		this.companyName = companyName;
		this.sportEventId = sportEventId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String name) {
		this.companyName = name;
	}

	public String getSportEventId() {
		return sportEventId;
	}

	public void setSportEventId(String sportEventId) {
		this.sportEventId = sportEventId;
	}

	@Override
	public String toString() {
		return "CateringCompany [companyId=" + companyId + ", companyName=" + companyName + ", sportEventId="
				+ sportEventId + "]";
	}
	
	
}
