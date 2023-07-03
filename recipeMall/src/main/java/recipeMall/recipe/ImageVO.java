package recipeMall.recipe;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

public class ImageVO {
	private int imgNum;
	private int imgCate;
	private String imgName;
	private String id;
	private int target;
	private int stepNum;
	List<ImageVO> imgList;

	public ImageVO() {

	}

	public ImageVO(int imgNum, int imgCate, String imgName, String id, int target, int stepNum, List<ImageVO> imgList) {
		super();
		this.imgNum = imgNum;
		this.imgCate = imgCate;
		this.imgName = imgName;
		this.id = id;
		this.target = target;
		this.stepNum = stepNum;
		this.imgList = imgList;
	}

	public int getImgNum() {
		return imgNum;
	}

	public void setImgNum(int imgNum) {
		this.imgNum = imgNum;
	}

	public int getImgCate() {
		return imgCate;
	}

	public String getImgName() {
		try {
			if (imgName != null && imgName.length() != 0) {
				imgName = URLDecoder.decode(imgName, "utf-8");
			}
		} catch (UnsupportedEncodingException e) {
			System.out.println("이미지 읽어오는 중 에러");
		}

		return imgName;
	}

	public void setImgName(String imgName) {
		try {
			if (imgName != null && imgName.length() != 0) {
				this.imgName = URLEncoder.encode(imgName, "utf-8");
			} else {
				this.imgName = null;
			}
		} catch (UnsupportedEncodingException e) {
			System.out.println("이미지 저장 중 에러");
		}
	}

	public void setImgCate(int imgCate) {
		this.imgCate = imgCate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	public List<ImageVO> getImgList() {
		return imgList;
	}

	public void setImgList(List<ImageVO> imgList) {
		this.imgList = imgList;
	}

	public int getStepNum() {
		return stepNum;
	}

	public void setStepNum(int stepNum) {
		this.stepNum = stepNum;
	}

}
