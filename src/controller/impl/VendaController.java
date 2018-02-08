package controller.impl;

import controller.IVendaController;
import dao.VendaDAO;
import model.Venda;

public class VendaController implements IVendaController{

	@Override
	public void salvarVenda(Venda venda) {
		VendaDAO.obterInstancia().salvar(venda);
	}

	
}
