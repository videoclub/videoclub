package view;

import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.*;

import dao.ProductDao;
import dao.UserDao;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;



@SuppressWarnings({ "unchecked", "rawtypes" })
public class ManageUserView extends JDialog {
	
    private JLabel headerLabel;
    private JButton resetButton;
    private JButton submitButton;
    private JTextField NameField;
    private JLabel titleLabel;
    private JTextField EmailField;
    private JTextField PhoneField;
    private JLabel lblPhone;
    private JLabel UserNamelbl;
    private JTextField UserNameField;
    private JLabel Passwordlbl;
    private JTextField PasswordField;
    // End of variables declaration//GEN-END:variables
    
    /**
     * Creates new form ManageCustomerView
     */
    public ManageUserView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    //Methods to return the text of the fields 
    public JTextField getNameField() {
		return NameField;
	}


	public JTextField getEmailField() {
		return EmailField;
	}

	public JTextField getPhoneField() {
		return PhoneField;
	}

	public JTextField getUserNameField() {
		return UserNameField;
	}

	public JTextField getPasswordField() {
		return PasswordField;
	}
	

	public void addSubmitButtonListener(ActionListener submit) {
        submitButton.addActionListener(submit);
    }
    
    public void addResetButtonListener(ActionListener reset) {
        resetButton.addActionListener(reset);
    }
    
    public void reset() {
    	NameField.setText("");
    	EmailField.setText("");
    	PhoneField.setText("");
    	UserNameField.setText("");
    	PasswordField.setText("");
     }

    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        NameField = new JTextField();
        titleLabel = new JLabel();
        headerLabel = new JLabel();
        resetButton = new JButton();
        submitButton = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Manage Customer");

        titleLabel.setText("Name");

        headerLabel.setFont(new java.awt.Font("Ubuntu", 3, 18)); // NOI18N
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setText("Add New Customer");

        resetButton.setText("Reset");

        submitButton.setText("Add Customer");
        
        JLabel lblEmail = new JLabel("Email");
        
        EmailField = new JTextField();
        EmailField.setColumns(10);
        
        PhoneField = new JTextField();
        PhoneField.setColumns(10);
        
        lblPhone = new JLabel("Phone");
        
        UserNamelbl = new JLabel("UserName");
        
        UserNameField = new JTextField();
        UserNameField.setColumns(10);
        
        Passwordlbl = new JLabel("Password");
        
        PasswordField = new JTextField();
        PasswordField.setColumns(10);
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addContainerGap(84, Short.MAX_VALUE)
        			.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(headerLabel, GroupLayout.PREFERRED_SIZE, 490, GroupLayout.PREFERRED_SIZE)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addComponent(submitButton, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
        					.addGap(53)
        					.addComponent(resetButton, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
        					.addGap(190))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
        						.addGroup(groupLayout.createSequentialGroup()
        							.addComponent(lblPhone)
        							.addGap(16)
        							.addComponent(PhoneField, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
        						.addGroup(groupLayout.createSequentialGroup()
        							.addComponent(lblEmail)
        							.addGap(22)
        							.addComponent(EmailField, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
        						.addGroup(groupLayout.createSequentialGroup()
        							.addComponent(titleLabel)
        							.addGap(19)
        							.addComponent(NameField, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)))
        					.addGap(18)
        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
        							.addComponent(UserNamelbl)
        							.addGap(10)
        							.addComponent(UserNameField, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE))
        						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
        							.addComponent(Passwordlbl)
        							.addGap(10)
        							.addComponent(PasswordField, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE))))))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(11)
        			.addComponent(headerLabel)
        			.addGap(29)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(3)
        					.addComponent(titleLabel))
        				.addComponent(NameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(3)
        					.addComponent(UserNamelbl))
        				.addComponent(UserNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(PasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(3)
        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        						.addGroup(groupLayout.createSequentialGroup()
        							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        								.addComponent(lblEmail)
        								.addComponent(EmailField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        							.addGap(18)
        							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        								.addGroup(groupLayout.createSequentialGroup()
        									.addGap(3)
        									.addComponent(lblPhone))
        								.addComponent(PhoneField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        						.addComponent(Passwordlbl))))
        			.addGap(78)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(submitButton)
        				.addComponent(resetButton))
        			.addGap(36))
        );
        getContentPane().setLayout(groupLayout);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    
   
    
    /*
     * Se periptwsi eisagogis kainouriou xristi ektos apo customer
     * 
    private String[] populateTypes() {
		String [] types = {"Employee", "Customer"};
		return types;
	}
	*/
     
   
}