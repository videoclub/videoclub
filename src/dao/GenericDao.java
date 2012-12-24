package dao;

import java.util.ArrayList;

public interface GenericDao {
	
	public void openConnection();
	public void persist(Object o);
	public ArrayList<String> getAllItems();
	public ArrayList<String> getItemDetails(String item);
	public void closeConnection();

}
