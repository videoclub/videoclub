package dao;

import java.util.ArrayList;

public interface Dao {
	
	void persist(Object o);
	abstract ArrayList<Object> getAllItems();
	abstract ArrayList<Object> getItemDetails(String arg1, String arg2);
	abstract void updateItem(ArrayList<Object> Object);
	abstract Object getItem(String arg1, String arg2);
	
}
