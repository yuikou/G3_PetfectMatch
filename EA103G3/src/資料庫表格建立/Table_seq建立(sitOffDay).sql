
                                          --建立表格: sitOffDay--
----------------------------------------------------------------------------------------------------------------------------
alter session set deferred_segment_creation=false;

DROP TABLE sitOffDay;
DROP SEQUENCE offDayNo_seq;


CREATE TABLE sitOffDay (
 OFFDAYNO           VARCHAR2(5) NOT NULL,
 SITSRVNO           VARCHAR2(5) NOT NULL,
 OFFDAY				Date NOT NULL,
 OFFTIME			VARCHAR2(5),
 OFFDAYTYP			NUMBER(1) NOT NULL,
 CONSTRAINT SITOFFDAY_OFFDAYNO_PK 	PRIMARY KEY (OFFDAYNO),
 CONSTRAINT SITOFFDAY_SITSRVNO_FK 	FOREIGN KEY (SITSRVNO) 	REFERENCES SITRV (SITSRVNO));
 
CREATE SEQUENCE offDayNo_seq
INCREMENT BY 1
START WITH 1
MAXVALUE 999
NOCYCLE
NOCACHE;

INSERT INTO sitOffDay VALUES ('SD' || lpad(offDayNo_seq.NEXTVAL, 3, '0'), 'SS001', to_date('2020-11-13', 'yyyy-mm-dd'), '', 0);
INSERT INTO sitOffDay VALUES ('SD' || lpad(offDayNo_seq.NEXTVAL, 3, '0'), 'SS002', to_date('2020-11-13', 'yyyy-mm-dd'), '', 0);
INSERT INTO sitOffDay VALUES ('SD' || lpad(offDayNo_seq.NEXTVAL, 3, '0'), 'SS003', to_date('2020-11-13', 'yyyy-mm-dd'), '', 0);
INSERT INTO sitOffDay VALUES ('SD' || lpad(offDayNo_seq.NEXTVAL, 3, '0'), 'SS004', to_date('2020-11-13', 'yyyy-mm-dd'), '', 0);
INSERT INTO sitOffDay VALUES ('SD' || lpad(offDayNo_seq.NEXTVAL, 3, '0'), 'SS005', to_date('2020-11-13', 'yyyy-mm-dd'), '0800', 1);
INSERT INTO sitOffDay VALUES ('SD' || lpad(offDayNo_seq.NEXTVAL, 3, '0'), 'SS005', to_date('2020-11-13', 'yyyy-mm-dd'), '0900', 1);
INSERT INTO sitOffDay VALUES ('SD' || lpad(offDayNo_seq.NEXTVAL, 3, '0'), 'SS005', to_date('2020-11-13', 'yyyy-mm-dd'), '1000', 1);

commit;
----------------------------------------------------------------------------------------------------------------------------