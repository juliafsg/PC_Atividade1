package thread.start;

public class Thread1 extends Thread {

	public Thread1 (String name) {
		super(name);
	}
	
	@Override
	public void run () {
		System.out.println("Hi my name is "+this.getName());
	}
}
