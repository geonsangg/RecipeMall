package recipeMall.shopping;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/shopping/*")
public class ShoppingController extends HttpServlet {
	ShoppingService shoppingService;
	ProductVO productVO;
	ShoppingDAO dao;

	public void init(ServletConfig config) throws ServletException {
		shoppingService = new ShoppingService();
		productVO = new ProductVO();
		dao = new ShoppingDAO();
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
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String nextPage = "";
		PrintWriter out;
		HttpSession session;
		String action = request.getPathInfo();// 요청명 가져오기
		System.out.println("요청이름: " + action);
		try {
			List<ProductVO> shoppingList = new ArrayList<ProductVO>();
			if (action == null || action.equals("")) {

			} else if (action.equals("/shopping_index.do")) {
				Map<String, Integer> shoppingMap = new HashMap<String, Integer>();
				Map shoppingIndexMap = shoppingService.selectshopIndex();
				request.setAttribute("shoppingIndexMap", shoppingIndexMap);
				nextPage = "/views/shopping/shopping_index.jsp";
			} else if (action.equals("/matarial.do")) {
				String _section = request.getParameter("section");
				String _pageNum = request.getParameter("pageNum");
				String _prodKind = request.getParameter("prodKind");
				String _prodName = request.getParameter("prodName");
				String section = (_section == null) ? "1" : _section;
				String pageNum = (_pageNum == null) ? "1" : _pageNum;
				String prodKind = (_prodKind == null) ? "값이 없음" : _prodKind;
				String prodName = (_prodName == null) ? "값이 없음" : _prodName;
				Map<String, String> pagingMap = new HashMap<String, String>();
				pagingMap.put("section", section);
				pagingMap.put("pageNum", pageNum);
				pagingMap.put("prodKind", prodKind);
				pagingMap.put("prodName", prodName);
				Map matarialMap = shoppingService.listMatarial(pagingMap);
				matarialMap.put("section", section);
				matarialMap.put("pageNum", pageNum);
				request.setAttribute("matarialMap", matarialMap);
				nextPage = "/views/shopping/matarial.jsp";
			}

			else if (action.equals("/shopping_best.do")) {
				Map bestMap = shoppingService.bestReview();
				request.setAttribute("bestMap", bestMap);
				nextPage = "/views/shopping/shopping_best.jsp";
			} else if (action.equals("/season.do")) {
				List<ProductVO> productList = new ArrayList<>();
				dao.selectSeason(productList);
				request.setAttribute("productList", productList);
				nextPage = "/views/shopping/season.jsp";
			} else if (action.equals("/product_info.do")) {
				int tempCode = Integer.parseInt(request.getParameter("prodCode"));
				Map<String, Integer> infoMap = new HashMap<String, Integer>();
				infoMap.put("tempCode", tempCode);
				Map productInfoMap = shoppingService.selectProdInfo(infoMap);
				request.setAttribute("productInfoMap", productInfoMap);
				nextPage = "/views/shopping/product_info.jsp";
			} else if (action.equals("/goCart.do")) {
				String productName = request.getParameter("prodName");
				String prodImage = request.getParameter("prodImage");
				int cartCount = Integer.parseInt(request.getParameter("cartCount"));
				int prodPrice = Integer.parseInt(request.getParameter("prodPrice"));
				int prodCode = Integer.parseInt(request.getParameter("prodCode"));
				String id = request.getParameter("id");
				CartVO cartVO = new CartVO();
				cartVO.setProdName(productName);
				cartVO.setProdImage(prodImage);
				cartVO.setProdPrice(prodPrice);
				cartVO.setId(id);
				cartVO.setCartCount(cartCount);
				cartVO.setProdCode(prodCode);
				dao.insertCart(cartVO);
				nextPage = "/mypage/myCart.do";
			} else if (action.equals("/buyProduct.do")) {
				String productName = request.getParameter("prodName");
				String prodImage = request.getParameter("prodImage");
				int cartCount = Integer.parseInt(request.getParameter("cartCount"));
				int prodPrice = Integer.parseInt(request.getParameter("prodPrice"));
				String id = request.getParameter("id");
				int prodCode = Integer.parseInt(request.getParameter("prodCode"));
				CartVO cartVO = new CartVO();
				cartVO.setProdName(productName);
				cartVO.setProdImage(prodImage);
				cartVO.setProdPrice(prodPrice);
				cartVO.setId(id);
				cartVO.setCartCount(cartCount);
				cartVO.setProdCode(prodCode);
				dao.insertCart(cartVO);
				nextPage = "/order/payment.do";
			} else if (action.equals("/addFaq.do")) {
				String prodFaqTitle = request.getParameter("prodFaqTitle");
				String content = request.getParameter("content");
				String id = request.getParameter("id");
				String prodName = request.getParameter("prodName");
				int prodCode = Integer.parseInt(request.getParameter("prodCode"));
				ProductFaqVO prodFaqVO = new ProductFaqVO();
				prodFaqVO.setProdFaqTitle(prodFaqTitle);
				prodFaqVO.setProdCode(prodCode);
				prodFaqVO.setId(id);
				prodFaqVO.setContent(content);
				prodFaqVO.setProdName(prodName);
				dao.insertFaq(prodFaqVO);
				nextPage = "/shopping/shopping_index.do";
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println("요청 처리 중 에러");
			e.printStackTrace();
		}
	}
}
