
                                          --建立表格: sitSrv--
----------------------------------------------------------------------------------------------------------------------------
alter session set deferred_segment_creation=false;

DROP SEQUENCE sitSrv_seq;
DROP TABLE sitSrv;


CREATE TABLE sitSrv (
 SITSRVNO             VARCHAR2(5) 	NOT NULL,
 SITSRVNAME           VARCHAR2(60) 	NOT NULL,
 SITSRVCODE		      VARCHAR2(8) 	NOT NULL,
 SITNO                VARCHAR2(4) 	NOT NULL,
 SRVFEE			      NUMBER(5) 	NOT NULL,
 SRVINFO			  VARCHAR2(150),
 SRVAREA			  NUMBER(1),
 ACPPETNUM    		  NUMBER(2),
 ACPPETTYP		      NUMBER(1),
 CARELEVEL			  NUMBER(1),
 STAYLOC			  NUMBER(1),
 OVERNIGHTLOC		  NUMBER(1),
 SMKFREE			  NUMBER(1),
 HASCHILD			  NUMBER(1),
 WALKTIME			  NUMBER(1),
 EQPT				  NUMBER(1),
 ADDBATHING			  NUMBER(1),
 ADDPICKUP			  NUMBER(1),
 OUTOFSRV			  NUMBER(1) 	NOT NULL,
 ISDEL				  NUMBER(1) 	NOT NULL,
 
 CONSTRAINT SITSRV_SITSRVNO_PK 	PRIMARY KEY (SITSRVNO),
 CONSTRAINT SITSRV_SITNO_FK 	FOREIGN KEY (SITNO) REFERENCES PETSITTER (SITNO));
 
 CREATE SEQUENCE sitSrv_seq
 INCREMENT BY 1
 START WITH 1
 MAXVALUE 999
 NOCYCLE
 NOCACHE;

 
 INSERT INTO sitSrv
 (SITSRVNO, SITSRVNAME, SITSRVCODE, SITNO, SRVFEE, SRVINFO, SRVAREA, ACPPETNUM, ACPPETTYP,
  CARELEVEL, STAYLOC, OVERNIGHTLOC, SMKFREE, HASCHILD, ADDBATHING, ADDPICKUP, OUTOFSRV, ISDEL)
  VALUES ('SS' || lpad(sitSrv_seq.NEXTVAL, 3, '0'), '郭老師的家', 'Boarding', 'S001', '500', '* 可以上沙發;* 可以上床;* 一天提供三次餵食;* 一天提供兩次大小便', '1', '3', '1',
  '1', '1', '1', '1', '1', '0', '0', '0', '0');
 INSERT INTO sitSrv
 (SITSRVNO, SITSRVNAME, SITSRVCODE, SITNO, SRVFEE, SRVINFO, SRVAREA, ACPPETNUM, ACPPETTYP,
  CARELEVEL, STAYLOC, SMKFREE, HASCHILD, ADDBATHING, ADDPICKUP, OUTOFSRV, ISDEL)
  VALUES ('SS' || lpad(sitSrv_seq.NEXTVAL, 3, '0'), '郭老師的家', 'DayCare', 'S001', '400', 'ttt', '1', '3', '1',
  '1', '1', '1', '1', '0', '0', '0', '0');
 INSERT INTO sitSrv
 (SITSRVNO, SITSRVNAME, SITSRVCODE, SITNO, SRVFEE, SRVINFO, SRVAREA, ACPPETNUM, ACPPETTYP, ADDBATHING, OUTOFSRV, ISDEL)
  VALUES ('SS' || lpad(sitSrv_seq.NEXTVAL, 3, '0'), '郭老師的家', 'DropIn', 'S001', '200', '* 探訪時間約30分鐘，有加洗澡多30分鐘;* 會幫忙清潔寵物便便', '1', '2', '1', '1', '0', '0');
 INSERT INTO sitSrv
 (SITSRVNO, SITSRVNAME, SITSRVCODE, SITNO, SRVFEE, SRVINFO, SRVAREA, ACPPETNUM, ACPPETTYP, WALKTIME, OUTOFSRV, ISDEL)
  VALUES ('SS' || lpad(sitSrv_seq.NEXTVAL, 3, '0'), '郭老師的家', 'DogWalking', 'S001', '100', '* 約散步40分鐘;* 可清狗便;* 可拍照記錄', '1', '2', '2', '1', '0', '0');
 INSERT INTO SITSRV
 (SITSRVNO, SITSRVNAME, SITSRVCODE, SITNO, SRVFEE, SRVINFO, SRVAREA, ACPPETNUM, ACPPETTYP, EQPT, OUTOFSRV, ISDEL)
  VALUES ('SS' || lpad(sitSrv_seq.NEXTVAL, 3, '0'), '郭老師的家', 'PetTaxi', 'S001', '30', '', '2', '1', '1', '2', '0', '0');
 INSERT INTO sitSrv
 (SITSRVNO, SITSRVNAME, SITSRVCODE, SITNO, SRVFEE, SRVINFO, SRVAREA, ACPPETNUM, ACPPETTYP,
  CARELEVEL, STAYLOC, OVERNIGHTLOC, SMKFREE, HASCHILD, ADDBATHING, ADDPICKUP, OUTOFSRV, ISDEL)
  VALUES ('SS' || lpad(sitSrv_seq.NEXTVAL, 3, '0'), '潘老師的窩', 'Boarding', 'S002', '450', '* 一天提供兩次餵食;* 一天提供兩次大小便', '2', '2', '2',
  '2', '2', '2', '0', '0', '1', '1', '0', '0');
 INSERT INTO sitSrv
 (SITSRVNO, SITSRVNAME, SITSRVCODE, SITNO, SRVFEE, SRVINFO, SRVAREA, ACPPETNUM, ACPPETTYP,
  CARELEVEL, STAYLOC, SMKFREE, HASCHILD, ADDBATHING, ADDPICKUP, OUTOFSRV, ISDEL)
  VALUES ('SS' || lpad(sitSrv_seq.NEXTVAL, 3, '0'), '潘老師的窩', 'DayCare', 'S002', '350', 'xxxxxxxxxxxxxx', '2', '2', '2',
  '2', '2', '0', '0', '1', '1', '0', '0');
 INSERT INTO sitSrv
 (SITSRVNO, SITSRVNAME, SITSRVCODE, SITNO, SRVFEE, SRVINFO, OUTOFSRV, ISDEL)
  VALUES ('SS' || lpad(sitSrv_seq.NEXTVAL, 3, '0'), '潘老師的窩', 'Bathing', 'S002', '100', '* 請準備好盥洗用品', '0', '0');
 INSERT INTO sitSrv
 (SITSRVNO, SITSRVNAME, SITSRVCODE, SITNO, SRVFEE, SRVINFO, OUTOFSRV, ISDEL)
  VALUES ('SS' || lpad(sitSrv_seq.NEXTVAL, 3, '0'), '潘老師的窩', 'Pickup', 'S002', '30', '* 來回接送算一趟', '0', '0');
  
  commit;
----------------------------------------------------------------------------------------------------------------------------