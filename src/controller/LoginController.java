package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.JFrame;

import controller.impl.ProductControllerImpl;
import controller.impl.UserControllerImpl;

import view.LoginView;
import view.ProductView;
import dao.UserDao;
import dao.impl.UserDaoImpl;
import main.Main;
import model.User;

public class LoginController extends UserControllerImpl{
	private EntityManagerFactory emf = PersistenceController.getInstance().getEntityManagerFactory();
	private EntityManager em = emf.createEntityManager();
	
	private LoginView login_view;
	private ProductView pr_view;
	private ActionListener logout;
	private ActionListener login;

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
		User user = dao.getUser(username);
		if (user != null && user.getPassword().equalsIgnoreCase(password)){
			Main.name = user.getName();
			Main.rights = user.getProfile().getRightLabels();
			userLoggedIn();
			ProductController prc = new ProductControllerImpl();
			login_view.dispose();
			return;
		}
		login_view.loginFailed();
			
	}
	
	private void userLoggedIn() {
		pr_view.userLoggedIn();
        pr_view.getLoginLabel().setText("Welcome " + Main.name);
        
		if (userCanManageProduct()){
        	showEditLabel();
        	showAddMovieButton();
        }
        
        if (userCanManageCustomers()){
        	showManageCustomerButton();
        }
	}
	
	private void showAddMovieButton() {
		pr_view.showAddMovieButton();		
	}
	
    private void showEditLabel() {
		pr_view.showEditLabel();
		
	}
    
    private void showManageCustomerButton() {
		pr_view.showManageCustomerButton();
    }

}
