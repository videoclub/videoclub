package controller.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import view.ManageProductView;
import view.ProductView;
import controller.ManageProductController;
import dao.ProductDao;

public class ManageProductControllerImpl extends CollectionImpl implements ManageProductController{

	public ManageProductControllerImpl(ProductDao model, ManageProductView m_view, ProductView view) {
        super.pr_model = model;
        super.manage_pr_view  = m_view;
        super.pr_view  = view;
        
        //... Add listeners to the view.
        m_view.addSubmitButtonListener(new SubmitListener());
        m_view.addResetButtonListener(new ResetListener());
	}
	
	class SubmitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	set();
        	String title = manage_pr_view.getTitleField();
        	ArrayList<Object> movie = getOne(title);
        	System.out.println(movie);
        	pr_view.showOne(movie);
        }
	}


	
	class ResetListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	manage_pr_view.reset();
        }
	}
}
