package JDBC02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BookList_Insert {

	public static void main(String[] args) {
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, "scott", "tiger");
			// booknum에는 book_seq.nextVal 로 자동입력되게 새로운 레코드를 insert 하세요
			// 필요한 사항의 입력은 Scanner 를 이용합니다.
			
			Scanner sc = new Scanner(System.in);
			System.out.print("책의 제목을 입력하세요: ");
			String subject = sc.nextLine();
			System.out.print("출판년도를 입력하세요: ");
			String makeyear = sc.nextLine();
			System.out.print("입고가격을 입력하세요: ");
			String inprice = sc.nextLine();
			System.out.print("출고가격을 입력하세요: ");
			String rentprice = sc.nextLine();
			System.out.print("시청할 수 있는 연령대를 입력하세요(all, 12, 18): ");
			String grade = sc.nextLine();
			
			String sql = "insert into booklist values(book_seq.nextVal,?, ?, ?, ?, ?)";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, subject);
			pstmt.setInt(2, Integer.parseInt(makeyear));
			pstmt.setInt(3, Integer.parseInt(inprice));
			pstmt.setInt(4, Integer.parseInt(rentprice));
			pstmt.setString(5, grade);
			
			
			int result= pstmt.executeUpdate();
			if( result == 1 ) System.out.println(" Insert 성공~ ");
			else System.out.println(" Insert 실패~ㅠ");
			
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
