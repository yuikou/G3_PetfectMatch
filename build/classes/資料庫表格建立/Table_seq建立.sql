DROP SEQUENCE SALNO_SEQ;
DROP SEQUENCE SALORDER_SEQ;
DROP SEQUENCE SALREPNO_SEQ;
DROP SEQUENCE SALALBNO_SEQ;
DROP SEQUENCE mem_seq;
DROP SEQUENCE pet_seq;
DROP SEQUENCE SALSER_SEQ;
DROP SEQUENCE GROOMER_SEQ;
DROP SEQUENCE GMOFF_SEQ;

DROP TABLE SALONREPORT;
DROP TABLE SALONALBUM;
DROP TABLE SALONFOLLOW;
DROP TABLE SALONORDERDETAIL;
DROP TABLE SALONORDER;
DROP TABLE GROOMEROFFDAY;
DROP TABLE SALONSERVICE;
DROP TABLE GROOMER;
DROP TABLE SALON;
DROP TABLE PET;
DROP TABLE member_table;

--會員
CREATE TABLE member_table(
 memNo				varchar2(4) not null,
 memName			varchar2(30) not null,
 memBirth			date not null,
 memSex				number(1) not null check(memSex in (0, 1, 2)),
 memPhone			VARCHAR2(15) not null,
 memEmail			varchar2(15) not null,
 memAddress			varchar2(60) not null,
 memId				varchar2(16) not null,
 memPsw				varchar2(16) not null,
 memPoint			number(5) default 0 not null check(memPoint>=0),
 memAuthority			number(1) default 0 not null check(memAuthority in (0, 1)),
 memStatus			number(1) default 0 not null check(memStatus in(0, 1)),
 memNickname 			varchar2(30),
 memPhoto			blob,
 CONSTRAINT MEMBER_TABLE_memNo_PK primary key (memNo)
);

create sequence mem_seq
	increment by 1
	start with 1
	nomaxvalue
	nocycle
	nocache;

--寵物  
CREATE TABLE pet(
 petNo			varchar2(6) not null,
 memNo 			varchar2(4) not null,
 petName		varchar2(20),
 petType		number(1) not null check(petType IN (0, 1, 2)),
 petCat			number(1) not null check(petCat IN (0, 1, 2, 3, 5, 6)),
 petSex			number(1) not null check(petSex IN (0, 1)),
 petBirth		date,
 petChar		varchar2(600),
 isDel			number(1) default 0 not null check(isDel IN (0, 1)),
 constraint pet_petNo_PK primary key (petNo),
 CONSTRAINT mem_memNo_FK FOREIGN KEY (memNo) REFERENCES MEMBER_TABLE (memNo)
);

create sequence pet_seq
	increment by 1
	start with 1
	nomaxvalue
	nocycle
	nocache;
    
--美容店業者
CREATE TABLE SALON (
SALNO VARCHAR2(4) NOT NULL,
SALNAME VARCHAR2(30) NOT NULL,
SALOWNER VARCHAR2(20),
SALPH VARCHAR2(15) NOT NULL,
SALMAIL VARCHAR2(30) NOT NULL,
SALADR VARCHAR2(50) NOT NULL,
SALAC VARCHAR2(16) NOT NULL,
SALPW VARCHAR2(16) NOT NULL,
SALCERTIF BLOB ,
SALSTIME TIMESTAMP NOT NULL,
SALETIME TIMESTAMP NOT NULL,
SALREMIT VARCHAR2(12) NOT NULL ,
BANKCODE NUMBER(3) NOT NULL,
SALSTATUS  NUMBER(1) NOT NULL,
SALTOTALSCORE NUMBER(10) ,
SALTOTALCOUNT NUMBER(10),
SALINFO VARCHAR2(600) NOT NULL,
CONSTRAINT SALNO_PK PRIMARY KEY(SALNO));

CREATE SEQUENCE SALNO_SEQ
INCREMENT BY 1
START WITH 1
MAXVALUE 999
NOCYCLE
NOCACHE;

--美容項目
CREATE TABLE SALONSERVICE(
SALSEVNO VARCHAR2(5)NOT NULL,
SALNO VARCHAR2(4)NOT NULL,
PETCAT NUMBER(1)NOT NULL, --CHECK(PETCAT>0)
SALSEVNAME VARCHAR2(30)NOT NULL,
SALSEVTIME NUMBER(5)NOT NULL,
SALSEVPR NUMBER(5) NOT NULL,
STATUS NUMBER(1) NOT NULL,
CONSTRAINT salonService_PK PRIMARY KEY (SALSEVNO),
CONSTRAINT SAL_SER_FK FOREIGN KEY (SALNO) REFERENCES SALON(SALNO)
);

CREATE SEQUENCE SALSER_SEQ
INCREMENT BY 1
START WITH 1
MAXVALUE 999
NOCYCLE
NOCACHE;

--美容師
CREATE TABLE GROOMER(
GROOMERNO VARCHAR2(4)NOT NULL,
SALNO VARCHAR2(4)NOT NULL,
GROOMERNAME VARCHAR2(10)NOT NULL,
GROOMERINFO VARCHAR2(300),
ISDELETE NUMBER(1) NOT NULL,
CONSTRAINT GROOMER_PK PRIMARY KEY (GROOMERNO),
CONSTRAINT GM_SAL_FK FOREIGN KEY (SALNO) REFERENCES SALON(SALNO)
);

CREATE SEQUENCE GROOMER_SEQ
INCREMENT BY 1
START WITH 1
MAXVALUE 999
NOCYCLE
NOCACHE;

--美容師不可服務日期
CREATE TABLE GROOMEROFFDAY(
OFFNO VARCHAR2(10)NOT NULL,
GROOMERNO VARCHAR2(4)NOT NULL,
OFFDAY DATE,  --NOT NULL
OFFDAYTYPE NUMBER(1) NOT NULL CHECK(OFFDAYTYPE>=0),
CONSTRAINT GOFFDAY_PK PRIMARY KEY (OFFNO),
CONSTRAINT OFF_GM_FK FOREIGN KEY (GROOMERNO) REFERENCES GROOMER(GROOMERNO)
);

CREATE SEQUENCE GMOFF_SEQ
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;

--美容店作品集照片
CREATE TABLE SALONALBUM(
SALALBNO VARCHAR2(5) NOT NULL,
SALNO VARCHAR2(4) NOT NULL,
SALPIC BLOB,
SALPORTINFO VARCHAR2(60),
CONSTRAINT SALALBNO_PK PRIMARY KEY(SALALBNO),
CONSTRAINT SALNO_FK FOREIGN KEY (SALNO) REFERENCES SALON (SALNO));

CREATE SEQUENCE SALALBNO_SEQ
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;

--收藏美容業者
CREATE TABLE SALONFOLLOW(
MEMNO VARCHAR2(4)NOT NULL,
SALNO VARCHAR2(4)NOT NULL,
CONSTRAINT MEMNO_SALNO_PK PRIMARY KEY (MEMNO,SALNO),
CONSTRAINT MEMNOFO_FK FOREIGN KEY (MEMNO) REFERENCES member_table(MEMNO),
CONSTRAINT SALNOFO_FK FOREIGN KEY (SALNO) REFERENCES SALON(SALNO));

--美容訂單
CREATE TABLE SALONORDER (
SALORDERNO VARCHAR2(5)NOT NULL,
MEMNO VARCHAR(4)NOT NULL,
SALNO VARCHAR2(4)NOT NULL,
PETNO VARCHAR2(4)NOT NULL,
SALORDERDATE DATE NOT NULL,
SALTP NUMBER(5) NOT NULL,
ORDERSTATUS NUMBER(1)NOT NULL,
REFUND NUMBER(5),
COUPON NUMBER (5),
SALORDERCT TIMESTAMP ,
SALSTAR NUMBER(1),
SALCOMMENT VARCHAR2(300),
CONSTRAINT SALORDERNO_PK PRIMARY KEY (SALORDERNO),
CONSTRAINT PETNOSAL_FK FOREIGN KEY (PETNO) REFERENCES PET(PETNO),
CONSTRAINT SALNOSAL_FK FOREIGN KEY (SALNO) REFERENCES SALON(SALNO),
CONSTRAINT MEMNO_FK FOREIGN KEY (MEMNO) REFERENCES member_table(MEMNO));

CREATE SEQUENCE SALORDER_SEQ
INCREMENT BY 1
START WITH 1
MAXVALUE 999
NOCYCLE
NOCACHE;

--美容訂單明細
CREATE TABLE SALONORDERDETAIL(
SALORDERNO VARCHAR2(5) NOT NULL ,
SALSEVNO VARCHAR2(5) NOT NULL,
GROOMERNO VARCHAR2(4) NOT NULL,
SALSEVPR NUMBER (5) NOT NULL,
CONSTRAINT SALORDERNO_SALSEVNO_PK PRIMARY KEY (SALORDERNO,SALSEVNO),
CONSTRAINT SALORDERNO_FK FOREIGN KEY (SALORDERNO) REFERENCES SALONORDER(SALORDERNO),
CONSTRAINT GROOMERNO_FK FOREIGN KEY (GROOMERNO) REFERENCES GROOMER(GROOMERNO)); 

--美容訂單檢舉
CREATE TABLE SALONREPORT(
SALREPNO VARCHAR2(5)NOT NULL,
SALORDERNO VARCHAR(5) NOT NULL,
MEMNO VARCHAR2(4)NOT NULL,
EMPNO VARCHAR2(6)NOT NULL,
SALREP VARCHAR2(300)NOT NULL,
SALREPSTATS NUMBER(1),
SALREPRESULT NUMBER(1),
CONSTRAINT SALREPNOPORT_PK PRIMARY KEY (SALREPNO),
CONSTRAINT SALORDERNOPORT_FK FOREIGN KEY (SALORDERNO) REFERENCES SALONORDER(SALORDERNO),
CONSTRAINT MEMNOPORT_FK FOREIGN KEY (MEMNO) REFERENCES member_table(MEMNO));

CREATE SEQUENCE SALREPNO_SEQ
INCREMENT BY 1
START WITH 1
MAXVALUE  999
NOCYCLE
NOCACHE;


--會員
insert into member_table (memNo, memName, memBirth, memSex, memPhone, memEmail, memAddress, memId, memPsw, memNickname, memPhoto) values ('M' || lpad(mem_seq.NEXTVAL, 3, '0'), '黃琦琦',  to_date('1994-1-17', 'yyyy-mm-dd'),  0,  '09-12345678',  'aaa@xxx.com',  '台北市信義區市府路100號101樓', 'king', '123456', 'king', null);
insert into member_table (memNo, memName, memBirth, memSex, memPhone, memEmail, memAddress, memId, memPsw, memNickname, memPhoto) values ('M' || lpad(mem_seq.NEXTVAL, 3, '0'), '方芳芳',  to_date('1994-05-01', 'yyyy-mm-dd'),  0,  '09-21957846',  'bbb@xxx.com',  '桃園市中壢區中央路300號', 'blake', '123456', 'blake', null);
insert into member_table (memNo, memName, memBirth, memSex, memPhone, memEmail, memAddress, memId, memPsw, memNickname, memPhoto) values ('M' || lpad(mem_seq.NEXTVAL, 3, '0'), '王大靖',  to_date('1994-01-09', 'yyyy-mm-dd'),  1,  '09-87652154',  'ccc@xxx.com',  '台中市慶濟路200號', 'clark', '123456', 'clark', null);
insert into member_table (memNo, memName, memBirth, memSex, memPhone, memEmail, memAddress, memId, memPsw, memNickname, memPhoto) values ('M' || lpad(mem_seq.NEXTVAL, 3, '0'), '林郁',  to_date('1994-04-02', 'yyyy-mm-dd'),  0,  '09-64913584',  'ddd@xxx.com',  '台南市找不到路100號', 'jones', '123456', 'jones', null);
insert into member_table (memNo, memName, memBirth, memSex, memPhone, memEmail, memAddress, memId, memPsw, memNickname, memPhoto) values ('M' || lpad(mem_seq.NEXTVAL, 3, '0'), '陳群杰',  to_date('1994-09-28', 'yyyy-mm-dd'),  1,  '09-35846291',  'eee@xxx.com',  '高雄市某條路10號', 'martin', '123456', 'martin', null);
insert into member_table (memNo, memName, memBirth, memSex, memPhone, memEmail, memAddress, memId, memPsw, memNickname, memPhoto) values ('M' || lpad(mem_seq.NEXTVAL, 3, '0'), '張元元',  to_date('1994-02-02', 'yyyy-mm-dd'),  1,  '09-34681752',  'fff@xxx.com',  '新北市巴巴路50號', 'allen', '123456', 'allen', null);
insert into member_table (memNo, memName, memBirth, memSex, memPhone, memEmail, memAddress, memId, memPsw, memNickname, memPhoto) values ('M' || lpad(mem_seq.NEXTVAL, 3, '0'), '林小晴',  to_date('1994-09-28', 'yyyy-mm-dd'),  0,  '09-82619473',  'ggg@xxx.com',  '新竹縣後田路30號', 'turner', '123456', 'turner', null);
insert into member_table (memNo, memName, memBirth, memSex, memPhone, memEmail, memAddress, memId, memPsw, memNickname, memPhoto) values ('M' || lpad(mem_seq.NEXTVAL, 3, '0'), '吳開司',  to_date('1994-12-03', 'yyyy-mm-dd'),  1,  '09-37486255',  'hhh@xxx.com',  '苗栗國梅投路40號', 'james', '123456','james', null);
insert into member_table (memNo, memName, memBirth, memSex, memPhone, memEmail, memAddress, memId, memPsw, memNickname, memPhoto) values ('M' || lpad(mem_seq.NEXTVAL, 3, '0'), '潘又真',  to_date('1994-02-22', 'yyyy-mm-dd'),  0,  '09-11886492',  'iii@xxx.com',  '彰化縣藍藍路20號', 'ward', '123456','ward', null);
insert into member_table (memNo, memName, memBirth, memSex, memPhone, memEmail, memAddress, memId, memPsw, memNickname, memPhoto) values ('M' || lpad(mem_seq.NEXTVAL, 3, '0'), '吳彼得',  to_date('1994-12-03', 'yyyy-mm-dd'),  1,  '09-76559482',  'jjj@xxx.com',  '雲林縣阿喜鐵路52巷13號14樓', 'ford', '123456','ford', null);
insert into member_table (memNo, memName, memBirth, memSex, memPhone, memEmail, memAddress, memId, memPsw, memNickname, memPhoto) values ('M' || lpad(mem_seq.NEXTVAL, 3, '0'), '吳大衛',  to_date('1993-12-17', 'yyyy-mm-dd'),  1,  '09-11269574',  'kkk@xxx.com',  '嘉義市蘇巴路800號', 'smith', '123456','smith', null);
insert into member_table (memNo, memName, memBirth, memSex, memPhone, memEmail, memAddress, memId, memPsw, memNickname, memPhoto) values ('M' || lpad(mem_seq.NEXTVAL, 3, '0'), '郭司福',  to_date('1994-12-09', 'yyyy-mm-dd'),  1,  '09-12594763',  'lll@xxx.com',  '宜蘭縣密之肝路99號', 'scott', '123456', 'scott', null);
insert into member_table (memNo, memName, memBirth, memSex, memPhone, memEmail, memAddress, memId, memPsw, memNickname, memPhoto) values ('M' || lpad(mem_seq.NEXTVAL, 3, '0'), '黃彬彬',  to_date('1983-01-12', 'yyyy-mm-dd'),  1,  '09-44685975',  'mmm@xxx.com',  '花蓮縣爬坡路88號', 'adams', '123456','adams', null);
insert into member_table (memNo, memName, memBirth, memSex, memPhone, memEmail, memAddress, memId, memPsw, memNickname, memPhoto) values ('M' || lpad(mem_seq.NEXTVAL, 3, '0'), '董咚咚',  to_date('1995-01-23', 'yyyy-mm-dd'),  0,  '09-15226345',  'ppp@xxx.com',  '台東縣下坡路120號', 'miller', '123456','miller', null);
insert into member_table (memNo, memName, memBirth, memSex, memPhone, memEmail, memAddress, memId, memPsw, memNickname, memPhoto) values ('M' || lpad(mem_seq.NEXTVAL, 3, '0'), '陳莉莉',  to_date('1996-06-24', 'yyyy-mm-dd'),  0,  '09-33486597',  'qqq@xxx.com',  '台北市內湖區仁愛路38號', 'alice', '123456','alice', null);

--寵物
INSERT INTO PET (PETNO,MEMNO,PETNAME,PETTYPE,PETCAT,PETSEX,ISDEL)VALUES('P' ||LPAD(pet_seq.NEXTVAL,'3',0),'M001','小黃',0,1,0,0);
INSERT INTO PET (PETNO,MEMNO,PETNAME,PETTYPE,PETCAT,PETSEX,ISDEL)VALUES('P' ||LPAD(pet_seq.NEXTVAL,'3',0),'M002','大白',1,1,1,0);
INSERT INTO PET (PETNO,MEMNO,PETNAME,PETTYPE,PETCAT,PETSEX,ISDEL)VALUES('P' ||LPAD(pet_seq.NEXTVAL,'3',0),'M003','鼠鼻',2,2,0,0);

--美容店業者
INSERT INTO SALON(SALNO,SALNAME,SALOWNER,SALPH,SALMAIL,SALADR,SALAC,SALPW,SALSTIME,SALETIME,SALREMIT,BANKCODE,SALSTATUS,SALINFO) VALUES('B' || lpad(SALNO_SEQ.NEXTVAL,3,'0'),'美容店1','DAVE',26856845,'aaa111@hotmail.com','新北市樹林區育英街19號','aaa111','aaa111',TO_DATE('8:00','HH24:MI'),TO_DATE('18:00','HH24:MI'),'151-11-45',015,0,'我是美容店1');
INSERT INTO SALON(SALNO,SALNAME,SALOWNER,SALPH,SALMAIL,SALADR,SALAC,SALPW,SALSTIME,SALETIME,SALREMIT,BANKCODE,SALSTATUS,SALINFO) VALUES('B' || lpad(SALNO_SEQ.NEXTVAL,3,'0'),'美容店2','JACK',26852345,'bbb222@hotmail.com','新北市板橋區中正路125號','bbb222','bbb222',TO_DATE('7:00','HH24:MI'),TO_DATE('17:00','HH24:MI'),'225-21-45',065,0,'我是美容店2');
INSERT INTO SALON(SALNO,SALNAME,SALOWNER,SALPH,SALMAIL,SALADR,SALAC,SALPW,SALSTIME,SALETIME,SALREMIT,BANKCODE,SALSTATUS,SALINFO)VALUES('B' || lpad(SALNO_SEQ.NEXTVAL,3,'0'),'美容店3','ALLEN',28454581,'ccc333@hotmail.com','新北市新莊區中山路1號','ccc333','ccc333',TO_DATE('6:00','HH24:MI'),TO_DATE('18:00','HH24:MI'),'365-11-95',034,0,'我是美容店3');
INSERT INTO SALON(SALNO,SALNAME,SALOWNER,SALPH,SALMAIL,SALADR,SALAC,SALPW,SALSTIME,SALETIME,SALREMIT,BANKCODE,SALSTATUS,SALINFO) VALUES('B' || lpad(SALNO_SEQ.NEXTVAL,3,'0'),'美容店4','JEFF',26869515,'ddd444@hotmail.com','新北市林口區幸福街9號','ddd444','ddd444',TO_DATE('8:00','HH24:MI'),TO_DATE('16:00','HH24:MI'),'954-11-45',065,0,'我是美容店4');
INSERT INTO SALON(SALNO,SALNAME,SALOWNER,SALPH,SALMAIL,SALADR,SALAC,SALPW,SALSTIME,SALETIME,SALREMIT,BANKCODE,SALSTATUS,SALINFO) VALUES('B' || lpad(SALNO_SEQ.NEXTVAL,3,'0'),'美容店5','ERIC',26665445,'eee555@hotmail.com','新北市三重區中華路88號','eee555','eee555',TO_DATE('9:00','HH24:MI'),TO_DATE('20:00','HH24:MI'),'658-11-45',098,0,'我是美容店5');

--美容師
INSERT INTO GROOMER(GROOMERNO, SALNO, GROOMERNAME, GROOMERINFO, ISDELETE) VALUES ('G' || lpad(GROOMER_SEQ.NEXTVAL, 3, '0'), 'B001', 'PETER', '毛小孩美容大神', 0);
INSERT INTO GROOMER(GROOMERNO, SALNO, GROOMERNAME, GROOMERINFO, ISDELETE) VALUES ('G' || lpad(GROOMER_SEQ.NEXTVAL, 3, '0'), 'B001', 'DAVID', '少林寺方丈御用美髮師', 0);
INSERT INTO GROOMER(GROOMERNO, SALNO, GROOMERNAME, GROOMERINFO, ISDELETE) VALUES ('G' || lpad(GROOMER_SEQ.NEXTVAL, 3, '0'), 'B002', 'MR.KUO', '毛小孩美容界第一把交椅', 0);
INSERT INTO GROOMER(GROOMERNO, SALNO, GROOMERNAME, GROOMERINFO, ISDELETE) VALUES ('G' || lpad(GROOMER_SEQ.NEXTVAL, 3, '0'), 'B002', 'MS.PAN', '修毛大師', 0);
--美容師不可服務日期
INSERT INTO GROOMEROFFDAY(OFFNO, GROOMERNO, OFFDAY, OFFDAYTYPE) VALUES ('GD' || lpad(GMOFF_SEQ.NEXTVAL, 3, '0'), 'G001', to_date('2020-10-10', 'yyyy-mm-dd'), 0);
INSERT INTO GROOMEROFFDAY(OFFNO, GROOMERNO, OFFDAY, OFFDAYTYPE) VALUES ('GD' || lpad(GMOFF_SEQ.NEXTVAL, 3, '0'), 'G001', to_date('2020-10-22', 'yyyy-mm-dd'), 1);
INSERT INTO GROOMEROFFDAY(OFFNO, GROOMERNO, OFFDAY, OFFDAYTYPE) VALUES ('GD' || lpad(GMOFF_SEQ.NEXTVAL, 3, '0'), 'G002', to_date('2020-10-20', 'yyyy-mm-dd'), 0);
INSERT INTO GROOMEROFFDAY(OFFNO, GROOMERNO, OFFDAY, OFFDAYTYPE) VALUES ('GD' || lpad(GMOFF_SEQ.NEXTVAL, 3, '0'), 'G003', to_date('2020-11-20', 'yyyy-mm-dd'), 1);
INSERT INTO GROOMEROFFDAY(OFFNO, GROOMERNO, OFFDAY, OFFDAYTYPE) VALUES ('GD' || lpad(GMOFF_SEQ.NEXTVAL, 3, '0'), 'G004', to_date('2020-10-25', 'yyyy-mm-dd'), 0);
--美容項目
INSERT INTO SALONSERVICE(SALSEVNO, SALNO, PETCAT, SALSEVNAME, SALSEVTIME, SALSEVPR, STATUS) VALUES ('BS' || lpad(SALSER_SEQ.NEXTVAL, 3, '0'), 'B001', 1, '造型修剪', 120, 500, 0);
INSERT INTO SALONSERVICE(SALSEVNO, SALNO, PETCAT, SALSEVNAME, SALSEVTIME, SALSEVPR, STATUS) VALUES ('BS' || lpad(SALSER_SEQ.NEXTVAL, 3, '0'), 'B002', 0, '貓咪基礎美容', 180, 2000, 0);
INSERT INTO SALONSERVICE(SALSEVNO, SALNO, PETCAT, SALSEVNAME, SALSEVTIME, SALSEVPR, STATUS) VALUES ('BS' || lpad(SALSER_SEQ.NEXTVAL, 3, '0'), 'B003', 1, '洗澡+護毛+吹乾', 120, 800, 0);
INSERT INTO SALONSERVICE(SALSEVNO, SALNO, PETCAT, SALSEVNAME, SALSEVTIME, SALSEVPR, STATUS) VALUES ('BS' || lpad(SALSER_SEQ.NEXTVAL, 3, '0'), 'B004', 3, '狗狗基礎美容', 150, 1000, 0);
INSERT INTO SALONSERVICE(SALSEVNO, SALNO, PETCAT, SALSEVNAME, SALSEVTIME, SALSEVPR, STATUS) VALUES ('BS' || lpad(SALSER_SEQ.NEXTVAL, 3, '0'), 'B005', 4, '洗澡+護毛+吹乾', 120, 1500, 0);
INSERT INTO SALONSERVICE(SALSEVNO, SALNO, PETCAT, SALSEVNAME, SALSEVTIME, SALSEVPR, STATUS) VALUES ('BS' || lpad(SALSER_SEQ.NEXTVAL, 3, '0'), 'B002', 2, '剪指甲', 60, 200, 0);

--美容店作品集照片
INSERT INTO salonalbum(salalbno,SALNO,SALPORTINFO)VALUES('BP' || LPAD(SALALBNO_SEQ.nextval,3,'0'),'B001','我們是B001美容店');
INSERT INTO salonalbum(salalbno,SALNO,SALPORTINFO)VALUES('BP' || LPAD(SALALBNO_SEQ.nextval,3,'0'),'B002','我們是B002美容店');
INSERT INTO salonalbum(salalbno,SALNO,SALPORTINFO)VALUES('BP' || LPAD(SALALBNO_SEQ.nextval,3,'0'),'B003','我們是B003美容店');
INSERT INTO salonalbum(salalbno,SALNO,SALPORTINFO)VALUES('BP' || LPAD(SALALBNO_SEQ.nextval,3,'0'),'B004','我們是B004美容店');
INSERT INTO salonalbum(salalbno,SALNO,SALPORTINFO)VALUES('BP' || LPAD(SALALBNO_SEQ.nextval,3,'0'),'B005','我們是B005美容店');

--收藏美容業者
INSERT INTO salonfollow VALUES('M001','B001');
INSERT INTO salonfollow VALUES('M001','B002');
INSERT INTO salonfollow VALUES('M002','B003');
INSERT INTO salonfollow VALUES('M003','B004');
INSERT INTO salonfollow VALUES('M004','B005');

--美容訂單
INSERT INTO SALONORDER(SALORDERNO,MEMNO,SALNO,PETNO,SALORDERDATE,SALTP,ORDERSTATUS)VALUES('BO' || LPAD(SALORDER_SEQ.NEXTVAL,3,'0'),'M001','B001','P001',TO_DATE('2020-09-20','YYYY-MM-DD'),1300,0);
INSERT INTO SALONORDER(SALORDERNO,MEMNO,SALNO,PETNO,SALORDERDATE,SALTP,ORDERSTATUS)VALUES('BO' || LPAD(SALORDER_SEQ.NEXTVAL,3,'0'),'M002','B002','P002',TO_DATE('2020-09-21','YYYY-MM-DD'),2500,1);
INSERT INTO SALONORDER(SALORDERNO,MEMNO,SALNO,PETNO,SALORDERDATE,SALTP,ORDERSTATUS)VALUES('BO' || LPAD(SALORDER_SEQ.NEXTVAL,3,'0'),'M003','B003','P003',TO_DATE('2020-09-19','YYYY-MM-DD'),1800,2);

--美容訂單明細
INSERT INTO SALONORDERDETAIL VALUES('BO001','BS001','G001',500);
INSERT INTO SALONORDERDETAIL VALUES('BO002','BS002','G002',700);

--美容訂單檢舉
INSERT INTO SALONREPORT(SALREPNO,SALORDERNO,MEMNO,EMPNO,SALREP)VALUES('BR' || LPAD(SALREPNO_SEQ.NEXTVAL,3,'0'),'BO001','M001','EMP001','服務態度不好,價格又貴');
COMMIT;