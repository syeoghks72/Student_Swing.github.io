
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcConnect {
	private static Connection connect;
	private static Statement statment;
	private static ResultSet resultSet;
	
	public static Statement getStatement() {
		if(connect!=null) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "ora_user", "hong");
				statment = connect.createStatement();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return statment;
	}	
	
	public static void disconnectJdbc() {
		try {
			if(connect!=null) connect.close();
			if(statment!=null) statment.close();
			if(resultSet!=null) resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
}
