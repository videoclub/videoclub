package dao.impl;

import java.util.ArrayList;
//import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
//import javax.persistence.TypedQuery;

//import model.Product;



import dao.Dao;

public abstract class DaoImpl implements Dao {
	
	protected EntityManagerFactory emf;
	protected EntityManager em;
	protected ArrayList<Object> items;
	protected ArrayList<Object> details;

	public void openConnection() {
		emf = Persistence.createEntityManagerFactory("db/videoclub.odb");
		em = emf.createEntityManager();
	}

	
	public void persist(Object o) {
		em.getTransaction().begin();
		em.persist(o);
		em.getTransaction().commit();
	}

	/*
	public ArrayList<Object> getAllItems() {
		items = new ArrayList<Object>();
		TypedQuery<Product> query = em.createQuery("SELECT p FROM Products p", Product.class);
		List<Product> results = query.getResultList();
		for (int i=0; i<results.size(); i++) {
			items.add(results.get(i).getTitle());
			items.add(results.get(i).getGenre());
			items.add(results.get(i).getRating());
			items.add(results.get(i).getYear());
			items.add(results.get(i).getType());
		}
		return items;
	}

	
	//public Object[] getItemDetails(long id) {
	public ArrayList<Object> getItemDetails(String title) {
		details = new ArrayList<Object>();
		TypedQuery<Product> query = em.createQuery("SELECT p FROM Products p WHERE p.title='" + title + "'", Product.class);
		List<Product> result = query.getResultList();
		if (!result.isEmpty()) {
			for (int i=0; i<result.size(); i++){
				details.add(result.get(i).getTitle());
				details.add(result.get(i).getGenre());
				details.add(result.get(i).getRating());
				details.add(result.get(i).getYear());
				details.add(result.get(i).getType());
				details.add(result.get(i).getDescription());
			}
		}
		return details;
	}	
	*/
	public void closeConnection() {
		em.close();
		emf.close();	
	}

}
