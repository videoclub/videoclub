package controller;

import java.util.ArrayList;

public interface Controller {
	public boolean userCanManageProduct();
	public boolean userCanManageCustomers();
	abstract void getAll();
	abstract ArrayList<Object> getOne(String title, String type);
	abstract void set();
	abstract void update(ArrayList<Object> item);
}