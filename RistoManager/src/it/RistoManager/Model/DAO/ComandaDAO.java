package it.RistoManager.Model.DAO;

import java.sql.Connection;
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

import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;

import it.RistoManager.Model.Enity.ClienteBean;
import it.RistoManager.Model.Enity.ComandaBean;
import it.RistoManager.Model.Enity.ComandaItemBean;
import it.RistoManager.Model.Enity.RiepilogoBean;

/**
 * 
 * Modella le interazioni con le comande con il database
 *
 */
public class ComandaDAO {

	private static final String COMANDA = "comanda";
	private static final String COMPOSIZIONE = "composizione";
	private ClienteDAO cDao = new ClienteDAO();
	private ProdottoDAO pDao = new ProdottoDAO();

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
	 * Salva una comanda sul database
	 * 
	 * @param c Comanda da salvare
	 * @return Comanda salvata
	 * @throws SQLException
	 */
	public ComandaBean create(ComandaBean c) throws SQLException {

		RiepilogoBean r = null;
		ClienteBean cliente = null;

		int result = 0;

		String createComanda = "INSERT INTO " + COMANDA + " (totale,cliente,completata) VALUES (?,?,?);";
		String insertProdotto = "INSERT INTO " + COMPOSIZIONE + " (comanda,prodotto, quantita) VALUES (?,?,?);";

		try (Connection conn = DriverManagerConnectionPool.getConnection();
				PreparedStatement query = conn.prepareStatement(createComanda, Statement.RETURN_GENERATED_KEYS);
				PreparedStatement query2 = conn.prepareStatement(insertProdotto, Statement.RETURN_GENERATED_KEYS);) {

			cliente = cDao.retrieveById(c.getCliente().getId());

			if (c != null) {

				query.setFloat(1, c.getTotale());
				query.setInt(2, cliente.getId());
				query.setBoolean(3, c.getCompletata());

				result = query.executeUpdate();

				ResultSet idSet = query.getGeneratedKeys();

				int id = idSet.next() ? idSet.getInt(1) : -1;

				c.setId(id);

				query.close();
				// 2

				for (ComandaItemBean item : c.getProdotti()) {

					query2.setInt(1, c.getId());
					query2.setInt(2, item.getProdotto().getId());
					query2.setInt(3, item.getQuantita());
					query2.executeUpdate();

				}

			}

		}

		return result == 1 ? c : null;
	}

	/**
	 * Restituisce una comanda dato l'ID
	 * 
	 * @param id ID della comanda
	 * @return Comanda cercata
	 * @throws SQLException
	 */
	public ComandaBean retrieveById(int id) throws SQLException {

		System.out.println("chiamata comandaDao retrieve");

		ComandaBean c = null;

		String selectString = "SELECT * FROM " + COMANDA + " WHERE id=?;";
		String queryString = "SELECT prodotto, quantita " + "FROM " + COMANDA + " JOIN " + COMPOSIZIONE
				+ " on comanda.id=composizione.comanda WHERE comanda.id=?;";

		try (Connection conn = DriverManagerConnectionPool.getConnection();
				PreparedStatement query = conn.prepareStatement(selectString, Statement.RETURN_GENERATED_KEYS);
				PreparedStatement query2 = conn.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);) {

			query.setInt(1, id);

			ResultSet rs = query.executeQuery();

			if (rs.next()) {
				c = new ComandaBean();
				c.setId(id);
				c.setTotale(rs.getFloat("totale"));
				c.setCliente(cDao.retrieveById(rs.getInt("cliente")));
				c.setCompletata(rs.getBoolean("completata"));

				query.close();

				query2.setInt(1, id);

				ResultSet products = query2.executeQuery();

				List<ComandaItemBean> list = new ArrayList<ComandaItemBean>();

				while (products.next()) {
					ComandaItemBean item = new ComandaItemBean();
					item.setProdotto(pDao.retrieveById(products.getInt("prodotto")));
					item.setQuantita(products.getInt("quantita"));
					list.add(item);

				}
				c.setProdotti(list);
			}
		}

		return c;
	}

	/**
	 * Restituisce tutte le comande
	 * 
	 * @return Tutte le comande
	 * @throws SQLException
	 */
	public List<ComandaBean> retrieveAll() throws SQLException {

		System.out.println("entro");

		List<ComandaBean> list = new ArrayList<ComandaBean>();

		String selectString = "SELECT * FROM " + COMANDA + ";";

		try (Connection conn = DriverManagerConnectionPool.getConnection();
				PreparedStatement query = conn.prepareStatement(selectString, Statement.RETURN_GENERATED_KEYS);) {

			ResultSet rs = query.executeQuery();

			while (rs.next()) {
				ComandaBean c = retrieveById(rs.getInt("id"));
				list.add(c);
			}

		}

		return list;

	}

	/**
	 * Restituisce le comande non completate
	 * @return Lista di comande non completate
	 * @throws SQLException
	 */
	public List<ComandaBean> retrieveNonCompletate() throws SQLException {

		System.out.println("entro");

		List<ComandaBean> list = new ArrayList<ComandaBean>();

		String selectString = "SELECT * FROM " + COMANDA + " WHERE completata = 0;";

		try (Connection conn = DriverManagerConnectionPool.getConnection();
				PreparedStatement query = conn.prepareStatement(selectString, Statement.RETURN_GENERATED_KEYS);) {

			ResultSet rs = query.executeQuery();

			while (rs.next()) {
				ComandaBean c = retrieveById(rs.getInt("id"));
				list.add(c);
			}

		}

		return list;

	}

	/**
	 * Restituisce una lista di comande dato il codice tavolo del cliente
	 * @param codice Codice Tavolo del cliente
	 * @return Lista di comande
	 * @throws SQLException
	 */
	public List<ComandaBean> retrieveByCodiceTavolo(String codice) throws SQLException {

		List<ComandaBean> list = new ArrayList<ComandaBean>();

		String selectString = "SELECT comanda.id " + "FROM " + COMANDA + " "
				+ "JOIN  cliente on comanda.cliente=cliente.id where codicetavolo=?";

		try (Connection conn = DriverManagerConnectionPool.getConnection();
				PreparedStatement query = conn.prepareStatement(selectString, Statement.RETURN_GENERATED_KEYS);) {

			query.setString(1, codice);

			ResultSet rs = query.executeQuery();

			while (rs.next()) {
				ComandaBean c = retrieveById(rs.getInt("id"));
				list.add(c);
			}

		}

		return list;

	}

	// aggiorna solo gli attributi base di comanda, essenzialmente dovrebbe soltanto
	// aggiornare lo stato di completata
	/**
	 * Aggiorna una comanda del Database
	 * @param id ID della comanda da aggiornare
	 * @param c Comanda da aggiornare
	 * @return Comanda aggiornata
	 * @throws SQLException
	 */
	public ComandaBean update(int id, ComandaBean c) throws SQLException {

		int result = 0;

		String queryString = "UPDATE " + COMANDA + " SET totale=?, cliente=?, completata=? WHERE id=?;";

		try (Connection conn = DriverManagerConnectionPool.getConnection();
				PreparedStatement query = conn.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);) {

			if (retrieveById(id) != null) {

				query.setFloat(1, c.getTotale());
				query.setInt(2, c.getCliente().getId());
				query.setBoolean(3, c.getCompletata());
				query.setInt(4, id);

				result = query.executeUpdate();
			}

		}

		return result == 1 ? c : null;

	}

	/**
	 * Rimuove una comanda dal Database
	 * 
	 * @param id ID della comanda da rimuovere
	 * @return 1: se la rimozione è andata a buon fine <br>
	 *         0: se la comadna non è stata rimossa
	 * @throws SQLException
	 */
	public int delete(int id) throws SQLException {

		int result = 0;

		String queryString = "DELETE FROM " + COMANDA + " WHERE id=?;";

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
