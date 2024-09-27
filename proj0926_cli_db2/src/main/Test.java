package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import dbquery.OrdersDao;
import exception.CantParseStringToNumberException;

public class Test {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		OrdersDao dao = ctx.getBean(OrdersDao.class);
		
		method1();
		System.out.println("아직함");
	}
	public static void method1() throws RuntimeException{
		method2();
	}
	public static void method2() throws RuntimeException{
		throw new CantParseStringToNumberException();
	}
}
