
                                          --�إߪ��: sitSrv--
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
  VALUES ('SS' || lpad(sitSrv_seq.NEXTVAL, 3, '0'), '���Ѯv���a', 'Boarding', 'S001', '500', '* �i�H�W�F�o;* �i�H�W��;* �@�Ѵ��ѤT������;* �@�Ѵ��Ѩ⦸�j�p�K', '1', '3', '1',
  '1', '1', '1', '1', '1', '0', '0', '0', '0');
 INSERT INTO sitSrv
 (SITSRVNO, SITSRVNAME, SITSRVCODE, SITNO, SRVFEE, SRVINFO, SRVAREA, ACPPETNUM, ACPPETTYP,
  CARELEVEL, STAYLOC, SMKFREE, HASCHILD, ADDBATHING, ADDPICKUP, OUTOFSRV, ISDEL)
  VALUES ('SS' || lpad(sitSrv_seq.NEXTVAL, 3, '0'), '���Ѯv���a', 'DayCare', 'S001', '400', 'ttt', '1', '3', '1',
  '1', '1', '1', '1', '0', '0', '0', '0');
 INSERT INTO sitSrv
 (SITSRVNO, SITSRVNAME, SITSRVCODE, SITNO, SRVFEE, SRVINFO, SRVAREA, ACPPETNUM, ACPPETTYP, ADDBATHING, OUTOFSRV, ISDEL)
  VALUES ('SS' || lpad(sitSrv_seq.NEXTVAL, 3, '0'), '���Ѯv���a', 'DropIn', 'S001', '200', '* ���X�ɶ���30�����A���[�~���h30����;* �|�����M���d���K�K', '1', '2', '1', '1', '0', '0');
 INSERT INTO sitSrv
 (SITSRVNO, SITSRVNAME, SITSRVCODE, SITNO, SRVFEE, SRVINFO, SRVAREA, ACPPETNUM, ACPPETTYP, WALKTIME, OUTOFSRV, ISDEL)
  VALUES ('SS' || lpad(sitSrv_seq.NEXTVAL, 3, '0'), '���Ѯv���a', 'DogWalking', 'S001', '100', '* �����B40����;* �i�M���K;* �i��ӰO��', '1', '2', '2', '1', '0', '0');
 INSERT INTO SITSRV
 (SITSRVNO, SITSRVNAME, SITSRVCODE, SITNO, SRVFEE, SRVINFO, SRVAREA, ACPPETNUM, ACPPETTYP, EQPT, OUTOFSRV, ISDEL)
  VALUES ('SS' || lpad(sitSrv_seq.NEXTVAL, 3, '0'), '���Ѯv���a', 'PetTaxi', 'S001', '30', '', '2', '1', '1', '2', '0', '0');
 INSERT INTO sitSrv
 (SITSRVNO, SITSRVNAME, SITSRVCODE, SITNO, SRVFEE, SRVINFO, SRVAREA, ACPPETNUM, ACPPETTYP,
  CARELEVEL, STAYLOC, OVERNIGHTLOC, SMKFREE, HASCHILD, ADDBATHING, ADDPICKUP, OUTOFSRV, ISDEL)
  VALUES ('SS' || lpad(sitSrv_seq.NEXTVAL, 3, '0'), '��Ѯv����', 'Boarding', 'S002', '450', '* �@�Ѵ��Ѩ⦸����;* �@�Ѵ��Ѩ⦸�j�p�K', '2', '2', '2',
  '2', '2', '2', '0', '0', '1', '1', '0', '0');
 INSERT INTO sitSrv
 (SITSRVNO, SITSRVNAME, SITSRVCODE, SITNO, SRVFEE, SRVINFO, SRVAREA, ACPPETNUM, ACPPETTYP,
  CARELEVEL, STAYLOC, SMKFREE, HASCHILD, ADDBATHING, ADDPICKUP, OUTOFSRV, ISDEL)
  VALUES ('SS' || lpad(sitSrv_seq.NEXTVAL, 3, '0'), '��Ѯv����', 'DayCare', 'S002', '350', 'xxxxxxxxxxxxxx', '2', '2', '2',
  '2', '2', '0', '0', '1', '1', '0', '0');
 INSERT INTO sitSrv
 (SITSRVNO, SITSRVNAME, SITSRVCODE, SITNO, SRVFEE, SRVINFO, OUTOFSRV, ISDEL)
  VALUES ('SS' || lpad(sitSrv_seq.NEXTVAL, 3, '0'), '��Ѯv����', 'Bathing', 'S002', '100', '* �зǳƦn�d�~�Ϋ~', '0', '0');
 INSERT INTO sitSrv
 (SITSRVNO, SITSRVNAME, SITSRVCODE, SITNO, SRVFEE, SRVINFO, OUTOFSRV, ISDEL)
  VALUES ('SS' || lpad(sitSrv_seq.NEXTVAL, 3, '0'), '��Ѯv����', 'Pickup', 'S002', '30', '* �Ӧ^���e��@��', '0', '0');
  
  commit;
----------------------------------------------------------------------------------------------------------------------------