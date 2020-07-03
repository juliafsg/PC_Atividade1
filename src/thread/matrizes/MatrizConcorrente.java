package thread.matrizes;

public class MatrizConcorrente  extends Thread{
	
	private int[][] matrizA;
	private int[][] matrizB;
	private int[][] matrizC;
	
	static int dimensao;
	
	public MatrizConcorrente(int[][] matrizA, int[][] matrizB, int dimensao) {
		this.matrizA = matrizA;
		this.matrizB = matrizB;
		this.matrizC = new int[dimensao][dimensao];	
		MatrizSequencial.dimensao = dimensao;
	}

	@Override
	public void run () {	
		
	}

}
