/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author larry
 */
@SuppressWarnings("serial")
public class OrderView extends JFrame {
	
    public OrderView(Frame parent, boolean modal) {
    	initComponents();
        this.setSize(1000, 550);
        this.setResizable(false);
        location = parent.getLocation();
        this.setLocation(location.x, location.y + 200);
    }

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        buttonsGroup = new ButtonGroup();
        oTableLabel = new JLabel();
        tableScrollPane = new JScrollPane();
        ordersTable = new JTable();
        delayedOrdersButton = new JButton();
        userHistoryLabel = new JLabel();
        productHistoryLabel = new JLabel();
        orderNoLabel = new JLabel();
        viewAllButton = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Video Club");
        setResizable(false);

        oTableLabel.setFont(new java.awt.Font("Ubuntu", 3, 18)); // NOI18N
        oTableLabel.setHorizontalAlignment(SwingConstants.CENTER);
        oTableLabel.setText("Orders List");

        ordersTable = new JTable(){
         	 public boolean isCellEditable(int row, int column){  
         		    return false;  
         		  } 
        };
       
        ordersTable.setModel(model);
        ordersTable.setColumnSelectionAllowed(false);
        ordersTable.getTableHeader().setReorderingAllowed(false);
        tableScrollPane.setViewportView(ordersTable);
        ordersTable.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ordersTable.getColumnModel().getColumn(0).setResizable(true);
        ordersTable.getColumnModel().getColumn(0).setResizable(true);
        ordersTable.getColumnModel().getColumn(1).setResizable(true);
        ordersTable.getColumnModel().getColumn(2).setResizable(true);
        ordersTable.getColumnModel().getColumn(3).setResizable(true);
        ordersTable.getColumnModel().getColumn(4).setResizable(true);
        ordersTable.getColumnModel().getColumn(5).setResizable(true);
        ordersTable.setSelectionMode(0);
        
        delayedOrdersButton.setFont(new java.awt.Font("Ubuntu", 3, 15)); // NOI18N
        delayedOrdersButton.setText("View Delayed Orders");
        
        viewAllButton.setFont(new java.awt.Font("Ubuntu", 3, 15)); // NOI18N
        viewAllButton.setText("View All Orders");
        viewAllButton.setEnabled(false);
        
        userHistoryLabel.setText("Double-Click on a User to view his/her rental history");

        productHistoryLabel.setText("Double-Click on a Product to view its rental history");

        orderNoLabel.setText("Double-Click on an Order No to view details and return this product");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tableScrollPane)
                    .addComponent(oTableLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(productHistoryLabel)
                            .addComponent(orderNoLabel)
                            .addComponent(userHistoryLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(delayedOrdersButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(viewAllButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(oTableLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(orderNoLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(productHistoryLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(userHistoryLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(delayedOrdersButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(viewAllButton)))
                .addContainerGap(72, Short.MAX_VALUE))
        );
    }
    
    public void addDelayedOrdersListener(ActionListener expired) {
    	delayedOrdersButton.addActionListener(expired);
    }
    
    public void addViewAllOrdersListener(ActionListener all) {
    	viewAllButton.addActionListener(all);
    }
    
    public void addOrdersTableListener(MouseListener tableDoubleClick){
    	ordersTable.addMouseListener(tableDoubleClick);
    }
    
    public void enableViewAllButton() {
    	viewAllButton.setEnabled(true);
    }
    
    public void disableViewAllButton() {
    	viewAllButton.setEnabled(false);
    }
    
    public void showAll(ArrayList<Object> allOrders) {
    	int tableSize = model.getRowCount();
    	
    	for (int i = 0; i < tableSize; i++) {
    		model.removeRow(tableSize-i-1);
    	}
    	
    	for (int i = 0; i < allOrders.size(); i += 6) {
    		String returned = "No";
    		row[0] = allOrders.get(i);
    		row[1] = allOrders.get(i+1);
    		row[2] = allOrders.get(i+2);
    		row[3] = allOrders.get(i+3);
    		row[4] = allOrders.get(i+4);
    		
    		if ((boolean) allOrders.get(i+5))
    			returned = "Yes";
    		
    		row[5] = returned;
    		model.addRow(row);
    	}
    }
    
    public void updateReturned(int row) {
    	ordersTable.setValueAt("Yes", row, 5);
	}
    
    // Variables declaration - do not modify
    @SuppressWarnings("unused")
	private ButtonGroup buttonsGroup;
    private JButton delayedOrdersButton;
    private JLabel oTableLabel;
    private JLabel orderNoLabel;
    private JTable ordersTable;
    private JLabel productHistoryLabel;
    private JScrollPane tableScrollPane;
    private JLabel userHistoryLabel;
    private JButton viewAllButton;
    
    private Point location;
    
    private String[] columns = {"Order No", "Product", "Customer", "Rented At", "Expected to return", "Returned"};
	private Object[] row = new Object[6];	    
	private DefaultTableModel model = new DefaultTableModel(columns, 0);
    // End of variables declaration
}