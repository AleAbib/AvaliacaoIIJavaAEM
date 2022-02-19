package questao10.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reacoes")
public class Reacao {

	@Id
	private String nome;
	private String descricao;
	private LocalDate dataCadastro;
	private int status;
	
	public Reacao(){
		
	}

	public Reacao(String nome, String descricao, int status) {
		this.descricao = descricao;
		this.nome = nome;
		this.status += status;
	}
	
	public Reacao(String nome, String descricao, int status, LocalDate dataCadastro) {
		this.descricao = descricao;
		this.nome = nome;
		this.status += status;
		this.dataCadastro = dataCadastro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
