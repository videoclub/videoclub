package dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dao.Dao;

public abstract class DaoImpl implements Dao {
	
	//private EntityManagerFactory emf;
	private EntityManager em;
	
	public void setEntityManager(EntityManager em){
		this.em = em;
	}
	
	public EntityManager getEntityManager() {
		return em;
	}
	
	
	/*public void openConnection() {
		emf = Persistence.createEntityManagerFactory("db/videoclub.odb");
		em = emf.createEntityManager();
	}*/

	public void persist(Object o) {
		em.getTransaction().begin();
		em.persist(o);
		em.getTransaction().commit();
	}

	/*public void closeConnection() {
		em.close();
		emf.close();	
	}*/

}
