package controller.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        	ManageProductView dialog = new ManageProductView(new javax.swing.JFrame(), true);
            dialog.setVisible(true);
        }
	}
	
	class Search implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	String title = pr_view.getSearchField().getText();
            System.out.println(title);
            product = getOne(title);
            System.out.println(product);
        	ProductDetailsView dialog = new ProductDetailsView(new javax.swing.JFrame(), true, product);
            dialog.setVisible(true);
            product.clear();
        }
	}
	
	/*class SubmitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	set();
        	getOne(pr_view.getTitleField().getText());
        }
	}


	
	class ResetListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	pr_view.reset();
        }
	}*/
}
