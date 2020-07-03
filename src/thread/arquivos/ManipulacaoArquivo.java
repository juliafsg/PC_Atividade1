package thread.arquivos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class ManipulacaoArquivo {
	static List<Integer> dimensoes_possiveis = Arrays.asList(4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048);
	static Integer dimensaoMatriz = 0;
	static Path caminho; 
	public static int leituraDimensaoMatriz(String dimensao) {
		try {
			dimensaoMatriz = Integer.parseInt(dimensao);
		} catch (Exception e) {
			throw new IllegalArgumentException("Não foi possivel ler a dimensão da matriz");
		}

		if (dimensoes_possiveis.indexOf(dimensaoMatriz) == -1) {
			throw new IllegalArgumentException("Não foi possivel criar matriz com essa dimensão");
		}
		
		return dimensaoMatriz;
	}
	
	public static void leituraMetodoMatriz(String metodo) {
		System.out.println("Leitura dos Argumentos...");
		System.out.println("Dimensão:" + dimensaoMatriz + "x" + dimensaoMatriz);

		if(metodo.equals("S")) {
			System.out.println("Metodo: Sequencial");			
		} else if (metodo.equals("C")) {
			System.out.println("Metodo: Concorrente");
		} else {
			throw new IllegalArgumentException("Metodo escolhido incorreto");
		}
	}
	
	public static void buscaCaminhoMatriz(String nomeMatriz) {
		System.out.println("\n Leitura do Arquivo " + nomeMatriz + "...");
		String diretorio_atual = System.getProperty("user.dir");
		caminho = Paths.get(diretorio_atual+"/matrizes/"+ nomeMatriz + dimensaoMatriz + "x" + dimensaoMatriz + ".txt");	
	}
	
	public static int[][] leituraArquivo(String nomeMatriz) throws IOException {
		int[][] matriz = new int[dimensaoMatriz][dimensaoMatriz];
		buscaCaminhoMatriz(nomeMatriz);
		List<String> arquivo = Files.readAllLines(caminho);
		int i = 0, j = 0;
		arquivo.remove(0);
		for (String linha : arquivo) {
			for (String numero: linha.split(" ")) {
				try {
					matriz[i][j] = Integer.parseInt(numero);
				} catch (Exception e) {
					System.out.println("Não foi possivel converter a matriz "+ nomeMatriz +" para números");
				}
				j++;
			}
			j = 0;
			i++;
		}
		imprimirMatriz(matriz, nomeMatriz);
		return matriz;
	}
	
	public static void imprimirMatriz(int[][] matriz, String nomeMatriz) {
		System.out.println("\n Imprimindo Matriz " + nomeMatriz + "...");
		
		for (int linha = 0; linha < matriz.length; linha++)  { 
			 System.out.print("\n");
		       for (int coluna = 0; coluna < matriz[0].length; coluna++)     { 
		           System.out.print(matriz[linha][coluna] + " "); 
		       }  
		     }  
		 System.out.print("\n");
	}
	
	public static void gerarArquivoMatriz(int[][] matriz, String nomeMatriz) throws IOException {
		FileWriter arquivo;
		String diretorio_atual = System.getProperty("user.dir");
		arquivo = new FileWriter(new File(diretorio_atual + "/matrizes/" + nomeMatriz + dimensaoMatriz +"x"+dimensaoMatriz +".txt"));
		
		String conteudo = Integer.toString(dimensaoMatriz) + " " + Integer.toString(dimensaoMatriz)+"\n";
		
		for (int linha = 0; linha < matriz.length; linha++)  { 
		       for (int coluna = 0; coluna < matriz[0].length; coluna++)  { 
		    	   conteudo = conteudo + matriz[linha][coluna] + " "; 
		       }  
		       conteudo = conteudo + "\n";
		     }  
		 System.out.print("\n");
		
		arquivo.write(conteudo);
		arquivo.close();
		
	}
	
}
