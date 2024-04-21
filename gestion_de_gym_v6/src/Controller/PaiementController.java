package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import Model.PaiementModel;
import View.paimentView;
import net.proteanit.sql.DbUtils;

public class PaiementController {
	public PaiementModel paiementmodel;
	public paimentView paiementview;

	public PaiementController(PaiementModel model, paimentView view) {
		this.paiementmodel = model;
		this.paiementview = view;
		paiementview.addAddButtonListener(new AddButtonListener());
		paiementview.addUpdateButtonListener(new UpdateButtonListener());
		paiementview.addDeleteButtonListener(new DeleteButtonListener());
		paiementview.addExitButtonListener(new ExitButtonListener());
		paiementview.search_tf.addKeyListener(new SearchListener());
	}

	public static void table_load() throws SQLException {
		ResultSet idClientsResultSet = PaiementModel.getClients();
		Vector<String> idClientsVector = new Vector<>();
		while (idClientsResultSet.next()) {
			idClientsVector.add(idClientsResultSet.getInt("id") + "-" + idClientsResultSet.getString("nom") + "-"
					+ idClientsResultSet.getString("prenom"));
		}

		paimentView.id_clients.setModel(new DefaultComboBoxModel<>(idClientsVector));

		// Load the paiement table data into the JTable
		ResultSet resultat = PaiementModel.getAllPaiements();
		paimentView.table.setModel(DbUtils.resultSetToTableModel(resultat));
	}

	public class AddButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Date datep = paiementview.dateChooser.getDate();
			String moispaye = paiementview.MoisPaye_tf.getText();
			String montant = paiementview.MontantPaye_tf.getText();
			String selectedItem = (String) paiementview.id_clients.getSelectedItem();
			String[] parts = selectedItem.split("-");
			int id_client = Integer.parseInt(parts[0]);
			try {
				paiementmodel.createPaiement(datep, moispaye, montant, id_client);
				table_load();
				JOptionPane.showMessageDialog(null, "added succefully");
			} catch (SQLException e1) {
				e1.printStackTrace();

			}
		}
	}

	private class UpdateButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int id = paiementview.getSearchId();
			System.out.println(id);

			Date datep = paiementview.dateChooser.getDate();
			String moispaye = paiementview.MoisPaye_tf.getText();
			String montant = paiementview.MontantPaye_tf.getText();
			String selectedItem = (String) paiementview.id_clients.getSelectedItem();
			String[] parts = selectedItem.split(" - ");
			int id_client = Integer.parseInt(parts[0]);

			try {
				paiementmodel.updatePaiement(id, datep, moispaye, montant, id_client);
				table_load();

				JOptionPane.showMessageDialog(null, "updated succefully");

			} catch (SQLException e1) {
				e1.printStackTrace();

			}
		}
	}

	private class DeleteButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int id = paiementview.getSearchId();

			try {
				paiementmodel.deletePaiement(id);
				table_load();

				JOptionPane.showMessageDialog(null, "deleted succefully");

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
				ResultSet rs = paiementmodel.getPaiement(paiementview.getSearchId());
				if (rs.next() == true) {
					String id = rs.getString(1);
					Date datep = rs.getDate(2);
					String MoisPaye = rs.getString(3);
					String MontantPaye = rs.getString(4);
					int id_client = rs.getInt(5);
					paiementview.Id_tf.setText(id);
					paiementview.MoisPaye_tf.setText(MoisPaye);
					paiementview.dateChooser.setDate(datep);
					paiementview.MontantPaye_tf.setText(MontantPaye);
					paiementview.id_clients.setSelectedItem(id);

				} else {
					paiementview.clearInputs();
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
