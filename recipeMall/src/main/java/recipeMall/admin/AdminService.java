package recipeMall.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import recipeMall.board.FreeBoardVO;
import recipeMall.productFaq.ProductFaqVO;
import recipeMall.service.InqueryVO;
import recipeMall.service.ServiceDAO;
import recipeMall.shopping.ProductVO;

public class AdminService {
	ServiceDAO serviceDAO;
	AdminDAO adminDAO;
	
	public AdminService() {
		serviceDAO=new ServiceDAO();
		adminDAO=new AdminDAO();
	}
	
	public Map mainList() {
		Map indexMap=new HashMap<>();
		// 리뷰 리스트
		
		// 게시판 리스트
		List<FreeBoardVO> freeList=adminDAO.recentFree();
		// 문의 리스트
		List<InqueryVO> inqList=serviceDAO.recentInqList();
		indexMap.put("freeList", freeList);
		indexMap.put("inqList", inqList);
		return indexMap;
	}
	
	public Map<String, Object> selectAllProd(Map<String, Integer> pagingMap) {
		Map<String, Object> prodMap=new HashMap<>();
		List<ProductVO> prodList=adminDAO.selectAllProd(pagingMap);
		int totProducts=adminDAO.selectToProducts();
		prodMap.put("prodList", prodList);
		prodMap.put("totProducts", totProducts);
		return prodMap;
	}
	
	public Map<String, Object> listAllOrders(Map<String, Integer> pagingMap) {
		Map<String, Object> orderMap=new HashMap<>();
		List<AllOrderVO> orderList=adminDAO.listAllOrders(pagingMap);
		int totOrders=adminDAO.selectTotOrder();
		orderMap.put("orderList", orderList);
		orderMap.put("totOrders", totOrders);
		return orderMap;
	}
	
	public Map<String, Object> boardList(Map<String, Integer> pagingMap) {
		Map<String, Object> boardMap=new HashMap<>();
		List<FreeBoardVO> boardList=adminDAO.selectAllBoardList(pagingMap);
		int totArticles=adminDAO.selectToArticles();
		boardMap.put("boardList",boardList);
		boardMap.put("totArticles", totArticles);
		return boardMap;
	}
	
	public Map<String, Object> prodInqList(Map<String, Integer> pagingMap) {
		Map<String, Object> prodInqdMap=new HashMap<>();
		List<ProductFaqVO> prodInqList=adminDAO.prodInqList(pagingMap);
		int totProdInq=adminDAO.selectTotProdInq();
		prodInqdMap.put("prodInqList",prodInqList);
		prodInqdMap.put("totProdInq", totProdInq);
		return prodInqdMap;
	}
	
	public ProductFaqVO selectProdFaq(int prodFaqNo) {
		ProductFaqVO prodFaq=adminDAO.selectProdFaq(prodFaqNo);
		return prodFaq;
	}
	
	public void answerProdFaq(int prodFaqNo, String adminId, String prodContent) {
		adminDAO.answerProdFaq(prodFaqNo, adminId, prodContent);
	}
}
