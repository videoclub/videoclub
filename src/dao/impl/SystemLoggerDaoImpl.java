package dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import model.SystemLogger;
import dao.SystemLoggerDao;

public class SystemLoggerDaoImpl extends DaoImpl implements SystemLoggerDao{

	private ArrayList<Object> systemLoggerList;
	
	
	public SystemLoggerDaoImpl(EntityManager em){
		super.setEntityManager(em);
	}
	
	public ArrayList<Object> getAllItems() {
		systemLoggerList = new ArrayList<Object>();
		TypedQuery<SystemLogger> query = getEntityManager().createQuery("SELECT s FROM System s", SystemLogger.class);
		try{
			List<SystemLogger> results = query.getResultList();
			for (int i=0; i<results.size(); i++) {
				systemLoggerList.add(results.get(i));
				System.out.println();
			}
		}catch(NoResultException nre){
			System.out.println(nre);
			return null;
		}catch(PersistenceException pe){
			System.out.println(pe);
			return null;
		}
		return systemLoggerList;
	}
	
	public ArrayList<Object> getItemDetails(String arg1, String arg2) {
		return null;
	}

	public void updateItem(ArrayList<Object> Object) {
	}

	public Object getItem(String arg1, String arg2) {
		return null;
	}

	

}
