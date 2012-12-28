package controller;

public interface ProductController extends Collection{
	void getByGenre(String genre);
	void getByRating(String rating);
	void getByYear(int year);
	void getByType(String type);
}
