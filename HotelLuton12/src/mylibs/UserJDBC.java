package mylibs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import Database.Database;
import reG.regLibs;

public class UserJDBC extends Database {

	public boolean updatePassword(User password) {
		Connection conn;
		PreparedStatement pstat;
		boolean result = false;
		String sql = "UPDATE registration SET FirstName=?, LastName=?, Email=?,PhoneNumber=?, Password=? WHERE Id=?";

		try {
				conn = connect();
				pstat = conn.prepareStatement(sql);
				pstat.setString(1,password.getfName());
				pstat.setString(2,password.getlName());
				pstat.setString(3,password.getEmail());
				pstat.setString(4,password.getMobile());
				pstat.setString(5,password.getLoginPassword());
				pstat.setInt(6, password.getUid());
				pstat.executeUpdate();
				pstat.close();
				conn.close();

			result = true;
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error : " + ex.getMessage());
		}
		return result;
	}
	
	public User login(User user) {
		String sql = "SELECT * FROM registration WHERE UName=? AND Password=?";
		try {
			Connection conn = connect();
			if (conn != null) {
				PreparedStatement pstat = conn.prepareStatement(sql);
				pstat.setString(1, user.getLoginName());
				pstat.setString(2, user.getLoginPassword());
				ResultSet rs = pstat.executeQuery();
				while(rs.next()) {
					user.setUid(rs.getInt("Id"));
					user.setfName(rs.getString("FirstName"));
					user.setlName(rs.getString("LastName"));
					user.setRole(rs.getString("UserType"));
					user.setEmail(rs.getString("Email"));
					user.setMobile(rs.getString("PhoneNumber"));
					user.setCreditCard(rs.getString("CreditCard"));
				}
			}
		} catch (Exception ex) {
			System.out.println("Error : "+ex.getMessage());
		}
		return user;
	}
	
	public User ShowProfile(User user) {
		String sql = "SELECT * FROM registration WHERE Id=?";
		try {
			Connection conn = connect();
			if (conn != null) {
				PreparedStatement pstat = conn.prepareStatement(sql);
				pstat.setInt(1, user.getUid());
				ResultSet rs = pstat.executeQuery();
				while(rs.next()) {
					user.setUid(rs.getInt("Id"));
					user.setfName(rs.getString("FirstName"));
					user.setlName(rs.getString("LastName"));
					user.setRole(rs.getString("UserType"));
					user.setEmail(rs.getString("Email"));
					user.setMobile(rs.getString("PhoneNumber"));
					user.setCreditCard(rs.getString("CreditCard"));
				}
			}
		} catch (Exception ex) {
			System.out.println("Error : "+ex.getMessage());
		}
		return user;
	}
	
	public boolean ProfileUpdate(User user) {
		boolean result = false;
		String sql = "UPDATE registration SET Email=?, PhoneNumber=?, CreditCard=?  WHERE Id=?";
		try {
			Connection conn = connect();
			if (conn != null) {
				PreparedStatement pstat = conn.prepareStatement(sql);
				pstat.setString(1, user.getEmail());
				pstat.setString(2, user.getMobile());
				pstat.setString(3, user.getCreditCard());
				pstat.setInt(4, user.getUid());
				pstat.executeUpdate();
				
				pstat.close();
				conn.close();
				result = true;
			}
		} catch (Exception ex) {
			System.out.println("Error : "+ex.getMessage());
		}
		return result;
	}
}
