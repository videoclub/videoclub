package controller.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.JFrame;

import model.Order;
import model.User;

import view.ManageUserView;
import view.OrderView;
import view.ProductView;
import view.UserView;

import controller.ManageUserController;
import controller.OrderController;
import controller.PersistenceController;
import controller.UserController;
import controller.impl.UserControllerImpl.AddNewUser;
import dao.OrderDao;
import dao.ProductDao;
import dao.UserDao;
import dao.impl.OrderDaoImpl;
import dao.impl.UserDaoImpl;

public class OrderControllerImpl extends ControllerImpl implements OrderController{

	public OrderControllerImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public OrderControllerImpl(OrderView view) {
		order_view = view;
        order_dao = new OrderDaoImpl(em);
        
        //... Add listeners to the view.
        order_view.addDelayedOrdersListener(new DelayedOrdersListener());
        
      //getAllOrders to populate JTable
        getAll();
	}

	class DelayedOrdersListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	ArrayList<Object> delayedOrders = new ArrayList<Object>();
    		delayedOrders = order_dao.getDelayedOrders();
    		order_view.showAll(delayedOrders);
        }
	}
	
	@Override
	public void getAll() {
		ArrayList<Object> allOrders = new ArrayList<Object>();
		allOrders = order_dao.getAllItems();
		order_view.showAll(allOrders);
	}

	@Override
	public ArrayList<Object> getOne(String title, String type) {
		// TODO Auto-generated method stub
		return null;
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

	protected OrderView order_view;
	protected OrderDao order_dao;
	//protected ManageOrderView manage_order_view;
	
}
