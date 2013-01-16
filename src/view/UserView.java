package view;

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


public class UserView extends JFrame {
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private ProductDao pr_model;
	
    private JButton addNewButton;
    private JButton searchButton;
    private JLabel mTableLabel;
    private JScrollPane movieListFrame;
    private JTable usersTable;
    private JTextField searchField;
    private JLabel searchLabel;
    private JLabel noticeLabel;
    private JScrollPane tableScrollPane;
    private JComboBox viewByBox;
    private JLabel viewByLabel;
    
    private String[] columns = {"Id", "UserName", "Name", "Email", "Phone"};
    
    private Object[] row = new Object[5];
    
    private DefaultTableModel model = new DefaultTableModel(columns, 0);
    // End of variables declaration//GEN-END:variables
    
   
    public UserView() {
    	initComponents();
        this.setSize(800, 600);
        this.setResizable(false);
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

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    
    
    
    private void initComponents() {

        mTableLabel = new JLabel();
        viewByLabel = new JLabel();
        tableScrollPane = new JScrollPane();
        usersTable = new JTable();
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
        mTableLabel.setText("Users List");

        viewByLabel.setText("View Users By:");
        
        
        /*
         * 
         * Allagi se usersTable
         * 
         * */
        usersTable.setModel(model);
        usersTable.setColumnSelectionAllowed(true);
        usersTable.getTableHeader().setReorderingAllowed(false);
        tableScrollPane.setViewportView(usersTable);
        usersTable.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        usersTable.getColumnModel().getColumn(0).setResizable(false);
        usersTable.getColumnModel().getColumn(1).setResizable(false);
        usersTable.getColumnModel().getColumn(2).setResizable(false);
        usersTable.getColumnModel().getColumn(3).setResizable(false);
        usersTable.getColumnModel().getColumn(4).setResizable(false);

        viewByBox.setModel(new DefaultComboBoxModel(new String[] {"Name", "UserName", "Email", "Phone"}));

        addNewButton.setFont(new Font("Ubuntu", 3, 15)); // NOI18N
        addNewButton.setText("Add New User");

        searchLabel.setText("Search By Name:");

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
        				.addComponent(mTableLabel, GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(addNewButton)
        					.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
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
        					.addGap(0, 403, Short.MAX_VALUE)
        					.addComponent(noticeLabel))
        				.addComponent(tableScrollPane, GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE))
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(mTableLabel, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(tableScrollPane, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(noticeLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(viewByLabel)
        						.addComponent(searchLabel)
        						.addComponent(searchField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(viewByBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(searchButton))
        					.addContainerGap(33, Short.MAX_VALUE))
        				.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(addNewButton)
        					.addGap(40))))
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
    
    public void addNewUserListener(ActionListener addUser) {
        addNewButton.addActionListener(addUser);
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
        
    public void showAll(ArrayList<Object> allUsers) {
    	int i = model.getRowCount()*5;
    	
    	while (i < allUsers.size()) {
    		row[0] = allUsers.get(i);
    		//etc....
    		model.addRow(row);
    		i += 5;
    	}
    	//moviesTable = new JTable(model);
    }
    
    public void showPart(ArrayList<Object> users) {
    	int tableSize = model.getRowCount();
    	for (int i = 0; i < tableSize; i++) {
    		model.removeRow(tableSize-i-1);
    	}
    	
    	int i = 0;
    	
    	while (i < users.size()) {
    		row[0] = users.get(i);
    		row[1] = users.get(i+1);
    		row[2] = users.get(i+2);
    		row[3] = users.get(i+3);
    		row[4] = users.get(i+4);
    		model.addRow(row);
    		i += 5;
    	}
    	
    	
    	//moviesTable = new JTable(model);
    }
	
    public void addOne(ArrayList<Object> oneUser) {
		row[0] = oneUser.get(0);
        row[1] = oneUser.get(1);
        row[2] = oneUser.get(2);
        row[3] = oneUser.get(3);
        row[4] = oneUser.get(4);
        model.addRow(row);
	}
    
    public void updateRow(int row, ArrayList<Object> product) {
    	usersTable.setValueAt(product.get(0), row, 0);
    	usersTable.setValueAt(product.get(1), row, 1);
    	usersTable.setValueAt(product.get(2), row, 2);
    	usersTable.setValueAt(product.get(3), row, 3);
    	usersTable.setValueAt(product.get(4), row, 4);	
	}
    
	public void setNotice() {
		noticeLabel.setVisible(true);
	}
	
	public void unsetNotice() {
		noticeLabel.setVisible(true);
	}
	
}