package recipeMall.review;

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

import recipeMall.order.OrderDVO;
import recipeMall.order.OrderVO;
import recipeMall.shopping.ProductVO;

public class ReviewDAO {
    private Connection conn;
    private PreparedStatement pstmt;
    private DataSource dataFactory;

    public ReviewDAO() {
        try {
            Context ctx = new InitialContext();
            Context envContext = (Context) ctx.lookup("java:/comp/env");
            dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
        } catch (Exception e) {
            System.out.println("DB 연결 오류");
            e.printStackTrace();
        }
    }

    public List<ReviewVO> selectReviewList(String id) {
        List<ReviewVO> reviewList = new ArrayList<>();
        try {
            conn = dataFactory.getConnection();
            //로그인 후에 구현시 이걸 사용하면 됨//String query = "SELECT prodCode, reviewCode, prodImage, reviewContent, id, reviewStar, reviewDate, prodName, orderCode FROM reviewtbl WHERE id = ?";
            String query = "SELECT prodCode, reviewCode, prodImage, reviewContent, id, reviewStar, reviewDate, prodName, orderCode FROM reviewtbl WHERE id = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ReviewVO reviewVO = new ReviewVO();
                reviewVO.setProdCode(rs.getInt("prodCode"));
                reviewVO.setReviewCode(rs.getInt("reviewCode"));
                reviewVO.setProdImage(rs.getString("prodImage"));
                reviewVO.setReviewContent(rs.getString("reviewContent"));
                reviewVO.setId(rs.getString("id"));
                reviewVO.setReviewStar(rs.getInt("reviewStar"));
                reviewVO.setReviewDate(rs.getDate("reviewDate"));
                reviewVO.setProdName(rs.getString("prodName"));
                reviewVO.setOrderCode(rs.getInt("orderCode"));
                reviewList.add(reviewVO);
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("selectReviewList 오류");
            e.printStackTrace();
        }
        return reviewList;
    }



    public void updaReview(ReviewVO reviewVO) {
        try {
            conn = dataFactory.getConnection();
            String query = "UPDATE reviewtbl SET reviewContent = ?, reviewStar = ? WHERE reviewCode = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, reviewVO.getReviewContent());
            pstmt.setInt(2, reviewVO.getReviewStar());
            pstmt.setInt(3, reviewVO.getReviewCode());
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("updateReview 오류");
            e.printStackTrace();
        }
    }
    
    public List<ProductVO> selectProd(List<OrderDVO> detailList) {
    	List<ProductVO> prodList=new ArrayList<>();
    	
    	try {
			conn=dataFactory.getConnection();
			String query="select prodImage from producttbl where prodcode=?";
			pstmt=conn.prepareStatement(query);
			for(int i=0; i<detailList.size(); i++) {
				pstmt.setInt(1, detailList.get(i).getProdCode());
				System.out.println("상품 코드: "+detailList.get(i).getProdCode());
				ResultSet rs=pstmt.executeQuery();
				rs.next();
				ProductVO prod=new ProductVO();
				prod.setProdImage(rs.getString("prodimage"));
				prodList.add(prod);
				rs.close();
			}
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("주문한 상품 이미지 조회 중 오류 발생");
			e.printStackTrace();
		}
    	return prodList;
    }
    
    // 주문한 상품 정보 리뷰 테이브에 넣기
    public void insertReviewProdInfo(Map<String, Object> orderMap, String id) {
    	List<OrderVO> orderList=(List<OrderVO>)orderMap.get("orderList");
    	List<OrderDVO> detailList=(List<OrderDVO>)orderMap.get("detailList");
    	List<ProductVO> prodList=selectProd(detailList);
        try {
            conn = dataFactory.getConnection();
            String query = "INSERT INTO reviewtbl (prodCode, reviewCode, prodImage, id, reviewDate, prodName, orderCode) "
            			+ "VALUES(?,REVIEW_SEQ.NEXTVAL,?,?,sysdate,?,?)";
            pstmt = conn.prepareStatement(query);
            for(int i=0; i<detailList.size(); i++) {
            	pstmt.setInt(1, detailList.get(i).getProdCode());
            	pstmt.setString(2, prodList.get(i).getProdImage());
            	pstmt.setString(3, id);
            	pstmt.setString(4, detailList.get(i).getProdName());
            	pstmt.setInt(5, orderList.get(i).getOrderCode());
            	pstmt.executeUpdate();
            }
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("insertReview 오류");
            e.printStackTrace();
        }
    }

    // 상품 리뷰 등록
    public void updateReview(ReviewVO review) {
    	try {
			conn=dataFactory.getConnection();
			String query="update reviewtbl set reviewContent=?, reviewStar=? where id=? and orderCode=? and prodCode=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, review.getReviewContent());
			pstmt.setInt(2, review.getReviewStar());
			pstmt.setString(3, review.getId());
			pstmt.setInt(4, review.getOrderCode());
			pstmt.setInt(5, review.getProdCode());
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("리뷰 등록 중 오류 발생");
			e.printStackTrace();
		}
    }
}
