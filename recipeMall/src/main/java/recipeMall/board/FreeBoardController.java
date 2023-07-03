package recipeMall.board;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

@WebServlet("/free/*")
public class FreeBoardController extends HttpServlet {
	FreeBoardService freeboardService;
	FreeBoardVO freeboardVO;
	FreeBoardDAO freeBoardDAO;
	HttpSession session;
	FreeBoardReplyVO replyVO;
	
	private static String BOARDIMG="C:\\phj\\recipeMall\\freeBoard_image";
	
	public void init(ServletConfig config) throws ServletException {
		freeboardService=new FreeBoardService();
		freeboardVO=new FreeBoardVO();
		freeBoardDAO=new FreeBoardDAO();
		replyVO=new FreeBoardReplyVO();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nextPage="";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session;
		String action=request.getPathInfo();//요청명을 가져옴.
		System.out.println("요청이름 : " + action);
		try {
			List<FreeBoardVO> boardList =new ArrayList<FreeBoardVO>();
			// 전체 리스트 가져오기
			if (action == null || action.equals("/free_board.do")) {
				String _section=request.getParameter("section");  //23-05-25
				String _pageNum=request.getParameter("pageNum"); // 페이지 번호 
				int section=Integer.parseInt((_section == null)?"1":_section);
				int pageNum=Integer.parseInt((_pageNum == null)?"1":_pageNum);
				Map<String, Integer> pagingMap=new HashMap<String, Integer>();
				pagingMap.put("section", section);
				pagingMap.put("pageNum", pageNum);
				Map boardMap=freeboardService.boardList(pagingMap);
				boardMap.put("section", section);
				boardMap.put("pageNum", pageNum);
				request.setAttribute("boardMap", boardMap);
				nextPage="/views/community/free_board.jsp";
			}else if(action.equals("/notice_write.do")) {
				nextPage="/views/community/notice_write.jsp";
			}else if(action.equals("/addWrite.do")) {
				//session=request.getSession(false);	// 세션 있을 경우에만 가져옴. 없으면 생성 안함.
				int boardNo = 0;
				Map<String, String> boardMap=upload(request, response);
				String id =boardMap.get("id");
				String boardName = boardMap.get("boardName");
				String boardContent=boardMap.get("boardContent"); 
				String boardimg=boardMap.get("boardimg");
				freeboardVO.setId(id);
				freeboardVO.setBoardName(boardName);
				freeboardVO.setBoardContent(boardContent);
				freeboardVO.setImgName(boardimg);//이미지를 추가하면 에러가 나지않지만 안넣고 글을쓰면 에러가 발생함 => ArticleVO
				boardNo=freeboardService.insertBoard(freeboardVO);
				 if(boardimg !=null && boardimg.length()!=0) {
		               //String originalFileName=boardMap.get("originalFileName");
					   System.out.println(boardimg + "입니다.");
		               File srcFile=new File(BOARDIMG + "\\temp\\" + boardimg );
		               File destDir=new File(BOARDIMG + "\\" + boardNo);
		               destDir.mkdirs();
		               FileUtils.moveFileToDirectory(srcFile, destDir, true);
		               srcFile.delete();
		               System.out.println("파일폴더 생성 끝");
				 }
				 	   nextPage="/free/free_board.do";
			}else if(action.equals("/view.do")) { //상세보기
				int boardNo = Integer.parseInt(request.getParameter("boardNo"));
				Map<String, Object> boardMap= new HashMap<String, Object>();
				boardMap=freeboardService.boardView(boardNo);
				request.setAttribute("boardMap", boardMap);
				nextPage="/views/community/view.jsp";
			}else if(action.equals("/reply.do")){ // 댓글
				int boardNo = Integer.parseInt(request.getParameter("boardNo"));
				/* FreeBoardVO boardVO=freeboardService.boardView(boardNo); */
				/* request.setAttribute("boardVO", boardVO); */
				String content =request.getParameter("content");
				String id =request.getParameter("id");
				 System.out.println(content);
				 System.out.println(boardNo);
				 System.out.println(id);
				 replyVO.setBoardNo(boardNo);
				 replyVO.setContent(content);
				 replyVO.setId(id);
				 freeboardService.reply(replyVO);
				 nextPage="/free/view.do";
			}else if(action.equals("/update.do")) { //수정하기 전에 수정 할 페이지 보여주기
				int boardNo = Integer.parseInt(request.getParameter("boardNo"));
				System.out.println(boardNo + "입니다.");
				FreeBoardVO freeVO = new FreeBoardVO();
				freeVO=freeboardService.reWrite(boardNo);
				request.setAttribute("freeVO", freeVO);
				nextPage="/views/community/notice_rewrite.jsp";
			}else if(action.equals("/modWrite.do")) { //수정 하기에서 완료 누르면 !여기가 진짜 수정하기 !!!!!!!!!!!!!!!!!!!
				 Map<String,String> boardMap=upload(request,response);
				 FreeBoardVO freeVO = new FreeBoardVO();
				 /*String id = request.getParameter("id");
				 int boardNo= Integer.parseInt(request.getParameter("boardNo"));
				 String boardName = request.getParameter("boardName"); 
				 String boardContent = request.getParameter("boardContent");
				 String boardimg = request.getParameter("boardimg");*/
				 String id = boardMap.get("id");
				 int boardNo= Integer.parseInt(boardMap.get("boardNo"));
				 String boardName = boardMap.get("boardName"); 
				 String boardContent = boardMap.get("boardContent");
				 String boardimg = boardMap.get("boardimg");
				 System.out.println(boardNo+ "지금번호");
				 System.out.println(boardName+ "입니다."); 
				 System.out.println(boardContent+ "입니다.");
				 System.out.println(boardimg+ "입니다.");
				 freeVO.setBoardNo(boardNo);
				 freeVO.setBoardName(boardName);
				 freeVO.setBoardContent(boardContent);
				 freeVO.setImgName(boardimg);
				 freeboardService.modView(freeVO);
				  //이미지가 없는 상태에서 새로 추가한 것만 
		         if(boardimg !=null && boardimg.length()!=0) {
			         String originalFileName=boardMap.get("originalFileName");
				     File srcFile=new File(BOARDIMG + "\\temp\\" + boardimg); // temp 안 에 이미지 가져은 것
				     File destDir=new File(BOARDIMG + "\\" + boardNo); //이게 만들어 지는 것 
				     destDir.mkdirs(); // 폴더 생성 하는 것 
				     FileUtils.moveFileToDirectory(srcFile, destDir, true);
				     File oldFile=new File(BOARDIMG + "\\" + boardNo + "\\" + originalFileName);
				     oldFile.delete();
			     }
		        out=response.getWriter();
	            out.print("<script>");
	            out.print("alert('수정 완료');");
	            out.print("</script>");
				nextPage="/free/free_board.do";
			}else if(action.equals("/remove.do")) {
				int boardNo= Integer.parseInt( request.getParameter("boardNo"));
				System.out.println(boardNo + "지우기 위해 가져온 번호");
				FreeBoardVO freeVO= new FreeBoardVO();
				freeVO.setBoardNo(boardNo);
				freeboardService.removeBoard(freeVO);
				nextPage="/free/free_board.do";
				
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);// 디스패처로 자료를 넣음
		    dispatcher.forward(request, response); // 그 자료를 포워드 즉 넘겨준는 거임
		} catch (Exception e) {
			System.out.println("요청 처리 중 에러");
			e.printStackTrace();
		}
	}
	
	//이미지 파일 업로드 + 새글 관련 정보 저장
	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Map<String, String> boardMap = new HashMap<String, String>();
		String encoding="utf-8";
		
        //글 이미지 저장 폴더에 대한 파일 객체를 생성
		File currentDirPath = new File(BOARDIMG);
		DiskFileItemFactory factory=new DiskFileItemFactory();
		factory.setRepository(currentDirPath);
		//한번에 저장할 수 있는 크기를 말함 => 이것보다 더 크면 버퍼에 저장했다가 나눠서 보내게됨 
		factory.setSizeThreshold(1024*1024);
		ServletFileUpload upload=new ServletFileUpload(factory); 
		try {
			List items=upload.parseRequest(request);
			for (int i = 0; i < items.size(); i++) {
				FileItem fileItem=(FileItem)items.get(i);
				if (fileItem.isFormField()) {
					System.out.println(fileItem.getFieldName()+"="+fileItem.getString(encoding));
					//파일 업로드 할 이미지와 같이 전송된 새글 관련(제목, 내용) 매개변수를 Map(키,값) 로 저장
					boardMap.put(fileItem.getFieldName(),fileItem.getString(encoding));
				}else  { //이미지
					System.out.println("매개변수 이름 : " +fileItem.getFieldName());
					System.out.println("파일(이미지)이름 : "+fileItem.getName());
					System.out.println("이미지 크기 : "+ fileItem.getSize()+"bytes");
					if (fileItem.getSize()>0) {
						int idx=fileItem.getName().lastIndexOf("\\");
						if (idx==-1) {
							idx=fileItem.getName().lastIndexOf("/");
						}
						String fileName=fileItem.getName().substring(idx+1);
						boardMap.put(fileItem.getFieldName(),fileName);
						File uploadFile= new File(currentDirPath+"\\temp\\"+fileName); 
						fileItem.write(uploadFile);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("파일 업로드 중 에러!!");
			e.printStackTrace();
		}
		System.out.println("리턴합니당");
		return boardMap;
	}
}
