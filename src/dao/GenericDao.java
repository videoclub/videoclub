package dao;

import java.util.ArrayList;

public interface GenericDao {
	
	public void openConnection();
	public void persist(Object o);
	public Object[][] getAllItems();
	public Object[] getItemDetails(long l);
	public void closeConnection();
	
}
