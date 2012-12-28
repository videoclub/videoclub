package main;

import controller.impl.ProductControllerImpl;
import view.ProductView;
import dao.impl.ProductDaoImpl;

import javax.swing.*;

public class Main {

	/**
	 * @param args
	 */
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
		
		ProductDaoImpl	     		model      = new ProductDaoImpl();
		ProductView    				view       = new ProductView(model);
		ProductControllerImpl		controller = new ProductControllerImpl(model, view);
		
        view.setVisible(true);
	}
}
