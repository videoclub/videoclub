package controller.impl;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JTable;

import main.Main;
import model.Product;
import model.User;

import controller.ManageProductController;
import controller.ManageUserController;
import controller.ProductController;
import controller.ProductDetailsController;
import controller.UserController;
import controller.UserDetailsController;
import view.LoginView;
import view.ManageProductView;
import view.ManageUserView;
import view.ProductDetailsView;
import view.ProductView;
import view.UserDetailsView;
import view.UserView;
import dao.ProductDao;
import dao.UserDao;
import dao.impl.UserDaoImpl;

public class UserControllerImpl extends ControllerImpl implements UserController{
	
	protected UserDao ur_dao;
	protected UserView ur_view;
	protected ManageUserView manage_ur_view;
	private ArrayList<Object> get_user;
	private User set_user;
	
	public UserControllerImpl(){
		
	}
	
	public UserControllerImpl(UserDao model, UserView view) {
        ur_dao = model;
        ur_view = view;
        
        //... Add listeners to the view.
        
        view.addMouseListener(new TableMouseAdapter());
        
        view.addNewUserListener(new AddNewUser());
        view.submitSearchListener(new Search());
        //view.viewByBoxItemStateChanged(new ViewByBoxListener());
        view.searchFieldFocusGained(new SearchFieldAdapter());
        //getAllProducts to populate JTable when user initially logged in
        getAll();
	}
/*
	class ViewByBoxListener implements ItemListener {
        public void itemStateChanged(ItemEvent e) {
        	//if statement because StateChange means unselecting a product AND selecting another one
        	if (e.getStateChange() == 1) {
        		//populate items in 2nd ComboBox based on 1st ComboBox selection
        		int view_by = ur_view.getViewByBox().getSelectedIndex();
        		switch (view_by){
        			case 1:
        				ur_view.populateGenres();
        				getByGenre("Action");
        				break;
        			case 2:
        				ur_view.populateRatings();
        				getByRating("UR (Unrated)");
        				break;
        			case 3:
        				ur_view.populateYears();
        				getByYear(Calendar.getInstance().get(Calendar.YEAR));
        				break;
        			case 4:
        				ur_view.populateTypes();
        				getByType("DVD");
        				break;
        			default:
        				getAll();
        				ur_view.getViewByOptionBox().setVisible(false);
        		}
        	}
        }
	}
	
*/	
	class AddNewUser implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	//Create and show a new JDialog to enable adding a new user
        	ManageUserView dialog = new ManageUserView(new JFrame(), false);
            dialog.setVisible(true);
            //Create the appropriate controller to interact with the JDialog
            ManageUserController m_ur_controller = new ManageUserControllerImpl(ur_dao, dialog, ur_view);
        }
	}
	
	
	class Search implements ActionListener {

		public void actionPerformed(ActionEvent e) {
        	//Instantiate a new product
        	get_user = new ArrayList<Object>();
        	String title = ur_view.getSearchField().getText();
        	get_user = getOne(title);
            try {
            	showUserDetails();
            }
            //if no product matches the search criteria print a notice message 
            catch (IndexOutOfBoundsException ioe) {
            	ur_view.getNoticeLabel().setText("There are no results matching your search criteria.");
            	ur_view.getNoticeLabel().setForeground(Color.red.darker());
            	ur_view.getNoticeLabel().setVisible(true);
            }
        }
	}
	
	private void showUserDetails() {
    	//Create and show a new JDialog to show product details
    	UserDetailsView dialog = new UserDetailsView(new javax.swing.JFrame(), true, get_user);
    	//enableRentBindButton(dialog);
    	UserDetailsController ur_det_controller = new UserDetailsControllerImpl(dialog, get_user.get(6));
    	dialog.setVisible(true);
	}
	
	class TableMouseAdapter implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {
		         JTable usersTable = (JTable)e.getSource();
		         int row = usersTable.getSelectedRow();
		         ArrayList<Object> name = getUser(usersTable, row);
		         get_user = new ArrayList<Object>();
		         String title = name.get(0).toString();
		         get_user = getOne(title);
		         showUserDetails();
		         //openEditProductView(product, row);
			}
		}

		private void openEditProductView(ArrayList<Object> user, int row) {
			//Create and show a new JDialog to enable adding a new movie
        	ManageUserView dialog = new ManageUserView(new javax.swing.JFrame(), false);
            dialog.setVisible(true);
            setEditViewFields(dialog, user);
            //Create the appropriate controller to interact with the JDialog
            ManageUserController m_ur_controller = new ManageUserControllerImpl(ur_dao, dialog, ur_view, row);
		}

		private ArrayList<Object> getUser(JTable usersTable, int row) {
			String title = usersTable.getValueAt(row, 0).toString();
			return getOne(title);
		}
		
		private void setEditViewFields(ManageUserView dialog, ArrayList<Object> user) {
			dialog.getNameField().setText(user.get(0).toString());
			dialog.getEmailField().setText(user.get(1).toString());
			dialog.getPhoneField().setText(user.get(2).toString());
			dialog.getUserNameField().setText(user.get(3).toString());
			dialog.getPasswordField().setText(user.get(4).toString());
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	class SearchFieldAdapter implements FocusListener {
        public void focusGained(FocusEvent e) {
        	//Notice label disappears when user is going to search for another movie
			ur_view.getNoticeLabel().setVisible(false);
		}
        
        //Not to be used
		public void focusLost(FocusEvent e) {
			return;
			
		}
	}

	//The following 4 methods are retrieving objects based on ComboBoxes selections
	//and calling productView 's showPart method to populate them to the JTable
	/*
	@Override
	public void searchByName(String name) {
		ArrayList<Object> names = ur_dao.searchByName(name);
		ur_view.showAll(names);
	}

	@Override
	public void searchByEmail(String email) {
		ArrayList<Object> emails = ur_dao.searchByEmail(email);
		ur_view.showAll(emails);
	}

	@Override
	public void searchByPhone(int phone) {
		ArrayList<Object> phones = ur_dao.searchByPhone(phone);
		ur_view.showAll(phones);
	}
*/

	@Override
	public void getAll() {
		ArrayList<Object> allNames = ur_dao.getAllItems();
		ur_view.showAll(allNames);
	}

	@Override
	public ArrayList<Object> getOne(String title) {
		ArrayList<Object> oneProduct = ur_dao.getItemDetails(title);
		return oneProduct;
	}

	@Override
	public void set(){
    	setUser();
    }
	
	private void setUser() {
		set_user = new User();
		set_user.setName(manage_ur_view.getNameField().getText());
		set_user.setEmail(manage_ur_view.getEmailField().getText());
		set_user.setPassword(manage_ur_view.getPasswordField().toString());
		set_user.setUsername(manage_ur_view.getUserNameField().getText());
		set_user.setPhone(manage_ur_view.getPhoneField().getText());
		
	    ur_dao.persist(set_user);
	}

	@Override
	public void update(ArrayList<Object> item){
		ur_dao.updateItem(item);
    	getAll();
	}
}
