package controller.impl;

import main.Main;
import controller.Controller;

public abstract class ControllerImpl implements Controller{
	
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
