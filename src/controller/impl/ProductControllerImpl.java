package controller.impl;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import view.ProductView;
import dao.ProductDao;
import entity.ProductEntity;

public class ProductControllerImpl {
	private ProductDao pr_model;
	private ProductView  pr_view;
	private ProductEntity product = new ProductEntity();
	
	public ProductControllerImpl(ProductDao model, ProductView view) {
        pr_model = model;
        pr_view  = view;
        
        //... Add listeners to the view.
        view.addSubmitButtonListener(new SubmitListener());
        view.addResetButtonListener(new ResetListener());
        getAll();
    }
	
	
	public void getAll() {
		dbConnect();
		Object[][] allProducts = pr_model.getAllItems();
		dbDisconnect();
		pr_view.showAll(allProducts);
	}

	public Object[] getOne(long id) {
		dbConnect();
		Object[] oneProduct = pr_model.getItemDetails(id);
		dbDisconnect();
		return oneProduct;
	}
	
	public void set(){
		dbConnect();
    	setProduct();
    	dbDisconnect();
    }
    
    private void setProduct() {
    	product.setTitle(pr_view.getTitleField().getText());
    	product.setGenre(pr_view.getGenreField().getText());
    	product.setRate(pr_view.getRatingField().getText());
    	product.setYear(Integer.parseInt(pr_view.getYearBox().getSelectedItem().toString()));
    	product.setType(pr_view.getTypeBox().getSelectedItem().toString());
    	pr_model.persist(product);
    	/*Object product = {"", "", "", "", ""};
		product[0] = pr_view.getTitleField();
		product[1] = pr_view.getRatingField();
		product[2] = pr_view.getGenreField();
		product[3] = pr_view.getYearBox();
		product[4] = pr_view.getTypeBox();
    	pr_model.persist(product);*/
	}

	private void dbConnect(){
		pr_model.openConnection();
	}
	
	private void dbDisconnect(){
		pr_model.closeConnection();
	}
	
	class SubmitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	set();
        	getAll();
        }
	}
	
	class ResetListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	pr_view.reset();
        }
	}
}
