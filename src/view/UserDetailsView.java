package view;

import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author larry
 */
public class UserDetailsView extends JDialog {

	public UserDetailsView(Frame parent, boolean modal, ArrayList<Object> product) {
        super(parent, modal);
        this.user = user;
        initComponents();
        location = parent.getLocation();
        this.setLocation(location.x + 140, location.y + 140);
        this.setResizable(false);
    }
	  
	public JButton getEditCustoerButton() {
    	return editCustomerButton;
    }
	
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleLabel = new javax.swing.JLabel();
        detailsLabel = new javax.swing.JLabel();
        editCustomerButton = new JButton();
        
        //Koumpi Edit Customer me Listener stin antistoixi methodo tou Controller
        

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Customer Details");
        
        titleLabel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        
       //Ta pedia gemizoun me ta stoixeia tou ekastote customer.H seira einai onoma, diefthinsi, email, tilefono, TK
        
        titleLabel.setText("Customer Details");

        detailsLabel.setFont(new Font("Dialog", Font.ITALIC, 15)); // NOI18N
        
        detailsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        detailsLabel.setText("View/Edit Details for: "+ user.get(0).toString());
      
        addressHeader = new JLabel();
        addressHeader.setText("Address");
        
        emailHeader = new JLabel();
        emailHeader.setText("Email");
        
        phoneHeader = new JLabel();
        phoneHeader.setText("Phone");
        
        zipcodeHeader = new JLabel();
        zipcodeHeader.setText("Zip Code");
      
        editCustomerButton.setText("Save Changes to Customer");
        
        JLabel nameHeader = new JLabel("Name");
        
        nameField = new JTextField();
        nameField.setColumns(10);
        nameField.setText(user.get(0).toString());
        
        addressField = new JTextField();
        addressField.setColumns(10);
        addressField.setText(user.get(1).toString());
        
        emailField = new JTextField();
        emailField.setColumns(10);
        emailField.setText(user.get(2).toString());
        
        phoneField = new JTextField();
        phoneField.setColumns(10);
        phoneField.setText(user.get(3).toString());
        
        zipcodeField = new JTextField();
        zipcodeField.setColumns(10);
        zipcodeField.setText(user.get(4).toString());
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(10)
        			.addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 536, GroupLayout.PREFERRED_SIZE))
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(54)
        			.addComponent(detailsLabel, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE))
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(10)
        			.addComponent(nameHeader)
        			.addGap(32)
        			.addComponent(nameField, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE))
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(10)
        			.addComponent(addressHeader, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
        			.addGap(10)
        			.addComponent(addressField, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE))
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(10)
        			.addComponent(emailHeader, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
        			.addGap(10)
        			.addComponent(emailField, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE))
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(10)
        			.addComponent(phoneHeader, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
        			.addGap(10)
        			.addComponent(phoneField, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE))
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(10)
        			.addComponent(zipcodeHeader, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
        			.addGap(10)
        			.addComponent(zipcodeField, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
        			.addGap(50)
        			.addComponent(editCustomerButton))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(11)
        			.addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
        			.addGap(6)
        			.addComponent(detailsLabel)
        			.addGap(18)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(3)
        					.addComponent(nameHeader))
        				.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(6)
        					.addComponent(addressHeader))
        				.addComponent(addressField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(3)
        					.addComponent(emailHeader))
        				.addComponent(emailField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(3)
        					.addComponent(phoneHeader))
        				.addComponent(phoneField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(3)
        					.addComponent(zipcodeHeader))
        				.addComponent(zipcodeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(editCustomerButton, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
        			.addGap(26))
        );
        getContentPane().setLayout(groupLayout);

        pack();
    }
    

	// Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton editCustomerButton;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel detailsLabel;
	private ArrayList<Object> user;
	private JLabel addressHeader;
	private JLabel emailHeader;
	private JLabel phoneHeader;
	private JLabel zipcodeHeader;
	private JTextField nameField;
	private JTextField addressField;
	private JTextField emailField;
	private JTextField phoneField;
	private JTextField zipcodeField;
	private Point location;
	// End of variables declaration//GEN-END:variables
	
}
