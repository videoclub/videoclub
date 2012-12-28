package controller.impl;

import java.util.ArrayList;

import entity.ProductEntity;
import view.ManageProductView;
import view.ProductView;
import controller.Collection;
import dao.ProductDao;

public class CollectionImpl implements Collection{
	
	protected ProductDao pr_model;
	protected ProductView  pr_view;
	protected ManageProductView  manage_pr_view;
	protected ProductEntity product = new ProductEntity();

	public void getAll() {
		dbConnect();
		ArrayList<Object> allProducts = pr_model.getAllItems();
		dbDisconnect();
		pr_view.showAll(allProducts);
	}

	public ArrayList<Object> getOne(String title) {
		dbConnect();
		ArrayList<Object> oneProduct = pr_model.getItemDetails(title);
		dbDisconnect();
		return oneProduct;
	}

	public void set(){
		dbConnect();
    	setProduct();
    	dbDisconnect();
    }
	
	public void dbConnect(){
		pr_model.openConnection();
	}
	
	private void setProduct() {
    	product.setTitle(manage_pr_view.getTitleField());
    	product.setGenre(manage_pr_view.getGenre());
    	product.setRate(manage_pr_view.getRating());
    	product.setYear(manage_pr_view.getYear());
    	product.setType(manage_pr_view.getTypeBox());
    	product.setDescription(manage_pr_view.getDescription());
    	pr_model.persist(product);
	}
	
	public void dbDisconnect(){
		pr_model.closeConnection();
	}

}
