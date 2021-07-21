import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/* 온라인 수업 진행 시 자동완성 ctrl + space 에서 ctrl + enter 변경함 
 * window - preferences - general - keys - contents assistant
 */
public class P01JdbcConnection {
	public static void main(String[] args) {
		/* Java와 DB를 연동하기 위해서 JDBC라는 API가 필요
		 * MySQL은 그중에서도 connector J라는 라이브러리 이용
		 * connector J는 MySQL공식홈페이지에서 다운받아 설치하거나 jar파일을 구해 바로 적용
		 * 공식 홈페이지
		 * 1. MySQL 홈페이지 접속 
		 * 2. download - mysql community(GPL)
		 * 3. 운영체제에 맞는 버전 선택
		 * 4. 상단의 2메가바이트 업데이트파일 다운로드
		 * 5. connector j 설치
		 * 6. program files(x86) - mysql - connector j 내부의 .jar파일 확인
		 * 
		 * jar 을 사용할 프로젝트에 추가해야함 - 생성시마다 추가
		 * 1. 프로젝트 우클릭 - properties
		 *    java build path
		 *    상단 탭 libraries - add library
		 *    user library
		 *    user libraries
		 *    new...
		 *    JDBC connection으로 작성(임의로 입력)
		 *    Add E
		 *    External JARs...
		 *    Apply and Close
		 * 
		 * DB 연동 코드 작성 
		 * 위의 모든 작업을 끝내고 코드 작성
		 * import java.sql.*;을 라인1에 작성하고 시작
		 * 
		 * 연결은 connection 자료형으로 진행 - connection은 java.sql의 인터페이스
		 */
			Connection con = null;	
		//  DB연결과 관련된 로직은 try ~ catch 구문을 활용
			try {
				// MySQL DB와 연동할 것을 나타냄
				Class.forName("com.mysql.jdbc.Driver");	
				
				// 접속 url은 jdbc:mysql://localhost/db명 - localhost는 내컴퓨터
				String url = "jdbc:mysql://localhost/employees";
				
				// 접속 주소, 계정, 비밀번호를 이용해 접속요청 
				con = DriverManager.getConnection(url, "root", "mysql");
			} catch(ClassNotFoundException e) {
			 System.out.println("드라이버 로딩 실패 ");
			} catch(SQLException e) {
				System.out.println("에러 : " + e);
			}finally {
				try {
					// 연결이 안되어있거나, 닫혀있지 않을때 실행
					if(con != null && !con.isClosed()) {
						con.close();
					}
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
	}
}












