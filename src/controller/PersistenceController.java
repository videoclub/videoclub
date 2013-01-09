package controller;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author puppet
 * O PersistenceController anoigei ena kai monadiko
 * connection. PROSOXI! Oi ipoloipoi controllers 
 * (ProductController, UserController etc) den ton klhronomoun.
 * Kanoun reference mono gia na steiloun sto dao tous to em
 * kai periexoun mono to business logic.
 */
public class PersistenceController{

	public static PersistenceController getInstance(){
		return CTRLSINGLETON;
	}

	//Empty private constructor
	private PersistenceController(){
	}

	public EntityManagerFactory getEntityManagerFactory() {
		if(emf == null){
			createEntityManagerFactory();
		}
		return emf;
	}

	public void closeEntityManagerFactory() {
		if (emf != null) {
			emf.close();
			emf = null;
			System.out.println("\nPersistence finished at " + new java.util.Date());
		}
	}

	protected void createEntityManagerFactory() {
		this.emf = Persistence.createEntityManagerFactory("db/videoclub.odb");
		System.out.println("\nPersistence started at " + new java.util.Date());
	}

	private static final PersistenceController CTRLSINGLETON = new PersistenceController();
	protected EntityManagerFactory emf;





}
