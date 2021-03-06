package entity;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @version 时间：2018-5-2 下午6:37:39
 *
 */
public class CardData implements Serializable{
	private String cardId;
	private String password;
	private HashMap<String,WwwData> wwwData;
	public CardData() {
		super();
	}
	public CardData(String cardId, String password) {
		super();
		setCardId(cardId);
		setPassword(password);
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	
	public HashMap<String, WwwData> getWwwData() {
		return wwwData;
	}
	public void setWwwData(HashMap<String, WwwData> wwwData) {
		this.wwwData = wwwData;
	}
	@Override
	public String toString() {
		String str = "身份证\t密码";
		String str1 = cardId+"\t"+password;		
		return str1;
	}
	
}
