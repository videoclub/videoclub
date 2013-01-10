package dao;

import java.util.ArrayList;

import model.Profile;
import model.User;

public interface UserDao extends Dao{
	User getUser(String username);
	Profile getProfile(String username);
	void updateItem(ArrayList<Object> user);
	ArrayList<Object> searchByProfile(Profile profile);
	ArrayList<Object> searchByName(String name);
	ArrayList<Object> searchByEmail(String email);
	ArrayList<Object> searchByPhone(String phone);
}
