package domain.repository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import domain.dao.MemberDao;
import domain.entity.Member;

public class MemberRepository {
	
	private static MemberDao memberDao;
	
	public MemberRepository(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public void printAll(){
		List<Member> list = memberDao.selectAll();
		list.forEach(m -> System.out.println(m));
	}

	private Map<Long, Member> map = new HashMap<>();
	
	public void save(Member member) {
		
	}
	public Member findOne(Long id) {
		return map.get(id);
	}
	public Collection<Member> findAll() {
		return map.values();
	}
	public List<Member> findByName(String name) {
		return Collections.emptyList();
	}

}
