/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author vovostudio
 */
public class MySQLCONN extends GenericCONN {

    private static final String DBURL = "jdbc:http://debian-dev:3306/bdsbs";
    private static final String user = "sbs";
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
