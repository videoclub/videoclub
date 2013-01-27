package controller;

import java.util.ArrayList;

public interface Controller {
	abstract void getAll();
	abstract ArrayList<Object> getOne(String title, String type);
	abstract void set();
	abstract void update(ArrayList<Object> item);
}