/**
 * 
 */
package BookingLibs;

/**
 * @author Anonymous
 *
 */
public class bookingLibs {
	int uid,bookingId,roomId;
	double roomPrice;
	String checkInDate, checkOutDate, roomType, bookingStatus, customerName;
	
	public bookingLibs() {
		this.uid = 0;
		this.bookingId = 0;
		this.roomId = 0;
		this.roomPrice = 0;
		this.checkInDate = "";
		this.checkOutDate = "";
		this.roomType = "";
		this.bookingStatus = "Pending";
		this.customerName = "";
	}
	
	public bookingLibs(int uid, int bookingId, int roomId, double roomPrice, String checkInDate, String checkOutDate,
			String roomType, String bookingStatus, String customerName) {
		this.uid = uid;
		this.bookingId = bookingId;
		this.roomId = roomId;
		this.roomPrice = roomPrice;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.roomType = roomType;
		this.bookingStatus = bookingStatus;
		this.customerName = customerName;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public double getRoomPrice() {
		return roomPrice;
	}

	public void setRoomPrice(double roomPrice) {
		this.roomPrice = roomPrice;
	}

	public String getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}

	public String getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Override
	public String toString() {
		return "bookingLibs [uid=" + uid + ", bookingId=" + bookingId + ", roomId=" + roomId + ", roomPrice="
				+ roomPrice + ", checkInDate=" + checkInDate + ", checkOutDate=" + checkOutDate + ", roomType="
				+ roomType + ", bookingStatus=" + bookingStatus + ", customerName=" + customerName + "]";
	}

	
}
