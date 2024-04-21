package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SeanceModel {
	private static Connection con;

	public SeanceModel() {
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

	public static ResultSet getAllSeances() throws SQLException {
		connect();
		PreparedStatement pst = con.prepareStatement("SELECT * FROM seance");
		return pst.executeQuery();
	}

	public static ResultSet getEquipmentList() throws SQLException {
		connect();
		PreparedStatement pst = con.prepareStatement("SELECT nom FROM equipement");
		return pst.executeQuery();
	}

	public static ResultSet getCoachs() throws SQLException {
		connect();
		PreparedStatement pst = con.prepareStatement("SELECT id,nom,prenom FROM coach");
		return pst.executeQuery();
	}

	public static void deleteSeance(String search_tf) throws SQLException {
		connect();
		PreparedStatement pst = con.prepareStatement("delete from seance where label=?");
		pst.setString(1, search_tf);
		pst.executeUpdate();
	}

	public static void updateSeance(String search_tf, String label, int id_coach, String horaire, String equipements)
			throws SQLException {
		connect();

		PreparedStatement pst = con
				.prepareStatement("update seance set label=?,id_coach=?,horaire=?,equipements=? where label=?");
		pst.setString(1, label);
		pst.setInt(2, id_coach);
		pst.setString(3, horaire);
		pst.setString(4, equipements);
		pst.setString(5, search_tf);
		pst.executeUpdate();

	}

	public static void createSeance(String label, int id_coach, String horaire, String equipements)
			throws SQLException {
		connect();

		PreparedStatement pst = con
				.prepareStatement("insert into seance (label,id_coach,horaire,equipements) values(?,?,?,?)");
		pst.setString(1, label);
		pst.setInt(2, id_coach);
		pst.setString(3, horaire);
		pst.setString(4, equipements);

		pst.executeUpdate();
	}

	public static ResultSet getSeance(String search_tf) throws SQLException {
		connect();
		PreparedStatement pst = con
				.prepareStatement("select id,label,id_coach,horaire,equipements from seance where label=?");
		pst.setString(1, search_tf);
		return pst.executeQuery();
	}

}