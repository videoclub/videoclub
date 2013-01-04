package controller;

import java.util.ArrayList;

public interface Controller {
	void getAll();
	ArrayList<Object> getOne(String title);
	void set();
	void dbConnect();
	void dbDisconnect();
}