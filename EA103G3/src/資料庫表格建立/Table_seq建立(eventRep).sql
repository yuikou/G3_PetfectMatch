
                                          --建立表格: eventRep--
----------------------------------------------------------------------------------------------------------------------------
alter session set deferred_segment_creation=false;

DROP SEQUENCE eventRepNo_seq;
DROP TABLE eventRep;


CREATE TABLE eventRep (
 EVENTREPNO         VARCHAR2(5) 	NOT NULL,
 EVENTNO          	VARCHAR2(5) 	NOT NULL,
 MEMNO        		VARCHAR2(4) 	NOT NULL,
 EVENTREP			VARCHAR2(300) 	NOT NULL,
 EVENTREPSTATUS		NUMBER(1) 		NOT NULL,
 EVENTREPRESULT		NUMBER(1),
 CONSTRAINT EVENTREP_EVENTREPNO_PK 	PRIMARY KEY (EVENTREPNO),
 CONSTRAINT EVENTREP_EVENTNO_FK 	FOREIGN KEY (EVENTNO) REFERENCES EVENT (EVENTNO),
 CONSTRAINT EVENTREP_MEMNO_FK 		FOREIGN KEY (MEMNO) REFERENCES MEMBER_TABLE (MEMNO));
 
 
CREATE SEQUENCE eventRepNo_seq
INCREMENT BY 1
START WITH 1
MAXVALUE 999
NOCYCLE
NOCACHE;

INSERT INTO eventRep (EVENTREPNO, EVENTNO, MEMNO, EVENTREP, EVENTREPSTATUS, EVENTREPRESULT) 
			VALUES ('ER' || lpad(eventRepNo_seq.NEXTVAL, 3, '0'), 'E001', 'M001', 'xxxxx', '1', '0');
INSERT INTO eventRep (EVENTREPNO, EVENTNO, MEMNO, EVENTREP, EVENTREPSTATUS, EVENTREPRESULT) 
			VALUES ('ER' || lpad(eventRepNo_seq.NEXTVAL, 3, '0'), 'E001', 'M002', 'xxxxx', '1', '0');
INSERT INTO eventRep (EVENTREPNO, EVENTNO, MEMNO, EVENTREP, EVENTREPSTATUS) 
			VALUES ('ER' || lpad(eventRepNo_seq.NEXTVAL, 3, '0'), 'E001', 'M003', 'xxxxx', '0');
INSERT INTO eventRep (EVENTREPNO, EVENTNO, MEMNO, EVENTREP, EVENTREPSTATUS, EVENTREPRESULT) 
			VALUES ('ER' || lpad(eventRepNo_seq.NEXTVAL, 3, '0'), 'E002', 'M001', 'xxxxx', '1', '0');
INSERT INTO eventRep (EVENTREPNO, EVENTNO, MEMNO, EVENTREP, EVENTREPSTATUS) 
			VALUES ('ER' || lpad(eventRepNo_seq.NEXTVAL, 3, '0'), 'E002', 'M004', 'xxxxx', '0');

commit;
----------------------------------------------------------------------------------------------------------------------------