package controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLCONN extends GenericCONN {

	private static final String DBURL = "jdbc:mysql://localhost:3306/DBbanco";
	private static final String user = "viniciusalopes";
	private static final String pass = "123";

	public Connection getConnection() {
		if (con == null) {
			try {
				con = DriverManager.getConnection(DBURL, user, pass);
			} catch (Exception e) {
				System.err.println("Não foi possível conectar com o banco de dados.");
			}
		}
		return con;
	}
}
