package recipeMall.user;

import java.util.Date;

public class UserVO {
	private String id;
	private String pw;
	private String name;
	private String nikName;
	private String phone;
	private String rcmd;
	private String userAddr;
	private String userAddr2;
	private int pointNo;
	private Date joinDate;
	
	public UserVO() {
		
	}
	
	
	public UserVO(String id, String pw, String name, String nikName, String phone, String userAddr, String userAddr2) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.nikName = nikName;
		this.phone = phone;
		this.userAddr = userAddr;
		this.userAddr2 = userAddr2;
	}

	public UserVO(String id, String pw, String name, String nikName, String phone, String rcmd, String userAddr, String userAddr2) {
		this(id,pw,name,nikName,phone,userAddr,userAddr2);
		this.rcmd = rcmd;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPw() {
		return pw;
	}
	
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getNikName() {
		return nikName;
	}
	
	public void setNikName(String nikName) {
		this.nikName = nikName;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getRcmd() {
		return rcmd;
	}
	
	public void setRcmd(String rcmd) {
		this.rcmd = rcmd;
	}
	
	public String getUserAddr() {
		return userAddr;
	}
	
	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}
	
	public String getUserAddr2() {
		return userAddr2;
	}
	
	public void setUserAddr2(String userAddr2) {
		this.userAddr2 = userAddr2;
	}
	
	public int getPointNo() {
		return pointNo;
	}
	
	public void setPointNo(int pointNo) {
		this.pointNo = pointNo;
	}
	
	public Date getJoinDate() {
		return joinDate;
	}
	
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	
}
