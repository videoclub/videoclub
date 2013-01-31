/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionListener;

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
public class ManageOrderView extends JDialog {

    public ManageOrderView(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //this.setSize(350, 220);
        location = parent.getLocation();
        this.setLocation(location.x + 350, location.y + 400);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        orderHeader = new JLabel();
        titleHeader = new JLabel();
        titleLabel = new JLabel();
        typeHeader = new JLabel();
        typeLabel = new JLabel();
        userHeader = new JLabel();
        userLabel = new JLabel();
        returnButton = new JButton();

        this.setTitle("Video Club");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        orderHeader.setFont(new Font("Ubuntu", 3, 18)); // NOI18N
        orderHeader.setHorizontalAlignment(SwingConstants.CENTER);

        titleHeader.setFont(new Font("Ubuntu", 1, 15)); // NOI18N
        titleHeader.setText("Title:");

        typeHeader.setFont(new Font("Ubuntu", 1, 15)); // NOI18N
        typeHeader.setText("Type:");

        userHeader.setFont(new Font("Ubuntu", 1, 15)); // NOI18N
        userHeader.setText("Rented to:");

        returnButton.setText("Set Product as returned");
        returnButton.setEnabled(false);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(orderHeader, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(titleHeader)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(titleLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(typeHeader)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(typeLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(userHeader)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(userLabel))
                            .addComponent(returnButton))
                        .addGap(0, 127, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(orderHeader, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(titleHeader)
                    .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(typeHeader)
                    .addComponent(typeLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(userHeader)
                    .addComponent(userLabel, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(returnButton)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
    }
    
    public void addSetReturnedButtonListener(ActionListener returned) {
        returnButton.addActionListener(returned);
    }
    
    public void setOrderHeader(String header) {
    	orderHeader.setText(header);	
    }
    
    public void setTitleLabel(String title) {
    	titleLabel.setText(title);
    }
    
    public void setTypeLabel(String type) {
    	typeLabel.setText(type);
    }

	public void setUserLabel(String user) {
		userLabel.setText(user);
	}
	
	public void enableReturnButton() {
    	returnButton.setEnabled(true);
    }

    // Variables declaration - do not modify
    private JLabel orderHeader;
    private JButton returnButton;
    private JLabel titleHeader;
    private JLabel titleLabel;
    private JLabel typeHeader;
    private JLabel typeLabel;
    private JLabel userHeader;
    private JLabel userLabel;
    
    private Point location;
    // End of variables declaration
}
