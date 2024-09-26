package test;


public class MyObject {
	@MyAnno(number=4)
	public void test1() {
		System.out.println("This is testMethod1");
	}
	
	@MyAnno(value="My new Annotation")
	public void test2() {
		System.out.println("This is test2");
	}
}
