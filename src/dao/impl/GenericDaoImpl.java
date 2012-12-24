package dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dao.GenericDao;
import entity.ProductEntity;

public class GenericDaoImpl implements GenericDao {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	private Object[][] items;
	private Object[] details;

	public void openConnection() {
		emf = Persistence.createEntityManagerFactory("db/videoclub.odb");
		em = emf.createEntityManager();
	}

	
	public void persist(Object o) {
		em.getTransaction().begin();
		em.persist(o);
		em.getTransaction().commit();
	}

	
	public Object[][] getAllItems() {
		Object[][] items = new Object[10][5];
		TypedQuery<ProductEntity> query = em.createQuery("SELECT p FROM Products p", ProductEntity.class);
		List<ProductEntity> results = query.getResultList();
		for (int i=0; i<results.size(); i++) {
			items[i][0] = results.get(i).getTitle();
			items[i][1] = results.get(i).getDescription();
			items[i][2] = results.get(i).getGenre();
			items[i][3] = results.get(i).getRate();
			items[i][4] = results.get(i).getType();
		}
		return items;
	}

	
	public Object[] getItemDetails(long id) {
		TypedQuery<ProductEntity> query = em.createQuery("SELECT p.title, p.description, p.genre FROM Products p WHERE p.id='" + id + "'", ProductEntity.class);
		List<ProductEntity> result = query.getResultList();
		//prosoxi sto casting apo long se int! Dokimastiko.
		details[0] = result.get((int) id).getTitle();
		details[1] = result.get((int) id).getDescription();
		details[2] = result.get((int) id).getGenre();
		return details;
	}

	public void closeConnection() {
		em.close();
		emf.close();	
	}

}
