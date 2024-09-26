package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import domain.repository.MemberRepository;

public class MainforMemberTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		
		MemberRepository memRep = ctx.getBean(MemberRepository.class);
		memRep.printAll();
		ctx.close();
	}

}
