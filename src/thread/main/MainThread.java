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

				int dimensao = 0;
		//		String metodo;
		// Criação das matrizes a serem trabalhadas


		String[] tempos = new String[20];
		
		List<String> dimensoes_possiveis = Arrays.asList("4", "8", "16", "32", "64", "128", "256", "512", "1024", "2048");
		
		String[] metodos = {"S", "C"};
		
		String nomeMetodosCompleto = "";
		
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
//					metodo = args[1];
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

				for (int vezes = 0; vezes < 20; vezes++) {
					Matriz matriz = new MatrizSequencial(matrizA, matrizB, dimensao);
					
					if(metodo.equals("S")) {
						// Mutiplicação Sequencial
						matriz = new MatrizSequencial(matrizA, matrizB, dimensao);
						nomeMetodosCompleto = "sequencial";
					} else if(metodo.equals("C")) {
						// Mutiplicação Concorrente
						matriz = new MatrizConcorrente(matrizA, matrizB, dimensao);
						nomeMetodosCompleto = "concorrente";
					}
					
					long tempoInicial = System.currentTimeMillis();
					matriz.multiplicarMatrizes();
					long tempoFinal = System.currentTimeMillis() - tempoInicial;
					
					matrizC = matriz.getMatrizC();
//					System.out.println("\n O operação executou em: "+ tempoFinal+" ms");
					tempos[vezes] = Long.toString(tempoFinal);
				}
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
				
				System.out.println("Finalizado metodo " + nomeMetodosCompleto 
						+ " de dimensão " + dimensao + "x" + dimensao);
			}
		}

	}

}
