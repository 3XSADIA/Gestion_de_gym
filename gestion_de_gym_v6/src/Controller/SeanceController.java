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

import Model.SeanceModel;
import View.seanceView;
import net.proteanit.sql.DbUtils;

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
		seanceView.list_equipements.setModel(equipmentListModel);

		// Load the seance table data into the JTable
		ResultSet resultat = SeanceModel.getAllSeances();
		seanceView.table.setModel(DbUtils.resultSetToTableModel(resultat));
	}

	public class AddButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String horaire = seanceview.Horaire_tf.getText();
			String label = seanceview.Label_tf.getText();
			String selectedItem = (String) seanceview.coachs.getSelectedItem();
			String[] parts = selectedItem.split("-");
			int id_coach = Integer.parseInt(parts[0]);
			Object[] equipementsArray = seanceview.list_equipements.getSelectedValues();
			String equipements = Arrays.stream(equipementsArray).map(Object::toString)
					.collect(Collectors.joining(", "));
			try {
				seancemodel.createSeance(label, id_coach, horaire, equipements);
				table_load();
				JOptionPane.showMessageDialog(null, "added successfully");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	private class UpdateButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int id = seanceview.getSearchId();
			System.out.println(id);

			String Label = seanceview.Label_tf.getText();
			String Horaire = seanceview.Horaire_tf.getText();
			String selectedItem = (String) seanceview.coachs.getSelectedItem();
			String[] parts = selectedItem.split(":");
			int id_coach = Integer.parseInt(parts[0]);
			String coachName = parts[1]; // Extract the coach name
			Object[] equipementsArray = seanceview.list_equipements.getSelectedValues();
			String equipements = Arrays.stream(equipementsArray).map(Object::toString)
					.collect(Collectors.joining(", "));
			try {
				seancemodel.updateSeance(id, Label, id_coach, Horaire, equipements);
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
			int id = seanceview.getSearchId();

			try {
				seancemodel.deleteSeance(id);
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