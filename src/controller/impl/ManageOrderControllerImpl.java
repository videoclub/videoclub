package controller.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.Order;
import model.Product;
import view.ManageOrderView;
import view.OrderView;
import controller.ManageOrderController;
import controller.PersistenceController;
import dao.OrderDao;
import dao.ProductDao;
import dao.impl.ProductDaoImpl;

public class ManageOrderControllerImpl  extends OrderControllerImpl implements ManageOrderController{

	public ManageOrderControllerImpl(OrderDao dao, ManageOrderView dialog, OrderView view, Order o, int r) {
		order_dao = dao;
		m_order_view = dialog;
		order_view = view;
		order = o;
		row = r;
		showDetails();
		enableReturnButton();
		
	}

	private void showDetails() {
		m_order_view.setOrderHeader("Order #" + order.getOrder_number());
		m_order_view.setTitleLabel(order.getProduct().getTitle());
		m_order_view.setTypeLabel(order.getProduct().getType());
		m_order_view.setUserLabel(order.getUser().getName() + " (" + order.getUser().getEmail() + ")");
	}

	private void enableReturnButton() {
		if (!order.getReturned()) {
			m_order_view.enableReturnButton();
			m_order_view.addSetReturnedButtonListener(new SetReturnButton());
		}
		
	}

	class SetReturnButton implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	setOrderAsReturned();
        	setProductAsReturned();
        	updateOrdersTable();
        	m_order_view.dispose();
        }

		private void setOrderAsReturned() {
			order_dao.setAsReturned(order);
			
		}
		
		private void setProductAsReturned() {
			ProductDao pr_dao = new ProductDaoImpl(em);
			Product product = (Product) pr_dao.getItem(order.getProduct().getTitle(), order.getProduct().getType());
        	pr_dao.toggleAvailability(product);
		}
		
		private void updateOrdersTable() {
			order_view.updateReturned(row);
			m_order_view.dispose();	
		}
	}
	
	private EntityManagerFactory emf = PersistenceController.getInstance().getEntityManagerFactory();
	private EntityManager em = emf.createEntityManager();
	
	private ManageOrderView m_order_view;
	private Order order;
	
}
