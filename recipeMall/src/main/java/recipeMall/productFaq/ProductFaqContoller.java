package recipeMall.productFaq;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/mypage/productFaq/*")
public class ProductFaqContoller extends HttpServlet {
	ProductFaqVO faqVO;
	ProductFaqDAO dao;
	public void init(ServletConfig config) throws ServletException {
		faqVO=new ProductFaqVO();
		dao=new ProductFaqDAO();
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
		HttpSession session = request.getSession();
        String action = request.getPathInfo();
		System.out.println("요청이름: " + action);
		try {
			if (action == null || action.equals("/productfaq.do")) {
				String id=request.getParameter("id");
                List<ProductFaqVO> productFaqList = dao.selectFaqList(id);
                request.setAttribute("productFaqList", productFaqList);
                nextPage = "/views/mypage/productFaq.jsp";
			}
			
		} catch (Exception e) {
			System.out.println("productFaqController 에러 : " + e.getMessage());
			e.printStackTrace();
		}
        RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
        dispatcher.forward(request, response);
	} 
		
}

