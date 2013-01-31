package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SystemLogger {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;
	private String action;
	private String details;
	private Date datetime;
	
	public SystemLogger(){
		//empty constructor
	}
	
	public long getId() {
		return id;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	
	
	
	
	
}
