package util;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
/**
 * @version 时间：2018-5-2 下午2:20:24
 *针对的是网页，不是单个表的数据操作
 */
public class PwdBase extends JdbcUtil{
	private static Logger logger = Logger.getLogger(PwdBase.class.getName());
	public PwdBase() {
		super();
	}
	//	用于注册时，失去焦点时，从数据库检索，如果表中没有当前用户，用户可以注册
	public boolean queryCard(String cardId){
		ResultSet rs = JdbcUtil.executeQueryRS("select * from carddata where cardId=?", cardId);
		boolean flag = false;
		try {
			flag=rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
//	(查重复和插入两步)在注册成功后，页面跳转时，向表中插入数据，如果已注册，返回false,同时保存数据到map还是？
	public boolean register(String cardId,String password){
		ResultSet rs = JdbcUtil.executeQueryRS("select * from carddata where cardId=?", cardId);
		boolean flag = false;
		int num = 0;
		try {
			flag = rs.next();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if(flag==false){
			num = JdbcUtil.executeUpdate("insert into carddata values(?,?)",cardId,password);
			logger.debug("执行SQL插入语句-注册成功");
			return flag;
		}
		else{
			logger.debug("没有执行SQL插入语句-注册失败"+flag);
			return flag;
		}
	}
	public boolean load(String cardId,String password){
		ResultSet rs = JdbcUtil.executeQueryRS("select * from carddata where cardId=? and password=?", cardId,password);
		boolean flag = false;
		try {
			flag = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			e.printStackTrace();
		}//如果查询到对应的帐号密码，返回true，登陆成功
		logger.debug("登陆验证"+flag);
		return flag;
	}
	public void save(String cardId,String www,String userName,String pwd10,String pwd6,String note){
		int num = 0;
		ResultSet rs = JdbcUtil.executeQueryRS("select * from wwwData where www=?", www);
		try {
			if(rs.next()==false){
				JdbcUtil.executeUpdate("insert into wwwData(www,cardId) values(?,?)", www,cardId);
				logger.debug("执行了插入"+cardId+www);
			}
			else{
				logger.debug("已存在不执行插入"+cardId+www);
			}
			rs = JdbcUtil.executeQueryRS(" select * from userData where userName=? and www=? ",userName,www);
			if(rs.next()==false){
				JdbcUtil.executeUpdate("insert into userData(userName,www,pwd10,pwd6,note,registerdate) values(?,?,?,?,?,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'))",userName,www,pwd10,pwd6,note);
				logger.debug("执行了插入"+userName+www+pwd10+pwd6);
			}
			else{
				num = JdbcUtil.executeUpdate(" update userData set pwd10=?,pwd6=?,note=?,registerdate=to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') ",pwd10,pwd6,note);
				logger.debug("已存在不执行插入，只更新密码，日期备注,"+pwd10+pwd6);
			}
			num = JdbcUtil.executeUpdate("insert into modifyrecord(recordId,userName,www,oldpwd10,oldpwd6,oldmodifydate) values(recordId.nextval-1,?,?,?,?,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'))",userName,www,pwd10,pwd6);
			logger.debug("执行了插入修改记录"+userName+www+pwd10+pwd6);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public List<HashMap> queryUser(String www){
		List<HashMap> list = JdbcUtil.exectueQuery("select * from userdata where www=?", www);
		logger.debug("执行SQL语句查询用户成功");
		return list;
	}
	public List<HashMap> queryRecord(String userName,String www){
		List<HashMap> list = JdbcUtil.exectueQuery("select * from modifyrecord where userName=? and www=?",userName,www);
		logger.debug("执行SQL语句查询记录成功");
		return list;
	}
	public HashMap<String,Object> queryNote(String userName,String www){
		HashMap<String,Object> map = JdbcUtil.queryOne("select * from userData where userName=? and www=?", userName,www);
		logger.debug("执行SQL语句查询备注成功");
		return map;
	}
//		public static void main(String[] args) {
//			PasswordInfo pi = new PasswordInfo();
//			pi.queryUser("www.hao123.com");
//			pi.queryRecord("jiance520","www.hao123.com");
//			pi.save("430523198311070056", "www.hao123.com", "jiance520", "DqMnfdJe5wX", "041600");
//	}
}
