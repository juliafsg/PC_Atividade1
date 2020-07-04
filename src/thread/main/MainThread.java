package thread.main;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import thread.arquivos.ManipulacaoArquivo;
import thread.matrizes.MatrizConcorrente;
import thread.matrizes.MatrizSequencial;
public class MainThread {

	public static void main(String[] args) throws IOException {	
		
		int dimensao = 0;
		String metodo;
		// Criação das matrizes a serem trabalhadas
		int[][] matrizA = new int[dimensao][dimensao];
		int[][] matrizB = new int[dimensao][dimensao];
		int[][] matrizC = new int[dimensao][dimensao];
		
		
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
		
		// Leitura do arquivo
		try {
			matrizA = ManipulacaoArquivo.leituraArquivo("A");
			matrizB = ManipulacaoArquivo.leituraArquivo("B");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}	
		
		// Mutiplicação Sequencial
		if(metodo.equals("S")) {
			MatrizSequencial sequencial = new MatrizSequencial(matrizA, matrizB, dimensao);			
			long tempoInicial = System.currentTimeMillis();
			matrizC = sequencial.multiplicarMatrizes();
			long tempoFinal = System.currentTimeMillis() - tempoInicial;
			System.out.println("\n O método Sequencial executou em: "+ tempoFinal+" ms");
			//ManipulacaoArquivo.imprimirMatriz(matrizC, "C");
		}
		
		// Mutiplicação Concorrente
		if(metodo.equals("C")) {
			MatrizConcorrente concorrente = new MatrizConcorrente(matrizA, matrizB, dimensao);	
			long tempoInicial = System.currentTimeMillis();
			concorrente.multiplicarMatrizes();
			long tempoFinal = System.currentTimeMillis() - tempoInicial;
			System.out.println("\n O método Concorrente executou em: "+ tempoFinal+" ms");
			matrizC = concorrente.getMatrizC();
			//ManipulacaoArquivo.imprimirMatriz(matrizC, "C");
		}
		
		// Gerar txt com resultado da multiplicação
		try {
			//ManipulacaoArquivo.gerarArquivoMatriz(matrizC, "C");	
		}
		catch (Exception e){
			System.out.println(e.getMessage());
			return;
		}

	}

}
