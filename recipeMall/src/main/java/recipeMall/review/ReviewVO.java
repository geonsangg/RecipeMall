package recipeMall.review;

import java.sql.Date;

public class ReviewVO {
    private int prodCode;
    private int reviewCode;
    private String prodImage;
    private String reviewContent;
    private String id;
    private int reviewStar;
    private Date reviewDate;
    private String prodName;
    private int orderCode;

    public ReviewVO() {
        // 기본 생성자
    }

    public ReviewVO(int prodCode, int reviewCode, String prodImage, String reviewContent, String id, int reviewStar, String prodName, Date reviewDate, int orderCode) {
        this.prodCode = prodCode;
        this.reviewCode = reviewCode;
        this.prodImage = prodImage;
        this.reviewContent = reviewContent;
        this.id = id;
        this.reviewStar = reviewStar;
        this.prodName = prodName;
        this.reviewDate = reviewDate;
        this.orderCode = orderCode;
    }

    public int getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}

	public int getProdCode() {
        return prodCode;
    }

    public void setProdCode(int prodCode) {
        this.prodCode = prodCode;
    }

    public int getReviewCode() {
        return reviewCode;
    }

    public void setReviewCode(int reviewCode) {
        this.reviewCode = reviewCode;
    }

    public String getProdImage() {
        return prodImage;
    }

    public void setProdImage(String prodImage) {
        this.prodImage = prodImage;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getReviewStar() {
        return reviewStar;
    }

    public void setReviewStar(int reviewStar) {
        this.reviewStar = reviewStar;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }
}
