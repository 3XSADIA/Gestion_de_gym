package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourModel {
	private static Connection con;

	public CourModel() {
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

	public static ResultSet getAllCours() throws SQLException {
		connect();
		PreparedStatement pst = con.prepareStatement("SELECT * FROM cours");
		return pst.executeQuery();
	}

	public static ResultSet getEquipmentList() throws SQLException {
		connect();
		PreparedStatement pst = con.prepareStatement("SELECT nom FROM equipement");
		return pst.executeQuery();
	}

	public static ResultSet getCours() throws SQLException {
		connect();
		PreparedStatement pst = con.prepareStatement("SELECT id,label,equipements FROM cours");
		return pst.executeQuery();
	}

	public static void deleteCours(String search_tf) throws SQLException {
		connect();
		PreparedStatement pst = con.prepareStatement("delete from cours where label=?");
		pst.setString(1, search_tf);
		pst.executeUpdate();
	}

	public static void updateCours(String search_tf, String label, String equipements) throws SQLException {
		connect();

		PreparedStatement pst = con.prepareStatement("update cours set label=?,equipements=? where label=?");
		pst.setString(1, label);
		pst.setString(2, equipements);
		pst.setString(3, search_tf);
		pst.executeUpdate();

	}

	public static void createCours(String label, String equipements) throws SQLException {
		connect();

		PreparedStatement pst = con.prepareStatement("insert into cours (label,equipements) values(?,?)");
		pst.setString(1, label);
		pst.setString(2, equipements);

		pst.executeUpdate();
	}

	public static ResultSet getCours(String search_tf) throws SQLException {
		connect();
		PreparedStatement pst = con.prepareStatement("select id,label,equipements from cours where label=?");
		pst.setString(1, search_tf);
		return pst.executeQuery();
	}

}