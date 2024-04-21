package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EquipementModel {
	private static Connection con;

	public EquipementModel() {
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

	public static ResultSet getAllEquipement() throws SQLException {
		connect();
		PreparedStatement pst = con.prepareStatement("SELECT * FROM equipement");
		return pst.executeQuery();
	}

	public static void deleteEquipement(int id) throws SQLException {
		connect();
		PreparedStatement pst = con.prepareStatement("delete from equipement where id=?");
		pst.setLong(1, id);
		pst.executeUpdate();
	}

	public static void updateEquipement(int id, String nom, int quantite) throws SQLException {
		connect();

		PreparedStatement pst = con.prepareStatement("update equipement set nom=?,quantite=? where id=?");
		pst.setString(1, nom);
		pst.setLong(2, quantite);
		pst.setLong(3, id);
		pst.executeUpdate();

	}

	public static void createEquipement(String nom, int quantite) throws SQLException {
		connect();

		PreparedStatement pst = con.prepareStatement("insert into equipement (nom,quantite) values(?,?)");
		pst.setString(1, nom);
		pst.setLong(2, quantite);

		pst.executeUpdate();
	}

	public static ResultSet getEquipement(int id) throws SQLException {
		connect();
		PreparedStatement pst = con.prepareStatement("select id,nom,quantite from equipement where id=?");
		pst.setLong(1, id);
		return pst.executeQuery();
	}

}
