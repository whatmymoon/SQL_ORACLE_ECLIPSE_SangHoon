-- 10_JOIN.sql

-- JOIN
-- 두 개이상의 테이블에 나눠져 있는 관련 데이터들을 하나의 테이블로
-- 모아서 조회하고자 할 때 사용하는 명령입니다.

-- [1] 이름 Douglas Grant 인 사원이 근무하는 부서명, 부서의 지역명 출력하고자 한다면...
select department_id from employees where emp_name='Douglas Grant';
-- 위 명령을 실행 후 얻어진 부서번호로 아래와 같이 부서번호 검색하여
-- 부서명을 알아냅니다.
select department_name, parent_id from departments where department_id=50;

-- 위의 두개의 명령을 하나의 명령으로 합해주는 역할을 join 명령이 실행합니다.
-- [2] join : 두 개이상의 테이블에 나뉘어져 있는 데이터를
-- 한번의 sql문으로 원하는 결과를 얻습니다.


-- cross join : 두 개이상의 테이블이 join될 때 where절에 의해
-- 공통되는 컬럼에 의한 결합이 발생하지 않는 경우
-- * 가장 최악의 결과를 얻는 조인 방식
-- A테이블과 B테이블의 cross join 된다면
-- A테이블의 1번 레코드와 B테이블의 모든 레코드와 하나하나 모두 조합
-- A테이블의 2번 레코드와 B테이블의 모든 레코드와 하나하나 모두 조합
-- A테이블의 3번 레코드와 B테이블의 모든 레코드와 하나하나 모두 조합
-- ....

create table A(
a1 varchar2(5),
a2 varchar2(5),
a3 varchar2(5)
);
create table B(
b1 varchar2(5),
b2 varchar2(5),
b3 varchar2(5)
);
insert into A values('ar1','ar1','ar1');
insert into A values('ar2','ar2','ar2');
insert into A values('ar3','ar3','ar3');
delete from B;
insert into B values('br1','br1','ar1');
insert into B values('br2','br2','ar2');
insert into B values('br3','br3','ar3');
insert into B values('br4','br4','ar3');

select * from A;
select * from B;

-- cross조인 : 별도의 조건이나 키워드 없이 from 뒤에 테이블이름을 콤마로 구분해서
--			   두 개이상 쓰면 cross 조인이 됩니다.
select * from A, B;


-- A테이블의 레코드가 8개, B테이블의 레코드가 7개 라면 총 크로스조인의 결과
-- 레코드 수는 8X7 = 56
-- A테이블의 필드가 8개, B테이블의 필드가 3개라면 총 크로스조인의 결과 필드 수는
-- 8+3 = 11
select * from dept; -- 레코드 3, 필드 3
select * from emp; -- 레코드 10, 필드 8

-- 크로스 조인
select * from dept, emp -- 레코드 30, 필드 11

-- equi join : join 대상이 되는 두 테이블에서 공통적으로 존재하는
-- 칼럼의 값이 일치하는 행을 연결하여 결과를 생성
select * from dept, emp where emp.deptno = dept.deptno;

-- 각 사원의 이름, 부서 번호, 부서명, 지역을 출력하시오 ( emp, dept)
select ename, emp.deptno, dname, loc  from  dept, emp where emp.deptno = dept.deptno;

-- 이름이 SCOTT인 사원의 이름, 부서번호, 부서명, 위치 출력( emp, dept)
select ename, emp.deptno, dname ,loc 
from dept, emp 
where emp.deptno= dept.deptno and ename = 'SCOTT';

-- 컬럼 명 앞에 테이블 명을 기술하여 칼럼의 소속을 명확히 해줘야 오류가 없음


-- 테이블 명에 별칭을 부여한 후 컬럼 앞에 소속 테이블을 지정
-- 테이블 명으로 소속을 기술할 때는 한 쪽에만 있는 필드에 생략이 가능하지만 아래와 같이
-- 별칭을 부여시에는 모든 필드 앞에 반드시 별칭을 기술해야함
select a.ename, b.dname, b.loc, a.deptno
from emp a, dept b
where a.deptno = b.deptno and a.ename='SCOTT';

-- ★★★★★시험문제★★★★★ (2,3개의 테이블을 조인하는 법)
-- rentlist의 대여 건수의 대여 일자, 대여 도서번호, 대여 회원번호, 
-- 할인 금액을 출력하되, 도서의 제목,
-- 회원의 이름을 같이 출력하시오.
select  c.rentdate, c.bnum, a.subject, c.mnum, b.name, 
a.rentprice - c.discount as "매출금액"
from booklist a, memberlist b, rentlist c
where a.booknum = c.bnum and b.membernum=c.mnum;

-- non-equi join
-- 동일 컬럼이 없어서 다른 조건을 사용하여 조인
-- 조인 조건에 특정 범위내에 있는지를 조사하기 위해 조건절에 조인 조건을 
-- '=' 연산자 이외의 비교 연산자를 이용
select * from emp;
select * from salgrade;

select a.ename, a.sal, b.grade from emp a, salgrade b
where a.sal > b.losal and a.sal <= b.hisal;

-- ★★★★★시험문제★★★★★ 
-- outer join
-- 조인 조건에 만족하지 못해서 해당 결과를 출력시에 누락이 되는 
-- 문제점이 발생할 때 해당 레코드를 출력하는 조인

select a.booknum, a.subject, b.rentdate from booklist a, rentlist b
where a.booknum = b.bnum(+);

-- outer 조인으로 emp 테이블의 인원에 대한 부서명을 출력하되 
-- 부서명이 없는 필드는 NULL 값으로 표시하세요
select * from emp a, dept b
where a.deptno(+) = b.deptno;


--[3] ANSI join

-- (1) Ansi Cross Join
select * from emp, dept -- 일반 크로스 조인 표현
select * from emp cross join dept  -- Ansi Cross Join  --> 일반 크로스 조인과 같은 효과


-- (2)  Ansi inner Join    -- 일반 equi 조인과 같은 효과
-- 일반 equi 조인 표현 방식
select ename, dname from emp a, dept b where a.deptno = b.deptno

-- Ansi 이너 조인의 표현 방식
select ename, dname from emp inner join dept on emp. deptno = dept.deptno;
select ename, dname from emp inner join dept on emp. deptno = dept.deptno
wherer ename = 'SCOTT';

-- Ansi 이너 조인의 다른 표현 방식
select ename, dname from emp inner join dept using (deptno);
-- 두 테이블의 조인 기준이 되는 필드명이 똑같은 떄만 사용가능

--(3) Ansi Outer Join -- 기존 아우터 조인의 표현 방식
select * from emp, dept where emp.deptno = dept.deptno(+);
select * from emp, dept where emp.deptno(+) = dept.deptno;

-- Ansi Outer Join 표현 방식
select * from emp left outer join dept on emp.deptno = dept.deptno;
select * from emp right outer join dept on emp.deptno = dept.deptno;
-- 기준이 되는 필드명 중 A 테이블의 필드에는 있으나 B 테이블 필드에는 
-- 해당값이 없는 경우에 대한 표현여부결정



















