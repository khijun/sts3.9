package dbquery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import domain.entity.OrderItemView;

@Component
public class OrderItemViewDao {

	private JdbcTemplate jdbcTemplate;
	
	private static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public OrderItemViewDao(@Autowired DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<OrderItemView> getOIViewById(int id) {
		String sql =  "select	m.name as 'member_name', o.city, o.street, o.zipcode, i.name as 'item_name', oi.count, i.price, oi.orderprice as 'total_price', o.order_date"
					+ "  from	member m, orders o, order_item oi, item i "
					+ " where	m.id = ?"
					+ "   and	m.id = o.member_id"
					+ "	  and	o.id = oi.order_id"
					+ "   and	oi.item_id = i.id";
		return jdbcTemplate.query(sql, new OIViewMapper(), id);
	}
	
	public List<OrderItemView> getAllOIView() {
		String sql =  "select	m.name as 'member_name', o.city, o.street, o.zipcode, i.name as 'item_name', oi.count, i.price, oi.orderprice as 'total_price', o.order_date"
					+ "  from	member m, orders o, order_item oi, item i "
					+ " where	m.id = o.member_id"
					+ "	  and	o.id = oi.order_id"
					+ "   and	oi.item_id = i.id";
		return jdbcTemplate.query(sql, new OIViewMapper());
	}
	
	private class OIViewMapper implements RowMapper<OrderItemView>{

		@Override
		public OrderItemView mapRow(ResultSet rs, int rowNum) throws SQLException {
			OrderItemView result = new OrderItemView(rs.getString("member_name"), rs.getString("city"), 
					rs.getString("street"), rs.getString("zipcode"), rs.getString("item_name"), 
					rs.getInt("count"), rs.getInt("price"), rs.getInt("total_price"), 
					LocalDateTime.parse(rs.getString("order_date"), dateFormat));
			return result;
		}
		
	}
}
