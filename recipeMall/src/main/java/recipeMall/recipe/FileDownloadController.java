package recipeMall.recipe;

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


@WebServlet("/download.do")
public class FileDownloadController extends HttpServlet {
    //  C:\\eunbi\\jspWebProject\\commu5 => 이부분 최종 경로 정하고 수정하기  그뒤에 src부터는 고정 
	private static String RECIPE_IMG="C:\\dev\\jspWebProject\\recipeMall\\src\\main\\webapp\\views\\community\\recipe_image";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void  doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		
		String recipeNo=request.getParameter("recipeNo");
		String imgName=request.getParameter("imgName");
		OutputStream outs=response.getOutputStream();
		String path=RECIPE_IMG+"\\rec"+recipeNo+"\\"+imgName;
		File imgFile = new File(path);
		response.setHeader("Cache-Control", "no-cache");
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



