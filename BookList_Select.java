package JDBC03;

import java.util.ArrayList;

public class BookList_Select {

	public static void main(String[] args) {
		
		BookDao bdao = new BookDao();
		
		// - 현재 위치에서 필요한 건 데이터베이스에서 booklist 를 조회한 결과입니다.
		// - 그 조회를 BookDao 의 selectBook 메서드에 맡기고 그 결과를 리턴받을 예정입니다.
		// - 데이터베이스의 결과를 ResultSet 객체에 저장되지만 selectBook 메서드에서 하나하나의 값들을
		// ArrayList 에 넣어서 리턴 받을 예정입니다.
		// - 다만, 필드값 하나하나를 ArrayList에 담는 것이 아니라 필드 값들은 레코드단위의 저장소에 담고,
		// 그 레코드단위의 저장소를 ArrayList에 담습니다.
		// - 여기서 레코드단위의 저장되고 클래스로 정의합니다.
		ArrayList<BookDto> list = bdao.selectBook();
		
		System.out.println("도서번호 \t 출판년도 \t 입고가격 \t 대여가격 \t 등급 \t\t 제목");
		System.out.println("--------------------------------------------------------------------------------------------------");
		for( BookDto dto : list) {
			System.out.printf("  %2d \t\t   %d \t  %d \t   %d \t %3s \t %-15s\n",
					dto.getBooknum(), dto.getMakeyear(), dto.getInprice(),
					dto.getRentprice(), dto.getGrade(), dto.getSubject());
		}
		
	}

}
