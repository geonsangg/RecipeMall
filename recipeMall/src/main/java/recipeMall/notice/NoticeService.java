package recipeMall.notice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoticeService {
	NoticeDAO noticeDAO;
	
	public NoticeService() {
		noticeDAO=new NoticeDAO();
	}
	
	// overridding
	public Map listNotices(Map<String, Integer> pagingMap) {	// section 1, pageNum 1 받음
		Map noticeMap=new HashMap();
		// 1페이지 개수인 10개만 목록 가져옴
		List<NoticeVO> noticeList=noticeDAO.seleteAllNotices(pagingMap);
		// 전체 글 개수 가져와서 페에지 개수 만듬
		int totArticles=noticeDAO.selectToNotices();
		noticeMap.put("noticeList", noticeList);
		noticeMap.put("totArticles", totArticles);
		return noticeMap;
	}
	
	public NoticeVO selectNoticeView(int noticeNo) {
		return noticeDAO.selectNoticeView(noticeNo);
	}
	
	public void addNotice(NoticeVO noticeVO) {
		noticeDAO.addNotice(noticeVO);
	}
	
	public void updateNotice(NoticeVO noticeVO) {
		noticeDAO.updateNotice(noticeVO);
	}
	
	public void deleteNotice(int noticeNo) {
		noticeDAO.deleteNotice(noticeNo);
	}
}
