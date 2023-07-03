package recipeMall.board;

import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import oracle.net.aso.d;

public class FreeBoardDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;

	// 커넥션풀
	 public FreeBoardDAO() {
		 try {
				Context ctx = new InitialContext();
				Context envContext = (Context) ctx.lookup("java:/comp/env");
				dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
			} catch (Exception e) {
				System.out.println("DB 연결 오류");
			}
		}

	
	
	public List<FreeBoardVO> selectAllBoardList() {
		List<FreeBoardVO> boardList = new ArrayList<FreeBoardVO>();

		try {
			conn = dataFactory.getConnection();
			String query = "select rownum as rn, boardNo, boardName, boardContent, writeDate, id, cntView from freeboardtbl order by writeDate desc";
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				FreeBoardVO boardVO = new FreeBoardVO();
				int rn=rs.getInt(1);	// 행 번호 가져옴
				int boardNo = rs.getInt(2);
				String boardName = rs.getString(3);
				String boardContent = rs.getString(4);
				Date writeDate = rs.getDate(5);
				String id = rs.getString(6);
				int cntView = rs.getInt(7);
				boardVO.setRownum(rn);
				boardVO.setBoardNo(boardNo);
				boardVO.setBoardName(boardName);
				boardVO.setBoardContent(boardContent);
				boardVO.setWriteDate(writeDate);
				boardVO.setId(id);
				boardVO.setCntView(cntView);
				boardList.add(boardVO);

			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("전체 목록 조회중 에러");
		}
		return boardList;
	}
	//페이징 처리 리스트 
	public List<FreeBoardVO> selectAllBoardList(Map<String, Integer> boardMap) {
		List<FreeBoardVO> boardList = new ArrayList<FreeBoardVO>();
		int section=boardMap.get("section");
		int pageNum=boardMap.get("pageNum");
		try {
			conn = dataFactory.getConnection();
			/*String query = "select * FROM (SELECT ROWNUM, boardNo, boardName, boardContent,writeDate,id,cntView FROM freeboardtbl order by writeDate desc)" +
					  " WHERE rownum BETWEEN (?-1)*100+(?-1)*5+1 AND (?-1)*100+?*5";*/
			
			
			String query="select * from (select ROWNUM AS recNum, boardNo, boardName, boardContent,writeDate,id,cntView from (SELECT boardNo, boardName, boardContent,writeDate,id,cntView FROM freeboardtbl order by writeDate desc)) where recNum BETWEEN (?-1)*50+(?-1)*5+1 AND (?-1)*50+?*5";
			
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, section);
			pstmt.setInt(2, pageNum);
			pstmt.setInt(3, section);
			pstmt.setInt(4, pageNum);	
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				FreeBoardVO boardVO = new FreeBoardVO();
				//int recNum=rs.getInt("recNUM");	// 행 번호 가져옴
				int boardNo = rs.getInt("boardNo");
				String boardName = rs.getString("boardName");
				String boardContent = rs.getString("boardContent");
				Date writeDate = rs.getDate("writeDate");
				String id = rs.getString("id");
				int cntView = rs.getInt("cntView");
				//boardVO.setRownum(recNum);
				boardVO.setBoardNo(boardNo);
				boardVO.setBoardName(boardName);
				boardVO.setBoardContent(boardContent);
				boardVO.setWriteDate(writeDate);
				boardVO.setId(id);
				boardVO.setCntView(cntView);
				boardList.add(boardVO);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("전체 목록 조회중 에러");
		}
		return boardList;
	}
	
	//전체 글 목록 수
	  public int selectToArticles() {
		  int totCount=0;
		  try {
			  conn=dataFactory.getConnection();
			  String query="select count(*) from freeboardtbl";
			  pstmt=conn.prepareStatement(query);
			  ResultSet rs=pstmt.executeQuery();
			  if(rs.next()) {
				  totCount=rs.getInt(1);
			  }
			  rs.close();
			  pstmt.close();
			  conn.close();
		  }catch (Exception e) {
			  System.out.println("전체 글 목록 수 처리중 에러");
			  e.printStackTrace();
		  }
		  return totCount;
	  }

	  
	//글 추가
	public int insertBoard(FreeBoardVO freeboardVO) {
		int boradNo=getBoardNo()+1;
		System.out.println("글 등록 중 글번호: "+boradNo);
			String id =freeboardVO.getId();
			String boardName =freeboardVO.getBoardName();
			String boardContent =freeboardVO.getBoardContent();
			String imgName =freeboardVO.getImgName();
			System.out.println(id);
		try {
			conn = dataFactory.getConnection();
			String query = "insert into freeboardtbl(boardNo,boardName, boardContent, id,imgName) values(boardno_seq.nextval,?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,boardName);
			pstmt.setString(2, boardContent);
			pstmt.setString(3, id);
			pstmt.setString(4, imgName);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("인서트문 실행중 에러");
		}
		return boradNo;
	}
	
	// 이미지 저장할 폴더 이름에 넣을 글 번호 가져옴. 최신 글 번호
	public int getBoardNo() {
		int boardNo=0;
		try {
			conn=dataFactory.getConnection();
			String query="select max(boardno) from freeboardtbl";
			pstmt=conn.prepareStatement(query);
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			boardNo=rs.getInt(1);
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("글 번호 조회 중 에러");
			e.printStackTrace();
		}
		return boardNo;
	}
	
	
	//상세글 보기
	public FreeBoardVO viewBoard(int boardNo) {
		FreeBoardVO boardVO = new FreeBoardVO();
	
		try {
			
			conn = dataFactory.getConnection();
			//조회수 증가
			String readsql = "update freeboardtbl set cntView = cntView+1 where boardNo=?";
			pstmt= conn.prepareStatement(readsql);
			pstmt.setInt(1, boardNo);
			pstmt.executeUpdate();

			//상세 글 보기
			String query = "select * from freeboardtbl where boardNo = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			System.out.println(boardNo+ "입니다.");
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int boardNum = rs.getInt(1);
			String boardName = rs.getString(2);
			String boardContent = rs.getString(3);
			Date writeDate = rs.getDate(4);
			String id = rs.getString(5);
			int cntView = rs.getInt(6);
			String imgName =rs.getString(7); 
			boardVO.setBoardNo(boardNum);
			boardVO.setBoardName(boardName);
			boardVO.setBoardContent(boardContent);
			boardVO.setWriteDate(writeDate);
			boardVO.setId(id);
			boardVO.setCntView(cntView);
			boardVO.setImgName(imgName);
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("글 상세보기 중 에러");
		}

		return boardVO;
	}

	// 게시글 수정
	public FreeBoardVO reWriteboard(int boardNo) {
		FreeBoardVO freeVO = new FreeBoardVO();
		try {
			conn = dataFactory.getConnection();
			String query = "select * from freeboardtbl where boardNo = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int boardNum = rs.getInt(1);
			String boardName = rs.getString(2);
			String boardContent = rs.getString(3);
			Date writeDate = rs.getDate(4);
			String id = rs.getString(5);
			int cntView = rs.getInt(6);
			String boardimg =rs.getString(7);
			freeVO.setBoardNo(boardNum);
			freeVO.setBoardName(boardName);
			freeVO.setBoardContent(boardContent);
			freeVO.setWriteDate(writeDate);
			freeVO.setId(id);
			freeVO.setCntView(cntView);
			freeVO.setImgName(boardimg);
			System.out.println(boardNum);
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("게시글 수정폼 에러");
		}
		return freeVO;

	}

		
	
	//업데이트
	  public void modView(FreeBoardVO freeVO) {
		  String boardName =freeVO.getBoardName();
		  String boardContent =freeVO.getBoardContent();
		  int boardNo =freeVO.getBoardNo();
		  String boardimg=freeVO.getImgName();
	        try {
	           conn=dataFactory.getConnection();
	           String query="update freeboardtbl set boardName=?,boardContent=?";
	           if(boardimg !=null && boardimg.length() !=0) {
	              query+=",imgName=?";
	           }
	           query+=" where boardNo=?";
	           pstmt=conn.prepareStatement(query);
	           pstmt.setString(1, boardName);
	           pstmt.setString(2, boardContent);
	           if(boardimg !=null && boardimg.length() !=0) {
	              pstmt.setString(3, boardimg);
	              pstmt.setInt(4, boardNo);
	           }else {
	              pstmt.setInt(3, boardNo);
	           }
	           pstmt.executeUpdate();
	           pstmt.close();
	           conn.close();
	        } catch (Exception e) {
	           System.out.println(" 글 수정 중 에러");
	           e.printStackTrace();
	        }
		  
		  /* try {
		 conn=dataFactory.getConnection();
		 String query = "update freeboardtbl set boardName=?, boardContent=? where boardNo=?";
		 pstmt=conn.prepareStatement(query);
		 pstmt.setString(1, boardName);
		 pstmt.setString(2, boardContent);
		 pstmt.setInt(3, boardNo);
		 pstmt.executeUpdate();
		 pstmt.close();
		 conn.close();
		 
		 
		 
	 }  catch (Exception e) { 
		 e.printStackTrace(); 
		 System.out.println("진짜 업데이트 하는 중 에러 ");
	 	}*/
	  }

	  public void removeBoard(FreeBoardVO freeVO) {
		  int boardNo =freeVO.getBoardNo();
		  try {
			conn=dataFactory.getConnection();
			String query = "DELETE FROM freeboardtbl WHERE boardNo =?";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("글 삭제도중 에러");
		}
		  
	  	}

	//전체 게시글 수 
		public int getAllCount() {
			int count=0;
			try {
				conn=dataFactory.getConnection();
				String sql="select count(*) from board";
				pstmt=conn.prepareStatement(sql);
				//쿼리 실행후 결과 리턴
				ResultSet rs =pstmt.executeQuery();
				while(rs.next()) {
					count=rs.getInt(1); // 전체 게시글 수
				}
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return count;
		}
		// 답글 저장
	  public void replyBoard(FreeBoardReplyVO replyVO){
		  int boardNo = replyVO.getBoardNo();
		  String content=replyVO.getContent();
		  String id = replyVO.getId();
		  
		  try {
			conn=dataFactory.getConnection();
			String query="insert into freeboardreplytbl (replyNO,boardNo,id,content,regdate) values(replyno_seq.nextval,?,?,?,sysdate)";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			pstmt.setString(2, id);
			pstmt.setString(3, content);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			
					
		} catch (Exception e) {
			System.out.println("댓글 쓰는 중 에러");
		}
	  }
	  
	  public List<FreeBoardReplyVO> selectreplyBoard(int boardNo){
		  List<FreeBoardReplyVO> list = new ArrayList<>();
		  try {
			conn=dataFactory.getConnection();
			String query="select * from freeboardreplytbl where boardNo = ? order by replyNo desc";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				FreeBoardReplyVO replyVO = new FreeBoardReplyVO();
				replyVO.setContent(rs.getString("content"));
				replyVO.setId(rs.getString("id"));
				list.add(replyVO);
			}
		} catch (Exception e) {
			System.out.println("댓글 조회 중 에러 ");
			e.printStackTrace();
		}
		  return list;
	  }
	  
}
	  
