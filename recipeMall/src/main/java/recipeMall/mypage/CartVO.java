package recipeMall.mypage;

import java.sql.Date;

public class CartVO {
	private int prodCode;
	private String id;
	private int cartCount;
	private Date cartDate;
	private int prodPrice;
	private String prodImage;
	private String prodName;
	
	public CartVO() {
		// TODO Auto-generated constructor stub
	}
	
	

	public CartVO(int prodCode, String id, int cartCount, Date cartDate, int prodPrice, String prodImage,
			String prodName) {
		super();
		this.prodCode = prodCode;
		this.id = id;
		this.cartCount = cartCount;
		this.cartDate = cartDate;
		this.prodPrice = prodPrice;
		this.prodImage = prodImage;
		this.prodName = prodName;
	}

	

	public CartVO(int prodCode, int cartCount, int prodPrice, String prodImage, String prodName) {
		this.prodCode = prodCode;
		this.cartCount = cartCount;
		this.prodPrice = prodPrice;
		this.prodImage = prodImage;
		this.prodName = prodName;
	}



	public int getProdCode() {
		return prodCode;
	}

	public void setProdCode(int prodCode) {
		this.prodCode = prodCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCartCount() {
		return cartCount;
	}

	public void setCartCount(int cartCount) {
		this.cartCount = cartCount;
	}

	public Date getCartDate() {
		return cartDate;
	}

	public void setCartDate(Date cartDate) {
		this.cartDate = cartDate;
	}

	public int getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(int prodPrice) {
		this.prodPrice = prodPrice;
	}

	public String getProdImage() {
		return prodImage;
	}

	public void setProdImage(String prodImage) {
		this.prodImage = prodImage;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	
	
	
}