package recipeMall.recipe;

import java.sql.Date;

import recipeMall.user.UserVO;

public class RecipeVO {
	private int recipeNo;
	private String recipeTitle;
	private String recipeIntro;
	private String videoLink;
	private String cate;
	private String recipeTip;
	private Date writeDate;
	private String id;
	private int cntLike;
	private UserVO userVO;
	private RecipeInfoVO infoVO;
	private MaterialVO mateVO;
	private MaterialtypeVO matetypeVO;
	private RecipeStepVO stepVO;
	private ImageVO imgVO;
	private LikeVO likeVO;
	

	public RecipeVO() {
		
	}
	

	public RecipeVO(int recipeNo, String recipeTitle, String recipeIntro, String videoLink, String cate,
			String recipeTip, Date writeDate, String id, int cntLike, UserVO userVO, RecipeInfoVO infoVO,
			MaterialVO mateVO, MaterialtypeVO matetypeVO, RecipeStepVO stepVO, ImageVO imgVO, LikeVO likeVO) {
		super();
		this.recipeNo = recipeNo;
		this.recipeTitle = recipeTitle;
		this.recipeIntro = recipeIntro;
		this.videoLink = videoLink;
		this.cate = cate;
		this.recipeTip = recipeTip;
		this.writeDate = writeDate;
		this.id = id;
		this.cntLike = cntLike;
		this.userVO = userVO;
		this.infoVO = infoVO;
		this.mateVO = mateVO;
		this.matetypeVO = matetypeVO;
		this.stepVO = stepVO;
		this.imgVO = imgVO;
		this.likeVO = likeVO;
	}
	


	public int getRecipeNo() {
		return recipeNo;
	}

	public void setRecipeNo(int recipeNo) {
		this.recipeNo = recipeNo;
	}
	
	
	

	public String getRecipeTitle() {
		return recipeTitle;
	}






	public void setRecipeTitle(String recipeTitle) {
		this.recipeTitle = recipeTitle;
	}






	public String getRecipeIntro() {
		return recipeIntro;
	}

	public void setRecipeIntro(String recipeIntro) {
		this.recipeIntro = recipeIntro;
	}

	public String getVideoLink() {
		return videoLink;
	}

	public void setVideoLink(String videoLink) {
		this.videoLink = videoLink;
	}



	public String getCate() {
		return cate;
	}




	public void setCate(String cate) {
		this.cate = cate;
	}





	public String getRecipeTip() {
		return recipeTip;
	}

	public void setRecipeTip(String recipeTip) {
		this.recipeTip = recipeTip;
	}

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCntLike() {
		return cntLike;
	}

	public void setCntLike(int cntLike) {
		this.cntLike = cntLike;
	}



	public UserVO getUserVO() {
		return userVO;
	}



	public void setUserVO(UserVO userVO) {
		this.userVO = userVO;
	}





	public RecipeInfoVO getInfoVO() {
		return infoVO;
	}





	public void setInfoVO(RecipeInfoVO infoVO) {
		this.infoVO = infoVO;
	}





	public MaterialVO getMateVO() {
		return mateVO;
	}





	public void setMateVO(MaterialVO mateVO) {
		this.mateVO = mateVO;
	}





	public MaterialtypeVO getMatetypeVO() {
		return matetypeVO;
	}





	public void setMatetypeVO(MaterialtypeVO matetypeVO) {
		this.matetypeVO = matetypeVO;
	}





	public RecipeStepVO getStepVO() {
		return stepVO;
	}

	



	public Date getWriteDate() {
		return writeDate;
	}



	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}



	public void setStepVO(RecipeStepVO stepVO) {
		this.stepVO = stepVO;
	}








	public ImageVO getImgVO() {
		return imgVO;
	}








	public void setImgVO(ImageVO imgVO) {
		this.imgVO = imgVO;
	}


	public LikeVO getLikeVO() {
		return likeVO;
	}


	public void setLikeVO(LikeVO likeVO) {
		this.likeVO = likeVO;
	}

	
	
	


	
	
	
	
}
