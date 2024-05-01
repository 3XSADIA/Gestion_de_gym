package Controller;

import java.sql.SQLException;
import java.util.List;

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

	public List<StatistiquesModel.Equipment> getEquipmentData() throws SQLException {
		return statistiquesmodel.retrieveData();
	}

	public List<StatistiquesModel.YearGains> getGainsData() throws SQLException {
		return statistiquesmodel.getGainsByYear();
	}

	public List<StatistiquesModel.YearClients> getClientsData() throws SQLException {
		return statistiquesmodel.getClientsByYear();
	}

	public void updateStatistics() throws SQLException {
		List<StatistiquesModel.Equipment> equipmentData = getEquipmentData();
		home.setData(equipmentData);

		List<StatistiquesModel.YearGains> yearGainsList = statistiquesmodel.getGainsByYear();
		home.setGainsClientsData();

		int equip = getEquip();
		int coach = getCoach();
		int client = getClient();
		int seance = getSeance();
		int paiement = getPaiement();
		int gains = getGains();

		home.clients.setText(String.valueOf(client));
		home.coachs.setText(String.valueOf(coach));
		home.seances.setText(String.valueOf(seance));
		home.gain.setText(String.valueOf(gains));
	}
}