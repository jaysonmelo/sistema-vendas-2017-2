package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class PrincipalUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalUI frame = new PrincipalUI();
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
	public PrincipalUI() {
		setTitle("Sistema de Vendas SENAI 2017-2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 679, 438);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnCliente = new JMenu("Clientes");
		menuBar.add(mnCliente);
		
		JMenuItem mntmNovoCliente = new JMenuItem("Novo Cliente");
		mntmNovoCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastrarClienteUI cadCliente = new CadastrarClienteUI();
				contentPane.add(cadCliente,0);
				cadCliente.setVisible(true);
			}
		});
		mnCliente.add(mntmNovoCliente);
		
		JMenuItem mntmConsultas = new JMenuItem("Consultas");
		mntmConsultas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultaClienteUI consultaCliente = new ConsultaClienteUI();
				contentPane.add(consultaCliente,0);
				consultaCliente.setVisible(true);
			}
		});
		mnCliente.add(mntmConsultas);
		
		JMenu mnProduto = new JMenu("Produtos");
		menuBar.add(mnProduto);
		
		JMenuItem mntmNovoProduto = new JMenuItem("Novo Produto");
		mntmNovoProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastrarProdutoUI cadProduto = new CadastrarProdutoUI();
				contentPane.add(cadProduto,0);
				cadProduto.setVisible(true);
			}
		});
		mnProduto.add(mntmNovoProduto);
		
		JMenuItem mntmConsulta = new JMenuItem("Consulta");
		mntmConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultaProdutoUI consultaProduto = new ConsultaProdutoUI();
				contentPane.add(consultaProduto,0);
				consultaProduto.setVisible(true);
			}
		});
		mnProduto.add(mntmConsulta);
		
		JMenu mnVendas = new JMenu("Vendas");
		menuBar.add(mnVendas);
		
		JMenuItem mntmRegistrarVenda = new JMenuItem("Registrar Venda");
		mntmRegistrarVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegistrarVendaUI registroVenda = new RegistrarVendaUI();
				contentPane.add(registroVenda,0);
				registroVenda.setVisible(true);
			}
		});
		mnVendas.add(mntmRegistrarVenda);
		
		JMenu mnRelatrios = new JMenu("Relat\u00F3rios");
		menuBar.add(mnRelatrios);
		
		JMenuItem mntmVendasPorPerodo = new JMenuItem("Vendas Por Per\u00EDodo");
		mntmVendasPorPerodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RelatorioVendasPorPeriodo relatorio = new RelatorioVendasPorPeriodo();
				contentPane.add(relatorio,0);
				relatorio.setVisible(true);
			}
		});
		mnRelatrios.add(mntmVendasPorPerodo);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 653, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 368, Short.MAX_VALUE)
		);
		contentPane.setLayout(gl_contentPane);
	}

}
