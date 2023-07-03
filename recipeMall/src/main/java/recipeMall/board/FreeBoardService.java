package recipeMall.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreeBoardService {
	FreeBoardDAO freBoardDAO;
	
	public FreeBoardService() {
		freBoardDAO = new FreeBoardDAO();
	}
	
	//게시글 리스트 기존 것
	public List<FreeBoardVO> boardList( ) {
		List<FreeBoardVO> boardList=freBoardDAO.selectAllBoardList();
		if(boardList.isEmpty()) {
			System.out.println("글 없음");
		}
		return boardList;
	}
	
	public Map boardList(Map pagingMap) {
		Map boardMap=new HashMap();
		List<FreeBoardVO> boardList=freBoardDAO.selectAllBoardList(pagingMap);
		int totArticles=freBoardDAO.selectToArticles();
		boardMap.put("boardList",boardList);
		boardMap.put("totArticles", totArticles);
		return boardMap;
	}
	//게시글 쓰기
	public int insertBoard(FreeBoardVO freeboardVO) {
		return freBoardDAO.insertBoard(freeboardVO);
		  	//  이미지 폴더 이름에 넣을 글 번호 가져옴
		 
	}
	
	//댓글
	public Map<String, Object> boardView(int boardNo) {
		Map<String, Object> boardMap= new HashMap<String, Object>();
		FreeBoardVO boardVO =freBoardDAO.viewBoard(boardNo);
		boardMap.put("boardVO", boardVO);
		List<FreeBoardReplyVO> replyList=freBoardDAO.selectreplyBoard(boardNo);
		boardMap.put("replyList", replyList);
		return boardMap;
	}
	
	public FreeBoardVO reWrite(int boardNo) {
		FreeBoardVO boardView =freBoardDAO.reWriteboard(boardNo);
		return boardView;
	}
	public void modView(FreeBoardVO freeVO) {
		freBoardDAO.modView(freeVO);
	}
	
	public void removeBoard(FreeBoardVO freeVO) {
		freBoardDAO.removeBoard(freeVO);
	}
	
	public void reply(FreeBoardReplyVO replyVO){
		freBoardDAO.replyBoard(replyVO);
	}
}
	
	
	

