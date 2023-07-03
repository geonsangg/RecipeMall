package recipeMall.shopping;

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
   
   public ReviewVO() {
      // TODO Auto-generated constructor stub
   }

   

   public ReviewVO(int prodCode, int reviewCode, String prodImage, String reviewContent, String id, int reviewStar,
         Date reviewDate, String prodName) {
      super();
      this.prodCode = prodCode;
      this.reviewCode = reviewCode;
      this.prodImage = prodImage;
      this.reviewContent = reviewContent;
      this.id = id;
      this.reviewStar = reviewStar;
      this.reviewDate = reviewDate;
      this.prodName = prodName;
   }

   public ReviewVO(int prodCode, int reviewCode, String prodImage, String reviewContent, String id, int reviewStar,
         String prodName) {
      super();
      this.prodCode = prodCode;
      this.reviewCode = reviewCode;
      this.prodImage = prodImage;
      this.reviewContent = reviewContent;
      this.id = id;
      this.reviewStar = reviewStar;
      this.prodName = prodName;
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