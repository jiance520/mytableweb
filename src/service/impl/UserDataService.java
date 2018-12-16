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
 * @version 时间：2018年6月6日 下午10:34:00
 *
 */
public class UserDataService{
	private static Logger logger = Logger.getLogger(JdbcUtil.class.getName());
	private SqlSession session;
	private UserDataMapper dao;
	//初始化代码块
	{	
		session = MyBatisUtil.getSession();//不要关session,最后关
		//用框架取dao对象，注意要提前绑定xml。接口。
		dao = session.getMapper(UserDataMapper.class);
	}
	public UserData selectByPrimaryKey(String www,String username){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("www", www);
		map.put("username", username);
		return dao.selectByPrimaryKey(map);
	}
}
