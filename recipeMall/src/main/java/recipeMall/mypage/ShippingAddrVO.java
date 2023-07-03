package recipeMall.mypage;

public class ShippingAddrVO {
	private String shippingName;
	private String ShippingTitle;
	private String id;
	private int shippingZonecode;
	private String shippingAddress;
	private String shippingAddressSub;
	private String shippingPhone;
	private String shippingCellphone;
	
	public ShippingAddrVO() {
		// TODO Auto-generated constructor stub
	}
	

	public ShippingAddrVO(String shippingName, String shippingTitle, String id, int shippingZonecode,
			String shippingAddress, String shippingAddressSub, String shippingPhone, String shippingCellphone) {
		super();
		this.shippingName = shippingName;
		ShippingTitle = shippingTitle;
		this.id = id;
		this.shippingZonecode = shippingZonecode;
		this.shippingAddress = shippingAddress;
		this.shippingAddressSub = shippingAddressSub;
		this.shippingPhone = shippingPhone;
		this.shippingCellphone = shippingCellphone;
	}


	public ShippingAddrVO(String shippingName, String shippingTitle, int shippingZonecode,
			String shippingAddress, String shippingAddressSub, String shippingPhone, String shippingCellphone) {
		
		this.shippingName = shippingName;
		this.ShippingTitle = shippingTitle;
		this.shippingZonecode = shippingZonecode;
		this.shippingAddress = shippingAddress;
		this.shippingAddressSub = shippingAddressSub;
		this.shippingPhone = shippingPhone;
		this.shippingCellphone = shippingCellphone;
	}
	


	public String getShippingName() {
		return shippingName;
	}

	public void setShippingName(String shippingName) {
		this.shippingName = shippingName;
	}

	public String getShippingTitle() {
		return ShippingTitle;
	}

	public void setShippingTitle(String shippingTitle) {
		ShippingTitle = shippingTitle;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getShippingZonecode() {
		return shippingZonecode;
	}

	public void setShippingZonecode(int shippingZonecode) {
		this.shippingZonecode = shippingZonecode;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getShippingAddressSub() {
		return shippingAddressSub;
	}

	public void setShippingAddressSub(String shippingAddressSub) {
		this.shippingAddressSub = shippingAddressSub;
	}

	public String getShippingPhone() {
		return shippingPhone;
	}

	public void setShippingPhone(String shippingPhone) {
		this.shippingPhone = shippingPhone;
	}

	public String getShippingCellphone() {
		return shippingCellphone;
	}

	public void setShippingCellphone(String shippingCellphone) {
		this.shippingCellphone = shippingCellphone;
	}
	
	
	
}
