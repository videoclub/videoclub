package dao;

import java.util.ArrayList;

import model.Profile;

public interface UserDao extends Dao{
	void updateUserDetails(ArrayList<Object> user);
	ArrayList<Object> searchByProfile(Profile profile);
	ArrayList<Object> searchByName(String name);
	ArrayList<Object> searchByEmail(String email);
	ArrayList<Object> searchByPhone(String phone);
}
