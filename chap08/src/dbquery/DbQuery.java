package dbquery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

public class DbQuery {
	private DataSource dataSource;

	public DbQuery(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public int count() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection(); // 풀에서 커넥션 구함
			try (Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("select 100+1")) {
				rs.next();
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null)
				try {
					conn.close(); // 커넥션을 풀에 반환
				} catch (SQLException e) {
				}
		}
	}
}
