package thread.main;
import java.io.IOException;

import java.util.Arrays;
import java.util.List;

import thread.arquivos.ManipulacaoArquivo;
import thread.matrizes.Matriz;
import thread.matrizes.MatrizConcorrente;
import thread.matrizes.MatrizSequencial;

public class MainThread {

	public static void main(String[] args) throws IOException {	
		
		// Declaração das variáveis 
		int dimensao = 0;
		String metodo;
		
		System.out.println("\n-- Início da Execução -- \n");
		
		// Leitura das dimensões da matriz
		try {
			dimensao = ManipulacaoArquivo.leituraDimensaoMatriz(args[0]);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return;
		}

		// Leitura do tipo de execução 
		try {
			metodo = args[1];
			ManipulacaoArquivo.leituraMetodoMatriz(metodo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
	
		// Criação das matrizes
		int[][] matrizA = new int[dimensao][dimensao];
		int[][] matrizB = new int[dimensao][dimensao];
		int[][] matrizC = new int[dimensao][dimensao];

		// Leitura dos arquivos das matrizes
		try {
			matrizA = ManipulacaoArquivo.leituraArquivo("A");
			matrizB = ManipulacaoArquivo.leituraArquivo("B");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}	

		// Instanciando as classes
		Matriz matriz = new MatrizSequencial(matrizA, matrizB, dimensao);

		if(metodo.equals("S")) {
			// Classe Sequencial
			matriz = new MatrizSequencial(matrizA, matrizB, dimensao);
			
		} else if(metodo.equals("C")) {
			// Classe Concorrente
			matriz = new MatrizConcorrente(matrizA, matrizB, dimensao);
		}

		// Multiplicação das matrizes
		long tempoInicial = System.currentTimeMillis();
		matriz.multiplicarMatrizes();
		long tempoFinal = System.currentTimeMillis() - tempoInicial;
		
		System.out.println("\n A operação executou em: "+ tempoFinal+" ms");
		
		matrizC = matriz.getMatrizC();

		// Gerar .txt com resultado da multiplicação
		try {
			ManipulacaoArquivo.gerarArquivoMatriz(matrizC, metodo);	
		}
		catch (Exception e){
			System.out.println(e.getMessage());
			return;
		}

		System.out.println("\n-- Fim da Execução -- \n");
		

	}

}
