package thread.arquivos;

import java.util.Arrays;
import java.util.List;

public class ManipulacaoArquivo {
	static List<Integer> dimensoes_possiveis = Arrays.asList(4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048);
	static Integer dimensaoMatriz = 0;

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
			System.out.println("Metodo: Sequencial \n");			
		} else if (metodo.equals("C")) {
			System.out.println("Metodo: Concorrente \n");
		} else {
			throw new IllegalArgumentException("Metodo escolhido incorreto");
		}
	}
}
