package domain.dao;

import java.util.List;

import domain.entity.Member;
import domain.mapper.MemberMapper;
import mybatis.SqlMapConfig;

public class MemberDao {
	private static MemberMapper mm = SqlMapConfig.getSqlMapInstance().openSession(true).getMapper(MemberMapper.class);
	
	public List<Member> selectAll(){
		return mm.selectAll();
	}
}
