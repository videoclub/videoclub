package entity;

import javax.jdo.annotations.*;
import javax.persistence.*; //Gia ta annotations - @Entity klp

import entity.ProductEntity;

import java.sql.Timestamp;

@Entity(name="Products")
public class ProductEntity {
	
	@Id @GeneratedValue
	private long id;
	
	@Index
	private String title; 
	
	@Index
	private String genre;
	
	@Index
	private double rate;
	
	private String type;
	private int year;
	private boolean avail;
	private Timestamp create_date, edit_date;
	

	public long getId() {
		return id;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getGenre() {
		return genre;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getRate() {
		return rate;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getYear() {
		return year;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setAvail(boolean avail) {
		this.avail = avail;
	}

	public boolean getAvail() {
		return avail;
	}

	public void setCreateDate(Timestamp create_date) {
		this.create_date = create_date;
	}

	public Timestamp getCreateDate() {
		return create_date;
	}

	public void setEditDate(Timestamp edit_date) {
		this.edit_date = edit_date;
	}

	public Timestamp getEditDate() {
		return edit_date;
	}


}
