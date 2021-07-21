import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class P09JdbcDelete {
	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		Scanner scan = new Scanner(System.in);
		System.out.println("삭제할 글 번호를 입력해주세요.");
		int col1 = scan.nextInt();
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			String url = "jdbc:mysql://localhost/sqldb";
			con = DriverManager.getConnection(url, "root", "mysql");
			stmt = con.createStatement();
			String sql = "DELETE FROM JDBCInsert WHERE num = " + col1;
			stmt.executeUpdate(sql);
			System.out.println(sql);
			
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
