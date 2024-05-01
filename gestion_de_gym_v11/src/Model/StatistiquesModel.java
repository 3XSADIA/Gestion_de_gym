package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StatistiquesModel {
	public static Connection con;
	private String month;
	private double client;
	private double profit;

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public double getclient() {
		return client;
	}

	public void setclient(double amount) {
		this.client = amount;
	}

	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

	public StatistiquesModel() {
		connect();
	}

	public StatistiquesModel(String month, double clients, double profit) {
		connect();
		this.month = month;
		this.client = clients;
		this.profit = profit;
	}

	private static void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/gym_management", "root", "");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Equipment> retrieveData() throws SQLException {
		List<Equipment> equipmentList = new ArrayList<>();

		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT nom, quantite FROM equipement");
		while (resultSet.next()) {
			String name = resultSet.getString("nom");
			int quantity = resultSet.getInt("quantite");
			Equipment equipment = new Equipment(name, quantity);
			equipmentList.add(equipment);
		}
		return equipmentList;
	}

	public List<YearGains> getGainsByYear() throws SQLException {
		List<YearGains> yearGainsList = new ArrayList<>();

		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery(
				"SELECT YEAR(date_paiement) as year, SUM(montantpaye) as gains FROM paiement GROUP BY YEAR(date_paiement)");
		while (resultSet.next()) {
			int year = resultSet.getInt("year");
			double gains = resultSet.getDouble("gains");
			YearGains yearGains = new YearGains(year, gains);
			yearGainsList.add(yearGains);
		}
		return yearGainsList;
	}

	public List<YearClients> getClientsByYear() throws SQLException {
		List<YearClients> yearClientsList = new ArrayList<>();

		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery(
				"SELECT YEAR(dateInscription) as year, COUNT(*) as total_clients FROM client GROUP BY YEAR(dateInscription)");
		while (resultSet.next()) {
			int year = resultSet.getInt("year");
			int totalClients = resultSet.getInt("total_clients");
			YearClients yearClients = new YearClients(year, totalClients);
			yearClientsList.add(yearClients);
		}
		return yearClientsList;
	}

	public static int getEquipement() throws SQLException {
		connect();
		PreparedStatement pst = con.prepareStatement("SELECT count(*) FROM equipement");
		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		} else {
			throw new SQLException("Aucun équipement trouvé");
		}
	}

	public int getCoach() throws SQLException {
		connect();
		PreparedStatement pst = con.prepareStatement("SELECT count(*) FROM coach");
		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		} else {
			throw new SQLException("Aucun coach trouvé");
		}
	}

	public static int getClient() throws SQLException {
		connect();
		PreparedStatement pst = con.prepareStatement("SELECT count(*) FROM client");
		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		} else {
			throw new SQLException("Aucun client");
		}
	}

	public static int getSeance() throws SQLException {
		connect();
		PreparedStatement pst = con.prepareStatement("SELECT count(*) FROM seance");
		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		} else {
			throw new SQLException("Aucun seance");
		}
	}

	public static int getPaiement() throws SQLException {
		connect();
		PreparedStatement pst = con.prepareStatement("SELECT count(*) FROM paiement");
		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		} else {
			throw new SQLException("Aucun paiement");
		}
	}

	public static int getGains() throws SQLException {
		connect();
		PreparedStatement pst = con.prepareStatement("SELECT sum(montantpaye) FROM paiement");
		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		} else {
			throw new SQLException("Aucun paiement");
		}
	}

	public class Equipment {
		private String name;
		private int quantity;

		public Equipment(String name, int quantity) {
			this.name = name;
			this.quantity = quantity;
		}

		public String getName() {
			return name;
		}

		public int getQuantity() {
			return quantity;
		}
	}

	public class YearGains {
		private int year;
		private double gains;

		public YearGains(int year, double gains) {
			this.year = year;
			this.gains = gains;
		}

		public int getYear() {
			return year;
		}

		public double getGains() {
			return gains;
		}
	}

	public class YearClients {
		private int year;
		private int totalClients;

		public YearClients(int year, int totalClients) {
			this.year = year;
			this.totalClients = totalClients;
		}

		public int getYear() {
			return year;
		}

		public int getTotalClients() {
			return totalClients;
		}
	}
}
