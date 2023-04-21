-- 05_DDL_Sequence.sql

-- * 오라클 - 시퀀스(Sequence)
--		: 테이블 내의 유일한 숫자를 자동으로 생성하는 자동 번호 발생기.
--		: 테이블 생성 후 시퀀스(일련번호)를 따로 만들어야 한다.

-- 생성 방법
-- create sequence 시퀀스 이름 start width 시작숫자 increment by 증가량;

-- 주로 number 형식에 기본키 값으로 사용합니다.
-- 일련번호 정도로 이해하셔도 무방합니다.
-- number( 자리수 ) : 자료형의 자리수가 몇자리냐에 따라 그 만큼 숫자가 증가 할 수 있습니다.

-- [1] 시퀀스의 생성
create sequence book_seq start with 1 increment by 1;
alter table booklist add grade varchar2(15);
drop sequence book_seq;
drop sequence member_seq;
create sequence member_seq start with 7 increment by 1;

-- 테이블에 레코드를 추가합니다.
insert into booklist values(book_seq.nextVal, '일곱해의 마지막', 2020, 12150, 2000,'all');
insert into booklist values(book_seq.nextVal, '봉제인형 살인사건', 2019, 13150, 2000,'18');
insert into booklist values(book_seq.nextVal, '쇼코의 미소', 2023, 13420, 2000,'19');

-- drop table booklist purge;  -- unique/primary keys in table referenced by foreign keys - 에러

select * from booklist;

-- [3] 시퀀스 수정 : 최대 증가값을 14까지로 제한.
alter sequence book_seq maxvalue 14;
-- [4] 시퀀스 삭제
drop sequence book_seq;
-- [5] 시퀀스 재생성 : 다음 숫자부터 시작하게 하여 기존 레코드와 중복되지 않게 합니다.
create sequence book_seq start with 7 increment by 1;

-- * 현재 로그인한 사용자가 만들거나 보유하고 있는 테이블의 목록을 조회
select * from tabs;

-- 1부터 1씩 늘어나는 member_seq rent_seq 를 생성해주세요
create sequence member_seq start with 1 increment by 1;
create sequence rent_seq start with 1 increment by 1;














