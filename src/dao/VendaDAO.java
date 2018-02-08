package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.ItemVenda;
import model.Venda;
import util.ConnectionUtil;

public class VendaDAO {

	private Connection con = ConnectionUtil.getConnection();
	private static VendaDAO vendaDao;
	//Singleton
	public static VendaDAO obterInstancia(){
		if (vendaDao == null){
			vendaDao = new VendaDAO();
		}
		return vendaDao;
	}
	
	public void salvar(Venda venda){
		try{
			String sql = "insert into venda (data, valor_total, id_cliente) values (?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setDate(1, Date.valueOf(venda.getDataEmissao()));
			pstmt.setDouble(2, venda.getValorTotal());
			pstmt.setInt(3, venda.getCliente().getId());
			
			int key = pstmt.executeUpdate();
			int idVenda;
			if (key > 0){
				ResultSet rs = pstmt.getGeneratedKeys();
				rs.next();
				idVenda = rs.getInt(1);
				
				String sqlItem = "insert into item_venda(qtde, preco_item, id_produto, id_venda) "
						+ "values (?, ?, ?, ?)";
				PreparedStatement pstmtItem = con.prepareStatement(sqlItem);
				for(ItemVenda item : venda.getItensVenda()){
					pstmtItem.setInt(1, item.getQtde());
					pstmtItem.setDouble(2, item.getValorTotal());
					pstmtItem.setInt(3, item.getProduto().getId());
					pstmtItem.setInt(4, idVenda);
					pstmtItem.execute();
				}
			}			
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
}
