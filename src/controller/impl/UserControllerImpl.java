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

import model.User;

import view.LoginView;
import view.ManageProductView;
import view.ProductDetailsView;
import view.ProductView;
import view.UserDetailsView;
import view.UserView;

import controller.ManageProductController;
import controller.PersistenceController;
import controller.ProductDetailsController;
import controller.UserController;
import controller.impl.ProductControllerImpl.AddNewMovie;
import controller.impl.ProductControllerImpl.ButtonsGroupListener;
import controller.impl.ProductControllerImpl.Log;
import controller.impl.ProductControllerImpl.ManageCustomer;
import controller.impl.ProductControllerImpl.Search;
import controller.impl.ProductControllerImpl.SearchFieldAdapter;
import controller.impl.ProductControllerImpl.TableMouseAdapter;
import controller.impl.ProductControllerImpl.ViewByBoxListener;
import controller.impl.ProductControllerImpl.ViewByOptionBoxListener;
import dao.ProductDao;
import dao.UserDao;
import dao.impl.UserDaoImpl;

public class UserControllerImpl extends ControllerImpl implements UserController {
	
	public UserControllerImpl() {
		
	}

	public UserControllerImpl(UserView uView) {
        userView = uView;
        userDao = new UserDaoImpl(em);
        
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
        	
        	//ManageUserView dialog = new ManageUserView(new JFrame(), false);
            //dialog.setVisible(true);
            
        	//Create the appropriate controller to interact with the JDialog
            
        	//ManageUserController m_user_controller = new ManageUserControllerImpl(userDao, dialog, userView);
        }
	}
	
	class UserSearch implements ActionListener {

		public void actionPerformed(ActionEvent e) {
        	//Instantiate a new user
			get_user = new ArrayList<Object>();
	        String email = userView.getSearchField().getText();
	        String profile = userView.getUserProfile();
	        get_user = getOne(email, profile);
            
	        try {
            	showUserDetails();
            }
            //if no user matches the search criteria print a notice message 
            catch (IndexOutOfBoundsException ioe) {
            	userView.getNoticeLabel().setText("There are no results matching your search criteria.");
            	userView.getNoticeLabel().setForeground(Color.red.darker());
            	userView.getNoticeLabel().setVisible(true);
            }
        }
	}
	
	private void showUserDetails() {
    	//Create and show a new JDialog to show user details
    	UserDetailsView dialog = new UserDetailsView(new javax.swing.JFrame(), true, get_user);
    	
    	// TO BE IMPLEMENTED
		//Create the appropriate controller to interact with the JDialog
    	//UserDetailsController user_det_controller = new UserDetailsControllerImpl(userDao, userView, dialog, get_user, row);
    	dialog.setVisible(true);
	}
	
	class UserSearchFieldAdapter implements FocusListener {
        public void focusGained(FocusEvent e) {
        	//Notice label disappears when user is going to search for another movie
			userView.getNoticeLabel().setVisible(false);
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
        		if (e.getSource() == userView.getCustomerRadio())
        			userView.setUserProfile("customer");
        		else if (e.getSource() == userView.getEmployeeRadio())
        			userView.setUserProfile("employee");
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
		allUsers = userDao.getAllItems();
		userView.showAll(allUsers);
	}

	@Override
	public ArrayList<Object> getOne(String email, String profile) {
		ArrayList<Object> oneProduct = userDao.getItemDetails(email, profile);
		return oneProduct;
	}

	@Override
	public void set() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ArrayList<Object> item) {
		// TODO Auto-generated method stub
		
	}
	
	private EntityManagerFactory emf = PersistenceController.getInstance().getEntityManagerFactory();
	private EntityManager em = emf.createEntityManager();

	private UserView userView;
	private UserDao userDao;
	private ArrayList<Object> get_user;
	private int row;
}
