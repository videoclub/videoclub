package controller.impl;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.Profile;
import view.ManageUserView;
import view.UserView;
import controller.ManageUserController;
import dao.UserDao;

public class ManageUserControllerImpl extends UserControllerImpl implements ManageUserController {

	public ManageUserControllerImpl(UserDao userDao, ManageUserView dialog, UserView userView) {
		user_dao = userDao;
        manage_user_view  = dialog;
        user_view  = userView;
        
        manage_user_view.addSubmitButtonListener(new AddListener());
        manage_user_view.addResetButtonListener(new ResetListener());
	}
	
	public ManageUserControllerImpl(UserDao userDao, ManageUserView dialog, UserView userView, int row) {
		user_dao = userDao;
        manage_user_view  = dialog;
        user_view  = userView;
        this.row = row;
        
        //... Add listeners to the view.
        manage_user_view.addEditButtonListener(new EditListener());
	}

	class AddListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	set();
        	populateProduct();
        	updateNotice();
        	manage_user_view.dispose();
        }

		private void populateProduct() {
			String email = manage_user_view.getEmailField().getText();
			String profile = manage_user_view.getProfileBox().getSelectedItem().toString();
			ArrayList<Object> user = getOne(email, profile);
			user_view.addOne(user);
		}
		
		private void updateNotice() {
			String name = manage_user_view.getNameField().getText();
			user_view.getNoticeLabel().setText(name + " successfully added!");
			user_view.getNoticeLabel().setForeground(Color.green.darker().darker());
        	user_view.getNoticeLabel().setVisible(true);
		}
	}

	class ResetListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	manage_user_view.reset();
        }
	}
	
	class EditListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	ArrayList<Object> user = new ArrayList<Object>();
        	Profile profile = user_dao.getProfileFromLabel(manage_user_view.getProfileBox().getSelectedItem().toString());
        	user = getUserDetails(user, profile);
        	update(user);
        	
        	String profileLabel = profile.getLabel();
        	user.add(profileLabel);
        	
        	user_view.updateRow(row, user);
        	updateNotice(user.get(0).toString());
        	manage_user_view.dispose();
        }

		@SuppressWarnings("deprecation")
		private ArrayList<Object> getUserDetails(ArrayList<Object> user, Profile profile) {
			user.add(manage_user_view.getUsernameField().getText());
        	user.add(manage_user_view.getPasswordField().getText());
        	user.add(manage_user_view.getNameField().getText());
        	user.add(manage_user_view.getEmailField().getText());
        	user.add(manage_user_view.getPhoneField().getText());
        	user.add(profile);
        	return user;
		}

		private void updateNotice(String title) {
        	user_view.getNoticeLabel().setText(title + " successfully updated!");
        	user_view.getNoticeLabel().setForeground(Color.green.darker().darker());
        	user_view.getNoticeLabel().setVisible(true);
			
		}
	}
	
	private int row;

}
