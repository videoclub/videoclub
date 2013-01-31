package controller;

import dao.ProductDao;
import model.Product;

public interface ProductDetailsController extends ProductController{
	void toggleAvailability();
}
