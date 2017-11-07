package view;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.IProdutoController;
import controller.impl.ProdutoController;
import model.Produto;
import model.ProdutoTableModel;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConsultaProdutoUI extends JInternalFrame {
	private JTextField jtfPesquisa;
	private JTable jtListaProdutos;
	private IProdutoController produtoControl = new ProdutoController();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaProdutoUI frame = new ConsultaProdutoUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ConsultaProdutoUI() {
		setClosable(true);
		setTitle("Consulta de Produtos");
		setBounds(100, 100, 560, 286);
		
		JPanel jpPesquisa = new JPanel();
		jpPesquisa.setBorder(new TitledBorder(null, "Pesquisa de Produtos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JScrollPane jspTabelaProduto = new JScrollPane();
		
		JButton btnNovoProduto = new JButton("Novo Produto");
		btnNovoProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastrarProdutoUI cadProduto = new CadastrarProdutoUI();
				getParent().add(cadProduto,0);
				cadProduto.setVisible(true);
			}
		});
		btnNovoProduto.setIcon(new ImageIcon(ConsultaProdutoUI.class.getResource("/img/plus.png")));
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int linhaSelecionada = jtListaProdutos.getSelectedRow();
				Produto produtoParaEdicao = produtoControl.listarProdutos().get(linhaSelecionada);
				CadastrarProdutoUI cadProduto = new CadastrarProdutoUI();
				getParent().add(cadProduto,0);
				cadProduto.setProdutoParaEdicao(produtoParaEdicao);
				cadProduto.setPosicaoParaEdicao(linhaSelecionada);
				cadProduto.setVisible(true);
			}
		});
		btnEditar.setIcon(new ImageIcon(ConsultaProdutoUI.class.getResource("/img/edit.png")));
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int linhaSelecionada = jtListaProdutos.getSelectedRow();
				
				produtoControl.remover(linhaSelecionada);
				
				JOptionPane.showMessageDialog(null, "Produto excluído com sucesso");
			}
		});
		btnExcluir.setIcon(new ImageIcon(ConsultaProdutoUI.class.getResource("/img/bag.png")));
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnFechar.setIcon(new ImageIcon(ConsultaProdutoUI.class.getResource("/img/close.png")));
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<Produto> listaProdutos = produtoControl.listarProdutos();
				ProdutoTableModel modelProduto = new ProdutoTableModel(listaProdutos);
				jtListaProdutos.setModel(modelProduto);
			}
		});
		btnAtualizar.setIcon(new ImageIcon(ConsultaProdutoUI.class.getResource("/img/refresh.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNovoProduto)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEditar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnExcluir)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnFechar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAtualizar))
						.addComponent(jpPesquisa, GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE)
						.addComponent(jspTabelaProduto))
					.addContainerGap(118, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jpPesquisa, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jspTabelaProduto, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNovoProduto)
						.addComponent(btnEditar)
						.addComponent(btnExcluir)
						.addComponent(btnFechar)
						.addComponent(btnAtualizar))
					.addContainerGap(12, Short.MAX_VALUE))
		);
		
		jtListaProdutos = new JTable();
		
		List<Produto> listaProdutos = produtoControl.listarProdutos();
		ProdutoTableModel modelProduto = new ProdutoTableModel(listaProdutos);
		jtListaProdutos.setModel(modelProduto);
		jspTabelaProduto.setViewportView(jtListaProdutos);
		
		jtfPesquisa = new JTextField();
		jtfPesquisa.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		GroupLayout gl_jpPesquisa = new GroupLayout(jpPesquisa);
		gl_jpPesquisa.setHorizontalGroup(
			gl_jpPesquisa.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_jpPesquisa.createSequentialGroup()
					.addContainerGap()
					.addComponent(jtfPesquisa, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnPesquisar)
					.addContainerGap())
		);
		gl_jpPesquisa.setVerticalGroup(
			gl_jpPesquisa.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpPesquisa.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_jpPesquisa.createParallelGroup(Alignment.BASELINE)
						.addComponent(jtfPesquisa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisar))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		jpPesquisa.setLayout(gl_jpPesquisa);
		getContentPane().setLayout(groupLayout);

	}
}
