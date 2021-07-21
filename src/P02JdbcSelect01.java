import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class P02JdbcSelect01 {
	public static void main(String[] args) {
		Connection con = null;
		// 쿼리문을 저장하고 실행하기 위한  구문객체 생성
		Statement stmt = null;
		// SELECT 구문은 결과물이 존재하기 때문에 결과데이터를 받아줄 ResultSet이 필요 
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			String url = "jdbc:mysql://localhost/employees";
			// con변수에 정보를 저장 
			con = DriverManager.getConnection(url, "root", "mysql");
			
			/* 쿼리를 실행하는 stmt 객체 처리
			 * con변수가 url, 아이디, 비밀번호 정보가 있기 때문에 con변수의 정보를 stmt에 넘겨야 함  
			 */
			stmt = con.createStatement();
			
			/* SQL 쿼리를 작성한 뒤, stmt의 파라미터로 제공
			 * 쿼리 작성법
			 * 1. JDBC에서는 가장 마지막 ;(세미콜론)을 생략
			 * 2. *로 컬럼을 지정하면 전체 데이터를 가져오고, 컬럼명을 작성하면 일부만 가져옴 
			 */
			// 쿼리문을 잘못 작성하면 에러 발생하는 것이 똑같음 
			String sql = "SELECT emp_no FROM employees LIMIT 2";
			
			// 작성된  쿼리를 수행시키고 결과물은 위의 rs변수에 저장
			rs = stmt.executeQuery(sql);
			
			/* rs는 커서라는 개념을 사용
			 * rs는 인덱스 -1부터 시작 
			 * rs의 길이는 SELECT 구문의 결과에 따라 해당 로우 개수만큼 생성됨 - SELECT 구문의 결과 로우가 1개면 길이: 1, 로우 10개면 길이: 10
			 * 커서를 옮기는 방법은 rs.next();
			 */
			/* 첫 호출로 -1에서 0으로 인덱스 커서가 이동됨 
			 * 커서를 이동시키지 않고 실행하면 'Before start of result set' 라는 에러 발생 
			 */
			rs.next();									// -1번 위치에 있던 커서를 0번으로 옮김 
			
			// rs.get데이터타입(컬럼번호); 를 통해 자료를 받아올 수 있음, 컬럼번호는 1번부터 시작
			System.out.println(rs.getInt(1));
//			System.out.println(rs.getInt("emp_no"));	// 컬럼명을 입력해도 컬럼번호와 같은 출력이 가능 
//			System.out.println(rs);						// 주소값이 출력됨 
			// rs.next();를 입력할 때마다 커서가 이동 - 입력하지 않으면 이동하지 않고 자리에 멈춰있음 
			rs.next();									// rs.next();를 sysout에 넣어 출력하면 해당 위치에 자료가 조회되면 true, 조회되지 않으면 false 값을 반환
			System.out.println(rs.getInt(1));
			rs.next();
			System.out.println(rs.getInt(1));
			System.out.println(rs.getInt(1));			// After end of result set
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
