package recipeMall.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class InqImgDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public InqImgDAO() {
		try {
			Context ctx=new InitialContext();
			Context envContext=(Context)ctx.lookup("java:/comp/env");
			dataFactory=(DataSource)envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			System.out.println("DB연결 오류");
		}
	}
	
	public void insertInqImg(Map<String, Object> inqMap, int inqNo, String[] imgFileNames) {
		InqueryVO inqVO=(InqueryVO)inqMap.get("inqVO");
		String id=inqVO.getId();
		int cnt=(Integer)inqMap.get("cnt");
		
		try {
			conn=dataFactory.getConnection();
			String query="insert into inqimgtbl(imgNo, id, inqNo, imgFileName) values(inqImgNo_seq.nextval, ?, ?, ?)";
			pstmt=conn.prepareStatement(query);
			for(int i=0; i<cnt; i++) {
				pstmt.setString(1, id);
				pstmt.setInt(2, inqNo);
				pstmt.setString(3, imgFileNames[i]);
				pstmt.executeUpdate();
			}
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("유저 일대일 문의 관련 이미지 등록 중 오류 발생");
			e.printStackTrace();
		}
	}
}
