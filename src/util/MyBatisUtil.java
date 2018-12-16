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
//	 * ���ݺ��������ļ����SqlSessionFactory
	public static SqlSessionFactory getFactory() {
		try {
			logger.debug("��ʼ����factory");
			InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
			factory = (new SqlSessionFactoryBuilder()).build(inputStream);
			logger.debug("����factory"+factory);
		} catch (IOException e) {		
			e.printStackTrace();
		}
		return factory;
	}
	 public static SqlSession getSession()  {
		 logger.debug("��ʼ����session");
		 SqlSession session = (SqlSession) threadLocal.get();

			if (session == null) {				
				session = (factory != null) ? factory.openSession(false):null;
				threadLocal.set(session);
			}
			logger.debug("����session"+session);
	        return session;
	}
	 public static void closeSession() {
		  SqlSession session = (SqlSession) threadLocal.get();
	      threadLocal.set(null);

	      if (session != null) {
	           session.close();
	      }
	      logger.debug("�ر�session");
	 }
}