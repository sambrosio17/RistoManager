package it.RistoManager.DAO; 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DriverManagerConnectionPool {
	private static List<Connection> freeDbConnections = new LinkedList<Connection>();

	static {
		freeDbConnections = new LinkedList<Connection>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public DriverManagerConnectionPool() {
		freeDbConnections = new LinkedList<Connection>();
	}

	private static synchronized Connection createDBConnection() throws SQLException {

		Connection newConnection = null;

		String ip = "localhost";
		String port = "3306";
		String db = "ristomanager";
		String username = "root";
		String password = "Nexus5CoC";
		String url = "jdbc:mysql://localhost:3306/";
		String database = "ristomanager";
		String parameter = "?serverTimezone=UTC";

		newConnection = DriverManager.getConnection(url + database + parameter, username, password);

//		System.out.println("Create a new DB connection");
		newConnection.setAutoCommit(true);
		return newConnection;
	}

	public static synchronized Connection getConnection() throws SQLException {
		Connection connection;

		if (!freeDbConnections.isEmpty()) {
			connection = (Connection) freeDbConnections.get(0);
			freeDbConnections.remove(0);

			try {
				if (connection.isClosed())
					connection = getConnection();
			} catch (SQLException e) {
				connection.close();
				connection = getConnection();
			}
		} else {
			connection = createDBConnection();
		}

		return connection;
	}

	public static synchronized void releaseConnection(Connection connection) throws SQLException {
		if (connection != null)
			freeDbConnections.add(connection);
	}
}
