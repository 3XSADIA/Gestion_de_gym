package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
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
		// coach
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

		// cours
		ResultSet idCoursResultSet = SeanceModel.getCours();
		Vector<String> idCoursVector = new Vector<>();
		while (idCoursResultSet.next()) {
			int id = idCoursResultSet.getInt("id");
			String label = idCoursResultSet.getString("label");
			idCoursVector.add(id + ":" + label); // Add the id and name to the vector
		}
		seanceView.cours.setModel(new DefaultComboBoxModel<>(idCoursVector));

		// Load the seance table data into the JTable
		ResultSet resultat = SeanceModel.getAllSeances();

		Vector<Vector<Object>> seancesVector = new Vector<>();
		// Parcourir les résultats des paiements
		while (resultat.next()) {
			// Récupérer l'id du client associé à ce paiement
			int id_coach = resultat.getInt("id_coach");
			int id_cours = resultat.getInt("id_cours");

			// Récupérer le nom du coach correspondant à cet id
			String nomCoach = getNomCoach(id_coach);
			String nomCours = getNomCours(id_cours);

			// Créer un vecteur pour stocker les détails du paiement avec le nom du client
			Vector<Object> row = new Vector<>();

			// Ajouter les détails du paiement dans le vecteur
			row.add(resultat.getInt("id"));
			row.add(nomCoach);
			row.add(resultat.getString("horaire"));
			row.add(nomCours);

			// Ajouter ce vecteur à paiementsVector
			seancesVector.add(row);
		}

		Vector<String> columnNames = new Vector<>();
		columnNames.add("ID");
		columnNames.add("nom Coach");
		columnNames.add("Horaire");
		columnNames.add("nom Cours");
		// Afficher les paiements dans la table
		DefaultTableModel model = new DefaultTableModel(seancesVector, columnNames);
		seanceView.table.setModel(model);

	}

	private static String getNomCoach(int id_coach) throws SQLException {
		// Récupérer le nom du client correspondant à l'id
		ResultSet coachResult = CoachModel.getCoach(id_coach);

		// Vérifier si un client a été trouvé
		if (coachResult.next()) {
			// Récupérer le nom du client
			return coachResult.getString("nom") + " " + coachResult.getString("prenom");
		} else {
			return "Coach non trouvé";
		}
	}

	private static String getNomCours(int id_cours) throws SQLException {
		// Récupérer le nom du client correspondant à l'id
		ResultSet coursResult = CoachModel.getCours(id_cours);

		// Vérifier si un client a été trouvé
		if (coursResult.next()) {
			// Récupérer le nom du client
			return coursResult.getString("label");
		} else {
			return "Cours non trouvé";
		}
	}

	public class AddButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String horaire = seanceview.Horaire_tf.getText();
			String selectedItem = (String) seanceview.coachs.getSelectedItem();
			String[] parts = selectedItem.split(":" + "");
			int id_coach = Integer.parseInt(parts[0]);

			String selectedItem2 = (String) seanceview.cours.getSelectedItem();
			String[] parts2 = selectedItem.split("-");
			int id_cours = Integer.parseInt(parts[0]);

			try {
				seancemodel.createSeance(id_coach, horaire, id_cours);
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
			int search_tf = seanceview.getSearchId();

			String Horaire = seanceview.Horaire_tf.getText();

			String selectedItem = (String) seanceview.coachs.getSelectedItem();
			String[] parts = selectedItem.split(":");
			int id_coach = Integer.parseInt(parts[0]);
			String coachName = parts[1]; // Extract the coach name

			String selectedItem2 = (String) seanceview.cours.getSelectedItem();
			String[] parts2 = selectedItem2.split(":");
			int id_cours = Integer.parseInt(parts2[0]);
			String coursName = parts2[1]; // Extract the coach name

			try {
				seancemodel.updateSeance(search_tf, id_coach, Horaire, id_cours);
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
			int search = seanceview.getSearchId();

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
					int id_coach = rs.getInt(2);
					String horaire = rs.getString(3);
					int id_cours = rs.getInt(4);

					seanceview.Id_tf.setText(id);
					seanceview.Horaire_tf.setText(horaire);
					seanceview.coachs.setSelectedItem(id_coach);
					seanceview.cours.setSelectedItem(id_cours);

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