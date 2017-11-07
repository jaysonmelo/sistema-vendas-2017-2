package model;

public class ItemVenda {

	private int qtde;
	private Produto produto;
	private Double valorTotal;
	
	public ItemVenda(int qtde, Produto produto) {
		this.qtde = qtde;
		this.produto = produto;
		if (produto != null)
			this.valorTotal = qtde * produto.getPrecoUnitario();
	}
	public ItemVenda() {
	}
	public int getQtde() {
		return qtde;
	}
	public void setQtde(int qtde) {
		this.qtde = qtde;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
}
