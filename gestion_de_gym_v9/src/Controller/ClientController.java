package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JOptionPane;

import Model.ClientModel;
import View.clientView;
import net.proteanit.sql.DbUtils;

public class ClientController {
	public ClientModel clientmodel;
	public clientView clientview;

	public ClientController(ClientModel model, clientView view) {
		this.clientmodel = model;
		this.clientview = view;
		clientview.addAddButtonListener(new AddButtonListener());
		clientview.addUpdateButtonListener(new UpdateButtonListener());
		clientview.addDeleteButtonListener(new DeleteButtonListener());
		clientview.addExitButtonListener(new ExitButtonListener());
		clientview.search_tf.addKeyListener(new SearchListener());
	}

	public static void table_load() throws SQLException {
		ResultSet resultat = ClientModel.getAllClients();
		clientView.table.setModel(DbUtils.resultSetToTableModel(resultat));
	}

	public class AddButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String nom = clientview.Nom_tf.getText();
			String prenom = clientview.Prenom_tf.getText();
			String tele = clientview.NumTelephone_tf.getText();
			Date DI = clientview.dateChooser.getDate();

			try {
				ClientModel.createClient(nom, prenom, tele, DI);
				table_load();
				JOptionPane.showMessageDialog(null, "added succefully");
				clientview.clearInputs();
			} catch (SQLException e1) {
				e1.printStackTrace();

			}
		}
	}

	private class UpdateButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String search_nom = clientview.getSearchId();
			Date DI = clientview.dateChooser.getDate();
			String nom = clientview.Nom_tf.getText();
			String prenom = clientview.Prenom_tf.getText();
			String tele = clientview.NumTelephone_tf.getText();

			try {
				ClientModel.updateClient(search_nom, nom, prenom, tele, DI);
				table_load();

				JOptionPane.showMessageDialog(null, "updated succefully");
				clientview.clearInputs();

			} catch (SQLException e1) {
				e1.printStackTrace();

			}
		}
	}

	private class DeleteButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String search_nom = clientview.getSearchId();

			try {
				ClientModel.deleteClient(search_nom);
				table_load();
				JOptionPane.showMessageDialog(null, "deleted succefully");
				clientview.clearInputs();

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
				ResultSet rs = ClientModel.getClient(clientview.getSearchId());
				if (rs.next() == true) {
					String id = rs.getString(1);
					String nom = rs.getString(2);
					String prenom = rs.getString(3);
					String telephone = rs.getString(4);
					Date dateInscription = rs.getDate(5);
					clientview.Id_tf.setText(id);
					clientview.Nom_tf.setText(nom);
					clientview.Prenom_tf.setText(prenom);
					clientview.NumTelephone_tf.setText(telephone);
					clientview.dateChooser.setDate(dateInscription);
				} else {
					clientview.clearInputs();
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
