package dao;

import java.util.ArrayList;

import model.Produto;

public class ProdutoDAO {

	private static ProdutoDAO instancia;
	public ArrayList<Produto> listaProdutos = new ArrayList<>();
	
	//Singleton
	public static ProdutoDAO obterInstancia(){
		if(instancia == null){
			instancia = new ProdutoDAO();
		}
		return instancia;
	}
}
