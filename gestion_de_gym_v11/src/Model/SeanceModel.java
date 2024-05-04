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

	public static ResultSet getCours() throws SQLException {
		connect();
		PreparedStatement pst = con.prepareStatement("SELECT id,label FROM cours");
		return pst.executeQuery();
	}

	public static void deleteSeance(int search) throws SQLException {
		connect();
		PreparedStatement pst = con.prepareStatement("delete from seance where id=?");
		pst.setInt(1, search);
		pst.executeUpdate();
	}

	public static void updateSeance(int search_tf, int id_coach, String horaire, int id_cours) throws SQLException {
		connect();

		PreparedStatement pst = con.prepareStatement("update seance set id_coach=?,horaire=?,id_cours=? where id=?");
		pst.setInt(1, id_coach);
		pst.setString(2, horaire);
		pst.setInt(3, id_cours);
		pst.setInt(4, search_tf);
		pst.executeUpdate();

	}

	public static void createSeance(int id_coach, String horaire, int id_cours) throws SQLException {
		connect();

		PreparedStatement pst = con.prepareStatement("insert into seance (id_coach,horaire,id_cours) values(?,?,?)");
		pst.setInt(1, id_coach);
		pst.setString(2, horaire);
		pst.setInt(3, id_cours);

		pst.executeUpdate();
	}

	public static ResultSet getSeance(int search_tf) throws SQLException {
		connect();
		PreparedStatement pst = con.prepareStatement("select id,id_coach,horaire,id_cours from seance where id=?");
		pst.setInt(1, search_tf);
		return pst.executeQuery();
	}

}