package recipeMall.review;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/mypage/review/*")
public class ReviewController extends HttpServlet {
    ReviewDAO dao;
    ReviewVO reviewVO;

    public void init(ServletConfig config) throws ServletException {
        dao = new ReviewDAO();
        reviewVO=new ReviewVO();
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
        String nextPage = "";
        HttpSession session = request.getSession();
        String action = request.getPathInfo();
        System.out.println("요청이름: " + action);
        try {
            if (action == null || action.equals("/viewReview.do")) {
            	String id = request.getParameter("id");
                List<ReviewVO> reviewList = dao.selectReviewList(id);
                if(reviewList.size()!=0) {
                	System.out.println("리뷰 ㅇ");                	
                }
                request.setAttribute("reviewList", reviewList);
                nextPage = "/views/mypage/viewReview.jsp";
            } else if (action.equals("/updateReview.do")) {
            	try {
            		String id=request.getParameter("id");
            		int prodCode=Integer.parseInt(request.getParameter("prodCode"));
            		int orderCode=Integer.parseInt(request.getParameter("orderCode"));
            		String reviewStarParam = request.getParameter("reviewStar");
            		int reviewStar = Integer.parseInt(reviewStarParam);
            		//String reviewCodeStr = request.getParameter("reviewCode");
                    String reviewContent = request.getParameter("reviewContent");
                    //int reviewCode = Integer.parseInt(reviewCodeStr);

                    ReviewVO reviewVO = new ReviewVO();
                    //reviewVO.setReviewCode(reviewCode);
                    reviewVO.setId(id);
                    reviewVO.setProdCode(prodCode);
                    reviewVO.setOrderCode(orderCode);
                    reviewVO.setReviewContent(reviewContent);
                    reviewVO.setReviewStar(reviewStar);
                    dao.updateReview(reviewVO);

                    nextPage = "/mypage/review/viewReview.do"; // 업데이트 후 리뷰 목록 페이지로 이동
                } catch (Exception e) {
                    System.out.println("updateReview 오류");
                    e.printStackTrace();
                }
			} /*
				 * else if (action.equals("/insertReview.do")) { try { String prodCodeStr =
				 * request.getParameter("prodCode"); String prodImage =
				 * request.getParameter("prodImage"); String id = (String)
				 * session.getAttribute("id"); String prodName =
				 * request.getParameter("prodName"); String orderCodeStr =
				 * request.getParameter("orderCode"); ReviewVO reviewVO = new ReviewVO();
				 * reviewVO.setProdCode(Integer.parseInt(prodCodeStr));
				 * reviewVO.setProdImage(prodImage); reviewVO.setId(id);
				 * reviewVO.setProdName(prodName);
				 * reviewVO.setOrderCode(Integer.parseInt(orderCodeStr));
				 * 
				 * dao.updateReview(reviewVO);
				 * 
				 * nextPage = "/mypage/review/viewReview.do"; // 리뷰 추가 후 리뷰 목록 페이지로 이동 } catch
				 * (Exception e) { System.out.println("insertReview 오류"); e.printStackTrace(); }
				 * }
				 */
            RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
            dispatcher.forward(request, response);
        }catch (Exception e) {
        	System.out.println("ReviewController 에러: " + e.getMessage());
            e.printStackTrace();
		}
    }
}