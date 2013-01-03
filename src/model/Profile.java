package model;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.*;
import javax.jdo.annotations.*;

@Entity
public class Profile implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	private long id;
	
	@Unique
	@Basic(optional=false)
	private String label;
	
	private @ManyToMany ArrayList<Right> rights;
	
	//Constructor
	public Profile(String label)
	{
		this.label = label;
		rights = new ArrayList<Right>();
	}
	
	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getLabel()
	{
		return label;
	}

	public void setLabel(String label)
	{
		this.label = label;
	}
	
	public void grantRight(Right r)
	{
		rights.add(r);
	}
	
	public ArrayList<Right> getRights()
	{
		return this.rights;
	}
	
	@Override
	public String toString()
	{
		String info = String.format("Profile id: %d\nProfile Name: %s\nGranted rights: ", this.id, this.label);   //\nGranted Rights: %s   , this.rights.toString()
		ArrayList<String> rightLabels = new ArrayList<String>();
		for (Right r : rights)
			rightLabels.add(r.getLabel());
		return info + rightLabels.toString();
	}
	
}
