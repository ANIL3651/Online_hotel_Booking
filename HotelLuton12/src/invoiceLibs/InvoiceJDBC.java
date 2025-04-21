package invoiceLibs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import Database.Database;

public class InvoiceJDBC extends Database {
	Connection conn;
	PreparedStatement pstat;
	ResultSet rs;
	InvoiceLibs libs = new InvoiceLibs();

	// Method to get room id from database
	public ArrayList showRoomNumber() {
		ArrayList invoice = new ArrayList();
		String sql = "SELECT r.id \r\n" + "from room as r\r\n" + "inner join bookingtest as b\r\n"
				+ "on b.RoomNumber = r.id\r\n" + "WHERE r.status='Occupied'";
		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();

			while (rs.next()) {
				invoice.add(rs.getInt("id"));
			}

		} catch (Exception e) {

		}
		return invoice;
	}
	// Method to get room id from database end

	// Method to get customer data from database
	public ArrayList<InvoiceLibs> showCustomerDetails(int roomId) {
		ArrayList<InvoiceLibs> invoice = new ArrayList<InvoiceLibs>();
		String sql = "SELECT b.Id,b.CheckIn,c.FirstName\r\n" + "from room as r\r\n" + "inner join bookingtest as b\r\n"
				+ "on b.RoomNumber = r.id\r\n" + "inner join registration as c\r\n" + "on b.CustomerId = c.Id\r\n"
				+ "WHERE b.RoomNumber=?";
		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, roomId);
			rs = pstat.executeQuery();

			while (rs.next()) {
				InvoiceLibs libs = new InvoiceLibs();
				libs.setBookingId(rs.getInt("Id"));
				libs.setCustName(rs.getString("FirstName"));
				libs.setCheckInDate(rs.getString("CheckIn"));
				invoice.add(libs);
			}

		} catch (Exception e) {

		}
		return invoice;
	}
	// Method to get customer data from database end

	// Method to get room details for the purpose of billing
	public ArrayList<InvoiceLibs> getRoomDetailBill(int bId) {
		ArrayList<InvoiceLibs> invoice = new ArrayList<InvoiceLibs>();
		String sql = "SELECT RoomType,CheckIn,RoomPrice from bookingtest where Id=?";
		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, bId);
			rs = pstat.executeQuery();

			while (rs.next()) {
				libs.setRoomType(rs.getString("RoomType"));
				libs.setRoomLease(rs.getString("CheckIn"));
				String DateIn = rs.getString("CheckIn");
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date();
				String DateOut = formatter.format(date);

				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date date1 = format.parse(DateIn);
				Date date2 = format.parse(DateOut);
				
				long difference = Math.abs(date1.getTime() - date2.getTime());
		        long differenceDates = difference / (24 * 60 * 60 * 1000);
		        
		        libs.setDays(differenceDates);

				libs.setRoomprice(rs.getDouble("RoomPrice"));
				invoice.add(libs);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
		}
		return invoice;
	}
	// Method to get room details for the purpose of billing end

	// Method to get Service details for the purpose of billing
	public ArrayList<InvoiceLibs> getServiceDetails(int bId) {
		ArrayList<InvoiceLibs> invoice = new ArrayList<InvoiceLibs>();
		String sql = "SELECT s.Name,c.Date,s.Type,s.rate\r\n" + "from customer_service as c\r\n"
				+ "inner join service as s\r\n" + "on s.Service_id=c.Service_id\r\n" + "inner join bookingtest as b\r\n"
				+ "on b.Id = c.Booking_Id\r\n" + "WHERE b.Id = ?\r\n" + ";";
		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, bId);
			rs = pstat.executeQuery();

			while (rs.next()) {
				libs.setParticulars(rs.getString("Name"));
				libs.setPurchaseDate(rs.getString("Date"));
				libs.setDescription(rs.getString("Type"));
				libs.setRate(rs.getDouble("rate"));
				invoice.add(libs);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
		}
		return invoice;
	}
	// Method to get Service details for the purpose of billing end

	// Method to get Restaurant details for the purpose of billing
	public ArrayList<InvoiceLibs> getRestaurantDetails(int bId) {
		ArrayList<InvoiceLibs> invoice = new ArrayList<InvoiceLibs>();
		String sql = "SELECT m.Item,r.Date,m.Menu_type,m.Price,r.Quantity\r\n" + "from resbar as r\r\n"
				+ "inner join menu as m\r\n" + "on r.Menu_Id = m.Menu_Id\r\n" + "inner join bookingtest as b\r\n"
				+ "on b.Id = r.Booking_Id\r\n" + "WHERE r.Booking_Id = ? AND m.Menu_type = 'Restaurant'\r\n" + ";";
		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, bId);
			rs = pstat.executeQuery();

			while (rs.next()) {
				libs.setParticulars(rs.getString("Item"));
				libs.setPurchaseDate(rs.getString("Date"));
				libs.setDescription(rs.getString("Menu_type"));
				libs.setRate(rs.getDouble("Price"));
				libs.setQuantity(rs.getInt("Quantity"));
				invoice.add(libs);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
		}
		return invoice;
	}
	// Method to get Restaurant details for the purpose of billing end

	// Method to get Bar details for the purpose of billing
	public ArrayList<InvoiceLibs> getBarDetails(int bId) {
		ArrayList<InvoiceLibs> invoice = new ArrayList<InvoiceLibs>();
		String sql = "SELECT m.Item,r.Date,m.Menu_type,m.Price,r.Quantity\r\n" + "from resbar as r\r\n"
				+ "inner join menu as m\r\n" + "on r.Menu_Id = m.Menu_Id\r\n" + "inner join bookingtest as b\r\n"
				+ "on b.Id = r.Booking_Id\r\n" + "WHERE r.Booking_Id = ? AND m.Menu_type = 'Bar'\r\n" + ";";
		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, bId);
			rs = pstat.executeQuery();

			while (rs.next()) {
				libs.setParticulars(rs.getString("Item"));
				libs.setPurchaseDate(rs.getString("Date"));
				libs.setDescription(rs.getString("Menu_type"));
				libs.setRate(rs.getDouble("Price"));
				libs.setQuantity(rs.getInt("Quantity"));
				invoice.add(libs);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
		}
		return invoice;
	}
	// Method to get Bar details for the purpose of billing end

	// Method to get Bar details for the purpose of billing
	public boolean insertIntoInvoice(InvoiceLibs libs) {
		boolean result = false;
		String sql = "Insert Into Invoice(Date,Status,Sub_Total,Discount,Service_Charge,Vat_Charge,Total)\r\n"
				+ "VALUES(?,?,?,?,?,?,?)";
		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, libs.getBillDate());
			pstat.setString(2, libs.getStatus());
			pstat.setDouble(3, libs.getSubTotal());
			pstat.setDouble(4, libs.getDiscount());
			pstat.setDouble(5, libs.getServiceCharge());
			pstat.setDouble(6, libs.getVatCharge());
			pstat.setDouble(7, libs.getTotal());
			pstat.executeUpdate();
			pstat.close();
			conn.close();
			result = true;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
		}
		return result;
	}
	// Method to get Bar details for the purpose of billing end

}
