package dao;

import java.util.ArrayList;

public interface GenericDao {
	
	public void openConnection();
	public void persist(Object o);
	//public Object[][] getAllItems();
	public ArrayList<Object> getAllItems();
	//public Object[] getItemDetails(long id);
	public ArrayList<Object> getItemDetails(String title);
	public void closeConnection();
	
}
