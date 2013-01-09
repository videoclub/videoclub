package dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Profile;
import model.User;
import dao.UserDao;

public class UserDaoImpl extends DaoImpl implements UserDao{

	private User user;
	private ArrayList<Object> user_list;
	private ArrayList<Object> user_details;
	
	public UserDaoImpl(EntityManager em){
		super.setEntityManager(em);
	}
	
	//get all users(show only user-name and email)
	public ArrayList<Object> getAllItems() {
		user_list = new ArrayList<Object>();
		if(user.getProfile().getLabel().equalsIgnoreCase("employee")){
			TypedQuery<User> query = getEntityManager().createQuery("SELECT u FROM User u", User.class);
			List<User> results = query.getResultList();
			for (int i=0; i<results.size(); i++) {
				user_list.add(results.get(i).getUsername());
				user_list.add(results.get(i).getEmail());
			}
		}
		return user_list;
	}

	//get user details (search by user-name)
	public ArrayList<Object> getItemDetails(String username) {
		user_details = new ArrayList<Object>();
		TypedQuery<User> query = getEntityManager().createQuery("SELECT u FROM User u WHERE u.username='" + username + "'", User.class);
		List<User> result = query.getResultList();
		if (!result.isEmpty()) {
			for (int i=0; i<result.size(); i++){
				user_details.add(result.get(i).getUsername());
				user_details.add(result.get(i).getProfile());
				user_details.add(result.get(i).getName());
				user_details.add(result.get(i).getEmail());
				user_details.add(result.get(i).getPhone());
			}
		}
		
		return user_details;
	}

	//update user details
	public void updateItem(ArrayList<Object> user) {
		TypedQuery<User> query = getEntityManager().createQuery("SELECT u FROM User u WHERE u.username='" + user.get(0).toString() + "'", User.class);
		List<User> result = query.getResultList();
		getEntityManager().getTransaction().begin();
		for (int i=0; i<result.size(); i++){
			result.get(i).setUsername(user.get(0).toString());
			result.get(i).setProfile((Profile) user.get(1));
			result.get(i).setName(user.get(2).toString());
			result.get(i).setEmail(user.get(3).toString());
			result.get(i).setPhone(user.get(4).toString());
		}
		getEntityManager().getTransaction().commit();
	}

	public ArrayList<Object> searchByProfile(Profile profile) {
		user_details = new ArrayList<Object>();
		TypedQuery<User> query = getEntityManager().createQuery("SELECT u FROM User u WHERE u.profile='" + profile + "'", User.class);
		List<User> result = query.getResultList();
		for(int i=0;i<result.size();i++){
			user_details.add(result.get(i).getUsername());
			user_details.add(result.get(i).getProfile());
			user_details.add(result.get(i).getName());
			user_details.add(result.get(i).getEmail());
			user_details.add(result.get(i).getPhone());
		}
		return user_details;
	}

	public ArrayList<Object> searchByName(String name) {
		user_details = new ArrayList<Object>();
		TypedQuery<User> query = getEntityManager().createQuery("SELECT u FROM User u WHERE u.name='" + name + "'", User.class);
		List<User> result = query.getResultList();
		for(int i=0;i<result.size();i++){
			user_details.add(result.get(i).getUsername());
			user_details.add(result.get(i).getProfile());
			user_details.add(result.get(i).getName());
			user_details.add(result.get(i).getEmail());
			user_details.add(result.get(i).getPhone());
		}
		return user_details;
	}

	public ArrayList<Object> searchByEmail(String email) {
		user_details = new ArrayList<Object>();
		TypedQuery<User> query = getEntityManager().createQuery("SELECT u FROM User u WHERE u.email='" + email + "'", User.class);
		List<User> result = query.getResultList();
		for(int i=0;i<result.size();i++){
			user_details.add(result.get(i).getUsername());
			user_details.add(result.get(i).getProfile());
			user_details.add(result.get(i).getName());
			user_details.add(result.get(i).getEmail());
			user_details.add(result.get(i).getPhone());
		}
		return user_details;
	}

	public ArrayList<Object> searchByPhone(String phone) {
		user_details = new ArrayList<Object>();
		TypedQuery<User> query = getEntityManager().createQuery("SELECT u FROM User u WHERE u.phone='" + phone + "'", User.class);
		List<User> result = query.getResultList();
		for(int i=0;i<result.size();i++){
			user_details.add(result.get(i).getUsername());
			user_details.add(result.get(i).getProfile());
			user_details.add(result.get(i).getName());
			user_details.add(result.get(i).getEmail());
			user_details.add(result.get(i).getPhone());
		}
		return user_details;
	}

}
