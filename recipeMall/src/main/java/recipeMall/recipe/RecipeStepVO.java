package recipeMall.recipe;

import java.util.List;

public class RecipeStepVO {
	private int recipeStepNo;
	private int stepSe;
	private String stepEx;
	private int recipeNo;
	
	private List<RecipeStepVO> RecipeStepList;

	public int getRecipeStepNo() {
		return recipeStepNo;
	}

	public void setRecipeStepNo(int recipeStepNo) {
		this.recipeStepNo = recipeStepNo;
	}

	public int getStepSe() {
		return stepSe;
	}

	public void setStepSe(int stepSe) {
		this.stepSe = stepSe;
	}

	public String getStepEx() {
		return stepEx;
	}

	public void setStepEx(String stepEx) {
		this.stepEx = stepEx;
	}

	public int getRecipeNo() {
		return recipeNo;
	}

	public void setRecipeNo(int recipeNo) {
		this.recipeNo = recipeNo;
	}

	public List<RecipeStepVO> getRecipeStepList() {
		return RecipeStepList;
	}

	public void setRecipeStepList(List<RecipeStepVO> recipeStepList) {
		RecipeStepList = recipeStepList;
	}
	
}
