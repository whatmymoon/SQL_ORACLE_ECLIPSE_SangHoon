package JDBC03;

import java.util.Scanner;

public class Booklist_Update {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		// 1. 수정할 레코드의 도서번호를 입력 받습니다.
		System.out.print("수정할 도서의 도서번호를 입력하세요");
		String booknum = sc.nextLine();
		
		BookDao bdao = new BookDao();
		BookDto bdto = null;
		
		// 2. 입력 받은 도서코드로 도서를 조회해서 리턴받습니다.
		bdto = bdao.getBook(booknum);
		
		if( bdto == null ) {
			System.out.println("입력한 도서가 없습니다");
			return;
		}
		
		String temp = "";
		
		System.out.printf("도서제목 : %s\n", bdto.getSubject());
		System.out.println("수정할 제목을 입력하세요(수정하지 않으려면 엔터를 입력하세요) : ");
		temp = sc.nextLine();
		if( !temp.equals("") ) bdto.setSubject(temp);;
		
		System.out.printf("출판년도 : %d\n", bdto.getMakeyear());
		System.out.println("수정할 출판년도를 입력하세요(수정하지 않으려면 엔터를 입력하세요) : ");
		temp = sc.nextLine();
		if( !temp.equals("") ) bdto.setMakeyear(Integer.parseInt(temp));;
		
		System.out.printf("입고가격 : %d\n", bdto.getInprice());
		System.out.println("수정할 입고가격을 입력하세요(수정하지 않으려면 엔터를 입력하세요) : ");
		temp = sc.nextLine();
		if( !temp.equals("") ) bdto.setInprice(Integer.parseInt(temp));;
		
		System.out.printf("대여가격 : %d\n", bdto.getRentprice());
		System.out.println("수정할 대여가격을 입력하세요(수정하지 않으려면 엔터를 입력하세요) : ");
		temp = sc.nextLine();
		if( !temp.equals("") ) bdto.setRentprice(Integer.parseInt(temp));;
		
		System.out.printf("등급 : %s\n", bdto.getGrade());
		System.out.println("수정할 등급을 입력하세요(수정하지 않으려면 엔터를 입력하세요) : ");
		temp = sc.nextLine();
		if( !temp.equals("") ) bdto.setGrade(temp);
		
		int result = bdao.Update(bdto);
		if( result == 1 ) System.out.println("수정 성공 ~");
		else System.out.println("수정 실패 ㅠ");
	}

}
