package thread.main;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import thread.arquivos.ManipulacaoArquivo;
public class MainThread {

	public static void main(String[] args) throws IOException {	
		int dimensao = 0;
		try {
			// Leitura das dimensões da matriz
			dimensao = ManipulacaoArquivo.leituraDimensaoMatriz(args[0]);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return;
		}

		// Leitura do argumento de tipo de execução a ser feita
		try {
			String metodo = args[1];
			ManipulacaoArquivo.leituraMetodoMatriz(metodo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
		
		// Criação das matrizes a serem trabalhadas
		int[][] matriz_A = new int[dimensao][dimensao];
		int[][] matriz_B = new int[dimensao][dimensao];
		int[][] matriz_C = new int[dimensao][dimensao];
		
		//Manipulação dos Arquivos
		System.out.println("Leitura dos Arquivos...");
		String diretorio_atual = System.getProperty("user.dir");
		Path caminho_A = Paths.get(diretorio_atual+"/matrizes/A" + dimensao + "x" + dimensao + ".txt");
		Path caminho_B = Paths.get(diretorio_atual+"/matrizes/B" + dimensao + "x" + dimensao + ".txt");		

		List<String> arquivo_A = Files.readAllLines(caminho_A);
		System.out.println("\n Matriz A");
		int i = 0, j = 0;
		arquivo_A.remove(0);
		for (String linha : arquivo_A) {
			System.out.println(linha);
			for (String numero: linha.split(" ")) {
				try {
					matriz_A[i][j] = Integer.parseInt(numero);
				} catch (Exception e) {
					System.out.println("Não foi possivel converter a matriz A para números");
				}
				j++;
			}
			j = 0;
			i++;
		}
		
		i = 0; 
		j = 0;
		List<String> arquivo_B = Files.readAllLines(caminho_B);
		arquivo_B.remove(0);
		System.out.println("\n Matriz B");
		for (String linha : arquivo_B) {
			System.out.println(linha);
			for (String numero: linha.split(" ")) {
				try {
					matriz_B[i][j] = Integer.parseInt(numero);
				} catch (Exception e) {
					System.out.println("Não foi possivel converter a matriz B para números");
				}
				j++;
			}
			j = 0;
			i++;
		}    

	}

}
