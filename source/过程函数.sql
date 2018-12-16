create or replace
package com
is
  type reftype is ref cursor;
  function queryPwd(www varchar2,username varchar2) return varchar2;
  procedure queryUser(www in varchar2,rs out reftype);
end com;
-------------------
create or replace package body com
is
  function queryPwd(www varchar2,username varchar2) return varchar2
  is
    pwd userdata.pwd10%type; 
  begin
    select pwd10 into pwd from userdata where www=www and username=username;
    return pwd;
  end;
  procedure queryUser(www in varchar2,rs out reftype)
  is
  begin
    open rs for select * from userdata where www=www;-- Ҫ������α꣬����ѭ����Ҳ���ùرգ�
  end;
end;
------------------
declare
  row userdata%rowtype;
  myref com.reftype;-- ʹ�ð��ﶨ����α��������α꣬�������չ��̵�out������
begin 
  dbms_output.put_line(com.queryPwd('www.hao123.com','jiance520'));
  -- ����ֱ�ӵ��ú���com.queryPwd('www.hao123.com','jiance520')����Ϊû�н��շ���ֵ��
  com.queryUser('www.hao123.com',myref);-- ��plSQL�����ֱ�ӵ��ù��̡�
  loop
    fetch myref into row;
    dbms_output.put_line(row.www);-- row.�����Զ�����
    exit when myref%notfound;
  end loop;
  close myref;
end;
