package dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import model.Profile;
import model.User;
import dao.UserDao;

public class UserDaoImpl extends DaoImpl implements UserDao{

	private User user;
	private ArrayList<Object> user_list;
	private ArrayList<Object> user_details;
	private Profile profile;

	public UserDaoImpl(EntityManager em){
		super.setEntityManager(em);
	}

	public Profile getProfile(String username){
		user = new User();
		TypedQuery<User> query = getEntityManager().createQuery("SELECT u FROM User u WHERE u.username='" + username + "'", User.class);
		user = query.getSingleResult();
		return user.getProfile();
	}
	
	 public Profile getProfileFromLabel(String profileLabel) {
		  profile = new Profile();
		  TypedQuery<Profile> query = getEntityManager().createQuery("SELECT p FROM Profile p WHERE p.label='" + profileLabel + "'", Profile.class);
		  profile = query.getSingleResult();
		  return profile;
	}
	
	public User getItem(String username, String password){
		user = new User();
		TypedQuery<User> query = getEntityManager().createQuery("SELECT u FROM User u WHERE u.username='" + username + "'", User.class);
		try {
			user = query.getSingleResult();
		}
		catch (NoResultException e){
			return null;
		}
		return user;
	}
	
	public User getUserByEmail(String email){
		user = new User();
		TypedQuery<User> query = getEntityManager().createQuery("SELECT u FROM User u WHERE u.email='" + email + "'", User.class);
		try {
			user = query.getSingleResult();
		}
		catch (NoResultException e){
			return null;
		}
		return user;
	}

	//get all users(show only user-name and email)
	public ArrayList<Object> getAllItems() {
		user_list = new ArrayList<Object>();
		TypedQuery<User> query = getEntityManager().createQuery("SELECT u FROM User u ORDER BY u.name", User.class);
		List<User> results = query.getResultList();
		for (int i=0; i<results.size(); i++) {
			user_list.add(results.get(i).getName());
			user_list.add(results.get(i).getEmail());
			user_list.add(results.get(i).getPhone());
			user_list.add(results.get(i).getProfile().getLabel());			
		}
		return user_list;
	}

	//get user details (search by user-name)
	//PROSOXI! to arg2 profanws tha allaksei kai tha mpei analogos auto pou theloume. px profile klp
	public ArrayList<Object> getItemDetails(String email, String profile) {
		user_details = new ArrayList<Object>();
		TypedQuery<User> query = getEntityManager().createQuery("SELECT u FROM User u WHERE u.email='" + email + "'", User.class);
		List<User> result = query.getResultList();
		if (!result.isEmpty() && result.get(0).getProfile().getLabel().equalsIgnoreCase(profile)) {
			user_details.add(result.get(0).getProfile().getLabel());
			user_details.add(result.get(0).getId());
			user_details.add(result.get(0).getName());
			user_details.add(result.get(0).getEmail());
			user_details.add(result.get(0).getPhone());
			user_details.add(result.get(0).getUsername());
			user_details.add(result.get(0).getPassword());
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
			result.get(i).setPassword(user.get(1).toString());
			result.get(i).setName(user.get(2).toString());
			result.get(i).setEmail(user.get(3).toString());
			result.get(i).setPhone(user.get(4).toString());
			result.get(i).setProfile((Profile) user.get(5));
		}
		getEntityManager().getTransaction().commit();
	}

	/*
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
	*/
}
