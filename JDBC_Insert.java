package JDBC01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBC_Insert {

	public static void main(String[] args) {
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String id = "scott";
		String pw = "tiger";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, id, pw);
			// System.out.println("연결 성공");
			
			Scanner sc = new Scanner(System.in);
			System.out.print("저장할 번호를 입력하세요: ");
			String num = sc.nextLine();
			System.out.print("이름을 입력하세요: ");
			String name = sc.nextLine();
			System.out.print("이메일 입력하세요: ");
			String email = sc.nextLine();
			System.out.print("전화번호를 입력하세요: ");
			String tel = sc.nextLine();
			
			// insert into customer values(1, '홍길동', 'abc1@abc.com', '010-1234-5234');
			//	얘는 옛날 방식 String sql = "insert into customer values( " + num +
			// ", '" + name + "' " + ",'" + email + "' " + ", '" + tel + "' )";
			
			// 얘가 요즘 방식
			String sql = "insert into customer values(?, ?, ?, ?)";
			pstmt = con.prepareCall(sql);
			pstmt.setInt(1, Integer.parseInt(num));
			pstmt.setString(2, name);
			pstmt.setString(3, email);
			pstmt.setString(4, tel);
			// setInt 와 setString 이 자동으로 필요한 따옴표('')를 붙여서 sql 명령을 구성하여 줍니다.
			
			// SQL select 명령만 executeQuery 를 사용하고, 나머지는 executeUpdate 메서드를 사용합니다.
			// executeUpdate의 결과는 sql 명령이 정상 동작했을 때 1, 실패했을 때 0 이 리턴됩니다.
			int result = pstmt.executeUpdate();
			if( result == 1 ) System.out.println("레코드 추가 성공");
			else System.out.println("레코드 추가 실패");
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if(con != null) con.close();
			if(pstmt != null) pstmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
