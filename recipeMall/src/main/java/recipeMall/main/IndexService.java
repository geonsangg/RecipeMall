package recipeMall.main;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import recipeMall.board.FreeBoardVO;
import recipeMall.recipe.RecipeVO;
import recipeMall.shopping.ProductVO;
import recipeMall.shopping.ShoppingDAO;

public class IndexService {
   IndexDAO dao;
   public IndexService() {
      dao=new IndexDAO();
   }
   public  Map selectIndex() {
      Map ReturnMap=new HashMap();
      List<FreeBoardVO> freeBoardList=dao.selectBoard();
      List<ProductVO> reviewList= dao.selectReview();
      List<RecipeVO> recipeList=dao.selectRecipe();
      for(int i=0; i<recipeList.size(); i++) {
    	  System.out.println("이미지 이름: "+recipeList.get(i).getImgVO().getImgName());
      }
      ReturnMap.put("freeBoardList", freeBoardList);
      ReturnMap.put("reviewList", reviewList);
      ReturnMap.put("recipeList", recipeList);
      
      return ReturnMap;
   }
}