package model;

import javax.persistence.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;


/* Κλάση για τον έλεγχο των υπολοίπων κλάσεων */
public class Main
{
	// Main method
	public static void main(String args[]) throws IOException
	{
		// Open a database connection
		// (create a new database if it doesn't exist yet)
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db/ProfilesAndRights.odb");
		EntityManager em = emf.createEntityManager();
		
		
		
		Profile p2 = new Profile("employee");
		Profile p3 = new Profile("customer");
		
		Right r1 = new Right("show_product");
		Right r2 = new Right("manage_product");
		Right r3 = new Right("manage_customer");
		
		p2.grantRight(r1);
		p2.grantRight(r2);
		p2.grantRight(r3);
		
		p3.grantRight(r1);
		
	    // RUN THIS CODE ONLY ON THE FIRST RUN
		// DO NOT UNCOMMENT OTHERWISE
		//
        	em.getTransaction().begin();
        	em.persist(p2);
        	em.persist(p3);
        	
        	em.persist(r1);
        	em.persist(r2);
        	em.persist(r3);
        	em.getTransaction().commit();
        
        
        //View all profiles along with their granted rights
        
        TypedQuery<Profile> query = em.createQuery("SELECT p FROM Profile p", Profile.class);
        List<Profile> profiles = query.getResultList();
        
        for (Profile p : profiles)
        {
        	System.out.println(p);
        	System.out.println("--------------------");
        }

        
        System.out.println("--------------------");
        
        User u1 = new User("mai1223", "mypass1", p3, "Jimmy Hoffas", "mai1223@uoo.rg", "xxxxxxxxxx");
        Product p1 = new Product("The Hobbit", "blah blah blah", "Action", 2012, "Type", false);
        User u2 = new User("mai1111", "mypass2", p3, "Michael Jordan", "mai1111@uoo.rg", "yyyyyyyyyy");
        
        int returnAfter = 2;
        
        //Get instance of Calendar class
        Calendar now = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("dd/MM/yyyy");
        //System.out.println(df.format(now.getTime()));
        
        //Compute day at which product must be returned
        now.add(Calendar.DAY_OF_MONTH, returnAfter);
        
        //System.out.println(df.format(now.getTime()));
        
        //Calendar returnDate = now;

        

        Order o1 = new Order(u1, p1, new Date(), now.getTime());
        
    	em.getTransaction().begin();
    	em.persist(p1);
    	em.persist(u1);
    	em.persist(o1);
    	em.persist(u2);
    	em.getTransaction().commit();
    	
    	System.out.println("\nDone commiting!\n");
        
        System.out.println(u1);
        System.out.println("--------------------");
        System.out.println(p1);
        System.out.println("--------------------");
        System.out.println(o1);
     
        
        //View all users
        
        System.out.println("\nVIEW ALL USERS:\n");
        
        TypedQuery<User> query1 = em.createQuery("SELECT u FROM User u", User.class);
        List<User> all_users = query1.getResultList();
        
        for (User u : all_users)
        {
        	System.out.println(u);
        	System.out.println("--------------------");
        }

        
        System.out.println("--------------------");
        
        //View user Jimmy Hoffa by name
        
        System.out.println("\nVIEW SPECIFIC USER BY NAME:\n");
        
        TypedQuery<User> query2 = em.createQuery("SELECT u FROM User u where u.getName()='Jimmy Hoffas'", User.class);
        List<User> one_user = query2.getResultList();
        
        for (User u : one_user)
        {
        	System.out.println(u);
        	System.out.println("--------------------");
        }
        
        //View user Jimmy Hoffa by using a reference
        //and an anonymous parameter query
        
        
        System.out.println("\nVIEW SPECIFIC USER USING A REFERENCE:\n");
        
        TypedQuery<User> query3 = em.createQuery("SELECT u FROM User u where u=?1", User.class);
        query3.setParameter(1, u1);
        List<User> one_user2 = query3.getResultList();
        
        for (User u : one_user2)
        {
        	System.out.println(u);
        	System.out.println("--------------------");
        }
        
        //View user Jimmy Hoffa by using a reference
        //and a named parameter query
        
        System.out.println("\nALTERNATIVE VIEW SPECIFIC USER USING A REFERENCE:\n");
        
        TypedQuery<User> query4 = em.createQuery("SELECT u FROM User u where u=:user", User.class);
        query4.setParameter("user", u1);
        List<User> one_user3 = query4.getResultList();
        
        for (User u : one_user3)
        {
        	System.out.println(u);
        	System.out.println("--------------------");
        }
        
        
        //Persist another Order object for User Jimmy Hoffa
        
        now.add(Calendar.DAY_OF_MONTH, returnAfter+30);
        Product pr2 = new Product("Return of the King", "For Frodo", "", 2004, "Adventure", true);
        Order o2 = new Order(u1, pr2, new Date(), now.getTime());
        
    	em.getTransaction().begin();
    	em.persist(pr2);
    	em.persist(o2);
    	em.getTransaction().commit();
    	
    	System.out.println("Added another order on Jimmy Hoffa:\n--------------------");
    	
        TypedQuery<Order> q1 = em.createQuery("SELECT o FROM Order o where o.getUser()=:user", Order.class);
        q1.setParameter("user", u1);
        List<Order> jimmy = q1.getResultList();
        
        for (Order o : jimmy)
        {
        	System.out.println(o);
        	System.out.println("--------------------");
        }
        
        //Jimmy returns The Hobbit
        
        System.out.println("Jimmy Hoffa returned The Hobbit:\n--------------------");
        
        TypedQuery<Order> q2 = em.createQuery("SELECT o FROM Order o where o.getProduct().getTitle()=:movie_title and o.getUser().getName()=:name and o.getReturned()=false", Order.class);
        q2.setParameter("movie_title", "The Hobbit");
        q2.setParameter("name", "Jimmy Hoffa");
        List<Order> results = q2.getResultList();
        
        System.out.println("Searching for The Hobbit...");
        Product movie = findMovieByTitle(em, "The Hobbit");
        System.out.println(movie);
        
        
        
        
    	//Update Product table in the object database
        //MUST BE DONE INSIDE AN ACTIVE TRANSACTION!!!
    	em.getTransaction().begin();
    	Query update_query = em.createQuery(
    		      "UPDATE Product SET availability = true " +
    		      "WHERE title = :movie_name");
    	update_query.setParameter("movie_name", "The Hobbit");
    	int updateCount = update_query.executeUpdate();
    	System.out.println("Affected row(s): " + updateCount);
    	em.getTransaction().commit();
    	
    	System.out.println("Updated: " + updateCount + " row(s).");
    	
    	
    	
        
        System.out.println("--------------------");
        
       
        
        System.out.println("Searching for The Hobbit...");
        Product temp = findMovieByTitle(em, "The Hobbit");
        System.out.println(temp);
        
        System.out.println("--------------------");
        
        System.out.println("Searching for The Two Towers...");
        System.out.println(findMovieByTitle(em, "The Two Towers"));
        
        
        em.close();
        emf.close();
	}
	
	//Στις παρακάτω κλάσεις υλοποιούνται παραδείγματα ορισμένων
	//πιθανών αιτημάτων προς την βάση. Μπορείτε να κρατήσετε ελεύθερα
	//όποιο κομμάτι του κώδικά τους χρειαστεί.
	
	//Find movie using its title.
	//Ο τίτλος ταινίας είναι Unique
	public static Product findMovieByTitle(EntityManager em, String title)
	{
        TypedQuery<Product> q = em.createQuery("SELECT p FROM Product p where p.getTitle()=:movie_name", Product.class);
        q.setParameter("movie_name", title);
        List<Product> products = q.getResultList();
        
        if (products.isEmpty())
        	return null;
        else
        	return products.get(0);
        
	}
	
	//Find a User for a given username
	//Το username είναι Unique στον πίνακα User,
	//οπότε δεν θα υπάρξουν περισσότερα του ενός
	//επιστρεφόμενα αντικείμενα.
	public static User findUser(EntityManager em, String username)
	{
        TypedQuery<User> q = em.createQuery("SELECT u FROM User u where u.getUsername()=:username", User.class);
        q.setParameter("username", username);
        List<User> users = q.getResultList();
        
        if (users.isEmpty())
        	return null;
        else
        	return q.getSingleResult();
	}
	
	//Fetch all Products
	public static List<Product> getAllProducts(EntityManager em)
	{
        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p", Product.class);
        List<Product> all_products = query.getResultList();
        
        return all_products;
	}
	
	//Fetch all Users
	public static List<User> getAllUsers(EntityManager em)
	{		
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
        List<User> all_users = query.getResultList();
        
        return all_users;
	}
	
	//Fetch all orders that have today as a return date
	public static List<Order> getTodaysOrders(EntityManager em)
	{
		TypedQuery<Order> query = em.createQuery("SELECT o FROM Order o where ", Order.class);
		List<Order> todays_orders = query.getResultList();
        
        return todays_orders;
	}
	
}
