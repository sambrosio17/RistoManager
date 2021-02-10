package it.RistoManager.Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import it.RistoManager.Model.Enity.ProdottoBean;

/**
 * 
 * Modella le interazioni con i prodotti del menu con il database
 *
 */
public class ProdottoDAO {

	public static final String TABLE = "prodotto";

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
	 * Salva un prodotto nel Database
	 * 
	 * @param p Prodotto da salvare
	 * @return Prodotto salvato
	 * @throws SQLException
	 */
	public ProdottoBean create(ProdottoBean p) throws SQLException {

		String queryString = "INSERT INTO " + TABLE
				+ " (nomeprodotto, prezzo, descrizione, immagine, ingredienti, categoria) VALUES (?,?,?,?,?,?);";

		int result = 0;
		int id = -1;

		try (Connection conn = DriverManagerConnectionPool.getConnection();
				PreparedStatement query = conn.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);) {

			query.setString(1, p.getNomeprodotto());
			query.setFloat(2, p.getPrezzo());
			query.setString(3, p.getDescrizione());
			query.setString(4, p.getImmagine());
			String ingredienti = "";
			for (String s : p.getIngredienti())
				ingredienti += s + ", ";
			if (ingredienti.length() > 0)
				ingredienti = ingredienti.substring(0, ingredienti.length() - 2);
			else
				ingredienti = "";
			query.setString(5, ingredienti);
			query.setString(6, p.getCategoria());

			result = query.executeUpdate();

			ResultSet rs = query.getGeneratedKeys();

			if (rs.next())
				id = rs.getInt(1);

			p.setId(id);

		}

		return result == 1 ? p : null;

	}

	/**
	 * Restituisce un prodotto dato l'ID
	 * 
	 * @param id ID del prodotto
	 * @return Prodotto cercato
	 * @throws SQLException
	 */
	public ProdottoBean retrieveById(int id) throws SQLException {

		ProdottoBean p = null;

		String queryString = "SELECT * FROM " + TABLE + " WHERE id=?;";

		try (Connection conn = DriverManagerConnectionPool.getConnection();
				PreparedStatement query = conn.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);) {

			query.setInt(1, id);

			ResultSet rs = query.executeQuery();

			if (rs.next()) {

				p = new ProdottoBean();

				p.setId(id);
				p.setNomeprodotto(rs.getString("nomeprodotto"));
				p.setDescrizione(rs.getString("descrizione"));
				p.setPrezzo(rs.getFloat("prezzo"));
				p.setImmagine(rs.getString("immagine"));
				p.setCategoria(rs.getString("categoria"));
				String[] ingredienti = rs.getString("ingredienti").split(",");
				List<String> ingredientiList = new ArrayList<String>(Arrays.asList(ingredienti));
				p.setIngredienti(ingredientiList);

			}
		}

		return p;

	}

	/**
	 * Restituisce un prodotto dato l'ID
	 * 
	 * @param id ID del prodotto
	 * @return Prodotto cercato
	 * @throws SQLException
	 */
	public ProdottoBean retrieveByNome(String nome) throws SQLException {

		ProdottoBean p = null;

		String queryString = "SELECT * FROM " + TABLE + " WHERE nomeprodotto=?;";

		try (Connection conn = DriverManagerConnectionPool.getConnection();
				PreparedStatement query = conn.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);) {

			query.setString(1, nome);
			;

			ResultSet rs = query.executeQuery();

			if (rs.next()) {

				p = new ProdottoBean();

				p.setId(rs.getInt("id"));
				p.setNomeprodotto(rs.getString("nomeprodotto"));
				p.setDescrizione(rs.getString("descrizione"));
				p.setPrezzo(rs.getFloat("prezzo"));
				p.setImmagine(rs.getString("immagine"));
				p.setCategoria(rs.getString("categoria"));
				String[] ingredienti = rs.getString("ingredienti").split(",");
				List<String> ingredientiList = new ArrayList<String>(Arrays.asList(ingredienti));
				p.setIngredienti(ingredientiList);

			}
		}

		return p;

	}

	/**
	 * Restituisce la lista di tutti i prodotti
	 * 
	 * @return Tutti i prodotti
	 * @throws SQLException
	 */
	public List<ProdottoBean> retrieveAll() throws SQLException {

		ProdottoBean p = null;
		List<ProdottoBean> pList = new ArrayList<ProdottoBean>();

		String queryString = "SELECT * FROM " + TABLE + ";";

		try (Connection conn = DriverManagerConnectionPool.getConnection();
				PreparedStatement query = conn.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);) {

			ResultSet rs = query.executeQuery();

			while (rs.next()) {

				p = new ProdottoBean();

				p.setId(rs.getInt("id"));
				p.setNomeprodotto(rs.getString("nomeprodotto"));
				p.setDescrizione(rs.getString("descrizione"));
				p.setPrezzo(rs.getFloat("prezzo"));
				p.setImmagine(rs.getString("immagine"));
				p.setCategoria(rs.getString("categoria"));
				String[] ingredienti = rs.getString("ingredienti").split(",");
				List<String> ingredientiList = new ArrayList<String>(Arrays.asList(ingredienti));
				p.setIngredienti(ingredientiList);
				pList.add(p);

			}

		}

		return pList;

	}

	/**
	 * Restituisce una lista di prodotti per categoria
	 * 
	 * @param category Categoria cercata
	 * @return Lista di prodotti di quella categoria
	 * @throws SQLException
	 */
	public List<ProdottoBean> retrieveByCategory(String category) throws SQLException {

		ProdottoBean p = null;
		List<ProdottoBean> pList = new ArrayList<ProdottoBean>();

		String queryString = "SELECT * FROM " + TABLE + " WHERE categoria=?;";

		try (Connection conn = DriverManagerConnectionPool.getConnection();
				PreparedStatement query = conn.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);) {

			query.setString(1, category);

			ResultSet rs = query.executeQuery();

			while (rs.next()) {

				p = new ProdottoBean();

				p.setId(rs.getInt("id"));
				p.setNomeprodotto(rs.getString("nomeprodotto"));
				p.setDescrizione(rs.getString("descrizione"));
				p.setPrezzo(rs.getFloat("prezzo"));
				p.setImmagine(rs.getString("immagine"));
				p.setCategoria(rs.getString("categoria"));
				String[] ingredienti = rs.getString("ingredienti").split(",");
				List<String> ingredientiList = new ArrayList<String>(Arrays.asList(ingredienti));
				p.setIngredienti(ingredientiList);
				pList.add(p);

			}

		}

		return pList;

	}

	/**
	 * Restituisce una lista di prodotto in un range di prezzo
	 * 
	 * @param min Prezzo minimo
	 * @param max Prezzo massimo
	 * @return Lista di prodotti in quel range di prezzi
	 * @throws SQLException
	 */
	public List<ProdottoBean> retrieveByPrice(int min, int max) throws SQLException {

		ProdottoBean p = null;
		List<ProdottoBean> pList = new ArrayList<ProdottoBean>();

		String queryString = "SELECT * FROM " + TABLE + " WHERE prezzo BETWEEN ? AND ?;";

		try (Connection conn = DriverManagerConnectionPool.getConnection();
				PreparedStatement query = conn.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);) {

			query.setInt(1, min);
			query.setInt(1, max);

			ResultSet rs = query.executeQuery();

			while (rs.next()) {

				p = new ProdottoBean();

				p.setId(rs.getInt("id"));
				p.setNomeprodotto(rs.getString("nomeprodotto"));
				p.setDescrizione(rs.getString("descrizione"));
				p.setPrezzo(rs.getFloat("prezzo"));
				p.setImmagine(rs.getString("immagine"));
				p.setCategoria(rs.getString("categoria"));
				String[] ingredienti = rs.getString("ingredienti").split(",");
				List<String> ingredientiList = new ArrayList<String>(Arrays.asList(ingredienti));
				p.setIngredienti(ingredientiList);
				pList.add(p);

			}

		}

		return pList;

	}

	/**
	 * Restituisce una lista di prodotti contenti un ingrediente
	 * 
	 * @param ingrediente Ingrediente cercato
	 * @return Lista di prodotti
	 * @throws SQLException
	 */
	public List<ProdottoBean> retrieveByIngredient(String ingrediente) throws SQLException {

		ProdottoBean p = null;
		List<ProdottoBean> pList = new ArrayList<ProdottoBean>();

		String queryString = "SELECT * FROM " + TABLE + " WHERE prezzo ?;";

		try (Connection conn = DriverManagerConnectionPool.getConnection();
				PreparedStatement query = conn.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);) {

			query.setString(1, "%" + ingrediente + "%");

			ResultSet rs = query.executeQuery();

			while (rs.next()) {

				p = new ProdottoBean();

				p.setId(rs.getInt("id"));
				p.setNomeprodotto(rs.getString("nomeprodotto"));
				p.setDescrizione(rs.getString("descrizione"));
				p.setPrezzo(rs.getFloat("prezzo"));
				p.setImmagine(rs.getString("immagine"));
				p.setCategoria(rs.getString("categoria"));
				String[] ingredienti = rs.getString("ingredienti").split(",");
				List<String> ingredientiList = new ArrayList<String>(Arrays.asList(ingredienti));
				p.setIngredienti(ingredientiList);
				pList.add(p);

			}

		}
		return pList;

	}

	/**
	 * Aggiorna un prodotto sul Database
	 * 
	 * @param id ID del prodotto da aggiornare
	 * @param p  Prodotto da aggiornare
	 * @return Prodotto aggiornato
	 * @throws SQLException
	 */
	public ProdottoBean update(int id, ProdottoBean p) throws SQLException {

		String queryString = "UPDATE " + TABLE
				+ " SET nomeprodotto=?, prezzo=?, descrizione=?, immagine=?, ingredienti=?, categoria=? WHERE id=?;";

		int result = 0;

		try (Connection conn = DriverManagerConnectionPool.getConnection();
				PreparedStatement query = conn.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);) {

			if (retrieveById(id) != null) {
				query.setString(1, p.getNomeprodotto());
				query.setFloat(2, p.getPrezzo());
				query.setString(3, p.getDescrizione());
				query.setString(4, p.getImmagine());
				String ingredienti = "";
				for (String s : p.getIngredienti())
					ingredienti += s + ", ";
				ingredienti = ingredienti.substring(0, ingredienti.length() - 2);
				query.setString(5, ingredienti);
				query.setString(6, p.getCategoria());
				query.setInt(7, id);

				result = query.executeUpdate();
			}

			p.setId(id);

		}

		return result == 1 ? p : null;

	}

	/**
	 * Rimuove un prodotto dal Database
	 * 
	 * @param id ID del prodotto da rimuovere
	 * @return 1: se la rimozione è andata a buon fine <br>
	 *         0: se il prodotto non è stata rimossa
	 * @throws SQLException
	 */
	public int delete(int id) throws SQLException {

		String queryString = "DELETE FROM " + TABLE + " WHERE id=?;";

		int result = 0;

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
