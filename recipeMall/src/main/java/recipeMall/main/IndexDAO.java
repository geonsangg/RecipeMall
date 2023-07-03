package recipeMall.main;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import recipeMall.board.FreeBoardVO;
import recipeMall.recipe.ImageVO;
import recipeMall.recipe.RecipeVO;
import recipeMall.shopping.ProductVO;

public class IndexDAO {
   private Connection conn;
   private PreparedStatement pstmt;
   private DataSource dataFactory;   // 서버 가동시 db정보 전달, 클라 요청할 때마다 db연결
   
   public IndexDAO(){
      try {
         Context ctx=new InitialContext();
         Context envContext=(Context)ctx.lookup("java:/comp/env");
         dataFactory=(DataSource)envContext.lookup("jdbc/oracle");
      } catch (Exception e) {
         System.out.println("DB연결 오류");
      }
   }
   public List<FreeBoardVO> selectBoard() {
	    List<FreeBoardVO> freeBoardList = new ArrayList<>();
	    try {
	        conn = dataFactory.getConnection();
	        String query = "SELECT boardNo, boardName, writeDate FROM freeboardtbl";
	        System.out.println(query);
	        pstmt = conn.prepareStatement(query);
	        ResultSet rs = pstmt.executeQuery();
	        while (rs.next()) {
	            int boardNo = rs.getInt("boardNo");
	            String boardName = rs.getString("boardName");
	            System.out.println(boardName);
	            Date writeDate = rs.getDate("writeDate");
	            System.out.println(writeDate);
	            FreeBoardVO freeBoardVo = new FreeBoardVO();
	            freeBoardVo.setBoardNo(boardNo);
	            freeBoardVo.setBoardName(boardName);
	            freeBoardVo.setWriteDate(writeDate);
	            freeBoardList.add(freeBoardVo);
	        }
	        rs.close();
	        pstmt.close();
	        conn.close();
	    } catch (Exception e) {
	        System.out.println("게시판 정보 가져오는 중 에러");
	        e.printStackTrace();
	    }
	    return freeBoardList;
	}
   // 별점 높은 순 글 목록 조회 메서드
   public List<ProductVO> selectReview() {
      List<ProductVO> reviewList = new ArrayList<ProductVO>();
      try {
         conn = dataFactory.getConnection();
         String query = "SELECT prodCode, prodName, prodPrice, prodImage, avgStar, reviewCnt FROM producttbl ORDER BY avgStar DESC";
         System.out.println(query);
         pstmt = conn.prepareStatement(query);
         ResultSet rs = pstmt.executeQuery();
         while (rs.next()) {
            int prodCode = rs.getInt("prodCode");
            String prodName = rs.getString("prodName");
            int prodPrice = rs.getInt("prodPrice");
            String prodImage = rs.getString("prodImage");
            double avgStar = rs.getDouble("avgStar");
            int reviewCnt = rs.getInt("reviewCnt");

            ProductVO shoppingVO = new ProductVO();
            shoppingVO.setProdCode(prodCode);
            shoppingVO.setProdName(prodName);
            shoppingVO.setProdPrice(prodPrice);
            shoppingVO.setProdImage(prodImage);
            shoppingVO.setAvgStar(avgStar);
            shoppingVO.setReviewCnt(reviewCnt);
           

            reviewList.add(shoppingVO);
         }
         rs.close();
         pstmt.close();
         conn.close();
      }catch (Exception e) {
         System.out.println("별점 높은 순 글 목록 조회 처리중 에러");
         e.printStackTrace();
      }
      return reviewList;
   }
   public List<RecipeVO> selectRecipe() {
	      List<RecipeVO> recipeList = new ArrayList();
	      
	      try {
	         conn = dataFactory.getConnection();
	         String query = "select r.recipeNo, r.recipeTitle,im.imgName from recipetbl r,imagetbl im where r.recipeNo=im.target and im.imgCate=1";
	         System.out.println(query);
	         pstmt = conn.prepareStatement(query);
	         ResultSet rs = pstmt.executeQuery();
	         while (rs.next()) {
	        	int recipeNo =rs.getInt("recipeNo");
	            String recipeTitle = rs.getString("recipeTitle");
	            String imgName = rs.getString("imgName");
	            RecipeVO recipeVO = new RecipeVO();
	            recipeVO.setRecipeNo(recipeNo);
	            recipeVO.setRecipeTitle(recipeTitle);
	            ImageVO imgVO = new ImageVO();
	            imgVO.setImgName(imgName);
	            recipeVO.setImgVO(imgVO);
	            recipeList.add(recipeVO);
	         }
	         rs.close();
	         pstmt.close();
	         conn.close();
	      } catch (Exception e) {
	         System.out.println("레시피 가져오던중에러");
	         e.printStackTrace();
	      }
	     
	      return recipeList;
	   }
}