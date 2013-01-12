/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kviews;

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
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;


public class CostumerView extends JFrame {
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private ProductDao pr_model;
	
    private JButton addNewButton;
    private JButton searchButton;
    private JLabel mTableLabel;
    private JScrollPane movieListFrame;
    private JTable customersTable;
    private JTextField searchField;
    private JLabel searchLabel;
    private JLabel noticeLabel;
    private JScrollPane tableScrollPane;
    private JComboBox viewByBox;
    private JLabel viewByLabel;
    
    private String[] columns = {"Title", "Genre", "Rating", "Release Year", "Type"};
    
    private Object[] row = new Object[5];
    
    private DefaultTableModel model = new DefaultTableModel(columns, 0);
    // End of variables declaration//GEN-END:variables
    
    
    /**
     * Creates new form CostumerView
     */
    public CostumerView(CostumerDao model) {
    	setResizable(false);
        initComponents();
    }
    
    // get() methods to give access to other classes
    public JTextField getSearchField(){
    	return searchField;
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
        customersTable = new JTable();
        viewByBox = new JComboBox();
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
        mTableLabel.setText("Customers List");

        viewByLabel.setText("View Customers By:");
        
        
        /*
         * 
         * Allagi se customersTable
         * 
         * */
        customersTable.setModel(model);
        customersTable.setColumnSelectionAllowed(true);
        customersTable.getTableHeader().setReorderingAllowed(false);
        tableScrollPane.setViewportView(customersTable);
        customersTable.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        customersTable.getColumnModel().getColumn(0).setResizable(false);
        customersTable.getColumnModel().getColumn(1).setResizable(false);
        customersTable.getColumnModel().getColumn(2).setResizable(false);
        customersTable.getColumnModel().getColumn(3).setResizable(false);
        customersTable.getColumnModel().getColumn(4).setResizable(false);

        viewByBox.setModel(new DefaultComboBoxModel(new String[] {"First Name", "Last Name", "Postal Code"}));

        addNewButton.setFont(new Font("Ubuntu", 3, 15)); // NOI18N
        addNewButton.setText("Add New Customer");

        searchLabel.setText("Search By Fist Name:");

        searchButton.setText("Search");

        noticeLabel.setForeground(new java.awt.Color(194, 0, 0));
        noticeLabel.setText("There are no results matching your search criteria.");
        noticeLabel.setVisible(false);
        
        /*------------------------------------------LAYOUT-----------------------------------------*/

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(mTableLabel, GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(addNewButton)
        					.addGap(37)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(viewByLabel)
        							.addGap(106)
        							.addComponent(searchLabel))
        						.addComponent(viewByBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(searchButton)
        						.addComponent(searchField, 131, 131, 131)))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(0, 348, Short.MAX_VALUE)
        					.addComponent(noticeLabel))
        				.addComponent(tableScrollPane, GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE))
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(mTableLabel, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(tableScrollPane, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(noticeLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(viewByLabel)
        				.addComponent(addNewButton)
        				.addComponent(searchLabel)
        				.addComponent(searchField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(viewByBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(searchButton))
        			.addContainerGap(33, Short.MAX_VALUE))
        );
        getContentPane().setLayout(layout);

        pack();
    }// </editor-fold>
    
    /*--------------------------------------------------------END OF LAYOUT-------------------------------------------------------------*/
    
    public void viewByBoxItemStateChanged(ItemListener viewBy) {
        viewByBox.addItemListener(viewBy);
    }
    
    public void submitSearchListener(ActionListener search) {
        searchButton.addActionListener(search);
    }
    
    public void addNewCustomerListener(ActionListener addCustomer) {
        addNewButton.addActionListener(addCustomer);
    }
    
    public void searchFieldFocusGained(FocusListener searchFocus) {
        searchField.addFocusListener(searchFocus);
    }
    
    
    /*
     * 
     * Methods: Must Be Adjusted for Costumers
     * 
     * 
     * */
    public void showOne(ArrayList<Object> oneCostumer) {
		row[0] = oneCostumer.get(0);
		//etc...
        model.addRow(row);
        //moviesTable = new JTable(model);
	}
        
    public void showAll(ArrayList<Object> allCostumers) {
    	int i = model.getRowCount()*5;
    	
    	while (i < allCostumers.size()) {
    		row[0] = allCostumers.get(i);
    		//etc....
    		model.addRow(row);
    		i += 5;
    	}
    	//moviesTable = new JTable(model);
    }
    
    public void showPart(ArrayList<Object> costumers) {
    	int tableSize = model.getRowCount();
    	for (int i = 0; i < tableSize; i++) {
    		model.removeRow(tableSize-i-1);
    	}
    	
    	int i = 0;
    	
    	while (i < costumers.size()) {
    		row[0] = costumers.get(i);
    		row[1] = costumers.get(i+1);
    		row[2] = costumers.get(i+2);
    		row[3] = costumers.get(i+3);
    		row[4] = costumers.get(i+4);
    		model.addRow(row);
    		i += 5;
    	}
    	
    	
    	//moviesTable = new JTable(model);
    }
	
	/*
	 * 
	 * Display notice if search doesn't return something
	 * Doesn't work properly
	 * 
	 * 
	 */
    
	public void setNotice() {
		noticeLabel.setVisible(true);
	}
	
	public void unsetNotice() {
		noticeLabel.setVisible(true);
	}
	
}