package controller.impl;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import view.ManageProductView;
import view.ManageUserView;
import view.ProductView;
import view.UserView;
import controller.ManageProductController;
import controller.ManageUserController;
import dao.ProductDao;
import dao.UserDao;

public class ManageUserControllerImpl extends UserControllerImpl implements ManageUserController{

	private int row;
	//private ProductDao pr_dao;
	//private ProductView pr_view;

	// Controller Constructor for Add Movie Action
	public ManageUserControllerImpl(UserDao model, ManageUserView m_view, UserView view) {
        ur_dao = model;
        manage_ur_view  = m_view;
        ur_view  = view;
        
        //... Add listeners to the view.
        m_view.addSubmitButtonListener(new SubmitListener());
        m_view.addResetButtonListener(new ResetListener());
	}
	
	// Controller Constructor for Edit Movie Action
	public ManageUserControllerImpl(UserDao model, ManageUserView m_view, UserView view, int row) {
        ur_dao = model;
        manage_ur_view  = m_view;
        ur_view  = view;
        this.row = row;
	}
	
	//Submit the newly added product to the DB and call method to immediately show this product to the Jtable
	class SubmitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	set();
        	populateUser();
        	updateNotice();
        }

		private void populateUser() {
			String name = manage_ur_view.getNameField().getText();
        	ArrayList<Object> user = getOne(name);
        	ur_view.addOne(user);
		}
		
		private void updateNotice() {
			String name = manage_ur_view.getNameField().getText();
			ur_view.getNoticeLabel().setText(name + " successfully added!");
        	ur_view.getNoticeLabel().setForeground(Color.green.darker().darker());
        	ur_view.getNoticeLabel().setVisible(true);
		}
	}

	class ResetListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	manage_ur_view.reset();
        }
	}
	
	class EditListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	ArrayList<Object> user = new ArrayList<Object>();
        	user.add(manage_ur_view.getNameField().getText());
        	user.add(manage_ur_view.getEmailField().getText());
        	user.add(manage_ur_view.getPasswordField().toString());
        	user.add(manage_ur_view.getUserNameField().getText());
        	user.add(manage_ur_view.getPhoneField().getText());
        	update(user);
        	ur_view.updateRow(row, user);
        	updateNotice(user.get(0).toString());
        }

		private void updateNotice(String title) {
        	ur_view.getNoticeLabel().setText(title + " successfully updated!");
        	ur_view.getNoticeLabel().setForeground(Color.green.darker().darker());
        	ur_view.getNoticeLabel().setVisible(true);
			
		}
	}
}
