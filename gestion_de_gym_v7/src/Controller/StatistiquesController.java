package Controller;

import java.sql.SQLException;

import Model.StatistiquesModel;

public class StatistiquesController {
	public StatistiquesModel statistiquesmodel;
	public View.home home;

	public StatistiquesController(StatistiquesModel model, View.home view) {
		this.statistiquesmodel = model;
		this.home = view;
	}

	public int getEquip() throws SQLException {
		return statistiquesmodel.getEquipement();
	}

	public int getCoach() throws SQLException {
		return statistiquesmodel.getCoach();
	}

	public int getClient() throws SQLException {
		return statistiquesmodel.getClient();
	}

	public int getSeance() throws SQLException {
		return statistiquesmodel.getSeance();
	}

	public int getPaiement() throws SQLException {
		return statistiquesmodel.getPaiement();
	}

	public int getGains() throws SQLException {
		return statistiquesmodel.getGains();
	}

	public void updateStatistics() throws SQLException {
		int equip = getEquip();
		int coach = getCoach();
		int client = getClient();
		int seance = getSeance();
		int paiement = getPaiement();
		int gains = getGains();

		home.client.setText(String.valueOf(client));
		home.coach.setText(String.valueOf(coach));
		home.equipement.setText(String.valueOf(equip));
		home.seance.setText(String.valueOf(seance));
		home.gains.setText(String.valueOf(gains));
		home.paiement.setText(String.valueOf(paiement));
	}

}
