--<ScriptOptions statementTerminator=";"/>

CREATE TABLE SPRING_BOARD(
B_NUM    NUMBER NOT NULL , 
B_NAME VARCHAR2(10) NOT NULL , 
B_TITLE    VARCHAR2(1000) NOT NULL , 
B_CONTENT CLOB,  B_PWD VARCHAR2(18) NOT NULL , 
B_DATE DATE DEFAULT SYSDATE ) ; 

ALTER TABLE SPRING_BOARD    
ADD CONSTRAINT SPRING_BOARD_PK PRIMARY KEY(B_NUM);

COMMENT ON TABLE     SPRING_BOARD IS '게시판 정보'; 
COMMENT ON COLUMN SPRING_BOARD.B_NUM IS '게시판순번';
COMMENT ON COLUMN SPRING_BOARD.B_NAME IS '게시판작성자'; 
COMMENT ON COLUMN SPRING_BOARD.B_TITLE IS '게시판제목';
COMMENT ON COLUMN SPRING_BOARD.B_CONTENT IS '게시판내용'; 
COMMENT ON COLUMN SPRING_BOARD.B_PWD IS '게시판비밀번호'; 
COMMENT ON COLUMN SPRING_BOARD.B_DATE IS '게시판등록일';

CREATE SEQUENCE SPRING_BOARD_SEQ
START WITH 1
INCREMENT BY 1
NOCYCLE;

-- 파일 업로드시 서버에 저장한 파일명
ALTER TABLE SPRING_BOARD
ADD(B_FILE VARCHAR2(500));

COMMENT ON COLUMN SPRING_BOARD.B_FILE IS '게시판첨부파일';

-- 댓글 테이블 생성
CREATE TABLE SPRING_REPLY(
	R_NUM NUMBER NOT NULL,
	B_NUM NUMBER NOT NULL,
	R_NAME VARCHAR2(10) NULL,
	R_CONTENT VARCHAR2(2000) NULL,
	R_PWD VARCHAR2(18) NULL,
	R_DATE DATE DEFAULT SYSDATE,
	CONSTRAINT SPRING_REPLY_PK PRIMARY KEY(R_NUM),
	CONSTRAINT SPRING_REPLY_FK FOREIGN KEY(B_NUM)
	REFERENCES SPRING_BOARD(B_NUM)
);

COMMENT ON TABLE     SPRING_REPLY IS '댓글 정보'; 
COMMENT ON COLUMN SPRING_REPLY.R_NUM IS '댓글번호'; 
COMMENT ON COLUMN SPRING_REPLY.B_NUM IS '게시판 글번호'; 
COMMENT ON COLUMN SPRING_REPLY.R_NAME IS '댓글 작성자'; 
COMMENT ON COLUMN SPRING_REPLY.R_CONTENT IS '댓글 내용';
COMMENT ON COLUMN SPRING_REPLY.R_PWD IS '댓글 비밀번호'; 
COMMENT ON COLUMN SPRING_REPLY.R_DATE IS '댓글 등록일';   

CREATE SEQUENCE SPRING_REPLY_SEQ
START WITH 1
INCREMENT BY 1
NOCYCLE;

-- 갤러리 테이블 생성 by.moon (feat.OhGee)
CREATE TABLE spring_gallery(
   g_num number not null,
   g_name varchar2(10) not null,
   g_subject varchar2(50 char) not null,
   g_content varchar2(100 char) not null,
   g_thumb varchar2(100) not null,
   g_file varchar2(100) not null,
   g_pwd varchar2(18) not null,
   g_date date default SYSDATE,
   CONSTRAINT spring_gallerty_pk PRIMARY KEY(g_num)
);

-- 갤러리 코멘트 달기 by.moon
COMMENT ON TABLE spring_gallery is '갤러리 게시판 정보';
COMMENT ON COLUMN spring_gallery.g_num is '갤러리게시판 순번';
COMMENT ON COLUMN spring_gallery.g_name is '갤러리게시판 작성자';
COMMENT ON COLUMN spring_gallery.g_subject is '갤러리게시판 제목';
COMMENT ON COLUMN spring_gallery.g_content is '갤러리게시판 내용';
COMMENT ON COLUMN spring_gallery.g_thumb is '갤러리게시판 썸네일 이미지';
COMMENT ON COLUMN spring_gallery.g_file is '갤러리게시판 이미지';
COMMENT ON COLUMN spring_gallery.g_pwd is '갤러리게시판 비밀번호';
COMMENT ON COLUMN spring_gallery.g_date is '갤러리게시판 등록일';

-- 갤러리 시퀀스 생성 by.moon
CREATE SEQUENCE spring_gallery_seq;

-- 회원가입 테이블
CREATE TABLE SPRING_MEMBER(
	IDX NUMBER,
	USERID VARCHAR2(70) NOT NULL,
	USERPW VARCHAR2(100) NOT NULL,
	USERNAME VARCHAR2(50) NOT NULL,
	PINNO VARCHAR2(15) NOT NULL,
	EMAIL VARCHAR2(70) NOT NULL,
	PHONE VARCHAR2(13) NOT NULL,
	JOINDATE DATE DEFAULT SYSDATE,
	CONSTRAINT SPRING_MEMBER_PK PRIMARY KEY(IDX),
	CONSTRAINT SPRING_MEMBER_UK UNIQUE(USERID)
);

comment on table    spring_member is '회원 테이블 정보'; 
comment on column spring_member.idx is '회원테이블 순번';
comment on column spring_member.userid is '회원테이블 아이디';
comment on column spring_member.userpw is '회원테이블 비밀번호';
comment on column spring_member.username is '회원테이블 이름';
comment on column spring_member.pinno is '회원테이블 생년월일';
comment on column spring_member.email is '회원테이블 이메일'; 
comment on column spring_member.phone is '회원테이블 핸드폰번호'; 
comment on column spring_member.joindate is '회원테이블 등록일';  

-- 회원 가입시 사용할 회원번호(시퀀스)
CREATE SEQUENCE SPRING_MEMBER_SEQ;

-- 해시함수 솔트값을 저장하기 위한 테이블(비밀번호 암호화)
CREATE TABLE SECURITY(
	USERID VARCHAR2(70),
	SALT VARCHAR2(70),
	CONSTRAINT SECURITY_PK PRIMARY KEY(USERID)
);

-- 로그인 정보 저장 테이블
CREATE TABLE LOGIN_HISTORY(
	idx NUMBER, userid VARCHAR2(70),
	retry NUMBER DEFAULT 0,
	lastFailedLogin NUMBER DEFAULT 0,
	lastSuccessedLogin NUMBER DEFAULT 0,
	clientIp VARCHAR2(15),
	CONSTRAINT login_history_pk PRIMARY KEY(idx) 
);

COMMENT ON TABLE login_history is '로그인 정보 저장 테이블'; 
COMMENT ON COLUMN login_history.idx is '순번';
COMMENT ON COLUMN login_history.userid is '로그인 아이디';
COMMENT ON COLUMN login_history.retry is '로그인 시도 횟수'; 
COMMENT ON COLUMN login_history.lastfailedlogin is '마지막으로 실패한 로그인 시간'; 
COMMENT ON COLUMN login_history.lastsuccessedlogin is '마지막으로 성공한 로그인 시간'; 
COMMENT ON COLUMN login_history.clientip is '로그인한 사용자의 ip 주소'; 

-- 로그인 정보 저장시 사용할 순번(시퀀스)
CREATE SEQUENCE LOGIN_HISTORY_SEQ;
