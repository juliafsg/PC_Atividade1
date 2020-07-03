package thread.matrizes;

public class MatrizSequencial {
	
	private int[][] matrizA;
	private int[][] matrizB;
	private int[][] matrizC;
	static int dimensao;
	
	public MatrizSequencial(int[][] matrizA, int[][] matrizB, int dimensao) {
		
		this.matrizA = matrizA;
		this.matrizB = matrizB;
		this.matrizC = new int[dimensao][dimensao];	
		MatrizSequencial.dimensao = dimensao;
	}
	
	public int[][] multiplicarMatrizes() {
				
		for (int linha=0; linha < dimensao; linha++) {
			for (int coluna=0; coluna < dimensao; coluna++) {
				
				int soma = 0;
				
				for (int k=0; k <dimensao; k++) {
					soma = soma + matrizA[linha][k] * matrizB[k][coluna];
				}
				this.matrizC[linha][coluna] = soma;	
			}
		}
		return matrizC;
	}
}
