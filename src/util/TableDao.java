package util;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import oracle.jdbc.OracleTypes;
/**
 * @version 时间：2018年5月13日 下午10:03:35
 *对表的增删改查。
 */
public class TableDao extends JdbcUtil implements ITableDao{
	private static String sql = null;
	private String tableName = null;
	public TableDao() {
		super();
	}
	public TableDao(String driver, String url, String user, String password) {
		super(driver, url, user, password);
	}
	public TableDao(String tableName) {
		super();
		this.tableName = tableName;
	}
	@Override
	public List<HashMap> getAll() {
		sql = " select * from "+tableName;
		List<HashMap> list = TableDao.exectueQuery(sql);//由于通过元数据放入了List中的map，所以可以不需要实体类。
//		System.out.println("用户名\t\t网站\t密码10\t密码6\t注册日期");
//		for (HashMap map:list) {
//			System.out.print(map.get("USERNAME")+"\t");
//			System.out.print(map.get("WWW")+"\t");
//			System.out.print(map.get("PWD10")+"\t");
//			System.out.print(map.get("PWD6")+"\t");
//			System.out.print(map.get("REGISTERDATE")+"\t");
//			System.out.println();			
//		}		
//		------------------------
		return list;
	}
	@Override
	public HashMap<String,Object> getOne(String sql,Object... params) {
		HashMap<String,Object> map = TableDao.queryOne(sql, params);
//		System.out.println("用户名\t\t网站\t密码10\t密码6\t注册日期");
//		System.out.print(map.get("USERNAME")+"\t");
//		System.out.print(map.get("WWW")+"\t");
//		System.out.print(map.get("PWD10")+"\t");
//		System.out.print(map.get("PWD6")+"\t");
//		System.out.print(map.get("REGISTERDATE")+"\t");
//		System.out.println();
//		-----------------------
		return map;
	}
	@Override
	public int insert(String sql,Object... params) {
		int num = TableDao.executeUpdate(sql,params);		
		return num;
	}
	@Override
	public int update(String sql,Object... params) {
		int num = TableDao.executeUpdate(sql,params);		
		return num;
	}
	@Override
	public int delete(String sql,Object... params) {
		int num = TableDao.executeUpdate(sql,params);		
		return num;
	}
	public static void main(String[] args) {
		TableDao td = new TableDao("userData");
//		List<HashMap> list = td.getAll();//List中存放的是map行数据，map中存放的是字段名和值
//		HashMap<String,Object> map = td.getOne("select * from userdata where username=? ", "jiance520");
//		int num = td.insert("insert into userdata(username,www,pwd10,pwd6,registerdate) values(?,?,?,?,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'))", "jiance521","www.hao123.com","asd123","123456");
//		System.out.println(num);
//		-----------------------
//		int num = td.update("update userdata set pwd10=?,registerdate=to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') where www='www.hao123.com' and username='jiance521' ","11111");
//		System.out.println(num);
//		-----------------------
//		int num = td.update("delete from userdata where www='www.hao123.com' and username='jiance521'");
//		System.out.println(num);
//		------------调用过程-------------
//		HashMap<String,Object> mapp = td.queryProcedure("{call com.queryUser(?,?)}", 2, OracleTypes.CURSOR, "www.hao123.com");//ref游标的类型是cousor
//		System.out.print(mapp.get("WWW")+"\t");
		Object obj = td.queryFunction("{?=call com.queryPwd(?,?)}", 1, OracleTypes.VARCHAR, "www.hao123.com","jiance520");//ref游标的类型是cousor
		System.out.print(obj.toString());
	}
}
