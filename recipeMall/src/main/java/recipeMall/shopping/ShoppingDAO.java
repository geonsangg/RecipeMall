package recipeMall.shopping;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import recipeMall.mypage.CartVO;
import recipeMall.recipe.ImageVO;
import recipeMall.recipe.RecipeVO;

public class ShoppingDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;

	public ShoppingDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle"); // "읽어올 데이터베이스"
		} catch (Exception e) {
			System.out.println("DB연결 오류");
		}
	}

	// 카테고리 조회 메서드
	public List<ProductVO> selectAllMatarial(Map<String, String> pagingMap) {
		List<ProductVO> matarialList = new ArrayList<ProductVO>();
		int section = Integer.parseInt(pagingMap.get("section"));
		int pageNum = Integer.parseInt(pagingMap.get("pageNum"));
		String prodKind_ = pagingMap.get("prodKind");
		String prodName_ = pagingMap.get("prodName");
		if (prodName_.equals("값이 없음") && prodKind_.equals("값이 없음")) {
			prodKind_ = "1";
		}
		try {
			conn = dataFactory.getConnection();
			String query;
			ResultSet rs;
			if (prodKind_.equals("값이 없음")) {
				query = "SELECT rownum, prodCode, prodName, prodKind, prodPrice, prodImage, avgStar, reviewCnt FROM (SELECT prodCode, prodName, prodKind, prodPrice, prodImage, avgStar, reviewCnt, ROW_NUMBER() OVER (ORDER BY prodCode) AS rn FROM producttbl WHERE prodName LIKE ?) WHERE rn BETWEEN (?-1)*90+(?-1)*9+1 AND (?-1)*90+?*9";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "%" + prodName_ + "%");
				pstmt.setInt(2, section);
				pstmt.setInt(3, pageNum);
				pstmt.setInt(4, section);
				pstmt.setInt(5, pageNum);
			} else if (prodName_.equals("값이 없음")) {
				int prodKind_2 = Integer.parseInt(prodKind_);
				query = "SELECT rownum, prodCode, prodName, prodKind, prodPrice, prodImage, avgStar, reviewCnt FROM (SELECT prodCode, prodName, prodKind, prodPrice, prodImage, avgStar, reviewCnt, ROW_NUMBER() OVER (ORDER BY prodCode) AS rn FROM producttbl WHERE prodKind=?) WHERE rn BETWEEN (?-1)*90+(?-1)*9+1 AND (?-1)*90+?*9";
				System.out.println(query);
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, prodKind_2);
				pstmt.setInt(2, section);
				pstmt.setInt(3, pageNum);
				pstmt.setInt(4, section);
				pstmt.setInt(5, pageNum);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int prodCode = rs.getInt("prodCode");
				String prodName = rs.getString("prodName");
				int prodKind = rs.getInt("prodKind");
				int prodPrice = rs.getInt("prodPrice");
				String prodImage = rs.getString("prodImage");
				double avgStar = rs.getDouble("avgStar");
				int reviewCnt = rs.getInt("reviewCnt");
				ProductVO shoppingVO = new ProductVO();
				shoppingVO.setProdCode(prodCode);
				shoppingVO.setProdName(prodName);
				shoppingVO.setProdKind(prodKind);
				shoppingVO.setProdPrice(prodPrice);
				shoppingVO.setProdImage(prodImage);
				shoppingVO.setAvgStar(avgStar);
				shoppingVO.setReviewCnt(reviewCnt);
				matarialList.add(shoppingVO);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("글 목록 페이지 조회 중 에러");
			e.printStackTrace();
		}
		return matarialList;
	}
	
	

	// 보여줄 상품의 총 개수 조회 메서드
	public int selectToMatarial(String prodKind, String prodName) {
		int totCount = 0;
		try {
			conn = dataFactory.getConnection();
			String query;

			if (prodKind.equals("값이 없음")) {
				query = "select count(*) from producttbl where prodName like ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "%" + prodName + "%");
			} else if (prodName.equals("값이 없음")) {
				int prodKind_ = Integer.parseInt(prodKind);
				query = "select count(*) from producttbl where prodKind=?";
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, prodKind_);
			}
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				totCount = rs.getInt(1);
			}
			System.out.println(totCount);
			pstmt.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("전체 글 목록 수 처리중 에러");
			e.printStackTrace();
		}
		return totCount;
	}

	// 별점 높은 순 글 목록 조회 메서드
	public List<ProductVO> slelectReview() {
		List<ProductVO> reviewList = new ArrayList<ProductVO>();
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT prodCode, prodName, prodPrice, prodImage, avgStar, reviewCnt FROM producttbl ORDER BY avgStar DESC";
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
		} catch (Exception e) {
			System.out.println("별점 높은 순 글 목록 조회 처리중 에러");
			e.printStackTrace();
		}
		return reviewList;
	}

	// 역대 베스트 순 글 목록 조회 메서드
	public List<ProductVO> slelectProdCount() {
		List<ProductVO> prodCountList = new ArrayList<ProductVO>();
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT prodCode, prodName, prodPrice, prodImage, avgStar, reviewCnt, prodCount FROM producttbl ORDER BY prodCount DESC";
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int prodCode = rs.getInt("prodCode");
				String prodName = rs.getString("prodName");
				int prodPrice = rs.getInt("prodPrice");
				String prodImage = rs.getString("prodImage");
				double avgStar = rs.getDouble("avgStar");
				int reviewCnt = rs.getInt("reviewCnt");
				int prodCount = rs.getInt("prodCount");
				ProductVO shoppingVO = new ProductVO();
				shoppingVO.setProdCode(prodCode);
				shoppingVO.setProdName(prodName);
				shoppingVO.setProdPrice(prodPrice);
				shoppingVO.setProdImage(prodImage);
				shoppingVO.setAvgStar(avgStar);
				shoppingVO.setReviewCnt(reviewCnt);
				shoppingVO.setProdCount(prodCount);
				prodCountList.add(shoppingVO);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("역대 베스트 순 글 목록 조회 처리중 에러");
			e.printStackTrace();
		}
		return prodCountList;
	}

	// 대표계절 선택 메서드
	public List<ProductVO> selectSeason(List<ProductVO> productList) {
		try {

			conn = dataFactory.getConnection();
			String query = "SELECT prodCode, prodName, prodPrice, prodImage,prodCount,prodSeason, avgStar, reviewCnt  FROM producttbl";
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int prodCode = rs.getInt("prodCode");
				String prodName = rs.getString("prodName");
				int prodPrice = rs.getInt("prodPrice");
				String prodImage = rs.getString("prodImage");
				double avgStar = rs.getDouble("avgStar");
				int reviewCnt = rs.getInt("reviewCnt");
				int prodCount = rs.getInt("prodCount");
				int prodSeason = rs.getInt("prodSeason");
				ProductVO productVO = new ProductVO();
				productVO.setProdCode(prodCode);
				productVO.setProdName(prodName);
				productVO.setProdPrice(prodPrice);
				productVO.setProdImage(prodImage);
				productVO.setAvgStar(avgStar);
				productVO.setReviewCnt(reviewCnt);
				productVO.setProdCount(prodCount);
				productVO.setProdSeason(prodSeason);
				productList.add(productVO);
			}
			;
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("시즌 리스트 조회 중 에러");
			e.printStackTrace();
		}
		return productList;
	}

	// 상품정보 조회 메서드
	public ProductVO selectCode(Map<String, Integer> infoMap) {
		List<ProductVO> productList = new ArrayList<>();
		ProductVO product1VO = new ProductVO();
		int tempCode = infoMap.get("tempCode");
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT  prodCode, prodName, prodKind, prodPrice, prodImage,prodContent1,prodContent2,prodContent3,prodContentImage1,prodContentImage2,"
					+ "prodCount,prodSeason, avgStar, reviewCnt,prodRest FROM producttbl WHERE prodCode=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, tempCode);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int prodCode = rs.getInt("prodCode");
				String prodName = rs.getString("prodName");
				int prodKind = rs.getInt("prodKind");
				int prodPrice = rs.getInt("prodPrice");
				String prodImage = rs.getString("prodImage");
				String prodContent1 = rs.getString("prodContent1");
				String prodContent2 = rs.getString("prodContent2");
				String prodContent3 = rs.getString("prodContent3");
				String prodContentImage1 = rs.getString("prodContentImage1");
				String prodContentImage2 = rs.getString("prodContentImage2");
				int prodCount = rs.getInt("prodCount");
				int prodSeason = rs.getInt("prodSeason");
				double avgStar = rs.getDouble("avgStar");
				int reviewCnt = rs.getInt("reviewCnt");
				int prodRest = rs.getInt("prodRest");

				product1VO.setProdCode(prodCode);
				product1VO.setProdName(prodName);
				product1VO.setProdKind(prodKind);
				product1VO.setProdPrice(prodPrice);
				product1VO.setProdImage(prodImage);
				product1VO.setProdContent1(prodContent1);
				product1VO.setProdContent2(prodContent2);
				product1VO.setProdContent3(prodContent3);
				product1VO.setProdContentImage1(prodContentImage1);
				product1VO.setProdContentImage2(prodContentImage2);
				product1VO.setProdCount(prodCount);
				product1VO.setProdSeason(prodSeason);
				product1VO.setAvgStar(avgStar);
				product1VO.setReviewCnt(reviewCnt);
				product1VO.setProdRest(prodRest);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("상품정보 조회 중 에러");
			e.printStackTrace();
		}
		return product1VO;

	}

	// 상품정보 리뷰 조회 메서드
	public List<ReviewVO> selectReview(Map<String, Integer> infoMap) {
		List<ReviewVO> reviewList = new ArrayList<>();
		int tempCode = infoMap.get("tempCode");
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT  prodCode, reviewCode, reviewContent, id,reviewDate"
					+ " FROM reviewtbl WHERE prodCode=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, tempCode);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int prodCode = rs.getInt("prodCode");
				int reviewCode = rs.getInt("reviewCode");
				String reviewContent = rs.getString("reviewContent");
				String id = rs.getString("id");
				Date reviewDate = rs.getDate("reviewDate");
				ReviewVO reviewVO = new ReviewVO();
				reviewVO.setProdCode(prodCode);
				reviewVO.setReviewCode(reviewCode);
				reviewVO.setReviewContent(reviewContent);
				reviewVO.setId(id);
				reviewVO.setReviewDate(reviewDate);
				reviewList.add(reviewVO);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("상품정보 리뷰 조회 중 에러");
			e.printStackTrace();
		}
		return reviewList;
	}

	// 비슷한 상품 조회 메서드
	public List<ProductVO> selectSimilarProduct(Map<String, Integer> infoMap) {
		List<ProductVO> similarList = new ArrayList<>();
		int tempCode = infoMap.get("tempCode");
		System.out.println(tempCode);
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT * FROM producttbl WHERE prodKind=(select prodKind from producttbl where prodCode=?) and  ROWNUM BETWEEN 1 AND 5";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, tempCode);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int prodCode = rs.getInt("prodCode");
				String prodName = rs.getString("prodName");
				int prodKind = rs.getInt("prodKind");
				int prodPrice = rs.getInt("prodPrice");
				String prodImage = rs.getString("prodImage");
				String prodContent1 = rs.getString("prodContent1");
				String prodContent2 = rs.getString("prodContent2");
				String prodContent3 = rs.getString("prodContent3");
				String prodContentImage1 = rs.getString("prodContentImage1");
				String prodContentImage2 = rs.getString("prodContentImage2");
				int prodCount = rs.getInt("prodCount");
				int prodSeason = rs.getInt("prodSeason");
				ProductVO productVO = new ProductVO();
				productVO.setProdCode(prodCode);
				productVO.setProdName(prodName);
				productVO.setProdKind(prodKind);
				productVO.setProdPrice(prodPrice);
				productVO.setProdImage(prodImage);
				productVO.setProdContent1(prodContent1);
				productVO.setProdContent2(prodContent2);
				productVO.setProdContent3(prodContent3);
				productVO.setProdContentImage1(prodContentImage1);
				productVO.setProdContentImage2(prodContentImage2);
				productVO.setProdCount(prodCount);
				productVO.setProdSeason(prodSeason);
				similarList.add(productVO);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("비슷한 상품 조회 중 에러");
			e.printStackTrace();
		}
		return similarList;
	}

	// 장바구니 등록 메서드
	public void insertCart(CartVO cartVO) {
		try {
			String prodName = cartVO.getProdName();
			String prodImage = cartVO.getProdImage();
			int cartCount = cartVO.getCartCount();
			int prodPrice = cartVO.getProdPrice();
			String id = cartVO.getId();
			int prodCode = cartVO.getProdCode();
			System.out.println(prodPrice);
			conn = dataFactory.getConnection();
			String query = "insert into carttbl(prodName,prodImage,cartCount,prodPrice,id,prodCode) values(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, prodName);
			pstmt.setString(2, prodImage);
			pstmt.setInt(3, cartCount);
			pstmt.setInt(4, prodPrice);
			pstmt.setString(5, id);
			pstmt.setInt(6, prodCode);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("장바구니 등록 중 에러");
			e.printStackTrace();
		}
	}

	// 상품문의 보여주는 메서드
	public List<ProductFaqVO> selectFaq(Map<String, Integer> infoMap) {
		List<ProductFaqVO> productFaqList = new ArrayList<>();
		int tempCode = infoMap.get("tempCode");
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT * FROM productFaqtbl where prodCode=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, tempCode);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int prodCode = rs.getInt("prodCode");
				String prodFaqTitle = rs.getString("prodFaqTitle");
				String prodContent = rs.getString("prodContent");
				String adminId = rs.getString("adminId");
				String Content = rs.getString("Content");
				int hasComent = rs.getInt("hasComent");
				String id = rs.getString("id");
				ProductFaqVO productFaqVO = new ProductFaqVO();
				productFaqVO.setProdFaqTitle(prodFaqTitle);
				productFaqVO.setProdCode(prodCode);
				productFaqVO.setProdContent(prodContent);
				productFaqVO.setAdminId(adminId);
				productFaqVO.setContent(Content);
				productFaqVO.setHasComent(hasComent);
				productFaqVO.setId(id);
				productFaqList.add(productFaqVO);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("상품 문의 등록 중 에러");
			e.printStackTrace();
		}
		return productFaqList;
	}

	// 상품문의 등록 등록 메서드
	public void insertFaq(ProductFaqVO prodFaqVO) {
		try {
			String prodFaqTitle = prodFaqVO.getProdFaqTitle();
			String content = prodFaqVO.getContent();
			String id = prodFaqVO.getId();
			int prodCode = prodFaqVO.getProdCode();
			String prodName = prodFaqVO.getProdName();
			conn = dataFactory.getConnection();
			String query = "insert into productFaqtbl(content,id,prodCode,prodFaqTitle,prodName,prodfaqno) values(?,?,?,?,?,prodfaqno_seq.nextval)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, content);
			pstmt.setString(2, id);
			pstmt.setInt(3, prodCode);
			pstmt.setString(4, prodFaqTitle);
			pstmt.setString(5, prodName);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("문의 등록 중 에러");
			e.printStackTrace();
		}
	}

	// 재료 페이지 오늘의 레시피 가져옴
	/*public List<RecipeVO> selectRecipe() {
		List<RecipeVO> recipeList = new ArrayList();
		try {
			conn = dataFactory.getConnection();
			String query = "select r.recipeTitle,r.recipeNo, i.imgName from recipetbl r,imagetbl i where r.recipeNo=i.target and i.imgCate=1";
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String recipeTitle = rs.getString("recipeTitle");
				String imgName = rs.getString("imgName");
				int recipeNo = rs.getInt("recipeNo");
				RecipeVO recipeVO = new RecipeVO();
				recipeVO.setRecipeTitle(recipeTitle);
				recipeVO.getImgVO().setImgName(imgName);
				recipeVO.setRecipeNo(recipeNo);
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
	}*/
	
	public List<RecipeVO> selectRecipe() {
		List<RecipeVO> recipeList = new ArrayList();
		try {
			conn = dataFactory.getConnection();
			String query = "select r.recipeNo, r.recipeTitle,im.imgName from recipetbl r,imagetbl im where r.recipeNo=im.target and im.imgCate=1 ";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int recipeNo = rs.getInt("recipeNo");
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
