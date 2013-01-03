package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.*;

/* Κλάση για την τήρηση ιστορικού */
@Entity
public class Order
{
	@Id @GeneratedValue
	private long order_number;
	
	@Basic(optional=false)
	private @ManyToOne User user;

	@Basic(optional=false)
	private @ManyToOne Product product;
	
	@Basic(optional=false)
	private Date order_date;
	
	@Basic(optional=false)
	private Date return_date;
	
	@Basic(optional=false)
	private boolean returned;
	
	//Constructor#1 (without an orderDate)
	public Order(User user, Product product, Date returnDate)
	{
		this.product = product;
		this.setUser(user);
		this.setOrderDate(new Date());   //Current system date and time is set as order_date
		this.setReturnDate(returnDate);
		this.returned = false;
	}
	
	//Constructor#2 (with an orderDate)
	public Order(User user, Product product, Date orderDate, Date returnDate)
	{
		this.product = product;
		this.setUser(user);
		this.setOrderDate(orderDate);
		this.setReturnDate(returnDate);
		this.returned = false;
	}
	
	public long getOrder_number()
	{
		return order_number;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public Product getProduct()
	{
		return this.product;
	}
	
	public void setProduct(Product p)
	{
		this.product = p;
	}

	public Date getOrderDate()
	{
		return order_date;
	}

	public void setOrderDate(Date orderDate)
	{
		this.order_date = orderDate;
	}

	public Date getReturnDate()
	{
		return return_date;
	}

	public void setReturnDate(Date returnDate)
	{
		this.return_date = returnDate;
	}
	
	public boolean getReturned()
	{
		return returned;
	}

	public void setReturned(boolean isReturned)
	{
		this.returned = isReturned;
	}
	
	@Override
	//For display
	public String toString()
	{
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("dd/MM/yyyy");
		return String.format("Order Number: %d\nBy User: %s\n" +
							 "Product Title: %s\nOrder Status: %s\n" +
							 "Order Date: %s\nReturn Date: %s", 
							 order_number, user.getUsername(), 
							 product.getTitle(), 
							 (returned? "Returned": "Still Rented"),
							 df.format(order_date.getTime()), df.format(return_date.getTime()));
	}

}