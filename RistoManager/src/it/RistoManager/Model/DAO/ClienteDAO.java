package it.RistoManager.Model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import it.RistoManager.Model.Enity.ClienteBean;

/**
 * 
 * Modella le interazioni del cliente con il database
 *
 */
public class ClienteDAO {

	public static final String TABLE = "cliente";

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
	 * Salav un cliente nel Database
	 * @param c CLiente da salvare
	 * @return Cliente salvato
	 * @throws SQLException
	 */
	public ClienteBean create(ClienteBean c) throws SQLException {

		String queryString = "INSERT INTO " + TABLE
				+ " (email, nome, cognome, cellulare, numerodocumento, data, ora,numeroposti, codicetavolo) VALUES (?,?,?,?,?,?,?,?,?);";

		int result = 0;
		int id = -1;

		try (Connection conn = DriverManagerConnectionPool.getConnection();
				PreparedStatement query = conn.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);) {

			query.setString(1, c.getEmail());
			query.setString(2, c.getNome());
			query.setString(3, c.getCognome());
			query.setString(4, c.getCellulare());
			query.setString(5, c.getNumeroDocumento());
			query.setDate(6, Date.valueOf(c.getData()));
			query.setTime(7, Time.valueOf(c.getOra()));
			query.setInt(8, c.getNumeroPosti());
			query.setString(9, c.getCodiceTavolo());

			result = query.executeUpdate();

			ResultSet rs = query.getGeneratedKeys();

			if (rs.next())
				id = rs.getInt(1);

			c.setId(id);

		}

		return result == 1 ? c : null;

	}

	/**
	 * Restituisce un cliente dato l'ID
	 * @param id ID del Cliente
	 * @return Cliente cercato
	 * @throws SQLException
	 */
	public ClienteBean retrieveById(int id) throws SQLException {

		ClienteBean c = null;

		String queryString = "SELECT * FROM " + TABLE + " WHERE id=?;";

		try (Connection conn = DriverManagerConnectionPool.getConnection();
				PreparedStatement query = conn.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);) {

			query.setInt(1, id);

			ResultSet rs = query.executeQuery();

			if (rs.next()) {

				c = new ClienteBean();

				c.setId(id);
				c.setNome(rs.getString("nome"));
				c.setCognome(rs.getString("cognome"));
				c.setEmail(rs.getString("email"));
				c.setCellulare(rs.getString("cellulare"));
				c.setData(rs.getDate("data").toLocalDate());
				c.setOra(rs.getTime("ora").toLocalTime());
				c.setNumeroDocumento(rs.getString("numerodocumento"));
				c.setNumeroPosti(rs.getInt("numeroposti"));
				c.setCodiceTavolo(rs.getString("codicetavolo"));
			}

		}

		return c;

	}

	/**
	 * Restituisce un cliente data l'e-mail arrivato in data odierna
	 * @param email Email del cliente
	 * @return La lista di clienti
	 * @throws SQLException
	 */
	public List<ClienteBean> retrieveByEmail(String email) throws SQLException {

		List<ClienteBean> clientiList = new ArrayList<ClienteBean>();

		System.out.println("entro");

		String queryString = "SELECT * FROM " + TABLE + " WHERE email LIKE ? AND data=?;";

		try (Connection conn = DriverManagerConnectionPool.getConnection();
				PreparedStatement query = conn.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);) {

			query.setString(1, email);
			query.setDate(2, Date.valueOf(LocalDate.now()));
			System.out.println(query.getFetchSize());
			System.out.println(query);
			ResultSet rs = query.executeQuery();

			while (rs.next()) {

				System.out.println("entro");
				ClienteBean c = new ClienteBean();

				c.setId(rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				c.setCognome(rs.getString("cognome"));
				c.setEmail(rs.getString("email"));
				c.setCellulare(rs.getString("cellulare"));
				c.setData(rs.getDate("data").toLocalDate());
				c.setOra(rs.getTime("ora").toLocalTime());
				c.setNumeroDocumento(rs.getString("numerodocumento"));
				c.setNumeroPosti(rs.getInt("numeroposti"));
				c.setCodiceTavolo(rs.getString("codicetavolo"));

				clientiList.add(c);
			}

		}
		return clientiList;

	}

	/**
	 * Restituisce un account dato il Codice Tavolo
	 * @param codice Codice Tavolo
	 * @return Cliente cercato
	 * @throws SQLException
	 */
	public ClienteBean retrieveByCodice(String codice) throws SQLException {

		ClienteBean c = null;

		String queryString = "SELECT * FROM " + TABLE + " WHERE codicetavolo=?;";

		try (Connection conn = DriverManagerConnectionPool.getConnection();
				PreparedStatement query = conn.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);) {

			query.setString(1, codice);

			ResultSet rs = query.executeQuery();

			if (rs.next()) {

				System.out.println(codice + " sono dentro prendo i dati");

				c = new ClienteBean();

				c.setId(rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				c.setCognome(rs.getString("cognome"));
				c.setEmail(rs.getString("email"));
				c.setCellulare(rs.getString("cellulare"));
				c.setData(rs.getDate("data").toLocalDate());
				c.setOra(rs.getTime("ora").toLocalTime());
				c.setNumeroDocumento(rs.getString("numerodocumento"));
				c.setNumeroPosti(rs.getInt("numeroposti"));
				c.setCodiceTavolo(rs.getString("codicetavolo"));
			}

		}

		return c;

	}

	// lista di client con nome e cognome nella data in cui si effettua
	// l'invocazione del metodo
	/**
	 * Restituisce i clienti dati Nome e Cognome, arrivati in data odierna
	 * @param nome Nome del cliente
	 * @param cognome Cognome del cliente
	 * @return Lista di clienti
	 * @throws SQLException
	 */
	public List<ClienteBean> retrieveByNome(String nome, String cognome) throws SQLException {

		List<ClienteBean> clientiList = new ArrayList<ClienteBean>();

		String queryString = "SELECT * FROM " + TABLE + " WHERE nome LIKE ? AND cognome LIKE ? AND data=?;";

		try (Connection conn = DriverManagerConnectionPool.getConnection();
				PreparedStatement query = conn.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);) {

			query.setString(1, nome);
			query.setString(2, cognome);
			query.setDate(3, Date.valueOf(LocalDate.now()));

			ResultSet rs = query.executeQuery();

			while (rs.next()) {

				ClienteBean c = new ClienteBean();

				c.setId(rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				c.setCognome(rs.getString("cognome"));
				c.setEmail(rs.getString("email"));
				c.setCellulare(rs.getString("cellulare"));
				c.setData(rs.getDate("data").toLocalDate());
				c.setOra(rs.getTime("ora").toLocalTime());
				c.setNumeroDocumento(rs.getString("numerodocumento"));
				c.setNumeroPosti(rs.getInt("numeroposti"));
				c.setCodiceTavolo(rs.getString("codicetavolo"));

				clientiList.add(c);
			}

		}
		return clientiList;

	}

	// lista di client con cellullare nella data in cui si effettua l'invocazione
	// del metodo
	/**
	 * Restituisce i clienti dato il Numero di cellulare, arrivati in data odierna
	 * @param cellulare Numero di Cellulare del cliente
	 * @return Lista di clienti
	 * @throws SQLException
	 */
	public List<ClienteBean> retrieveByCellulare(String cellulare) throws SQLException {

		List<ClienteBean> clientiList = new ArrayList<ClienteBean>();

		String queryString = "SELECT * FROM " + TABLE + " WHERE cellulare=? AND data=?;";

		 try(Connection conn = DriverManagerConnectionPool.getConnection();
			PreparedStatement query = conn.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);) {			 

			query.setString(1, cellulare);
			query.setDate(2, Date.valueOf(LocalDate.now()));

			ResultSet rs = query.executeQuery();

			while (rs.next()) {

				ClienteBean c = new ClienteBean();

				c.setId(rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				c.setCognome(rs.getString("cognome"));
				c.setEmail(rs.getString("email"));
				c.setCellulare(rs.getString("cellulare"));
				c.setData(rs.getDate("data").toLocalDate());
				c.setOra(rs.getTime("ora").toLocalTime());
				c.setNumeroDocumento(rs.getString("numerodocumento"));
				c.setNumeroPosti(rs.getInt("numeroposti"));
				c.setCodiceTavolo(rs.getString("codicetavolo"));

				clientiList.add(c);
			}

		}  
		return clientiList;

	}

	// lista di clienti nella data passata
	/**
	 * Restituisce i clienti arrivati in una data
	 * @param data Data di arrivo
	 * @return Lista dei clienti
	 * @throws SQLException
	 */
	public List<ClienteBean> retrieveByDate(LocalDate data) throws SQLException {

		List<ClienteBean> clientiList = new ArrayList<ClienteBean>();

		  

		String queryString = "SELECT * FROM " + TABLE + " WHERE data=?;";

		try (Connection conn = DriverManagerConnectionPool.getConnection();
				PreparedStatement query = conn.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);) {

			 

			query.setDate(1, Date.valueOf(data));

			ResultSet rs = query.executeQuery();

			while (rs.next()) {

				ClienteBean c = new ClienteBean();

				c.setId(rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				c.setCognome(rs.getString("cognome"));
				c.setEmail(rs.getString("email"));
				c.setCellulare(rs.getString("cellulare"));
				c.setData(rs.getDate("data").toLocalDate());
				c.setOra(rs.getTime("ora").toLocalTime());
				c.setNumeroDocumento(rs.getString("numerodocumento"));
				c.setNumeroPosti(rs.getInt("numeroposti"));
				c.setCodiceTavolo(rs.getString("codicetavolo"));

				clientiList.add(c);
			}

		} 
		return clientiList;

	}

	/**
	 * Restituisce restituisce i clienti arrivati in un range di date
	 * @param start Data di inizio
	 * @param end Data di fine
	 * @return Lista di clienti
	 * @throws SQLException
	 */
	public List<ClienteBean> retrieveBetweenDates(LocalDate start, LocalDate end) throws SQLException {

		List<ClienteBean> clientiList = new ArrayList<ClienteBean>();

		  

		String queryString = "SELECT * FROM " + TABLE + " WHERE data BETWEEN ? AND ?;";

		try (Connection conn = DriverManagerConnectionPool.getConnection();
				PreparedStatement query = conn.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);) {

			 

			query.setDate(1, Date.valueOf(start));
			query.setDate(2, Date.valueOf(end));

			ResultSet rs = query.executeQuery();

			while (rs.next()) {

				ClienteBean c = new ClienteBean();

				c.setId(rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				c.setCognome(rs.getString("cognome"));
				c.setEmail(rs.getString("email"));
				c.setCellulare(rs.getString("cellulare"));
				c.setData(rs.getDate("data").toLocalDate());
				c.setOra(rs.getTime("ora").toLocalTime());
				c.setNumeroDocumento(rs.getString("numerodocumento"));
				c.setNumeroPosti(rs.getInt("numeroposti"));
				c.setCodiceTavolo(rs.getString("codicetavolo"));

				clientiList.add(c);
			}

		} 
		return clientiList;

	}

	/**
	 * Restituisce tutti i clienti
	 * @return Lista di tutti i clienti
	 * @throws SQLException
	 */
	public List<ClienteBean> retrieveAll() throws SQLException {

		List<ClienteBean> clientiList = new ArrayList<ClienteBean>();

		  

		String queryString = "SELECT * FROM " + TABLE + ";";

		try (Connection conn = DriverManagerConnectionPool.getConnection();
				PreparedStatement query = conn.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);) {

			 

			ResultSet rs = query.executeQuery();

			while (rs.next()) {

				ClienteBean c = new ClienteBean();

				c.setId(rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				c.setCognome(rs.getString("cognome"));
				c.setEmail(rs.getString("email"));
				c.setCellulare(rs.getString("cellulare"));
				c.setData(rs.getDate("data").toLocalDate());
				c.setOra(rs.getTime("ora").toLocalTime());
				c.setNumeroDocumento(rs.getString("numerodocumento"));
				c.setNumeroPosti(rs.getInt("numeroposti"));
				c.setCodiceTavolo(rs.getString("codicetavolo"));

				clientiList.add(c);
			}

		} 
		return clientiList;

	}

	/**
	 * Aggiorna un cliente sul Database
	 * @param id ID del cliente da aggiornare
	 * @param c Cliente da aggiornare
	 * @return Cliente aggiornato
	 * @throws SQLException
	 */
	public ClienteBean update(int id, ClienteBean c) throws SQLException {

		  

		String queryString = "UPDATE " + TABLE
				+ " SET email=?, nome=?, cognome=?, cellulare=?, numerodocumento=?, data=?, ora=?, numeroposti=?, codicetavolo=? WHERE id=?;";

		int result = 0;

		try (Connection conn = DriverManagerConnectionPool.getConnection();
				PreparedStatement query = conn.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);) {

			 

			if (retrieveById(id) != null) {
				query.setString(1, c.getEmail());
				query.setString(2, c.getNome());
				query.setString(3, c.getCognome());
				query.setString(4, c.getCellulare());
				query.setString(5, c.getNumeroDocumento());
				query.setDate(6, Date.valueOf(c.getData()));
				query.setTime(7, Time.valueOf(c.getOra()));
				query.setInt(8, c.getNumeroPosti());
				query.setString(9, c.getCodiceTavolo());
				query.setInt(10, id);

				result = query.executeUpdate();

			}
			c.setId(id);

		} 

		return result == 1 ? c : null;

	}

	/**
	 * Rimuove un cliente dal Database
	 * @param id ID del Cliente da rimmuovere
	 * @return 1: se la rimozione è andata a buon fine <br> 0: se il cliente non è stato rimosso
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
