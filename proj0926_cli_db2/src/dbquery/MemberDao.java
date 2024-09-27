package dbquery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import domain.entity.Member;
import exception.ColumnNotFoundException;

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
		return jdbcTemplate.query(sql, new MemberMapper());
	}
	
	public Member getById(int id) {
		String sql = "select * from member where id = ?";
		return jdbcTemplate.query(sql, new MemberMapper(), id).get(0);
	}
	
	public boolean isExist(long id) {
		String sql = "select 1 from member where id = ?";
		try {
			int result = jdbcTemplate.queryForObject(sql, Integer.class, id);
			return result == 1?true:false;
		}catch(RuntimeException e) {
			throw new ColumnNotFoundException();
		}
	}
	
	public class MemberMapper implements RowMapper<Member>{

		@Override
		public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
			Member result = new Member(rs.getLong("id"), rs.getString("name"), rs.getString("city"), 
					rs.getString("street"), rs.getString("zipcode"));
			return result;
		}
		
	}
	
}
