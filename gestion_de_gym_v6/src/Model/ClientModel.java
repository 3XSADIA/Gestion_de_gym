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

	public static ResultSet getAllClients() throws SQLException {
		connect();
		PreparedStatement pst = con.prepareStatement("SELECT * FROM client");
		return pst.executeQuery();
	}

	public static void deleteClient(int id) throws SQLException {
		connect();
		PreparedStatement pst = con.prepareStatement("delete from client where id=?");
		pst.setLong(1, id);
		pst.executeUpdate();
	}

	public static void updateClient(int id, String nom, String prenom, String tele, Date dI) throws SQLException {
		connect();

		PreparedStatement pst = con
				.prepareStatement("update client set nom=?,prenom=?,NumTelephone=?,dateInscription=?  where id=?");
		pst.setString(1, nom);
		pst.setString(2, prenom);
		pst.setString(3, tele);
		pst.setTimestamp(4, new Timestamp(dI.getTime()));
		pst.setLong(5, id);

		pst.executeUpdate();

	}

	public static void createClient(String nom, String prenom, String tele, Date dateinscription) throws SQLException {
		connect();

		PreparedStatement pst = con
				.prepareStatement("insert into client (nom,prenom,NumTelephone,dateInscription) values(?,?,?,?)");
		pst.setString(1, nom);
		pst.setString(2, prenom);
		pst.setString(3, tele);
		pst.setTimestamp(4, new Timestamp(dateinscription.getTime()));

		pst.executeUpdate();
	}

	public static ResultSet getClient(int id) throws SQLException {
		connect();
		PreparedStatement pst = con
				.prepareStatement("select id,nom,prenom,NumTelephone,dateInscription from client where id=?");
		pst.setLong(1, id);
		return pst.executeQuery();
	}

}
