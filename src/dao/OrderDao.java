package dao;

import java.util.ArrayList;

import model.Order;

public interface OrderDao extends Dao{
	ArrayList<Object> getDelayedOrders();
	ArrayList<Object> getOrderDetailsByProduct(String title, String type);
	ArrayList<Object> getOrderDetailsByUser(String email);
	void setAsReturned(Order order);
}
