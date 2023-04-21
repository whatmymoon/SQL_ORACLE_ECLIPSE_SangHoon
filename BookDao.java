package JDBC03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// Dao : Database Access Object -> 데이터베이스 접근 및 작업 전용 클래스

public class BookDao {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	private Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, "scott", "tiger");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	private void close() {
		if(con != null)
			try {
				con.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public ArrayList<BookDto> selectBook() {
		
		ArrayList<BookDto> list = new ArrayList<BookDto>();
		// 데이터베이스에서 booklist 테이블을 조회하고 결과를 list에 담아서 retrun
		
		con = getConnection();
		String sql = "select * from booklist order by booknum desc";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				/*
				int booknum = rs.getInt("booknum");
				String subject = rs.getString("subject");
				int makeyear = rs.getInt("makeyear");
				int inprice = rs.getInt("inprice");
				int rentprice = rs.getInt("rentprice");
				String grade = rs.getString("grade");
				
				BookDto bdto = new BookDto();
				bdto.setBooknum(booknum);
				bdto.setSubject(subject);
				bdto.setMakeyear(makeyear);
				bdto.setInprice(inprice);
				bdto.setRentprice(rentprice);
				bdto.setGrade(grade);
				*/
				BookDto bdto = new BookDto();
				bdto.setBooknum( rs.getInt("Booknum"));
				bdto.setSubject( rs.getString("subject"));
				bdto.setMakeyear( rs.getInt("makeyear"));
				bdto.setInprice( rs.getInt("inprice"));
				bdto.setRentprice( rs.getInt("rentprice"));
				bdto.setGrade( rs.getString("grade"));
				// 반복이 실행될 때 마다 BookDto() 로 만들어진 객체가 다음 반복으로 없어지기 전에 list에 담아서 보관합니다.
				list.add(bdto);
				
			}
		} catch (SQLException e) { e.printStackTrace();
		}
	
		close();
		
		return list;
	}

//	public void insertBook(String subject, String makeyear, String inprice, String rentprice, String grade) {
//		
//		
//	}

	public int insertBook(BookDto bdto) {
		int result = 0;
		
		// 1. DB에 연결
		con = getConnection();
		
		// 2. SQL문 설정
		String sql = "insert into booklist values(book_seq.nextVal, ?,?,?,?,?)";
		
		
		try {
			
			// 3. SQL을 con과 함께 pstmt 에 장착
			pstmt = con.prepareStatement(sql);
			
			// 4. ?에 적정 값을 배치
			pstmt.setString(1, bdto.getSubject());
			pstmt.setInt(2, bdto.getMakeyear());
			pstmt.setInt(3, bdto.getInprice());
			pstmt.setInt(4, bdto.getRentprice());
			pstmt.setString(5, bdto.getGrade());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) { e.printStackTrace();
		}
		
		// DB 연결을 끊습니다.
		close();
		
		return result;
	}

	public int deleteBook(int num) {
		int result = 0;
		
		con = getConnection();
		
		String sql = "delete from booklist where booknum = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) { e.printStackTrace();
		}
		
		close();
		
		return result;
	}

	public BookDto getBook(String booknum) {
		BookDto bdto = null;
		
		con = getConnection();
		String sql = "select * from booklist where booknum=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(booknum));
			rs = pstmt.executeQuery();
			if( rs.next() ) {
				bdto = new BookDto();
				bdto.setBooknum( rs.getInt("booknum") );
				bdto.setSubject( rs.getString("subject") );
				bdto.setMakeyear( rs.getInt("makeyear") );
				bdto.setInprice( rs.getInt("inprice") );
				bdto.setRentprice( rs.getInt("rentprice") );
				bdto.setGrade( rs.getString("grade") );
			}
		} catch (SQLException e) { e.printStackTrace();
		}
		
		return bdto;
	}

	public int Update(BookDto bdto) {
		int result = 0;
		con = getConnection();
		String sql = "update booklist set subject=?, makeyear=?, inprice=?, rentprice=?, grade=? where booknum=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bdto.getSubject());
			pstmt.setInt(2, bdto.getMakeyear());
			pstmt.setInt(3, bdto.getInprice());
			pstmt.setInt(4, bdto.getRentprice());
			pstmt.setString(5, bdto.getGrade());
			pstmt.setInt(6, bdto.getBooknum());
			result = pstmt.executeUpdate();
		} catch (SQLException e) { e.printStackTrace();
		}
		
		close();
		
		return result;
	}
	
}
