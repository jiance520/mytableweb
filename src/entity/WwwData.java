package entity;
import java.util.HashMap;

import entity.UserData;
public class WwwData {
	private String www;
	private HashMap<String,UserData> userData;
	public WwwData() {
		super();
	}
	public WwwData(String www) {
		super();
		setWww(www);
	}
	public String getWww() {
		return www;
	}
	public void setWww(String www) {
		this.www = www;
	}
	public HashMap<String, UserData> getUserData() {
		return userData;
	}
	public void setUserData(HashMap<String, UserData> userData) {
		this.userData = userData;
	}
	@Override
	public String toString() {
		String str = "ÍøÕ¾";
		String str1 = www;		
		return str1;
	}
}
