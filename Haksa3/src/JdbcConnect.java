
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcConnect {
	private static Connection connect;
	private static Statement statment;
	private static ResultSet resultSet;
	
	//연결 후 Statement 반환
	public static Statement getStatement() {
		if(connect==null) {
			try {
				//Class.forName("com.mysql.jdbc.Driver") mysql 할때..
				Class.forName("oracle.jdbc.driver.OracleDriver");
				//"jdbc:mysql://localhost:3306/sampledb?useSSL=false" mysql url
				connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "ora_user", "hong");
				statment = connect.createStatement();
				System.out.println(statment);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return statment;
	}	
	
	//연결 해제
	public static void disconnectJdbc() {
		try {
			if(connect!=null) connect.close();
			if(statment!=null) statment.close();
			if(resultSet!=null) resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	//excuteQuery.. select 내용이 들어가기
	public static ResultSet executeQuery(String sql) {
		statment = JdbcConnect.getStatement();
		try {
			System.out.println(sql);
			System.out.println(statment);
			resultSet = statment.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}
	
    //executeUpdate.. select를 제외한 다른 쿼리
	public static void executeUpdate(String sql) {
		statment = JdbcConnect.getStatement();
		try {
			System.out.println(sql);
			statment.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
