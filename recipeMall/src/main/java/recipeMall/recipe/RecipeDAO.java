package recipeMall.recipe;

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
import javax.swing.plaf.synth.SynthStyleFactory;

import recipeMall.user.UserVO;

public class RecipeDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;

	public RecipeDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			System.out.println("DB 연결 오류");
		}
	}

	// 다합친거 - list :recipe
	public List<RecipeVO> selectAllRecipe(Map<String, String> pagingMap) {
		List<RecipeVO> recipeList = new ArrayList<RecipeVO>();

		int section = Integer.parseInt(pagingMap.get("section"));
		int pageNum = Integer.parseInt(pagingMap.get("pageNum"));
		String cate_ = pagingMap.get("cate");
		String recipeTitle_ = pagingMap.get("recipeTitle");
		String recipeId_ = pagingMap.get("recipeId");

		if (cate_.equals("값이 없음") && recipeTitle_.equals("값이 없음") && recipeId_.equals("값이 없음")) {
			cate_ = "ko";
		}
		try {
			conn = dataFactory.getConnection();
			String query;
			ResultSet rs;

			// 제목검색

			if (cate_.equals("값이 없음") && recipeId_.equals("값이 없음")) {

				query = "SELECT * FROM (SELECT rownum as rn, re.recipeNo, re.recipeTitle, re.cate, re.id, re.cntLike, im.imgName FROM recipetbl re JOIN imagetbl im ON re.recipeNo = im.target"
						+ " WHERE im.imgCate = 1 AND re.recipeTitle LIKE ? ORDER BY recipeNo) WHERE rn BETWEEN ((? - 1) * 120 + (? - 1) * 12 + 1) AND ((? - 1) * 120 + ? * 12)";
				System.out.println(query);
				System.out.println("recipeTitle : " + recipeTitle_);
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "%" + recipeTitle_ + "%");
				pstmt.setInt(2, section);
				pstmt.setInt(3, pageNum);
				pstmt.setInt(4, section);
				pstmt.setInt(5, pageNum);
				// 카테고리 선택
			} else if (recipeTitle_.equals("값이 없음") && recipeId_.equals("값이 없음")) {
				System.out.println("section : " + section);
				System.out.println("pageNum : " + pageNum);
				System.out.println("cate : " + cate_);
				query = "SELECT * FROM (SELECT rownum as rn, re.recipeNo, re.recipeTitle, re.cate, re.id, re.cntLike, im.imgName FROM recipetbl re JOIN imagetbl im ON re.recipeNo = im.target WHERE im.imgCate = 1 AND re.cate = ? ORDER BY recipeNo) WHERE rn BETWEEN ((? - 1) * 120 + (? - 1) * 12 + 1) AND ((? - 1) * 120 + ? * 12)";

				System.out.println(query);
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, cate_);
				pstmt.setInt(2, section);
				pstmt.setInt(3, pageNum);
				pstmt.setInt(4, section);
				pstmt.setInt(5, pageNum);

				// 아이디검색
			} else if (recipeTitle_.equals("값이 없음") && cate_.equals("값이 없음")) {
				query = "SELECT * FROM (SELECT rownum as rn, re.recipeNo, re.recipeTitle, re.cate, re.id, re.cntLike, im.imgName FROM recipetbl re JOIN imagetbl im ON re.recipeNo = im.target WHERE im.imgCate = 1 AND re.id = ? ORDER BY recipeNo) WHERE rn BETWEEN ((? - 1) * 120 + (? - 1) * 12 + 1) AND ((? - 1) * 120 + ? * 12)";
				System.out.println(query);
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, recipeId_);
				pstmt.setInt(2, section);
				pstmt.setInt(3, pageNum);
				pstmt.setInt(4, section);
				pstmt.setInt(5, pageNum);
			}

			rs = pstmt.executeQuery();
			while (rs.next()) {
				int recipeNo = rs.getInt("recipeNo");
				String recipeTitle = rs.getString("recipeTitle");
				String imgName = rs.getString("imgName");
				int cntLike = rs.getInt("cntLike");
				String cate = rs.getString("cate");
				String id = rs.getString("id");
				RecipeVO recipeVO = new RecipeVO();
				ImageVO imgVO = new ImageVO();
				recipeVO.setRecipeNo(recipeNo);
				recipeVO.setRecipeTitle(recipeTitle);
				recipeVO.setCntLike(cntLike);
				recipeVO.setId(id);
				imgVO.setImgName(imgName);
				recipeVO.setImgVO(imgVO);
				recipeList.add(recipeVO);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("레시피 목록 조회 중 에러");
			e.printStackTrace();
		}
		return recipeList;
	}

	// 베스트 받아오는 메서드
	public List<RecipeVO> selectbest() {
		List<RecipeVO> bestList = new ArrayList<>();
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT re.recipeNo, re.recipeTitle, re.id, re.cntLike, im.imgName FROM recipetbl re INNER JOIN imagetbl im ON re.recipeNo = im.target WHERE im.imgCate = 1 ORDER BY re.cntlike DESC";
			PreparedStatement pstmt = conn.prepareStatement(query); // PreparedStatement 생성
			ResultSet rs = pstmt.executeQuery(); // 쿼리 실행
			while (rs.next()) {
				int recipeNo = rs.getInt("recipeNo");
				String recipeTitle = rs.getString("recipeTitle");
				String imgName = rs.getString("imgName");
				int cntLike = rs.getInt("cntLike");
				String id = rs.getString("id");
				RecipeVO recipeVO = new RecipeVO();
				ImageVO imgVO = new ImageVO();
				recipeVO.setRecipeNo(recipeNo);
				recipeVO.setRecipeTitle(recipeTitle);
				recipeVO.setCntLike(cntLike);
				recipeVO.setId(id);
				imgVO.setImgName(imgName);
				recipeVO.setImgVO(imgVO);
				bestList.add(recipeVO);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bestList;
	}

	// 다 합친거 -레시피목록수
	public int selectToRecipe(String cate, String recipeTitle, String recipeId) {
		int totCount = 0;
		try {
			conn = dataFactory.getConnection();
			String query;
			if (cate.equals("값이 없음") && recipeId.equals("값이 없음")) {
				query = "select count(*) from recipetbl where recipeTitle like ?";
				System.out.println(query);
				System.out.println("title : " + recipeTitle);
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "%" + recipeTitle + "%");
				System.out.println(query);
			} else if (recipeTitle.equals("값이 없음") && recipeId.equals("값이 없음")) {
				query = "select count(*) from recipetbl where cate=?";
				System.out.println(query);
				System.out.println("cate : " + cate);
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, cate);
				System.out.println(query);
			} else if (recipeTitle.equals("값이 없음") && cate.equals("값이 없음")) {
				query = "select count(*) from recipetbl where id=?";
				System.out.println(query);
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, recipeId);
				System.out.println(query);
				/*
				 * 작성자만 정확히 검색할 거면 위에 query 포함된 모든 작성자 검색할거면 밑에 궈리 query =
				 * "select count(*) from recipetbl where id like ?"; pstmt =
				 * conn.prepareStatement(query); pstmt.setString(1, "%" + recipeId + "%");
				 */

			}
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				totCount = rs.getInt(1);
			}
			pstmt.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("전체 글 목록 수 처리중 에러");
			e.printStackTrace();
		}
		return totCount;
	}

	// 글번호 생성 메소드 여기서 글번호 생성한다. - 필요한 메소드인지 확인하기 
	public int getNewRecipeNo() {
		int _recipeNo = 0;
		try {
			conn = dataFactory.getConnection();
			// max함수를 이용해서 가장 큰 번호를 조회
			String query = "select max(recipeNo) from recipetbl";
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				_recipeNo = rs.getInt(1) + 1;
			} else {
				_recipeNo=1;
			}
			rs.close();
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			System.out.println("글번호 생성중 에러!!");
			e.printStackTrace();
		}
		return _recipeNo;
	}

	// 새 글 추가 - recipetbl 추가
	public int insertNewRecipetbl(RecipeVO recipeVO) {
		int recipeNo = getNewRecipeNo();
		try {
			conn = dataFactory.getConnection();
			String title = recipeVO.getRecipeTitle();
			String intro = recipeVO.getRecipeIntro();
			String videolink = recipeVO.getVideoLink();
			String cate = recipeVO.getCate();
			String tip = recipeVO.getRecipeTip();
			String id = recipeVO.getId();
			int cntLike = recipeVO.getCntLike();

			String recipetbl_query = "insert into recipetbl(recipeNo, recipeTitle, recipeIntro, videoLink, cate, recipeTip, id, cntLike) values(?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(recipetbl_query);
			pstmt.setInt(1, recipeNo);
			pstmt.setString(2, title);
			pstmt.setString(3, intro);
			pstmt.setString(4, videolink);
			pstmt.setString(5, cate);
			pstmt.setString(6, tip);
			pstmt.setString(7, id);
			pstmt.setInt(8, cntLike);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();

			System.out.println("recipetbl 추가 완료");
		} catch (Exception e) {
			System.out.println("recipetbl 추가중 에러!!");
			e.printStackTrace();
		}
		return recipeNo;
	}

	// 새 글 추가 - recipeInfotbl 추가
	public void insertNewRecipeInfotbl(RecipeVO recipeVO, int recipeNo) {
		String serv = recipeVO.getInfoVO().getInfoServ();
		String time = recipeVO.getInfoVO().getInfoTime();
		String diff = recipeVO.getInfoVO().getInfoDiff();
		try {
			conn = dataFactory.getConnection();
			// (SELECT COALESCE(MAX(infoNo), 1) => infoNo 컬럼에 아무값도 없을땐 1을 넣는다
			String infotbl_query = "INSERT INTO recipeInfotbl (recipeInfoNo, recipeNo, infoServ, infoTime, infoDiff) VALUES ((SELECT COALESCE(MAX(recipeInfoNo) + 1 , 1)  FROM recipeInfotbl),?, ?, ?, ?)";
			pstmt = conn.prepareStatement(infotbl_query);
			pstmt.setInt(1, recipeNo);
			pstmt.setString(2, serv);
			pstmt.setString(3, time);
			pstmt.setString(4, diff);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			System.out.println("recipeinfotbl 추가 완료");
		} catch (Exception e) {
			System.out.println("infotbl추가중 에러");
			e.printStackTrace();
		}

	}

	// 새 글 추가 - matetypetbl 추가
	public void insertNewMateTypetbl(RecipeVO recipeVO, int recipeNo) {
		List<MaterialtypeVO> MaterialtypeList = recipeVO.getMatetypeVO().getMaterialtypeList();
		try {
			conn = dataFactory.getConnection();
			for (int i = 0; i < MaterialtypeList.size(); i++) {
				String mateType_query = "insert into materialTypetbl(matetypeNo ,recipeNo, mateTypeName ) values  (?,?, ?)";
				pstmt = conn.prepareStatement(mateType_query);
				pstmt.setInt(1, MaterialtypeList.get(i).getMatetypeNo());
				pstmt.setInt(2, recipeNo);
				pstmt.setString(3, MaterialtypeList.get(i).getMatetypeName());
				pstmt.executeUpdate();
			}
			pstmt.close();
			conn.close();
			System.out.println("matetypetbl 추가 완료");
		} catch (Exception e) {
			System.out.println("matetypetbl추가중 에러");
			e.printStackTrace();
		}
	}

	// 새 글 추가 - matetbl 추가
	public void insertNewMatetbl(RecipeVO recipeVO, int recipeNo) {
		List<MaterialVO> MaterialList = recipeVO.getMateVO().getMaterialList();
		try {
			conn = dataFactory.getConnection();
			for (int i = 0; i < MaterialList.size(); i++) {
				String mate_query = "insert into materialtbl(mateNo,recipeNo,matetypeNo,mateName,mateAmount) values ((SELECT COALESCE(MAX(mateNo)+ 1 , 1) FROM materialtbl), ?, ?, ?, ?)";
				pstmt = conn.prepareStatement(mate_query);
				pstmt.setInt(1, recipeNo);
				pstmt.setInt(2, MaterialList.get(i).getMatetypeNo());
				pstmt.setString(3, MaterialList.get(i).getMateName());
				pstmt.setString(4, MaterialList.get(i).getMateAmount());
				pstmt.executeUpdate();
			}
			pstmt.close();
			conn.close();
			System.out.println("matetbl 추가 완료");
		} catch (Exception e) {
			System.out.println("materialtbl추가중 에러");
			e.printStackTrace();
		}
	}

	// 새 글 추가 - steptbl 추가
	public void insertNewSteptbl(RecipeVO recipeVO, int recipeNo) {
		int stepSe = recipeVO.getStepVO().getStepSe();
		List<RecipeStepVO> recipeStepList = recipeVO.getStepVO().getRecipeStepList();
		try {
			for (int i = 0; i < recipeStepList.size(); i++) {
				conn = dataFactory.getConnection();
				String step_query = "insert into recipeSteptbl(recipeStepNo,recipeNo,stepSe,stepEx) values ((SELECT COALESCE(MAX(recipeStepNo)+ 1, 1)  FROM recipeSteptbl),?,?,?)";
				pstmt = conn.prepareStatement(step_query);
				pstmt.setInt(1, recipeNo);
				pstmt.setInt(2, i + 1);
				pstmt.setString(3, recipeStepList.get(i).getStepEx());
				pstmt.executeUpdate();
			}

			pstmt.close();
			conn.close();
			System.out.println("steptbl 추가 완료");
		} catch (Exception e) {
			System.out.println("steptbl추가중 에러");
			e.printStackTrace();
		}

	}

	// 새글 추가 - imgtbl
	public void insertImgtbl(RecipeVO recipeVO, int recipeNo) {
		String id = recipeVO.getId();
		List<ImageVO> ImgList = recipeVO.getImgVO().getImgList();
		try {
			for (int i = 0; i < ImgList.size(); i++) {
				conn = dataFactory.getConnection();
				String img_query = "insert into imagetbl(imgNum,imgCate,imgName,id,target,stepNum) values ((SELECT COALESCE(MAX(imgNum)+ 1, 1)  FROM imagetbl),?,?,?,?,?)";
				pstmt = conn.prepareStatement(img_query);
				pstmt.setInt(1, ImgList.get(i).getImgCate());
				pstmt.setString(2, ImgList.get(i).getImgName());
				pstmt.setString(3, id);
				pstmt.setInt(4, recipeNo);
				pstmt.setInt(5, ImgList.get(i).getStepNum());
				pstmt.executeUpdate();
			}
			pstmt.close();
			conn.close();
			System.out.println("imgtbl 추가 완료");
		} catch (Exception e) {
			System.out.println("imgtbl추가중 에러");
			e.printStackTrace();
		}

	}

	// *****상세보기 시작
	// 레시피 상세보기 - recipetbl
	public RecipeVO selectRecipetbl(int recipeNo) {
		RecipeVO recipeVO = new RecipeVO();
		try {
			System.out.println("보여줄 recipeNo:" + recipeNo);
			conn = dataFactory.getConnection();
			String query = "select recipeNo, recipeTitle, recipeIntro, videoLink, cate, recipeTip, id, writeDate, cntLike from recipetbl where recipeNo=?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, recipeNo);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int _recipeNo = rs.getInt("recipeNo");
			String title = rs.getString("recipeTitle");
			String intro = rs.getString("recipeIntro");
			String videoLink = rs.getString("videoLink");
			String cate = rs.getString("cate");
			String tip = rs.getString("recipeTip");
			String id = rs.getString("id");
			Date writeDate = rs.getDate("writeDate");
			int cntLike = rs.getInt("cntLike");

			recipeVO.setRecipeNo(_recipeNo);
			recipeVO.setRecipeTitle(title);
			recipeVO.setRecipeIntro(intro);
			recipeVO.setVideoLink(videoLink);
			recipeVO.setCate(cate);
			recipeVO.setRecipeTip(tip);
			recipeVO.setId(id);
			recipeVO.setWriteDate(writeDate);
			recipeVO.setCntLike(cntLike);

			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("recipetbl 상세 구현 중 에러!!");
			e.printStackTrace();
		}
		return recipeVO;
	}

	// 레시피 상세보기 - infotbl
	public RecipeInfoVO selectInfotbl(int recipeNo) {
		RecipeInfoVO infoVO = new RecipeInfoVO();
		try {
			conn = dataFactory.getConnection();
			String query = "select recipeNo, recipeInfoNo, infoServ, infoTime, infoDiff from recipeInfotbl where recipeNo=?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, recipeNo);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int _recipeNo = rs.getInt("recipeNo");
			String infoServ = rs.getString("infoServ");
			String infoTime = rs.getString("infoTime");
			String infoDiff = rs.getString("infoDiff");

			infoVO.setInfoServ(infoServ);
			infoVO.setInfoTime(infoTime);
			infoVO.setInfoDiff(infoDiff);
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("infotbl 상세 구현 중 에러!!");
			e.printStackTrace();
		}
		return infoVO;
	}

	// 레시피 상세보기 - matetypetbl
	public MaterialtypeVO selectMatetypetbl(int recipeNo) {
		List<MaterialtypeVO> MaterialtypeList = new ArrayList<>();
		MaterialtypeVO matetypeVO = new MaterialtypeVO();
		try {
			conn = dataFactory.getConnection();
			String query = "select recipeNo, matetypeNo, matetypeName from materialTypetbl where recipeNo=?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, recipeNo);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				MaterialtypeVO TempmatetypeVO = new MaterialtypeVO();
				TempmatetypeVO.setRecipeNo(recipeNo);
				TempmatetypeVO.setMatetypeNo(rs.getInt("matetypeNo"));
				TempmatetypeVO.setMatetypeName(rs.getString("matetypeName"));
				MaterialtypeList.add(TempmatetypeVO);
			}
			matetypeVO.setMaterialtypeList(MaterialtypeList);
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("matetypetbl 상세 구현 중 에러!!");
			e.printStackTrace();
		}
		return matetypeVO;
	}

	// 레시피 상세보기 - matetbl
	public MaterialVO selectMatetbl(int recipeNo) {
		List<MaterialVO> mateVoList = new ArrayList<>();
		MaterialVO mateVO = new MaterialVO();
		try {
			conn = dataFactory.getConnection();
			String query = "select recipeNo, matetypeNo, mateName, mateAmount from materialtbl where recipeNo=? ";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, recipeNo);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				MaterialVO TempmateVO = new MaterialVO();
				TempmateVO.setRecipeNo(recipeNo);
				TempmateVO.setMatetypeNo(rs.getInt("matetypeNo"));
				TempmateVO.setMateName(rs.getString("mateName"));
				TempmateVO.setMateAmount(rs.getString("mateAmount"));
				mateVoList.add(TempmateVO);
			}
			mateVO.setMaterialList(mateVoList);
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("matetbl 상세 구현 중 에러!!");
			e.printStackTrace();
		}
		return mateVO;
	}

	// 레시피 상세보기 - steptbl
	public RecipeStepVO selectSteptbl(int recipeNo) {
		List<RecipeStepVO> recipeStepList = new ArrayList<>();
		RecipeStepVO stepVO = new RecipeStepVO();
		try {
			conn = dataFactory.getConnection();
			String query = "select recipeStepNo, stepSe, recipeNo, stepEx from recipeSteptbl where recipeNo=? ";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, recipeNo);
			ResultSet rs = pstmt.executeQuery();
			List<String> stepEx = new ArrayList<>();
			while (rs.next()) {
				RecipeStepVO TempstepVO = new RecipeStepVO();
				TempstepVO.setRecipeNo(recipeNo);
				TempstepVO.setRecipeStepNo(rs.getInt("recipeStepNo"));
				TempstepVO.setStepEx(rs.getString("stepEx"));
				TempstepVO.setStepSe(rs.getInt("stepSe"));
				recipeStepList.add(TempstepVO);
			}
			stepVO.setRecipeStepList(recipeStepList);
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("steptbl 상세 구현 중 에러!!");
			e.printStackTrace();
		}
		return stepVO;
	}

	// 레시피 상세보기 - imagetbl
	public ImageVO selectImgtbl(int recipeNo) {

		List<ImageVO> ImgList = new ArrayList<>();

		ImageVO imgVO = new ImageVO();
		try {
			conn = dataFactory.getConnection();
			String query = "select imgNum, imgCate, imgName, stepNum from imagetbl where target=?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, recipeNo);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ImageVO TempimgVO = new ImageVO();
				TempimgVO.setTarget(recipeNo);
				TempimgVO.setImgNum(rs.getInt("imgNum"));
				TempimgVO.setImgCate(rs.getInt("imgCate"));
				TempimgVO.setImgName(rs.getString("imgName"));
				TempimgVO.setStepNum(rs.getInt("stepNum"));
				ImgList.add(TempimgVO);
			}
			imgVO.setImgList(ImgList);
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("imgtbl 상세 구현 중 에러!!");
			e.printStackTrace();
		}
		return imgVO;
	}

	// matetypeno 생성 메소드
	public int selectMaxTypeNo() {
		int num = 0;

		try {
			conn = dataFactory.getConnection();
			String query = "SELECT COALESCE(matetypeNo + 1, 1) AS maxNo FROM materialTypetbl";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				num = rs.getInt("maxNo");
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("selectMaxTypeNo 상세 구현 중 에러!!");
			e.printStackTrace();
		}

		return num;
	}

	// 글 상세보기 - liketbl
	public LikeVO selectLike(String id, int recipeNo) {
		LikeVO likeVO = new LikeVO();
		try {
			conn = dataFactory.getConnection();
			String query = "select likeValue from liketbl where id=? and recipeNo=?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setInt(2, recipeNo);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int likeValue = rs.getInt("likeValue");
				likeVO.setLikeValue(likeValue);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("likeValue가져오던중에러");
			e.printStackTrace();
		}
		return likeVO;
	}

	// 좋아요 정보를 liketbl에 삽입하는 메소드 - likeValue= 1
	public void insertLike(int recipeNo, String id) {
		try {
			conn = dataFactory.getConnection();
			String query = "INSERT INTO liketbl (likeNo, recipeNo, id,likeValue) VALUES ((SELECT COALESCE(MAX(likeNo) + 1 , 1)  FROM liketbl), ?, ?,1)";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, recipeNo);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("DAO에 insertLike 메소드 에러");
			e.printStackTrace();
		}
	}

	// 좋아요 정보를 liketbl에서 수정하는 메소드 (좋아요 취소했다가 다시 누르는 사람) - likeValue= 1
	public void updateLike(int recipeNo, String id) {
		try {
			conn = dataFactory.getConnection();
			String query = "UPDATE liketbl SET likeValue = 1 WHERE recipeNo = ? AND id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, recipeNo);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("DAO에 updateLike 메소드 에러");
			e.printStackTrace();
		}
	}

	// cntLike 더하기
	public void plusCntLike(int recipeNo, int cntLike) {
		try {
			conn = dataFactory.getConnection();
			String query = "UPDATE recipetbl SET cntLike = ? where recipeNo = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cntLike + 1);
			pstmt.setInt(2, recipeNo);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			System.out.println();
		} catch (Exception e) {
			System.out.println("DAO에 plusCntLike 메소드 에러");
			e.printStackTrace();
		}
	}

	// 좋아요 정보를 liketbl에서 삭제하는 메소드 -- 좋아요 안누름 likevalue= 2
	public void deleteLike(int recipeNo, String id) {
		try {
			conn = dataFactory.getConnection();
			String query = "UPDATE liketbl SET likeValue = 2 WHERE recipeNo = ? AND id = ?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, recipeNo);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("DAO에 deleteLike 메소드 에러");
			e.printStackTrace();
		}
	}

	// cntLike 빼기
	public void minusCntLike(int recipeNo, int cntLike) {
		try {
			conn = dataFactory.getConnection();
			String query = "UPDATE recipetbl SET cntLike = ? WHERE recipeNo = ?";
			pstmt = conn.prepareStatement(query);
			System.out.println(query);
			cntLike -= 1; // cntLike 값을 1 감소시킴
			if (cntLike < 0) {
				cntLike = 0; // 음수 값은 0으로 설정
			}
			pstmt.setInt(1, cntLike);
			pstmt.setInt(2, recipeNo);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("DAO에 minusCntLike 메소드 에러");
			e.printStackTrace();
		}
	}

}
