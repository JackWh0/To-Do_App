package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public Connection getConnection() {
		try {
			String url = "jdbc:mysql://localhost/todo?useTimezone=true&serverTimezone=UTC";
			String user = "";
			String password = "";
			return DriverManager.getConnection(url, user, password);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
