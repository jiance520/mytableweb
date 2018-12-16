package service.impl;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import service.IUserDataService;
import util.JdbcUtil;
import util.MyBatisUtil;
import entity.UserData;
import entity.UserDataMapper;
/**
 * @version ʱ�䣺2018��6��6�� ����10:34:00
 *
 */
public class UserDataService{
	private static Logger logger = Logger.getLogger(JdbcUtil.class.getName());
	private SqlSession session;
	private UserDataMapper dao;
	//��ʼ�������
	{	
		session = MyBatisUtil.getSession();//��Ҫ��session,����
		//�ÿ��ȡdao����ע��Ҫ��ǰ��xml���ӿڡ�
		dao = session.getMapper(UserDataMapper.class);
	}
	public UserData selectByPrimaryKey(String www,String username){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("www", www);
		map.put("username", username);
		return dao.selectByPrimaryKey(map);
	}
}
