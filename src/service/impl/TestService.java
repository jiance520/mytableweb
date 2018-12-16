package service.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import util.JdbcUtil;
import util.MyBatisUtil;
import entity.Test;
import entity.TestMapper;

/**
 * @version 时间：2018年6月7日 下午7:55:21
 *
 */
public class TestService {
	private static Logger logger = Logger.getLogger(JdbcUtil.class.getName());
	private SqlSession session;
	private TestMapper tm;
	//初始化代码块
	{	
		session = MyBatisUtil.getSession();//不要关session,最后关
		//用框架取dao对象，注意要提前绑定xml。接口。
		tm = session.getMapper(TestMapper.class);
	}
	public Test selectByPrimaryKey(Long l){
		return tm.selectByPrimaryKey(l);
	}
	public int insert_seq(Test t){
		int num = tm.insert_seq(t);
		session.commit();
		return num;
	}
}
