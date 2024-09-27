package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dbquery.MemberDao;
import domain.entity.Member;

@Component
public class MemberService {
	
	@Autowired
	private MemberDao memberDao;
	
	public void addMember(String[] arg) {	// 멤버 등록		
		memberDao.addMember(new Member(-1, arg[1], arg[2], arg[3], arg[4]));
		System.out.println("멤버 등록 완료!");
	}
	
	public void printAllMember() {
		memberDao.getAllMember().forEach(m->System.out.println(m));
		System.out.println("멤버 조회 완료");
	}
	
	
}
