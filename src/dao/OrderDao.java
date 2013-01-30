package dao;

import java.util.ArrayList;

import model.Order;
import model.User;

public interface OrderDao extends Dao{
	//ArrayList<Order> getPendingOrders();
	ArrayList<Object> getDelayedOrders();
	//ArrayList<Order> getPendingOrdersByUser(User user);
	//ArrayList<Order> getDelayedOrdersByUser(User user);
}
