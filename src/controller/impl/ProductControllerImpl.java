package controller.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

import controller.ProductController;

import view.ManageProductView;
import view.ProductDetailsView;
import view.ProductView;
import dao.ProductDao;

public class ProductControllerImpl extends CollectionImpl implements ProductController{
	
	private ArrayList<Object> product;
	
	public ProductControllerImpl(ProductDao model, ProductView view) {
        super.pr_model = model;
        super.pr_view  = view;
        
        //... Add listeners to the view.
        //view.addSubmitButtonListener(new SubmitListener());
        view.addNewMovieListener(new AddNewMovie());
        view.submitSearchListener(new Search());
        view.viewByBoxItemStateChanged(new ViewByBoxListener());
        view.viewByOptionBoxItemStateChanged(new ViewByOptionBoxListener());
        view.searchFieldFocusGained(new SearchFieldAdapter());
        getAll();
	}
	
	class ViewByBoxListener implements ItemListener {
        public void itemStateChanged(ItemEvent e) {
        	pr_view.getViewByOptionBox().setModel(new DefaultComboBoxModel(new String[] { "blah", "blah, blah" }));
        	pr_view.getViewByOptionBox().validate();
        }
	}
	
	class ViewByOptionBoxListener implements ItemListener {
        public void itemStateChanged(ItemEvent e) {
        	pr_view.getSearchField().setText("Blah");
        }
	}
	
	class AddNewMovie implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	ManageProductView dialog = new ManageProductView(new javax.swing.JFrame(), false, pr_model);
            dialog.setVisible(true);
            ManageProductControllerImpl m_pr_controller = new ManageProductControllerImpl(pr_model, dialog, pr_view);
        }
	}
	
	class Search implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	String title = pr_view.getSearchField().getText();
            product = getOne(title);
            if (product.isEmpty()) {
            	pr_view.getNoticeLabel().setVisible(true);
            }
            else {
            	ProductDetailsView dialog = new ProductDetailsView(new javax.swing.JFrame(), true, product);
            	dialog.setVisible(true);
            	
            	//TO BE IMPLEMENTED
            	
            	//ProductDetailsControllerImpl pr_det_controller = new ProductDetailsControllerImpl(pr_model, dialog);
            }
            product.clear();
        }
	}
	
	class SearchFieldAdapter implements FocusListener {
        public void focusGained(FocusEvent e) {
			pr_view.getNoticeLabel().setVisible(false);
		}

		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}
