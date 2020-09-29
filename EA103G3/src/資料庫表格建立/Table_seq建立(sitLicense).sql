
                                          --建立表格: sitLic--
----------------------------------------------------------------------------------------------------------------------------
alter session set deferred_segment_creation=false;

DROP SEQUENCE licNo_seq;
DROP TABLE sitLic;


CREATE TABLE sitLic (
 LICNO          VARCHAR2(5) NOT NULL,
 SITNO          VARCHAR2(4) NOT NULL,
 LICNAME        VARCHAR2(60) NOT NULL,
 LICPIC			BLOB NOT NULL,
 LICEXP			DATE,
 LICSTATUS		NUMBER(1) NOT NULL,
 CONSTRAINT SITLIC_LICNO_PK PRIMARY KEY (LICNO),
 CONSTRAINT SITLIC_SITNO_FK FOREIGN KEY (SITNO) REFERENCES PETSITTER (SITNO));
 
 
CREATE SEQUENCE licNo_seq
INCREMENT BY 1
START WITH 1
MAXVALUE 999
NOCYCLE
NOCACHE;

INSERT INTO sitLic (LICNO, SITNO, LICNAME, LICEXP, LICSTATUS)   VALUES ('SL' || lpad(licNo_seq.NEXTVAL, 3, '0'), 'S001', '特定寵物業許可證', to_date('2023-09-13', 'yyyy-mm-dd'), '0');
INSERT INTO sitLic (LICNO, SITNO, LICNAME, LICSTATUS)           VALUES ('SL' || lpad(licNo_seq.NEXTVAL, 3, '0'), 'S001', 'aaa', '1');
INSERT INTO sitLic (LICNO, SITNO, LICNAME, LICSTATUS)           VALUES ('SL' || lpad(licNo_seq.NEXTVAL, 3, '0'), 'S001', 'bbb', '2');
INSERT INTO sitLic (LICNO, SITNO, LICNAME, LICEXP, LICSTATUS)   VALUES ('SL' || lpad(licNo_seq.NEXTVAL, 3, '0'), 'S002', 'ccc', to_date('2022-12-31', 'yyyy-mm-dd'), '0');
INSERT INTO sitLic (LICNO, SITNO, LICNAME, LICSTATUS)           VALUES ('SL' || lpad(licNo_seq.NEXTVAL, 3, '0'), 'S002', 'ddd', '1');

commit;
----------------------------------------------------------------------------------------------------------------------------