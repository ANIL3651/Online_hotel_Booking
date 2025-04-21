package BookingLibs;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import Database.Database;

public class BookingJDBC extends Database {
	Connection conn;
	PreparedStatement pstat;

	// Adding Booking
	public boolean add(bookingLibs book) {
		boolean result = false;
		String sql = "INSERT INTO BookingTest(CheckIn,CheckOut,RoomType,RoomPrice,BookingStatus,CustomerId) VALUES(?,?,?,?,?,?)";

		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, book.getCheckInDate());
			pstat.setString(2, book.getCheckOutDate());
			pstat.setString(3, book.getRoomType());
			pstat.setDouble(4, book.getRoomPrice());
			pstat.setString(5, book.getBookingStatus());
			pstat.setInt(6, book.getUid());
			pstat.executeUpdate(); // Insert,Update,Delete

			pstat.close();
			conn.close();

			result = true;

		} catch (Exception ae) {
			JOptionPane.showMessageDialog(null, "Error : " + ae.getMessage());
		}

		return result;

	}

	// Update Booking
	public boolean update(bookingLibs book) {
		boolean result = false;
		String sql = "UPDATE BookingTest SET CheckIn=?, CheckOut=?, RoomType=?, RoomPrice=?, BookingStatus=?, RoomNumber=0 WHERE Id=?";
		String sql1 = "UPDATE room SET status='Available' WHERE Id=?";
		String test = book.getBookingStatus().toString();

		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, book.getCheckInDate());
			pstat.setString(2, book.getCheckOutDate());
			pstat.setString(3, book.getRoomType());
			pstat.setDouble(4, book.getRoomPrice());
			pstat.setString(5, "Pending");
			pstat.setInt(6, book.getBookingId());
			pstat.executeUpdate();

			pstat.close();
			conn.close();
			
			conn = connect();
			pstat = conn.prepareStatement(sql1);
			pstat.setInt(1, book.getRoomId());
			pstat.executeUpdate();

			pstat.close();
			conn.close();

			result = true;

		} catch (Exception ae) {
			JOptionPane.showMessageDialog(null, "Error : " + ae.getMessage());
		}

		return result;

	}

	// Delete Booking
	public boolean delete(bookingLibs book) {
		boolean result = false;
		String sql = "DELETE FROM BookingTest WHERE Id=?";
		String sql1 = "UPDATE room SET status='Available' WHERE Id=?";

		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, book.getBookingId());
			pstat.executeUpdate();
		
			
			pstat = conn.prepareStatement(sql1);
			pstat.setInt(1, book.getRoomId());
			pstat.executeUpdate();
			pstat.close();
			conn.close();
			pstat.close();
			conn.close();

			result = true;

		} catch (Exception ae) {
			JOptionPane.showMessageDialog(null, "Error in delete JDBC : " + ae.getMessage());
		}

		return result;

	}
}
