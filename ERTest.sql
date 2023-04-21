
/* Drop Triggers */

DROP TRIGGER TRI_booklist_booknum;
DROP TRIGGER TRI_memberlist_membernum;
DROP TRIGGER TRI_rentlist_numseq;



/* Drop Tables */

DROP TABLE rentlist CASCADE CONSTRAINTS;
DROP TABLE booklist CASCADE CONSTRAINTS;
DROP TABLE memberlist CASCADE CONSTRAINTS;



/* Drop Sequences */

DROP SEQUENCE SEQ_booklist_booknum;
DROP SEQUENCE SEQ_memberlist_membernum;
DROP SEQUENCE SEQ_rentlist_numseq;




/* Create Sequences */

CREATE SEQUENCE SEQ_booklist_booknum INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_memberlist_membernum INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_rentlist_numseq INCREMENT BY 1 START WITH 1;



/* Create Tables */

CREATE TABLE booklist
(
	booknum number(5) NOT NULL,
	subject varchar2(100) NOT NULL,
	makeyear number(4),
	inprice number(6),
	rentprice number(4) DEFAULT 200,
	PRIMARY KEY (booknum)
);


CREATE TABLE memberlist
(
	membernum number(5) NOT NULL,
	name nvarchar2(30) NOT NULL,
	birth date,
	phone varchar2(15) NOT NULL,
	bpoint number(4),
	PRIMARY KEY (membernum)
);


CREATE TABLE rentlist
(
	numseq number(5) NOT NULL,
	rentdate date DEFAULT sysdate NOT NULL,
	bnum number(5) NOT NULL,
	mnum number(5) NOT NULL,
	discount number(4) DEFAULT 100,
	PRIMARY KEY (numseq)
);



/* Create Foreign Keys */

ALTER TABLE rentlist
	ADD FOREIGN KEY (bnum)
	REFERENCES booklist (booknum)
;


ALTER TABLE rentlist
	ADD FOREIGN KEY (mnum)
	REFERENCES memberlist (membernum)
;



/* Create Triggers */

CREATE OR REPLACE TRIGGER TRI_booklist_booknum BEFORE INSERT ON booklist
FOR EACH ROW
BEGIN
	SELECT SEQ_booklist_booknum.nextval
	INTO :new.booknum
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_memberlist_membernum BEFORE INSERT ON memberlist
FOR EACH ROW
BEGIN
	SELECT SEQ_memberlist_membernum.nextval
	INTO :new.membernum
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_rentlist_numseq BEFORE INSERT ON rentlist
FOR EACH ROW
BEGIN
	SELECT SEQ_rentlist_numseq.nextval
	INTO :new.numseq
	FROM dual;
END;

/




