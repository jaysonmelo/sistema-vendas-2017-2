package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import controller.IProdutoController;
import controller.impl.ProdutoController;
import model.Produto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastrarProdutoUI extends JInternalFrame {
	private JTextField jtfDescricao;
	private JTextField jtfPrecoUnitario;
	private IProdutoController produtoControl = new ProdutoController();
	private Produto produtoParaEdicao;
	private int posicaoParaEdicao;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarProdutoUI frame = new CadastrarProdutoUI();
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
	public CadastrarProdutoUI() {
		setClosable(true);
		setTitle("Cadastro de Produtos");
		setBounds(100, 100, 468, 217);

		JPanel jpCadastroProdutos = new JPanel();
		jpCadastroProdutos.setBorder(new TitledBorder(null, "Dados do Produto", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (produtoParaEdicao == null){
					Produto produto = new Produto();
					produto.setDescricao(jtfDescricao.getText());
					produto.setPrecoUnitario(Double.parseDouble(jtfPrecoUnitario.getText()));

					produtoControl.salvar(produto);

					JOptionPane.showMessageDialog(null, "Produto salvo com sucesso");
				} else {
					produtoParaEdicao.setDescricao(jtfDescricao.getText());
					produtoParaEdicao.setPrecoUnitario(Double.parseDouble(jtfPrecoUnitario.getText()));
					
					produtoControl.editar(produtoParaEdicao, posicaoParaEdicao);
					
					JOptionPane.showMessageDialog(null, "Produto editado com sucesso");
				}
			}
		});
		btnSalvar.setIcon(new ImageIcon(CadastrarProdutoUI.class.getResource("/img/save.png")));

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setIcon(new ImageIcon(CadastrarProdutoUI.class.getResource("/img/cancel.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(jpCadastroProdutos, GroupLayout.PREFERRED_SIZE, 427, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(btnSalvar)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnCancelar)))
						.addContainerGap(175, Short.MAX_VALUE))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jpCadastroProdutos, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnSalvar)
								.addComponent(btnCancelar))
						.addContainerGap(165, Short.MAX_VALUE))
				);

		JLabel lblDescricao = new JLabel("Descri\u00E7\u00E3o:");

		jtfDescricao = new JTextField();
		jtfDescricao.setColumns(10);

		JLabel lblPreoUnitrior = new JLabel("Pre\u00E7o Unit\u00E1rio (R$):");

		jtfPrecoUnitario = new JTextField();
		jtfPrecoUnitario.setColumns(10);
		GroupLayout gl_jpCadastroProdutos = new GroupLayout(jpCadastroProdutos);
		gl_jpCadastroProdutos.setHorizontalGroup(
				gl_jpCadastroProdutos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpCadastroProdutos.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_jpCadastroProdutos.createParallelGroup(Alignment.LEADING)
								.addComponent(jtfDescricao, GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
								.addComponent(lblDescricao)
								.addComponent(lblPreoUnitrior)
								.addComponent(jtfPrecoUnitario, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))
						.addContainerGap())
				);
		gl_jpCadastroProdutos.setVerticalGroup(
				gl_jpCadastroProdutos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpCadastroProdutos.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblDescricao)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(jtfDescricao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(lblPreoUnitrior)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(jtfPrecoUnitario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(54, Short.MAX_VALUE))
				);
		jpCadastroProdutos.setLayout(gl_jpCadastroProdutos);
		getContentPane().setLayout(groupLayout);

	}

	public Produto getProdutoParaEdicao() {
		return produtoParaEdicao;
	}

	public void setProdutoParaEdicao(Produto produtoParaEdicao) {
		this.produtoParaEdicao = produtoParaEdicao;
		preencherCamposParaEdicao();
	}

	public int getPosicaoParaEdicao() {
		return posicaoParaEdicao;
	}

	public void setPosicaoParaEdicao(int posicaoParaEdicao) {
		this.posicaoParaEdicao = posicaoParaEdicao;
	}

	public void preencherCamposParaEdicao(){
		if(produtoParaEdicao != null){
			jtfDescricao.setText(produtoParaEdicao.getDescricao());
			jtfPrecoUnitario.setText(produtoParaEdicao.getPrecoUnitario().toString());
		}
	}
}
