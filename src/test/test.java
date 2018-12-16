package test;

import service.impl.UserDataService;
import entity.UserData;

/**
 * @version 时间：2018年6月6日 下午11:22:30
 *
 */
public class test {
	public static void main(String[] args) {
		UserDataService is = new UserDataService();
		UserData userData = is.selectByPrimaryKey("www.hao123.com", "jiance520");
		System.out.println(userData);

//		TestService ts = new TestService();
//		Test t = new Test();
//		t.setName("小明");
//		int num = ts.insert_seq(t);
//		System.out.println(t);
	} 
}
