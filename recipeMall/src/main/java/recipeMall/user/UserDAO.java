package recipeMall.user;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UserDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public UserDAO() {
		try {
			Context ctx=new InitialContext();
			Context envContext=(Context)ctx.lookup("java:/comp/env");
			dataFactory=(DataSource)envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			System.out.println("DB연결 오류");
		}
	}
	
	// 로그인 시 아이디,비밀번호 일치 확인
	public boolean loginCheck(String id, String pw) {
		boolean result=false;
		try {
			conn=dataFactory.getConnection();
			// 받은 id,pw가 데이터와 일치할 경우 true, 불일치할 경우 false를 result 이름으로 반환
			String query="select decode(count(*), 1, 'true','false') as result from usertbl where id=? and pw=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			result=Boolean.parseBoolean(rs.getString("result"));
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("로그인 아이디/비밀번호 확인 중 오류");
			e.printStackTrace();
		}
		return result;
	}
	
	// 아이디 중복 확인
	public Boolean idExisted(String id) {
		Boolean result=false;
		try {
			conn=dataFactory.getConnection();
			String query="select decode(count(*),1,'true','false') as result from usertbl where id=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, id);
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			result=Boolean.parseBoolean(rs.getString("result"));
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("아이디 중복 확인 여부 처리 중 에러!!");
			e.printStackTrace();
		}
		return result;
	}
	
	// 회원가입 + 추천인 x
	public void addUser(UserVO userVO) {
		try {
			conn=dataFactory.getConnection();
			String query="insert into usertbl(id,pw,name,nikName,phone,userAddr,userAddr2) values(?,?,?,?,?,?,?)";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, userVO.getId());
			pstmt.setString(2, userVO.getPw());
			pstmt.setString(3, userVO.getName());
			pstmt.setString(4, userVO.getNikName());
			pstmt.setString(5, userVO.getPhone());
			pstmt.setString(6, userVO.getUserAddr());
			pstmt.setString(7, userVO.getUserAddr2());
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("회원 등록 중 오류 발생!!");
			e.printStackTrace();
		}
	}
	
	// 회원가입 + 추천인 o
	public void addUser2(UserVO userVO) {
		try {
			conn=dataFactory.getConnection();
			String query="insert into usertbl(id,pw,name,nikName,phone,userAddr,userAddr2,rcmd) values(?,?,?,?,?,?,?,?)";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, userVO.getId());
			pstmt.setString(2, userVO.getPw());
			pstmt.setString(3, userVO.getName());
			pstmt.setString(4, userVO.getNikName());
			pstmt.setString(5, userVO.getPhone());
			pstmt.setString(6, userVO.getUserAddr());
			pstmt.setString(7, userVO.getUserAddr2());
			pstmt.setString(8, userVO.getRcmd());
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("회원 등록 중 오류 발생!!");
			e.printStackTrace();
		}
	}
	
	// 모든 회원 정보 조회
	public List<UserVO> selectAllUsers() {
		List<UserVO> userList=new ArrayList<>();
		try {
			conn=dataFactory.getConnection();
			String query="select * from usertbl";
			pstmt=conn.prepareStatement(query);
			ResultSet rs=pstmt.executeQuery();
			while (rs.next()) {
				String id=rs.getNString("id");
				String pw=rs.getNString("pw");
				String name=rs.getNString("name");
				String nikName=rs.getNString("nikName");
				String phone=rs.getNString("phone");
				String userAddr=rs.getNString("userAddr");
				String userAddr2=rs.getNString("userAddr2");
				Date joinDate=rs.getDate("joinDate");
				String rcmd;
				if(rs.getInt("hasRcmd")==1) {	// 추천인 있을 경우
					rcmd=rs.getString("rcmd");
					UserVO userVO=new UserVO(id, pw, name, nikName, rcmd, phone, userAddr, userAddr2);
				}
				// 추천인 없을 경우
				UserVO userVO=new UserVO(id, pw, name, nikName, phone, userAddr, userAddr2);
				userVO.setJoinDate(joinDate);
				
				userList.add(userVO);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("모든 회원 정보 조회 중 오류 발생!!");
			e.printStackTrace();
		}
		return userList;
	}
	
	// 회원 정보 조회
	public UserVO userInfo(String id) {
		UserVO userVO=new UserVO();
		try {
			conn=dataFactory.getConnection();
			String query="select * from usertbl where id=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, id);
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			userVO.setId(rs.getString("id"));
			userVO.setPw(rs.getString("pw"));
			userVO.setNikName(rs.getString("nikName"));
			userVO.setPhone(rs.getString("phone"));
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("회원 정보 조회 중 오류 발생");
			e.printStackTrace();
		}
		return userVO;
	}
	
	// 회원정보 수정
	public void updateUser(UserVO userVO) {
		String id=userVO.getId();
		String nikName=userVO.getNikName();
		String pw=userVO.getPw();
		String phone=userVO.getPhone();
		try {
			conn=dataFactory.getConnection();
			String query="update usertbl set pw=?, nikName=?, phone=? where id=?";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, pw);
			pstmt.setString(2, nikName);
			pstmt.setString(3, phone);
			pstmt.setString(4, id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("회원 정보 수정 중 오류 발생");
			e.printStackTrace();
		}
	}
	
	// 회원정보 삭제
	public void deleteUser(String id) {
		try {
			conn=dataFactory.getConnection();
			String query="delete from usertbl where id=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.executeQuery();
			
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("회원 정보 삭제 중 오류 발생");
			e.printStackTrace();
		}
		
	}
	
	public String findId(String name, String phone) {
		String id ="";
		try {
			conn=dataFactory.getConnection();
			String query="select id from usertbl where name=? AND phone=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			id = rs.getString("id");
			rs.close();
	        pstmt.close();
	        conn.close();
		}catch (Exception e) {
			System.out.println("아이디 찾는 중 오류 발생");
			e.printStackTrace();
		}
		return id;
	}
	
	public String findPw(String id, String name, String phone) {
		String check ="";
		try {
			conn=dataFactory.getConnection();
			String query="select id from usertbl where id=? AND name=? AND phone=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, phone);
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			check = rs.getString("id");
			rs.close();
	        pstmt.close();
	        conn.close();
		}catch (Exception e) {
			System.out.println("비밀번호 체크 중 오류 발생");
			e.printStackTrace();
		}
		return check;
	}
	
	public void updatePw(String id, String pw) {
		try {
			conn=dataFactory.getConnection();
			String query="UPDATE usertbl SET pw = ? WHERE id = ?";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, pw);
			pstmt.setString(2, id);
			pstmt.executeQuery();			
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("비밀번호 바꾸는 중 오류 발생");
			e.printStackTrace();
		}
		
	}

}
