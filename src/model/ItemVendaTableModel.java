package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ItemVendaTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private static final int COL_DESCRICAO = 0;
	private static final int COL_QTDE = 1;
	private static final int COL_PRECO_UNITARIO = 2;
	private static final int COL_VALOR_TOTAL = 3;

	private List<ItemVenda> valores;       

	//Esse � um construtor, que recebe a nossa lista de clientes
	public ItemVendaTableModel(List<ItemVenda> valores) {
		this.valores = new ArrayList<ItemVenda>(valores);
	}

	public int getRowCount() {
		//Quantas linhas tem sua tabela? Uma para cada item da lista.
		return valores.size();
	}

	public int getColumnCount() {
		//Quantas colunas tem a tabela? Nesse exemplo, s� 2.
		return 4;
	}

	public String getColumnName(int column) {
		//Qual � o nome das nossas colunas?
		if (column == COL_DESCRICAO) return "Descri��o";
		if (column == COL_QTDE) return "Qtde";
		if (column == COL_PRECO_UNITARIO) return "Pre�o Unit�rio(R$)";
		if (column == COL_VALOR_TOTAL) return "Valor Total(R$)";
		return ""; //Nunca deve ocorrer
	}

	public Object getValueAt(int row, int column) {
		//Precisamos retornar o valor da coluna column e da linha row.
		ItemVenda itemVenda = valores.get(row);
		if (column == COL_DESCRICAO)
			return itemVenda.getProduto().getDescricao();
		else 
			if ( column == COL_QTDE)
				return itemVenda.getQtde();
			else 
				if (column == COL_PRECO_UNITARIO) 
					return itemVenda.getProduto().getPrecoUnitario();
				else 
					if (column == COL_VALOR_TOTAL)
						return itemVenda.getValorTotal();
		return ""; //Nunca deve ocorrer
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		ItemVenda itemVenda = valores.get(rowIndex);
		//Vamos alterar o valor da coluna columnIndex na linha rowIndex com o valor aValue passado no par�metro.
		//Note que vc poderia alterar 2 campos ao inv�s de um s�.
		if (columnIndex == COL_DESCRICAO)
			itemVenda.getProduto().setDescricao(aValue.toString());
		else 
			if (columnIndex == COL_QTDE)
				itemVenda.setQtde(Integer.parseInt(aValue.toString()));
			else
				if (columnIndex == COL_PRECO_UNITARIO) 
					itemVenda.getProduto().setPrecoUnitario(Double.parseDouble(aValue.toString()));
				else
					if (columnIndex == COL_VALOR_TOTAL)
						itemVenda.setValorTotal(Double.parseDouble(aValue.toString()));
	}

	public Class<?> getColumnClass(int columnIndex) {
		//Qual a classe das nossas colunas? Como estamos exibindo texto, � string.
		return String.class;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		//Indicamos se a c�lula da rowIndex e da columnIndex � edit�vel. Nossa tabela toda �.
		return true;
	}
	//J� que esse tableModel � de clientes, vamos fazer um get que retorne um objeto cliente inteiro.
	//Isso elimina a necessidade de chamar o getValueAt() nas telas. 
	public ItemVenda get(int row) {
		return valores.get(row);
	}
}

