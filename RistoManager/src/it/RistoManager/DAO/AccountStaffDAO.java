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

import it.RistoManager.Model.AccountStaffBean;
import it.RistoManager.Model.AccountStaffBean.Ruolo;

public class AccountStaffDAO{


	public static final String TABLE="accountstaff";

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


	public AccountStaffBean create(AccountStaffBean  staff) throws SQLException {

		Connection conn=null;
		PreparedStatement query=null;

		String queryString="INSERT INTO "+TABLE+" (email, nome, cognome, password, ruolo) VALUES (?,?,?,?,?);";

		int result=0;
		int id=-1;

		try {
			conn = ds.getConnection();
			query = conn.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);

			query.setString(1, staff.getEmail());
			query.setString(2, staff.getNome());
			query.setString(3, staff.getCognome());
			query.setString(4, staff.getPassword());
			query.setString(5, staff.getRuolo().name());

			result=query.executeUpdate();

			ResultSet rs=query.getGeneratedKeys();

			if(rs.next())
				id=rs.getInt(1);

			staff.setId(id);

		} finally {
			try {
				if (query != null)
					query.close();
			} finally {
				if(conn!=null)
					conn.close();
			}
		}


		return (result == 1 ? staff : null);
	}

	public AccountStaffBean retrieveById(int id) throws SQLException {

		AccountStaffBean staff= null;

		Connection conn=null;
		PreparedStatement query=null;

		String queryString="SELECT * FROM "+TABLE+" WHERE id=?;";

		try {
			conn = ds.getConnection();
			query = conn.prepareStatement(queryString);

			query.setInt(1, id);

			ResultSet rs=query.executeQuery();

			if(rs.next()) {

				staff=new AccountStaffBean();
				staff.setId(id);
				staff.setEmail(rs.getString("email"));
				staff.setNome(rs.getString("nome"));
				staff.setCognome(rs.getString("cognome"));
				staff.setPassword(rs.getString("password"));
				staff.setRuolo(Ruolo.valueOf(rs.getString("ruolo")));
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



		return staff;
	}

	public AccountStaffBean  retrieveByEmail(String email) throws SQLException {

		AccountStaffBean staff= null;

		Connection conn=null;
		PreparedStatement query=null;

		String queryString="SELECT * FROM "+TABLE+" WHERE email=?;";

		try {
			conn = ds.getConnection();
			query = conn.prepareStatement(queryString);

			query.setString(1, email);

			ResultSet rs=query.executeQuery();

			if(rs.next()) {

				staff=new AccountStaffBean();
				staff.setId(rs.getInt("id"));
				staff.setEmail(rs.getString("email"));
				staff.setNome(rs.getString("nome"));
				staff.setCognome(rs.getString("cognome"));
				staff.setPassword(rs.getString("password"));
				staff.setRuolo(Ruolo.valueOf(rs.getString("ruolo")));
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



		return staff;
	}

	public List<AccountStaffBean> retrieveAll() throws SQLException {

		List<AccountStaffBean>staffList=new ArrayList<AccountStaffBean>();

		Connection conn=null;
		PreparedStatement query=null;

		String queryString="SELECT * FROM "+TABLE+";";

		try {
			conn = ds.getConnection();
			query = conn.prepareStatement(queryString);


			ResultSet rs=query.executeQuery();

			while(rs.next()) {

				AccountStaffBean staff=new AccountStaffBean();
				staff=new AccountStaffBean();
				staff.setId(rs.getInt("id"));
				staff.setEmail(rs.getString("email"));
				staff.setNome(rs.getString("nome"));
				staff.setCognome(rs.getString("cognome"));
				staff.setPassword(rs.getString("password"));
				staff.setRuolo(Ruolo.valueOf(rs.getString("ruolo")));

				staffList.add(staff);
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



		return staffList;
	}

	public AccountStaffBean update(int id, AccountStaffBean staff) throws SQLException {
		// TODO Auto-generated method stub

		Connection conn=null;
		PreparedStatement query=null;

		int result=0;

		String queryString="UPDATE "+TABLE+" SET email=?, nome=?, cognome=?, password=?, ruolo=? WHERE id=?;";

		try {
			conn = ds.getConnection();
			query = conn.prepareStatement(queryString);

			if(retrieveById(id)!=null){

				query.setString(1, staff.getEmail());
				query.setString(2, staff.getNome());
				query.setString(3, staff.getCognome());
				query.setString(4, staff.getPassword());
				query.setString(5, staff.getRuolo().name());
				query.setInt(6, id);

				result=query.executeUpdate();


			}
			
			staff.setId(id);

		} finally {
			try {
				if (query != null)
					query.close();
			} finally {
				if(conn!=null)
					conn.close();
			}
		}

		return (result == 1 ? retrieveById(id) : null);
	}

	public int delete(int id) throws SQLException {

		Connection conn=null;
		PreparedStatement query=null;

		int result=0;

		String queryString="DELETE FROM "+TABLE+" WHERE id=?;";

		try {
			conn = ds.getConnection();
			query = conn.prepareStatement(queryString);

			if(retrieveById(id)!=null){


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
