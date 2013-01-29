package controller.impl;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import view.ManageProductView;
import view.ProductView;
import controller.ManageProductController;
import dao.ProductDao;

public class ManageProductControllerImpl extends ProductControllerImpl implements ManageProductController{

	private int row;

	// Controller Constructor for Add Movie Action
	public ManageProductControllerImpl(ProductDao model, ManageProductView m_view, ProductView view) {
        pr_dao = model;
        manage_pr_view  = m_view;
        pr_view  = view;
        
        //... Add listeners to the view.
        m_view.addSubmitButtonListener(new SubmitListener());
        m_view.addResetButtonListener(new ResetListener());
        //m_view.addEditButtonListener(new EditListener());
	}
	
	// Controller Constructor for Edit Movie Action
	public ManageProductControllerImpl(ProductDao model, ManageProductView m_view, ProductView view, int row) {
        pr_dao = model;
        manage_pr_view  = m_view;
        pr_view  = view;
        this.row = row;
        
        //... Add listeners to the view.
        m_view.addEditButtonListener(new EditListener());
	}
	
	//Submit the newly added product to the DB and call method to immediately show this product to the Jtable
	class SubmitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	set();
        	populateProduct();
        	updateNotice();
        }

		private void populateProduct() {
			String title = manage_pr_view.getTitleField().getText();
			String type = manage_pr_view.getTypeBox().getSelectedItem().toString();
			ArrayList<Object> movie = getOne(title, type);
			pr_view.addOne(movie);
		}
		
		private void updateNotice() {
			String title = manage_pr_view.getTitleField().getText();
			pr_view.getNoticeLabel().setText(title + " successfully added!");
        	pr_view.getNoticeLabel().setForeground(Color.green.darker().darker());
        	pr_view.getNoticeLabel().setVisible(true);
		}
	}

	class ResetListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	manage_pr_view.reset();
        }
	}
	
	class EditListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	ArrayList<Object> product = new ArrayList<Object>();
        	product.add(manage_pr_view.getTitleField().getText());
        	product.add(manage_pr_view.getGenreBox().getSelectedItem().toString());
        	product.add(manage_pr_view.getRatingBox().getSelectedItem().toString());
        	product.add(manage_pr_view.getYearBox().getSelectedItem().toString());
        	product.add(manage_pr_view.getTypeBox().getSelectedItem().toString());
        	product.add(manage_pr_view.getDescription().getText());
        	update(product);
        	pr_view.updateRow(row, product);
        	updateNotice(product.get(0).toString());
        }

		private void updateNotice(String title) {
        	pr_view.getNoticeLabel().setText(title + " successfully updated!");
        	pr_view.getNoticeLabel().setForeground(Color.green.darker().darker());
        	pr_view.getNoticeLabel().setVisible(true);
			
		}
	}
}
