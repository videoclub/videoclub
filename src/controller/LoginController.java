package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import main.Main;
import model.User;
import view.LoginView;
import view.ProductView;
import controller.impl.UserControllerImpl;
import dao.UserDao;
import dao.impl.UserDaoImpl;

public class LoginController extends UserControllerImpl{
	private EntityManagerFactory emf = PersistenceController.getInstance().getEntityManagerFactory();
	private EntityManager em = emf.createEntityManager();
	
	private LoginView login_view;
	private ProductView pr_view;
	
	public LoginController(LoginView dialog, ProductView p_view) {
		login_view = dialog;
		pr_view = p_view;
		
		//Add Submit Listener
		dialog.addSubmitListener(new Submit());
	}
	
	class Submit implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	String username = login_view.getUsername().getText();
        	@SuppressWarnings("deprecation")
			String password = login_view.getPassword().getText();
        	checkLogin(username, password);
        }
	}

	private void checkLogin(String username, String password) { 
		UserDao dao = new UserDaoImpl(em);
		User user = (User) dao.getItem(username, password);
		if (user != null && user.getPassword().equalsIgnoreCase(password)){
			Main.current_user = user;
			Main.rights = user.getProfile().getRightLabels();
			userLoggedIn();
			login_view.dispose();
			return;
		}
		login_view.loginFailed();
			
	}
	
	private void userLoggedIn() {
		pr_view.userLoggedIn();
        pr_view.getLoginLabel().setText("<html>Welcome <strong>" + Main.current_user.getName() + "</strong></html>");
        showUserDetailsLabel();
        
		if (userCanManageProduct()){
        	showAddMovieButton();
        	showViewOrdersButton();
        }
        
        if (userCanManageCustomers()){
        	showManageCustomerButton();
        }
	}
	
	private void showAddMovieButton() {
		pr_view.showAddMovieButton();		
	}
	
    private void showUserDetailsLabel() {
		pr_view.showUserDetailsLabel();
		
	}
    
    private void showManageCustomerButton() {
		pr_view.showManageCustomerButton();
    }
    
    private void showViewOrdersButton() {
		pr_view.showViewOrdersButton();
    }

}
