package dao;

import java.util.ArrayList;

public interface ProductDao extends GenericDao{
	public ArrayList<Object> getByGenre(String genre);
	public ArrayList<Object> getByRating(String rating);
	public ArrayList<Object> getByYear(int year);
	public ArrayList<Object> getByType(String type);
}
