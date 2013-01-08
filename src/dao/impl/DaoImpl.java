package dao.impl;

import javax.persistence.EntityManager;

import dao.Dao;

public abstract class DaoImpl implements Dao {
	
	private EntityManager em;
	
	public void setEntityManager(EntityManager em){
		this.em = em;
	}
	
	public EntityManager getEntityManager() {
		return em;
	}
	
	public void persist(Object o) {
		em.getTransaction().begin();
		em.persist(o);
		em.getTransaction().commit();
	}

}
