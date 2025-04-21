package RestaurantLibs;

public class RestaurantLibs {
	int bookingId, quantity, roomId, menuId;
	double price, total;
	String date, customerName, menu;

	public RestaurantLibs() {
		this.bookingId = 0;
		this.quantity = 0;
		this.roomId = 0;
		this.menuId = 0;
		this.price = 0.0;
		this.total = 0.0;
		this.date = "";
		this.customerName = "";
		this.menu = "";
	}

	public RestaurantLibs(int bookingId, int quantity, int roomId, int menuId, double price, double total, String date,
			String customerName, String menu) {
		super();
		this.bookingId = bookingId;
		this.quantity = quantity;
		this.roomId = roomId;
		this.menuId = menuId;
		this.price = price;
		this.total = total;
		this.date = date;
		this.customerName = customerName;
		this.menu = menu;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	@Override
	public String toString() {
		return "RestaurantLibs [bookingId=" + bookingId + ", quantity=" + quantity + ", roomId=" + roomId + ", menuId="
				+ menuId + ", price=" + price + ", total=" + total + ", date=" + date + ", customerName=" + customerName
				+ ", menu=" + menu + "]";
	}

}
