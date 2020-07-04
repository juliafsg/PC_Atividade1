package thread.matrizes;

public class MatrizConcorrente  extends Thread{
	
	static int[][] matrizA;
	static int[][] matrizB;
	static int[][] matrizC;
	private int indice;
	static int dimensao;
	
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
	
	public void run () {	
		//System.out.println("\n" + this.getName() + " multiplicando linha: " + linha+ " X coluna: "+ coluna);
		
		int resultado = 0;
		for(int linha=0; linha < dimensao; linha++) {
			for(int coluna=0; coluna < dimensao; coluna++) {
				resultado = MatrizConcorrente.matrizA[linha][this.indice] * MatrizConcorrente.matrizB[this.indice][coluna];
				MatrizConcorrente.matrizC[linha][coluna] += resultado;
			}	
		}

		//System.out.println(resultado);
	}
	
	public void multiplicarMatrizes() {

		for(int indice=0; indice < dimensao; indice++) {
			String nome = "Thread" + indice;
			Thread teste  = new MatrizConcorrente(nome, indice);	
			teste.start();
			//System.out.println(nome);
		}											

		}
	
	
	public int[][] getMatrizC(){
		return matrizC;
	}
	
}
