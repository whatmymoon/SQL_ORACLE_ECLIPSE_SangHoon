package JDBC04;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import JDBC03.BookDao;

public class Member_main {

	public static void main(String[] args) {
		
		while(true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("\n--- 메뉴 선택 ---");
			System.out.printf("1. 데이터 열람.");	System.out.printf("  2. 데이터 추가.");
			System.out.printf("  3. 데이터 수정.");	System.out.printf("  4. 데이터 삭제.");
			System.out.println("  5. 프로그램 종료.");	System.out.printf(">> 메뉴 선택 : ");
			String choice = sc.nextLine();
			
			if( choice.equals("5") ) break;
			switch(choice) {
				case"1" : select(); break;
				case"2" : insert(); break;
				case"3" : update(); break;
				case"4" : delete(); break;
				default : System.out.println("메뉴 선택이 잘못되었습니다");
			}
		}
		System.out.println("프로그램을 종료합니다");
	}

	private static void select() {
		MemberDao mdao = new MemberDao();
		ArrayList<MemberDto> list = mdao.selectMember();
		System.out.println("회원번호 \t 이름 \t    전화번호 \t     생년월일 \t 사온포인트 \t 성별 \t 나이");
		System.out.println("----------------------------------------------------------------------------------"
				+"-----");
		for( MemberDto dto : list) {
			System.out.printf("   %2d \t        %s \t  %s    %10s \t    %4d \t  %s \t  %2d\n",
					dto.getMembernum(), dto.getName(), dto.getPhone(), dto.getBirth(),
					dto.getBpoint(), dto.getGender(), dto.getAge());
		}
	}

	private static void insert() {
		
		MemberDao mdao = new MemberDao();
		MemberDto mdto = new MemberDto();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("회원 이름을 입력하세요 : ");
		mdto.setName(sc.nextLine());
		
		System.out.print("전화번호를 입력하세요 : ");
		mdto.setPhone(sc.nextLine());
		
		// 생일 입력 - java.util.Date()형식의 입력 받은 후 java.sql.Date()로의 변환이 필요합니다.
		// java.util.Date() 의 입력을 위해선 SimpleDateFormat 의 parse 루틴이 필요합니다.
		System.out.print("생일을 입력하세요(YYYY-MM-DD) : ");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date d = null;
		String temp;
		while(true) {
			try {
				temp = sc.nextLine();
				d = sdf.parse( temp );
				break;
			} catch (ParseException e) {
				System.out.print("다시 입력(입력 예:2015-12-31) : ");
			}
		}
		mdto.setBirth(temp);
		/*
		// java.util.Date 을 java.sql.Date 로 변환
		// d.getTime() 을 java.sql.Date 의 생성자의 전달인수로 넣습니다.
		java.sql.Date birth = new java.sql.Date(d.getTime());
		// mdto.setBirth(birth);
		*/
		
//		System.out.print("사온포인트를 입력하세요 : ");
//		mdto.setBpoint(Integer.parseInt(sc.nextLine()));
		
		System.out.print("성별을 입력하세요 : ");
		mdto.setGender(sc.nextLine());
		
		// 나이는 입력 받지 않고 계산
		Calendar c = Calendar.getInstance();
		Calendar today = Calendar.getInstance();
		c.setTime(d);	// c.setTime( birth );	Date 자료를 Calendar 자료로 변환
		int age = today.get( Calendar.YEAR ) - c.get( Calendar.YEAR );
		mdto.setAge(age);
		
		int result = mdao.insertMember(mdto);
		if(result == 1) System.out.println("레코드 추가 성공");
		else System.out.println("레코드 추가 실패");
		
	}

	private static void update() {
		
		// 필요한 객체들 생성
		MemberDto mdto = null;
		Scanner sc = new Scanner(System.in);
		MemberDao mdao = new MemberDao();
		
		// 회원 번호 입력
		String membernum;
		while(true) {
			System.out.printf("수정할 회원번호를 입력하세요");
			membernum = sc.nextLine();
			if( membernum.equals("")) System.out.println("회원번호 입력은 필수 입니다");
			else break;
		}
		
		mdto = mdao.getMember( Integer.parseInt(membernum));
		if(mdto == null) {
			System.out.println("해당 회원이 없습니다");
			return;
		}
		
		// 조회된 기존값을 먼저 출력하고 수정할 내용을 입력 받습니다. 각 항목들 수정하지 않으려면 엔터만 입력
		String temp = "";
		// 이름
		System.out.println("성명 : " + mdto.getName());
		System.out.println("수정할 이름을 입력하세요. (수정하지 않으려면 엔터만 입력)");
		temp = sc.nextLine();
		if( !temp.equals("") ) mdto.setName(temp);
		// 전화번호
		System.out.println("전화번호 : " + mdto.getPhone());
		System.out.println("수정할 전화번호를 입력하세요. (수정하지 않으려면 엔터만 입력)");
		temp = sc.nextLine();
		if( !temp.equals("") ) mdto.setPhone(temp);
		// 성별
		System.out.println("성별 : " + mdto.getGender());
		System.out.println("수정할 성별을 입력하세요(M/F). (수정하지 않으려면 엔터만 입력)");
		temp = sc.nextLine();
		if( !temp.equals("") ) mdto.setGender(temp);
		// 사온포인트
		System.out.println("사은포인트 : " + mdto.getBpoint());
		System.out.println("수정할 사은포인트를 입력하세요. (수정하지 않으려면 엔터만 입력)");
		temp = sc.nextLine();
		if( !temp.equals("") ) mdto.setBpoint(Integer.parseInt(temp));
		// 생년월일 & 나이
		System.out.print("생일을 입력하세요(YYYY-MM-DD) : ");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date d = null;
		java.sql.Date birth = null;
		while(true) {
			try {
				temp = sc.nextLine();
				if( temp.equals("") ) break;  // 수정을 위해 입력한 날짜가 없다면
				d = sdf.parse(temp);  // String -> java.util.Date 변환
				
				mdto.setBirth(temp);
				// birth = new java.sql.Date(d.getTime());  // java.sql.Date 로 변환
				// mdto.setBirth(birth);  // 입력 받은 날짜를 mdto에 저장
				break;
			} catch (ParseException e) {
				System.out.print("다시 입력(입력예:2015-12-31) : ");
			}
		}
		if( !temp.equals("") ) {	
			// temp 가 "" 가 아니면서 입력받은 날짜와 기존 날짜가 같지 않다면
			Calendar c = Calendar.getInstance();
			Calendar today = Calendar.getInstance();
			c.setTime(d);
			int age = today.get(Calendar.YEAR)- c.get(Calendar.YEAR) + 1;
			mdto.setAge(age);
		}
		
		int result = mdao.updateMember(mdto);
		if( result == 1 ) System.out.println("수정 성공");
		else System.out.println("수정 실패");
	}

	private static void delete() {
		
		// 삭제할 회원번호를 입력 받되, 없는 회원번호를 입력할 시 "회원이 없습니다"라고 출력하고 메서드를 종료하세요
		// 회원이 있으면 레코드를 삭제합니다.
		// 회원 번호 입력
		MemberDto mdto = null;
		Scanner sc = new Scanner(System.in);
		MemberDao mdao = new MemberDao();
		
		String membernum;
		while(true) {
			System.out.printf("삭제할 회원번호를 입력하세요");
			membernum = sc.nextLine();
			if( membernum.equals("") ) System.out.println("회원번호 입력은 필수입니다");
			else break;
		}
		mdto = mdao.getMember( Integer.parseInt(membernum));
		if( mdto == null ) {
			System.out.println("해당 회원이 없습니다");
			return;
		}
		
		int result = mdao.deleteMember( Integer.parseInt(membernum));
		if( result == 1 ) System.out.println("삭제 성공");
		else System.out.println("삭제 실패");
	}

	

}
