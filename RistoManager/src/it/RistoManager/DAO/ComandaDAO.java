package it.RistoManager.DAO;

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

import it.RistoManager.Model.ClienteBean;
import it.RistoManager.Model.ComandaBean;
import it.RistoManager.Model.ComandaItemBean;
import it.RistoManager.Model.RiepilogoBean;

public class ComandaDAO {

	private static final String COMANDA = "comanda";
	private static final String COMPOSIZIONE ="composizione";
	private ClienteDAO cDao=new ClienteDAO();
	private ProdottoDAO pDao=new ProdottoDAO();

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


	public ComandaBean create(ComandaBean c) throws SQLException {

		Connection conn=null;
		PreparedStatement query=null;
		RiepilogoBean r=null;
		ClienteBean cliente=null;

		int result=0;


		String createComanda="INSERT INTO "+COMANDA+ " (totale,cliente,completata) VALUES (?,?,?);";
		String insertProdotto="INSERT INTO "+COMPOSIZIONE+" (comanda,prodotto, quantita) VALUES (?,?,?);";

		try {
			conn = ds.getConnection();
			query = conn.prepareStatement(createComanda,Statement.RETURN_GENERATED_KEYS);


			cliente=cDao.retrieveById(c.getCliente().getId());

			if(c!=null) {

				query.setFloat(1, c.getTotale());
				query.setInt(2, cliente.getId());
				query.setBoolean(3, c.getCompletata());

				result=query.executeUpdate();

				ResultSet idSet=query.getGeneratedKeys();

				int id= idSet.next() ? idSet.getInt(1) : -1;

				c.setId(id);

				query.close();

				query=conn.prepareStatement(insertProdotto);

				for(ComandaItemBean item : c.getProdotti()) {

					query.setInt(1, c.getId());
					query.setInt(2, item.getProdotto().getId());
					query.setInt(3, item.getQuantita());
					query.executeUpdate();

				}

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

		return result==1 ? c : null;
	}

	public ComandaBean retrieveById(int id) throws SQLException {


		System.out.println("chiamata comandaDao retrieve");

		ComandaBean c=null;

		Connection conn=null;
		PreparedStatement query=null;

		String selectString="SELECT * FROM "+COMANDA+" WHERE id=?;";
		String queryString="SELECT prodotto, quantita " + 
				"FROM "+COMANDA+ 
				" JOIN "+COMPOSIZIONE+" on comanda.id=composizione.comanda WHERE comanda.id=?;";

		try {

			conn=ds.getConnection();
			query=conn.prepareStatement(selectString);

			query.setInt(1, id);

			ResultSet rs=query.executeQuery();

			if(rs.next()) {
				c=new ComandaBean();
				c.setId(id);
				c.setTotale(rs.getFloat("totale"));
				c.setCliente(cDao.retrieveById(rs.getInt("cliente")));
				c.setCompletata(rs.getBoolean("completata"));

				query.close();
				query=conn.prepareStatement(queryString);

				query.setInt(1, id);

				ResultSet products=query.executeQuery();

				List<ComandaItemBean> list=new ArrayList<ComandaItemBean>();

				while(products.next()) {
					ComandaItemBean item=new ComandaItemBean();
					item.setProdotto(pDao.retrieveById(products.getInt("prodotto")));
					item.setQuantita(products.getInt("quantita"));
					list.add(item);

				}
				c.setProdotti(list);
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

		return c;
	}

	public List<ComandaBean> retrieveAll() throws SQLException{

		System.out.println("entro");

		List<ComandaBean> list=new ArrayList<ComandaBean>();

		Connection conn=null;
		PreparedStatement query=null;
		String selectString="SELECT * FROM "+COMANDA+";";

		try {

			conn=ds.getConnection();
			query=conn.prepareStatement(selectString);

			ResultSet rs=query.executeQuery();

			while(rs.next()) {
				ComandaBean c=retrieveById(rs.getInt("id"));
				list.add(c);
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

		return list;

	}

	public List<ComandaBean> retrieveByCodiceTavolo(String codice) throws SQLException{

		List<ComandaBean> list=new ArrayList<ComandaBean>();

		Connection conn=null;
		PreparedStatement query=null;
		String selectString="SELECT comanda.id " + 
				"FROM "+COMANDA+" "+ 
				"JOIN  cliente on comanda.cliente=cliente.id where codicetavolo=?";

		try {

			conn=ds.getConnection();
			query=conn.prepareStatement(selectString);
			query.setString(1, codice);

			ResultSet rs=query.executeQuery();

			while(rs.next()) {
				ComandaBean c=retrieveById(rs.getInt("id"));
				list.add(c);
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

		return list;

	}

	//aggiorna solo gli attributi base di comanda, essenzialmente dovrebbe soltanto aggiornare lo stato di completata
	public ComandaBean update(int id, ComandaBean c) throws SQLException {

		Connection conn=null;
		PreparedStatement query=null;
		int result=0;

		String queryString="UPDATE "+COMANDA+" SET totale=?, cliente=?, riepilogo=?, completata=? WHERE id=?;";

		try {

			conn=ds.getConnection();
			query=conn.prepareStatement(queryString);

			if(retrieveById(id)!=null) {

				query.setFloat(1, c.getTotale());
				query.setInt(2,c.getCliente().getId());
				query.setBoolean(3, c.getCompletata());
				query.setInt(4, id);

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

		return result==1 ? c : null;

	}


	public int delete(int id) throws SQLException {

		Connection conn=null;
		PreparedStatement query=null;
		int result=0;

		String queryString="DELETE FROM "+COMANDA+" WHERE id=?;";

		try {

			conn=ds.getConnection();
			query=conn.prepareStatement(queryString);

			if(retrieveById(id)!=null) {

				query.setInt(1, id);

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
