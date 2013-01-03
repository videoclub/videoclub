package model;

import java.util.ArrayList;
import javax.jdo.annotations.Unique;
import javax.persistence.*;

@Entity
public class User
{
	@Id @GeneratedValue
	private long id;
	
	@Unique
	@Basic(optional=false)
	private String username;
	
	@Unique
	@Basic(optional=false)
	private String password;
	
	private @ManyToOne Profile profile;
	
	@Basic(optional=false)
	private String name;
	
	@Unique
	private String email;
	
	@Basic(optional=false)
	private String phone;
	
	private @OneToMany(mappedBy="user") ArrayList<Order> orders;
	
	//Constructor
	public User(String username, String password, Profile profile,
				String name, String email, String phone)
	{
		this.username = username;
		this.password = password;
		this.profile = profile;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public Profile getProfile()
	{
		return profile;
	}

	public void setProfile(Profile profile)
	{
		this.profile = profile;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	//пяосовг! циа амайтгсг окым тым паяаццекиым
	//(истояийо) емос вягстг
	public ArrayList<Order> getOrders()
	{
		return orders;
	}
	
	//пяосовг! циа амайтгсг ломо тым емеяцым паяаццекиым
	public ArrayList<Order> getActiveOrders()
	{
		/*
		if (orders == null)
			System.out.println("!!!");
		*/
		
		ArrayList<Order> activeOrders = new ArrayList<Order>();
		for (Order o : orders)
			if (!o.getReturned())
				activeOrders.add(o);
		return activeOrders;
	}

	public void setOrders(ArrayList<Order> orders)
	{
		this.orders = orders;
	}
	
	public void addOrder(Order order)
	{
		orders.add(order);
	}
	
	@Override
	public String toString()
	{
		String res = String.format("User Id: %d\nUsername: %s\nProfile: %s\n" +
							 "Name: %s\nemail: %s\nPhone: %s",
							 id, username, profile.getLabel(), name, email, phone);
		//ArrayList<Order> active = getActiveOrders();
		//res += String.format("\nNumber of Active orders: %d\nActive Products: %s", active.size(), active);
		return res;
	}

}
