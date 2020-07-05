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
	private static String diretorio_atual = System.getProperty("user.dir");
	public static List<Integer> dimensoes_possiveis = Arrays.asList(4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048);
	public static Integer dimensaoMatriz = 0;
	public static Path caminho;

	// Metodo para a leitura da dimensão das matrizes
	public static int leituraDimensaoMatriz(String dimensao) throws IllegalArgumentException {
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

	// Metodo para capturar o metodo que deve ser calculada a multiplicação das matrizes
	public static void leituraMetodoMatriz(String metodo) throws IllegalArgumentException {
		System.out.println(" Leitura dos Argumentos...");

		if(!metodo.equals("S") && !metodo.equals("C")) {
			throw new IllegalArgumentException("Metodo escolhido incorreto");
		}
	}

	// Busca do caminho para realizar a leitura dos arquivos das matrizes
	public static void buscaCaminhoMatriz(String nomeMatriz) {
		System.out.println("\n Leitura do Arquivo " + nomeMatriz + "...");
		caminho = Paths.get(ManipulacaoArquivo.diretorio_atual+"/matrizes/testes/"+ nomeMatriz + dimensaoMatriz + "x" + dimensaoMatriz + ".txt");	
	}
	
	// Metodo para a leitura das matrizes no arquivo
	public static int[][] leituraArquivo(String nomeMatriz) throws Exception {
		int[][] matriz = new int[dimensaoMatriz][dimensaoMatriz];
		buscaCaminhoMatriz(nomeMatriz);
		List<String> arquivo = Files.readAllLines(caminho);
		arquivo.remove(0);
		
		int i = 0, j = 0;
		for (String linha : arquivo) {
			for (String coluna: linha.split(" ")) {
				try {
					matriz[i][j] = Integer.parseInt(coluna);
				} catch (Exception e) {
					throw new Exception("Não foi possivel converter a matriz "+ nomeMatriz +" para números");
				}
				j++;
			}
			j = 0;
			i++;
		}
		return matriz;
	}

	// Metodo de impressão das matrizes
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

	// Metodo para gerar um arquivo .txt com as matrizes
	public static void gerarArquivoMatriz(int[][] matriz, String metodo) throws IOException {
		FileWriter arquivo;
		try {
			if (metodo.equals("C")) {
				arquivo = new FileWriter(new File(ManipulacaoArquivo.diretorio_atual + "/matrizes/resultados/concorrente/" + 
						"C" + dimensaoMatriz +"x"+dimensaoMatriz +".txt"));
			} else {
				arquivo = new FileWriter(new File(ManipulacaoArquivo.diretorio_atual + "/matrizes/resultados/sequencial/" + 
						"C" + dimensaoMatriz +"x"+dimensaoMatriz +".txt"));
			}
		} catch (IOException e) {
			throw new IOException("Não foi possivel criar os arquivos resultados");
		}
		
		String conteudo = Integer.toString(dimensaoMatriz) + " " + Integer.toString(dimensaoMatriz)+"\n";
		
		for (int linha = 0; linha < matriz.length; linha++)  { 
	           for (int coluna = 0; coluna < matriz[0].length; coluna++)  { 
	             conteudo = conteudo + matriz[linha][coluna] + " "; 
	           }  
	           
	           arquivo.write(conteudo);
	           
	           conteudo = "\n";
	         }  
	    
	    arquivo.close();
	    
	    System.out.println("\n Arquivo criado com sucesso.");
	    

	}

	// Metodo para gerar um arquivo .csv com os tempos de execuções das matrizes
	public static void gerarArquivoCSVTempos(String nomeMatriz, String[] tempos) throws IOException {
		FileWriter arquivoCSV;
		try {
			arquivoCSV = new FileWriter(new File(ManipulacaoArquivo.diretorio_atual + "/matrizes/resultados/tempos/" + 
					nomeMatriz + dimensaoMatriz + "x" + dimensaoMatriz + ".csv"));
		} catch (IOException e) {
			throw new IOException("Não foi possivel criar os arquivos resultados");
		}

		String conteudo = Integer.toString(dimensaoMatriz) + " " + Integer.toString(dimensaoMatriz) + " ";
		for(String tempo : tempos) {
			conteudo += tempo + " ";
		}
		conteudo += "\n"; 
		
		arquivoCSV.write(conteudo);
		arquivoCSV.close();
	}

}
