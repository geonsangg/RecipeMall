package recipeMall.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import recipeMall.mypage.CartVO;
import recipeMall.review.ReviewDAO;
import recipeMall.user.UserVO;

public class OrderService {
	OrderDAO orderDAO;
	ReviewDAO reviewDAO;
	
	public OrderService() {
		orderDAO = new OrderDAO();
		reviewDAO = new ReviewDAO();
	}
	
	public Map<String, Object> listPayment(String id, String[] prodNames) {
		Map<String, Object> paymentMap = new HashMap<>();
		List<UserVO> userList = orderDAO.selectUserInfo(id);
		List<CartVO> cartList =  orderDAO.selectCartInfos(id, prodNames);
		paymentMap.put("userList", userList);
		paymentMap.put("cartList", cartList);
		return paymentMap;
	}
	
	public Map<String, Object> listOrder(String id) {
		Map<String, Object> orderMap = new HashMap();
		List<OrderVO> orderList = orderDAO.selectOrder(id);
		List<OrderDVO> detailList = new ArrayList<OrderDVO>();
		if (!orderList.isEmpty()) {
		    OrderVO order = orderList.get(0);
		    int orderCode = order.getOrderCode();
		    detailList = orderDAO.selectDetail(orderCode);
		}
		orderMap.put("orderList", orderList);
		orderMap.put("detailList", detailList);
		if(detailList.size() != 0) {
			System.out.println("주문 상세 정보 있음");			
		}
		reviewDAO.insertReviewProdInfo(orderMap, id);
		orderDAO.deleteCart(id);
		return orderMap;
	}
	
	public void insertOrder(Map<String, Object> orderMap) {
		orderDAO.insertOrder(orderMap);
		orderDAO.insertOrderD(orderMap);
		orderDAO.updateProd(orderMap);
	}
}
