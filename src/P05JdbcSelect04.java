import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class P05JdbcSelect04 {
	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			String url = "jdbc:mysql://localhost/employees";
			con = DriverManager.getConnection(url, "root", "mysql");
			stmt = con.createStatement();
			String sql = "SELECT emp_no, first_name, hire_date FROM employees LIMIT 10";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int empNo = rs.getInt(1);
				String firstName = rs.getString(2);
				Date hireDate = rs.getDate(3);
				System.out.println("사번: " + empNo + ", 입사일: " + hireDate + ", 이름: " + firstName);
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
