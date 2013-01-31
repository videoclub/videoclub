package controller.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.Order;
import model.Product;
import model.User;
import view.BindProductView;
import view.ProductView;
import controller.BindProductController;
import controller.PersistenceController;
import dao.OrderDao;
import dao.ProductDao;
import dao.UserDao;
import dao.impl.OrderDaoImpl;
import dao.impl.UserDaoImpl;

public class BindProductControllerImpl extends ProductControllerImpl implements BindProductController{

	public BindProductControllerImpl(ProductDao dao, BindProductView dialog, ProductView view, Product prod) {
		pr_dao = dao;
		pr_bind_view = dialog;
		pr_view = view;
		product = prod;
		
		fillProductLabels();
		pr_bind_view.addBindMovieListener(new BindMovie());
		pr_bind_view.addSearchUserListener(new SearchUser());
		pr_bind_view.addSearchFieldFocusGained(new SearchFieldAdapter());
	}
	
	private void fillProductLabels() {
		pr_bind_view.getTitleLabel().setText(product.getTitle());
		pr_bind_view.getTypeLabel().setText(product.getType());
	}
	
	class SearchUser implements ActionListener {

		public void actionPerformed(ActionEvent e) {
        	UserDao userDao = new UserDaoImpl(em);
        	user = userDao.getUserByEmail(pr_bind_view.getSearchUserField().getText());
        	if (user == null) {
        		pr_bind_view.getNoticeLabel().setVisible(true);
        	}
        	else {
        		pr_bind_view.getBindButton().setEnabled(true);
        	}
        		
        }
	}
	
	class SearchFieldAdapter implements FocusListener {
        public void focusGained(FocusEvent e) {
        	//Notice label disappears when user is going to search for another movie
			pr_bind_view.getNoticeLabel().setVisible(false);
		}
        
        //Not to be used
		public void focusLost(FocusEvent e) {
			return;
			
		}
	}

	class BindMovie implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	Order order = new Order(user, product);
    		OrderDao orderDao = new OrderDaoImpl(em);
    		orderDao.persist(order);
    		toggleAvailability();
    		pr_bind_view.dispose();
        }
	}
	
	@Override
	public void toggleAvailability() {
		pr_dao.toggleAvailability(product);		
	}
	
	private EntityManagerFactory emf = PersistenceController.getInstance().getEntityManagerFactory();
	private EntityManager em = emf.createEntityManager();
	
	private BindProductView pr_bind_view;
	private Product product;
	private User user;
	
}
