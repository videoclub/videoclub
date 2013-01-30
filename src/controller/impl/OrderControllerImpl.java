package controller.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.JFrame;
import javax.swing.JTable;

import model.Order;
import model.User;

import view.ManageOrderView;
import view.ManageProductView;
import view.ManageUserView;
import view.OrderView;
import view.ProductView;
import view.UserView;

import controller.ManageOrderController;
import controller.ManageProductController;
import controller.ManageUserController;
import controller.OrderController;
import controller.PersistenceController;
import controller.UserController;
import controller.impl.ProductControllerImpl.TableMouseAdapter;
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
        order_view.addOrdersTableListener(new TableMouseAdapter());
        
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
	
	class TableMouseAdapter implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {
		         JTable ordersTable = (JTable)e.getSource();
		         row = ordersTable.getSelectedRow();
		         column = ordersTable.getSelectedColumn();
		         applyAction(ordersTable, column);
			}
		}

		private void applyAction(JTable ordersTable, int column ) {
			switch (column){
				case 0:
					showOrderDetails(ordersTable.getValueAt(row, 0).toString());
					break;
				case 1:
					pr = new String[2];
					pr = formatProduct(ordersTable.getValueAt(row, 1).toString());
					getProductHistory(pr[0], pr[1]);
					break;
				case 2:
					String email;
					email = getEmail(ordersTable.getValueAt(row, 2).toString());
					getUserHistory(email);
					break;
				default:
					getAll();
			
			}
		}

		private void showOrderDetails(String orderNo) {
			Order order = (Order) order_dao.getItem(orderNo, null);
			ManageOrderView dialog = new ManageOrderView(new JFrame(), false);
            //Create the appropriate controller to interact with the JDialog
            ManageOrderController m_pr_controller = new ManageOrderControllerImpl(order_dao, dialog, order_view, order, row);
            dialog.setVisible(true);
		}

		private String[] formatProduct(String product) {
			int split = product.indexOf("(");
			pr[0] = product.substring(0, split);
			pr[1] = product.substring(split+1, product.length()-1);
			return pr;
		}
		
		private String getEmail(String user) {
			int split = user.indexOf("(");
			return user.substring(0, split);
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
		ArrayList<Object> allOrders = new ArrayList<Object>();
		allOrders = order_dao.getAllItems();
		order_view.showAll(allOrders);
	}
	
	@Override
	public void getProductHistory(String title, String type) {
		ArrayList<Object> productHistory = new ArrayList<Object>();
		productHistory = order_dao.getOrderDetailsByProduct(title, type);
		order_view.showAll(productHistory);
	}

	@Override
	public void getUserHistory(String email) {
		ArrayList<Object> userHistory = new ArrayList<Object>();
		userHistory = order_dao.getOrderDetailsByUser(email);
		order_view.showAll(userHistory);
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
	protected ManageOrderView manage_order_view;
	
	protected int row;
	private int column;
	private String[] pr;
	
}
