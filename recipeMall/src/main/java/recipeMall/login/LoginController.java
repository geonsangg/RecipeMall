package recipeMall.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import recipeMall.user.UserDAO;
import recipeMall.user.UserVO;


@WebServlet("/loginProc/*")
public class LoginController extends HttpServlet {
	UserVO userVO;
	UserDAO dao;
	HttpSession session;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		ServletContext context=getServletContext();
		String ContextPath=context.getContextPath();
		String action=request.getPathInfo();
		PrintWriter out=response.getWriter();
		
		if(action.equals("/login")) {
			String id=request.getParameter("id");
			String pw=request.getParameter("pass");
			
			dao=new UserDAO();
			boolean result=dao.loginCheck(id,pw);
			if(result) {
				//out.print("true");
				userVO=new UserVO();
				userVO.setId(id);
				userVO.setPw(pw);
				
				session=request.getSession();
				session.setAttribute("isLogon", result); // or result
				session.setAttribute("log_id", id);
				System.out.println("세션 유지 시간 : "+session.getMaxInactiveInterval());
				response.sendRedirect(ContextPath+"/main/index.do");
			} else {
				response.sendRedirect(ContextPath+"/main/index.do");
			}
		} else if(action.equals("/kakao/oauth")) {
			
		} else if(action.equals("/naver/login")) {
			
		} else if(action.equals("/logout")) {
			session=request.getSession(false);
			session.removeAttribute("isLogon");
			session.removeAttribute("log_id");
			session.invalidate();
			response.sendRedirect(ContextPath+"/main/index.do");
		}
	}
}
