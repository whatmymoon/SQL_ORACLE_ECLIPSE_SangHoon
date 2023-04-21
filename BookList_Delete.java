package JDBC02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class BookList_Delete {

	public static void main(String[] args) {
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, "scott", "tiger");
			// 삭제할 booknum 을 입력 받아서 해당 도서를 삭제하세요
			
			Scanner sc = new Scanner(System.in);
			System.out.print("삭제할 도서번호를 입력하세요: ");
			int num = Integer.parseInt(sc.nextLine());
			String sql = "Delete from booklist where booknum = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			int result = pstmt.executeUpdate();
			if(result == 1) System.out.println("삭제 성공~");
			else System.out.println("실패~ㅠㅠ");
			
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
