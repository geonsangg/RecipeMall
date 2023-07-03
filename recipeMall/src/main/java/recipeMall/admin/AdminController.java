package recipeMall.admin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.net.aso.ad;
import oracle.net.aso.n;
import recipeMall.board.FreeBoardService;
import recipeMall.notice.NoticeService;
import recipeMall.notice.NoticeVO;
import recipeMall.productFaq.ProductFaqVO;
import recipeMall.recipe.RecipeService;
import recipeMall.service.CenterService;
import recipeMall.service.FaqVO;
import recipeMall.service.InqueryVO;
import recipeMall.shopping.ProductVO;
import recipeMall.user.UserDAO;
import recipeMall.user.UserVO;

@WebServlet("/admin/*")
public class AdminController extends HttpServlet {
	NoticeVO noticeVO;
	InqueryVO inqVO;
	AdminVO adminVO;
	FaqVO faqVO;
	AdminDAO adminDAO;
	NoticeService noticeService;
	CenterService csService;
	AdminService adminService;
	FreeBoardService freeService;
	RecipeService recipeService;
	HttpSession session;
	
	private static String PROD_IMG_REPO="C:\\dev\\jspWebProject\\recipeMall\\src\\main\\webapp\\views\\shopping\\productImages";

	@Override
	public void init() throws ServletException {
		noticeService=new NoticeService();
		noticeVO=new NoticeVO();
		csService=new CenterService();
		adminService=new AdminService();
		freeService=new FreeBoardService();
		recipeService=new RecipeService();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		ServletContext context=getServletContext();
		String contextPath=context.getContextPath();
		String action=request.getPathInfo();
		String nextPage="";
		
		adminVO=new AdminVO();
		adminDAO=new AdminDAO();
		
		if(action.equals("/") || action.equals("/admLoginForm.do")) {	// 로그인
			nextPage="/views/admin/adminLogin.jsp";
		} else if(action.equals("/login.do")) {
			String id=request.getParameter("adminId");
			String pw=request.getParameter("adminPw");
			
			boolean result=adminDAO.loginCheck(id, pw);
			if(result) {
				session=request.getSession();
				session.setAttribute("isAdmLogon", result);
				session.setAttribute("log_adminId", id);
				System.out.println("세션 유지 시간 : "+session.getMaxInactiveInterval());
				nextPage="/admin/main.do";
			} else {
				response.sendRedirect("/admLoginForm.do");
			}
		} else if(action.equals("/logout.do")) {	// 로그아웃
			session.removeAttribute("isAdmLogon");
			session.removeAttribute("log_adminId");
			session.invalidate();
			nextPage="/admin/admLoginForm.do";
		} else if(action.equals("/main.do")) {
			Map<String, Object> indexMap=new HashMap<>();
			indexMap=adminService.mainList();
			
			request.setAttribute("indexMap", indexMap);
			nextPage="/views/admin/main.jsp";
		} else if(action.equals("/userList.do")) {		// 회원목록
			UserDAO userDAO=new UserDAO();
			List<UserVO> userList=userDAO.selectAllUsers();
			request.setAttribute("userList", userList);
			nextPage="/views/admin/user.jsp";
			
		} else if(action.equals("/product.do")) {	// 상품 목록
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
			Map productMap=adminService.selectAllProd(pagingMap);
			
			productMap.put("section", section);
			productMap.put("pageNum", pageNum);
			
			request.setAttribute("productMap", productMap);
			nextPage="/views/admin/product.jsp";
			
		} else if(action.equals("/productForm.do")) {	// 상품 등록 페이지
			nextPage="/views/admin/productForm.jsp";
			
		} else if(action.equals("/addProduct.do")) {	// DB에 상품 등록
			
		} else if(action.equals("/modProduct.do")) {	// 상품 수정
			
		} else if(action.equals("/delProduct.do")) {	// 상품 삭제
			int prodCode=Integer.parseInt(request.getParameter("prodCode"));
			
		} else if(action.equals("/orderList.do")) {	// DB에 상품 등록
			String _section=request.getParameter("section");
			String _pageNum=request.getParameter("pageNum");
			int section=Integer.parseInt((_section == null)?"1":_section);
			int pageNum=Integer.parseInt((_pageNum == null)?"1":_pageNum);
			
			Map<String, Integer> pagingMap=new HashMap<String, Integer>();
			pagingMap.put("section", section);	// 1
			pagingMap.put("pageNum", pageNum);	// 1
			// articlesList, totArticles 받음
			Map orderMap=adminService.listAllOrders(pagingMap);
			
			orderMap.put("section", section);
			orderMap.put("pageNum", pageNum);
			
			request.setAttribute("orderMap", orderMap);
			nextPage="/views/admin/order.jsp";
		} else if(action.equals("/noticeList.do")) {	// 공지사항 목록
			// 페이지, 섹션 최초 요청시 null값
			String _section=request.getParameter("section");
			String _pageNum=request.getParameter("pageNum");
			int section=Integer.parseInt((_section == null)?"1":_section);
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
			nextPage="/views/admin/notice.jsp";
		} else if(action.equals("/noticeForm.do")) {	// 공지사항 등록 페이지
			nextPage="/views/admin/noticeForm.jsp";
		} else if(action.equals("/addNotice.do")) {		// 입력한 공지사항 db insert
			String adminId=request.getParameter("adminId");
			String noticeTitle=request.getParameter("noticeTitle");
			String noticeContent=request.getParameter("noticeContent");
			
			NoticeVO noticeVO=new NoticeVO(adminId, noticeTitle, noticeContent);
			noticeService.addNotice(noticeVO);
			nextPage="/admin/noticeList.do";
		} else if(action.equals("/noticeView.do")) {	// 공지사항 상세보기
			int noticeNo=Integer.parseInt(request.getParameter("noticeNo"));
			noticeVO=noticeService.selectNoticeView(noticeNo);
			request.setAttribute("notice", noticeVO);
			nextPage="/views/admin/noticeView.jsp";
		} else if(action.equals("/modNotice.do")) {		// 공지사항 수정 페이지
			int noticeNo=Integer.parseInt(request.getParameter("noticeNo"));
			noticeVO=noticeService.selectNoticeView(noticeNo);
			request.setAttribute("notice", noticeVO);
			nextPage="/views/admin/modNoticeForm.jsp";
		} else if(action.equals("/updateNotice.do")) {		// 공지사항 수정한 데이터 db에 업데이트
			int noticeNo=Integer.parseInt(request.getParameter("noticeNo"));
			String adminId=request.getParameter("adminId");
			String noticeTitle=request.getParameter("noticeTitle");
			String noticeContent=request.getParameter("noticeContent");
			
			noticeVO.setNoticeNo(noticeNo);
			noticeVO.setAdminId(adminId);
			noticeVO.setNoticeTitle(noticeTitle);
			noticeVO.setNoticeContent(noticeContent);
			noticeService.updateNotice(noticeVO);
			nextPage="/admin/noticeView.do?noticeNo="+noticeNo;
			
		} else if(action.equals("/delNotice.do")) {		// 공지사항 삭제
			int noticeNo=Integer.parseInt(request.getParameter("noticeNo"));
			noticeService.deleteNotice(noticeNo);
			nextPage="/admin/noticeList.do";
		} else if(action.equals("/prodInqList.do")) {	// 상품 문의 리스트
			String _section=request.getParameter("section");
			String _pageNum=request.getParameter("pageNum");
			int section=Integer.parseInt((_section == null)?"1":_section);
			int pageNum=Integer.parseInt((_pageNum == null)?"1":_pageNum);
			
			Map<String, Integer> pagingMap=new HashMap<String, Integer>();
			// page set
			pagingMap.put("section", section);	// 1
			pagingMap.put("pageNum", pageNum);	// 1
			// articlesList, totArticles 받음
			Map prodInqMap=adminService.prodInqList(pagingMap);
			
			prodInqMap.put("section", section);
			prodInqMap.put("pageNum", pageNum);
			
			request.setAttribute("prodInqMap", prodInqMap);
			nextPage="/views/admin/prodInquery.jsp";
		} else if(action.equals("/prodInqView.do")) {	// 상품 문의 상세보기
			int prodFaqNo=Integer.parseInt(request.getParameter("prodFaqNo"));
			ProductFaqVO prodFaq=adminService.selectProdFaq(prodFaqNo);
			request.setAttribute("prodFaq", prodFaq);
			nextPage="/views/admin/prodInqView.jsp";
		} else if(action.equals("/answerProdFaq.do")) {
			int prodFaqNo=Integer.parseInt(request.getParameter("prodFaqNo"));
			String adminId=request.getParameter("adminId");
			String prodContent=request.getParameter("prodContent");
			adminService.answerProdFaq(prodFaqNo, adminId, prodContent);
			nextPage="/admin/prodInqView.do?prodFaqNo="+prodFaqNo;
			
		} else if(action.equals("/userInqList.do")) {	// 유저 문의 목록
			System.out.println("요청 매핑: "+action);
			// 페이지, 섹션 최초 요청시 null값
			String _section=request.getParameter("section");
			System.out.println("section: "+_section);
			String _pageNum=request.getParameter("pageNum");
			System.out.println("pageNum: "+_pageNum);
			// _section=null일 경우 1반환_초기값 1 세팅. 10->11로 페이지 넘어가면 2를 받음
			int section=Integer.parseInt((_section == null)?"1":_section);
			System.out.println("section: "+section);
			// _pageNum=null일 경우 1반환_초기값 1 세팅. 2페이지 누르면 2를 받음
			int pageNum=Integer.parseInt((_pageNum == null)?"1":_pageNum);
			System.out.println("pageNum: "+pageNum);
			
			Map<String, Integer> pagingMap=new HashMap<String, Integer>();
			// page set
			pagingMap.put("section", section);	// 1
			pagingMap.put("pageNum", pageNum);	// 1
			// articlesList, totArticles 받음
			Map inqueryMap=csService.inqAllList(pagingMap);
			
			inqueryMap.put("section", section);
			inqueryMap.put("pageNum", pageNum);
			
			request.setAttribute("inqueryMap", inqueryMap);
			nextPage="/views/admin/userInquery.jsp";
		} else if(action.equals("/userInqAnswer.do")) {		// 유저 문의 답변
			int inqNo=Integer.parseInt(request.getParameter("inqNo"));
			inqVO=csService.selectInquery(inqNo);
			request.setAttribute("inquery", inqVO);
			nextPage="/views/admin/inqueryView.jsp";
			
		} else if(action.equals("/inUserInqReply.do")) {	// 답변 등록
			int inqNo=Integer.parseInt(request.getParameter("inqNo"));
			String adminId=request.getParameter("adminId");
			String inqReply=request.getParameter("inq_reply");
			csService.inqReply(inqNo, adminId, inqReply);
			nextPage="/admin/userInqAnswer.do?inqNo="+request.getParameter("inqNo");
		} else if(action.equals("/faqList.do")) {	// 자주하는 질문 목록
			
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
			Map faqMap=csService.admFaqList(pagingMap);
			
			faqMap.put("section", section);
			faqMap.put("pageNum", pageNum);
			
			request.setAttribute("faqMap", faqMap);
			nextPage="/views/admin/faqList.jsp";
		} else if(action.equals("/faqView.do")) {	// 자주하는 질문 상세 보기
			int faqNo=Integer.parseInt(request.getParameter("faqNo"));
			faqVO=new FaqVO();
			faqVO=csService.selectFaq(faqNo);
			request.setAttribute("faq", faqVO);
			nextPage="/views/admin/faqView.jsp";
			
		} else if(action.equals("/faqForm.do")) {	// 자주하는 질문 등록 페이지 이동
			nextPage="/views/admin/faqForm.jsp";
			
		} else if(action.equals("/addFaq.do")) {	// 자주하는 질문 DB에 등록
			String adminId=request.getParameter("adminId");
			String faqTitle=request.getParameter("faqTitle");
			String faqContent=request.getParameter("faqContent");
			FaqVO faqVO=new FaqVO();
			faqVO.setAdminId(adminId);
			faqVO.setFaqTitle(faqTitle);
			faqVO.setFaqContent(faqContent);
			csService.addFaq(faqVO);
			nextPage="/admin/faqList.do";
		} else if(action.equals("/modFaqForm.do")) {	// 자주하는 질문 수정 페이지 이동
			int faqNo=Integer.parseInt(request.getParameter("faqNo"));
			faqVO=csService.selectFaq(faqNo);
			request.setAttribute("faq", faqVO);
			nextPage="/views/admin/modFaqForm.jsp";
		} else if(action.equals("/modFaq.do")) {	// 자주하는 질문 DB에 수정
			int faqNo=Integer.parseInt(request.getParameter("faqNo"));
			String adminId=request.getParameter("adminId");
			String faqTitle=request.getParameter("faqTitle");
			String faqContent=request.getParameter("faqContent");
			FaqVO faqVO=new FaqVO();
			faqVO.setAdminId(adminId);
			faqVO.setFaqTitle(faqTitle);
			faqVO.setFaqContent(faqContent);
			csService.updateFaq(faqVO,faqNo);
			nextPage="/admin/faqView.do?faqNo="+faqNo;
		} else if(action.equals("/deleteFaq.do")) {		// 자주하는 질문 삭제
			int faqNo=Integer.parseInt(request.getParameter("faqNo"));
			csService.deleteFaq(faqNo);
			nextPage="/admin/faqList.do";
		} else if(action.equals("/freeBoardList.do")) {		// 자유게시판 목록
			String _section=request.getParameter("section");
			String _pageNum=request.getParameter("pageNum");
			int section=Integer.parseInt((_section == null)?"1":_section);
			int pageNum=Integer.parseInt((_pageNum == null)?"1":_pageNum);
			Map<String, Integer> pagingMap=new HashMap<String, Integer>();
			pagingMap.put("section", section);
			pagingMap.put("pageNum", pageNum);
			Map boardMap=adminService.boardList(pagingMap);
			boardMap.put("section", section);
			boardMap.put("pageNum", pageNum);
			request.setAttribute("boardMap", boardMap);
			nextPage="/views/admin/boardList.jsp";
		} else if(action.equals("/freeView.do")) {		// 자유게시판 글 상세보기
			
		} else if(action.equals("/deleteFreeBoard.do")) {	// 자유게시판 글 삭제
			
		} else if(action.equals("/recipeList.do")) {	// 레시피 목록
			String _section = request.getParameter("section");
            String _pageNum = request.getParameter("pageNum");
            String _cate = request.getParameter("cate");
            System.out.println(_cate);
            String _recipeTitle = request.getParameter("recipeTitle");
            String _recipeId = request.getParameter("recipeId");
            String section = (_section == null)?"1":_section;
            String pageNum = (_pageNum == null)?"1":_pageNum;
            String cate = (_cate == null)?"값이 없음":_cate;
            String recipeTitle = (_recipeTitle == null)?"값이 없음":_recipeTitle;
            String recipeId = (_recipeId == null)?"값이 없음":_recipeId;
            Map<String, String> pagingMap = new HashMap<String, String>();
            pagingMap.put("section", section);
            pagingMap.put("pageNum", pageNum);
            pagingMap.put("cate", cate);
            pagingMap.put("recipeTitle", recipeTitle);
            pagingMap.put("recipeId", recipeId);
            Map recipeMap = recipeService.listRecipe(pagingMap);
            recipeMap.put("section", section);
            recipeMap.put("pageNum", pageNum);
            request.setAttribute("recipeMap", recipeMap);
            nextPage="/views/admin/recipeList.jsp";
		}
		 
		RequestDispatcher dispatcher=request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
	}
	
	// 파일 업로드 메소드
	/*private List<String> fileUpload(MultipartHttpServletRequest multipartRequest) throws Exception {
		List<String> fileList=new ArrayList<String>();
		Iterator<String> fileNames=multipartRequest.getFileNames();		// input file의 name속성 값을 모두 가져옴 ex) file1
		while(fileNames.hasNext()) {	// 파일 이름 다 가져올 때까지 수행
			String fileName=fileNames.next();	// 가져온 name값을 하나씩 넘겨줌
			MultipartFile mFile=multipartRequest.getFile(fileName);		// 클라이언트로부터 fileName이름을 가진 파일을 가져옴
			String originalFileName=mFile.getOriginalFilename();		// 클라이언트로부터 가져온 파일의 실제 이름을 가져옴
			fileList.add(originalFileName);		// 리스트에 실제 파일 이름 넣음
			File file=new File(PROD_IMG_REPO+"\\"+fileName);		// 저장할 파일의 경로를 가진 File 객체 생성
			if(mFile.getSize() != 0) { // 파일 크기가 0이 아닐때
				if(!file.exists()) {	// 파일 혹은 폴더가 없을 경우
					System.out.println("parentFile : "+file.getParentFile());
					if(file.getParentFile().mkdirs()) {		// 현재 파일 경로의 상위 폴더 이름으로 폴더 생성
						file.createNewFile();	// 새로운 디렉터리 만든 후 파일 생성
					}
				}
				mFile.transferTo(new File(PROD_IMG_REPO+"\\"+originalFileName));	// 임시로 갖고있던 파일들을 오리지널 이름으로 저장
			}
		}
		return fileList;
	}*/

}
