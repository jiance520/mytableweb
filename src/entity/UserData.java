package entity;

public class UserData {
    private String www;

    private String username;

    private String userid;

    private String name;

    private String pwd10;

    private String pwd6;

    private String secondpwd;

    private String tel;

    private String tel1;

    private String email;

    private String email1;

    private String registerdate;

    private String pwdsafe;

    private String note;

    private String strnum;

    private String cardimage;

    public String getWww() {
        return www;
    }

    public void setWww(String www) {
        this.www = www == null ? null : www.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPwd10() {
        return pwd10;
    }

    public void setPwd10(String pwd10) {
        this.pwd10 = pwd10 == null ? null : pwd10.trim();
    }

    public String getPwd6() {
        return pwd6;
    }

    public void setPwd6(String pwd6) {
        this.pwd6 = pwd6 == null ? null : pwd6.trim();
    }

    public String getSecondpwd() {
        return secondpwd;
    }

    public void setSecondpwd(String secondpwd) {
        this.secondpwd = secondpwd == null ? null : secondpwd.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1 == null ? null : tel1.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1 == null ? null : email1.trim();
    }

    public String getRegisterdate() {
        return registerdate;
    }

    public void setRegisterdate(String registerdate) {
        this.registerdate = registerdate == null ? null : registerdate.trim();
    }

    public String getPwdsafe() {
        return pwdsafe;
    }

    public void setPwdsafe(String pwdsafe) {
        this.pwdsafe = pwdsafe == null ? null : pwdsafe.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getStrnum() {
        return strnum;
    }

    public void setStrnum(String strnum) {
        this.strnum = strnum == null ? null : strnum.trim();
    }

    public String getCardimage() {
        return cardimage;
    }

    public void setCardimage(String cardimage) {
        this.cardimage = cardimage == null ? null : cardimage.trim();
    }

	@Override
	public String toString() {
		return "UserData [www=" + www + ", username=" + username + ", userid="
				+ userid + ", name=" + name + ", pwd10=" + pwd10 + ", pwd6="
				+ pwd6 + ", secondpwd=" + secondpwd + ", tel=" + tel
				+ ", tel1=" + tel1 + ", email=" + email + ", email1=" + email1
				+ ", registerdate=" + registerdate + ", pwdsafe=" + pwdsafe
				+ ", note=" + note + ", strnum=" + strnum + ", cardimage="
				+ cardimage + "]";
	}
}