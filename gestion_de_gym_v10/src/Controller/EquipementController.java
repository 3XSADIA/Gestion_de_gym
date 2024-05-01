package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Model.EquipementModel;
import View.equipementView;
import net.proteanit.sql.DbUtils;

public class EquipementController {
	public EquipementModel equipementmodel;
	public equipementView equipementview;

	public EquipementController(EquipementModel model, equipementView view) {
		this.equipementmodel = model;
		this.equipementview = view;
		equipementview.addAddButtonListener(new AddButtonListener());
		equipementview.addUpdateButtonListener(new UpdateButtonListener());
		equipementview.addDeleteButtonListener(new DeleteButtonListener());
		equipementview.addExitButtonListener(new ExitButtonListener());
		equipementview.search_tf.addKeyListener(new SearchListener());
	}

	public static void table_load() throws SQLException {
		ResultSet resultat = EquipementModel.getAllEquipement();
		equipementView.table.setModel(DbUtils.resultSetToTableModel(resultat));
	}

	public class AddButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String nom = equipementview.Nom_tf.getText();
			int quantite = Integer.parseInt(equipementview.Quantite_tf.getText());

			try {
				equipementmodel.createEquipement(nom, quantite);
				table_load();
				JOptionPane.showMessageDialog(null, "added succefully");
				equipementview.clearInputs();
			} catch (SQLException e1) {
				e1.printStackTrace();

			}
		}
	}

	private class UpdateButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String search_nom = equipementview.getSearchId();

			String nom = equipementview.Nom_tf.getText();
			int quantite = Integer.parseInt(equipementview.Quantite_tf.getText());
			try {
				equipementmodel.updateEquipement(search_nom, nom, quantite);
				table_load();

				JOptionPane.showMessageDialog(null, "updated succefully");
				equipementview.clearInputs();

			} catch (SQLException e1) {
				e1.printStackTrace();

			}
		}
	}

	private class DeleteButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String search_nom = equipementview.getSearchId();

			try {
				equipementmodel.deleteEquipement(search_nom);
				table_load();

				JOptionPane.showMessageDialog(null, "deleted succefully");
				equipementview.clearInputs();

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}
	}

	private class ExitButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	private class SearchListener implements KeyListener {
		@Override
		public void keyReleased(KeyEvent e) {
			try {
				ResultSet rs = equipementmodel.getEquipement(equipementview.getSearchId());
				if (rs.next() == true) {
					String id = rs.getString(1);
					String nom = rs.getString(2);
					int quantite = Integer.parseInt(rs.getString(3));
					equipementview.Id_tf.setText(id);
					equipementview.Nom_tf.setText(nom);
					equipementview.Quantite_tf.setText("" + quantite);
				} else {
					equipementview.clearInputs();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub

		}

	}

}
