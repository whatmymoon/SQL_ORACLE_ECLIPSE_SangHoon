package JDBC02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookList_Select {

	public static void main(String[] args) {
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, "scott", "tiger");
			
			System.out.println("  도서번호\t출판년도\t입고가격\t출고가격\t 등급\t\t   제목");
			System.out.println("-------------------------------------------------------------------------------------------------");
			String sql = "select * from booklist";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// jquery 구문을 사용하면 pstmt.executeQuery(); 하지 않으면 pstmt.executeUpdate();
			while( rs.next() ) {
				int num = rs.getInt("booknum");
				// int makeyear = rs.getInt("makeyears");	// 에러★★★ 
				// 부적합한 열 이름 -> field 값을 잘못 입력하면 뜨는 에러 ★★★시험문제입니다.★★★
				int makeyear = rs.getInt("makeyear");
				int inprice = rs.getInt("inprice");
				int rentprice = rs.getInt("rentprice");
				String grade = rs.getString("grade");
				String subject = rs.getString("subject");
				System.out.printf("     %2d \t %d \t\t %d \t\t  %d \t\t %3s \t    %-11s\n", num, makeyear, inprice, rentprice, grade, subject);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if(con != null) con.close();
			if(pstmt != null) pstmt.close();
			if(rs != null)rs.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
