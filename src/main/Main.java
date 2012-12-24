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
		ProductDaoImpl	     		model      = new ProductDaoImpl();
		ProductView    				view       = new ProductView(model);
		ProductControllerImpl		controller = new ProductControllerImpl(model, view);
        
        view.setVisible(true);
	}
}
