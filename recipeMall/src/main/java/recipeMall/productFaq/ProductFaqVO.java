package recipeMall.productFaq;

import java.sql.Date;

public class ProductFaqVO {
	private int prodFaqNo;
	private String prodFaqTitle;
	private String content;
	private String adminId;
	private String prodContent;
	private int hasComent;
	private String id;
	private int prodCode;
	private String prodName;
	private Date prodFaqDate;
	

	public ProductFaqVO() {
	// TODO Auto-generated constructor stub

	}

	public ProductFaqVO(String prodFaqTitle, String content, String adminId, String prodContent, int hasComent,
			String id, int prodCode, String prodName) {
		super();
		this.prodFaqTitle = prodFaqTitle;
		this.content = content;
		this.adminId = adminId;
		this.prodContent = prodContent;
		this.hasComent = hasComent;
		this.id = id;
		this.prodCode = prodCode;
		this.prodName = prodName;
	}

	
	
	public int getProdFaqNo() {
		return prodFaqNo;
	}

	public void setProdFaqNo(int prodFaqNo) {
		this.prodFaqNo = prodFaqNo;
	}

	public Date getProdFaqDate() {
		return prodFaqDate;
	}

	public void setProdFaqDate(Date prodFaqDate) {
		this.prodFaqDate = prodFaqDate;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getProdFaqTitle() {
		return prodFaqTitle;
	}


	public void setProdFaqTitle(String prodFaqTitle) {
		this.prodFaqTitle = prodFaqTitle;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getAdminId() {
		return adminId;
	}


	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}


	public String getProdContent() {
		return prodContent;
	}


	public void setProdContent(String prodContent) {
		this.prodContent = prodContent;
	}


	public int getHasComent() {
		return hasComent;
	}


	public void setHasComent(int hasComent) {
		this.hasComent = hasComent;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public int getProdCode() {
		return prodCode;
	}


	public void setProdCode(int prodCode) {
		this.prodCode = prodCode;
	}
	
	
}
