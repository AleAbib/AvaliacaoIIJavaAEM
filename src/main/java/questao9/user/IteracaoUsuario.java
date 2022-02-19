package questao9.user;

import java.util.InputMismatchException;

public class IteracaoUsuario {
	
	public static void main(String[] args) {
		
		try{
			Iteracao iteracaoUsuario = new Iteracao();
			iteracaoUsuario.menu();
		}catch (Exception ex) {
			System.out.println("Entrada inválida");
			throw new InputMismatchException();
		}
	}
}
