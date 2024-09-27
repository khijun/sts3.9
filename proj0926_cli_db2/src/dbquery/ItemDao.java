package dbquery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import domain.entity.Item;
import exception.ColumnNotFoundException;

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
	
	public Item getById(long id) {
		String sql = "select * from item where id = ?";
		return jdbcTemplate.query(sql, new ItemMapper(), id).get(0);
	}
	
	public boolean isExist(long id) {
		String sql = "select 1 from item where id = ?";
		try {
			int result = jdbcTemplate.queryForObject(sql, Integer.class, id);
			return result == 1?true:false;
		}catch(RuntimeException e) {
			throw new ColumnNotFoundException();
		}
	}
	
	public class ItemMapper implements RowMapper<Item>{

		@Override
		public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
			Item result = new Item(rs.getLong("id"), rs.getString("name"), rs.getInt("price"), rs.getInt("stockquantity"));
			return result;
		}
		
	}
}
