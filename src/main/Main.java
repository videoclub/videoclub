package main;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import model.Profile;
import model.Right;
import model.User;

import view.ProductView;
import controller.PersistenceController;
import controller.ProductController;
import controller.impl.ProductControllerImpl;
import dao.ProductDao;
import dao.impl.ProductDaoImpl;

public class Main {

	/**
	 * @param args
	 */
	
	//If this is  the first run of the program on a specific machine AND videoclub.odb does not exist
	//create videoclub.odb, initialize profiles and rights and insert an employee user
	private static void initializeProfilesRightsAndOneEmployeeUser(){
		Profile p0 = new Profile("employee");
		Profile p1 = new Profile("customer");
		
		Right r0 = new Right("show_product");
		Right r1 = new Right("manage_product");
		Right r2 = new Right("manage_customer");
		
		User u0 = new User("admin", "admin", p0, "Admin", "admin@videoclub.com", "1234567890");
		
		//Check if profile and right data are already persistent in the database
		TypedQuery<Profile> check_query = em.createQuery("SELECT p FROM Profile p", Profile.class);
		List<Profile> all_profiles = check_query.getResultList();
		
		if (all_profiles.size() == 0) {
		    p0.grantRight(r0);
		    p0.grantRight(r1);
		    p0.grantRight(r2);
		  
		    p1.grantRight(r0);
		    
		    em.getTransaction().begin();
		    em.persist(p0);
		    em.persist(p1);
		    em.persist(r0);
		    em.persist(r1);
		    em.persist(r2);
		    em.persist(u0);
		    em.getTransaction().commit();
		}
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		//set the Look and Feel
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProductView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProductView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProductView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProductView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
		 
		initializeProfilesRightsAndOneEmployeeUser();
        
		ProductDao	     		model		= new ProductDaoImpl(em);
		ProductView    			view		= new ProductView();
		ProductController		controller	= new ProductControllerImpl(model, view);
		
        view.setVisible(true);
		
	}
	
	//Initialize connection to DB
	private static EntityManagerFactory emf = PersistenceController.getInstance().getEntityManagerFactory();
	private static EntityManager em = emf.createEntityManager();
	
	//Initialize static rights and name variable 
	public static ArrayList<String> rights = null;
	public static User current_user = null;
}
