package questao10.dao;

import java.util.List;

import javax.persistence.EntityManager;

import questao10.model.Reacao;


public class ReacaoDao {

	private EntityManager em;

	public ReacaoDao(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Reacao reacao) {
		this.em.persist(reacao);
	}

	public void atualizar(Reacao reacao) {
		this.em.merge(reacao);
	}

	public List<Reacao> buscarPorNome(String nome){
		String jpql = "SELECT r FROM Reacao r WHERE r.nome = :nome";
		return em.createQuery(jpql, Reacao.class).setParameter("nome", nome).getResultList();
	}
}
