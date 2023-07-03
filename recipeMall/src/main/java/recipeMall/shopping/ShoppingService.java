package recipeMall.shopping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import recipeMall.recipe.RecipeVO;

public class ShoppingService {
   ShoppingDAO shoppingDAO;
   
   public ShoppingService() {
      shoppingDAO = new ShoppingDAO();
   }
   
   public Map listMatarial(Map<String, String> pagingMap) {
	   Map matarialMap = new HashMap();
	      List<ProductVO> matarialList = shoppingDAO.selectAllMatarial(pagingMap);
	      String prodKind = pagingMap.get("prodKind");
	      String prodName = pagingMap.get("prodName");
	      if(prodName.equals("값이 없음") && prodKind.equals("값이 없음")) {
	         prodKind = "1";
	      }
	      int totmatarial = shoppingDAO.selectToMatarial(prodKind, prodName);
	      List<RecipeVO> recipeList=shoppingDAO.selectRecipe();
	      matarialMap.put("matarialList", matarialList);
	      matarialMap.put("totmatarial", totmatarial);
	      matarialMap.put("recipeList", recipeList);
	      return matarialMap;
	}
	
	public Map bestReview() {
		Map bestMap = new HashMap();
		List<ProductVO> reviewList = shoppingDAO.slelectReview();
		List<ProductVO> bestList = shoppingDAO.slelectProdCount();
		bestMap.put("reviewList", reviewList);
		bestMap.put("bestList", bestList);
		return bestMap;
	}
   public Map selectProdInfo(Map<String, Integer> infoMap) {
      Map productInfoMap = new HashMap();
      ProductVO productList =shoppingDAO.selectCode(infoMap);
      List<ReviewVO> reviewList= shoppingDAO.selectReview(infoMap);
      List<ProductVO> similarList= shoppingDAO.selectSimilarProduct(infoMap);
      List<ProductFaqVO> productFaqList =shoppingDAO.selectFaq(infoMap);
      productInfoMap.put("productList", productList);
      productInfoMap.put("reviewList", reviewList);
      productInfoMap.put("similarList", similarList);
      productInfoMap.put("productFaqList", productFaqList);
      return productInfoMap;
   }
   public Map selectshopIndex() {
	      Map shoppingIndexMap = new HashMap();
	      List<ProductVO> productList=new ArrayList<>();
	      List<ProductVO> prodCountList=new ArrayList<>();
	      productList =shoppingDAO.selectSeason(productList);
	      prodCountList =shoppingDAO.slelectProdCount();	// 쇼핑 메인 베스트 상품 5개 가져옴
	      if(productList != null) {
	    	  System.out.println("상품리스트 받음");
	      }
	      if(prodCountList != null) {
	    	  System.out.println("베스트 받음");
	      }
	      shoppingIndexMap.put("productList", productList);
	      shoppingIndexMap.put("prodCountList", prodCountList);
	      return shoppingIndexMap;
	   }
}