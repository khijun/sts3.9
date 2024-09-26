package test;

import java.lang.reflect.Method;

public class MyMain {
	public static void main(String[] args) {
		Method[] methodList = MyObject.class.getMethods();
		
		for(Method method : methodList) {
			if(method.isAnnotationPresent(MyAnno.class)) {
				MyAnno annotation = method.getDeclaredAnnotation(MyAnno.class);
				String value = annotation.value();
				System.out.println(method.getName() + ":" + value);
				System.out.println(annotation.number());
			}
		}
	}
}
