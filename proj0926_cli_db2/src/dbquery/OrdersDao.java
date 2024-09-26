package dbquery;

import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import domain.entity.Orders;

@Component
public class OrdersDao {
	private JdbcTemplate jdbcTemplate;
	
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
	
	
}
