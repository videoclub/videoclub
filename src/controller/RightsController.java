package controller;

import main.Main;

public class RightsController {
	public boolean userCanManageProduct(){
		if (Main.rights.contains("manage_product"))
			return true;
		
		return false;
	}
	
	public boolean userCanManageCustomers(){
		if (Main.rights.contains("manage_customer"))
			return true;
		
		return false;
	}

}
