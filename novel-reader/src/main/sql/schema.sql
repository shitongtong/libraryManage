/*连接数据库控制台*/
/*mysql -uroot -p*/

/*数据库初始化脚本*/

/*创建数据库*/
CREATE DATABASE novel_reader DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
/*使用数据库*/
USE novel_reader;
/* Drop Tables */
DROP TABLE IF EXISTS t_system_block;
DROP TABLE IF EXISTS t_bookcase;
DROP TABLE IF EXISTS t_chapter;
DROP TABLE IF EXISTS t_message;
DROP TABLE IF EXISTS t_review;
DROP TABLE IF EXISTS t_credit_history;
DROP TABLE IF EXISTS t_user;
DROP TABLE IF EXISTS t_article;

/* Create Tables */

CREATE TABLE t_system_block(
#   blockno BIGINT NOT NULL AUTO_INCREMENT,
  blockno serial NOT NULL,
  blockid VARCHAR(32) ,
  blockname VARCHAR(32) ,
  type smallint,
  category int,
  sortcol varchar(32),
  isasc varchar(11),
  limitnum int,
  content text,
  target smallint,
  deleteflag varchar(11) DEFAULT 'false',
  modifyuserno integer,
  modifytime timestamp,
PRIMARY KEY (blockno)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE t_bookcase
(
  bookcaseno serial NOT NULL,
  articleno int,
  articlename varchar(100),
  category int,
  userno int,
  username varchar(50),
  chapterid int,
  chaptername varchar(100),
  lastvisit timestamp,
  createtime timestamp,
  deleteflag varchar(11) DEFAULT 'false',
  modifyuserno integer,
  modifytime timestamp,
  PRIMARY KEY (bookcaseno)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;


CREATE TABLE t_message
(
  messageno serial NOT NULL,
  userno int,
  loginid varchar(32),
  touserno int,
  tologinid varchar(32),
  title varchar(32),
  content varchar(255),
  category smallint,
  isread varchar(11),
  postdate timestamp ,
  deleteflag varchar(11) DEFAULT 'false',
  modifyuserno integer,
  modifytime timestamp ,
  PRIMARY KEY (messageno)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;


CREATE TABLE t_review
(
  reviweno serial NOT NULL,
  userno int,
  username varchar(50),
  articleno int,
  articlename varchar(100),
  title varchar(30),
  review varchar(500),
  email varchar(60),
  postdate timestamp,
  deleteflag varchar(11) DEFAULT 'false',
  modifyuserno integer,
  modifytime timestamp,
  PRIMARY KEY (reviweno)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;


CREATE TABLE t_credit_history
(
  credithistoryno serial NOT NULL,
  userno int,
  loginid varchar(32),
  articleno int,
  articlename varchar(100),
  chapterno int,
  chaptername varchar(100),
  timestamp timestamp,
  creditpoint int,
  deleteflag varchar(11) DEFAULT 'false',
  modifyuserno integer,
  modifytime timestamp ,
  PRIMARY KEY (credithistoryno)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;


CREATE TABLE t_user
(
  userno serial NOT NULL,
  loginid varchar(32) NOT NULL,
  password varchar(32),
  username varchar(50),
  email varchar(60),
  regdate timestamp,
  sex smallint,
  qq varchar(15),
  lastlogin timestamp,
  lineno varchar(32),
  type smallint,
  votecount int,
  realname varchar(10),
  id varchar(18),
  mobileno varchar(11),
  branch varchar(50),
  bankno varchar(20),
  alipayacount varchar(50),
  category int,
  subcategory int,
  deleteflag varchar(11) DEFAULT 'false',
  modifyuserno integer,
  modifytime timestamp ,
  PRIMARY KEY (userno)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;


CREATE TABLE t_chapter
(
  chapterno serial NOT NULL,
  articleno int,
  articlename varchar(100),
  chaptertype smallint,
  chaptername varchar(100),
  size int DEFAULT 0,
  isvip boolean,
  postdate timestamp,
  publishtime timestamp,
  ispublish varchar(11) DEFAULT 'false',
  lastchecktime timestamp,
  deleteflag varchar(11) DEFAULT 'false',
  modifyuserno integer,
  modifytime timestamp ,
  PRIMARY KEY (chapterno)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;


CREATE TABLE t_article
(
  articleno serial NOT NULL,
  articlename varchar(100),
  initial char(1),
  keywords varchar(500),
  authorid int,
  author varchar(50),
  category int DEFAULT 0,
  subcategory int,
  intro text,
  lastchapterno int,
  lastchapter varchar(50),
  chapters int,
  size int DEFAULT 0,
  fullflag varchar(11),
  imgflag smallint,
  agent varchar(50),
  firstflag varchar(11) DEFAULT 'false',
  permission int,
  authorflag varchar(11) DEFAULT 'false',
  postdate timestamp,
  lastupdate timestamp,
  dayvisit int DEFAULT 0,
  weekvisit int DEFAULT 0,
  monthvisit int DEFAULT 0,
  allvisit int DEFAULT 0,
  dayvote int DEFAULT 0,
  weekvote int DEFAULT 0,
  monthvote int DEFAULT 0,
  allvote int DEFAULT 0,
  deleteflag varchar(11) DEFAULT 'false',
  publicflag int,
  modifyuserno integer,
  modifytime timestamp ,
  PRIMARY KEY (articleno)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

/*建立索引*/
CREATE INDEX t_article_articlename_index  ON t_article (articlename);
CREATE INDEX t_chapter_articleno_index  ON t_chapter (articleno);
CREATE INDEX t_chapter_chaptername_index  ON t_chapter (chaptername);
CREATE INDEX index_credithistory_userno ON t_credit_history (userno);

/*初始化数据*/
INSERT INTO t_system_block( blockid,blockname,TYPE,sortcol,isasc,limitnum,target) VALUES ('last_update_list','最新更新列表',10,'lastupdate','false',15,6);
INSERT INTO t_system_block( blockid,blockname,TYPE,sortcol,isasc,limitnum,target) VALUES ('last_insert_list','最新入库',10,'postdate','false',15,6);
INSERT INTO t_system_block( blockid,blockname,TYPE,content,target) VALUES ('friend_link','友情链接',30,'<a href="http://www.51yd.org" target="_blank">易读小说系统</a>',6);
INSERT INTO t_system_block( blockid,blockname,TYPE,content,target) VALUES ('index_yanqing_tuijian','首页言情推荐',20,'1,2,3,4',6);
INSERT INTO t_system_block( blockid,blockname,TYPE,content,target) VALUES ('index_xuanhuan_tuijian','首页玄幻推荐',20,'1,2,3,4',6);
INSERT INTO t_system_block( blockid,blockname,TYPE,content,target) VALUES ('index_junshi_tuijian','首页军事推荐',20,'1,2,3,4',6);
INSERT INTO t_system_block( blockid,blockname,TYPE,content,target) VALUES ('index_kongbu_tuijian','首页恐怖灵异推荐',20,'1,2,3,4',6);
INSERT INTO t_system_block( blockid,blockname,TYPE,content,target) VALUES ('index_wuxia_tuijian','首页武侠修真推荐',20,'1,2,3,4',6);
INSERT INTO t_system_block(blockid, blockname, type, sortcol, isasc, limitnum, target, deleteflag) VALUES ('last_update_list_mobile','手机页面更新列表',10,'lastupdate','false',6,6,'false');
INSERT INTO t_system_block(blockid, blockname, type, isasc, content,  target, deleteflag) VALUES ('index_hot_list_mobile','手机页热点',20,'false','58755,58754,58753',6,'false');
INSERT INTO t_user(loginid, password,type,deleteflag) VALUES ('{0}', '{1}', 30 ,'false');


