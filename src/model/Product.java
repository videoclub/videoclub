package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.*;

@Entity
public class Product
{
	
	@Id @GeneratedValue
	private long id;
	
	@Basic(optional=false)
	private String title;
	
	private String description;
	
	@Basic(optional=false)
	private String genre;
	
	private String rating;
	private int year;
	
	@Basic(optional=false)
	private String type;
	
	@Basic(optional=false)
	private boolean availability;   //Consider changing this to private int availableCopies;
	
	private Date create_date;
	private Date edit_date;
	
	//Constructor#1 (without rating)
	public Product(String title, String description, 
				   String genre, int year, String type, 
				   boolean availability)
	{
		this.setTitle(title);
		this.setDescription(description);
		this.setGenre(genre);
		this.setYear(year);
		this.setType(type);
		this.setAvailability(availability);
		this.create_date = new Date();
		this.edit_date = new Date();
		this.setRating(null);
	}
	
	//Constructor#2 (with rating)
	public Product(String title, String description, 
			  	   String genre, int year, String type, 
			       boolean availability, String rating)
	{
		this.setTitle(title);
		this.setDescription(description);
		this.setGenre(genre);
		this.setYear(year);
		this.setType(type);
		this.setAvailability(availability);
		this.setRating(rating);
		this.create_date = new Date();
		this.edit_date = new Date();
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getGenre()
	{
		return genre;
	}

	public void setGenre(String genre)
	{
		this.genre = genre;
	}

	public String getRating()
	{
		return rating;
	}

	public void setRating(String rating)
	{
		this.rating = rating;
	}

	public int getYear()
	{
		return year;
	}

	public void setYear(int year)
	{
		this.year = year;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public boolean getAvailability()
	{
		return availability;
	}

	public void setAvailability(boolean availability)
	{
		this.availability = availability;
	}

	public Date getEdit_date() {
		return edit_date;
	}

	public void setEdit_date(Date edit_date) {
		this.edit_date = edit_date;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	@Override
	public String toString()
	{
		SimpleDateFormat df = new SimpleDateFormat();
		df.applyPattern("dd/MM/yyyy hh:mm:ss a");
		return String.format("Product Id: %d\nTitle: %s\nDescription: %s\n" +
							 "Genre: %s\nRating: %s\nYear: %d\nType: %s\n" +
							 "Available: %s\nCreate Date: %s\nEdit Date: %s",
							 id, title, description, genre, rating, year, type,
							 (availability? "Yes": "No"), df.format(create_date), df.format(edit_date));
	}
}