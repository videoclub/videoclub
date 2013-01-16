package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BindProduct extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel bindMovieLabel;
	private JLabel BindMovieHeader;
	private JTextField movieIDField;

	
	public static void main(String[] args) {
		try {
			BindProduct dialog = new BindProduct();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BindProduct() {
		this.setTitle("Bind Movie");
		
		BindButton = new JButton();
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);{
			bindMovieLabel = new JLabel("Search Movie By ID");
			bindMovieLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		{
			BindMovieHeader = new JLabel();
			BindMovieHeader.setText("Bind Movie");
			BindMovieHeader.setHorizontalAlignment(SwingConstants.CENTER);
			BindMovieHeader.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16));
		}
		movieIDField = new JTextField();
		movieIDField.setHorizontalAlignment(SwingConstants.LEFT);
		movieIDField.setColumns(10);
		
		BindButton.setText("Bind");

		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap(177, Short.MAX_VALUE)
					.addComponent(BindMovieHeader)
					.addGap(163))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(33)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(BindButton, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(bindMovieLabel)
							.addGap(26)
							.addComponent(movieIDField, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(62, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(BindMovieHeader)
					.addGap(35)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(bindMovieLabel)
						.addComponent(movieIDField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(42)
					.addComponent(BindButton, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(88, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
	}
	
	public void addSubmitListener(ActionListener submit) {
        BindButton.addActionListener(submit);
    }
	
	// Variables declaration - do not modify
    private JButton BindButton;
    
    // End of variables declaration
}
