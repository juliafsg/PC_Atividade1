package thread.matrizes;

public class MatrizConcorrente extends Thread{
	public MatrizConcorrente(String nome) {
		super(nome);
	}
	
	@Override
	public void run () {
		System.out.println("Hi my name is " + this.getName());
	}
}
