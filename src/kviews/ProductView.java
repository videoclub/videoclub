/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EmployeeView;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.ProductDao;
import java.awt.event.ActionEvent;

/**
 *
 * @author larry
 */
public class ProductView extends JFrame {
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private ProductDao pr_model;
	
    private JButton addNewButton;
    private JButton searchButton;
    private JLabel mTableLabel;
    private JScrollPane movieListFrame;
    private JTable moviesTable;
    private JTextField searchField;
    private JLabel searchLabel;
    private JLabel noticeLabel;
    private JScrollPane tableScrollPane;
    private JComboBox viewByBox;
    private JLabel viewByLabel;
    private JComboBox viewByOptionBox;
    
    private String[] columns = {"Title", "Genre", "Rating", "Release Year", "Type"};
    
    private Object[] row = new Object[5];
    
    private DefaultTableModel model = new DefaultTableModel(columns, 0);
    // End of variables declaration//GEN-END:variables
    /**
     * Creates new form ProductView2
     */
    public ProductView(ProductDao model) {
    	setResizable(false);
        initComponents();
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
    
    // End of get() methods

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mTableLabel = new JLabel();
        viewByLabel = new JLabel();
        tableScrollPane = new JScrollPane();
        moviesTable = new JTable();
        viewByBox = new JComboBox();
        viewByOptionBox = new JComboBox();
        addNewButton = new JButton();
        searchLabel = new JLabel();
        searchField = new JTextField();
        searchButton = new JButton();
        searchButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        noticeLabel = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Video Club");

        mTableLabel.setFont(new Font("Ubuntu", 3, 18)); // NOI18N
        mTableLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mTableLabel.setText("Movies List");

        viewByLabel.setText("View Movies By:");
        
        moviesTable.setModel(model);
        moviesTable.setColumnSelectionAllowed(true);
        moviesTable.getTableHeader().setReorderingAllowed(false);
        tableScrollPane.setViewportView(moviesTable);
        moviesTable.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        moviesTable.getColumnModel().getColumn(0).setResizable(false);
        moviesTable.getColumnModel().getColumn(1).setResizable(false);
        moviesTable.getColumnModel().getColumn(2).setResizable(false);
        moviesTable.getColumnModel().getColumn(3).setResizable(false);
        moviesTable.getColumnModel().getColumn(4).setResizable(false);

        viewByBox.setModel(new DefaultComboBoxModel(new String[] { "Genre", "Rating", "Year", "Type" }));

        populateGenres();

        addNewButton.setFont(new Font("Ubuntu", 3, 15)); // NOI18N
        addNewButton.setText("Add New Movie");

        searchLabel.setText("Search By Title:");

        searchButton.setText("Submit");

        noticeLabel.setForeground(new java.awt.Color(194, 0, 0));
        noticeLabel.setText("There are no results matching your search criteria.");
        noticeLabel.setVisible(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mTableLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tableScrollPane, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addNewButton)
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(viewByLabel)
                                .addGap(106, 106, 106)
                                .addComponent(searchLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(viewByBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(viewByOptionBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(searchButton)
                                .addGap(0, 66, Short.MAX_VALUE))
                            .addComponent(searchField)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(noticeLabel)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mTableLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(noticeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewByLabel)
                    .addComponent(addNewButton)
                    .addComponent(searchLabel)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewByBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewByOptionBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>
    
    
    public void viewByBoxItemStateChanged(ItemListener viewBy) {
        viewByBox.addItemListener(viewBy);
    }
    
    public void viewByOptionBoxItemStateChanged(ItemListener viewByOption) {
        viewByOptionBox.addItemListener(viewByOption);
    }
    
    public void submitSearchListener(ActionListener search) {
        searchButton.addActionListener(search);
    }
    
    public void addNewMovieListener(ActionListener addMovie) {
        addNewButton.addActionListener(addMovie);
    }
    
    public void searchFieldFocusGained(FocusListener searchFocus) {
        searchField.addFocusListener(searchFocus);
    }
    
    public void showOne(ArrayList<Object> oneProduct) {
		row[0] = oneProduct.get(0);
        row[1] = oneProduct.get(1);
        row[2] = oneProduct.get(2);
        row[3] = oneProduct.get(3);
        row[4] = oneProduct.get(4);
        model.addRow(row);
        //moviesTable = new JTable(model);
	}
        
    public void showAll(ArrayList<Object> allProducts) {
    	int i = model.getRowCount()*5;
    	
    	while (i < allProducts.size()) {
    		row[0] = allProducts.get(i);
    		row[1] = allProducts.get(i+1);
    		row[2] = allProducts.get(i+2);
    		row[3] = allProducts.get(i+3);
    		row[4] = allProducts.get(i+4);
    		model.addRow(row);
    		i += 5;
    	}
    	//moviesTable = new JTable(model);
    }
    
    public void showPart(ArrayList<Object> products) {
    	int tableSize = model.getRowCount();
    	for (int i = 0; i < tableSize; i++) {
    		model.removeRow(tableSize-i-1);
    	}
    	
    	int i = 0;
    	
    	while (i < products.size()) {
    		row[0] = products.get(i);
    		row[1] = products.get(i+1);
    		row[2] = products.get(i+2);
    		row[3] = products.get(i+3);
    		row[4] = products.get(i+4);
    		model.addRow(row);
    		i += 5;
    	}
    	
    	
    	//moviesTable = new JTable(model);
    }
	
	// Display notice if search doesn't return something
	// Doesn't work properly
	public void setNotice() {
		noticeLabel.setVisible(true);
	}
	
	public void unsetNotice() {
		noticeLabel.setVisible(true);
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
	}
	
	public void populateRatings() {
		String [] ratings = {"UR (Unrated)", "G", "PG", "PG-13", "R", "NC-17"};
		viewByOptionBox.setModel(new DefaultComboBoxModel(ratings));
	}
	
	public void populateYears() {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		String [] years = new String[year-1949];
		
		for (int i = 0; i < years.length; i++) {
			years[i] = Integer.toString(year-i);
		}
		viewByOptionBox.setModel(new DefaultComboBoxModel(years));
	}
	
	public void populateTypes() {
		String [] types = {"DVD", "BlueRay"};
		viewByOptionBox.setModel(new DefaultComboBoxModel(types));
	}
	
}