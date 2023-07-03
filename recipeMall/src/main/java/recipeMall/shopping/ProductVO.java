package recipeMall.shopping;

import java.sql.Date;

public class ProductVO {
	private int prodCode;
	private String prodName;
	private int prodKind;
	private int prodPrice;
	private String prodImage;
	private Date prodDate;
	private String prodContent1;
	private String prodContent2;
	private String prodContent3;
	private String prodContentImage1;
	private String prodContentImage2;
	private int prodCount;
	private int prodSeason;
	private double avgStar;
	private int reviewCnt;
	private int prodRest;
	
	public ProductVO() {
	
	}

	public int getProdCode() {
		return prodCode;
	}

	public void setProdCode(int prodCode) {
		this.prodCode = prodCode;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public int getProdKind() {
		return prodKind;
	}

	public void setProdKind(int prodKind) {
		this.prodKind = prodKind;
	}

	public int getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(int prodPrice) {
		this.prodPrice = prodPrice;
	}

	public String getProdImage() {
		return prodImage;
	}

	public void setProdImage(String prodImage) {
		this.prodImage = prodImage;
	}

	public Date getProdDate() {
		return prodDate;
	}

	public void setProdDate(Date prodDate) {
		this.prodDate = prodDate;
	}

	public String getProdContent1() {
		return prodContent1;
	}

	public void setProdContent1(String prodContent1) {
		this.prodContent1 = prodContent1;
	}

	public String getProdContent2() {
		return prodContent2;
	}

	public void setProdContent2(String prodContent2) {
		this.prodContent2 = prodContent2;
	}

	public String getProdContent3() {
		return prodContent3;
	}

	public void setProdContent3(String prodContent3) {
		this.prodContent3 = prodContent3;
	}

	public String getProdContentImage1() {
		return prodContentImage1;
	}

	public void setProdContentImage1(String prodContentImage1) {
		this.prodContentImage1 = prodContentImage1;
	}

	public String getProdContentImage2() {
		return prodContentImage2;
	}

	public void setProdContentImage2(String prodContentImage2) {
		this.prodContentImage2 = prodContentImage2;
	}

	public int getProdCount() {
		return prodCount;
	}

	public void setProdCount(int prodCount) {
		this.prodCount = prodCount;
	}

	
	public int getProdSeason() {
		return prodSeason;
	}

	public void setProdSeason(int prodSeason) {
		this.prodSeason = prodSeason;
	}

	public double getAvgStar() {
		return avgStar;
	}

	public void setAvgStar(double avgStar) {
		this.avgStar = avgStar;
	}

	public int getReviewCnt() {
		return reviewCnt;
	}

	public void setReviewCnt(int reviewCnt) {
		this.reviewCnt = reviewCnt;
	}

	public int getProdRest() {
		return prodRest;
	}

	public void setProdRest(int prodRest) {
		this.prodRest = prodRest;
	}
	
	
}
