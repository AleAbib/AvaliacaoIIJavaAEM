package questao9.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import questao9.model.Produto;

public class ProdutoDAO {

	private Connection connection;

	public ProdutoDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void inserir(Produto produto) {
		
		try{
			String sql = "INSERT INTO PRODUTO (ID, NOME, DESCRICAO, DESCONTO, VALOR, DATA_ENTRADA) VALUES (?, ?, ?, ?, ?, ?)";
			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				pstm.setInt(1, produto.getId());
				pstm.setString(2, produto.getNome());
				pstm.setString(3, produto.getDescricao());
				pstm.setFloat(4, produto.getDesconto());
				pstm.setFloat(5, produto.getValor());
				pstm.setDate(6, produto.getData_entrada());
				
				pstm.execute();

				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						produto.setId(rst.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Produto> buscarId(Integer id) {
		try{
			List<Produto> produtos = new ArrayList<Produto>();

			try (PreparedStatement pstm = connection.prepareStatement("SELECT * FROM PRODUTO WHERE ID = ?")) {
				pstm.setInt(1, id);
				pstm.execute();

				trasformarResultSetEmProduto(produtos, pstm);
			}
			return produtos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void excluir(Integer id) {
		try (PreparedStatement stm = connection.prepareStatement("DELETE FROM PRODUTO WHERE ID = ?")) {
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void atualizar(String nome, String descricao, int id, Float desconto, Float valor) {
		try (PreparedStatement stm = connection
				.prepareStatement("UPDATE PRODUTO P SET P.NOME = ?, P.DESCRICAO = ?, P.DESCONTO = ?, P.VALOR = ? WHERE ID = ?")) {
			stm.setString(1, nome);
			stm.setString(2, descricao);
			stm.setFloat(3, desconto);
			stm.setFloat(4, valor);
			stm.setInt(5, id);
			stm.execute();
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void listar(String palavra) {
		try{
			List<Produto> produtos = new ArrayList<Produto>();

			try (PreparedStatement pstm = connection.prepareStatement("SELECT * FROM PRODUTO WHERE DESCRICAO LIKE ?")) {
				pstm.setString(1, palavra);
				pstm.execute();

				trasformarResultSetEmPrint(produtos, pstm);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void trasformarResultSetEmProduto(List<Produto> produtos, PreparedStatement pstm) {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				Produto produto = new Produto(rst.getInt(1), rst.getString(2), rst.getString(3));

				produtos.add(produto);
			} 
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void trasformarResultSetEmPrint(List<Produto> produtos, PreparedStatement pstm) {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				Integer id = rst.getInt("id");
				String nome =  rst.getString("NOME");
				String descricao = rst.getString("descricao");
				
				System.out.println("ID: " + id + " - " + nome + " - " + descricao);
			} 
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
