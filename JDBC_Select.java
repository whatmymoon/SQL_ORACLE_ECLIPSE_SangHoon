package JDBC01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBC_Select {

	public static void main(String[] args) {
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String id = "scott";
		String pw = "tiger";
				
		Connection con = null; // 데이터베이스 연결을 위한 객체
		PreparedStatement pstmt = null; // con 에서 SQL 실행해주는 객체
		ResultSet rs = null; // SQL 실행결과를 저장하는 객체
		
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, id, pw);
			//System.out.println("데이터베이스에 연결 성공했습니다");
			
			// 데이터베이스 연결 후에는 SQL 명령을 사용하기 위해 String 변수에 SQL 명령을 준비합니다.
			// 데이터베이스에 제공되어질 명령이므로 String 형으로 준비합니다.
			String sql = "select * from customer";
			//★★★시험문제 테이블을 잘못 쓸 경우★★★ 에러 : table or view does not exit
			
			// sql 에 담긴 sql 명령을 con을 통해서 pstmt 에 장착합니다.
			pstmt = con.prepareStatement(sql);
			// pstmt = con.prepareStatement("select * from customer");
			
			// pstmt 에 담겨진 SQL 명령 실행하고 그 결과를 rs 에 저장합니다.
			rs = pstmt.executeQuery();
			
			System.out.println("번호 \t 이름 \t\t 이메일 \t 전화번호");
			System.out.println("--------------------------------------------------------");
			
			// rs.next() : 최초 실행은 객체의 시작부분(데이터 없는곳)에서 첫번째 레코드로 이동하는 메서드
			// 그 다음부터는 다음 레코드로 이동.
			// rs.next() 메서드가 실행될 때 다음 레코드가 있으면 true, 없으면 false를 리턴
			
			while( rs.next() ) { // rs.next() 의 값이 있으면 계속 반복, 없으면 종료
				// rs.getInt() : number 형 필드값을 추출하는 메서드. 괄호안에 필드이름을 정확히 써야합니다.
				// rs.getString() : varchar2 형 필드값을 추출하는 메서드
				// 모든 자료형에 대해 get~() 메서드가 모두 존재합니다.
				
				// 필드명에 오타가 있거나 안맞으면 부적합한 열 이름이라는 에러가 발생합니다.
				int num = rs.getInt("num");
				String name = rs.getString("name");  // ★★★시험문제★★★ field 명을 잘못 쓸 경우 -> 에러 : 부적합한 열 이름
				String email = rs.getString("email");
				String tel = rs.getString("tel");
				
				System.out.printf("%d \t %s \t %s \t %s\n", num, name, email, tel);
			}
			
			
		}catch (ClassNotFoundException e) { 
			e.printStackTrace(); 
		} 
		catch (SQLException e) { 
			e.printStackTrace(); 
		}
		
		
		try { 
			if( con != null ) con.close();
			// System.out.println("\n\n데이터베이스 종료");
		} catch (SQLException e) { 
			e.printStackTrace(); 
		}
	}
}
