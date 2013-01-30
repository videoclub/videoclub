package controller.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import main.Main;
import model.Order;
import model.Product;
import model.User;

import controller.BindProductController;
import controller.PersistenceController;

import view.BindProductView;
import view.ProductView;
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
	}
	
	private void fillProductLabels() {
		pr_bind_view.getTitleLabel().setText(product.getTitle());
		pr_bind_view.getTypeLabel().setText(product.getType());
	}
	
	class SearchUser implements ActionListener {
       
		public void actionPerformed(ActionEvent e) {
        	UserDao userDao = new UserDaoImpl(em);
        	User user = userDao.getUserByEmail(pr_bind_view.getSearchUserField().getText());
	        Order order = new Order(user, product);
    		OrderDao orderDao = new OrderDaoImpl(em);
    		orderDao.persist(order);
    		System.out.println(order.getUser().getUsername());
    		System.out.println(order.getProduct().getTitle());
    		printOrders();
        }
	}
	
	private void printOrders() {
		OrderDao orderDao = new OrderDaoImpl(em);
		ArrayList<Object> orders = new ArrayList<Object>();
		orders = orderDao.getAllItems();
		System.out.println(orders);
	}

	class BindMovie implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            
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
	
}
