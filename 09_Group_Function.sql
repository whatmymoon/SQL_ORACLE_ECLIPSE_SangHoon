-- 09_Group_Function.sql

-- sum() : 특정 필드의 합계
select sum( rentprice ) as "대여가격 합계" from booklist;
select sum( rentprice ) as "대여가격 합계" from booklist where inprice >= 18000;


-- count(*) : 필드내의 데이터 갯수(레코드 갯수)
select count(*) as "회원인원수" from memberlist;
select count(*) as "회원인원수" from memberlist where bpoint >= 2000;

-- avg : 평균
select round( AVG(inprice), 0) from booklist;

-- MAX : 최대값
select MAX(inprice) from booklist;

-- MIN : 최소값
select min(inprice) from booklist;

-- VARIANCE(분산), STDDEV(표준편차)
select variance(salary), stddev(salary) from employees;
select salary from employees;


-- group by : 하나의 필드를 지목해서 같은 값끼리 그룹을 형성한 결과를 도출합니다.
-- 도서별 대여 건수
select count(*) from rentlist; -- 전체 대여건수
select bnum, count(*) from rentlist group by bnum;
-- 각 도서별 대여건수:한번도 대여한 적 없는 도서 제외
-- *** group by 에 사용된 필드는 select 와 from 사이에 반드시 표기되어야 합니다.
-- (* 라도 써서 포함시킵니다)

-- rentlist 날짜별 할인금액의 평균
select * from rentlist;
select rentdate, avg(discount) as "할인금액평균" from rentlist group by rentdate
order by rentdate;

-- rentlist 날짜별 대여 건수
select rentdate as 대여날짜, count(*) as "대여건수" from rentlist group by rentdate 
order by rentdate;

-- employees 테이블의 부서id(department_id)별 급여(salary)의 평균
select * from employees;
select department_id, round(avg(salary),0) as "부서별 급여 평균" from employees
group by department_id order by department_id;


-- kor_loan_status 테이블의 period( 년도와 월 ) 을 1차 그룹으로 region(지역) 을
-- 2차 그룹으로 한 대출 잔액(loan_jan_amt)의 합계
select * from kor_loan_status;

select period, region, sum(loan_jan_amt) as "대출 잔액합계", count(*)
from kor_loan_status
group by period, region
order by period, region;

select period, count(*) from kor_loan_status group by period order by period;


-- HAVING 절 : 그룹핑된 내용들에 조건을 붙일때
-- 날짜별 할인금액의 평균을 출력합니다. 다만 그 평균 금액이 150 미만인 그룹만 출력
select rentdate as 날짜, avg(discount) as 할인평균, count(*) as 건수 from RENTLIST
group by rentdate
having avg(discount) < 150
order by rentdate;

-- kor_loan_status 테이블의 날짜별 대출 잔액의 합계 중 period 가 2013년 11월 인
-- 데이터만 출력
select period as 대출날짜, region as 대출지역, 
sum(loan_jan_amt) as "대출 잔액의 합계", count(*) as 건수
from kor_loan_status
where period = '201311'
group by period, region
order by region;


















