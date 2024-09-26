package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import domain.dao.MemberDao;
import domain.repository.MemberRepository;

@Configuration
public class AppCtx {
	
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	@Bean
	public MemberRepository memberRepository() {
		return new MemberRepository(memberDao());
	}
}
