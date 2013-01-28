/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.ProductDao;
import main.Main;

/**
 *
 * @author larry
 */
public class ProductView extends JFrame {
    /**
     * Creates new form ProductView
     */
    public ProductView() {
        initComponents();
        this.setSize(800, 600);
        this.setResizable(false);
    }
    
    // get() methods to give access to other classes
    public JTextField getSearchField(){
    	return searchField;
    }
    
    public JComboBox getViewByOptionBox(){
    	return viewByOptionBox;
    }
    
    public JComboBox getViewByBox(){
    	return viewByBox;
    }
    
    public JLabel getNoticeLabel(){
    	return this.noticeLabel;
    }
    
    public JLabel getEditMovieLabel(){
    	return this.showDetailsLabel;
    }
    
    public JLabel getLoginLabel(){
    	return this.loginLabel;
    }
    
    public JButton getLogButton(){
    	return this.logButton;
    }
    
    public JButton getAddMovieButton(){
    	return this.addMovieButton;
    }
    
    public JButton getManageCustomerButton(){
    	return this.manageCustomerButton;
    }
    // End of get() methods

    @SuppressWarnings("unchecked")
    private void initComponents() {

        mTableLabel = new JLabel();
        viewByLabel = new JLabel();
        tableScrollPane = new JScrollPane();
        viewByBox = new JComboBox();
        viewByOptionBox = new JComboBox();
        addMovieButton = new JButton();
        searchLabel = new JLabel();
        searchField = new JTextField();
        searchButton = new JButton();
        noticeLabel = new JLabel();
        showDetailsLabel = new JLabel();
        loginLabel = new JLabel();
        logButton = new JButton();
        manageCustomerButton = new JButton();
        
        moviesTable = new JTable(){
       	 public boolean isCellEditable(int row, int column){  
       		    return false;  
       		  } 
       };

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Video Club");

        mTableLabel.setFont(new Font("Ubuntu", 3, 24)); // NOI18N
        mTableLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mTableLabel.setText("Movies List");

        viewByLabel.setText("View Movies By:");
        
        moviesTable.setModel(model);
        moviesTable.setColumnSelectionAllowed(true);
        moviesTable.getTableHeader().setReorderingAllowed(false);
        tableScrollPane.setViewportView(moviesTable);
        moviesTable.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        moviesTable.getColumnModel().getColumn(0).setResizable(true);
        moviesTable.getColumnModel().getColumn(1).setResizable(true);
        moviesTable.getColumnModel().getColumn(2).setResizable(true);
        moviesTable.getColumnModel().getColumn(3).setResizable(true);
        moviesTable.getColumnModel().getColumn(4).setResizable(true);
        moviesTable.getColumnModel().setColumnSelectionAllowed(false);
        moviesTable.setSelectionMode(0);

        viewByBox.setModel(new DefaultComboBoxModel(new String[] { "All", "Genre", "Rating", "Year", "Type" }));
        viewByOptionBox.setVisible(false);

        logButton.setFont(new Font("Ubuntu", 3, 18));
        logButton.setText("Login");
        
        loginLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        
        addMovieButton.setText("Add New Movie");
        addMovieButton.setEnabled(false);
        addMovieButton.setVisible(false);
        
        manageCustomerButton.setText("Manage Users");
        manageCustomerButton.setEnabled(false);
        manageCustomerButton.setVisible(false);

        searchLabel.setText("Search By Title:");

        searchButton.setText("Search");

        noticeLabel.setFont(new Font("Ubuntu", 1, 14));
        noticeLabel.setVisible(false);
        
        showDetailsLabel.setFont(new Font("Ubuntu", 1, 14));
        showDetailsLabel.setText("Double-Click on a movie to view its details");
        showDetailsLabel.setVisible(false);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(tableScrollPane)
                    .addComponent(mTableLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(showDetailsLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(loginLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(logButton, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(viewByLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(viewByBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(viewByOptionBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(searchLabel)
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(searchField, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(addMovieButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(manageCustomerButton, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(noticeLabel)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(mTableLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(showDetailsLabel)
                    .addComponent(loginLabel)
                    .addComponent(logButton))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tableScrollPane, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addMovieButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(manageCustomerButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(viewByLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(viewByBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(viewByOptionBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(searchLabel)
                            .addComponent(searchField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchButton)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(noticeLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
        );
    }
    
    public void addViewByBoxItemStateChanged(ItemListener viewBy) {
        viewByBox.addItemListener(viewBy);
    }
    
    public void addViewByOptionBoxItemStateChanged(ItemListener viewByOption) {
        viewByOptionBox.addItemListener(viewByOption);
    }
    
    public void addLogListener(ActionListener log) {
        logButton.addActionListener(log);
    }
    
    public void addSubmitSearchListener(ActionListener search) {
        searchButton.addActionListener(search);
    }
    
    public void addNewMovieListener(ActionListener addMovie) {
        addMovieButton.addActionListener(addMovie);
    }
    
    public void addManageCustomerListener(ActionListener manageCustomer) {
        manageCustomerButton.addActionListener(manageCustomer);
    }
    
    public void addSearchFieldFocusGained(FocusListener searchFocus) {
        searchField.addFocusListener(searchFocus);
    }
    
    public void addMouseListener(MouseListener tableDoubleClick){
    	moviesTable.addMouseListener(tableDoubleClick);
    }
    
    public void userLoggedIn(){
    	logButton.setText("Logout");
    	loginLabel.setText("Welcome " + Main.current_user.getName());
    	loginLabel.setVisible(true);
    }
    
    public void userLoggedOut() {
		showDetailsLabel.setVisible(false);
		loginLabel.setVisible(false);
		addMovieButton.setEnabled(false);
		addMovieButton.setVisible(false);
		manageCustomerButton.setEnabled(false);
		manageCustomerButton.setVisible(false);
		logButton.setText("Login");
	}
    
    public void showAddMovieButton(){
    	addMovieButton.setEnabled(true);
		addMovieButton.setVisible(true);
    }
    
    public void showDetailsLabel(){
    	showDetailsLabel.setVisible(true);
    }
    
    public void showManageCustomerButton(){
    	manageCustomerButton.setEnabled(true);
    	manageCustomerButton.setVisible(true);
    }
    
    public void addOne(ArrayList<Object> oneProduct) {
		row[0] = oneProduct.get(0);
        row[1] = oneProduct.get(1);
        row[2] = oneProduct.get(2);
        row[3] = oneProduct.get(3);
        row[4] = oneProduct.get(4);
        model.addRow(row);
	}
        
    public void showAll(ArrayList<Object> allProducts) {
    	int tableSize = model.getRowCount();
    	
    	for (int i = 0; i < tableSize; i++) {
    		model.removeRow(tableSize-i-1);
    	}
    	
    	for (int i = 0; i < allProducts.size(); i += 5) {
    		row[0] = allProducts.get(i);
    		row[1] = allProducts.get(i+1);
    		row[2] = allProducts.get(i+2);
    		row[3] = allProducts.get(i+3);
    		row[4] = allProducts.get(i+4);
    		model.addRow(row);
    	}
    }
    
    
	
	//Populate ComboBoxes' Values
	public void populateGenres() {
		String [] genres = {"Action", "Adventure", "Animation", "Biography",
							"Comedy", "Crime", "Documentary", "Drama",
							"Family", "Fantasy", "Film-Noir", "Game-Show",
							"History", "Horror", "Music", "Musical", "Mystery",
							"News", "Reality-TV", "Romance", "Sci-Fi", "Sport",
							"Talk-Show", "Thriller", "War", "Western"};
		viewByOptionBox.setModel(new DefaultComboBoxModel(genres));
		viewByOptionBox.setVisible(true);
	}
	
	public void populateRatings() {
		String [] ratings = {"UR (Unrated)", "G", "PG", "PG-13", "R", "NC-17"};
		viewByOptionBox.setModel(new DefaultComboBoxModel(ratings));
		viewByOptionBox.setVisible(true);
	}
	
	public void populateYears() {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		String [] years = new String[year-1949];
		
		for (int i = 0; i < years.length; i++) {
			years[i] = Integer.toString(year-i);
		}
		viewByOptionBox.setModel(new DefaultComboBoxModel(years));
		viewByOptionBox.setVisible(true);
	}
	
	public void populateTypes() {
		String [] types = {"DVD", "BlueRay"};
		viewByOptionBox.setModel(new DefaultComboBoxModel(types));
		viewByOptionBox.setVisible(true);
	}

	public void updateRow(int row, ArrayList<Object> product) {
		moviesTable.setValueAt(product.get(0), row, 0);
		moviesTable.setValueAt(product.get(1), row, 1);
		moviesTable.setValueAt(product.get(2), row, 2);
		moviesTable.setValueAt(product.get(3), row, 3);
		moviesTable.setValueAt(product.get(4), row, 4);	
	}
	
	private JButton addMovieButton;
	private JButton searchButton;
	private JButton logButton;
	private JButton manageCustomerButton;
	private JLabel mTableLabel;
	private JTable moviesTable;
	private JTextField searchField;
	private JLabel searchLabel;
	private JLabel showDetailsLabel;
	private JLabel noticeLabel;
	private JScrollPane tableScrollPane;
	private JComboBox viewByBox;
	private JLabel viewByLabel;
	private JComboBox viewByOptionBox;
	private JLabel loginLabel;
	  
	private String[] columns = {"Title", "Genre", "Rating", "Release Year", "Type"};
	  
	private Object[] row = new Object[5];
	    
	private DefaultTableModel model = new DefaultTableModel(columns, 0);
	// End of variables declaration//GEN-END:variables
}