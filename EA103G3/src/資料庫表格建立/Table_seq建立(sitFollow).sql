
                                          --建立表格: sitFollow--
----------------------------------------------------------------------------------------------------------------------------
alter session set deferred_segment_creation=false;

DROP TABLE sitFollow;


CREATE TABLE sitFollow (
 MEMNO		VARCHAR2(4) NOT NULL,
 SITNO      VARCHAR2(4) NOT NULL,
 CONSTRAINT SITFOLLOW_2PK 		PRIMARY KEY (MEMNO, SITNO),
 CONSTRAINT SITFOLLOW_MEMNO_FK 	FOREIGN KEY (MEMNO) REFERENCES MEMBER_TABLE (MEMNO),
 CONSTRAINT SITFOLLOW_SITNO_FK 	FOREIGN KEY (SITNO) REFERENCES PETSITTER (SITNO));
 
 
INSERT INTO sitFollow VALUES ('M001', 'S001');
INSERT INTO sitFollow VALUES ('M001', 'S002');
INSERT INTO sitFollow VALUES ('M001', 'S003');
INSERT INTO sitFollow VALUES ('M001', 'S004');
INSERT INTO sitFollow VALUES ('M002', 'S001');
INSERT INTO sitFollow VALUES ('M002', 'S004');

commit;
----------------------------------------------------------------------------------------------------------------------------