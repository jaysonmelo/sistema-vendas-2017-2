package view;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.ClienteController;
import controller.IProdutoController;
import controller.impl.ProdutoController;
import model.Cliente;
import model.ItemVenda;
import model.ItemVendaTableModel;
import model.Produto;

import javax.swing.ImageIcon;

public class RegistrarVendaUI extends JInternalFrame {
	private JTextField jtfDataEmissao;
	private JTextField jtfQtde;
	private JTable jtProdutosVenda;
	private ClienteController clienteControl = new ClienteController();
	private IProdutoController produtoControl = new ProdutoController();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrarVendaUI frame = new RegistrarVendaUI();
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
	public RegistrarVendaUI() {
		setClosable(true);
		setTitle("Registrar Venda");
		setBounds(100, 100, 560, 444);
		
		JPanel jpDados = new JPanel();
		jpDados.setBorder(new TitledBorder(null, "Dados da Venda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel jpProdutos = new JPanel();
		jpProdutos.setBorder(new TitledBorder(null, "Produtos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(RegistrarVendaUI.class.getResource("/img/save.png")));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(RegistrarVendaUI.class.getResource("/img/cancel.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(jpProdutos, GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnSalvar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancelar))
						.addComponent(jpDados, GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jpDados, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jpProdutos, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSalvar)
						.addComponent(btnCancelar))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		
		jtfQtde = new JTextField();
		jtfQtde.setColumns(10);
		
		JLabel lblProduto = new JLabel("Produto:");
		
		DefaultComboBoxModel<Produto> modelProduto = new DefaultComboBoxModel<>();
		for(Produto produto : produtoControl.listarProdutos()){
			modelProduto.addElement(produto);
		}
		
		JComboBox<Produto> jcbProduto = new JComboBox<>();
		jcbProduto.setModel(modelProduto);
		
		JButton jbAdicionar = new JButton("Adicionar");
		jbAdicionar.setIcon(new ImageIcon(RegistrarVendaUI.class.getResource("/img/plus.png")));
		
		JScrollPane jspProdutos = new JScrollPane();
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.setIcon(new ImageIcon(RegistrarVendaUI.class.getResource("/img/close.png")));
		
		JLabel lblValorTotalr = new JLabel("Valor Total(R$):");
		
		JLabel lblSomaTotal = new JLabel("0,00");
		GroupLayout gl_jpProdutos = new GroupLayout(jpProdutos);
		gl_jpProdutos.setHorizontalGroup(
			gl_jpProdutos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpProdutos.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_jpProdutos.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jpProdutos.createSequentialGroup()
							.addComponent(jspProdutos, GroupLayout.PREFERRED_SIZE, 366, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_jpProdutos.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSomaTotal)
								.addComponent(lblValorTotalr)
								.addComponent(btnRemover)))
						.addGroup(gl_jpProdutos.createSequentialGroup()
							.addGroup(gl_jpProdutos.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(jtfQtde, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
								.addComponent(lblQuantidade, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(gl_jpProdutos.createParallelGroup(Alignment.LEADING)
								.addComponent(lblProduto)
								.addGroup(gl_jpProdutos.createSequentialGroup()
									.addComponent(jcbProduto, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(jbAdicionar)))))
					.addContainerGap(169, Short.MAX_VALUE))
		);
		gl_jpProdutos.setVerticalGroup(
			gl_jpProdutos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpProdutos.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_jpProdutos.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblQuantidade)
						.addComponent(lblProduto))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_jpProdutos.createParallelGroup(Alignment.BASELINE)
						.addComponent(jtfQtde, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jbAdicionar)
						.addComponent(jcbProduto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_jpProdutos.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jpProdutos.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jspProdutos, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))
						.addGroup(gl_jpProdutos.createSequentialGroup()
							.addGap(31)
							.addComponent(btnRemover)
							.addPreferredGap(ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
							.addComponent(lblValorTotalr)
							.addGap(7)
							.addComponent(lblSomaTotal)))
					.addContainerGap())
		);
		
		jtProdutosVenda = new JTable();
		List<ItemVenda> listaItensVenda = new ArrayList<ItemVenda>();
		ItemVendaTableModel modelItemVenda = new ItemVendaTableModel(listaItensVenda);
		jtProdutosVenda.setModel(modelItemVenda);

		jspProdutos.setViewportView(jtProdutosVenda);
		jpProdutos.setLayout(gl_jpProdutos);
		
		JLabel lblDataEmisso = new JLabel("Data emiss\u00E3o:");
		
		jtfDataEmissao = new JTextField();
		jtfDataEmissao.setColumns(10);
		
		JLabel lblCliente = new JLabel("Cliente:");
		
		DefaultComboBoxModel<Cliente> modelCliente = new DefaultComboBoxModel<>();
		for (Cliente cliente : clienteControl.listarTodos()) {
			modelCliente.addElement(cliente);
		}
		
		JComboBox<Cliente> jcbCliente = new JComboBox<>();
		jcbCliente.setModel(modelCliente);
		
		GroupLayout gl_jpDados = new GroupLayout(jpDados);
		gl_jpDados.setHorizontalGroup(
			gl_jpDados.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpDados.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_jpDados.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jpDados.createSequentialGroup()
							.addComponent(jtfDataEmissao, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(jcbCliente, GroupLayout.PREFERRED_SIZE, 345, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_jpDados.createSequentialGroup()
							.addComponent(lblDataEmisso)
							.addGap(55)
							.addComponent(lblCliente)))
					.addContainerGap(173, Short.MAX_VALUE))
		);
		gl_jpDados.setVerticalGroup(
			gl_jpDados.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpDados.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_jpDados.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDataEmisso)
						.addComponent(lblCliente))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_jpDados.createParallelGroup(Alignment.BASELINE)
						.addComponent(jtfDataEmissao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jcbCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(59, Short.MAX_VALUE))
		);
		jpDados.setLayout(gl_jpDados);
		getContentPane().setLayout(groupLayout);

	}
}
