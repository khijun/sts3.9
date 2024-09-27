package dbquery;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import domain.entity.Orders;
import exception.ColumnNotFoundException;

@Component
public class OrdersDao {
	private JdbcTemplate jdbcTemplate;
	
	private static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	public OrdersDao(@Autowired DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public long addOrders(Orders dto) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "insert into orders(member_id, city, street, zipcode, order_date)"
				+ "values(?, ?, ?, ?, now())";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, dto.getMemberId());
            ps.setString(2, dto.getCity());
            ps.setString(3, dto.getStreet());
            ps.setString(4, dto.getZipcode());
            return ps;
        }, keyHolder);
        
        return keyHolder.getKey().longValue();
	}
	
	public int delOne(long id) {
		String sql = "delete from orders where id = ?";
		return jdbcTemplate.update(sql, id);
	}
	
	public List<Orders> getAll(){
		String sql = "select * from orders";
		return jdbcTemplate.query(sql, new OrdersMapper());
	}
	
	public Orders getOne(long id){
		String sql = "select * from orders where id = ?";
		return jdbcTemplate.queryForObject(sql, new OrdersMapper(), id);
	}
	
	public boolean isExist(long id) {
		String sql = "select 1 from orders where id = ?";
		try {
			int result = jdbcTemplate.queryForObject(sql, Integer.class, id);
			return result == 1?true:false;
		}catch(RuntimeException e) {
			throw new ColumnNotFoundException();
		}
	}
	
	private class OrdersMapper implements RowMapper<Orders>{
		@Override
		public Orders mapRow(ResultSet rs, int rowNum) throws SQLException {
			Orders orders = new Orders(rs.getLong("id"), rs.getLong("member_id"), rs.getString("city"), 
					rs.getString("street"), rs.getString("zipcode"), 
					LocalDateTime.parse(rs.getString("order_date"), dateFormat));
			return orders;
		}
	}
	
}
