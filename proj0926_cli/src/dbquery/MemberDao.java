package dbquery;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import domain.entity.Member;

@Component
public class MemberDao {

	private JdbcTemplate jdbcTemplate;

	public MemberDao(@Autowired DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public int addMember(Member member) {
		System.out.println(member);
		String sql = "insert into member(name, city, street, zipcode) values"
				+ "(?, ?, ?, ?)";
		return jdbcTemplate.update(sql,member.getName(), member.getCity(), member.getStreet(), member.getZipcode());
	}
	
	public List<Member> getAllMember(){
		String sql = "select * from member";
		return jdbcTemplate.query(sql, (rs, rowNum)->{
			Member result = new Member(rs.getLong("id"), rs.getString("name"), 
					rs.getString("city"), rs.getString("street"), rs.getString("zipcode"));
			return result;
		});
	}
	
}
