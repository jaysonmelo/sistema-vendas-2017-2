package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Produto;
import util.ConnectionUtil;

public class ProdutoDAO {

	private static ProdutoDAO instancia;
	public ArrayList<Produto> listaProdutos;
	private Connection con = ConnectionUtil.getConnection();
	
	//Singleton
	public static ProdutoDAO obterInstancia(){
		if(instancia == null){
			instancia = new ProdutoDAO();
		}
		return instancia;
	}
	
	public List<Produto> listarTodos(){
		listaProdutos = new ArrayList<>();
		try{
			String sql = "select * from produto";
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()){
				Produto p = new Produto();
				p.setId(rs.getInt("id"));
				p.setDescricao(rs.getString("descricao"));
				p.setPrecoUnitario(rs.getDouble("preco_unitario"));
				listaProdutos.add(p);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return listaProdutos;
	}
}
