package dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import dao.ProductDao;
import model.Product;

public class ProductDaoImpl extends DaoImpl implements ProductDao{

	private ArrayList<Object> product_list;
	private ArrayList<Object> items;
	private ArrayList<Object> details;
	private Product product;

	public ProductDaoImpl(EntityManager em){
		super.setEntityManager(em);
	}

	public ArrayList<Object> getAllItems() {
		items = new ArrayList<Object>();
		TypedQuery<Product> query = getEntityManager().createQuery("SELECT p FROM Product p ORDER BY title", Product.class);
		List<Product> results = query.getResultList();
		for (int i=0; i<results.size(); i++) {
			items.add(results.get(i).getTitle());
			items.add(results.get(i).getGenre());
			items.add(results.get(i).getRating());
			items.add(results.get(i).getYear());
			items.add(results.get(i).getType());
		}
		return items;
	}

	public ArrayList<Object> getItemDetails(String title, String type) {
		details = new ArrayList<Object>();
		TypedQuery<Product> query = getEntityManager().createQuery("SELECT p FROM Product p WHERE p.title='" + title + "' and p.type='" + type + "'", Product.class);
		List<Product> result = query.getResultList();
		if (!result.isEmpty()) {
			details.add(result.get(0).getTitle());
			details.add(result.get(0).getGenre());
			details.add(result.get(0).getRating());
			details.add(result.get(0).getYear());
			details.add(result.get(0).getType());
			details.add(result.get(0).getDescription());
			details.add(result.get(0).getAvailability());
		}
		return details;
	}

	public void updateItem(ArrayList<Object> product) {
		TypedQuery<Product> query = getEntityManager().createQuery("SELECT p FROM Product p WHERE p.title='" + product.get(0).toString() + "'", Product.class);
		List<Product> result = query.getResultList();
		getEntityManager().getTransaction().begin();
		for (int i=0; i<result.size(); i++){
			result.get(i).setTitle(product.get(0).toString());
			result.get(i).setGenre(product.get(1).toString());
			result.get(i).setRating(product.get(2).toString());
			result.get(i).setYear(Integer.parseInt(product.get(3).toString()));
			result.get(i).setType(product.get(4).toString());
			result.get(i).setDescription(product.get(5).toString());
		}
		getEntityManager().getTransaction().commit();
	}
	
	public void toggleAvailability (Product product) {
		getEntityManager().getTransaction().begin();
		product.setAvailability(!product.getAvailability());
		getEntityManager().getTransaction().commit();
	}

	public ArrayList<Object> getByGenre(String genre) {
		product_list = new ArrayList<Object>();
		TypedQuery<Product> query = getEntityManager().createQuery("SELECT p FROM Product p WHERE p.genre='" + genre + "' ORDER BY title", Product.class);
		List<Product> result = query.getResultList();
		for(int i=0;i<result.size();i++){
			product_list.add(result.get(i).getTitle());
			product_list.add(result.get(i).getGenre());
			product_list.add(result.get(i).getRating());
			product_list.add(result.get(i).getYear());
			product_list.add(result.get(i).getType());
		}
		return product_list;
	}

	public ArrayList<Object> getByRating(String rating) {
		product_list = new ArrayList<Object>();
		TypedQuery<Product> query = getEntityManager().createQuery("SELECT p FROM Product p WHERE p.rating='" + rating + "' ORDER BY title", Product.class);
		List<Product> result = query.getResultList();
		for(int i=0;i<result.size();i++){
			product_list.add(result.get(i).getTitle());
			product_list.add(result.get(i).getGenre());
			product_list.add(result.get(i).getRating());
			product_list.add(result.get(i).getYear());
			product_list.add(result.get(i).getType());
		}
		return product_list;
	}

	public ArrayList<Object> getByYear(int year) {
		product_list = new ArrayList<Object>();
		TypedQuery<Product> query = getEntityManager().createQuery("SELECT p FROM Product p WHERE p.year=" + year + " ORDER BY title", Product.class);
		List<Product> result = query.getResultList();
		for(int i=0;i<result.size();i++){
			product_list.add(result.get(i).getTitle());
			product_list.add(result.get(i).getGenre());
			product_list.add(result.get(i).getRating());
			product_list.add(result.get(i).getYear());
			product_list.add(result.get(i).getType());
		}
		return product_list;
	}

	public ArrayList<Object> getByType(String type) {
		product_list = new ArrayList<Object>();
		TypedQuery<Product> query = getEntityManager().createQuery("SELECT p FROM Product p WHERE p.type='" + type + "' ORDER BY title", Product.class);
		List<Product> result = query.getResultList();
		for(int i=0;i<result.size();i++){
			product_list.add(result.get(i).getTitle());
			product_list.add(result.get(i).getGenre());
			product_list.add(result.get(i).getRating());
			product_list.add(result.get(i).getYear());
			product_list.add(result.get(i).getType());
		}
		return product_list;
	}

	public Product getItem(String title, String type){
		product = new Product();
		TypedQuery<Product> query = getEntityManager().createQuery("SELECT p FROM Product p WHERE p.title like '%" + title + "%' and p.type= '" + type + "'", Product.class);
		try {
			product = query.getSingleResult();
		}
		catch (NoResultException e){
			return null;
		}
		return product;
	}

}
