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

//import javax.swing.DefaultComboBoxModel;

import javax.swing.JTable;

import controller.ManageProductController;
import controller.ProductController;

import view.ManageProductView;
import view.ProductDetailsView;
import view.ProductView;
import dao.ProductDao;

public class ProductControllerImpl extends ControllerImpl implements ProductController{
	
	//private EntityManagerFactory emf = PersistenceController.getInstance().getEntityManagerFactory();
	//private EntityManager em = emf.createEntityManager();
	
	private ArrayList<Object> product;
	protected ProductDao pr_dao;
	protected ProductView pr_view;
	protected ManageProductView manage_pr_view;
	
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
        //getAllProducts to populate JTable when user initially logged in
        getAll();
        
        /*
         * if user => employee 
         */
        showEditLabel();
	}
	
	public ProductDao getPrDao() {
		return pr_dao;
	}

	public ProductView getPrView() {
		return pr_view;
	}

	private void showEditLabel() {
		pr_view.getEditMovieLabel().setVisible(true);
		
	}

	class ViewByBoxListener implements ItemListener {
        public void itemStateChanged(ItemEvent e) {
        	//if statement because StateChange means unselecting a product AND selecting another one
        	if (e.getStateChange() == 1) {
        		//populate items in 2nd ComboBox based on 1st ComboBox selection
        		switch (e.getItem().toString()){
        			case "Genre":
        				pr_view.populateGenres();
        				break;
        			case "Rating":
        				pr_view.populateRatings();
        				break;
        			case "Year":
        				pr_view.populateYears();
        				break;
        			case "Type":
        				pr_view.populateTypes();
        				break;
        			default:
        				pr_view.populateGenres();
        		}
        	}
        }
	}
	
	class ViewByOptionBoxListener implements ItemListener {
        public void itemStateChanged(ItemEvent e) {
        	//String to check in switch statement to call appropriate method
        	String view_by = pr_view.getViewByBox().getSelectedItem().toString();
        	if (e.getStateChange() ==1) {
        		switch (view_by){
        			case "Genre":
        				//populate items based on genre selection in the 2nd ComboBox
        				getByGenre(e.getItem().toString());
        				break;
        			case "Rating":
        				//populate items based on rating selection in the 2nd ComboBox
        				getByRating(e.getItem().toString());
        				break;
        			case "Year":
        				//populate items based on year selection in the 2nd ComboBox
        				getByYear(Integer.parseInt(e.getItem().toString()));
        				break;
        			case "Type":
        				//populate items based on type selection in the 2nd ComboBox
        				getByType(e.getItem().toString());
        				break;
        			default:
        				getAll();
        				break;
        		}
        	}
        }
	}
	
	class AddNewMovie implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	//Create and show a new JDialog to enable adding a new movie
        	ManageProductView dialog = new ManageProductView(new javax.swing.JFrame(), false, pr_dao);
            dialog.setVisible(true);
            //Create the appropriate controller to interact with the JDialog
            ManageProductController m_pr_controller = new ManageProductControllerImpl(pr_dao, dialog, pr_view);
        }
	}
	
	class Search implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	//Instantiate a new product
        	product = new ArrayList<Object>();
        	String title = pr_view.getSearchField().getText();
            product = getOne(title);
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

		private void showProductDetails() {
			String type = getType();
			//ATTENTION if both types are present it sets them concatenated only in this product instance. NOT in DB. 
        	product.set(4, type);
        	//Create and show a new JDialog to show product details
        	ProductDetailsView dialog = new ProductDetailsView(new javax.swing.JFrame(), true, product);
        	dialog.setVisible(true);
        	
        	//TO BE IMPLEMENTED for rent/bind button listeners
        	
        	//ProductDetailsControllerImpl pr_det_controller = new ProductDetailsControllerImpl(pr_model, dialog);
			
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
	
	class TableMouseAdapter implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {
		         JTable moviesTable = (JTable)e.getSource();
		         int row = moviesTable.getSelectedRow();
		         ArrayList<Object> product = getProduct(moviesTable, row);
		         openEditProductView(product, row);
			}
		}

		private void openEditProductView(ArrayList<Object> product, int row) {
			//Create and show a new JDialog to enable adding a new movie
        	ManageProductView dialog = new ManageProductView(new javax.swing.JFrame(), false, pr_dao);
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

	//The following 4 methods are retrieving objects based on ComboBoxes selections
	//and calling productView 's showPart method to populate them to the JTable
	@Override
	public void getByGenre(String genre) {
		ArrayList<Object> products = pr_dao.getByGenre(genre);
		pr_view.showPart(products);
	}

	@Override
	public void getByRating(String rating) {
		ArrayList<Object> products = pr_dao.getByRating(rating);
		pr_view.showPart(products);
	}

	@Override
	public void getByYear(int year) {
		ArrayList<Object> products = pr_dao.getByYear(year);
		pr_view.showPart(products);
	}

	@Override
	public void getByType(String type) {
		ArrayList<Object> products = pr_dao.getByType(type);
		pr_view.showPart(products);
	}
	
	private String getType() {
		//if product is available in both formats concatenate them and show both
		String type = product.get(4).toString();
    	if (product.size() > 6) {
    		type += ", " + product.get(10);
    	}
    	return type;
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
		product = new ArrayList<Object>();
		product.add(manage_pr_view.getTitleField().getText());
    	product.add(manage_pr_view.getGenreBox().getSelectedItem().toString());
    	product.add(manage_pr_view.getRatingBox().getSelectedItem().toString());
    	product.add(Integer.parseInt(manage_pr_view.getYearBox().getSelectedItem().toString()));
    	product.add(manage_pr_view.getTypeBox().getSelectedItem().toString());
    	product.add(manage_pr_view.getDescription().getText());
    	pr_dao.persist(product);
	}

	@Override
	public void update(ArrayList<Object> item){
		pr_dao.updateItem(item);
    	getAll();
	}
}
