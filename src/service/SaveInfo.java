package service;

import java.sql.ResultSet;

import org.apache.log4j.Logger;

import util.JdbcUtil;

/**
 * @version 时间：2018年5月25日 上午12:16:05
 *
 */
public class SaveInfo{
	private static Logger logger = Logger.getLogger(JdbcUtil.class.getName());
	private String cardId = null;
	private String www = null;
	private String userName = null;
	private String pwd10 = null;
	private String pwd6 = null;
	private String note = null;
	private String cardImage = null;
	public SaveInfo(String cardId,String www, String userName, String pwd10, String pwd6,
			String note,String cardImage) {
		super();
		this.www = www;
		this.userName = userName;
		this.pwd10 = pwd10;
		this.pwd6 = pwd6;
		this.note = note;
		this.cardId = cardId;
		this.cardImage = cardImage;
	}
	public String getWww() {
		return www;
	}
	public void setWww(String www) {
		this.www = www;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPwd10() {
		return pwd10;
	}
	public void setPwd10(String pwd10) {
		this.pwd10 = pwd10;
	}
	public String getPwd6() {
		return pwd6;
	}
	public void setPwd6(String pwd6) {
		this.pwd6 = pwd6;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	
	public String getCardImage() {
		return cardImage;
	}
	public void setCardImage(String cardImage) {
		this.cardImage = cardImage;
	}
	
	@Override
	public String toString() {
		return "SaveInfo [cardId=" + cardId + ", www=" + www + ", userName="
				+ userName + ", pwd10=" + pwd10 + ", pwd6=" + pwd6 + ", note="
				+ note + ", cardImage=" + cardImage + "]";
	}
	public void saveInfo(){
		int num = 0;
		ResultSet rs = JdbcUtil.executeQueryRS("select * from wwwData where www=?", www);
		try {
			if(rs.next()==false){
				JdbcUtil.executeUpdate("insert into wwwData(www,cardId) values(?,?)", www,cardId);
				logger.debug("插入了"+cardId+www);
			}
			else{
				logger.debug("已存在不执行插入"+cardId+www);
			}
			rs = JdbcUtil.executeQueryRS(" select * from userData where userName=? and www=? ",userName,www);
			if(rs.next()==false){
				JdbcUtil.executeUpdate("insert into userData(userName,www,pwd10,pwd6,note,cardImage,registerdate) values(?,?,?,?,?,?,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'))",userName,www,pwd10,pwd6,note,cardImage);
				logger.debug("执行了插入"+userName+www+pwd10+pwd6);
			}
			else{
				num = JdbcUtil.executeUpdate(" update userData set pwd10=?,pwd6=?,registerdate=to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') ",pwd10,pwd6);
				logger.debug("已存在不执行插入，只更新密码，日期,"+pwd10+pwd6);
			}
			num = JdbcUtil.executeUpdate("insert into modifyrecord(recordId,userName,www,oldpwd10,oldpwd6,oldmodifydate) values(recordId.nextval-1,?,?,?,?,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'))",userName,www,pwd10,pwd6);
			logger.debug("执行了插入修改记录"+userName+www+pwd10+pwd6);
			logger.debug("修改保存成功");
		} catch (Exception e) {
			logger.debug("修改保存出错");
			e.printStackTrace();
		}
	}
}
