/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Cliente;

/**
 *
 * @author vovostudio
 */
public class ClienteDAO extends GenericDAO {

    private static ClienteDAO instance;

    private ClienteDAO() {
        super();
    }

    public static ClienteDAO getInstance() {
        if (instance == null) {
            instance = new ClienteDAO();
        }
        return instance;
    }

    protected Object buildObject(ResultSet rs)
            throws SQLException {
        Cliente obj = null;
        obj = new Cliente(rs.getInt("id"), rs.getString("nome"),
                rs.getString("email"));
        rs.getString("email");
        return obj;
    }

    public void create(String nome, String email) throws SQLException {
        PreparedStatement stmt;
        stmt = con.getConnection().prepareStatement("INSERT INTO cliente (nome, email) VALUES (?,?)");
        stmt.setString(1, nome);
        stmt.setString(2, email);
        con.executeUpdate(stmt);
    }

    public List<Object> retrieveAll() throws SQLException {
        return retrieveListOfObjects("SELECT * FROM cliente ORDER BY nome");
    }

    public List<Object> retrieveLike(String nome) throws SQLException {
        return retrieveListOfObjects("SELECT * FROM cliente WHERE nome LIKE '%" + nome + "%'");
    }

    public Cliente retrieveById(int id) throws SQLException {
        return (Cliente) retrieveById(id, "cliente");
    }

    public Cliente retrieveLastId() throws SQLException {
        return (Cliente) retrieveLastId("cliente");
    }

    public boolean update(Cliente cliente) throws SQLException {
        PreparedStatement stmt;
        stmt = con.getConnection().prepareStatement(
                "UPDATE cliente SET nome = ?, email = ? WHERE id = ?");

        stmt.setString(1, cliente.getNome());
        stmt.setString(2, cliente.getEmail());
        return (con.executeUpdate(stmt) == 1);
    }

    public void delete(Cliente cliente) throws SQLException {
        this.delete(cliente.getId(), "cliente");
    }
}
