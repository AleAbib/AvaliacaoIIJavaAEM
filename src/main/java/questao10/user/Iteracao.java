package questao10.user;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

import questao10.dao.ReacaoDao;
import questao10.model.Reacao;
import questao10.util.JPAUtil;

public class Iteracao {
	
	private String sentimento;
	private String nome;
	private LocalDate dataCadastro;
	private int status=0;
	private String reacaoDoDia;
	
	Scanner leDados = new Scanner(System.in);
	
	
	EntityManager em = JPAUtil.getEntityManager();
	ReacaoDao reacaoDao = new ReacaoDao(em);
	
	public void menu() {
		
		System.out.println("========= Iniciando Sistema =========");
		System.out.println("Insira seu nome: ");
		nome = leDados.nextLine();
		
		System.out.println("Qual seu sentimento hoje ?");
		sentimento = leDados.nextLine();
		
		reacaoDoDia = formataString(sentimento);
		
		em.getTransaction().begin();

		if(!BuscarNome(nome).isEmpty()) {
			atualizaReacao();
		}else {
			cadastraReacao();
		}
		
		leDados.close();
		em.close();
	}
	
	private String formataString(String sentimento) {
		
		int divertido=0, chateado=0;
		char[] caracteres = sentimento.toCharArray();
		
		for(int i=0, j=1; i < caracteres.length - 2; i++, j++) {
			if(caracteres[i] == ':' && caracteres[j] == '-') {
				if(caracteres[j+1] == ')') {
					divertido++;
				}else if(caracteres[j+1] == '(') {
					chateado++;
				}
			}
		}
		
		if(divertido == chateado) {
			sentimento = "neutro";
		}else if(divertido > chateado) {
			sentimento = "divertido";
			status += 1;
		}else {
			sentimento = "triste";
			status -= 1;
		}
		
		return sentimento;
		
	}
	
	private void cadastraReacao() {
		dataCadastro = LocalDate.now();
		Reacao reacao = new Reacao(nome.toLowerCase(), reacaoDoDia, status, dataCadastro);
		
		reacaoDao.cadastrar(reacao);
		em.getTransaction().commit();
		System.out.println("Sentimento do dia: " + reacaoDoDia);
		System.out.println("Usuário e reação cadastrados!");

	}
	
	private List<Reacao> BuscarNome(String nome) {
		
		return reacaoDao.buscarPorNome(nome);
	}
	
	private void atualizaReacao() {
		Reacao reacao = new Reacao(nome.toLowerCase(), reacaoDoDia, status);
		
		reacaoDao.atualizar(reacao);
		em.getTransaction().commit();
		System.out.println("Sentimento do dia: " + reacaoDoDia);
		System.out.println("Usuário logado e reação atualizada!");
	}
}
