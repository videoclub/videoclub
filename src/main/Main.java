package main;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.Profile;
import model.Right;

import profiles_and_rights.ProfilesAndRights;

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
	private static EntityManagerFactory emf = PersistenceController.getInstance().getEntityManagerFactory();
	private static EntityManager em = emf.createEntityManager();
	//Initialize static rights variable 
	public static ArrayList<String> rights;
	
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
		
		//Just to check interaction with profile
		//To be removed/modified after login view will be created
		Profile p2 = new Profile("employee");
		Profile p3 = new Profile("customer");
		
		Right r1 = new Right("show_product");
		Right r2 = new Right("manage_product");
		Right r3 = new Right("manage_customer");
		
		p2.grantRight(r1);
		p2.grantRight(r2);
		p2.grantRight(r3);
		
		p3.grantRight(r1);
		
        rights = p2.getRightLabels();
        
		ProductDao	     		model		= new ProductDaoImpl(em);
		ProductView    			view		= new ProductView(model);
		ProductController		controller	= new ProductControllerImpl(model, view);
		
        view.setVisible(true);
		
	}
}
