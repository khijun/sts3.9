package domain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import domain.entity.Member;

@Mapper
public interface MemberMapper {
	@Select("select * from member")
	List<Member> selectAll();
}
