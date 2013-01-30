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
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author larry
 */
public class OrderView extends JFrame {
	
    public OrderView(Frame parent, boolean modal) {
    	initComponents();
        this.setSize(1000, 500);
        this.setResizable(false);
        location = parent.getLocation();
        this.setLocation(location.x, location.y + 200);
    }

    @SuppressWarnings("unchecked")
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
        viewAllLabel = new JLabel();

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
        
        userHistoryLabel.setText("Double-Click on a User to view his/her rental history");

        productHistoryLabel.setText("Double-Click on a Product to view its rental history");

        orderNoLabel.setText("Double-Click on an Order No to view details and return this product");
        
        viewAllLabel.setText("Double-Click on any other cell to go back to the initial view");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(tableScrollPane)
                    .addComponent(oTableLabel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(productHistoryLabel)
                            .addComponent(orderNoLabel))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(delayedOrdersButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(userHistoryLabel)
                            .addComponent(viewAllLabel))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(oTableLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tableScrollPane, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(delayedOrdersButton)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(orderNoLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(productHistoryLabel)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userHistoryLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(viewAllLabel)
                .addContainerGap(52, Short.MAX_VALUE))
        );
    }
    
    public void addDelayedOrdersListener(ActionListener expired) {
    	delayedOrdersButton.addActionListener(expired);
    }
    
    public void addOrdersTableListener(MouseListener tableDoubleClick){
    	ordersTable.addMouseListener(tableDoubleClick);
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
    private ButtonGroup buttonsGroup;
    private JButton delayedOrdersButton;
    private JLabel oTableLabel;
    private JLabel orderNoLabel;
    private JTable ordersTable;
    private JLabel productHistoryLabel;
    private JScrollPane tableScrollPane;
    private JLabel userHistoryLabel;
    private JLabel viewAllLabel;
    
    private Point location;
    
    private String[] columns = {"Order No", "Product", "Customer", "Rented At", "Expected to return", "Returned"};
	private Object[] row = new Object[6];	    
	private DefaultTableModel model = new DefaultTableModel(columns, 0);
    // End of variables declaration
}