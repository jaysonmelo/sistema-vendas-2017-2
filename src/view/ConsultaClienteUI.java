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

import controller.ClienteController;
import dao.ClienteDAO;
import model.Cliente;
import model.ClienteTableModel;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConsultaClienteUI extends JInternalFrame {
	private JTextField jtfPesquisa;
	private JTable jtListaClientes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaClienteUI frame = new ConsultaClienteUI();
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
	public ConsultaClienteUI() {
		setClosable(true);
		setTitle("Consulta de Clientes");
		setBounds(100, 100, 502, 314);

		JPanel jpPesquisa = new JPanel();
		jpPesquisa.setBorder(new TitledBorder(null, "Pesquisa de Clientes", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JScrollPane jspTabelaCliente = new JScrollPane();

		JButton btnNovoCliente = new JButton("Novo Cliente");
		btnNovoCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastrarClienteUI clienteUI = new CadastrarClienteUI();
				getParent().add(clienteUI,0);
				clienteUI.setVisible(true);
			}
		});
		btnNovoCliente.setIcon(new ImageIcon(ConsultaClienteUI.class.getResource("/img/plus.png")));

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int linhaSelecionada = jtListaClientes.getSelectedRow();
				Cliente cliente = new ClienteTableModel(
						ClienteDAO.obterInstancia().listaClientes
						).get(linhaSelecionada);
				CadastrarClienteUI clienteUI = new CadastrarClienteUI();
				clienteUI.setClienteParaEdicao(cliente);
				getParent().add(clienteUI,0);
				clienteUI.setVisible(true);
			}
		});
		btnEditar.setIcon(new ImageIcon(ConsultaClienteUI.class.getResource("/img/edit.png")));

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int linhaSelecionada = jtListaClientes.getSelectedRow();
					Cliente cliente = new ClienteTableModel(
							ClienteDAO.obterInstancia().listaClientes
							).get(linhaSelecionada);
					new ClienteController().remover(cliente);

					JOptionPane.showMessageDialog(null, 
							"Cliente excluído com sucesso", 
							"Exclusão de Cliente", 
							JOptionPane.WARNING_MESSAGE);
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		btnExcluir.setIcon(new ImageIcon(ConsultaClienteUI.class.getResource("/img/bag.png")));

		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnFechar.setIcon(new ImageIcon(ConsultaClienteUI.class.getResource("/img/close.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(jspTabelaCliente, GroupLayout.PREFERRED_SIZE, 461, GroupLayout.PREFERRED_SIZE)
								.addComponent(jpPesquisa, GroupLayout.PREFERRED_SIZE, 464, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(btnNovoCliente)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnEditar)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnExcluir)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnFechar)))
						.addContainerGap(193, Short.MAX_VALUE))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jpPesquisa, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(jspTabelaCliente, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNovoCliente)
								.addComponent(btnEditar)
								.addComponent(btnExcluir)
								.addComponent(btnFechar))
						.addContainerGap(128, Short.MAX_VALUE))
				);

		jtListaClientes = new JTable();
		ClienteTableModel modelCliente = new ClienteTableModel(
				new ClienteController().listarTodos()
				);
		jtListaClientes.setModel(modelCliente);
		jspTabelaCliente.setViewportView(jtListaClientes);

		jtfPesquisa = new JTextField();
		jtfPesquisa.setColumns(10);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<Cliente> filtro = 
						new ClienteController().pesquisarClientePorNome(jtfPesquisa.getText());
				
				ClienteTableModel modelCliente = new ClienteTableModel(
						filtro
						);
				jtListaClientes.setModel(modelCliente);
			}
		});
		GroupLayout gl_jpPesquisa = new GroupLayout(jpPesquisa);
		gl_jpPesquisa.setHorizontalGroup(
				gl_jpPesquisa.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpPesquisa.createSequentialGroup()
						.addContainerGap()
						.addComponent(jtfPesquisa, GroupLayout.PREFERRED_SIZE, 349, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnPesquisar)
						.addContainerGap(36, Short.MAX_VALUE))
				);
		gl_jpPesquisa.setVerticalGroup(
				gl_jpPesquisa.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpPesquisa.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_jpPesquisa.createParallelGroup(Alignment.BASELINE)
								.addComponent(jtfPesquisa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnPesquisar))
						.addContainerGap(56, Short.MAX_VALUE))
				);
		jpPesquisa.setLayout(gl_jpPesquisa);
		getContentPane().setLayout(groupLayout);

	}
}
