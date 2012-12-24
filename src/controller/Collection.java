package controller;

public interface Collection {
	Object[][] getAll();
	Object[] getOne(long id);
	void set();
}