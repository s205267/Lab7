package it.polito.tdp.dizionario.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	
	private static final String jdbcURL = "jdbc:mysql://localhost/dizionario?user=root" ;
	
	public static Connection getConnection() {
		Connection conn;
		try {
			conn = DriverManager.getConnection(jdbcURL);
			return conn ;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore nella connessione", e) ;
		}
	}

}
