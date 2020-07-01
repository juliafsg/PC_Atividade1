package thread.start;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
public class MainThread {
 
	public static void main(String[] args) throws IOException {	
	
		//Leitura dos Argumentos
		int dimensao = Integer.parseInt(args[0]);
		String metodo = args[1];
		
		System.out.println("Leitura dos Argumentos...");
		System.out.println("Dimensão:" + dimensao + "x" + dimensao);
		
		if(metodo.equals("S")) {
			System.out.println("Metodo: Sequencial \n");			
		} else {
			System.out.println("Metodo: Concorrente \n");
		}
		
		//Manipulação dos Arquivos
		System.out.println("Leitura dos Arquivos...");
		String diretorio_atual = System.getProperty("user.dir");
		Path caminho_A = Paths.get(diretorio_atual+"/matrizes/A" + dimensao + "x" + dimensao + ".txt");
		Path caminho_B = Paths.get(diretorio_atual+"/matrizes/B" + dimensao + "x" + dimensao + ".txt");		
			
		List<String> arquivo_A = Files.readAllLines(caminho_A);
		System.out.println("\n Matriz A");
	    for (String linha : arquivo_A) {
	        System.out.println(linha);
	   }
	        
	    List<String> arquivo_B = Files.readAllLines(caminho_B);
		System.out.println("\n Matriz B");
	    for (String linha : arquivo_B) {
	        System.out.println(linha);
	    }    
		 
	}
	
}
