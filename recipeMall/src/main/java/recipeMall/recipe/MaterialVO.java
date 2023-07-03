package recipeMall.recipe;

import java.util.List;

public class MaterialVO {
	private int mateNo;
	private int matetypeNo;
	private String mateName;
	private String mateAmount;
	private int recipeNo;
	
	private List<MaterialVO> MaterialList;

	public int getMateNo() {
		return mateNo;
	}

	public void setMateNo(int mateNo) {
		this.mateNo = mateNo;
	}

	public int getMatetypeNo() {
		return matetypeNo;
	}

	public void setMatetypeNo(int matetypeNo) {
		this.matetypeNo = matetypeNo;
	}

	public String getMateName() {
		return mateName;
	}

	public void setMateName(String mateName) {
		this.mateName = mateName;
	}

	public String getMateAmount() {
		return mateAmount;
	}

	public void setMateAmount(String mateAmount) {
		this.mateAmount = mateAmount;
	}

	public int getRecipeNo() {
		return recipeNo;
	}

	public void setRecipeNo(int recipeNo) {
		this.recipeNo = recipeNo;
	}

	public List<MaterialVO> getMaterialList() {
		return MaterialList;
	}

	public void setMaterialList(List<MaterialVO> materialList) {
		MaterialList = materialList;
	}
}
