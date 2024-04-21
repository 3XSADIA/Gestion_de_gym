package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatistiquesModel {
	private static Connection con;

	public StatistiquesModel() {
		connect();
	}

	private static void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/gym_management", "root", "");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
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

}
