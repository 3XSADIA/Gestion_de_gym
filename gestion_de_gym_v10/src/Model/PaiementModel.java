package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class PaiementModel {
	private static Connection con;

	public PaiementModel() {
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

	public static ResultSet getAllPaiements() throws SQLException {
		connect();
		PreparedStatement pst = con.prepareStatement("SELECT * FROM paiement");
		return pst.executeQuery();
	}

	public static ResultSet getClients() throws SQLException {
		connect();
		PreparedStatement pst = con.prepareStatement("SELECT id,nom,prenom FROM client");
		return pst.executeQuery();
	}

	public static void deletePaiement(int id) throws SQLException {
		connect();
		PreparedStatement pst = con.prepareStatement("delete from paiement where id=?");
		pst.setInt(1, id);
		pst.executeUpdate();
	}

	public static void updatePaiement(int id, Date date_paiement, String moispaye, String montantpaye, int id_client)
			throws SQLException {
		connect();

		PreparedStatement pst = con.prepareStatement(
				"update paiement set date_paiement=?,moispaye=?,montantpaye=?,id_client=? where id=?");
		pst.setDate(1, new java.sql.Date(date_paiement.getTime()));
		pst.setString(2, moispaye);
		pst.setString(3, montantpaye);
		pst.setInt(4, id_client);
		pst.setInt(5, id);

		pst.executeUpdate();

	}

	public static void createPaiement(Date datep, String moispaye, String montantpaye, int id_client)
			throws SQLException {
		connect();

		PreparedStatement pst = con.prepareStatement(
				"insert into paiement (date_paiement,moispaye,montantpaye,id_client) values(?,?,?,?)");
		pst.setDate(1, new java.sql.Date(datep.getTime()));
		pst.setString(2, moispaye);
		pst.setString(3, montantpaye);
		pst.setInt(4, id_client);

		pst.executeUpdate();
	}

	public static ResultSet getPaiement(int id) throws SQLException {
		connect();
		PreparedStatement pst = con.prepareStatement(
				"select id,date_paiement,moispaye,montantpaye,id_client from paiement where id_client=?");
		pst.setInt(1, id);
		return pst.executeQuery();
	}

	public int getClientIdByName(String clientName) throws SQLException {
		connect();
		String query = "SELECT id FROM client WHERE nom = ?";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, clientName);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("id");
		} else {
			return -1; // Retourne -1 si le client n'est pas trouvé
		}
	}

}