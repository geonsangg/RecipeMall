package recipeMall.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class NoticeDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public NoticeDAO() {
		try {
			Context ctx=new InitialContext();
			Context envContext=(Context)ctx.lookup("java:/comp/env");
			dataFactory=(DataSource)envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			System.out.println("DB연결 오류");
		}
	}
	
	// 공지사항 목록 + 페이징
	public List<NoticeVO> seleteAllNotices(Map<String, Integer> pagingMap) {
		List<NoticeVO> noticeList=new ArrayList<>();
		int section=pagingMap.get("section");	// 1
		int pageNum=pagingMap.get("pageNum");	// 1
		try {
			conn=dataFactory.getConnection();
			String query="SELECT * FROM (SELECT ROWNUM AS recNum, noticeNo, adminId, noticeTitle, noticeDate "+
						 "FROM noticetbl order by noticeDate desc) "+
						 "WHERE recNum BETWEEN (?-1)*100+(?-1)*10+1 AND (?-1)*100+?*10";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, section);
			pstmt.setInt(2, pageNum);
			pstmt.setInt(3, section);
			pstmt.setInt(4, pageNum);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				NoticeVO noticeVO=new NoticeVO();
				noticeVO.setNoticeNo(rs.getInt("noticeNo"));
				noticeVO.setAdminId(rs.getString("adminId"));
				noticeVO.setNoticeTitle(rs.getString("noticeTitle"));
				noticeVO.setNoticeDate(rs.getDate("noticeDate"));
				
				noticeList.add(noticeVO);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("공지사항 목록 + 페이징 조회 중 오류 발생");
			e.printStackTrace();
		}
		return noticeList;
	}
	
	// 공지사항 전체 개수 가져오는 메소드
	public int selectToNotices() {
		int totCount=0;
		try {
			conn=dataFactory.getConnection();
			String query="select count(*) from noticetbl";
			pstmt=conn.prepareStatement(query);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {	// 자료가 있을 경우
				totCount=rs.getInt(1);	// 첫 번째 컬럼 값				
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("전체 공지사항 개수 조회 중 오류 발생");
			e.printStackTrace();
		}
		return totCount;
	}
	
	// 공지사항 상세보기
	public NoticeVO selectNoticeView(int noticeNo) {
		NoticeVO noticeVO=new NoticeVO();
		try {
			conn=dataFactory.getConnection();
			String query="select * from noticetbl where noticeNo=?";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			
			noticeVO.setNoticeNo(rs.getInt("noticeNo"));
			noticeVO.setAdminId(rs.getString("adminId"));
			noticeVO.setNoticeTitle(rs.getString("noticeTitle"));
			noticeVO.setNoticeContent(rs.getString("noticeContent"));
			noticeVO.setNoticeDate(rs.getDate("noticeDate"));
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("공지사항 상세 조회 중 오류 발생");
			e.printStackTrace();
		}
		return noticeVO;
	}
	
	// 공지사항 추가
	public void addNotice(NoticeVO noticeVO) {
		String adminId=noticeVO.getAdminId();
		String noticeTitle=noticeVO.getNoticeTitle();
		String noticeContent=noticeVO.getNoticeContent();
		try {
			conn=dataFactory.getConnection();
			String query="insert into noticetbl(noticeNo, adminId, noticeTitle, noticeContent) values(noticeNo_seq.nextval, ?,?,?)";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, adminId);
			pstmt.setString(2, noticeTitle);
			pstmt.setString(3, noticeContent);
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("공지사항 등록 중 오류 발생");
			e.printStackTrace();
		}
	}
	
	// 공지사항 수정
	public void updateNotice(NoticeVO noticeVO) {
		int noticeNo=noticeVO.getNoticeNo();
		String adminId=noticeVO.getAdminId();
		String title=noticeVO.getNoticeTitle();
		String content=noticeVO.getNoticeContent();
		try {
			conn=dataFactory.getConnection();
			String query="update noticetbl set adminId=?, noticeTitle=?, noticeContent=? where noticeNo=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, adminId);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			pstmt.setInt(4, noticeNo);
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("공지사항 수정 중 오류 발생");
			e.printStackTrace();
		}
	}
	
	// 공지사항 삭제
	public void deleteNotice(int noticeNo) {
		try {
			conn=dataFactory.getConnection();
			String query="delete from noticetbl where noticeNo=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("공지사항 삭제 중 오류 발생");
			e.printStackTrace();
		}
	}
}
