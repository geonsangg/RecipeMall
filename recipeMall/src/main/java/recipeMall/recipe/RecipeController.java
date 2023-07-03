package recipeMall.recipe;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
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

@WebServlet("/recipe/*")
public class RecipeController extends HttpServlet {
	RecipeService recipeService;
	RecipeVO recipeVO;
	RecipeInfoVO recipeInfoVO;
	MaterialtypeVO mateTypeVO;
	MaterialVO mateVO;
	RecipeStepVO stepVO;
	ImageVO  imageVO;
	
	//  C:\\eunbi\\jspWebProject\\commu5 => 이부분 최종 경로 정하고 수정하기 그뒤에 src부터는 고정 
	
	private static String RECIPE_IMG="C:\\dev\\jspWebProject\\recipeMall\\src\\main\\webapp\\views\\community\\recipe_image";
	
	public void init(ServletConfig config) throws ServletException{
		recipeService=new RecipeService();
		recipeVO=new RecipeVO();
		recipeInfoVO= new RecipeInfoVO();
		mateVO= new MaterialVO();
		mateTypeVO= new MaterialtypeVO();
		stepVO= new RecipeStepVO();
		imageVO= new ImageVO();
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	public void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); // 문자 인코딩 설정
		response.setContentType("text/html;charset=utf-8");
		String nextPage = "";
		PrintWriter out; 
		HttpSession session;
		String action=request.getPathInfo();
		System.out.println("요청이름 : "+action);
		try {
			List<RecipeVO> recipeList=new ArrayList<RecipeVO>();
			//페이징 + 검색 - recipe.jsp 페이지 
			if(action==null||action.equals("/recipe.do")) {
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
	            nextPage="/views/community/recipe.jsp";
				
			//best 만들어야함 	
			}else if(action.equals("/best.do")) {
				RecipeDAO dao = new RecipeDAO();
				List<RecipeVO>bestList = new ArrayList<>();
				bestList = dao.selectbest();
				request.setAttribute("bestList", bestList);
				nextPage="/views/community/best.jsp";
				
			}else if (action.equals("/insert.do")){
				
				nextPage="/views/community/insert.jsp";
			
			}
			else if(action.equals("/recinsert.do")) {
				//session=request.getSession(false);
				int recipeNo=0; 
				Map<String, Object> recipeMap=upload(request, response);
				String id= (String)recipeMap.get("id");
				String title=(String)recipeMap.get("title");
				String intro=(String)recipeMap.get("intro");
				String videoLink =(String)recipeMap.get("link");
				String cate=(String)recipeMap.get("cate");
				String infoServ =(String)recipeMap.get("serv");
				String infoTime=(String)recipeMap.get("time");
				String infoDiff =(String)recipeMap.get("diff");
				
				//matetypeNo 만드는 메소드 서비스에서 가져옴 
				int maxTypeNo = recipeService.maxTypeNo();
				
				List matetypeNameList = (List)recipeMap.get("matetypeList");
				List matetypeNoListTemp=(List)recipeMap.get("matetypeNoList");
				List matetypeNoList1 = new ArrayList();
				List matetypeNoList2 = new ArrayList();
				
				int tempNo=0;
				int cnt=0;
				for(int i=0; i<matetypeNoListTemp.size(); i++) {
					int typeNo = Integer.parseInt(matetypeNoListTemp.get(i).toString());
					if(tempNo == 0) {
						tempNo = typeNo;
						matetypeNoList1.add(i,maxTypeNo);
						matetypeNoList2.add(cnt,maxTypeNo);
						cnt++;
					}else {
						if(tempNo == typeNo) {
							matetypeNoList1.add(i,maxTypeNo);
						}else {
							tempNo = typeNo;
							maxTypeNo+=1;
							matetypeNoList1.add(i,maxTypeNo);
							matetypeNoList2.add(cnt,maxTypeNo);
							cnt++;
						}
					}
				}
				List<MaterialtypeVO> MaterialtypeList = new ArrayList<>();
				for(int i=0;i<matetypeNameList.size();i++) {
					MaterialtypeVO mateTypeVO = new MaterialtypeVO();
					mateTypeVO.setMatetypeName(matetypeNameList.get(i).toString());
					mateTypeVO.setMatetypeNo(Integer.parseInt(matetypeNoList2.get(i).toString()));
					MaterialtypeList.add(mateTypeVO);
				}
				mateTypeVO.setMaterialtypeList(MaterialtypeList);
				
				List mateNameList=(List)recipeMap.get("mate_nameList");
				List mateAmountList=(List)recipeMap.get("mate_amountList");
				List<MaterialVO> MaterialList = new ArrayList<>();
				for(int i=0;i<mateNameList.size();i++) {
					MaterialVO mateVO = new MaterialVO();
					mateVO.setMateName(mateNameList.get(i).toString());
					mateVO.setMateAmount(mateAmountList.get(i).toString());
					mateVO.setMatetypeNo(Integer.parseInt(matetypeNoList1.get(i).toString()));
					MaterialList.add(mateVO);
				}
				mateVO.setMaterialList(MaterialList);
				
				List recipeStepExList=(List)recipeMap.get("step_exList");
				List<RecipeStepVO> recipeStepList = new ArrayList<>();
				for(int i=0;i<recipeStepExList.size();i++) {
					RecipeStepVO recipeStepVO = new RecipeStepVO();
					recipeStepVO.setStepEx(recipeStepExList.get(i).toString());
					recipeStepList.add(recipeStepVO);
				}
				stepVO.setRecipeStepList(recipeStepList);
				
				String tip=(String)recipeMap.get("tip");
				
				//이미지 
				List<ImageVO> ImageList  = new ArrayList<>();
				List ImgCateList=(List)recipeMap.get("ImgCateList");
				List ImgNameList = (List)recipeMap.get("ImgNameList");
				List StepNumList =(List)recipeMap.get("StepNumList");
				
				for(int i=0; i<ImgCateList.size(); i++) {
					ImageVO imgVO = new ImageVO();
					imgVO.setImgCate(Integer.parseInt(ImgCateList.get(i).toString()));
					imgVO.setImgName(ImgNameList.get(i).toString());
					imgVO.setStepNum(Integer.parseInt(StepNumList.get(i).toString()));
					ImageList.add(imgVO);
				}
				imageVO.setImgList(ImageList);
				
				
				//글쓰는 작성자 id 어떻게 받아오나요..
				recipeVO.setId(id); 
				recipeVO.setCntLike(0);
				recipeVO.setRecipeTitle(title);
				recipeVO.setRecipeIntro(intro);
				recipeVO.setVideoLink(videoLink);
				recipeVO.setCate(cate);
				recipeVO.setRecipeTip(tip);
				
				recipeInfoVO.setInfoServ(infoServ);
				recipeInfoVO.setInfoTime(infoTime);
				recipeInfoVO.setInfoDiff(infoDiff);
				recipeVO.setInfoVO(recipeInfoVO);
				recipeVO.setMatetypeVO(mateTypeVO);
				recipeVO.setMateVO(mateVO);
				recipeVO.setStepVO(stepVO);
				recipeVO.setImgVO(imageVO);
				
				recipeNo=recipeService.insertRecipe(recipeVO);
				//새 글 추가시 이미지를 첨부한 경우에만 수행 - 받아온 네임리스트의  크기로
				if (ImgNameList !=null && ImgNameList.size()!=0) {
					for(int i=0; i<ImgNameList.size(); i++) {
						System.out.println("파일생성");
						File srcFile=new File(RECIPE_IMG+"\\temp\\"+ImgNameList.get(i));
						File destDir=new File(RECIPE_IMG+"\\rec"+recipeNo);
			            destDir.mkdirs(); // 폴더 생성
			            try {
			                FileUtils.moveFileToDirectory(srcFile, destDir, true);
			                srcFile.delete();
			            } catch (IOException e) {
			                e.printStackTrace();
			            }
					}
					
				}
				out=response.getWriter();
				out.print("<script>");
				out.print("alert('새글을 추가했습니다.');");
				out.print("location.href='"+request.getContextPath()+"/recipe/recipe.do'");
				out.print("</script>");
				return;
				
			}//상세페이지 보기 - 완성된거임 
			else if(action.equals("/specific.do")) {
				String id= request.getParameter("id");
				int recipeNo=Integer.parseInt(request.getParameter("recipeNo"));
                //specific.do 수행시 주소창에 recipeNo=?&id=..로그인한 사람의 아이디를 가져가야함 => 수정하기 
				recipeVO=recipeService.viewRecipe(id,recipeNo);
				request.setAttribute("recipe", recipeVO);
				
				//리스트 받아오기 - 묶음테이블
				List mateTypeList= recipeVO.getMatetypeVO().getMaterialtypeList();
				request.setAttribute("mateTypeList", mateTypeList);
				
				//리스트 받아오기 - 재료테이블
				List materialList = recipeVO.getMateVO().getMaterialList();
				request.setAttribute("materialList", materialList);
				
				//리스트 받아오기 - 순서테이블
				List stepExList = recipeVO.getStepVO().getRecipeStepList();
				request.setAttribute("stepExList", stepExList);
				
				//리스트 받아오기 - 이미지테이블 
				List imgList= recipeVO.getImgVO().getImgList();
				request.setAttribute("imgList", imgList);
				
				
				
				nextPage="/views/community/specific.jsp";
			}else if (action.equals("/deleteLike.do")) {
	             try {
	            	  //id 로그인한 정보로 받아오는방법으로 수정해야함 
	            	int recipeNo= Integer.parseInt(request.getParameter("recipeNo")) ;
	                String id = request.getParameter("id");
	                int cntLike = Integer.parseInt(request.getParameter("cntLike"));
	                recipeService.deleteLike(recipeNo,id,cntLike);
	                nextPage="/recipe/specific.do";
	                out=response.getWriter();
					out.print("<script>");
					out.print("alert('좋아요 취소!');");
					out.print("location.href='"+request.getContextPath()+"/recipe/specific.do?recipeNo="+recipeNo + "&id="+ id +"'");
					out.print("</script>");
	              } catch (Exception e) {
	                  System.out.println("deleteLike 요청 에러");
	                  e.printStackTrace();
	              }
					return;
	          }else if (action.equals("/insertLike.do")) {
	              try {
	            	  //id 로그인한 정보로 받아오는방법으로 수정해야함 
		                String id = request.getParameter("id");
		                int cntLike = Integer.parseInt(request.getParameter("cntLike"));
		                int recipeNo=Integer.parseInt(request.getParameter("recipeNo"));
		                RecipeDAO dao=new RecipeDAO();
		                recipeService.insertLike(recipeNo,id, cntLike);
		                out=response.getWriter();
						out.print("<script>");
						out.print("alert('좋아요!');");
						out.print("location.href='"+request.getContextPath()+"/recipe/specific.do?recipeNo="+recipeNo + "&id="+ id +"'");
						out.print("</script>");
						return;
		              } catch (Exception e) {
		                  System.out.println("insertLike 요청 에러");
		                  e.printStackTrace();
		              }
	          }else if (action.equals("/updateLike.do")) {
              try {
            	  //id 로그인한 정보로 받아오는방법으로 수정해야함 
                String id = request.getParameter("id");
                int cntLike = Integer.parseInt(request.getParameter("cntLike"));
                int recipeNo=Integer.parseInt(request.getParameter("recipeNo"));
                RecipeDAO dao=new RecipeDAO();
                recipeService.updateLike(recipeNo,id, cntLike);
                out=response.getWriter();
				out.print("<script>");
				out.print("alert('다시 좋아요!');");
				out.print("location.href='"+request.getContextPath()+"/recipe/specific.do?recipeNo=" + recipeNo + "&id="+ id +"'");
				out.print("</script>");
				return;
              } catch (Exception e) {
                  System.out.println("updateLike 요청 에러");
                  e.printStackTrace();
              }
          }
			RequestDispatcher dispatcher=request.getRequestDispatcher(nextPage);	
			dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println("recipeController 요청 에러");
			e.printStackTrace();
		}
	}
	
	
	 //이미지 파일 업로드 + 새글 관련 정보 저장
	private Map<String, Object> upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Map<String, Object> recipeMap = new HashMap<String, Object>();
		String encoding="utf-8";
		File currentDirPath = new java.io.File(RECIPE_IMG);
		DiskFileItemFactory factory=new DiskFileItemFactory();
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024*1024);
		ServletFileUpload upload=new ServletFileUpload(factory); 
		try {
			List items=upload.parseRequest(request);
			int matetypeNo=0;
			int stepNum=0;
			for (int i = 0; i < items.size(); i++) {
				FileItem fileItem=(FileItem)items.get(i);
				if (fileItem.isFormField()) {
					String fieldName = fileItem.getFieldName();
					String fieldValue = fileItem.getString(encoding);
					String listFieldName = fieldName + "List";
					if (fieldName.equals("matetype")){
						if (matetypeNo==0) {
							 matetypeNo = 1;
						}else {
							matetypeNo++;
						}
					}
					if (fieldName.equals("matetype") || fieldName.equals("mate_name") || fieldName.equals("mate_amount") || fieldName.equals("step_ex")) {
					    List<String> fieldValueList = (List<String>) recipeMap.get(listFieldName); //기존꺼는 계속 리스트를 생성하게되서 이렇게 변경함 
						if ( fieldName.equals("mate_name")) {
							System.out.println("matetypeNo : " +matetypeNo);
							List matetypeNoList = (List) recipeMap.get("matetypeNoList"); 
							if (matetypeNoList==null) {
								matetypeNoList = new ArrayList();
								recipeMap.put("matetypeNoList", matetypeNoList);
							}
							matetypeNoList.add(matetypeNo);
						}
					    if (fieldValueList == null) {
					        fieldValueList = new ArrayList<>();
					        recipeMap.put(listFieldName, fieldValueList);
					    }
					    fieldValueList.add(fieldValue);
					}
					System.out.println(fileItem.getFieldName()+"="+fileItem.getString(encoding));

					recipeMap.put(fileItem.getFieldName(),fileItem.getString(encoding));
				}
				else  { 
					List ImgCateList = (List)recipeMap.get("ImgCateList");
					List ImgNameList = (List<String>)recipeMap.get("ImgNameList");
					List StepNumList =(List)recipeMap.get("StepNumList");
					if (ImgNameList== null) {
						 ImgNameList = new ArrayList<>();
						 ImgCateList = new ArrayList<>();
						 StepNumList = new ArrayList<>();
						 recipeMap.put("ImgNameList",ImgNameList);
						 recipeMap.put("ImgCateList",ImgCateList);
						 recipeMap.put("StepNumList",StepNumList);
					}
					System.out.println("매개변수 이름 : " +fileItem.getFieldName());
					System.out.println("파일(이미지)이름 : "+fileItem.getName());
					System.out.println("이미지 크기 : "+ fileItem.getSize()+"bytes");
					//대표이미지
					int cateNum;
					int f_stepNum =0;
					
					if (fileItem.getFieldName().equals("rec_file")) {
						System.out.println("메인이미지");
						cateNum = 1;
						ImgCateList.add(cateNum);
						StepNumList.add(f_stepNum);
						if (fileItem.getSize()>0) {//이미지가 있다면
							int idx=fileItem.getName().lastIndexOf("\\");
							if (idx==-1) {
								idx=fileItem.getName().lastIndexOf("/");
							}
							String rec_fileName=fileItem.getName().substring(idx+1);
							ImgNameList.add(rec_fileName);
							recipeMap.put(encoding, upload);
							File uploadFile= new File(currentDirPath+"\\temp\\"+rec_fileName);
							fileItem.write(uploadFile);
						}
					//순서이미지	
					}else {
						System.out.println("순서이미지");
						cateNum = 2;
						stepNum++;
						System.out.println("stepNum : " + stepNum );
						ImgCateList.add(cateNum);
						StepNumList.add(stepNum);
						
						if (fileItem.getSize()>0) {
							int idx=fileItem.getName().lastIndexOf("\\");
							if (idx==-1) {
								idx=fileItem.getName().lastIndexOf("/");
							}
							String rec_step_fileName=fileItem.getName().substring(idx+1);
							ImgNameList.add(rec_step_fileName);
							File uploadFile= new File(currentDirPath+"\\temp\\"+rec_step_fileName); //temp파일에 넣기 위해 수정함 
							fileItem.write(uploadFile);
						}
					}
					
					recipeMap.put("ImgCateList",ImgCateList);
					recipeMap.put("ImgNameList",ImgNameList);
					recipeMap.put("StepNumList",StepNumList);
					
				}
			}
		} catch (Exception e) {
			System.out.println("파일 업로드 중 에러!!");
			e.printStackTrace();
		}
		return recipeMap;
	}

}
