package questao9.model;

import java.sql.Date;

public class Produto {

	private Integer id;
	private String nome;
	private String descricao;
	private Float desconto;
	private Float valor;
	private Date data_entrada;
	
	public Produto(int id, String nome, String descricao, Float desconto, Float valor, Date data) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.desconto = desconto;
		this.valor = valor;
		this.data_entrada = data;
	}
	
	public Produto(int id, String nome, String descricao) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	
	public Float getDesconto() {
		return desconto;
	}
	
	public Float getValor() {
		return valor;
	}
	public Date getData_entrada() {
		return data_entrada;
	}
	
	
}
