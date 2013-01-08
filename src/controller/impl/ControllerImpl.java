package controller.impl;

import java.util.ArrayList;

import model.Product;


import view.ManageProductView;
import view.ProductView;
import controller.Controller;
import dao.ProductDao;

public class ControllerImpl implements Controller{
	
	protected ProductDao pr_dao;
	protected ProductView  pr_view;
	protected ManageProductView  manage_pr_view;
	protected Product product;

	public void dbConnect(){
		pr_dao.openConnection();
	}
	
	public void dbDisconnect(){
		pr_dao.closeConnection();
	}
	
	public void getAll() {
		dbConnect();
		ArrayList<Object> allProducts = pr_dao.getAllItems();
		dbDisconnect();
		pr_view.showAll(allProducts);
	}

	public ArrayList<Object> getOne(String title) {
		dbConnect();
		ArrayList<Object> oneProduct = pr_dao.getItemDetails(title);
		dbDisconnect();
		return oneProduct;
	}

	public void set(){
		dbConnect();
    	setProduct();
    	dbDisconnect();
    }
	
	public void update(ArrayList<Object> item){
		dbConnect();
		pr_dao.updateProductDetails(item);
    	dbDisconnect();
    	getAll();
	}
	
	private void setProduct() {
		product = new Product();
    	product.setTitle(manage_pr_view.getTitleField().getText());
    	product.setGenre(manage_pr_view.getGenreBox().getSelectedItem().toString());
    	product.setRating(manage_pr_view.getRatingBox().getSelectedItem().toString());
    	product.setYear(Integer.parseInt(manage_pr_view.getYearBox().getSelectedItem().toString()));
    	product.setType(manage_pr_view.getTypeBox().getSelectedItem().toString());
    	product.setDescription(manage_pr_view.getDescription().getText());
    	pr_dao.persist(product);
	}

}
