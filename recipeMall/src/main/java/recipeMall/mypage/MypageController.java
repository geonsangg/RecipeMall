package recipeMall.mypage;

import java.io.IOException;
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

@WebServlet("/mypage/*")
public class MypageController extends HttpServlet {
	MypageDAO dao;
	MypageService service;

	public void init() throws ServletException {
		dao = new MypageDAO();
		service = new MypageService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getPathInfo();
		System.out.println("요청 매핑이름 : " + action);

		try {
			if (action == null || action.equals("/listShippingAddr.do")) {
				String id = request.getParameter("id");
				List<ShippingAddrVO> shippingAddrList = dao.listShippingAddr(id);
				request.setAttribute("shippingAddrList", shippingAddrList);
				nextPage = "/views/mypage/listShippingAddr.jsp";
			} else if (action.equals("/addShippingForm.do")) {
				nextPage = "/views/mypage/addShippingAddr.jsp";
			} else if (action.equals("/addShipping.do")) {
				String shippingName = request.getParameter("shippingName");
				String shippingTitle = request.getParameter("shippingTitle");
				String id = request.getParameter("id");
				int shippingZonecode = Integer.parseInt(request.getParameter("shippingZonecode"));
				String shippingAddress = request.getParameter("shippingAddress");
				String shippingAddressSub = request.getParameter("shippingAddressSub");
				String shippingPhone = request.getParameter("shippingPhone");
				String shippingCellphone = request.getParameter("shippingCellphone");
				ShippingAddrVO shipVo = new ShippingAddrVO(shippingName, shippingTitle, id, shippingZonecode,
						shippingAddress, shippingAddressSub, shippingPhone, shippingCellphone);
				dao.addShipping(shipVo);
				nextPage = "/mypage/listShippingAddr.do";
			} else if (action.equals("/delShipping.do")) {
				String id = request.getParameter("id");
				String shippingName = request.getParameter("shippingName");
				dao.deleteShipping(shippingName, id);
				request.setAttribute("msg", "deleted");
				nextPage = "/mypage/listShippingAddr.do";
			} else if (action.equals("/myCart.do")) {
				String id = request.getParameter("id");
				List<CartVO> cartList = dao.cartSelect(id);
				request.setAttribute("cartList", cartList);
				nextPage = "/views/mypage/myCart.jsp";
			} else if (action.equals("/delCart.do")) {
				int prodCode = Integer.parseInt(request.getParameter("prodCode"));
				String id = request.getParameter("id");
				dao.deleteCart(prodCode, id);
				request.setAttribute("msg", "deleted");
				nextPage = "/mypage/myCart.do?id="+id;
			} else if (action.equals("/updateCart.do")) {
				int cartCount = Integer.parseInt(request.getParameter("cartCount"));
				String id = request.getParameter("id");
				String[] prodName = request.getParameterValues("prodName");
				if (prodName != null) {
					for (int i = 0; i < prodName.length; i++) {
						String currentProdName = prodName[i];
						dao.upadteCart(cartCount, id, currentProdName);
					}
					request.setAttribute("prodName", prodName);
					nextPage = "/order/payment.do";
				} else{
		               nextPage = "/mypage/myCart.do";
	            }
			} else if (action.equals("/selectOrder.do")) {
				String id = request.getParameter("id");
				Map orderMap = service.selectServiceOrder(id);
				request.setAttribute("orderMap", orderMap);
				nextPage = "/views/mypage/orderList.jsp";
			} else if (action.equals("/orderList.do")) {
				nextPage = "/views/mypage/orderList.jsp";
			} else if(action.equals("/myPost.do")) {
				String id=request.getParameter("id");
				String _section=request.getParameter("section");  //23-05-25
				String _pageNum=request.getParameter("pageNum"); // 페이지 번호 
				int section=Integer.parseInt((_section == null)?"1":_section);
				int pageNum=Integer.parseInt((_pageNum == null)?"1":_pageNum);
				Map<String, Integer> pagingMap=new HashMap<String, Integer>();
				pagingMap.put("section", section);
				pagingMap.put("pageNum", pageNum);
				Map posts=service.selectUserPosts(pagingMap, id);
				posts.put("section", section);
				posts.put("pageNum", pageNum);
				request.setAttribute("posts", posts);
				nextPage="/views/mypage/myPost.jsp";
				
			} else if(action.equals("/myReply.do")) {
				String id=request.getParameter("id");
				
				String _section=request.getParameter("section");  //23-05-25
				String _pageNum=request.getParameter("pageNum"); // 페이지 번호 
				int section=Integer.parseInt((_section == null)?"1":_section);
				int pageNum=Integer.parseInt((_pageNum == null)?"1":_pageNum);
				Map<String, Integer> pagingMap=new HashMap<String, Integer>();
				pagingMap.put("section", section);
				pagingMap.put("pageNum", pageNum);
				Map replyMap=service.selectUserReply(pagingMap, id);
				replyMap.put("section", section);
				replyMap.put("pageNum", pageNum);
				
				request.setAttribute("replyMap", replyMap);
				nextPage="/views/mypage/myReply.jsp";
				
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage); // 포워딩할 페이지
			dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println("컨트롤러 처리중에러");
			e.printStackTrace();
		}
	}

}