package controller.impl;

import java.util.List;

import controller.IProdutoController;
import dao.ProdutoDAO;
import model.Produto;

public class ProdutoController implements IProdutoController{

	@Override
	public void salvar(Produto produto) {
		ProdutoDAO.obterInstancia().listaProdutos.add(produto);
	}

	@Override
	public void editar(Produto produto, int posicao) {
		ProdutoDAO.obterInstancia().listaProdutos.set(posicao, produto);
	}

	@Override
	public void remover(int posicao) {
		ProdutoDAO.obterInstancia().listaProdutos.remove(posicao);	
	}

	@Override
	public List<Produto> listarProdutos() {
		return ProdutoDAO.obterInstancia().listarTodos();
	}
	
}
