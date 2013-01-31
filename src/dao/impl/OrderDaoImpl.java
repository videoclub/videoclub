package dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import model.Order;
import dao.OrderDao;

public class OrderDaoImpl  extends DaoImpl implements OrderDao{

	private ArrayList<Object> order_list;

	private Order order;
	
	private Date now;

	public OrderDaoImpl(EntityManager em){
		super.setEntityManager(em);
	}

	@Override
	public ArrayList<Object> getAllItems() {
		order_list = new ArrayList<Object>();
		TypedQuery<Order> query = getEntityManager().createQuery("SELECT o FROM Order o ORDER BY order_number DESC", Order.class);
		List<Order> results = query.getResultList();
		for (int i=0; i<results.size(); i++) {
			String userRow = results.get(i).getUser().getEmail() + "(" +
								results.get(i).getUser().getName() + ")";
			String productRow = results.get(i).getProduct().getTitle() + "(" +
								results.get(i).getProduct().getType() + ")";
			
			order_list.add(results.get(i).getOrder_number());
			order_list.add(productRow);
			order_list.add(userRow);
			order_list.add(results.get(i).getOrderDate());
			order_list.add(results.get(i).getReturnDate());
			order_list.add(results.get(i).getReturned());
		}
		return order_list;
	}

	public ArrayList<Object> getDelayedOrders() {
		now = new Date();
		order_list = new ArrayList<Object>();
		TypedQuery<Order> query = getEntityManager().createQuery("SELECT o FROM Order o", Order.class);
		try{
			List<Order> results = query.getResultList();
			for(int i=0; i<results.size();i++){
				if(results.get(i).getReturnDate().before(now)){
					String userRow = results.get(i).getUser().getEmail() + "(" +
							results.get(i).getUser().getName() + ")";
					String productRow = results.get(i).getProduct().getTitle() + "(" +
							results.get(i).getProduct().getType() + ")";
		
					order_list.add(results.get(i).getOrder_number());
					order_list.add(productRow);
					order_list.add(userRow);
					order_list.add(results.get(i).getOrderDate());
					order_list.add(results.get(i).getReturnDate());
					order_list.add(results.get(i).getReturned());
				}
			}
		}catch(NoResultException nre){
			System.out.println(nre);
			return null;
		}catch(PersistenceException pe){
			System.out.println(pe);
			return null;
		}
		return order_list;
	}
	
	public ArrayList<Object> getOrderDetailsByProduct(String title, String type) {
		order_list = new ArrayList<Object>();
		TypedQuery<Order> query = getEntityManager().createQuery("SELECT o FROM Order o", Order.class);
		try{
			List<Order> results = query.getResultList();
			for(int i=0; i<results.size();i++){
				if(title.equals(results.get(i).getProduct().getTitle())
						&& type.equals(results.get(i).getProduct().getType())){
					String userRow = results.get(i).getUser().getEmail() + "(" +
							results.get(i).getUser().getName() + ")";
					String productRow = results.get(i).getProduct().getTitle() + "(" +
							results.get(i).getProduct().getType() + ")";
		
					order_list.add(results.get(i).getOrder_number());
					order_list.add(productRow);
					order_list.add(userRow);
					order_list.add(results.get(i).getOrderDate());
					order_list.add(results.get(i).getReturnDate());
					order_list.add(results.get(i).getReturned());
				}
			}
		}catch(NoResultException nre){
			System.out.println(nre);
			return null;
		}catch(PersistenceException pe){
			System.out.println(pe);
			return null;
		}
		return order_list;
	}

	public ArrayList<Object> getOrderDetailsByUser(String email) {
		order_list = new ArrayList<Object>();
		TypedQuery<Order> query = getEntityManager().createQuery("SELECT o FROM Order o", Order.class);
		try{
			List<Order> results = query.getResultList();
			for(int i=0; i<results.size();i++){
				//System.out.println(results.get(i).toString());
				if(email.equals(results.get(i).getUser().getEmail())){
					String userRow = results.get(i).getUser().getEmail() + "(" +
							results.get(i).getUser().getName() + ")";
					String productRow = results.get(i).getProduct().getTitle() + "(" +
							results.get(i).getProduct().getType() + ")";
		
					order_list.add(results.get(i).getOrder_number());
					order_list.add(productRow);
					order_list.add(userRow);
					order_list.add(results.get(i).getOrderDate());
					order_list.add(results.get(i).getReturnDate());
					order_list.add(results.get(i).getReturned());
				}
			}
		}catch(NoResultException nre){
			System.out.println(nre);
			return null;
		}catch(PersistenceException pe){
			System.out.println(pe);
			return null;
		}
		return order_list;
	}
	
	@Override
	public void updateItem(ArrayList<Object> Object) {
	}

	@Override
	public ArrayList<Object> getItemDetails(String arg1, String arg2) {
		return null;
	}

	@Override
	public Order getItem(String orderNo, String arg2) {
		order = new Order();
		long orderNumber = Long.parseLong(orderNo);
		TypedQuery<Order> query = getEntityManager().createQuery("SELECT o FROM Order o WHERE o.order_number=" + orderNumber, Order.class);
		try {
			order = query.getSingleResult();
		}
		catch (NoResultException e){
			return null;
		}
		return order;
	}

	public void setAsReturned (Order order) {
		getEntityManager().getTransaction().begin();
		order.setReturned(true);
		getEntityManager().getTransaction().commit();
	}
}
