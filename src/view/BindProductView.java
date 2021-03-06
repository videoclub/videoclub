/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 *
 * @author larry
 */
@SuppressWarnings("serial")
public class BindProductView extends JDialog {
	/**
     * Creates new form bindProductView
     */
    public BindProductView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setSize(400, 300);
        location = parent.getLocation();
        this.setLocation(location.x + 800, location.y + 0);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
    }
    
    public JLabel getTitleLabel() {
    	return titleLabel;
    }
    
    public JLabel getTypeLabel() {
    	return typeLabel;
    }
    
    public JTextField getSearchUserField() {
    	return searchUserField;
    }
    
    public JLabel getNoticeLabel() {
    	return noticeLabel;
    }
    
    public JButton getBindButton() {
    	return bindButton;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        dialogHeader = new JLabel();
        typeLabel = new JLabel();
        typeHeader = new JLabel();
        titleLabel = new JLabel();
        titleHeader = new JLabel();
        searchUserLabel = new JLabel();
        searchUserField = new JTextField();
        noticeLabel = new JLabel();
        bindButton = new JButton();
        searchButton = new JButton();

        this.setTitle("Video Club");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        dialogHeader.setFont(new java.awt.Font("Ubuntu", 3, 18)); // NOI18N
        dialogHeader.setHorizontalAlignment(SwingConstants.CENTER);
        dialogHeader.setText("Bind Movie");

        typeHeader.setText("Type:");

        titleHeader.setText("Title:");

        searchUserLabel.setText("Search for a user to bind this movie");

        searchUserField.setText("user email");
        
        searchButton.setText("Search User");

        noticeLabel.setForeground(Color.red.darker());
        noticeLabel.setText("The is no user with this email address");
        noticeLabel.setVisible(false);

        bindButton.setText("Bind Movie");
        bindButton.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(titleHeader)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(titleLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(typeHeader)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(typeLabel))
                    .addComponent(searchUserLabel)
                    .addComponent(noticeLabel)
                    .addComponent(bindButton)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(searchUserField, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchButton)))
                .addContainerGap(110, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(dialogHeader, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(titleHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(typeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(typeHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(searchUserLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchUserField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(noticeLabel)
                .addGap(18, 18, 18)
                .addComponent(bindButton)
                .addGap(13, 13, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(dialogHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(214, Short.MAX_VALUE)))
        );
    }
    
    public void addBindMovieListener(ActionListener bindMovie) {
        bindButton.addActionListener(bindMovie);
    }
    
    public void addSearchUserListener(ActionListener searchUser) {
        searchButton.addActionListener(searchUser);
    }
    
    public void addSearchFieldFocusGained(FocusListener searchUserFocus) {
        searchUserField.addFocusListener(searchUserFocus);
    }

    // Variables declaration - do not modify
    private JButton bindButton;
    private JLabel noticeLabel;
    private JTextField searchUserField;
    private JLabel searchUserLabel;
    private JLabel dialogHeader;
    private JLabel titleHeader;
    private JLabel titleLabel;
    private JLabel typeHeader;
    private JLabel typeLabel;
    private JButton searchButton;
    
    private Point location;
    // End of variables declaration
}
