package recipeMall.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

@WebServlet("/service/*")
public class ServiceController extends HttpServlet {
	FaqVO faqVO;
	InqueryVO inqVO;
	HttpSession session;
	CenterService service;
	
	private static String INQUERY_IMG_REPO="C:\\phj\\inquery";
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		faqVO=new FaqVO();
		inqVO=new InqueryVO();
		service=new CenterService();
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
		System.out.println(action);

		try {
			if(action == null || action.equals("/main.do")) {
				String id=request.getParameter("id");
				nextPage="/views/mypage/mypage.jsp";
			} else if(action.equals("/faq.do")) {
				List<FaqVO> faqList=service.selectFaqList();
				request.setAttribute("faqList", faqList);
				nextPage="/views/mypage/faqList.jsp";
				
			} else if(action.equals("/inqList.do")) {
				String id=request.getParameter("id");
				// 페이지, 섹션 최초 요청시 null값
				String _section=request.getParameter("section");
				String _pageNum=request.getParameter("pageNum");
				// _section=null일 경우 1반환_초기값 1 세팅. 10->11로 페이지 넘어가면 2를 받음
				int section=Integer.parseInt((_section == null)?"1":_section);
				// _pageNum=null일 경우 1반환_초기값 1 세팅. 2페이지 누르면 2를 받음
				int pageNum=Integer.parseInt((_pageNum == null)?"1":_pageNum);
				
				Map<String, Integer> pagingMap=new HashMap<String, Integer>();
				// page set
				pagingMap.put("section", section);	// 1
				pagingMap.put("pageNum", pageNum);	// 1
				// articlesList, totArticles 받음
				Map inqueryMap=service.inqList(pagingMap, id);
				
				inqueryMap.put("section", section);
				inqueryMap.put("pageNum", pageNum);
				request.setAttribute("inqueryMap", inqueryMap);
				nextPage="/views/mypage/inqueryList.jsp";
				
			} else if(action.equals("/inqForm.do")) {
				System.out.println(action);
				nextPage="/views/mypage/inqueryForm.jsp";
				
			} else if(action.equals("/addInq.do")) {
				Map<String, Object> inqueryMap=upload(request, response);
				/*
				 * String id=request.getParameter("id"); int
				 * inqCate=Integer.parseInt(request.getParameter("inqCate")); String
				 * inqTitle=request.getParameter("inq_title"); String
				 * inqContent=request.getParameter("inq_content");
				 */
				
				String id=(String)inqueryMap.get("id");
				String inqCate=(String)inqueryMap.get("inqCate");
				String inqTitle=(String)inqueryMap.get("inq_title");
				String inqContent=(String)inqueryMap.get("inq_content");
				int cnt=(Integer)inqueryMap.get("cnt");
				String[] imgFileNames=new String[cnt];
				
				for(int i=0; i<cnt; i++) {
					imgFileNames[i]=(String)inqueryMap.get("inq_file");
					System.out.println("이미지 파일 이름"+(i+1)+" : "+imgFileNames[i]);
				}
				
				inqVO.setId(id);
				inqVO.setInqCate(Integer.parseInt(inqCate));
				inqVO.setInqTitle(inqTitle);
				inqVO.setInqContent(inqContent);
				inqueryMap.put("inqVO", inqVO);
				int inqNo=service.addInquery(inqueryMap, imgFileNames);
				
				for(int i=0; i<cnt; i++) {
					if(imgFileNames[i] != null && imgFileNames[i].length() != 0) {
						File srcFile=new File(INQUERY_IMG_REPO + "\\temp\\" + imgFileNames[i]);
						File destDir=new File(INQUERY_IMG_REPO+"\\"+id+"\\"+inqNo);	// 글 번호와 같은 파일 객체 생성
						destDir.mkdirs();	// 글 번호와 같은 폴더 생성
						FileUtils.moveFileToDirectory(srcFile, destDir, true);	// 파일이 있는 폴더, 이동할 폴더, 이동(true)
						srcFile.delete();
					}
				}
				
				nextPage="/service/inqList.do?id="+id;
			} else if(action.equals("/inqView.do")) {
				String id=request.getParameter("id");
				int inqNo=Integer.parseInt(request.getParameter("inqNo"));
				inqVO=service.inqView(id,inqNo);
				request.setAttribute("inqVO",inqVO);
				nextPage="/views/mypage/inqueryView.jsp";
			}
			
		} catch (Exception e) {
			System.out.println("service 요청 처리 중 오류 발생");
			e.printStackTrace();
		}
			
		RequestDispatcher dispatcher=request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
		
	}
	
	// 이미지 파일 업로드 + 새 글 정보 저장
	private Map<String, Object> upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> map=new HashMap<String, Object>();
		String encoding="utf-8";
		int count=0;
		// 글 이미지 저장 폴더에 대한 파일 객체 생성
		File currentDirPath=new File(INQUERY_IMG_REPO);
		// 업로드된 파일을 저장할 저장소와 관련된 클래스
		DiskFileItemFactory factory=new DiskFileItemFactory();
		// 업로드 된 파일을 저장할 위치를 File객체로 지정합니다.
		factory.setRepository(currentDirPath);
		// 저장소에 임시파일을 생성할 한계 크기를 byte단위로 지정. 지정한 크기 이상의 파일을 업로드 했을때 메모리에 있던 파일의 바이너리 데이터를 저장소에 임시파일로 잠시 저장
		factory.setSizeThreshold(1024*1024);
		// HTTP 요청에 대한 HttpServletRequest 객체로부터 multipart/form-data형식으로 넘어온 HTTP Body 부분을 다루기 쉽게 변환(parse)해주는 역할을 수행합니다.
		// parseRequest()메서드를 수행하면 FileItem이라는 형식으로 변환
		ServletFileUpload upload=new ServletFileUpload(factory);
		try {
			List items=upload.parseRequest(request);
			for(int i=0; i<items.size(); i++) {
				FileItem fileItem=(FileItem)items.get(i);
				if(fileItem.isFormField()) {	// form안의 요소의 name속성 값
					System.out.println(fileItem.getFieldName()+"="+fileItem.getString(encoding));
					// 파일 업로드할 이미지와 같이 전송된 새 글 관련(제목, 내용, 이미지) 매개변수를 Map(키,값)으로 저장 
					map.put(fileItem.getFieldName(), fileItem.getString(encoding));
				} else {
					System.out.println("매개변수 이름: "+fileItem.getFieldName());
					System.out.println("파일(이미지) 이름: "+fileItem.getName());
					System.out.println("파일(이미지) 크기: "+fileItem.getSize()+"bytes");
					if(fileItem.getSize() > 0) {
						int idx=fileItem.getName().lastIndexOf("\\");	// 윈도우
						if(idx == -1) {	// 유닉스, 리눅스
							idx=fileItem.getName().lastIndexOf("/");
						}
						String fileName=fileItem.getName().substring(idx+1);
						map.put(fileItem.getFieldName(), fileName);
						File uploadFile=new File(currentDirPath+"\\temp\\"+fileName);
						fileItem.write(uploadFile);
						count++;
					}
				}
			}
			map.put("cnt", count);
		} catch (Exception e) {
			System.out.println("새 글의 파일 업로드 중 오류 발생");
			e.printStackTrace();
		}
		return map;	// 글 제목,내용,첨부이미지 파일 이름
	}
}
