import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class P06JdbcInsert01 {
	public static void main(String[] args) {
		/* 
		 * 
		 */
		Connection con = null;
		Statement stmt = null;
//		ResultSet rs = null;				// ResultSet은 SELECT구문에 대한 결과만 처리 
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			String url = "jdbc:mysql://localhost/sqldb";
			con = DriverManager.getConnection(url, "root", "mysql");
			stmt = con.createStatement();
			String sql = "INSERT INTO JDBCInsert VALUES(2, 'PYTHON')";
//			rs = stmt.executeQuery(sql);	// SELECT 구문을 호출하는 방식 
			// SELECT 구문 이외의 형식 SQL문을 호출하는 방식 
			stmt.executeUpdate(sql);
			
			
			
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		}catch(SQLException e) {
			System.out.println("에러: " + e);
		}finally {
			try {
				if(con != null && !con.isClosed()) {
					con.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
