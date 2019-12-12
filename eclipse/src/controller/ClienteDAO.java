package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Cliente;

public class ClienteDAO extends GenericDAO {

	private static ClienteDAO instance;

	private ClienteDAO() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		super();
	}

	public static ClienteDAO getInstance() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (instance == null) {
			instance = new ClienteDAO();
		}
		return instance;
	}

	protected Object buildObject(ResultSet rs) throws SQLException {
		Cliente obj = null;
		obj = new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("email"));
		return obj;
	}

	public void create(String nome, String email) throws SQLException {
		PreparedStatement stmt;
		String query = "INSERT INTO cliente (nome, email) VALUES (?, ?)";
		stmt = con.getConnection().prepareStatement(query);
		stmt.setString(1, nome);
		stmt.setString(2, email);
		con.executeUpdate(stmt);
	}

	public ArrayList<Object> retrieveAll() throws SQLException {
		String query = "SELECT * FROM cliente ORDER BY nome";
		return retrieveListOfObjects(query);
	}

	public ArrayList<Object> retrieveLike(String nome) throws SQLException {
		String query = "SELECT * FROM cliente WHERE nome LIKE '%" + nome + "%' ORDER BY nome";
		return retrieveListOfObjects(query);
	}

	public Cliente retrieveById(int id) throws SQLException {
		return (Cliente) retrieveById(id, "cliente");
	}

	public Cliente retrieveLastId() throws SQLException {
		return (Cliente) retrieveLastId("cliente");
	}

	public boolean update(Cliente cliente) throws SQLException {
		PreparedStatement stmt;
		String query = "UPDATE cliente SET nome = ?, email = ? WHERE id = ?";
		stmt = con.getConnection().prepareStatement(query);
		stmt.setString(1, cliente.getNome());
		stmt.setString(2, cliente.getEmail());
		stmt.setInt(3, cliente.getId());
		return (con.executeUpdate(stmt) == 1);
	}
	
	public void delete (Cliente cliente) throws SQLException  {
		this.delete(cliente.getId(), "cliente");
	}

}
