package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import spring.MemberDao;

public class Main2 {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx 
		     = new AnnotationConfigApplicationContext(AppCtx.class);

		MemberDao dbQuery = ctx.getBean(MemberDao.class);
		System.out.println(dbQuery.selectByEmail("aaa@korea.com"));
		ctx.close();
	}
}
