package questao9.controller;

import java.sql.Connection;
import java.util.List;

import questao9.dao.ProdutoDAO;
import questao9.factory.ConnectionFactory;
import questao9.model.Produto;

public class ProdutoController {
	
	private ProdutoDAO produtoDAO;
	
	public ProdutoController() {
		Connection connection = new ConnectionFactory().recuperarConexao();
		this.produtoDAO = new ProdutoDAO(connection);
	}

	public void excluir(Integer id) {
		this.produtoDAO.excluir(id);
	}

	public void inserir(Produto produto) {
		this.produtoDAO.inserir(produto);
	}

	public void listar(String palavra) {
		this.produtoDAO.listar(palavra);
	}
	
	public List<Produto> buscarId(Integer id) {
		return this.produtoDAO.buscarId(id);
	}


	public void atualizar(String nome, String descricao, int id, Float desconto, Float valor) {
		this.produtoDAO.atualizar(nome, descricao, id, desconto, valor);
	}
	
}
