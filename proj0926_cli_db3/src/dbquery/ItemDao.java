package dbquery;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import domain.entity.Item;

@Component
public class ItemDao {

	private JdbcTemplate jdbcTemplate;
	
	public ItemDao(@Autowired DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public int addItem(Item item) {
		System.out.println(item);
		String sql = "insert into item(name, price, stockquantity) values"
				+ "(?,?,?)";
		return jdbcTemplate.update(sql,item.getName(), item.getPrice(), item.getStockQuantity());
	}
	
	public int subStockquantity(int id, int count) {
//		String sql = "";
//		return jdbcTemplate.update(sql,item.getName(), item.getPrice(), item.getStockQuantity());
		return 0;
	}
	
	public List<Item> getAllItem(){
		String sql = "select * from item";
		return jdbcTemplate.query(sql, (rs, rowNum)->{
			Item result = new Item(rs.getInt("id"), rs.getString("name"), rs.getInt("price"), rs.getInt("stockquantity"));
			return result;
		});
	}
}
