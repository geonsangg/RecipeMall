package recipeMall.main;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import recipeMall.main.IndexDAO;
import recipeMall.main.IndexService;
import recipeMall.recipe.RecipeVO;

@WebServlet("/main/*")
public class MainController extends HttpServlet {
	IndexService service;
	IndexDAO dao;
	RecipeVO reicpeVo;

	@Override
	public void init() throws ServletException {
		dao = new IndexDAO();
		service = new IndexService();
		reicpeVo = new RecipeVO();
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
		String nextPage=null;
		ServletContext context = getServletContext();
		String contextPath = context.getContextPath();
		String action = request.getPathInfo();

		try {
			if (action == null || action.equals("/index.do")) {
				Map indexMap = service.selectIndex();
				request.setAttribute("indexMap", indexMap);

				nextPage = "/views/index.jsp";
			} else if (action.equals("/mypage.do")) {
				nextPage="/views/mypage/mypage.jsp";
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage); // 포워딩할 페이지
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			System.out.println("컨트롤러 처리중 에러");
			e.printStackTrace();
		}

	}

}
