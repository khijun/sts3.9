package chap02;

public class Chap02 {
	public static void main(String[] args) {
		Greeter greeter = new Greeter();
		greeter.setFormat("Hello %s");
		System.out.println(greeter.greet("world"));
	}
}
