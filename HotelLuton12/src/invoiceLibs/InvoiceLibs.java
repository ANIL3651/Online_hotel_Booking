

package invoiceLibs;

public class InvoiceLibs {
	int bookingId, roomNumber, quantity;
	String custName, checkInDate, roomLease, roomType, particulars, purchaseDate, description, billDate, status;
	double roomprice, rate;
	double subTotal, Discount, serviceCharge, vatCharge, total;
	long days;

	public InvoiceLibs() {
		this.bookingId = 0;
		this.roomNumber = 0;
		this.quantity = 0;
		this.custName = ""; 
		this.checkInDate = "";
		this.roomType = "";
		this.roomLease = "";
		this.particulars = "";
		this.purchaseDate = "";
		this.description = "";
		this.billDate = "";
		this.status = "";
		this.roomprice = 0.0;
		this.rate = 0.0;
		this.subTotal = 0.0;
		this.Discount = 0.0;
		this.serviceCharge = 0.0;
		this.vatCharge = 0.0;
		this.total = 0.0;
		this.days = 0;
	}

	public InvoiceLibs(int bookingId, int roomNumber, int quantity, String custName, String checkInDate,
			String roomLease, String roomType, String particulars, String purchaseDate, String description,
			String billDate, String status, double roomprice, double rate, double subTotal, double discount,
			double serviceCharge, double vatCharge, double total, long days) {
		this.bookingId = bookingId;
		this.roomNumber = roomNumber;
		this.quantity = quantity;
		this.custName = custName;
		this.checkInDate = checkInDate;
		this.roomLease = roomLease;
		this.roomType = roomType;
		this.particulars = particulars;
		this.purchaseDate = purchaseDate;
		this.description = description;
		this.billDate = billDate;
		this.status = status;
		this.roomprice = roomprice;
		this.rate = rate;
		this.subTotal = subTotal;
		Discount = discount;
		this.serviceCharge = serviceCharge;
		this.vatCharge = vatCharge;
		this.total = total;
		this.days = days;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}

	public String getRoomLease() {
		return roomLease;
	}

	public void setRoomLease(String roomLease) {
		this.roomLease = roomLease;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getParticulars() {
		return particulars;
	}

	public void setParticulars(String particulars) {
		this.particulars = particulars;
	}

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getRoomprice() {
		return roomprice;
	}

	public void setRoomprice(double roomprice) {
		this.roomprice = roomprice;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public double getDiscount() {
		return Discount;
	}

	public void setDiscount(double discount) {
		Discount = discount;
	}

	public double getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(double serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public double getVatCharge() {
		return vatCharge;
	}

	public void setVatCharge(double vatCharge) {
		this.vatCharge = vatCharge;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public long getDays() {
		return days;
	}

	public void setDays(long days) {
		this.days = days;
	}

	@Override
	public String toString() {
		return "InvoiceLibs [bookingId=" + bookingId + ", roomNumber=" + roomNumber + ", quantity=" + quantity
				+ ", custName=" + custName + ", checkInDate=" + checkInDate + ", roomLease=" + roomLease + ", roomType="
				+ roomType + ", particulars=" + particulars + ", purchaseDate=" + purchaseDate + ", description="
				+ description + ", billDate=" + billDate + ", status=" + status + ", roomprice=" + roomprice + ", rate="
				+ rate + ", subTotal=" + subTotal + ", Discount=" + Discount + ", serviceCharge=" + serviceCharge
				+ ", vatCharge=" + vatCharge + ", total=" + total + ", days=" + days + "]";
	}

}
