package dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Order;
import model.User;

import dao.OrderDao;

public class OrderDaoImpl  extends DaoImpl implements OrderDao{
	
	private ArrayList<Object> order_list;

	public OrderDaoImpl(EntityManager em){
		super.setEntityManager(em);
	}

	@Override
	public ArrayList<Object> getAllItems() {
		order_list = new ArrayList<Object>();
		TypedQuery<Order> query = getEntityManager().createQuery("SELECT o FROM Order o", Order.class);
		List<Order> results = query.getResultList();
		for (int i=0; i<results.size(); i++) {
			order_list.add(results.get(i).getOrder_number());
			order_list.add(results.get(i).getOrderDate());
			order_list.add(results.get(i).getReturnDate());
			order_list.add(results.get(i).getReturned());
			order_list.add(results.get(i).getUser().getName());
			order_list.add(results.get(i).getProduct().getTitle());
			
		}
		return order_list;
	}

	@Override
	public void updateItem(ArrayList<Object> Object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Object> getItemDetails(String arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getItem(String arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}

}
