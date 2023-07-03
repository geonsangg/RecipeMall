package recipeMall.productFaq;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ProductFaqDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;

	public ProductFaqDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			System.out.println("DB 연결 오류");
			e.printStackTrace();
		}
	}

	// 상품 문의 조회 메서드
	public List<ProductFaqVO> selectFaqList(String id) {
		List<ProductFaqVO> faqList = new ArrayList<>();
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT prodFaqTitle, content, adminId, prodContent, hasComent, id, prodCode, prodName "
					+ "FROM productfaqtbl";
			//String query = "SELECT prodFaqTitle, content, adminId, prodContent, hasComent, id, prodCode, prodName "
			//		+ "FROM productfaqtbl where id = ?";
			pstmt = conn.prepareStatement(query);
			//pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductFaqVO productFaqVO = new ProductFaqVO();
				productFaqVO.setProdFaqTitle(rs.getString("prodFaqTitle"));
				productFaqVO.setContent(rs.getString("content"));
				productFaqVO.setAdminId(rs.getString("adminId"));
				productFaqVO.setProdContent(rs.getString("prodContent"));
				productFaqVO.setHasComent(rs.getInt("hasComent"));
				productFaqVO.setId(rs.getString("id"));
				productFaqVO.setProdCode(rs.getInt("prodCode"));
				productFaqVO.setProdName(rs.getString("prodName"));
				faqList.add(productFaqVO);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("상품정보 리뷰 조회 중 에러");
			e.printStackTrace();
		}
		return faqList;
	}
}
