package login;

import model.Product;
import dao.ProductDao;
import dao.impl.ProductDaoImpl;

public class Login {
	
	private static ManageLogin ml;
	private static Product pe;
	private static ProductDao pd;

	public static void main(String[] args) {
		//aspect example start
		ml = new ManageLogin();
		ml.checkLogin("greg");
		//aspect example finish
		
		//get all products/get specific product example start
		pe = new Product();
		pd = new ProductDaoImpl();
		pd.openConnection();
		System.out.println(pd.getAllItems());
		System.out.println(pd.getItemDetails("title2")); //bale the "The Mofo Knight" esi.
		//get all products/get specific product example finish
	}

}
