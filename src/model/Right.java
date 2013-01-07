package model;

import java.io.Serializable;
import java.util.ArrayList;
import javax.jdo.annotations.*;
import javax.persistence.*;

@Entity
public class Right implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;

	@Unique
	@Basic(optional = false)
	private String label;

	@ManyToMany(mappedBy = "rights")
	private ArrayList<Profile> granted_to;

	//Empty constructor
	public Right(){
	}
	
	// Constructor
	public Right(String label) {
		this.setLabel(label);
		granted_to = new ArrayList<Profile>();
	}

	public long getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
