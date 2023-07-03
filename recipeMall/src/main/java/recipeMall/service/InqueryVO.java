package recipeMall.service;

import java.util.Date;

public class InqueryVO {
	private int inqNo;
	private String id;
	private String inqTitle;
	private String inqContent;
	private String adminId;
	private String inqReply;
	private int hasRe;
	private int inqCate;
	private Date inqDate;
	
	public int getInqNo() {
		return inqNo;
	}
	public void setInqNo(int inqNo) {
		this.inqNo = inqNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInqTitle() {
		return inqTitle;
	}
	public void setInqTitle(String inqTitle) {
		this.inqTitle = inqTitle;
	}
	public String getInqContent() {
		return inqContent;
	}
	public void setInqContent(String inqContent) {
		this.inqContent = inqContent;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getInqReply() {
		return inqReply;
	}
	public void setInqReply(String inqReply) {
		this.inqReply = inqReply;
	}
	public int getHasRe() {
		return hasRe;
	}
	public void setHasRe(int hasRe) {
		this.hasRe = hasRe;
	}
	public Date getInqDate() {
		return inqDate;
	}
	public void setInqDate(Date inqDate) {
		this.inqDate = inqDate;
	}
	public int getInqCate() {
		return inqCate;
	}
	public void setInqCate(int inqCate) {
		this.inqCate = inqCate;
	}
	
	
	
}
