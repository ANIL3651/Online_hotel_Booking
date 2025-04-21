package BarLibs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Database.Database;
import RestaurantLibs.RestaurantLibs;
import ServicesLibs.ServicesLibs;

public class BarJDBC extends Database {
	// Declaration
	Connection conn;
	PreparedStatement pstat;
	ResultSet rs;

	// Method to get menu item
	public ArrayList<RestaurantLibs> getMenuItem() {
		ArrayList<RestaurantLibs> item = new ArrayList<RestaurantLibs>();
		String sql = "SELECT Item FROM menu WHERE Menu_type='BAR'";
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

	public int getMenuId(BarLibs menu) {
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
