package dao;

import java.util.ArrayList;

public interface Dao {
	
	//void openConnection();
	void persist(Object o);
	abstract ArrayList<Object> getAllItems();
	abstract ArrayList<Object> getItemDetails(String title);
	abstract void updateItem(ArrayList<Object> Object);
	//void closeConnection();
	
}
