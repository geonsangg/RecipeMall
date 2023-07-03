package recipeMall.recipe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeService {
	RecipeDAO recipeDAO;
	
	public RecipeService() {
		recipeDAO=new RecipeDAO();
	}
	 
	   
   //레시피 목록 조회 + 검색 
      public Map listRecipe(Map<String, String> pagingMap) {
         Map recipeMap=new HashMap();
         List<RecipeVO> recipeList=recipeDAO.selectAllRecipe(pagingMap);
         String cate = pagingMap.get("cate");
         String recipeTitle = pagingMap.get("recipeTitle");
         String recipeId = pagingMap.get("recipeId");
         if(cate.equals("값이 없음") && recipeTitle.equals("값이 없음") && recipeId.equals("값이 없음")) {
            cate = "ko";
            
         }
         int totRecipe = recipeDAO.selectToRecipe(cate, recipeTitle, recipeId);
         recipeMap.put("recipeList", recipeList);
         recipeMap.put("totRecipe", totRecipe);
         return recipeMap;
      }   
	
	//글 추가 요청 서비스
	public int insertRecipe(RecipeVO recipeVO) {
		
		int recipeNo= recipeDAO.getNewRecipeNo();
		recipeDAO.insertNewRecipetbl(recipeVO);
		recipeDAO.insertNewRecipeInfotbl(recipeVO, recipeNo);
		
		//받는값 list 
		recipeDAO.insertNewMateTypetbl(recipeVO, recipeNo);
		recipeDAO.insertNewMatetbl(recipeVO, recipeNo);
		recipeDAO.insertNewSteptbl(recipeVO, recipeNo);
		recipeDAO.insertImgtbl(recipeVO, recipeNo);
		return recipeNo;
	}
	
	
	
	//글 상세보기 서비스 
	public RecipeVO viewRecipe(String id ,int recipeNo) {
		RecipeVO recipeVO=null;
		RecipeInfoVO infoVO= null;
		MaterialtypeVO matetypeVO=null;
		MaterialVO mateVO=null;
		RecipeStepVO stepVO=null;
		ImageVO imgVO= null;
		LikeVO likeVO= null;
		recipeVO=recipeDAO.selectRecipetbl(recipeNo);
		infoVO=recipeDAO.selectInfotbl(recipeNo);
		matetypeVO=recipeDAO.selectMatetypetbl(recipeNo);
		mateVO=recipeDAO.selectMatetbl(recipeNo);
		stepVO=recipeDAO.selectSteptbl(recipeNo);
		imgVO=recipeDAO.selectImgtbl(recipeNo);
		likeVO=recipeDAO.selectLike(id, recipeNo);
		recipeVO.setInfoVO(infoVO);
		recipeVO.setMatetypeVO(matetypeVO);
		recipeVO.setMateVO(mateVO);
		recipeVO.setStepVO(stepVO);
		recipeVO.setImgVO(imgVO);
		recipeVO.setLikeVO(likeVO);
		return recipeVO;
	}
	
	
	//최대값 가져오기 - 초기값 1로 수정했음 
	public int maxTypeNo() {
		int maxTypeNo = 1;
		
		maxTypeNo = recipeDAO.selectMaxTypeNo();
		
		return maxTypeNo;
	}
	
	 // 좋아요 정보 삽입
    public void insertLike(int recipeNo, String id, int cntLike) {
        recipeDAO.insertLike(recipeNo, id);
        recipeDAO.plusCntLike(recipeNo,cntLike);
    }
    
    //좋아요 정보 수정 - 좋아요 취소 했다가 다시 좋아요 누른 사람 
    public void updateLike(int recipeNo, String id, int cntLike) {
    	recipeDAO.updateLike(recipeNo, id);
    	recipeDAO.plusCntLike(recipeNo,cntLike);
    }
   

    // 좋아요 정보 삭제
    public void deleteLike(int recipeNo, String id, int cntLike) {
        recipeDAO.deleteLike(recipeNo, id);
        recipeDAO.minusCntLike(recipeNo,cntLike);
    }

	


}