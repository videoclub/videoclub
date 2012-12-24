package login;

import java.sql.Timestamp;

import dao.ProductDao;
import dao.impl.ProductDaoImpl;
import entity.ProductEntity;

public class Login {
	
	private static ManageLogin ml;
	
	private static ProductEntity pe;
	private static ProductDao pd;

	public static void main(String[] args) {
		ml = new ManageLogin();
		ml.checkLogin("greg");
		
		pd = new ProductDaoImpl();
		pe = new ProductEntity();
		pe.setTitle("The Mofo Knight");
		pe.setGenre("Action");
		pe.setRate(7);
		pe.setAvail(true);
		pe.setType("DVD");
		pe.setYear(2008);
		pe.setCreateDate(Timestamp.valueOf("2007-09-23 10:10:10.0"));
		pe.setEditDate(Timestamp.valueOf("2009-09-23 23:11:55.0"));
		pd.openConnection();
		pd.persist(pe);
		System.out.println(pd.getAllItems());
		System.out.println(pd.getItemDetails(pe.getTitle()));
		pd.closeConnection();
		
	}

}
