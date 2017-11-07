package controller;

import java.util.List;

import model.Produto;

public interface IProdutoController {

	public void salvar(Produto produto);
	
	public void editar(Produto produto, int posicao);
	
	public void remover(int posicao);
	
	public List<Produto> listarProdutos();
	
}
