package recipeMall.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import recipeMall.notice.NoticeVO;

public class CenterService {
	ServiceDAO serviceDAO;
	InqImgDAO imgDAO;
	
	public CenterService() {
		serviceDAO=new ServiceDAO();
		imgDAO=new InqImgDAO();
	}
	
	// 유저 일대일 문의 목록 페이징
	public Map inqList(Map<String, Integer> pagingMap, String id) {
		Map inqueryMap=new HashMap();
		// 1페이지 개수인 10개만 목록 가져옴
		List<InqueryVO> inqueryList=serviceDAO.selectUserInquery(pagingMap, id);
		// 전체 글 개수 가져와서 페에지 개수 만듬
		int totArticles=serviceDAO.selectToInqueries(id);
		inqueryMap.put("inqueryList", inqueryList);
		inqueryMap.put("totArticles", totArticles);
		
		return inqueryMap;
	}
	
	// 관리자 - 모든 유저 일대일 문의 목록 페이징
	public Map inqAllList(Map<String, Integer> pagingMap) {
		Map inqueryMap=new HashMap();
		// 1페이지 개수인 10개만 목록 가져옴
		List<InqueryVO> inqueryList=serviceDAO.seleteAllUserInquery(pagingMap);
		// 전체 글 개수 가져와서 페에지 개수 만듬
		int totInqueries=serviceDAO.selectToInqueries();
		System.out.println("문의 개수: "+totInqueries);
		inqueryMap.put("inqueryList", inqueryList);
		inqueryMap.put("totInqueries", totInqueries);
		
		return inqueryMap;
	}
	
	public List<FaqVO> selectFaqList() {
		List<FaqVO> faqList=serviceDAO.selectFaqList();
		return faqList;
	}
	
	public Map admFaqList(Map<String, Integer> pagingMap) {
		Map faqMap=new HashMap();
		// 1페이지 개수인 10개만 목록 가져옴
		List<FaqVO> faqList=serviceDAO.admFaqList(pagingMap);
		// 전체 글 개수 가져와서 페에지 개수 만듬
		int totFaq=serviceDAO.selectToFaq();
		System.out.println("문의 개수: "+totFaq);
		faqMap.put("faqList", faqList);
		faqMap.put("totFaq", totFaq);
		
		return faqMap;
	}
	
	public int addInquery(Map<String, Object> inqMap, String[] imgFileNames) {
		InqueryVO inqVO=(InqueryVO)inqMap.get("inqVO");
		serviceDAO.addInquery(inqVO);
		int inqNo=serviceDAO.selectInqNo(inqVO.getId());
		
		if(imgFileNames != null) {
			imgDAO.insertInqImg(inqMap, inqNo, imgFileNames);			
		}
		return inqNo;
	}
	
	public InqueryVO inqView(String id, int inqNo) {
		InqueryVO inqueryVO=serviceDAO.inqView(id, inqNo);
		return inqueryVO;
	}
	
	// 관리자 - 유저 문의 보기
	public InqueryVO selectInquery(int inqNo) {
		InqueryVO inqueryVO=serviceDAO.selectInquery(inqNo);
		return inqueryVO;
	}
	
	// 관리자 - 답변 등록
	public void inqReply(int inqNo, String adminId, String inqReply) {
		serviceDAO.inqReply(inqNo, adminId, inqReply);
	}
	
	// 관리자 - 자주하는 질문 등록
	public void addFaq(FaqVO faqVO) {
		serviceDAO.addFaq(faqVO);
	}
	
	// 관리자 - faq 보기
	public FaqVO selectFaq(int faqNo) {
		FaqVO faqVO=serviceDAO.selectFaq(faqNo);
		return faqVO;
	}
	
	public void updateFaq(FaqVO faqVO, int faqNo) {
		serviceDAO.updateFaq(faqVO, faqNo);
	}
	
	public void deleteFaq(int faqNo) {
		serviceDAO.deleteFaq(faqNo);
	}
}
