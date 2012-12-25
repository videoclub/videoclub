package dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dao.GenericDao;
import entity.ProductEntity;

/**
 * http://stackoverflow.com/questions/3888575/single-dao-generic-crud-methods-jpa-hibernate-spring
 * http://java.dzone.com/articles/jpa-implementation-patterns
 * @author puppet
 *
 */
public class GenericDaoImpl implements GenericDao {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	private ArrayList<String> items = new ArrayList<String>();
	private ArrayList<String> details = new ArrayList<String>();

	public void openConnection() {
		emf = Persistence.createEntityManagerFactory("db/videoclub.odb");
		em = emf.createEntityManager();
	}

	
	public void persist(Object o) {
		em.getTransaction().begin();
		em.persist(o);
		em.getTransaction().commit();
	}

	
	public ArrayList<String> getAllItems() {
		TypedQuery<ProductEntity> query = em.createQuery("SELECT p FROM Products p", ProductEntity.class);
		List<ProductEntity> results = query.getResultList();
		for (int i=0; i<results.size(); i++) {
			items.add(results.get(i).getTitle() + "\n");
		}
		return items;
	}

	
	public ArrayList<String> getItemDetails(String title) {
		TypedQuery<ProductEntity> query = em.createQuery("SELECT p.id, p.title FROM Products p WHERE p.title='" + title + "'", ProductEntity.class);
		List<ProductEntity> results = query.getResultList();
		details.add(results.get(0) + "\n");
		return details;
	}

	public void closeConnection() {
		em.close();
		emf.close();	
	}

}
