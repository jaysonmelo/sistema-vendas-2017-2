package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import util.ConnectionUtil;

public class ClienteDAO {

	private static ClienteDAO instancia;
	public ArrayList<Cliente> listaClientes;
	private Connection con = ConnectionUtil.getConnection();

	//Singleton
	public static ClienteDAO obterInstancia(){
		if (instancia == null){
			instancia = new ClienteDAO();
		}
		return instancia;
	}

	public void salvar(Cliente cliente){
		try {
			String sql = "insert into cliente (nome, cpf, endereco, telefone) "
					+ "values (? , ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cliente.getNome());
			pstmt.setString(2, cliente.getCpf());
			pstmt.setString(3, cliente.getEndereco());
			pstmt.setString(4, cliente.getTelefone());

			pstmt.execute();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}

	public List<Cliente> listarTodos(){
		listaClientes = new ArrayList<>();
		try {
			Statement stmt = con.createStatement();
			String sql = "select * from cliente";
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()){
				Cliente cliente = new Cliente();
				cliente.setId(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setEndereco(rs.getString("endereco"));
				cliente.setTelefone(rs.getString("telefone"));

				listaClientes.add(cliente);
			}

		}catch(SQLException e){
			e.printStackTrace();
		}

		return listaClientes;
	}

	public void editar(Cliente cliente){
		try {
			String sql = "update cliente set nome = ?, "
					+ "cpf = ?, endereco = ?, telefone = ? "
					+ "where id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cliente.getNome());
			pstmt.setString(2, cliente.getCpf());
			pstmt.setString(3, cliente.getEndereco());
			pstmt.setString(4, cliente.getTelefone());
			pstmt.setInt(5, cliente.getId());

			pstmt.execute();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}

	public void excluir(int id){
		try {
			String sql = "delete from cliente where id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);

			pstmt.execute();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}

	public List<Cliente> pesquisarClientes(String nome){
		try{
			String sql = "select * from cliente where nome like ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + nome + "%");
			ResultSet rs = pstmt.executeQuery();
			listaClientes = new ArrayList<>();
			while(rs.next()){
				Cliente cliente = new Cliente();
				cliente.setId(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setEndereco(rs.getString("endereco"));
				cliente.setTelefone(rs.getString("telefone"));

				listaClientes.add(cliente);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return listaClientes;
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
