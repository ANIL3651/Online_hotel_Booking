package RestaurantLibs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Database.Database;
import ServicesLibs.ServicesLibs;

public class RestaurantJDBC extends Database {
	// Declaration
	Connection conn;
	PreparedStatement pstat;
	ResultSet rs;

	// Method to get menu item
	public ArrayList<RestaurantLibs> getMenuItem() {
		ArrayList<RestaurantLibs> item = new ArrayList<RestaurantLibs>();
		String sql = "SELECT Item FROM menu WHERE Menu_type='Restaurant'";
		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();

			while (rs.next()) {
				RestaurantLibs libs = new RestaurantLibs();
				libs.setMenu(rs.getString("Item"));
				item.add(libs);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
		}
		return item;
	}
	// Method to get menu item end

	// Method to get menu item
	public ArrayList<RestaurantLibs> getItemPrice(RestaurantLibs libsRes) {
		ArrayList<RestaurantLibs> item = new ArrayList<RestaurantLibs>();
		String sql = "SELECT Price FROM menu WHERE Item=?";
		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, libsRes.getMenu());
			rs = pstat.executeQuery();

			while (rs.next()) {
				RestaurantLibs libs = new RestaurantLibs();
				libs.setPrice(rs.getDouble("Price"));
				item.add(libs);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
		}
		return item;
	}
	// Method to get menu item end

	public void calculateTotal(RestaurantLibs libs) {
		libs.setTotal(libs.getPrice() * libs.getQuantity());
		System.out.println(libs.getTotal());
	}

	// method to insert
	public boolean insertRestaurant(RestaurantLibs restaurant) {
		boolean result = false;
		String sql = "INSERT INTO restaurant(date, quantity, menu_Id, booking_id) VALUES (?,?,?,?)";
		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, restaurant.getDate());
			pstat.setInt(2, restaurant.getQuantity());
			pstat.setInt(3, restaurant.getMenuId());
			pstat.setInt(4, restaurant.getBookingId());
			pstat.close();
			conn.close();
			result = true;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error:" + e.getMessage());
		}
		return result;
	}
	
	// Fetching service data from database and send to gui for display
		public int getMenuId(RestaurantLibs menu) {
			int menuId = 0;
			String sql = "SELECT Menu_Id FROM menu where Item=?";
			try {
				conn = connect();
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, menu.getMenu());
				rs = pstat.executeQuery();

				while (rs.next()) {
					menuId = rs.getInt("Menu_Id");
					System.err.println(menuId);
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
			}
			return menuId;
		}
		// End
}
