package questao9.user;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import questao9.Exception.InvalidLetterException;
import questao9.controller.ProdutoController;
import questao9.model.Produto;

public class Iteracao {

	private ProdutoController produtoController;
	private String[] nomesString = { "Limão", "mamão", "melão", "banana", "Monitor", "Mouse", "Teclado", "Caneta",
			"L�pis", "Copo", "Garrafa", "Mesa" };
	private String[] descricoesString = { "frutas", "frutas", "frutas", "frutas", "eletronicos", "eletronicos",
			"eletronicos", "utensilios", "utensilios", "utensilios", "utensilios", "utensilios" };
	Scanner leDados = new Scanner(System.in);
	private List<String> nomes = new ArrayList<>();
	private List<String> descricoes = new ArrayList<>();

	public Iteracao() {
		produtoController = new ProdutoController();
		preencherArrays();
	}

	public void menu() {

		int opcao = 10;
		int id;

		while (opcao != 0) {
			System.out.println("========= XPTO System ========= \n" + "Digite o opção desejada: \n"
					+ "1 - para INSERIR uma nova oferta\n" + "2 - para ATUALIZAR uma oferta\n"
					+ "3 - para DELETAR uma oferta\n" + "4 - para LISTAR os produtos que contém: \n" + "0 - para SAIR");
			opcao = 0;
			try{
				opcao = leDados.nextInt();
			} catch (Exception ex) {
				throw new InvalidLetterException("Variável só recebe números como entrada!");
			}
			
			switch (opcao) {
			case 1:
				try{
					System.out.println("Digite o id do produto: ");
					id = leDados.nextInt();
				} catch (Exception ex) {
					throw new InvalidLetterException("Variável só recebe números como entrada!");
				}
				if (!buscarId(id).isEmpty()) {
					System.err.println("Usuário já existe.");
				}else {
					inserir(id);
					inserirAleatorio();
				}
				break;
			case 2:
				try{
					System.out.println("Digite o id do produto: ");
					id = leDados.nextInt();
				} catch (Exception ex) {
					throw new InvalidLetterException("Variável só recebe números como entrada!");
				}
				if (buscarId(id).isEmpty()) {
					inserir(id);
				}
				atualizar(id);
				break;
			case 3:
				try{
					System.out.println("Digite o id do produto: ");
					id = leDados.nextInt();
				} catch (Exception ex) {
					throw new InvalidLetterException("Variável só recebe números como entrada!");
				}
				if (buscarId(id).isEmpty()) {
					System.err.println("Usuário não existe no banco.");
				}else {
					excluir(id);
				}
				break;
			case 4:
				listar();
				break;
			case 0:
				leDados.close();
				break;
			default:
				System.out.println("Opção inválida!\n");
				break;
			}
		}
	}

	private void preencherArrays() {
		for (int i = 0; i < nomesString.length; i++) {
			nomes.add(nomesString[i]);
			descricoes.add(descricoesString[i]);
		}
	}

	private void inserirAleatorio() {

		Date data;
		Random gerador = new Random();

		data = data();

		for (int i = 0; i < 3; i++) {
			Produto produto = new Produto(gerador.nextInt(200), nomes.get(0), descricoes.get(0), gerador.nextFloat(),
					gerador.nextFloat() * 100, data);
			this.produtoController.inserir(produto);
			nomes.remove(0);
			descricoes.remove(0);
		}
	}

	private void inserir(int id) {

		String nome;
		String descricao;
		float desconto, valor;
		Date data;
		
		try {
			leDados.nextLine();
			
			System.out.println("Digite o nome: ");
			nome = leDados.nextLine();

			System.out.println("Digite a descrição: ");
			descricao = leDados.nextLine();

			System.out.println("Digite o valor do produto: ");
			valor = leDados.nextFloat();

			System.out.println("Digite o desconto: ");
			desconto = leDados.nextFloat();
			
			data = data();

			Produto produto = new Produto(id, nome, descricao, desconto, valor, data);

			this.produtoController.inserir(produto);

			System.out.println("Produto Salvo com sucesso!\n");
		} catch (Exception ex) {
			System.err.println("Erro de inicialização de variáveis");
			leDados.nextLine();
			menu();
		}
	}

	private void atualizar(int id) {

		String nome;
		String descricao;
		float desconto, valor;

		try {
			leDados.nextLine();

			System.out.println("Digite o nome: ");
			nome = leDados.nextLine();

			System.out.println("Digite a descrição: ");
			descricao = leDados.nextLine();

			System.out.println("Digite o valor do produto: ");
			valor = leDados.nextFloat();

			System.out.println("Digite o novo desconto: ");
			desconto = leDados.nextFloat();

			this.produtoController.atualizar(nome, descricao, id, desconto, valor);

			System.out.println("Produto Alterado com sucesso!\n");
		} catch (Exception ex) {
			System.err.println("Erro de inicialização de variáveis");
			leDados.nextLine();
			menu();
		}
	}

	private void excluir(int id) {

		this.produtoController.excluir(id);

		System.out.println("Produto Excluído com sucesso!\n");
	}

	private void listar() {

		String palavra;

		System.out.println("Que produto deseja procurar ?");

		leDados.nextLine();

		palavra = "%" + leDados.nextLine() + "%";
		String[] stringPalavras = palavra.split(" ");

		for (int i = 0; i < stringPalavras.length; i++) {
			this.produtoController.listar(stringPalavras[i]);
		}

	}

	public List<Produto> buscarId(Integer id) {
		return this.produtoController.buscarId(id);
	}

	private Date data() {
		java.util.Date data = new java.util.Date();
		java.sql.Date dataSQL = new java.sql.Date(data.getTime());
		return dataSQL;
	}

}
