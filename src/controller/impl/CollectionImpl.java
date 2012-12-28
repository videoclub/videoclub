package controller.impl;

import java.util.ArrayList;

import entity.ProductEntity;
import view.ProductView;
import controller.Collection;
import dao.ProductDao;

public class CollectionImpl implements Collection{
	
	protected ProductDao pr_model;
	protected ProductView  pr_view;
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
    	//product.setTitle(pr_view.getTitleField().getText());
    	//product.setGenre(pr_view.getGenreField().getText());
    	//product.setRate(pr_view.getRatingField().getText());
    	//product.setYear(Integer.parseInt(pr_view.getYearBox().getSelectedItem().toString()));
    	//product.setType(pr_view.getTypeBox().getSelectedItem().toString());
    	//pr_model.persist(product);
    	/*Object product = {"", "", "", "", ""};
		product[0] = pr_view.getTitleField();
		product[1] = pr_view.getRatingField();
		product[2] = pr_view.getGenreField();
		product[3] = pr_view.getYearBox();
		product[4] = pr_view.getTypeBox();
    	pr_model.persist(product);*/
	}
	
	public void dbDisconnect(){
		pr_model.closeConnection();
	}

}
