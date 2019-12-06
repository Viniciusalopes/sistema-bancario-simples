/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author vovostudio
 */
public abstract class GenericCONN {

    public static final String DAOName = "MySQLCONN";

    protected static Connection con;

    public abstract Connection getConnection();

    protected ResultSet getResultSet(PreparedStatement queryStatement)
            throws SQLException {
        ResultSet rs = null;
        return queryStatement.executeQuery();
    }

    protected int executeUpdate(PreparedStatement queryStatement)
            throws SQLException {
        return queryStatement.executeUpdate();
    }

    protected int lastId(String tableName, String primaryKey)
            throws SQLException {
        Statement s;
        ResultSet rs;
        int lastId = -1;
        this.getConnection();
        s = con.createStatement();
        s.executeQuery("SELECT MAX(" + primaryKey + ") AS \"key\" FROM " + tableName);
        rs = s.getResultSet();
        if (rs.next()) {
            lastId = rs.getInt("key");
        }
        return lastId;
    }

}
