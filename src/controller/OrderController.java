package controller;

import java.util.ArrayList;

public interface OrderController extends Controller{
	void getProductHistory(String title, String type);
	void getUserHistory(String email);

}
