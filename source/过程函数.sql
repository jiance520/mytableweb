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
    open rs for select * from userdata where www=www;-- 要输出的游标，不用循环，也不用关闭！
  end;
end;
------------------
declare
  row userdata%rowtype;
  myref com.reftype;-- 使用包里定义的游标来定义游标，用来接收过程的out参数。
begin 
  dbms_output.put_line(com.queryPwd('www.hao123.com','jiance520'));
  -- 不能直接调用函数com.queryPwd('www.hao123.com','jiance520')，因为没有接收返回值。
  com.queryUser('www.hao123.com',myref);-- 在plSQL里可以直接调用过程。
  loop
    fetch myref into row;
    dbms_output.put_line(row.www);-- row.不会自动弹出
    exit when myref%notfound;
  end loop;
  close myref;
end;
