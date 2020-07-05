package thread.matrizes;

public class MatrizSequencial implements Matriz {
	private int[][] matrizA;
	private int[][] matrizB;
	private int[][] matrizC;
	private int dimensao;
	

	public MatrizSequencial(int[][] matrizA, int[][] matrizB, int dimensao) {
		this.matrizA = matrizA;
		this.matrizB = matrizB;
		this.matrizC = new int[dimensao][dimensao];
		this.dimensao = dimensao; 
	}
	
	// Metodo para realizar a multiplicação da matriz de modo sequencial
	public void multiplicarMatrizes() {
		for (int linha=0; linha < this.dimensao; linha++) {
			for (int coluna=0; coluna < this.dimensao; coluna++) {
				
				int soma = 0;
				
				for (int k=0; k <dimensao; k++) {
					soma += matrizA[linha][k] * matrizB[k][coluna];
				}
				this.matrizC[linha][coluna] = soma;	
			}
		}
	}
	
	public int[][] getMatrizC(){
		return matrizC;
	}
}
