package dbquery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import domain.entity.OrderItem;
import exception.ColumnNotFoundException;

@Component
public class OrderItemDao {
	private JdbcTemplate jdbcTemplate;
	
	public OrderItemDao(@Autowired DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public int addOrderItem(OrderItem dto) {
		String sql = "insert into order_item(item_id, order_id, orderprice, count)"
				+ "values(?,?,?,?)";
		return jdbcTemplate.update(sql, dto.getItemId(), dto.getOrderId(), dto.getOrderprice(), dto.getCount());
	}
	
	public int delOne(long id) {
		String sql = "delete from order_item where id = ?";
		return jdbcTemplate.update(sql, id);
	}
	
	public int delByOrderId(long id) {
		String sql = "delete from order_item where order_id = ?";
		return jdbcTemplate.update(sql, id);
	}
	
	public List<OrderItem> getAll(){
		String sql = "select * from order_item";
		return jdbcTemplate.query(sql, new OrderItemMapper());
	}
	
	public boolean isExist(long id) {
		String sql = "select 1 from order_item where id = ?";
		try {
			int result = jdbcTemplate.queryForObject(sql, Integer.class, id);
			return result == 1?true:false;
		}catch(RuntimeException e) {
			throw new ColumnNotFoundException();
		}
	}
	
	private class OrderItemMapper implements RowMapper<OrderItem>{

		@Override
		public OrderItem mapRow(ResultSet rs, int rowNum) throws SQLException {
			OrderItem result = new OrderItem(rs.getLong("id"), rs.getLong("item_id"), 
					rs.getLong("order_id"), rs.getInt("orderprice"), rs.getInt("count"));
			return result;
		}
		
	}
}
