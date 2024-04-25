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

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Model.CoachModel;
import Model.SeanceModel;
import View.seanceView;

public class SeanceController {
	public SeanceModel seancemodel;
	public seanceView seanceview;

	public SeanceController(SeanceModel model, seanceView view) {
		this.seancemodel = model;
		this.seanceview = view;
		seanceview.addAddButtonListener(new AddButtonListener());
		seanceview.addUpdateButtonListener(new UpdateButtonListener());
		seanceview.addDeleteButtonListener(new DeleteButtonListener());
		seanceview.addExitButtonListener(new ExitButtonListener());
		seanceview.search_tf.addKeyListener(new SearchListener());

	}

	public static void table_load() throws SQLException {
		ResultSet idCoachResultSet = SeanceModel.getCoachs();
		Vector<String> idCoachVector = new Vector<>();
		while (idCoachResultSet.next()) {
			int id = idCoachResultSet.getInt("id");
			String nom = idCoachResultSet.getString("nom");
			String prenom = idCoachResultSet.getString("prenom");
			String coachName = nom + " " + prenom; // Combine the first and last name
			idCoachVector.add(id + ":" + coachName); // Add the id and name to the vector
		}
		seanceView.coachs.setModel(new DefaultComboBoxModel<>(idCoachVector));

		ResultSet equipmentListResultSet = SeanceModel.getEquipmentList();
		DefaultListModel<String> equipmentListModel = new DefaultListModel<>();
		while (equipmentListResultSet.next()) {
			equipmentListModel.addElement(equipmentListResultSet.getString("nom"));
		}
		seanceView.equipmentList.setModel(equipmentListModel);

		// Load the seance table data into the JTable
		ResultSet resultat = SeanceModel.getAllSeances();

		Vector<Vector<Object>> seancesVector = new Vector<>();
		// Parcourir les résultats des paiements
		while (resultat.next()) {
			// Récupérer l'id du client associé à ce paiement
			int id_coach = resultat.getInt("id_coach");

			// Récupérer le nom du coach correspondant à cet id
			String nomCoach = getNomCoach(id_coach);

			// Créer un vecteur pour stocker les détails du paiement avec le nom du client
			Vector<Object> row = new Vector<>();

			// Ajouter les détails du paiement dans le vecteur
			row.add(resultat.getInt("id"));
			row.add(resultat.getString("label"));
			row.add(nomCoach);
			row.add(resultat.getString("horaire"));
			row.add(resultat.getString("equipements"));

			// Ajouter ce vecteur à paiementsVector
			seancesVector.add(row);
		}
		Vector<String> columnNames = new Vector<>();
		columnNames.add("ID");
		columnNames.add("Label");
		columnNames.add("nom Coach");
		columnNames.add("Horaire");
		columnNames.add("Equipements");
		// Afficher les paiements dans la table
		DefaultTableModel model = new DefaultTableModel(seancesVector, columnNames);
		seanceView.table.setModel(model);

	}

	private static String getNomCoach(int id_coach) throws SQLException {
		// Récupérer le nom du client correspondant à l'id
		ResultSet clientResult = CoachModel.getCoach(id_coach);

		// Vérifier si un client a été trouvé
		if (clientResult.next()) {
			// Récupérer le nom du client
			return clientResult.getString("nom") + " " + clientResult.getString("prenom");
		} else {
			return "Client non trouvé";
		}
	}

	public class AddButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String horaire = seanceview.Horaire_tf.getText();
			String label = seanceview.Label_tf.getText();
			String selectedItem = (String) seanceview.coachs.getSelectedItem();
			String[] parts = selectedItem.split("-");
			int id_coach = Integer.parseInt(parts[0]);
			Object[] equipementsArray = seanceview.equipmentList.getSelectedValues();
			String equipements = Arrays.stream(equipementsArray).map(Object::toString)
					.collect(Collectors.joining(", "));
			try {
				seancemodel.createSeance(label, id_coach, horaire, equipements);
				table_load();
				JOptionPane.showMessageDialog(null, "added successfully");
				seanceview.clearInputs();

			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	private class UpdateButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String search_tf = seanceview.getSearchId();

			String Label = seanceview.Label_tf.getText();
			String Horaire = seanceview.Horaire_tf.getText();
			String selectedItem = (String) seanceview.coachs.getSelectedItem();
			String[] parts = selectedItem.split(":");
			int id_coach = Integer.parseInt(parts[0]);
			String coachName = parts[1]; // Extract the coach name
			Object[] equipementsArray = seanceview.equipmentList.getSelectedValues();
			String equipements = Arrays.stream(equipementsArray).map(Object::toString)
					.collect(Collectors.joining(", "));
			try {
				seancemodel.updateSeance(search_tf, Label, id_coach, Horaire, equipements);
				table_load();

				JOptionPane.showMessageDialog(null, "updated succefully");
				seanceview.clearInputs();

			} catch (SQLException e1) {
				e1.printStackTrace();

			}

		}
	}

	private class DeleteButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String search = seanceview.getSearchId();

			try {
				seancemodel.deleteSeance(search);
				table_load();

				JOptionPane.showMessageDialog(null, "deleted succefully");
				seanceview.clearInputs();
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
				ResultSet rs = seancemodel.getSeance(seanceview.getSearchId());
				if (rs.next() == true) {
					String id = rs.getString(1);
					String label = rs.getString(2);
					int id_coach = rs.getInt(3);
					String horaire = rs.getString(4);
					String equipements = rs.getString(5);

					seanceview.Id_tf.setText(id);
					seanceview.Horaire_tf.setText(horaire);
					seanceview.Label_tf.setText(label);
					seanceview.coachs.setSelectedItem(id_coach);

				} else {
					seanceview.clearInputs();
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