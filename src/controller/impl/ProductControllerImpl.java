package controller.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
        getGenres();
	}
	
	class ViewByBoxListener implements ItemListener {
        public void itemStateChanged(ItemEvent e) {
        	if (e.getStateChange() ==1) {
        		switch (e.getItem().toString()){
        			case "Genre":
        				getGenres();
        				break;
        			case "Rating":
        				getRatings();
        				break;
        			case "Year":
        				pr_view.populateYears();
        				break;
        			case "Type":
        				pr_view.populateTypes();
        				break;
        			default:
        				getGenres();
        				break;
        		}
        	}
        }
	}
	
	class ViewByOptionBoxListener implements ItemListener {
        public void itemStateChanged(ItemEvent e) {
        	String view_by = pr_view.getViewByBox().getSelectedItem().toString();
        	if (e.getStateChange() ==1) {
        		switch (view_by){
        			case "Genre":
        				getByGenre(e.getItem().toString());
        				break;
        			case "Rating":
        				getByRating(e.getItem().toString());
        				break;
        			case "Year":
        				getByYear(Integer.parseInt(e.getItem().toString()));
        				break;
        			case "Type":
        				getByType(e.getItem().toString());
        				break;
        			default:
        				getAll();
        				break;
        		}
        	}
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
            	
            	//TO BE IMPLEMENTED for rent/bind button listeners
            	
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
	
	@Override
	public void getGenres() {
		dbConnect();
		ArrayList<Object> allGenres = pr_model.getGenreDistinct();
		dbDisconnect();
		pr_view.populateGenres(allGenres);
	}
	
	@Override
	public void getRatings() {
		dbConnect();
		ArrayList<Object> allRatings = pr_model.getRatingDistinct();
		dbDisconnect();
		pr_view.populateRatings(allRatings);
	}

	@Override
	public void getByGenre(String genre) {
		dbConnect();
		ArrayList<Object> products = pr_model.getByGenre(genre);
		dbDisconnect();
		pr_view.showPart(products);
	}

	@Override
	public void getByRating(String rating) {
		dbConnect();
		ArrayList<Object> products = pr_model.getByRating(rating);
		dbDisconnect();
		pr_view.showPart(products);
	}

	@Override
	public void getByYear(int year) {
		dbConnect();
		ArrayList<Object> products = pr_model.getByYear(year);
		dbDisconnect();
		pr_view.showPart(products);
	}

	@Override
	public void getByType(String type) {
		dbConnect();
		ArrayList<Object> products = pr_model.getByType(type);
		dbDisconnect();
		pr_view.showPart(products);
	}
}
