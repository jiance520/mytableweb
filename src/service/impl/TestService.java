package service.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import util.JdbcUtil;
import util.MyBatisUtil;
import entity.Test;
import entity.TestMapper;

/**
 * @version ʱ�䣺2018��6��7�� ����7:55:21
 *
 */
public class TestService {
	private static Logger logger = Logger.getLogger(JdbcUtil.class.getName());
	private SqlSession session;
	private TestMapper tm;
	//��ʼ�������
	{	
		session = MyBatisUtil.getSession();//��Ҫ��session,����
		//�ÿ��ȡdao����ע��Ҫ��ǰ��xml���ӿڡ�
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
