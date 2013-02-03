package controller.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import main.Main;
import model.Order;
import model.Product;
import view.BindProductView;
import view.ManageProductView;
import view.ProductDetailsView;
import view.ProductView;
import controller.BindProductController;
import controller.ManageProductController;
import controller.PersistenceController;
import controller.ProductDetailsController;
import dao.OrderDao;
import dao.ProductDao;
import dao.impl.OrderDaoImpl;

public class ProductDetailsControllerImpl  extends ProductControllerImpl implements ProductDetailsController{
	
	public ProductDetailsControllerImpl(ProductDao dao, ProductView view, ProductDetailsView dialog, ArrayList<Object> pr, int row) {
		pr_dao = dao;
		pr_view = view;
		pr_details_view = dialog;
		product = pr;
		tableRow = row;
		if (Main.rights != null)
			enableRentBindButton();
	}

	private void enableRentBindButton() {
		if (product.get(6).toString().equalsIgnoreCase("Available") && userCanManageCustomers()) {
			pr_details_view.enableEditButton();
			pr_details_view.enableBindButton();
			// Add employee's edit/bind Listeners
			pr_details_view.addBindListener(new BindMovie());
			pr_details_view.addEditMovieListener(new EditMovie());
		}
		else if (product.get(6).toString().equalsIgnoreCase("Available")) {
			pr_details_view.enableRentButton();
			// Add customer's rent Listener
			pr_details_view.addRentListener(new RentMovie());
		}
		
	}
	
	class RentMovie implements ActionListener {

		public void actionPerformed(ActionEvent e) {
    		prod = (Product) pr_dao.getItem(product.get(0).toString(), product.get(4).toString());
    		Order order = new Order(Main.current_user, prod);
    		OrderDao orderDao = new OrderDaoImpl(em);
    		orderDao.persist(order);
    		toggleAvailability();
    		pr_details_view.dispose();
        }
	}
	
	class BindMovie implements ActionListener {
        public void actionPerformed(ActionEvent e) {
    		Product prod = (Product) pr_dao.getItem(product.get(0).toString(), product.get(4).toString());
        	BindProductView dialog = new BindProductView(new javax.swing.JFrame(), false);
            //Create the appropriate controller to interact with the JDialog
            @SuppressWarnings("unused")
			BindProductController bind_pr_controller = new BindProductControllerImpl(pr_dao, dialog, pr_view, prod);
            dialog.setVisible(true);
            pr_details_view.dispose();
            
        }
	}
	
	public void toggleAvailability() {
		pr_dao.toggleAvailability(prod);
	}
	
	class EditMovie implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	openEditProductView();
        }
	}
	
	private void openEditProductView() {
		//Create and show a new JDialog to enable adding a new movie
    	ManageProductView dialog = new ManageProductView(new javax.swing.JFrame(), false);
        setEditView(dialog);
        setEditViewFields(dialog, product);
        //Create the appropriate controller to interact with the JDialog
        @SuppressWarnings("unused")
		ManageProductController m_pr_controller = new ManageProductControllerImpl(pr_dao, dialog, pr_view, tableRow);
        dialog.setVisible(true);
        pr_details_view.dispose();
	}

	private void setEditView(ManageProductView dialog) {
		dialog.getHeaderLabel().setText("Edit Movie");
		dialog.getResetButton().setEnabled(false);
		dialog.getResetButton().setVisible(false);
		dialog.getSubmitButton().setEnabled(false);
		dialog.getSubmitButton().setVisible(false);
		dialog.getEditButton().setEnabled(true);
		dialog.getEditButton().setVisible(true);
	}
	
	private void setEditViewFields(ManageProductView dialog, ArrayList<Object> product) {
		dialog.getTitleField().setText(product.get(0).toString());
		dialog.getGenreBox().setSelectedItem(product.get(1));
		dialog.getRatingBox().setSelectedItem(product.get(2));
		dialog.getYearBox().setSelectedItem(product.get(3).toString());
		dialog.getTypeBox().setSelectedItem(product.get(4));
		dialog.getDescription().setText(product.get(5).toString());
	}
	
	private EntityManagerFactory emf = PersistenceController.getInstance().getEntityManagerFactory();
	private EntityManager em = emf.createEntityManager();

	private ProductDetailsView pr_details_view;
	private ArrayList<Object> product;
	private int tableRow;
	
	private Product prod;

}
