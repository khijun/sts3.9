package dbquery;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import domain.entity.OrderItem;

@Component
public class OrderItemDao {
	private JdbcTemplate jdbcTemplate;
	
	public OrderItemDao(@Autowired DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public int addOrderItem(OrderItem dto) {
		String sql = "insert into order_item(member_id, item_id, count, order_date)"
				+ "values(?,?,?, now())";
		return jdbcTemplate.update(sql,dto.getMemberId(), dto.getItemId(),dto.getCount());
	}
	
}
