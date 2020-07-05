package thread.main;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import thread.arquivos.ManipulacaoArquivo;
import thread.matrizes.Matriz;
import thread.matrizes.MatrizConcorrente;
import thread.matrizes.MatrizSequencial;

public class MainExperimento {
	
	public static void main(String[] args) throws IOException {	

		int dimensao = 0;

		String[] tempos = new String[20];

		List<String> dimensoes_possiveis = Arrays.asList("4", "8", "16", "32", "64", "128", "256", "512","1024", "2048");

		String[] metodos = {"S"}; //S para Sequencial e C para Concorrente

		String nomeMetodosCompleto = "";
		
		System.out.println("\n-- Início da Execução -- \n");

		for (String dimensaoString: dimensoes_possiveis) {
			int[][] matrizA = new int[Integer.parseInt(dimensaoString)][Integer.parseInt(dimensaoString)];
			int[][] matrizB = new int[Integer.parseInt(dimensaoString)][Integer.parseInt(dimensaoString)];
			int[][] matrizC = new int[Integer.parseInt(dimensaoString)][Integer.parseInt(dimensaoString)];	

			// Leitura das dimensões da matriz
			try {
				dimensao = ManipulacaoArquivo.leituraDimensaoMatriz(dimensaoString);
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				return;
			}

			for(String metodo: metodos) {

				// Leitura do tipo de execução 
				try {
					ManipulacaoArquivo.leituraMetodoMatriz(metodo);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					return;
				}

				// Leitura dos arquivos das matrizes
				try {
					matrizA = ManipulacaoArquivo.leituraArquivo("A");
					matrizB = ManipulacaoArquivo.leituraArquivo("B");
				} catch (Exception e) {
					System.out.println(e.getMessage());
					return;
				}	

				// Multiplicação das Matrizes
				for (int vezes = 0; vezes < 1; vezes++) {
					Matriz matriz = new MatrizSequencial(matrizA, matrizB, dimensao);

					if(metodo.equals("S")) {
						//Sequencial
						matriz = new MatrizSequencial(matrizA, matrizB, dimensao);
						nomeMetodosCompleto = "sequencial";
					} else if(metodo.equals("C")) {
						//Concorrente
						matriz = new MatrizConcorrente(matrizA, matrizB, dimensao);
						nomeMetodosCompleto = "concorrente";
					}

					long tempoInicial = System.currentTimeMillis();
					matriz.multiplicarMatrizes();
					long tempoFinal = System.currentTimeMillis() - tempoInicial;

					matrizC = matriz.getMatrizC();
					tempos[vezes] = Long.toString(tempoFinal);
				}

				// Gerar .csv com os tempos
				System.out.println("\n Armazenando tempos...");
				try {
					ManipulacaoArquivo.gerarArquivoCSVTempos("matriz"+ metodo, tempos);
					tempos = new String[20];
				} catch (Exception e) {
					System.out.println(e.getMessage());
					return;
				}

				// Gerar .txt com resultado da multiplicação
				try {
					ManipulacaoArquivo.gerarArquivoMatriz(matrizC, metodo);	
				}
				catch (Exception e){
					System.out.println(e.getMessage());
					return;
				}

				System.out.println("\n Finalizado metodo " + nomeMetodosCompleto 
						+ " de dimensão " + dimensao + "x" + dimensao + "!\n");
			}
		}
		System.out.println("\n-- Fim da Execução -- \n");
	}	
}

