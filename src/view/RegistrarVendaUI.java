package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

import controller.ClienteController;
import controller.IProdutoController;
import controller.IVendaController;
import controller.impl.ProdutoController;
import controller.impl.VendaController;
import model.Cliente;
import model.ItemVenda;
import model.ItemVendaTableModel;
import model.Produto;
import model.Venda;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class RegistrarVendaUI extends JInternalFrame {
	private JTextField jtfDataEmissao;
	private JTextField jtfQtde;
	private JTable jtProdutosVenda;
	
	private ClienteController clienteControl = new ClienteController();
	private IProdutoController produtoControl = new ProdutoController();
	private IVendaController vendaControl = new VendaController();
	
	private List<ItemVenda> itensVenda = new ArrayList<>();
	private JComboBox<Cliente> jcbCliente;
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
		
		JLabel lblSomaTotal = new JLabel("0,00");
		
		JPanel jpDados = new JPanel();
		jpDados.setBorder(new TitledBorder(null, "Dados da Venda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel jpProdutos = new JPanel();
		jpProdutos.setBorder(new TitledBorder(null, "Produtos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Venda venda = new Venda();
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				venda.setDataEmissao(LocalDate.parse(jtfDataEmissao.getText(),dtf));
				venda.setCliente((Cliente)jcbCliente.getSelectedItem());
				venda.setValorTotal(Double.parseDouble(lblSomaTotal.getText()));
				venda.setItensVenda(itensVenda);
				
				vendaControl.salvarVenda(venda);
			}
		});
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
		jbAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int qtde = Integer.parseInt(jtfQtde.getText());
				Produto prod = (Produto)jcbProduto.getSelectedItem();
				
				ItemVenda item = new ItemVenda();
				item.setQtde(qtde);
				item.setProduto(prod);
				item.calcularValorTotal();
				
				itensVenda.add(item);
				
				ItemVendaTableModel modelItemVenda = new ItemVendaTableModel(itensVenda);
				jtProdutosVenda.setModel(modelItemVenda);
				
				lblSomaTotal.setText(calcularValorTotal().toString());
			}
		});
		jbAdicionar.setIcon(new ImageIcon(RegistrarVendaUI.class.getResource("/img/plus.png")));
		
		JScrollPane jspProdutos = new JScrollPane();
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int linhaSelecionada = jtProdutosVenda.getSelectedRow();
				
				itensVenda.remove(linhaSelecionada);
				
				ItemVendaTableModel modelItemVenda = new ItemVendaTableModel(itensVenda);
				jtProdutosVenda.setModel(modelItemVenda);
				
				lblSomaTotal.setText(calcularValorTotal().toString());
			}
		});
		btnRemover.setIcon(new ImageIcon(RegistrarVendaUI.class.getResource("/img/close.png")));
		
		JLabel lblValorTotalr = new JLabel("Valor Total(R$):");
		
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
		
		jcbCliente = new JComboBox<>();
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
	
	public Double calcularValorTotal(){
		Double soma = 0.0;
		for(ItemVenda item : itensVenda){
			soma += item.getValorTotal();
		}
		return soma;
	}
}









