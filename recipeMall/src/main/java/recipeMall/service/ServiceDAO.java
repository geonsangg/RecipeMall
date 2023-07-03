package recipeMall.service;

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

public class ServiceDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public ServiceDAO() {
		try {
			Context ctx=new InitialContext();
			Context envContext=(Context)ctx.lookup("java:/comp/env");
			dataFactory=(DataSource)envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			System.out.println("DB연결 오류");
		}
	}
	
	// 문의 번호 최대갓
	public int selectInqNo(String id) {
		int maxInqNo=0;
		try {
			conn=dataFactory.getConnection();
			String query="select max(inqNo) from inquerytbl where id=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, id);
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			maxInqNo=rs.getInt(1);
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("해당 유저의 최근 문의 번호 조회 중 오류 발생");
			e.printStackTrace();
		}
		return maxInqNo;
	}
	
	public List<FaqVO> selectFaqList() {
		List<FaqVO> faqList=new ArrayList<FaqVO>();
		try {
			conn=dataFactory.getConnection();
			String query="select faqNo,adminId,faqTitle,faqContent,faqDate from faqtbl order by faqDate Desc";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				String title=rs.getString("faqTitle");
				String content=rs.getString("faqContent");
				
				FaqVO faqVO=new FaqVO();
				faqVO.setFaqNo(rs.getInt("faqNo"));
				faqVO.setAdminId(rs.getString("adminId"));
				faqVO.setFaqTitle(title);
				faqVO.setFaqContent(content);
				faqVO.setFaqDate(rs.getDate("faqDate"));
				
				faqList.add(faqVO);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("자주하는 질문 목록 조회 중 오류 발생");
			e.printStackTrace();
		}
		return faqList;
	}
	
	// 관리자 - faq리스트 페이징
	public List<FaqVO> admFaqList(Map<String, Integer> pagingMap) {
		List<FaqVO> faqList=new ArrayList<FaqVO>();
		int section=pagingMap.get("section");	// 1
		int pageNum=pagingMap.get("pageNum");	// 1
		try {
			conn=dataFactory.getConnection();
			String query="SELECT * FROM (select ROWNUM AS recNum,faqNo,adminId,faqTitle,faqContent,faqDate from faqtbl order by faqDate Desc) "+
					 "WHERE recNum BETWEEN (?-1)*100+(?-1)*10+1 AND (?-1)*100+?*10";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, section);
			pstmt.setInt(2, pageNum);
			pstmt.setInt(3, section);
			pstmt.setInt(4, pageNum);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				String title=rs.getString("faqTitle");
				String content=rs.getString("faqContent");
				
				FaqVO faqVO=new FaqVO();
				faqVO.setFaqNo(rs.getInt("faqNo"));
				faqVO.setAdminId(rs.getString("adminId"));
				faqVO.setFaqTitle(title);
				faqVO.setFaqContent(content);
				faqVO.setFaqDate(rs.getDate("faqDate"));
				
				faqList.add(faqVO);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("자주하는 질문 목록 조회 중 오류 발생");
			e.printStackTrace();
		}
		return faqList;
	}
	
	public int selectToFaq() {
		int totCount=0;
		try {
			conn=dataFactory.getConnection();
			String query="select count(*) from faqtbl";
			pstmt=conn.prepareStatement(query);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {	// 자료가 있을 경우
				totCount=rs.getInt(1);	// 첫 번째 컬럼 값				
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("관리자 faq 전체 개수 조회 중 오류 발생");
			e.printStackTrace();
		}
		return totCount;
	}
	
	// 유저 일대일 문의 전체 개수
	public int selectToInqueries(String id) {
		int totCount=0;
		try {
			conn=dataFactory.getConnection();
			String query="select count(*) from inquerytbl where id=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, id);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {	// 자료가 있을 경우
				totCount=rs.getInt(1);	// 첫 번째 컬럼 값				
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("유저의 일대일 문의 전체 개수 조회 중 오류 발생");
			e.printStackTrace();
		}
		return totCount;
	}
	
	// 관리자 - 모든 유저 문의 개수
	public int selectToInqueries() {
		int totCount=0;
		try {
			conn=dataFactory.getConnection();
			String query="select count(*) from inquerytbl";
			pstmt=conn.prepareStatement(query);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {	// 자료가 있을 경우
				totCount=rs.getInt(1);	// 첫 번째 컬럼 값				
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("모든 유저의 일대일 문의 전체 개수 조회 중 오류 발생");
			e.printStackTrace();
		}
		return totCount;
	}
	
	// 유저 일대일 문의 목록 페이징
	public List<InqueryVO> selectUserInquery(Map<String, Integer> pagingMap, String id) {
		List<InqueryVO> inqueryList=new ArrayList<InqueryVO>();
		int section=pagingMap.get("section");	// 1
		int pageNum=pagingMap.get("pageNum");	// 1
		try {
			conn=dataFactory.getConnection();
			String query="SELECT * FROM (select ROWNUM AS recNum,inqNo,id,inqTitle,hasRe,inqCate,inqDate from inquerytbl where id=? order by inqDate Desc) "+
						 "WHERE recNum BETWEEN (?-1)*100+(?-1)*10+1 AND (?-1)*100+?*10";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setInt(2, section);
			pstmt.setInt(3, pageNum);
			pstmt.setInt(4, section);
			pstmt.setInt(5, pageNum);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				int inqNo=rs.getInt("inqNo");
				String inqTitle=rs.getString("inqTitle");
				int hasRe=rs.getInt("hasRe");
				int inqCate=rs.getInt("inqCate");
				Date inqDate=rs.getDate("inqDate");
				
				InqueryVO inqueryVO=new InqueryVO();
				inqueryVO.setInqNo(inqNo);
				inqueryVO.setId(id);
				inqueryVO.setInqTitle(inqTitle);
				inqueryVO.setHasRe(hasRe);
				inqueryVO.setInqCate(inqCate);
				inqueryVO.setInqDate(inqDate);
				
				inqueryList.add(inqueryVO);
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("유저 일대일 문의 목록 + 페이징 조회 중 오류 발생");
			e.printStackTrace();
		}
		return inqueryList;
	}
	
	// 관리자 - 모든 유저 일대일 문의 목록 페이징
	public List<InqueryVO> seleteAllUserInquery(Map<String, Integer> pagingMap) {
		List<InqueryVO> inqueryList=new ArrayList<InqueryVO>();
		int section=pagingMap.get("section");	// 1
		int pageNum=pagingMap.get("pageNum");	// 1
		try {
			conn=dataFactory.getConnection();
			String query="SELECT * FROM (select ROWNUM AS recNum,inqNo,id,inqTitle,hasRe,inqCate,inqDate from inquerytbl order by inqDate Desc) "+
						 "WHERE recNum BETWEEN (?-1)*100+(?-1)*10+1 AND (?-1)*100+?*10";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, section);
			pstmt.setInt(2, pageNum);
			pstmt.setInt(3, section);
			pstmt.setInt(4, pageNum);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				int inqNo=rs.getInt("inqNo");
				String id=rs.getString("id");
				String inqTitle=rs.getString("inqTitle");
				int hasRe=rs.getInt("hasRe");
				int inqCate=rs.getInt("inqCate");
				Date inqDate=rs.getDate("inqDate");
				
				InqueryVO inqueryVO=new InqueryVO();
				inqueryVO.setInqNo(inqNo);
				inqueryVO.setId(id);
				inqueryVO.setInqTitle(inqTitle);
				inqueryVO.setHasRe(hasRe);
				inqueryVO.setInqCate(inqCate);
				inqueryVO.setInqDate(inqDate);
				
				inqueryList.add(inqueryVO);
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("유저 일대일 문의 목록 + 페이징 조회 중 오류 발생");
			e.printStackTrace();
		}
		return inqueryList;
	}
	
	// 유저 문의 등록
	public void addInquery(InqueryVO inqVO) {
		try {
			conn=dataFactory.getConnection();
			String query="insert into inquerytbl(inqNo,id,inqTitle,inqContent,inqCate) values(inqNo_seq.nextval,?,?,?,?)";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, inqVO.getId());
			pstmt.setString(2, inqVO.getInqTitle());
			pstmt.setString(3, inqVO.getInqContent());
			pstmt.setInt(4, inqVO.getInqCate());
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("유저 일대일 문의 등록 중 오류 발생");
			e.printStackTrace();
		}
	}
	
	// 유저 문의 상세보기
	public InqueryVO inqView(String id, int inqNo) {
		InqueryVO inqVO=new InqueryVO();
		try {
			conn=dataFactory.getConnection();
			String query="select * from inquerytbl where id=? and inqNo=?";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setInt(2, inqNo);
			ResultSet rs=pstmt.executeQuery();
			
			if(rs.next()) {
				String inqTitle=rs.getString("inqTitle");
				String inqContent=rs.getString("inqContent");
				int hasRe=rs.getInt("hasRe");
				int inqCate=rs.getInt("inqCate");
				Date inqDate=rs.getDate("inqDate");
				
				inqVO.setInqTitle(inqTitle);
				inqVO.setInqContent(inqContent);
				inqVO.setHasRe(hasRe);
				inqVO.setInqReply(rs.getString("inqReply"));
				inqVO.setInqCate(inqCate);
				inqVO.setInqDate(inqDate);
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("유저 일대일 문의 상세보기 조회 중 오류 발생");
			e.printStackTrace();
		}
		return inqVO;
	}
	
	// 관리자 - 메인에 출력될 최신 문의글
	public List<InqueryVO> recentInqList() {
		List<InqueryVO> inqList=new ArrayList<>();
		try {
			conn=dataFactory.getConnection();
			String query="select inqNo, inqTitle, id, inqDate from (select inqNo, inqTitle, id, inqDate from inquerytbl order by inqDate desc) where ROWNUM <= 5";
			pstmt=conn.prepareStatement(query);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				InqueryVO inqVO=new InqueryVO();
				inqVO.setInqNo(rs.getInt("inqNo"));
				inqVO.setId(rs.getString("id"));
				inqVO.setInqTitle(rs.getString("inqTitle"));
				inqVO.setInqDate(rs.getDate("inqDate"));
				
				inqList.add(inqVO);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("관리자 메인 화면에 출력할 최신 문의 조회 중 오류 발생");
			e.printStackTrace();
		}
		return inqList;
	}
	
	// 관리자 - 선택한 유저 문의 가져옴
	public InqueryVO selectInquery(int inqNo) {
		InqueryVO inqVO=new InqueryVO();
		try {
			conn=dataFactory.getConnection();
			String query="select * from inquerytbl where inqNo=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, inqNo);
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			
			inqVO.setInqNo(inqNo);
			inqVO.setId(rs.getString("id"));
			inqVO.setInqCate(rs.getInt("inqCate"));
			inqVO.setInqTitle(rs.getString("inqTitle"));
			inqVO.setInqContent(rs.getString("inqContent"));
			inqVO.setHasRe(rs.getInt("hasRe"));
			inqVO.setInqReply(rs.getString("inqReply"));
			inqVO.setInqDate(rs.getDate("inqDate"));
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("유저 문의 개별 조회 중 오류 발생");
			e.printStackTrace();
		}
		return inqVO;
	}
	
	// 관리자 - 유저 문의 답변 등록
	public void inqReply(int inqNo, String adminId, String inqReply) {
		try {
			conn=dataFactory.getConnection();
			String query="update inquerytbl set adminId=?, inqReply=?, hasre=1 where inqNO=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, adminId);
			pstmt.setString(2, inqReply);
			pstmt.setInt(3, inqNo);
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("유저 문의 답변 중 오류 발생");
			e.printStackTrace();
		}
	}
	
	// 관리자 - 자주하는 질문 등록
	public void addFaq(FaqVO faqVO) {
		String adminId=faqVO.getAdminId();
		String faqTitle=faqVO.getFaqTitle();
		String faqContent=faqVO.getFaqContent();
		try {
			conn=dataFactory.getConnection();
			String query="insert into faqtbl values(faqno_seq.nextval,?,?,?,sysdate)";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, adminId);
			pstmt.setString(2, faqTitle);
			pstmt.setString(3, faqContent);
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("자주하는 질문 등록 중 오류 발생");
			e.printStackTrace();
		}
	}
	
	// 관리자 - faq 보기
	public FaqVO selectFaq(int faqNo) {
		FaqVO faqVO=new FaqVO();
		try {
			conn=dataFactory.getConnection();
			String query="select * from faqtbl where faqNo=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, faqNo);
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			faqVO.setFaqNo(faqNo);
			faqVO.setAdminId(rs.getString("adminId"));
			faqVO.setFaqTitle(rs.getString("faqTitle"));
			faqVO.setFaqContent(rs.getString("faqContent"));
			faqVO.setFaqDate(rs.getDate("faqDate"));
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("선택한 faq 조회 중 오류 발생");
			e.printStackTrace();
		}
		return faqVO;
	}
	
	public void updateFaq(FaqVO faqVO, int faqNo) {
		String adminId=faqVO.getAdminId();
		String faqTitle=faqVO.getFaqTitle();
		String faqContent=faqVO.getFaqContent();
		try {
			conn=dataFactory.getConnection();
			String query="update faqtbl set adminId=?, faqTitle=?, faqContent=? where faqNo=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, adminId);
			pstmt.setString(2, faqTitle);
			pstmt.setString(3, faqContent);
			pstmt.setInt(4, faqNo);
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("선택한 faq 수정 중 오류 발생");
			e.printStackTrace();
		}
	}
	
	public void deleteFaq(int faqNo) {
		try {
			conn=dataFactory.getConnection();
			String query="delete from faqtbl where faqNo=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, faqNo);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("선택한 faq 삭제 중 오류 발생");
			e.printStackTrace();
		}
	}
}
