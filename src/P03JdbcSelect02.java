import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class P03JdbcSelect02 {
	public static void main(String[] args) {
		/* 1. 커넥터를 연결
		 * 2. 조회구문은 "SELECT emp_no, first_name * FROM employees LIMIT 10"
		 * 3. 위 조회구문으로 조회한 결과물을 while문을 활용하여 콘솔창에
		 * 	     사번: emp_no, 이름: first_name과 같은 형태로 10줄을 출력
		 * while(rs.next())를 조건식으로 작성하면 정환하게 ResultSet에 저장된 만큼 반복됨 
		 */
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			String url = "jdbc:mysql://localhost/employees";
			con = DriverManager.getConnection(url, "root", "mysql");
			stmt = con.createStatement();
			String sql = "SELECT emp_no, first_name FROM employees LIMIT 10";
			rs = stmt.executeQuery(sql);
			// rs.next()는 커서를 옮기는 동시에 true/ false를 반환 
			while(rs.next()) {
				System.out.println("사번: " + rs.getInt(1) + ", 이름: " + rs.getString(2));
			}
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