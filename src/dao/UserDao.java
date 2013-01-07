package dao;

import java.util.ArrayList;

public interface UserDao extends Dao{
	void updateUserDetails(ArrayList<Object> user);
}
