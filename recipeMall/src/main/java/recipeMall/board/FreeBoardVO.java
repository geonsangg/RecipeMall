package recipeMall.board;

import java.sql.Date;

public class FreeBoardVO {
	
	private int boardNo;
	private String boardName;
	private String boardContent;
	private Date writeDate;
	private String id;
	private int cntView;
	private int rownum;
	private String imgName;
	
	public FreeBoardVO() {
		
	}
	
	

	public FreeBoardVO(int boardNo, String boardName, String boardContent, Date writeDate, String id, int cntView,
			int rownum, String imgName) {
		super();
		this.boardNo = boardNo;
		this.boardName = boardName;
		this.boardContent = boardContent;
		this.writeDate = writeDate;
		this.id = id;
		this.cntView = cntView;
		this.rownum = rownum;
		this.imgName = imgName;
	}







	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public Date getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getCntView() {
		return cntView;
	}
	public void setCntView(int cntView) {
		this.cntView = cntView;
	}



	public int getRownum() {
		return rownum;
	}



	public void setRownum(int rownum) {
		this.rownum = rownum;
	}







	public String getImgName() {
		return imgName;
	}







	public void setImgName(String imgName) {
		this.imgName = imgName;
	} 	
	
	
	
}
