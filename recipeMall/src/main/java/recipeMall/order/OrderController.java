package recipeMall.order;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import recipeMall.mypage.CartVO;

@WebServlet("/order/*")
public class OrderController extends HttpServlet {
	OrderService orderService;
	OrderVO orderVO;
	OrderDVO orderDVO;
	
	public void init(ServletConfig config) throws ServletException {
		orderService = new OrderService();
		orderVO = new OrderVO();
		orderDVO = new OrderDVO();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String nextPage = "";
		PrintWriter out;
		HttpSession session;
		String action = request.getPathInfo();// 요청명 가져오기
		System.out.println("요청이름: " + action);
		try {
			if(action == null || action.equals("")) {
				
			}else if(action.equals("/payment.do")) {
				String id = request.getParameter("id");
				String[] prodNames =request.getParameterValues("prodName");
				int Cnt = prodNames.length;
				int priceAll = 0;
				Map<String, Object> paymentMap = orderService.listPayment(id, prodNames);
				List<CartVO> cartList = (List<CartVO>) paymentMap.get("cartList");
				for(CartVO s : cartList) {
					int prodPrice = s.getProdPrice();
					int cartCount = s.getCartCount();
					priceAll += (prodPrice * cartCount);
				}
				int paymentAll = priceAll + 2500; 
				DecimalFormat formatter = new DecimalFormat("#,###");
				String formattedPriceAll = formatter.format(priceAll);
				String formattedPaymentAll = formatter.format(paymentAll);
				paymentMap.put("Cnt", Cnt);
				paymentMap.put("priceAll", formattedPriceAll);
				paymentMap.put("paymentAll", formattedPaymentAll);
				request.setAttribute("paymentMap", paymentMap);
				nextPage = "/views/shopping/payment.jsp";
			}else if(action.equals("/goPayment.do")) {
				String id = request.getParameter("id");
				String[] prodCode = request.getParameterValues("prodCode");
				String[] cartCount = request.getParameterValues("cartCount");
				String allProdPrice = request.getParameter("allProdPrice");
				String userAddr = request.getParameter("userAddr");
				String userAddr2 = request.getParameter("userAddr2");
				String deliveryMSG = request.getParameter("deliveryMSG");
				Map<String, Object> orderMap = new HashMap<String, Object>();
				orderMap.put("id", id);
				orderMap.put("prodCode", prodCode);
				orderMap.put("cartCount", cartCount);
				orderMap.put("allProdPrice", allProdPrice);
				orderMap.put("userAddr", userAddr);
				orderMap.put("userAddr2", userAddr2);
				orderMap.put("deliveryMSG", deliveryMSG);
				orderService.insertOrder(orderMap);
				Map<String, Object> orderMap2 = orderService.listOrder(id);
				request.setAttribute("orderMap", orderMap2);
				nextPage = "/views/shopping/payment_result.jsp";
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response); 
		}catch (Exception e) {
			System.out.println("요청 처리 중 에러");
			e.printStackTrace();
		}

	}
}
