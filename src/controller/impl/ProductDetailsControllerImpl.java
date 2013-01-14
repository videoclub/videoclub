package controller.impl;

import main.Main;
import view.ProductDetailsView;
import controller.ProductDetailsController;

public class ProductDetailsControllerImpl  extends ProductControllerImpl implements ProductDetailsController{

	public ProductDetailsControllerImpl(ProductDetailsView dialog, Object availability) {
		pr_details_view = dialog;
		product = availability.toString();
		if (Main.rights != null)
			enableRentBindButton();
	}

	private void enableRentBindButton() {
		if (product.equalsIgnoreCase("Available") && userCanManageCustomers()) {
			pr_details_view.enableEditButton();
			pr_details_view.enableBindButton();
		}
		else if (product.equalsIgnoreCase("Available"))
			pr_details_view.enableRentButton();
	}
	
	private ProductDetailsView pr_details_view;
	private String product;

}
