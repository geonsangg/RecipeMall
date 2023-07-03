package recipeMall.board;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/downloadboa.do")
public class FileDownloadController extends HttpServlet {
	private static String BOARDIMG="C:\\phj\\recipeMall\\freeBoard_image";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void  doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//imgcate를 정의하고 setattribute 해서 jsp로 보내야함 
		String boardNo=request.getParameter("boardNo");
		String imgName=request.getParameter("imgName");
		OutputStream outs=response.getOutputStream();
		String path=BOARDIMG+"\\"+boardNo+"\\"+imgName;
		File imgFile = new File(path);
		response.setHeader("Cache-Control", "no-cache");
		//이미지 파일을 내려받는데 필요한 response 헤더 정보를 설정
		response.addHeader("Content-disposition", "attachement;fileName="+imgFile);
		FileInputStream fis=new FileInputStream(imgFile);
		byte[] buffer=new byte[1024*8]; //버퍼이용해서 한번에 8k씩 전송
		while (true) {
			int count=fis.read(buffer);
			if (count==-1) break;
			outs.write(buffer,0,count);
		}
		fis.close();
		outs.close();
	}

}



