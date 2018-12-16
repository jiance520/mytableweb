package util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

public class MyBatisUtil {
	private static Logger logger = Logger.getLogger(MyBatisUtil.class.getName());
	private static SqlSessionFactory factory = getFactory();
	private static final ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();
//	 * 根据核心配置文件获得SqlSessionFactory
	public static SqlSessionFactory getFactory() {
		try {
			logger.debug("开始生成factory");
			InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
			factory = (new SqlSessionFactoryBuilder()).build(inputStream);
			logger.debug("生成factory"+factory);
		} catch (IOException e) {		
			e.printStackTrace();
		}
		return factory;
	}
	 public static SqlSession getSession()  {
		 logger.debug("开始生成session");
		 SqlSession session = (SqlSession) threadLocal.get();

			if (session == null) {				
				session = (factory != null) ? factory.openSession(false):null;
				threadLocal.set(session);
			}
			logger.debug("生成session"+session);
	        return session;
	}
	 public static void closeSession() {
		  SqlSession session = (SqlSession) threadLocal.get();
	      threadLocal.set(null);

	      if (session != null) {
	           session.close();
	      }
	      logger.debug("关闭session");
	 }
}