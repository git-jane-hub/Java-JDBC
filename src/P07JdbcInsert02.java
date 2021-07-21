import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
// P05JdbcInsert에서 직접 데이터 삽입하던 내용을 스캐너로 입력받아 자료 삽입 
public class P07JdbcInsert02 {
	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		Scanner scan = new Scanner(System.in);
		System.out.println("num 컬럼에 들어갈 정수를 입력해주세요.");
		int col1 = scan.nextInt();
		System.out.println("str 컬럼에 들어갈 문자열을 입력해주세요.");
		// scan.nextLine()으로 작성하지 않고 scan.next()로 작성 
		String col2 = scan.next();
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			String url = "jdbc:mysql://localhost/sqldb";
			con = DriverManager.getConnection(url, "root", "mysql");
			stmt = con.createStatement();
			String sql = "INSERT INTO JDBCInsert VALUES(" + col1 + ", '" + col2 + "')";
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
