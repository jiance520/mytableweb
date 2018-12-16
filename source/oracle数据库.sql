-- ���֤>��վ>�û�>�޸ļ�¼
-- ���û��������֤������ʱ�����Լ��������е�Ψһkey���֤����Ӧ��value�����Բ鿴���е��ʺ�����
-- �����֤��value�����������key��վ����Ӧ��value
-- ����վ��value�����������id����Ӧ��value,record
-- ������൱��ָ����ǰ��ĸ������Ƶ�ǰ��ĸ��࣬����֤��ǰ��/�������һһ��Ӧ��һ����
-- A���е�һ���ֶΣ���B��������������Ϳ�����A��������
-- ��ͬ�ı���Ϣ�Ǹ���ͬ���û����ģ�����ֻ��Ҫ������Ϣ�ͼ�����Ϣ������Ҫ�ֲ�ͬ�ı���˿���
-- ������java�����˼ά������һ����Ķ������Ա�������һ������(��)����ϸ��Ϣ�����е���Ϣ�ֱ����������С�
create table cardData(-- һ��ϵͳ����û�
  cardId number(18) not null primary key,	-- ��������һ�������û� �൱�ڱ�����һ�����󣬱������û���ϸ��Ϣ
  password varchar2(18) not null -- �û��Լ�Ҫ��ס�����룬
);
create table wwwData(-- �鿴һ���û���������վ
  www	varchar2(100)	not null primary key, -- ���� ��ͬ����վ �����վ�൱�ڱ�����һ�����󣬱�������վ��ϸ��Ϣ
  cardId number(18) not null, foreign key(cardId) references cardData(cardId)--	���֤	��� ͬһ�����֤����Щ�ʺ�
);
create table userData( -- ���ڱ��浱ǰ��������Ϣ
  userName varchar2(30)	not null, -- �û���/ID ����û��൱�ڱ�����һ�����󣬱����ż�¼����ϸ��Ϣ
  www	varchar2(100)	not null,foreign key(www) references wwwData(www),-- ��� ͬһ����վ����Щ�ʺ�
  userId varchar2(100), -- �ʺ�/ID
  name varchar2(100), -- �û��ǳ�
  pwd10	varchar2(30) not null, --	�������
  pwd6	varchar2(15) not null,	-- �������
  secondPwd varchar2(20), -- ���������������������
  tel varchar2(20), -- ע���ֻ�
  tel1 varchar2(20), -- �����ֻ�
  email varchar2(100), -- ע������
  email1 varchar2(100), -- ��������
  registerDate varchar2(100), -- ע������
  pwdSafe varchar2(1000), -- �ܱ�
  cardImage varchar2(1000),-- ��Ƭ��ͼƬ·��
  note varchar2(1000), -- ��ע/�Ƿ��΢�š�QQ������΢����֧���������п�
  strNum	varchar2(200),	--	��ͬ�������ַ����
  constraint wwwUser primary key("WWW","USERNAME")
);
create table modifyRecord( -- ���ڱ��������޸ļ�¼
  recordId number(10) not null primary key, -- ����
  userName varchar2(30)	not null,-- �û���/ID
  www varchar2(100) not null,
  oldPwd10	varchar2(30)	not null, --	��ͬ������
  oldPwd6	varchar2(15)	not null,	-- ��ͬ������
  oldSecondPwd varchar2(20), -- ���������������������
  oldModifyDate	varchar2(100),		--	����޸�ʱ��
  oldPwdSafe varchar2(1000), -- �ܱ�
  oldStrNum	varchar2(200)	--	��ͬ�������ַ����	
  
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