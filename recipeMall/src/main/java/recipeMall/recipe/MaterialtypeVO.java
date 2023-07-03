package recipeMall.recipe;

import java.util.List;

public class MaterialtypeVO {
	private int matetypeNo;
	private String matetypeName;
	private int recipeNo;
	
	private List<MaterialtypeVO> MaterialtypeList;

	public int getMatetypeNo() {
		return matetypeNo;
	}

	public void setMatetypeNo(int matetypeNo) {
		this.matetypeNo = matetypeNo;
	}

	public String getMatetypeName() {
		return matetypeName;
	}

	public void setMatetypeName(String matetypeName) {
		this.matetypeName = matetypeName;
	}

	public int getRecipeNo() {
		return recipeNo;
	}

	public void setRecipeNo(int recipeNo) {
		this.recipeNo = recipeNo;
	}

	public List<MaterialtypeVO> getMaterialtypeList() {
		return MaterialtypeList;
	}

	public void setMaterialtypeList(List<MaterialtypeVO> materialtypeList) {
		MaterialtypeList = materialtypeList;
	}
	
}
