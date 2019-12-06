/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vovostudio
 */
public abstract class GenericDAO {

    protected GenericCONN con;

    protected GenericDAO() {
        try {
            Class<?> daoClass = Class.forName("model." + GenericCONN.DAOName);
            con = (GenericCONN) daoClass.newInstance();
            con.getConnection();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected abstract Object buildObject(ResultSet rs);

    protected List<Object> retrieveListOfObjects(String query)
            throws SQLException {
        PreparedStatement stmt;
        List<Object> list = new ArrayList<Object>();
        ResultSet rs;
        stmt = con.getConnection().prepareStatement(query);
        rs = con.getResultSet(stmt);
        while (rs.next()) {
            list.add(buildObject(rs));
        }
        rs.close();
        return list;
    }

    protected Object retrieveById(int id, String tableName)
            throws SQLException {
        PreparedStatement stmt;
        Object obj = null;
        ResultSet rs;
        stmt = con.getConnection().prepareStatement(
                "SELECT * FROM " + tableName + " WHERE id = ?");
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

    private void delete(int id, String tableName) throws SQLException {
        PreparedStatement stmt;
        stmt = con.getConnection().prepareStatement("DELETE FROM " + tableName + " WHERE id = ?");
        stmt.setInt(1, id);
        con.executeUpdate(stmt);
    }
}
