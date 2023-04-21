-- 12_View.sql

-- * 오라클 - 뷰(View)
--		- 물리적인 테이블에 근거한 논리적인 "가상 테이블." -> 저장되어 있는 select 문
--		- 가상이란 단어는 실질적으로 데이터를 저장하고 있지 않기 때문에 붙인 것이고,
--		  테이블이란 단어는 실질적으로 데이터를 저장하고 있지 않더라도 사용 계정자는 마치
--		  테이블을 사용하는 것과 동일하게 뷰를 사용할 수 있기 때문에 붙인 것
--		- 뷰는 기본테이블에서 파생된 객체로서 기본테이블에 대한 하나의 쿼리문임.
--		- 실제 테이블에 저장된 데이터를 뷰를 통해서 볼 수 있도록 함.
--		- 사용자에게 주어진 뷰를 통해서 기본 테이블을 제한적으로 사용하게 됨.
--		- 뷰는 이미 존재하고 있는 테이블에 제한적으로 접근하도록 한다.
--		- 뷰를 생성하기 위해서는 실질적으로 데이터를 저장하고 있는 물리적인 테이블이
--		  존재해야 되는데 이 테이블은 기본테이블이라고 한다.

-- 뷰 생성 방법
-- create or replace view 뷰이름 as select 조회 명령............
-- -> 결과는 select 의 결과를 테이블로 내어 놓는 가상테이블 제작 명령이 생기는 셈입니다.
-- 뷰 이름으로 조회명령을 저장하고 있다가 뷰이름으로 조회할때마다 조회 명령이 실행되어 
-- 결과를 내놓습니다.
select * from booklist;
create or replace view result_inner_join as
select a.empno, a.ename, a.job, a.hiredate, a.deptno, b.dname, b.loc
from emp a, dept b
where a.deptno = b.deptno;

-- 테이블 저장이 아니라 select 문 저장으로써, 만들어진 뷰를 또 다른 select로 조회할때마다
-- 저장된 select 가 실행되며 결과를 보여줍니다.
select * from result_inner_join where job = 'MANAGER';
-- where 를 붙여서 결과내에서 또 다른 조건을 사용할 수 있습니다.

-- create or replace view result_inner_join 을 사용하여 다른 select 문을 적용하고 실행하면
-- replace 명령이 동작하여 result_inner_join 라는 이름으로 뷰 내용이 대체됩니다.
-- 최초실행은 create 실행 같은 이름의 두번째 실행부터는 replace 실행























