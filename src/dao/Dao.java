package dao;

import java.util.ArrayList;

public interface Dao {
	
	public void openConnection();
	public void persist(Object o);
	//public Object[][] getAllItems();
	abstract ArrayList<Object> getAllItems();
	//public Object[] getItemDetails(long id);
	abstract ArrayList<Object> getItemDetails(String title);
	public void closeConnection();
	
}
