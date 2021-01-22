package it.RistoManager.DAO;

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


import it.RistoManager.Model.ProdottoBean;

public class ProdottoDAO {


	public static final String TABLE="prodotto";

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


	public ProdottoBean create(ProdottoBean p) throws SQLException {

		Connection conn=null;
		PreparedStatement query=null;

		String queryString="INSERT INTO "+TABLE+" (nomeprodotto, prezzo, descrizione, immagine, ingredienti, categoria) VALUES (?,?,?,?,?,?);";

		int result=0;
		int id=-1;

		try {
			conn = ds.getConnection();
			query = conn.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);

			query.setString(1, p.getNomeprodotto());
			query.setFloat(2,p.getPrezzo());
			query.setString(3, p.getDescrizione());
			query.setString(4, p.getImmagine());
			String ingredienti="";
			for(String s: p.getIngredienti())
				ingredienti+=s+", ";
			ingredienti=ingredienti.substring(0, ingredienti.length()-2);
			query.setString(5, ingredienti);
			query.setString(6, p.getCategoria());


			result=query.executeUpdate();

			ResultSet rs=query.getGeneratedKeys();

			if(rs.next())
				id=rs.getInt(1);

			p.setId(id);

		} finally {
			try {
				if (query != null)
					query.close();
			} finally {
				if(conn!=null)
					conn.close();
			}
		}



		return result==1 ? p : null;


	}

	public ProdottoBean retrieveById(int id) throws SQLException {

		ProdottoBean p=null;

		Connection conn=null;
		PreparedStatement query=null;

		String queryString="SELECT * FROM "+TABLE+" WHERE id=?;";

		try {
			conn = ds.getConnection();
			query = conn.prepareStatement(queryString);

			query.setInt(1, id);

			ResultSet rs=query.executeQuery();

			if(rs.next()) {
				

				p=new ProdottoBean();

				p.setId(id);
				p.setNomeprodotto(rs.getString("nomeprodotto"));
				p.setDescrizione(rs.getString("descrizione"));
				p.setPrezzo(rs.getFloat("prezzo"));
				p.setImmagine(rs.getString("immagine"));
				p.setCategoria(rs.getString("categoria"));
				String[] ingredienti=rs.getString("ingredienti").split(",");
				List<String> ingredientiList=new ArrayList<String>(Arrays.asList(ingredienti));
				p.setIngredienti(ingredientiList);
				

			}

		} finally {
			try {
				if (query != null)
					query.close();
			} finally {
				if(conn!=null)
					conn.close();
			}
		}


		return p;

	}

	public List<ProdottoBean> retrieveAll() throws SQLException {

		ProdottoBean p=null;
		List<ProdottoBean> pList=new ArrayList<ProdottoBean>();

		Connection conn=null;
		PreparedStatement query=null;

		String queryString="SELECT * FROM "+TABLE+";";

		try {
			conn = ds.getConnection();
			query = conn.prepareStatement(queryString);


			ResultSet rs=query.executeQuery();

			while(rs.next()) {

				p=new ProdottoBean();

				p.setId(rs.getInt("id"));
				p.setNomeprodotto(rs.getString("nomeprodotto"));
				p.setDescrizione(rs.getString("descrizione"));
				p.setPrezzo(rs.getFloat("prezzo"));
				p.setImmagine(rs.getString("immagine"));
				p.setCategoria(rs.getString("categoria"));
				String[] ingredienti=rs.getString("ingredienti").split(",");
				List<String> ingredientiList=new ArrayList<String>(Arrays.asList(ingredienti));
				p.setIngredienti(ingredientiList);
				pList.add(p);

			}

		} finally {
			try {
				if (query != null)
					query.close();
			} finally {
				if(conn!=null)
					conn.close();
			}
		}


		return pList;

	}

	
	public List<ProdottoBean> retrieveByCategory(String category) throws SQLException {

		ProdottoBean p=null;
		List<ProdottoBean> pList=new ArrayList<ProdottoBean>();

		Connection conn=null;
		PreparedStatement query=null;

		String queryString="SELECT * FROM "+TABLE+" WHERE categoria=?;";

		try {
			conn = ds.getConnection();
			query = conn.prepareStatement(queryString);
			query.setString(1, category);


			ResultSet rs=query.executeQuery();

			while(rs.next()) {

				p=new ProdottoBean();

				p.setId(rs.getInt("id"));
				p.setNomeprodotto(rs.getString("nomeprodotto"));
				p.setDescrizione(rs.getString("descrizione"));
				p.setPrezzo(rs.getFloat("prezzo"));
				p.setImmagine(rs.getString("immagine"));
				p.setCategoria(rs.getString("categoria"));
				String[] ingredienti=rs.getString("ingredienti").split(",");
				List<String> ingredientiList=new ArrayList<String>(Arrays.asList(ingredienti));
				p.setIngredienti(ingredientiList);
				pList.add(p);

			}

		} finally {
			try {
				if (query != null)
					query.close();
			} finally {
				if(conn!=null)
					conn.close();
			}
		}


		return pList;

	}
	
	
	public List<ProdottoBean> retrieveByPrice(int min, int max) throws SQLException{
		
		ProdottoBean p=null;
		List<ProdottoBean> pList=new ArrayList<ProdottoBean>();

		Connection conn=null;
		PreparedStatement query=null;

		String queryString="SELECT * FROM "+TABLE+" WHERE prezzo BETWEEN ? AND ?;";

		try {
			conn = ds.getConnection();
			query = conn.prepareStatement(queryString);
			query.setInt(1, min);
			query.setInt(1, max);

			ResultSet rs=query.executeQuery();

			while(rs.next()) {

				p=new ProdottoBean();

				p.setId(rs.getInt("id"));
				p.setNomeprodotto(rs.getString("nomeprodotto"));
				p.setDescrizione(rs.getString("descrizione"));
				p.setPrezzo(rs.getFloat("prezzo"));
				p.setImmagine(rs.getString("immagine"));
				p.setCategoria(rs.getString("categoria"));
				String[] ingredienti=rs.getString("ingredienti").split(",");
				List<String> ingredientiList=new ArrayList<String>(Arrays.asList(ingredienti));
				p.setIngredienti(ingredientiList);
				pList.add(p);

			}

		} finally {
			try {
				if (query != null)
					query.close();
			} finally {
				if(conn!=null)
					conn.close();
			}
		}


		return pList;

		
	}
	
	public List<ProdottoBean> retrieveByIngredient(String ingrediente) throws SQLException{
		
		ProdottoBean p=null;
		List<ProdottoBean> pList=new ArrayList<ProdottoBean>();

		Connection conn=null;
		PreparedStatement query=null;

		String queryString="SELECT * FROM "+TABLE+" WHERE prezzo ?;";

		try {
			conn = ds.getConnection();
			query = conn.prepareStatement(queryString);
			query.setString(1, "%"+ingrediente+"%");

			ResultSet rs=query.executeQuery();

			while(rs.next()) {

				p=new ProdottoBean();

				p.setId(rs.getInt("id"));
				p.setNomeprodotto(rs.getString("nomeprodotto"));
				p.setDescrizione(rs.getString("descrizione"));
				p.setPrezzo(rs.getFloat("prezzo"));
				p.setImmagine(rs.getString("immagine"));
				p.setCategoria(rs.getString("categoria"));
				String[] ingredienti=rs.getString("ingredienti").split(",");
				List<String> ingredientiList=new ArrayList<String>(Arrays.asList(ingredienti));
				p.setIngredienti(ingredientiList);
				pList.add(p);

			}

		} finally {
			try {
				if (query != null)
					query.close();
			} finally {
				if(conn!=null)
					conn.close();
			}
		}


		return pList;
		
	}

	public ProdottoBean update(int id, ProdottoBean p) throws SQLException {

		Connection conn=null;
		PreparedStatement query=null;

		String queryString="UPDATE "+TABLE+" SET nomeprodotto=?, prezzo=?, descrizione=?, immagine=?, ingredienti=?, categoria=? WHERE id=?;";


		int result=0;


		try {
			conn = ds.getConnection();
			query = conn.prepareStatement(queryString);

			if(retrieveById(id)!=null) {
				query.setString(1, p.getNomeprodotto());
				query.setFloat(2,p.getPrezzo());
				query.setString(3, p.getDescrizione());
				query.setString(4, p.getImmagine());
				String ingredienti="";
				for(String s: p.getIngredienti())
					ingredienti+=s+", ";
				ingredienti=ingredienti.substring(0, ingredienti.length()-2);
				query.setString(5, ingredienti);
				query.setString(6, p.getCategoria());
				query.setInt(7, id);

				result=query.executeUpdate();
			}

			p.setId(id);

		} finally {
			try {
				if (query != null)
					query.close();
			} finally {
				if(conn!=null)
					conn.close();
			}
		}



		return result==1 ? p : null;

	}

	public int delete(int id) throws SQLException {

		Connection conn=null;
		PreparedStatement query=null;

		String queryString="DELETE FROM "+TABLE+" WHERE id=?;";

		int result=0;

		try {
			conn = ds.getConnection();
			query = conn.prepareStatement(queryString);

			if(retrieveById(id)!=null) {
				query.setInt(1,id);
				result=query.executeUpdate();
			}


		} finally {
			try {
				if (query != null)
					query.close();
			} finally {
				if(conn!=null)
					conn.close();
			}
		}



		return result;

	}

}
