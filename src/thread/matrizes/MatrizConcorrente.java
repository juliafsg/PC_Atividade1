package thread.matrizes;

public class MatrizConcorrente  extends Thread implements Matriz {
	private static int[][] matrizA;
	private static int[][] matrizB;
	private static int[][] matrizC;
	private static int dimensao;
	private int indice; 
	
	public MatrizConcorrente(int[][] matrizA, int[][] matrizB, int dimensao) {
		MatrizConcorrente.matrizA = matrizA;
		MatrizConcorrente.matrizB = matrizB;
		MatrizConcorrente.matrizC = new int[dimensao][dimensao];	
		MatrizConcorrente.dimensao = dimensao;
	}

	public MatrizConcorrente(String nome, int indice) {
		super(nome);
		this.indice= indice;
	}

	// Metodo para rodar a thread com a multiplicação de uma linha da matriz resultante
	public void run () {	
		int resultado = 0;
		for(int linha=0; linha < dimensao; linha++) {
			for(int coluna=0; coluna < dimensao; coluna++) {
				resultado = MatrizConcorrente.matrizA[linha][this.indice] * MatrizConcorrente.matrizB[this.indice][coluna];
				MatrizConcorrente.matrizC[linha][coluna] += resultado;
				
			}	
		}
	}

	// Metodo para a multiplicação de matrizes, de modo concorrente
	public void multiplicarMatrizes() {
		for(int indice=0; indice < dimensao; indice++) {
			String nome = "Thread" + indice;
			Thread threadCalculo  = new MatrizConcorrente(nome, indice);	
			threadCalculo.start();
			
			try {
				threadCalculo.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}											
	}

	public int[][] getMatrizC(){
		return matrizC;
	}

}
