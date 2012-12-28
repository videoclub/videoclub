package login;

import dao.ProductDao;
import dao.impl.ProductDaoImpl;
import entity.ProductEntity;

public class Login {
	
	private static ManageLogin ml;
	private static ProductEntity pe;
	private static ProductDao pd;

	public static void main(String[] args) {
		//aspect example start
		ml = new ManageLogin();
		ml.checkLogin("greg");
		//aspect example finish
		
		//get all products/get specific product example start
		pe = new ProductEntity();
		pd = new ProductDaoImpl();
		pd.openConnection();
		System.out.println(pd.getAllItems());
		System.out.println(pd.getItemDetails("title2")); //bale the "The Mofo Knight" esi.
		//get all products/get specific product example finish
	}

}
