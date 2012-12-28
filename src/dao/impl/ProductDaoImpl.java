package dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import dao.ProductDao;
import entity.ProductEntity;

public class ProductDaoImpl extends GenericDaoImpl implements ProductDao{
	
	private ArrayList<Object> product_list;

	public ArrayList<Object> getByGenre(String genre) {
		product_list = new ArrayList<Object>();
		TypedQuery<ProductEntity> query = em.createQuery("SELECT p FROM Products p WHERE p.genre='" + genre + "'", ProductEntity.class);
		List<ProductEntity> result = query.getResultList();
		for(int i=0;i<result.size();i++){
			product_list.add(result.get(i).getTitle());
			product_list.add(result.get(i).getGenre());
			product_list.add(result.get(i).getRate());
			product_list.add(result.get(i).getYear());
			product_list.add(result.get(i).getType());
			product_list.add(result.get(i).getDescription());
		}
		return product_list;
	}

	public ArrayList<Object> getByRating(String rating) {
		product_list = new ArrayList<Object>();
		TypedQuery<ProductEntity> query = em.createQuery("SELECT p FROM Products p WHERE p.rate='" + rating + "'", ProductEntity.class);
		List<ProductEntity> result = query.getResultList();
		for(int i=0;i<result.size();i++){
			product_list.add(result.get(i).getTitle());
			product_list.add(result.get(i).getGenre());
			product_list.add(result.get(i).getRate());
			product_list.add(result.get(i).getYear());
			product_list.add(result.get(i).getType());
			product_list.add(result.get(i).getDescription());
		}
		return product_list;
	}

	public ArrayList<Object> getByYear(int year) {
		product_list = new ArrayList<Object>();
		TypedQuery<ProductEntity> query = em.createQuery("SELECT p FROM Products p WHERE p.year=" + year, ProductEntity.class);
		List<ProductEntity> result = query.getResultList();
		for(int i=0;i<result.size();i++){
			product_list.add(result.get(i).getTitle());
			product_list.add(result.get(i).getGenre());
			product_list.add(result.get(i).getRate());
			product_list.add(result.get(i).getYear());
			product_list.add(result.get(i).getType());
			product_list.add(result.get(i).getDescription());
		}
		return product_list;
	}

	public ArrayList<Object> getByType(String type) {
		product_list = new ArrayList<Object>();
		TypedQuery<ProductEntity> query = em.createQuery("SELECT p FROM Products p WHERE p.type='" + type + "'", ProductEntity.class);
		List<ProductEntity> result = query.getResultList();
		for(int i=0;i<result.size();i++){
			product_list.add(result.get(i).getTitle());
			product_list.add(result.get(i).getGenre());
			product_list.add(result.get(i).getRate());
			product_list.add(result.get(i).getYear());
			product_list.add(result.get(i).getType());
			product_list.add(result.get(i).getDescription());
		}
		return product_list;
	}

	public ArrayList<Object> getGenreDistinct() {
		product_list = new ArrayList<Object>();
		TypedQuery<ProductEntity> query = em.createQuery("SELECT p FROM Products p", ProductEntity.class);
		List<ProductEntity> result = query.getResultList();
		for(int i=0;i<result.size();i++){
			if (!product_list.contains(result.get(i).getGenre()))
				product_list.add(result.get(i).getGenre());
		}
		return product_list;
	}

	public ArrayList<Object> getRatingDistinct() {
		product_list = new ArrayList<Object>();
		TypedQuery<ProductEntity> query = em.createQuery("SELECT p FROM Products p", ProductEntity.class);
		List<ProductEntity> result = query.getResultList();
		for(int i=0;i<result.size();i++){
			if (!product_list.contains(result.get(i).getRate()))
				product_list.add(result.get(i).getRate());
		}
		return product_list;
	}
}
