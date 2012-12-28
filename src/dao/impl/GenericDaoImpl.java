package dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entity.ProductEntity;

import dao.GenericDao;

public class GenericDaoImpl implements GenericDao {
	
	protected EntityManagerFactory emf;
	protected EntityManager em;
	//private Object[][] items;
	//private Object[] details;
	protected ArrayList<Object> items = new ArrayList<Object>();
	protected ArrayList<Object> details = new ArrayList<Object>();

	public void openConnection() {
		emf = Persistence.createEntityManagerFactory("db/videoclub.odb");
		em = emf.createEntityManager();
	}

	
	public void persist(Object o) {
		em.getTransaction().begin();
		em.persist(o);
		em.getTransaction().commit();
	}

	
	//public Object[][] getAllItems() {
	public ArrayList<Object> getAllItems() {
		//items = new Object[10][5];
		TypedQuery<ProductEntity> query = em.createQuery("SELECT p FROM Products p", ProductEntity.class);
		List<ProductEntity> results = query.getResultList();
		//String[] item = new String[5];
		for (int i=0; i<results.size(); i++) {
			items.add(results.get(i).getTitle());
			items.add(results.get(i).getGenre());
			items.add(results.get(i).getRate());
			items.add(results.get(i).getYear());
			items.add(results.get(i).getType());
			/*item[0] = results.get(i).getTitle();
			item[1] = results.get(i).getGenre();
			item[2] = results.get(i).getRate();
			item[3] = Integer.toString(results.get(i).getYear());
			item[4] = results.get(i).getType();
			items.add(item);*/
		}
		return items;
	}

	
	//public Object[] getItemDetails(long id) {
	public ArrayList<Object> getItemDetails(String title) {
		//details = new Object[3]; // <- den ipirxe to initialization
		//TypedQuery<ProductEntity> query = em.createQuery("SELECT p.title, p.description, p.genre FROM Products p WHERE p.id='" + id + "'", ProductEntity.class);
		TypedQuery<ProductEntity> query = em.createQuery("SELECT p FROM Products p WHERE p.title='" + title + "'", ProductEntity.class);
		List<ProductEntity> result = query.getResultList();
		//prosoxi sto casting apo long se int! Dokimastiko.
		/*details[0] = result.get((int) id).getTitle();
		details[1] = result.get((int) id).getDescription();
		details[2] = result.get((int) id).getGenre();*/
		if (!result.isEmpty()) {
			details.add(result.get(0).getTitle());
			details.add(result.get(0).getGenre());
			details.add(result.get(0).getRate());
			details.add(result.get(0).getYear());
			details.add(result.get(0).getType());
			details.add(result.get(0).getDescription());
		}
		return details;
	}

	public void closeConnection() {
		em.close();
		emf.close();	
	}

}
