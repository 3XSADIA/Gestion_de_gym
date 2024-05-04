package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Model.CourModel;
import View.courView;

public class CourController {
	public CourModel courmodel;
	public courView courview;

	public CourController(CourModel model, courView view) {
		this.courmodel = model;
		this.courview = view;
		courview.addAddButtonListener(new AddButtonListener());
		courview.addUpdateButtonListener(new UpdateButtonListener());
		courview.addDeleteButtonListener(new DeleteButtonListener());
		courview.addExitButtonListener(new ExitButtonListener());
		courview.search_tf.addKeyListener(new SearchListener());

	}

	public static void table_load() throws SQLException {

		ResultSet equipmentListResultSet = CourModel.getEquipmentList();
		DefaultListModel<String> equipmentListModel = new DefaultListModel<>();
		while (equipmentListResultSet.next()) {
			equipmentListModel.addElement(equipmentListResultSet.getString("nom"));
		}
		courView.equipmentList.setModel(equipmentListModel);

		// Load the seance table data into the JTable
		ResultSet resultat = CourModel.getAllCours();

		Vector<Vector<Object>> coursVector = new Vector<>();
		// Parcourir les résultats des paiements
		while (resultat.next()) {
			// Créer un vecteur pour stocker les détails du paiement avec le nom du client
			Vector<Object> row = new Vector<>();
			// Ajouter les détails du paiement dans le vecteur
			row.add(resultat.getInt("id"));
			row.add(resultat.getString("label"));
			row.add(resultat.getString("equipements"));

			// Ajouter ce vecteur à paiementsVector
			coursVector.add(row);
		}
		Vector<String> columnNames = new Vector<>();
		columnNames.add("ID");
		columnNames.add("Label");
		columnNames.add("Equipements");

		DefaultTableModel model = new DefaultTableModel(coursVector, columnNames);
		courView.table.setModel(model);

	}

	public class AddButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String label = courview.Label_tf.getText();
			Object[] equipementsArray = courview.equipmentList.getSelectedValues();
			String equipements = Arrays.stream(equipementsArray).map(Object::toString)
					.collect(Collectors.joining(", "));
			try {
				courmodel.createCours(label, equipements);
				table_load();
				JOptionPane.showMessageDialog(null, "added successfully");
				courview.clearInputs();

			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	private class UpdateButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String search_tf = courview.getSearchId();

			String Label = courview.Label_tf.getText();
			Object[] equipementsArray = courview.equipmentList.getSelectedValues();
			String equipements = Arrays.stream(equipementsArray).map(Object::toString)
					.collect(Collectors.joining(", "));
			try {
				courmodel.updateCours(search_tf, Label, equipements);
				table_load();

				JOptionPane.showMessageDialog(null, "updated succefully");
				courview.clearInputs();

			} catch (SQLException e1) {
				e1.printStackTrace();

			}

		}
	}

	private class DeleteButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String search = courview.getSearchId();

			try {
				courmodel.deleteCours(search);
				table_load();

				JOptionPane.showMessageDialog(null, "deleted succefully");
				courview.clearInputs();
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

	public class SearchListener implements KeyListener {
		@Override
		public void keyReleased(KeyEvent e) {
			try {
				ResultSet rs = courmodel.getCours(courview.getSearchId());
				if (rs.next() == true) {
					String id = rs.getString(1);
					String label = rs.getString(2);
					String equipements = rs.getString(3);

					courview.Id_tf.setText(id);
					courview.Label_tf.setText(label);

				} else {
					courview.clearInputs();
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