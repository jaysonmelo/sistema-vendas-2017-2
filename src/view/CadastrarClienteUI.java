package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import controller.ClienteController;
import model.Cliente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastrarClienteUI extends JInternalFrame {
	private JTextField jtfNome;
	private JTextField jtfCpf;
	private JTextField jtfEndereco;
	private JTextField jtfTelefone;
	private Cliente clienteParaEdicao;
	private int posicaoParaEdicao;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarClienteUI frame = new CadastrarClienteUI();
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
	public CadastrarClienteUI() {
		setMaximizable(true);
		setClosable(true);
		setTitle("Cadastro de Cliente");
		setBounds(100, 100, 484, 264);
		
		JPanel jpCadastroCliente = new JPanel();
		jpCadastroCliente.setBorder(new TitledBorder(null, "Dados do Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (clienteParaEdicao == null){
						Cliente cliente = new Cliente();
						cliente.setNome(jtfNome.getText());
						cliente.setCpf(jtfCpf.getText());
						cliente.setTelefone(jtfTelefone.getText());
						cliente.setEndereco(jtfEndereco.getText());
						
						new ClienteController().salvar(cliente);
						
						JOptionPane.showMessageDialog(null, "Cliente Cadastrado com sucesso");
					}else{
						clienteParaEdicao.setNome(jtfNome.getText());
						clienteParaEdicao.setCpf(jtfCpf.getText());
						clienteParaEdicao.setTelefone(jtfTelefone.getText());
						clienteParaEdicao.setEndereco(jtfEndereco.getText());
						
						new ClienteController().editar(clienteParaEdicao);
	
						JOptionPane.showMessageDialog(null, "Cliente Editado com sucesso");
					}
					dispose();
				} catch (Exception e){
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				
			}
		});
		btnSalvar.setIcon(new ImageIcon(CadastrarClienteUI.class.getResource("/img/save.png")));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setIcon(new ImageIcon(CadastrarClienteUI.class.getResource("/img/cancel.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(jpCadastroCliente, GroupLayout.PREFERRED_SIZE, 452, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnSalvar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancelar)))
					.addContainerGap(6, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jpCadastroCliente, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSalvar)
						.addComponent(btnCancelar))
					.addContainerGap(34, Short.MAX_VALUE))
		);
		
		JLabel lblNome = new JLabel("Nome:");
		
		jtfNome = new JTextField();
		jtfNome.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF:");
		
		jtfCpf = new JTextField();
		jtfCpf.setColumns(10);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o:");
		
		jtfEndereco = new JTextField();
		jtfEndereco.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		
		jtfTelefone = new JTextField();
		jtfTelefone.setColumns(10);
		GroupLayout gl_jpCadastroCliente = new GroupLayout(jpCadastroCliente);
		gl_jpCadastroCliente.setHorizontalGroup(
			gl_jpCadastroCliente.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpCadastroCliente.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_jpCadastroCliente.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblNome)
						.addComponent(jtfNome, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addGroup(gl_jpCadastroCliente.createSequentialGroup()
							.addComponent(lblCpf)
							.addGap(159)
							.addComponent(lblTelefone))
						.addGroup(gl_jpCadastroCliente.createSequentialGroup()
							.addComponent(jtfCpf, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(jtfTelefone))
						.addComponent(lblEndereo)
						.addComponent(jtfEndereco))
					.addContainerGap(227, Short.MAX_VALUE))
		);
		gl_jpCadastroCliente.setVerticalGroup(
			gl_jpCadastroCliente.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpCadastroCliente.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNome)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jtfNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_jpCadastroCliente.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCpf)
						.addComponent(lblTelefone))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_jpCadastroCliente.createParallelGroup(Alignment.BASELINE)
						.addComponent(jtfCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jtfTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblEndereo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jtfEndereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(90, Short.MAX_VALUE))
		);
		jpCadastroCliente.setLayout(gl_jpCadastroCliente);
		getContentPane().setLayout(groupLayout);
		
	}

	public Cliente getClienteParaEdicao() {
		return clienteParaEdicao;
	}

	public void setClienteParaEdicao(Cliente clienteParaEdicao) {
		this.clienteParaEdicao = clienteParaEdicao;
		preencherCamposParaEdicao();
	}
	
	public int getPosicaoParaEdicao() {
		return posicaoParaEdicao;
	}

	public void setPosicaoParaEdicao(int posicaoParaEdicao) {
		this.posicaoParaEdicao = posicaoParaEdicao;
	}

	public void preencherCamposParaEdicao(){
		if (clienteParaEdicao != null){
			jtfNome.setText(clienteParaEdicao.getNome());
			jtfCpf.setText(clienteParaEdicao.getCpf());
			jtfTelefone.setText(clienteParaEdicao.getTelefone());
			jtfEndereco.setText(clienteParaEdicao.getEndereco());
		}
	}
	
}
