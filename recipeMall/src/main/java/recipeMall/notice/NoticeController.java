package recipeMall.notice;

import java.io.IOException;
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

@WebServlet("/notice/*")
public class NoticeController extends HttpServlet {
	NoticeVO noticeVO;
	NoticeService noticeService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		noticeVO=new NoticeVO();
		noticeService=new NoticeService();
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
		String action=request.getPathInfo();
		String nextPage="";
		
		try {
			//List<NoticeVO> noticeList=new ArrayList<NoticeVO>();
			if(action==null || action.equals("/noticeList.do")) {
				// 페이지, 섹션 최초 요청시 null값
				String _section=request.getParameter("section");
				System.out.println("section: "+_section);
				String _pageNum=request.getParameter("pageNum");
				System.out.println("pageNum: "+_pageNum);
				// _section=null일 경우 1반환_초기값 1 세팅. 10->11로 페이지 넘어가면 2를 받음
				int section=Integer.parseInt((_section == null)?"1":_section);
				// _pageNum=null일 경우 1반환_초기값 1 세팅. 2페이지 누르면 2를 받음
				int pageNum=Integer.parseInt((_pageNum == null)?"1":_pageNum);
				
				Map<String, Integer> pagingMap=new HashMap<String, Integer>();
				// page set
				pagingMap.put("section", section);	// 1
				pagingMap.put("pageNum", pageNum);	// 1
				// articlesList, totArticles 받음
				Map noticeMap=noticeService.listNotices(pagingMap);
				
				noticeMap.put("section", section);
				noticeMap.put("pageNum", pageNum);
				
				request.setAttribute("noticeMap", noticeMap);
				nextPage="/views/mypage/noticeList.jsp";
				
			} else if(action.equals("/noticeView.do")) {
				int noticeNo=Integer.parseInt(request.getParameter("noticeNo"));
				noticeVO=noticeService.selectNoticeView(noticeNo);
				request.setAttribute("notice", noticeVO);
				nextPage="/views/mypage/noticeView.jsp";
			} 
			
		} catch (Exception e) {
			System.out.println("요청 처리 중 오류 발생");
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher=request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
	}

}
