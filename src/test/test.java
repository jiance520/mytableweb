package test;

import service.impl.UserDataService;
import entity.UserData;

/**
 * @version ʱ�䣺2018��6��6�� ����11:22:30
 *
 */
public class test {
	public static void main(String[] args) {
		UserDataService is = new UserDataService();
		UserData userData = is.selectByPrimaryKey("www.hao123.com", "jiance520");
		System.out.println(userData);

//		TestService ts = new TestService();
//		Test t = new Test();
//		t.setName("С��");
//		int num = ts.insert_seq(t);
//		System.out.println(t);
	} 
}
