package controller.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import main.Main;
import view.ManageProductView;
import view.ManageUserView;
import view.ProductDetailsView;
import view.ProductView;
import view.UserDetailsView;
import view.UserView;
import controller.ManageProductController;
import controller.ManageUserController;
import controller.PersistenceController;
import controller.UserDetailsController;
import controller.impl.ProductDetailsControllerImpl.EditMovie;
import dao.ProductDao;
import dao.UserDao;

public class UserDetailsControllerImpl  extends UserControllerImpl implements UserDetailsController {

	public UserDetailsControllerImpl(UserDao dao, UserView view, UserDetailsView dialog, ArrayList<Object> u, int row) {
		user_dao = dao;
		user_view = view;
		user_details_view = dialog;
		user = u;
		tableRow = row;
		
		if (userCanManageCustomers()) {
			user_details_view.showEditButton();
			
			user_details_view.addEditMovieListener(new EditUser());
		}
	}
	
	class EditUser implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	openEditUserView();
        }
	}
	
	private void openEditUserView() {
		//Create and show a new JDialog to enable adding a new movie
    	ManageUserView dialog = new ManageUserView(new javax.swing.JFrame(), false);
        setEditView(dialog);
        setEditViewFields(dialog, user);
        //Create the appropriate controller to interact with the JDialog
        ManageUserController m_user_controller = new ManageUserControllerImpl(user_dao, dialog, user_view, tableRow);
        dialog.setVisible(true);
        user_details_view.dispose();
	}

	private void setEditView(ManageUserView dialog) {
		dialog.getHeaderLabel().setText("Edit User");
		dialog.getResetButton().setEnabled(false);
		dialog.getResetButton().setVisible(false);
		dialog.getAddButton().setEnabled(false);
		dialog.getAddButton().setVisible(false);
		dialog.getEditButton().setEnabled(true);
		dialog.getEditButton().setVisible(true);
	}
	
	private void setEditViewFields(ManageUserView dialog, ArrayList<Object> user) {
		dialog.getUsernameField().setText(user.get(5).toString());
		dialog.getPasswordField().setText(user.get(6).toString());
		dialog.getNameField().setText(user.get(2).toString());
		dialog.getEmailField().setText(user.get(3).toString());
		dialog.getPhoneField().setText(user.get(4).toString());
		dialog.getProfileBox().setSelectedItem(user.get(0));
	}
	
	private UserDetailsView user_details_view;
	private ArrayList<Object> user;
	private int tableRow;
}
