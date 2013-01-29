package controller.impl;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JTable;

import main.Main;
import model.Product;
import view.LoginView;
import view.ManageProductView;
import view.ProductDetailsView;
import view.ProductView;
import view.UserView;
import controller.LoginController;
import controller.ManageProductController;
import controller.ProductController;
import controller.ProductDetailsController;
import controller.UserController;
import dao.ProductDao;

public class ProductControllerImpl extends ControllerImpl implements ProductController{
	
	protected ProductDao pr_dao;
	protected ProductView pr_view;
	protected ManageProductView manage_pr_view;
	private ArrayList<Object> get_product;
	private Product set_product;
	private int row;

	//private EntityManagerFactory emf = PersistenceController.getInstance().getEntityManagerFactory();
	//private EntityManager em = emf.createEntityManager();
	
	
	public ProductControllerImpl(){
		
	}
	
	public ProductControllerImpl(ProductDao model, ProductView view) {
        pr_dao = model;
        pr_view = view;
        
        //... Add listeners to the view.
        view.addNewMovieListener(new AddNewMovie());
        view.addManageCustomerListener(new ManageCustomer());
        view.addSubmitSearchListener(new Search());
        view.addViewByBoxItemStateChanged(new ViewByBoxListener());
        view.addViewByOptionBoxItemStateChanged(new ViewByOptionBoxListener());
        view.addSearchFieldFocusGained(new SearchFieldAdapter());
        view.addMouseListener(new TableMouseAdapter());
        view.addLogListener(new Log());
        view.addButtonsGroupItemStateChanged(new ButtonsGroupListener());
        //getAllProducts to populate JTable when user initially logged in
        getAll();
	}

	class ViewByBoxListener implements ItemListener {
        public void itemStateChanged(ItemEvent e) {
        	//if statement because StateChange means unselecting a product AND selecting another one
        	if (e.getStateChange() == 1) {
        		//populate items in 2nd ComboBox based on 1st ComboBox selection
        		int view_by = pr_view.getViewByBox().getSelectedIndex();
        		switch (view_by){
        			case 1:
        				pr_view.populateGenres();
        				getByGenre("Action");
        				break;
        			case 2:
        				pr_view.populateRatings();
        				getByRating("UR (Unrated)");
        				break;
        			case 3:
        				pr_view.populateYears();
        				getByYear(Calendar.getInstance().get(Calendar.YEAR));
        				break;
        			case 4:
        				pr_view.populateTypes();
        				getByType("DVD");
        				break;
        			default:
        				getAll();
        				pr_view.getViewByOptionBox().setVisible(false);
        		}
        	}
        }
	}
	
	class ViewByOptionBoxListener implements ItemListener {
        public void itemStateChanged(ItemEvent e) {
        	//String to check in switch statement to call appropriate method
        	int view_by = pr_view.getViewByBox().getSelectedIndex();
        	if (e.getStateChange() == 1) {
        		switch (view_by){
        			case 1:
        				//populate items based on genre selection in the 2nd ComboBox
        				getByGenre(e.getItem().toString());
        				break;
        			case 2:
        				//populate items based on rating selection in the 2nd ComboBox
        				getByRating(e.getItem().toString());
        				break;
        			case 3:
        				//populate items based on year selection in the 2nd ComboBox
        				getByYear(Integer.parseInt(e.getItem().toString()));
        				break;
        			case 4:
        				//populate items based on type selection in the 2nd ComboBox
        				getByType(e.getItem().toString());
        				break;
        			default:
        				getAll();
        		}
        	}
        }
	}
	
	class ButtonsGroupListener implements ItemListener {
        public void itemStateChanged(ItemEvent e) {
        	//if statement because StateChange means unselecting a product AND selecting another one
        	if (e.getStateChange() == 1) {
        		if (e.getSource() == pr_view.getDvdRadio())
        			pr_view.setProductType("DVD");
        		else if (e.getSource() == pr_view.getBluerayRadio())
        			pr_view.setProductType("BlueRay");
        	}
       	}
    }
	
	class AddNewMovie implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	//Create and show a new JDialog to enable adding a new movie
        	ManageProductView dialog = new ManageProductView(new JFrame(), false);
            dialog.setVisible(true);
            //Create the appropriate controller to interact with the JDialog
            ManageProductController m_pr_controller = new ManageProductControllerImpl(pr_dao, dialog, pr_view);
        }
	}
	
	// TO BE IMPLEMENTED AFTER I (Lazaros) create the appropriate view
	class ManageCustomer implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	// Create and show a new JDialog to view a list of customers
        	// and be able to search and select any one of them for viewing/editing
        	UserView dialog = new UserView(new JFrame(), false);
        	dialog.setVisible(true);
            //Create the appropriate controller to interact with the JDialog
        	UserController user_controller = new UserControllerImpl(dialog);
            
        }
	}
	
	
	// Login/logout user and toggle the button accordingly
	class Log implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	if (Main.current_user == null) {
        		//Create and show a new JDialog to enable adding a new movie
        		LoginView dialog = new LoginView(new JFrame(), false);
        		dialog.setVisible(true);
        		//Create the appropriate controller to interact with the JDialog
        		LoginController login_controller = new LoginController(dialog, pr_view);
        	}
        	else {
        		Main.current_user = null;
        		Main.rights = null;
        		pr_view.userLoggedOut();
        	}
        }
	}
	
	class Search implements ActionListener {

		public void actionPerformed(ActionEvent e) {
        	//Instantiate a new product
			get_product = new ArrayList<Object>();
	        String title = pr_view.getSearchField().getText();
	        String type = pr_view.getProductType();
	        get_product = getOne(title, type);
            try {
            	showProductDetails();
            }
            //if no product matches the search criteria print a notice message 
            catch (IndexOutOfBoundsException ioe) {
            	pr_view.getNoticeLabel().setText("There are no results matching your search criteria.");
            	pr_view.getNoticeLabel().setForeground(Color.red.darker());
            	pr_view.getNoticeLabel().setVisible(true);
            }
        }
	}
	
	private void showProductDetails() {
    	//Create and show a new JDialog to show product details
    	ProductDetailsView dialog = new ProductDetailsView(new javax.swing.JFrame(), true, get_product);
    	
    	ProductDetailsController pr_det_controller = new ProductDetailsControllerImpl(pr_dao, pr_view, dialog, get_product, row);
    	dialog.setVisible(true);
	}
	
	class TableMouseAdapter implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {
		         JTable moviesTable = (JTable)e.getSource();
		         row = moviesTable.getSelectedRow();
		         get_product = getProduct(moviesTable, row);
		         showProductDetails();
			}
		}

		private ArrayList<Object> getProduct(JTable moviesTable, int row) {
			String title = moviesTable.getValueAt(row, 0).toString();
			String type = moviesTable.getValueAt(row, 4).toString();
			return getOne(title, type);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	class SearchFieldAdapter implements FocusListener {
        public void focusGained(FocusEvent e) {
        	//Notice label disappears when user is going to search for another movie
			pr_view.getNoticeLabel().setVisible(false);
		}
        
        //Not to be used
		public void focusLost(FocusEvent e) {
			return;
			
		}
	}

	//The following 4 methods are retrieving objects based on ComboBoxes selections
	//and calling productView 's showPart method to populate them to the JTable
	@Override
	public void getByGenre(String genre) {
		ArrayList<Object> products = pr_dao.getByGenre(genre);
		pr_view.showAll(products);
	}

	@Override
	public void getByRating(String rating) {
		ArrayList<Object> products = pr_dao.getByRating(rating);
		pr_view.showAll(products);
	}

	@Override
	public void getByYear(int year) {
		ArrayList<Object> products = pr_dao.getByYear(year);
		pr_view.showAll(products);
	}

	@Override
	public void getByType(String type) {
		ArrayList<Object> products = pr_dao.getByType(type);
		pr_view.showAll(products);
	}
	
	@Override
	public void getAll() {
		ArrayList<Object> allProducts = pr_dao.getAllItems();
		pr_view.showAll(allProducts);
	}

	@Override
	 public ArrayList<Object> getOne(String title, String type) {
		  ArrayList<Object> oneProduct = pr_dao.getItemDetails(title, type);
		  return oneProduct;
	}

	@Override
	public void set(){
    	setProduct();
    }
	
	private void setProduct() {
		set_product = new Product();
		set_product.setTitle(manage_pr_view.getTitleField().getText());
		set_product.setGenre(manage_pr_view.getGenreBox().getSelectedItem().toString());
		set_product.setRating(manage_pr_view.getRatingBox().getSelectedItem().toString());
		set_product.setYear(Integer.parseInt(manage_pr_view.getYearBox().getSelectedItem().toString()));
		set_product.setType(manage_pr_view.getTypeBox().getSelectedItem().toString());
		set_product.setDescription(manage_pr_view.getDescription().getText());
	    pr_dao.persist(set_product);
	}

	@Override
	public void update(ArrayList<Object> item){
		pr_dao.updateItem(item);
	}
}
