package recipeMall.recipe;

import java.sql.Date;

public class LikeVO {
   private int likeNo;	// 좋아요 번호
   private int recipeNo;	// 좋아요 누른 레시피 번호
   private String id;	// 아이디
   private Date likeDate;	// 좋아요 누른 날짜
   private int likeValue;	// 좋아요 상태
   private int likeCnt; 	// like 총 개수, db 데이터에 따라 유동적으로 달라짐
   
   



public LikeVO() {
     
   }

   public LikeVO(int likeNo, int recipeNo, String id, Date likeDate, int likeValue) {
      super();
      this.likeNo = likeNo;
      this.recipeNo = recipeNo;
      this.id = id;
      this.likeDate = likeDate;
      this.likeValue = likeValue;
   }

   public int getLikeNo() {
      return likeNo;
   }

   public void setLikeNo(int likeNo) {
      this.likeNo = likeNo;
   }

   public int getRecipeNo() {
      return recipeNo;
   }

   public void setRecipeNo(int recipeNo) {
      this.recipeNo = recipeNo;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public Date getLikeDate() {
      return likeDate;
   }

   public void setLikeDate(Date likeDate) {
      this.likeDate = likeDate;
   }

   public int getLikeValue() {
      return likeValue;
   }

   public void setLikeValue(int likeValue) {
      this.likeValue = likeValue;
   }
   
   public int getLikeCnt() {
		return likeCnt;
	}

	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}

   
   
   
}