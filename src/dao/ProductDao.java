package dao;

import java.util.ArrayList;
import model.Product;

public interface ProductDao extends Dao{
	void toggleAvailability (Product product);
	ArrayList<Object> getByGenre(String genre);
	ArrayList<Object> getByRating(String rating);
	ArrayList<Object> getByYear(int year);
	ArrayList<Object> getByType(String type);
}
