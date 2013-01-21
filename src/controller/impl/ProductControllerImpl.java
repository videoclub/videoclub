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

import controller.LoginController;
import controller.ManageProductController;
import controller.ProductController;
import controller.ProductDetailsController;
import view.LoginView;
import view.ManageProductView;
import view.ProductDetailsView;
import view.ProductView;
import dao.ProductDao;
import dao.UserDao;
import dao.impl.UserDaoImpl;

public class ProductControllerImpl extends ControllerImpl implements ProductController{
	
	protected ProductDao pr_dao;
	protected ProductView pr_view;
	protected ManageProductView manage_pr_view;
	private ArrayList<Object> get_product;
	private Product set_product;

	//private EntityManagerFactory emf = PersistenceController.getInstance().getEntityManagerFactory();
	//private EntityManager em = emf.createEntityManager();
	
	
	public ProductControllerImpl(){
		
	}
	
	public ProductControllerImpl(ProductDao model, ProductView view) {
        pr_dao = model;
        pr_view = view;
        
        //... Add listeners to the view.
        view.addNewMovieListener(new AddNewMovie());
        view.addSubmitSearchListener(new Search());
        view.addViewByBoxItemStateChanged(new ViewByBoxListener());
        view.addViewByOptionBoxItemStateChanged(new ViewByOptionBoxListener());
        view.addSearchFieldFocusGained(new SearchFieldAdapter());
        view.addMouseListener(new TableMouseAdapter());
        view.addLogListener(new Log());
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
	
	class AddNewMovie implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	//Create and show a new JDialog to enable adding a new movie
        	ManageProductView dialog = new ManageProductView(new JFrame(), false);
            dialog.setVisible(true);
            //Create the appropriate controller to interact with the JDialog
            ManageProductController m_pr_controller = new ManageProductControllerImpl(pr_dao, dialog, pr_view);
        }
	}
	
	
	//Skata ton ekana... sorry!!!
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
        	get_product = getOne(title);
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
    	//enableRentBindButton(dialog);
    	ProductDetailsController pr_det_controller = new ProductDetailsControllerImpl(dialog, get_product.get(6));
    	dialog.setVisible(true);
	}
	
	class TableMouseAdapter implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {
		         JTable moviesTable = (JTable)e.getSource();
		         int row = moviesTable.getSelectedRow();
		         ArrayList<Object> product = getProduct(moviesTable, row);
		         get_product = new ArrayList<Object>();
		         String title = product.get(0).toString();
		         get_product = getOne(title);
		         showProductDetails();
		         //openEditProductView(product, row);
			}
		}

		private void openEditProductView(ArrayList<Object> product, int row) {
			//Create and show a new JDialog to enable adding a new movie
        	ManageProductView dialog = new ManageProductView(new javax.swing.JFrame(), false);
            dialog.setVisible(true);
            setEditView(dialog);
            setEditViewFields(dialog, product);
            //Create the appropriate controller to interact with the JDialog
            ManageProductController m_pr_controller = new ManageProductControllerImpl(pr_dao, dialog, pr_view, row);
		}

		private void setEditView(ManageProductView dialog) {
			dialog.getHeaderLabel().setText("Edit Movie");
			dialog.getResetButton().setEnabled(false);
			dialog.getResetButton().setVisible(false);
			dialog.getSubmitButton().setEnabled(false);
			dialog.getSubmitButton().setVisible(false);
			dialog.getEditButton().setEnabled(true);
			dialog.getEditButton().setVisible(true);
		}

		private ArrayList<Object> getProduct(JTable moviesTable, int row) {
			String title = moviesTable.getValueAt(row, 0).toString();
			return getOne(title);
		}
		
		private void setEditViewFields(ManageProductView dialog, ArrayList<Object> product) {
			dialog.getTitleField().setText(product.get(0).toString());
			dialog.getGenreBox().setSelectedItem(product.get(1));
			dialog.getRatingBox().setSelectedItem(product.get(2));
			dialog.getYearBox().setSelectedItem(product.get(3).toString());
			dialog.getTypeBox().setSelectedItem(product.get(4));
			dialog.getDescription().setText(product.get(5).toString());
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
	public ArrayList<Object> getOne(String title) {
		ArrayList<Object> oneProduct = pr_dao.getItemDetails(title);
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
    	getAll();
	}
}
