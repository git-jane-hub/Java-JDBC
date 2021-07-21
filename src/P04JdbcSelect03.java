import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class P04JdbcSelect03 {
	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		Scanner scan = new Scanner(System.in);
		System.out.println("몇명을 조회합니까?");
		int num = scan.nextInt();
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			String url = "jdbc:mysql://localhost/employees";
			con = DriverManager.getConnection(url, "root", "mysql");
			stmt = con.createStatement();
			
			String sql = "SELECT emp_no, first_name FROM employees LIMIT " + num;	// LIMIT 뒤에 띄어쓰기 작성해줘야 함
			rs = stmt.executeQuery(sql);
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
