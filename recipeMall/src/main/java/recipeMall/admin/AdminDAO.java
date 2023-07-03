package recipeMall.admin;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import recipeMall.board.FreeBoardVO;
import recipeMall.order.OrderDVO;
import recipeMall.order.OrderVO;
import recipeMall.productFaq.ProductFaqVO;
import recipeMall.shopping.ProductVO;
import recipeMall.user.UserVO;

public class AdminDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;

	public AdminDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			System.out.println("DB연결 오류");
		}
	}

	// 로그인 시 아이디,비밀번호 일치 확인
	public boolean loginCheck(String id, String pw) {
		boolean result = false;
		try {
			conn = dataFactory.getConnection();
			// 받은 id,pw가 데이터와 일치할 경우 true, 불일치할 경우 false를 result 이름으로 반환
			String query = "select decode(count(*), 1, 'true','false') as result from admintbl where adminId=? and adminPw=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			result = Boolean.parseBoolean(rs.getString("result"));

			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("관리자 로그인 아이디/비밀번호 확인 중 오류");
			e.printStackTrace();
		}
		return result;
	}
	
	// index 최신글
	public List<FreeBoardVO> recentFree() {
		List<FreeBoardVO> free = new ArrayList<>();
		try {
			conn = dataFactory.getConnection();
			String query = "select boardNo, boardName, id, writeDate from (select boardNo, boardName, id, writeDate from freeboardtbl order by writeDate desc) where ROWNUM <= 5";
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				FreeBoardVO freeVO = new FreeBoardVO();
				freeVO.setBoardNo(rs.getInt("boardNo"));
				freeVO.setBoardName(rs.getString("boardName"));
				freeVO.setId(rs.getString("id"));
				freeVO.setWriteDate(rs.getDate("writeDate"));
				free.add(freeVO);
			}
		} catch (Exception e) {
			System.out.println("자유게시판 최신 글 조회 중 오류 발생");
			e.printStackTrace();
		}
		return free;
	}
	
	// 상품 목록
	public List<ProductVO> selectAllProd(Map<String, Integer> pagingMap) {
		List<ProductVO> prodList = new ArrayList<>();
		int section = pagingMap.get("section");
		int pageNum = pagingMap.get("pageNum");
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT * FROM (SELECT ROWNUM AS recNum, prodcode, prodname, prodkind, prodprice, prodrest "
					+ "FROM producttbl order by proddate desc) "
					+ "WHERE recNum BETWEEN (?-1)*100+(?-1)*10+1 AND (?-1)*100+?*10";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, section);
			pstmt.setInt(2, pageNum);
			pstmt.setInt(3, section);
			pstmt.setInt(4, pageNum);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductVO prod = new ProductVO();
				prod.setProdCode(rs.getInt("prodCode"));
				prod.setProdName(rs.getString("prodname"));
				prod.setProdKind(rs.getInt("prodKind"));
				prod.setProdPrice(rs.getInt("prodprice"));
				prod.setProdRest(rs.getInt("prodrest"));
				prodList.add(prod);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("상품 목록 조회 중 오류 발생");
			e.printStackTrace();
		}
		return prodList;
	}
	
	// 상품 전체 수
	public int selectToProducts() {
		int totCount = 0;
		try {
			conn = dataFactory.getConnection();
			String query = "select count(*) from producttbl";
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) { // 자료가 있을 경우
				totCount = rs.getInt(1); // 첫 번째 컬럼 값
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("상품 전체 개수 조회 중 오류 발생");
			e.printStackTrace();
		}
		return totCount;
	}

	// 전체 글 목록 수
	public int selectToArticles() {
		int totCount = 0;
		try {
			conn = dataFactory.getConnection();
			String query = "select count(*) from freeboardtbl";
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				totCount = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("전체 글 목록 수 처리중 에러");
			e.printStackTrace();
		}
		return totCount;
	}

	// 페이징 처리 자유게시판 리스트
	public List<FreeBoardVO> selectAllBoardList(Map<String, Integer> boardMap) {
		List<FreeBoardVO> boardList = new ArrayList<FreeBoardVO>();
		int section = boardMap.get("section");
		int pageNum = boardMap.get("pageNum");
		try {
			conn = dataFactory.getConnection();
			/*
			 * String query =
			 * "select * FROM (SELECT ROWNUM, boardNo, boardName, boardContent,writeDate,id,cntView FROM freeboardtbl order by writeDate desc)"
			 * + " WHERE rownum BETWEEN (?-1)*100+(?-1)*5+1 AND (?-1)*100+?*5";
			 */

			String query = "select * from (select ROWNUM AS recNum, boardNo, boardName, writeDate, id, cntView from (SELECT boardNo, boardName, boardContent,writeDate,id,cntView FROM freeboardtbl order by writeDate desc)) where recNum BETWEEN (?-1)*100+(?-1)*10+1 AND (?-1)*100+?*10";

			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, section);
			pstmt.setInt(2, pageNum);
			pstmt.setInt(3, section);
			pstmt.setInt(4, pageNum);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				FreeBoardVO boardVO = new FreeBoardVO();
				// int recNum=rs.getInt("recNUM"); // 행 번호 가져옴
				int boardNo = rs.getInt("boardNo");
				String boardName = rs.getString("boardName");
				Date writeDate = rs.getDate("writeDate");
				String id = rs.getString("id");
				int cntView = rs.getInt("cntView");
				// boardVO.setRownum(recNum);
				boardVO.setBoardNo(boardNo);
				boardVO.setBoardName(boardName);
				boardVO.setWriteDate(writeDate);
				boardVO.setId(id);
				boardVO.setCntView(cntView);
				boardList.add(boardVO);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("전체 목록 조회중 에러");
		}
		return boardList;
	}
	
	// 주문 목록
	public List<AllOrderVO> listAllOrders(Map<String, Integer> pagingMap) {
		List<AllOrderVO> orderList=new ArrayList<>();
		int section = pagingMap.get("section");
		int pageNum = pagingMap.get("pageNum");
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT * FROM (SELECT ROWNUM AS recNum, o.orderdate, u.name, o.ordercode, pd.prodname, od.orderamount, o.allorderprice, o.orderstatus, o.paymentstatus "
					+ "FROM usertbl u, ordertbl o, orderdtbl od, producttbl pd where u.id=o.id and o.ordercode=od.ordercode and od.prodcode=pd.prodcode order by o.orderdate desc) "
					+ "WHERE recNum BETWEEN (?-1)*100+(?-1)*10+1 AND (?-1)*100+?*10";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, section);
			pstmt.setInt(2, pageNum);
			pstmt.setInt(3, section);
			pstmt.setInt(4, pageNum);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				AllOrderVO order=new AllOrderVO();
				order.setOrderDate(rs.getDate("orderdate"));
				order.setName(rs.getString("name"));
				order.setOrderCode(rs.getInt("ordercode"));
				order.setProdName(rs.getString("prodname"));
				order.setOrderAmount(rs.getInt("orderamount"));
				order.setAllOrderPrice(rs.getInt("allorderprice"));
				order.setOrderStatus(rs.getInt("orderstatus"));
				order.setPaymentStatus(rs.getInt("paymentstatus"));
				orderList.add(order);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("주문 목록 조회 중 오류 발생");
			e.printStackTrace();
		}
		return orderList;
	}
	
	// 주문 전체 수
	public int selectTotOrder() {
		int totCount = 0;
		try {
			conn = dataFactory.getConnection();
			String query = "select count(*) from ordertbl";
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) { // 자료가 있을 경우
				totCount = rs.getInt(1); // 첫 번째 컬럼 값
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("주문 전체 개수 조회 중 오류 발생");
			e.printStackTrace();
		}
		return totCount;
	}
	
	// 상품 목록
	public List<ProductFaqVO> prodInqList(Map<String, Integer> pagingMap) {
		List<ProductFaqVO> prodInqList = new ArrayList<>();
		int section = pagingMap.get("section");
		int pageNum = pagingMap.get("pageNum");
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT * FROM (SELECT ROWNUM AS recNum, prodfaqno, id, prodfaqtitle, prodname, hascoment, prodfaqdate "
					+ "FROM productfaqtbl order by prodfaqdate desc) "
					+ "WHERE recNum BETWEEN (?-1)*100+(?-1)*10+1 AND (?-1)*100+?*10";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, section);
			pstmt.setInt(2, pageNum);
			pstmt.setInt(3, section);
			pstmt.setInt(4, pageNum);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductFaqVO prodFaq = new ProductFaqVO();
				
				prodFaq.setProdFaqNo(rs.getInt("prodfaqno"));
				prodFaq.setId(rs.getString("id"));
				prodFaq.setProdFaqTitle(rs.getString("prodfaqtitle"));
				prodFaq.setProdName(rs.getString("prodname"));
				prodFaq.setHasComent(rs.getInt("hascoment"));
				prodFaq.setProdFaqDate(rs.getDate("prodfaqdate"));
				prodInqList.add(prodFaq);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("상품 문의 목록 조회 중 오류 발생");
			e.printStackTrace();
		}
		return prodInqList;
	}
	
	// 상품 전체 수
	public int selectTotProdInq() {
		int totCount = 0;
		try {
			conn = dataFactory.getConnection();
			String query = "select count(*) from productfaqtbl";
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) { // 자료가 있을 경우
				totCount = rs.getInt(1); // 첫 번째 컬럼 값
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("상품 문의 전체 개수 조회 중 오류 발생");
			e.printStackTrace();
		}
		return totCount;
	}
	
	// 상품 문의 상세 보기
	public ProductFaqVO selectProdFaq(int prodFaqNo) {
		ProductFaqVO prodFaq=new ProductFaqVO();
		try {
			conn=dataFactory.getConnection();
			String query="select * from productfaqtbl where prodfaqno=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, prodFaqNo);
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			
			prodFaq.setProdFaqNo(rs.getInt("prodfaqno"));
			prodFaq.setProdFaqTitle(rs.getString("prodfaqtitle"));
			prodFaq.setProdName(rs.getString("prodname"));
			prodFaq.setProdFaqDate(rs.getDate("prodfaqdate"));
			prodFaq.setId(rs.getString("id"));
			prodFaq.setContent(rs.getString("content"));
			prodFaq.setHasComent(rs.getInt("hasComent"));
			prodFaq.setProdContent(rs.getString("prodcontent"));
		} catch (Exception e) {
			System.out.println("선택한 상품 문의 조회 중 오류 발생");
			e.printStackTrace();
		}
		return prodFaq;
	}
	
	public void answerProdFaq(int prodFaqNo, String adminId, String prodContent) {
		try {
			conn=dataFactory.getConnection();
			String query="update productfaqtbl set adminId=?, prodContent=?, hasComent=1 where prodFaqNo=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, adminId);
			pstmt.setString(2, prodContent);
			pstmt.setInt(3, prodFaqNo);
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("상품 문의 답변 등록 중 오류 발생");
			e.printStackTrace();
		}
	}
}
