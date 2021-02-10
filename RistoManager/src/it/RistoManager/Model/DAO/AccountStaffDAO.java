package it.RistoManager.Model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.DriverManagerConnectionFactory;

import it.RistoManager.Model.Enity.AccountStaffBean;
import it.RistoManager.Model.Enity.AccountStaffBean.Ruolo;

/**
 * 
 * Modella le interazioni dei membri dello staff con il database
 *
 */
public class AccountStaffDAO {

	public static final String TABLE = "accountstaff";

	static DataSource ds;

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/secretbook");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	/**
	 * Salva un nuovo Account nel Database
	 * @param staff account dal salvare
	 * @return L'aacount salvato
	 * @throws SQLException
	 */
	public AccountStaffBean create(AccountStaffBean staff) throws SQLException {

//		Connection conn=null;
//		PreparedStatement query=null;

		String queryString = "INSERT INTO " + TABLE + " (email, nome, cognome, password, ruolo) VALUES (?,?,?,?,?);";

		int result = 0;
		int id = -1;

		try (Connection conn = DriverManagerConnectionPool.getConnection();
				PreparedStatement query = conn.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);) {
//			conn = ds.getConnection();
//			query = conn.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);

			query.setString(1, staff.getEmail());
			query.setString(2, staff.getNome());
			query.setString(3, staff.getCognome());
			query.setString(4, staff.getPassword());
			query.setString(5, staff.getRuolo().name());

			result = query.executeUpdate();

			ResultSet rs = query.getGeneratedKeys();

			if (rs.next())
				id = rs.getInt(1);

			staff.setId(id);

		}
		return (result == 1 ? staff : null);
	}

	/**
	 * Restituisce un account dato l'ID
	 * @param id ID dell'account
	 * @return Account cercato
	 * @throws SQLException
	 */
	public AccountStaffBean retrieveById(int id) throws SQLException {

		AccountStaffBean staff = null;

		String queryString = "SELECT * FROM " + TABLE + " WHERE id=?;";

		try (Connection conn = DriverManagerConnectionPool.getConnection();
				PreparedStatement query = conn.prepareStatement(queryString);) {
			
			
			query.setInt(1, id);

			ResultSet rs = query.executeQuery();

			if (rs.next()) {

				staff = new AccountStaffBean();
				staff.setId(id);
				staff.setEmail(rs.getString("email"));
				staff.setNome(rs.getString("nome"));
				staff.setCognome(rs.getString("cognome"));
				staff.setPassword(rs.getString("password"));
				staff.setRuolo(Ruolo.valueOf(rs.getString("ruolo")));
			}

		}
		
		return staff;
	}

	/**
	 * Restituisce un account data l'e-mail
	 * @param email E-mail dell'account
	 * @return L'account cercato
	 * @throws SQLException
	 */
	public AccountStaffBean retrieveByEmail(String email) throws SQLException {

		AccountStaffBean staff = null;

		String queryString = "SELECT * FROM " + TABLE + " WHERE email=?;";

		try (Connection conn = DriverManagerConnectionPool.getConnection();
				PreparedStatement query = conn.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);) {

			query.setString(1, email);

			ResultSet rs = query.executeQuery();

			if (rs.next()) {

				staff = new AccountStaffBean();
				staff.setId(rs.getInt("id"));
				staff.setEmail(rs.getString("email"));
				staff.setNome(rs.getString("nome"));
				staff.setCognome(rs.getString("cognome"));
				staff.setPassword(rs.getString("password"));
				staff.setRuolo(Ruolo.valueOf(rs.getString("ruolo")));
			}

		}

		return staff;
	}

	/**
	 * Restituisce tutti gli account salvati
	 * @return Lista di tutti gli account
	 * @throws SQLException
	 */
	public List<AccountStaffBean> retrieveAll() throws SQLException {

		List<AccountStaffBean> staffList = new ArrayList<AccountStaffBean>();

		String queryString = "SELECT * FROM " + TABLE + ";";

		try (Connection conn = DriverManagerConnectionPool.getConnection();
				PreparedStatement query = conn.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);) {

			ResultSet rs = query.executeQuery();

			while (rs.next()) {

				AccountStaffBean staff = new AccountStaffBean();
				staff = new AccountStaffBean();
				staff.setId(rs.getInt("id"));
				staff.setEmail(rs.getString("email"));
				staff.setNome(rs.getString("nome"));
				staff.setCognome(rs.getString("cognome"));
				staff.setPassword(rs.getString("password"));
				staff.setRuolo(Ruolo.valueOf(rs.getString("ruolo")));

				staffList.add(staff);
			}

		}

		return staffList;
	}

	/**
	 * Aggiorna un account nel Database
	 * @param id ID dell'account
	 * @param staff Account da aggiornare
	 * @return L'account aggiornato
	 * @throws SQLException
	 */
	public AccountStaffBean update(int id, AccountStaffBean staff) throws SQLException {
		// TODO Auto-generated method stub

		int result = 0;

		String queryString = "UPDATE " + TABLE + " SET email=?, nome=?, cognome=?, password=?, ruolo=? WHERE id=?;";

		try (Connection conn = DriverManagerConnectionPool.getConnection();
				PreparedStatement query = conn.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);) {

			if (retrieveById(id) != null) {

				query.setString(1, staff.getEmail());
				query.setString(2, staff.getNome());
				query.setString(3, staff.getCognome());
				query.setString(4, staff.getPassword());
				query.setString(5, staff.getRuolo().name());
				query.setInt(6, id);

				result = query.executeUpdate();

			}

			staff.setId(id);

		}

		return (result == 1 ? retrieveById(id) : null);
	}

	/**
	 * Rimuove un account dal Database
	 * @param id ID dell'account da rimuovere
	 * @return 1: se la rimozione è andata a buon fine <br> 0: se l'account non è stato rimosso
	 * @throws SQLException
	 */
	public int delete(int id) throws SQLException {

		int result = 0;

		String queryString = "DELETE FROM " + TABLE + " WHERE id=?;";

		try (Connection conn = DriverManagerConnectionPool.getConnection();
				PreparedStatement query = conn.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);) {

			if (retrieveById(id) != null) {

				query.setInt(1, id);

				result = query.executeUpdate();

			}

		} 

		return result;

	}

}
