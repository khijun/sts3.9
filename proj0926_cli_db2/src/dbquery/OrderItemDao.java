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
		String sql = "insert into order_item(item_id, order_id, orderprice, count)"
				+ "values(?,?,?,?)";
		return jdbcTemplate.update(sql, dto.getItemId(), dto.getOrderId(), dto.getOrderprice(), dto.getCount());
	}
	
}
