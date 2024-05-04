package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class ClientModel {
	private static Connection con;

	public ClientModel() {
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

	public static ResultSet getCoursList() throws SQLException {
		connect();
		PreparedStatement pst = con.prepareStatement("SELECT label FROM cours");
		return pst.executeQuery();
	}

	public static ResultSet getAllClients() throws SQLException {
		connect();
		PreparedStatement pst = con.prepareStatement("SELECT * FROM client");
		return pst.executeQuery();
	}

	public static void deleteClient(String search_nom) throws SQLException {
		connect();
		PreparedStatement pst = con.prepareStatement("delete from client where nom=?");
		pst.setString(1, search_nom);
		pst.executeUpdate();
	}

	public static void updateClient(String search_nom, String nom, String prenom, String tele, Date dI, String cours)
			throws SQLException {
		connect();

		PreparedStatement pst = con.prepareStatement(
				"update client set nom=?,prenom=?,NumTelephone=?,dateInscription=?,Cours=?  where nom=?");
		pst.setString(1, nom);
		pst.setString(2, prenom);
		pst.setString(3, tele);
		pst.setTimestamp(4, new Timestamp(dI.getTime()));
		pst.setString(5, cours);
		pst.setString(6, search_nom);

		pst.executeUpdate();

	}

	public static void createClient(String nom, String prenom, String tele, Date dateinscription, String cours)
			throws SQLException {
		connect();

		PreparedStatement pst = con.prepareStatement(
				"insert into client (nom,prenom,NumTelephone,dateInscription,Cours) values(?,?,?,?,?)");
		pst.setString(1, nom);
		pst.setString(2, prenom);
		pst.setString(3, tele);
		pst.setTimestamp(4, new Timestamp(dateinscription.getTime()));
		pst.setString(5, cours);

		pst.executeUpdate();
	}

	public static ResultSet getClient(String search_nom) throws SQLException {
		connect();
		PreparedStatement pst = con
				.prepareStatement("select id,nom,prenom,NumTelephone,dateInscription,Cours from client where nom=?");
		pst.setString(1, search_nom);
		return pst.executeQuery();
	}

	public static ResultSet getClient(int search_id) throws SQLException {
		connect();
		PreparedStatement pst = con
				.prepareStatement("select id,nom,prenom,NumTelephone,dateInscription,Cours from client where id=?");
		pst.setLong(1, search_id);
		return pst.executeQuery();
	}
}
