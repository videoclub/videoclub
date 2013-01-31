package dao;

import java.util.ArrayList;

import model.Profile;
import model.User;

public interface UserDao extends Dao{
	
	Profile getProfile(String username);
	void updateItem(ArrayList<Object> user);
	User getUserByEmail(String email);
	ArrayList<Object> searchByName(String name);
	/*
	ArrayList<Object> searchByProfile(Profile profile);
	ArrayList<Object> searchByEmail(String email);
	ArrayList<Object> searchByPhone(String phone);
	*/
	Profile getProfileFromLabel(String profileLabel);
}
