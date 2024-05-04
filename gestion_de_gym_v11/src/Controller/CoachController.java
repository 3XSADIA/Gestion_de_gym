
package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import Model.ClientModel;
import Model.CoachModel;
import View.coachView;
import net.proteanit.sql.DbUtils;

public class CoachController {
	public CoachModel coachmodel;
	public coachView coachview;

	public CoachController(CoachModel model, coachView view) {
		this.coachmodel = model;
		this.coachview = view;
		coachview.addAddButtonListener(new AddButtonListener());
		coachview.addUpdateButtonListener(new UpdateButtonListener());
		coachview.addDeleteButtonListener(new DeleteButtonListener());
		coachview.addExitButtonListener(new ExitButtonListener());
		coachview.search_tf.addKeyListener(new SearchListener());
	}

	public static void table_load() throws SQLException {
		ResultSet coursListResultSet = ClientModel.getCoursList();
		DefaultListModel<String> coursListModel = new DefaultListModel<>();
		while (coursListResultSet.next()) {
			coursListModel.addElement(coursListResultSet.getString("label"));
		}
		coachView.coursList.setModel(coursListModel);

		ResultSet resultat = CoachModel.getAllCoachs();
		coachView.table.setModel(DbUtils.resultSetToTableModel(resultat));
	}

	public class AddButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String nom = coachview.Nom_tf.getText();
			String prenom = coachview.Prenom_tf.getText();
			String tele = coachview.NumTelephone_tf.getText();
			Object[] coursArray = coachview.coursList.getSelectedValues();
			String cours = Arrays.stream(coursArray).map(Object::toString).collect(Collectors.joining(", "));
			String salaire = coachview.Salaire_tf.getText();

			try {
				coachmodel.createCoach(nom, prenom, tele, cours, salaire);
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
			String search_nom = coachview.getSearchId();
			int id = Integer.parseInt(coachview.Id_tf.getText());
			String nom = coachview.Nom_tf.getText();
			String prenom = coachview.Prenom_tf.getText();
			String tele = coachview.NumTelephone_tf.getText();
			Object[] coursArray = coachview.coursList.getSelectedValues();
			String cours = Arrays.stream(coursArray).map(Object::toString).collect(Collectors.joining(", "));
			String salaire = coachview.Salaire_tf.getText();

			try {
				coachmodel.updateCoach(search_nom, nom, prenom, tele, cours, salaire);
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
			String id = coachview.getSearchId();

			try {
				coachmodel.deleteCoach(id);
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
				ResultSet rs = CoachModel.getCoach(coachview.getSearchId());
				System.out.println(rs);
				if (rs.next() == true) {
					String id = rs.getString(1);
					String nom = rs.getString(2);
					String prenom = rs.getString(3);
					String telephone = rs.getString(4);
					String Cours = rs.getString(5);
					String salaire = rs.getString(6);
					System.out.println(id);
					coachview.Id_tf.setText(id);
					coachview.Nom_tf.setText(nom);
					coachview.Prenom_tf.setText(prenom);
					coachview.NumTelephone_tf.setText(telephone);
					coachview.Salaire_tf.setText(salaire);
				} else {
					coachview.clearInputs();
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
