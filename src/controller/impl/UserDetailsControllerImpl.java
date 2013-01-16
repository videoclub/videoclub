package controller.impl;

import main.Main;
import view.ProductDetailsView;
import view.UserDetailsView;
import controller.ProductDetailsController;
import controller.UserDetailsController;

public class UserDetailsControllerImpl  extends UserControllerImpl implements UserDetailsController{

	public UserDetailsControllerImpl(UserDetailsView dialog, Object availability) {
		ur_details_view = dialog;
		product = availability.toString();
	}
	
	private UserDetailsView ur_details_view;
	private String product;

}
