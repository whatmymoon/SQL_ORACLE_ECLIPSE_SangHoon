package JDBC01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC_Connect {

	public static void main(String[] args) {
		
		// 자바에서 지원하는 데이터베이스연결을 위한 구성요소들과 객체를 세팅
		// 접속에 필요한 정보들(데이터베이스 연결 객체의 멤버들에 저장될 내용들)을 String 변수에 저장
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String id = "scott";
		String pw = "tiger";
		
		// 1. JDBC 를 통한 데이터 베이스 연결 클래스의 객체 : Connection 객체 생성
		Connection con = null;
		
		try {
			// 2. 드라이버 클래스 파일을 지정
			Class.forName(driver);
			
			// 3. 연결 드라이버 매니저가 데이터베이스 연결을 하고, 연결해 준 연결 인스턴스를 con 변수에 저장
			con = DriverManager.getConnection(url, id, pw);
			
			System.out.println("데이터베이스에 연결 성공했습니다");
			// 데이터 베이스 작업
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("데이터베이스에 연결 실패");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("데이터베이스에 연결 실패");
		}
		
		try {
			if( con != null ) con.close();
			System.out.println("데이터베이스 종료");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("연결이 종료되지 않았습니다.");
		}
		
	}

}
