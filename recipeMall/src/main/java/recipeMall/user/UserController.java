package recipeMall.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/user/*")
public class UserController extends HttpServlet {
	UserVO userVO;
	UserDAO dao;
	HttpSession session;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		userVO=new UserVO();
		dao=new UserDAO();
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
		PrintWriter out=response.getWriter();
		String action=request.getPathInfo();
		String nextPage="";
		
		
		if(action.equals("/joinForm.do")) {
			nextPage="/views/join.jsp";
		} else if(action.equals("/idExisted.do")) {
			String id=request.getParameter("id");
			boolean result=dao.idExisted(id);
			if(result) {
				out.print("true");				
			} else {
				out.print("false");
			}
			return;
		} else if(action.equals("/addUser.do")) {
			String name=request.getParameter("user_name");
			String id=request.getParameter("user_id");
			String pw=request.getParameter("user_pwd_check");
			String nikName=request.getParameter("user_nickname");
			String phone=request.getParameter("user_phone");
			String userAddr=request.getParameter("user_addr1");
			String userAddr2=request.getParameter("user_addr2");
			String rcmd=request.getParameter("user_recommend");
			
			if(rcmd==null || rcmd.equals("")) {	// 추천인 없는 경우
				userVO=new UserVO(id, pw, name, nikName, phone, userAddr, userAddr2);
				System.out.println("회원가입 id="+userVO.getId());
				dao.addUser(userVO);
			} else {	// 추천인 있는 경우
				userVO=new UserVO(id, pw, name, nikName, phone, rcmd, userAddr, userAddr2);
				dao.addUser2(userVO);
			}
			out.print("<script>");
			out.print("alert('회원가입이 완료되었습니다.');");
			out.print("</script>");
			nextPage="/main/index.do";
		
		} else if(action.equals("/userInfo.do")) {
			String id=request.getParameter("id");
			userVO=dao.userInfo(id);
			request.setAttribute("user", userVO);
			nextPage="/views/mypage/mypage_user_info.jsp";
			
		} else if(action.equals("/updateUser.do")) {
			String id=request.getParameter("id");
			String nikName=request.getParameter("user_nikname");
			String pw=request.getParameter("pw");
			String phone=request.getParameter("user_phone");
			System.out.println(nikName);
			System.out.println(pw);
			
			userVO.setId(id);
			userVO.setNikName(nikName);
			userVO.setPw(pw);
			userVO.setPhone(phone);
			dao.updateUser(userVO);
			request.setAttribute("user", userVO);
			nextPage="/user/userInfo.do?id="+id;
			
		} else if(action.equals("/deleteUser.do")) {
			session=request.getSession(false);
			String id=request.getParameter("id");
			dao.deleteUser(id);
			session.removeAttribute("isLogon");
			session.removeAttribute("log_id");
			session.invalidate();
			nextPage="/views/index.jsp";
		} else if(action.equals("/find.do")) {
			String kind=request.getParameter("kind");
			if(kind.equals("id")) {
				request.setAttribute("kind", "id");
				nextPage="/views/user/find.jsp";
			} else if(kind.equals("pw")) {
				request.setAttribute("kind", "pw");
				nextPage="/views/user/find.jsp";
			} else if(kind.equals("succesId")) {
				request.setAttribute("kind", "succesId");
				nextPage="/views/user/find.jsp";
			} else if(kind.equals("succesPw")) {
				request.setAttribute("kind", "succesPw");
				nextPage="/views/user/find.jsp";
			}
			else if(kind.equals("updatePw")) {
				request.setAttribute("kind", "updatePw");
				nextPage="/views/user/find.jsp";
			}
		} else if(action.equals("/findId.do")) {
			String name = request.getParameter("name");
			String phoneNumber = request.getParameter("phone");
			String phone = changePhoneNumber(phoneNumber);
			String id = dao.findId(name, phone);
			request.setAttribute("id", id);
			nextPage = "/user/find.do?kind=succesId";
		} else if(action.equals("/findPw.do")) {
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String phoneNumber = request.getParameter("phone");
			String phone = changePhoneNumber(phoneNumber);
			String check = dao.findPw(id, name, phone);
			request.setAttribute("id", id);
			request.setAttribute("check", check);
			nextPage = "/user/find.do?kind=succesPw";
		} else if(action.equals("/updatePw.do")) {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			dao.updatePw(id, pw);
			nextPage = "/user/find.do?kind=updatePw"; 
		}
		
		RequestDispatcher dispatcher=request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
	}
	
	//핸드폰 번호 - 넣기
	public static String changePhoneNumber(String phoneNumber) {
        String regex = "(\\d{3})(\\d{3,4})(\\d{4})";
        String replacement = "$1-$2-$3";
        return phoneNumber.replaceAll(regex, replacement);
    }

}
