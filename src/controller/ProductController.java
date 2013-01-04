package controller;

public interface ProductController extends Controller{
	void getByGenre(String genre);
	void getByRating(String rating);
	void getByYear(int year);
	void getByType(String type);
}
