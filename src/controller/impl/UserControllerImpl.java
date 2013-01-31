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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.JFrame;
import javax.swing.JTable;

import model.Profile;
import model.User;
import view.ManageUserView;
import view.UserDetailsView;
import view.UserView;
import controller.ManageUserController;
import controller.PersistenceController;
import controller.UserController;
import controller.UserDetailsController;
import dao.UserDao;
import dao.impl.UserDaoImpl;

public class UserControllerImpl extends ControllerImpl implements UserController {

	public UserControllerImpl() {
		
	}

	public UserControllerImpl(UserView uView) {
        user_view = uView;
        user_dao = new UserDaoImpl(em);
        
        //... Add listeners to the view.
        uView.addNewUserListener(new AddNewUser());
        uView.addSubmitSearchListener(new UserSearch());
        uView.addSearchFieldFocusGained(new UserSearchFieldAdapter());
        uView.addMouseListener(new UserTableMouseAdapter());
        uView.addButtonsGroupItemStateChanged(new ButtonsGroupListener());
        
        //getAllUsers to populate JTable
        getAll();
	}
	
	class AddNewUser implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	//Create and show a new JDialog to enable adding a new user
        	ManageUserView dialog = new ManageUserView(new JFrame(), false);
            dialog.setVisible(true);
            
        	//Create the appropriate controller to interact with the JDialog
        	@SuppressWarnings("unused")
			ManageUserController m_user_controller = new ManageUserControllerImpl(user_dao, dialog, user_view);
        }
	}
	
	class UserSearch implements ActionListener {

		public void actionPerformed(ActionEvent e) {
        	//Instantiate a new user
			get_user = new ArrayList<Object>();
	        String email = user_view.getSearchField().getText();
	        String profile = user_view.getUserProfile();
	        get_user = getOne(email, profile);
            
	        try {
            	showUserDetails();
            }
            //if no user matches the search criteria print a notice message 
            catch (IndexOutOfBoundsException ioe) {
            	user_view.getNoticeLabel().setText("There are no results matching your search criteria.");
            	user_view.getNoticeLabel().setForeground(Color.red.darker());
            	user_view.getNoticeLabel().setVisible(true);
            }
        }
	}
	
	private void showUserDetails() {
    	//Create and show a new JDialog to show user details
    	UserDetailsView dialog = new UserDetailsView(new javax.swing.JFrame(), true, get_user);
    	
    	// TO BE IMPLEMENTED
		//Create the appropriate controller to interact with the JDialog
    	@SuppressWarnings("unused")
		UserDetailsController user_det_controller = new UserDetailsControllerImpl(user_dao, user_view, dialog, get_user, row);
    	dialog.setVisible(true);
	}
	
	class UserSearchFieldAdapter implements FocusListener {
        public void focusGained(FocusEvent e) {
        	//Notice label disappears when user is going to search for another movie
			user_view.getNoticeLabel().setVisible(false);
		}
        
        //Not to be used
		public void focusLost(FocusEvent e) {
			return;
			
		}
	}
	
	class ButtonsGroupListener implements ItemListener {
        public void itemStateChanged(ItemEvent e) {
        	//if statement because StateChange means unselecting a product AND selecting another one
        	if (e.getStateChange() == 1) {
        		if (e.getSource() == user_view.getCustomerRadio())
        			user_view.setUserProfile("customer");
        		else if (e.getSource() == user_view.getEmployeeRadio())
        			user_view.setUserProfile("employee");
        	}
       	}
    }
	
	class UserTableMouseAdapter implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {
		         JTable usersTable = (JTable)e.getSource();
		         row = usersTable.getSelectedRow();
		         get_user = getUser(usersTable, row);
		         showUserDetails();
			}
		}

		private ArrayList<Object> getUser(JTable moviesTable, int row) {
			String email = moviesTable.getValueAt(row, 1).toString();
			String profile = moviesTable.getValueAt(row, 3).toString();
			return getOne(email, profile);
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
	
	@Override
	public void getAll() {
		ArrayList<Object> allUsers = new ArrayList<Object>();
		allUsers = user_dao.getAllItems();
		user_view.showAll(allUsers);
	}

	@Override
	public ArrayList<Object> getOne(String email, String profile) {
		ArrayList<Object> oneProduct = user_dao.getItemDetails(email, profile);
		return oneProduct;
	}

	@Override
	public void set() {
		setUser();
	}
	
	@SuppressWarnings("deprecation")
	private void setUser() {
		set_user = new User();
		set_user.setUsername(manage_user_view.getUsernameField().getText());
		set_user.setPassword(manage_user_view.getPasswordField().getText());
		set_user.setName(manage_user_view.getNameField().getText());
		set_user.setEmail(manage_user_view.getEmailField().getText());
		set_user.setPhone(manage_user_view.getPhoneField().getText());
		Profile profile = user_dao.getProfileFromLabel(manage_user_view.getProfileBox().getSelectedItem().toString());
		set_user.setProfile(profile);
	    user_dao.persist(set_user);
	}

	@Override
	public void update(ArrayList<Object> item) {
		user_dao.updateItem(item);
		
	}
	
	private EntityManagerFactory emf = PersistenceController.getInstance().getEntityManagerFactory();
	private EntityManager em = emf.createEntityManager();

	protected UserView user_view;
	protected UserDao user_dao;
	protected ManageUserView manage_user_view;
	
	private ArrayList<Object> get_user;
	private int row;
	
	private User set_user;
}
