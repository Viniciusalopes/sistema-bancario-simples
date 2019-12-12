package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class GenericDAO {

	protected GenericCONN con;

	protected GenericDAO() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class<?> daoClass = Class.forName("model." + GenericCONN.DAOName);
		con = (GenericCONN) daoClass.newInstance();
		con.getConnection();
	}

	protected abstract Object buildObject(ResultSet rs) throws SQLException;

	protected ArrayList<Object> retrieveListOfObjects(String query) throws SQLException {
		PreparedStatement stmt;
		ArrayList<Object> list = new ArrayList<Object>();
		ResultSet rs;
		stmt = con.getConnection().prepareStatement(query);
		rs = con.getResultSet(stmt);
		while (rs.next()) {
			list.add(buildObject(rs));
		}
		rs.close();
		return list;
	}

	protected Object retrieveById(int id, String tableName) throws SQLException {
		PreparedStatement stmt;
		Object obj = null;
		ResultSet rs;
		String query = "SELECT * FROM " + tableName + "WHERE id = ?";

		stmt = con.getConnection().prepareStatement(query);
		stmt.setInt(1, id);
		rs = con.getResultSet(stmt);
		if (rs.next()) {
			obj = buildObject(rs);
		}
		rs.close();
		return obj;
	}

	protected Object retrieveLastId(String tableName) throws SQLException {
		int id = con.lastId(tableName, "id");
		return retrieveById(id, tableName);
	}

	protected void delete(int id, String tableName) throws SQLException {
		PreparedStatement stmt;
		String query = "DELETE FROM " + tableName + " WHERE id = ?";
		stmt = con.getConnection().prepareStatement(query);
		stmt.setInt(1, id);
		con.executeUpdate(stmt);
	}

}
