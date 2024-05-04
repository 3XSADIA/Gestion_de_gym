package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CoachModel {

	private static Connection con;

	public CoachModel() {
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

	public static ResultSet getAllCoachs() throws SQLException {
		connect();
		PreparedStatement pst = con.prepareStatement("SELECT * FROM coach");
		return pst.executeQuery();
	}

	public static void deleteCoach(String nom) throws SQLException {
		connect();
		PreparedStatement pst = con.prepareStatement("delete from coach where nom=?");
		pst.setString(1, nom);
		pst.executeUpdate();
	}

	public static void updateCoach(String search_nom, String nom, String prenom, String tele, String Cours,
			String salaire) throws SQLException {
		connect();

		PreparedStatement pst = con
				.prepareStatement("update coach set nom=?,prenom=?,num_telephone=?,Cours=?,salaire=? where nom=?");
		pst.setString(1, nom);
		pst.setString(2, prenom);
		pst.setString(3, tele);
		pst.setString(4, Cours);
		pst.setString(5, salaire);
		pst.setString(6, search_nom);

		pst.executeUpdate();

	}

	public static void createCoach(String nom, String prenom, String tele, String Cours, String salaire)
			throws SQLException {
		connect();

		PreparedStatement pst = con
				.prepareStatement("insert into coach (nom,prenom,num_telephone,Cours,salaire) values(?,?,?,?,?)");
		pst.setString(1, nom);
		pst.setString(2, prenom);
		pst.setString(3, tele);
		pst.setString(4, Cours);
		pst.setString(5, salaire);

		pst.executeUpdate();
	}

	public static ResultSet getCoach(String nom) throws SQLException {
		connect();
		PreparedStatement pst = con
				.prepareStatement("select id,nom,prenom,num_telephone,Cours,salaire from coach where nom=?");
		pst.setString(1, nom);
		return pst.executeQuery();
	}

	public static ResultSet getCoach(int id) throws SQLException {
		connect();
		PreparedStatement pst = con
				.prepareStatement("select id,nom,prenom,num_telephone,Cours,salaire from coach where id=?");
		pst.setInt(1, id);
		return pst.executeQuery();
	}

	public static ResultSet getCours(int id) throws SQLException {
		connect();
		PreparedStatement pst = con.prepareStatement("select id,label from cours where id=?");
		pst.setInt(1, id);
		return pst.executeQuery();
	}
}
