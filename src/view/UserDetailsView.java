package view;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 *
 * @author larry
 */
@SuppressWarnings("serial")
public class UserDetailsView extends JDialog {
    
	public UserDetailsView(Frame parent, boolean modal, ArrayList<Object> user) {
        super(parent, modal);
        this.user = user;
        initComponents();
        location = parent.getLocation();
        this.setLocation(location.x + 280, location.y + 180);
        this.setSize(300, 180);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
    }

    public JLabel getProfileAndIdLabel() {
    	return idLabel;
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        idLabel = new JLabel();
        nameHeader = new JLabel();
        nameLabel = new JLabel();
        emailHeader = new JLabel();
        emailLabel = new JLabel();
        phoneHeader = new JLabel();
        phoneLabel = new JLabel();
        editButton = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Movie Details");

        idLabel.setFont(new Font("Ubuntu", 3, 24)); // NOI18N
        idLabel.setHorizontalAlignment(SwingConstants.CENTER);
        String profileAndId = user.get(0) + " #" + user.get(1);
        idLabel.setText(profileAndId);

        nameHeader.setFont(new Font("Ubuntu", 1, 14)); // NOI18N
        nameHeader.setText("Name:");
        
        nameLabel.setText(user.get(2).toString());

        emailHeader.setFont(new Font("Ubuntu", 1, 14)); // NOI18N
        emailHeader.setText("Email:");
        
        emailLabel.setText(user.get(3).toString());

        phoneHeader.setFont(new Font("Ubuntu", 1, 14)); // NOI18N
        phoneHeader.setText("Phone:");
        
        phoneLabel.setText(user.get(4).toString());
        
        editButton.setText("Edit User");
        editButton.setEnabled(false);
        editButton.setVisible(false);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(idLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(nameHeader)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(nameLabel))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(emailHeader)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(emailLabel))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(phoneHeader)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(phoneLabel))))
                            .addComponent(editButton, GroupLayout.Alignment.LEADING))
                        .addGap(0, 227, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(idLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(nameHeader)
                    .addComponent(nameLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(emailHeader)
                    .addComponent(emailLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(phoneLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(phoneHeader, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(editButton)
                .addGap(0, 19, Short.MAX_VALUE))
        );
    }
    
    public void addEditMovieListener(ActionListener search) {
        editButton.addActionListener(search);
    }
    
    public void showEditButton() {
    	editButton.setEnabled(true);
    	editButton.setVisible(true);
    }

	// Variables declaration
	private JLabel idLabel;
	private JLabel nameHeader;
    private JLabel nameLabel;
    private JLabel emailHeader;
    private JLabel emailLabel;
    private JLabel phoneHeader;
    private JLabel phoneLabel;
	private ArrayList<Object> user;
	private JButton editButton;
	private Point location;
    // End of variables declaration
	
}
