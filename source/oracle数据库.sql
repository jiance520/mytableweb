-- 身份证>网站>用户>修改记录
-- 当用户输入身份证和密码时，可以检索链表中的唯一key身份证所对应的value，可以查看所有的帐号密码
-- 在身份证的value里检索链表中key网站所对应的value
-- 在网站的value里检索链表中id所对应的value,record
-- 外键就相当于指定当前表的父表，类似当前类的父类，来保证当前类/表的属性一一对应上一级，
-- A表中的一个字段，是B表的主键，那他就可以是A表的外键。
-- 不同的表信息是给不同的用户看的，而我只需要保存信息和检索信息，不需要分不同的表给人看。
-- 可以用java对象的思维建表，让一个类的对象属性保存着另一个对象(表)的详细信息。所有的信息又保存在链表中。
create table cardData(-- 一个系统多个用户
  cardId number(18) not null primary key,	-- 用来检索一个主人用户 相当于保存另一个对象，保存着用户详细信息
  password varchar2(18) not null -- 用户自己要记住的密码，
);
create table wwwData(-- 查看一个用户的所有网站
  www	varchar2(100)	not null primary key, -- 主键 不同的网站 这个网站相当于保存另一个对象，保存着网站详细信息
  cardId number(18) not null, foreign key(cardId) references cardData(cardId)--	身份证	外键 同一个身份证有哪些帐号
);
create table userData( -- 用于保存当前的最新信息
  userName varchar2(30)	not null, -- 用户名/ID 这个用户相当于保存另一个对象，保存着记录的详细信息
  www	varchar2(100)	not null,foreign key(www) references wwwData(www),-- 外键 同一个网站有哪些帐号
  userId varchar2(100), -- 帐号/ID
  name varchar2(100), -- 用户昵称
  pwd10	varchar2(30) not null, --	多个密码
  pwd6	varchar2(15) not null,	-- 多个密码
  secondPwd varchar2(20), -- 二次密码或手势锁屏密码
  tel varchar2(20), -- 注册手机
  tel1 varchar2(20), -- 备用手机
  email varchar2(100), -- 注册邮箱
  email1 varchar2(100), -- 备用邮箱
  registerDate varchar2(100), -- 注册日期
  pwdSafe varchar2(1000), -- 密保
  cardImage varchar2(1000),-- 卡片，图片路径
  note varchar2(1000), -- 备注/是否绑定微信、QQ、新浪微博、支付宝、银行卡
  strNum	varchar2(200),	--	不同的密码字符组成
  constraint wwwUser primary key("WWW","USERNAME")
);
create table modifyRecord( -- 用于保存密码修改记录
  recordId number(10) not null primary key, -- 主键
  userName varchar2(30)	not null,-- 用户名/ID
  www varchar2(100) not null,
  oldPwd10	varchar2(30)	not null, --	不同的密码
  oldPwd6	varchar2(15)	not null,	-- 不同的密码
  oldSecondPwd varchar2(20), -- 二次密码或手势锁屏密码
  oldModifyDate	varchar2(100),		--	多个修改时间
  oldPwdSafe varchar2(1000), -- 密保
  oldStrNum	varchar2(200)	--	不同的密码字符组成	
  
);
create sequence recordId
start with 1
increment by 1;

--update userdata set registerdate=null;
--alter table modifyrecord modify (oldmodifydate varchar2(100));
--alter table userdata modify pwd10 not null;
--update userdata set registerdate=to_char(sysdate,'yyyy-mm-dd hh24:mi:ss');
--select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') from dual;
--update userData set pwd10='DqMnfdJe5wX',pwd6='041600',registerdate=to_char(sysdate,'yyyy-mm-dd hh24:mi:ss');
--select * from userData where userName='jiance520' and www='www.hao123.com';
--insert into modifyrecord(recordId,www,userName,oldpwd10,oldpwd6,oldmodifydate) values(recordId.nextval,'www.hao123.com', 'jiance520', 'DqMnfdJe5wX', '041600',to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'));