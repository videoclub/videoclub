package dao;

import java.util.ArrayList;

public interface ProductDao extends Dao{
	void updateProductDetails(ArrayList<Object> product);
	ArrayList<Object> getByGenre(String genre);
	ArrayList<Object> getByRating(String rating);
	ArrayList<Object> getByYear(int year);
	ArrayList<Object> getByType(String type);
}
