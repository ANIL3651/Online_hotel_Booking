package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import BookingLibs.GlobalRoom;
import CustomerUi.Booking1;
import Database.Database;

public class Customer_dashh implements ActionListener, ItemListener {
	JLabel hotelPicture_Label = new JLabel();
	JLabel Wifi_Pic_label = new JLabel();
	JLabel Gym_pic_label = new JLabel();
	JLabel Laundary_pic_label = new JLabel();
	JLabel Food_pic_label = new JLabel();
	JFrame frame;
	JPanel Booking_Panel, Bill_info, Time_panel, Headerpanel, AboutUs_Panel, Customer_info_panel, panel3, panel4,
			HomeTab_Panel, Hotel_Booking_panel, Food_Tab, panel7, panelCustomer, panelStaf;
	

	// text field for food management
	JTextField txtId, txtType, txtPrice;

	JLabel HotelName_Label, Booking_Label, label4, GroupMember_Label, Ajaya_Pic_label, MountainPicture_label, label,
			Clz_IMG_Label, Time_label, Foodlbl, Ajaya_Label, Anmol_Label, Anil_Label, Atyush_Label;

	JTabbedPane tab, Manager_tab;
	JButton Setting_btn, ProfileBtn, Ajaya_Pic_btn, Anmol_pic_btn, Anil_pic_btn, Atyush_pic_btn;

//--------For Booking---------//

	JLabel heading, lblDepartureDate, lblArrivalDate, lblRoomNum, lblRoomPrice,BookBg_Lbl;
	JDateChooser dateArrival, dateDeparture;
	JComboBox cmbRoomType;
	JButton btnAdd, btnSearch, btnUpdate, btnDelete, btnClear, btnClose;
	 JPanel pnlTable;
	 
	 
	JTextField txt, txt_search;
//---------Customer managema----------------\\
	DefaultTableModel dtm = new DefaultTableModel();
	JTable table = new JTable(dtm);
//room management
	JPanel Booking_Tab_Panel;

	
	int userId;
	String userName;
	public Customer_dashh(int uid,String uName) {

		userId = uid;
		userName = uName;
		
		frame = new JFrame("Customer_Dashboard");
		frame.setLayout(new BorderLayout());
		frame.setExtendedState(frame.MAXIMIZED_BOTH);
		frame.setSize(250, 250);

	//	frame.setResizable(false);
// ------------Header panel represent the upper part of the dash_board-------------------\\

		Headerpanel = new JPanel();
		Headerpanel.setBackground(new Color(190, 41, 236));
		Headerpanel.setPreferredSize(new Dimension(150, 100));
		Headerpanel.setLayout(null);
		frame.add(Headerpanel, BorderLayout.NORTH);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/LOGO2.png")));

		// ----------profile-----------\\
		ProfileBtn = new JButton();
		Image img = new ImageIcon(this.getClass().getResource("/user.png")).getImage();
		ProfileBtn.setBounds(10, 10, 80, 70);
		ProfileBtn.setBackground(new Color(190, 41, 236));
		ProfileBtn.addActionListener(this);
		ProfileBtn.setBorder(BorderFactory.createLineBorder(Color.white, 0));
		ProfileBtn.setToolTipText("PROFILE ");
		ProfileBtn.setFocusable(false);

		ProfileBtn.setOpaque(false);

		ProfileBtn.setIcon(new ImageIcon(img));
		Headerpanel.add(ProfileBtn);

//----------------------------------Setting button----------------------------------------\\

		Setting_btn = new JButton();
		Image setting = new ImageIcon(this.getClass().getResource("/Power.png")).getImage();
		Setting_btn.setIcon(new ImageIcon(setting));
		Setting_btn.setBackground(new Color(190, 41, 236));
		Setting_btn.setOpaque(false);
		Setting_btn.addActionListener(this);
		Setting_btn.setBorder(BorderFactory.createLineBorder(Color.white, 0));
		Setting_btn.setBounds(1455, 17, 60, 60);

		Headerpanel.add(Setting_btn);

		Setting_btn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {
				
				int con = JOptionPane.showConfirmDialog(null, "<Html> <B> Do you want to Logout? </B> </Html>: ",
						"Logout", JOptionPane.YES_NO_OPTION);
				if (con==JOptionPane.YES_OPTION) {
					frame.dispose();
					frame.dispose();
					new LOGIN_DASH();
								}
				}
			

		});

//-------------------------------Real Time Code---------------------------------------\\

		Time_panel = new JPanel();
		Time_panel.setBounds(1200, 20, 200, 40);
		Time_panel.setBackground(new Color(190, 41, 236));
		Time_panel.setOpaque(false);
		Time_panel.setBorder(BorderFactory.createLineBorder(Color.black, 4));

		Headerpanel.add(Time_panel);

		Time_label = new JLabel("", SwingConstants.CENTER);
		Time_label.setBounds(100, 1, 200, 40);
		Time_label.setFont(new Font("Calibri", Font.BOLD, 30));
		Time_label.setForeground(Color.black);

		Time_panel.add(Time_label, BorderLayout.CENTER);

		int delay = 100;
		Timer timer = new Timer(delay, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LocalDateTime now = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
				String formattedDateTime = now.format(formatter);
				Time_label.setText(formattedDateTime);
			}
		});

		timer.start();

		// ----------------------Real Time Code END----------------------------\\

		// --------------Continue upper part Decoration----------------\\

		HotelName_Label = new JLabel("<Html> <u> HOTEL LUTON </u> </Html>");
		HotelName_Label.setBounds(150, 10, 230, 60);
		HotelName_Label.setForeground(Color.white);
		HotelName_Label.setFont(new Font("Verdana", Font.BOLD, 28));

		Headerpanel.add(HotelName_Label);

		MountainPicture_label = new JLabel();
		Image img1 = new ImageIcon(this.getClass().getResource("/mountain.jpg")).getImage();
		MountainPicture_label.setIcon(new ImageIcon(img1));
		MountainPicture_label.setBounds(0, 0, 1580, 100);
		Headerpanel.add(MountainPicture_label);

// -------------------------TAB Codes------------------------\\

		tab = new JTabbedPane();
		tab.setBounds(10, 10, 550, 550);
		tab.setFont(new Font("VERDANA ", Font.BOLD, 18));

		frame.add(tab);

		HomeTab_Panel = new JPanel();
		HomeTab_Panel.setLayout(null);
		HomeTab_Panel.setBackground(Color.white);
		
		
	
		tab.add("Home", HomeTab_Panel);

		Image img2 = new ImageIcon(this.getClass().getResource("/Hotel7.jpg")).getImage();
		hotelPicture_Label.setIcon(new ImageIcon(img2));
		hotelPicture_Label.setBounds(5, 5, 925, 400);
		HomeTab_Panel.add(hotelPicture_Label);

		Hotel_Booking_panel = new JPanel();
		
		Hotel_Booking_panel.setBackground(Color.LIGHT_GRAY);
		Hotel_Booking_panel.setBounds(935, 0, 640, 400);
		HomeTab_Panel.add(Hotel_Booking_panel);
		Hotel_Booking_panel.setLayout(null);
		
//Booking
		
		
		
		Booking_Label = new JLabel();
		Booking_Label.setLayout(null); 
		
		Image Book = new ImageIcon(this.getClass().getResource("/booking.png")).getImage();
		Booking_Label.setIcon(new ImageIcon(Book));
		Booking_Label.setBounds(60, 7, 500, 400);
		
		Booking_Label.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ae) {


			//	System.out.println(Global_Customer_ID.currentUser);
		
				
				new Booking1(uid, uName);
				
			}

			public void mouseEntered(MouseEvent e) {
				Image Book = new ImageIcon(this.getClass().getResource("/booking (1).png")).getImage();
				Booking_Label.setIcon(new ImageIcon(Book));
				Booking_Label.setBounds(60, 0, 500, 400);
			}

			public void mouseExited(MouseEvent e) {
				Image Book = new ImageIcon(this.getClass().getResource("/booking.png")).getImage();
				Booking_Label.setIcon(new ImageIcon(Book));
				Booking_Label.setBounds(60, 7, 500, 400);


			}


		});
		
		
		
		Booking_Label.setFont(new Font("Monotype Corsiva", Font.ITALIC, 20));
		Hotel_Booking_panel.add(Booking_Label);
/// Services label
		label4 = new JLabel("Services : ");
		label4.setBounds(10, 350, 200, 200);
		label4.setFont(new Font("Monotype Corsive", Font.BOLD, 25));
		HomeTab_Panel.add(label4);

// for wifi

		Image img3 = new ImageIcon(this.getClass().getResource("/wifi1.png")).getImage();
		Wifi_Pic_label.setIcon(new ImageIcon(img3));
		Wifi_Pic_label.setBounds(35, 480, 350, 200);

		Wifi_Pic_label.setToolTipText("Wifi available ");

		Wifi_Pic_label.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ae) {

				JOptionPane JOptionPane = new JOptionPane();
				JOptionPane.showMessageDialog(JOptionPane, "(USER : LUTON )\n (PASSWORD : Luton1234)");
			}

			public void mouseEntered(MouseEvent e) {
				Image img3 = new ImageIcon(this.getClass().getResource("/wifi.png")).getImage();
				Wifi_Pic_label.setIcon(new ImageIcon(img3));

			}

			public void mouseExited(MouseEvent e) {
				Image img3 = new ImageIcon(this.getClass().getResource("/wifi1.png")).getImage();
				Wifi_Pic_label.setIcon(new ImageIcon(img3));

			}

		});

		HomeTab_Panel.add(Wifi_Pic_label);

// for gym
		Image img4 = new ImageIcon(this.getClass().getResource("/gym.png")).getImage();
		Gym_pic_label.setIcon(new ImageIcon(img4));
		Gym_pic_label.setBounds(350, 480, 400, 200);
		Gym_pic_label.setToolTipText("GYM available ");

		Gym_pic_label.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ae) {

				JOptionPane JOptionPane = new JOptionPane();
				JOptionPane.showMessageDialog(JOptionPane, "OPENS AT : 6AM - 8PM");
			}

			public void mouseEntered(MouseEvent e) {
				Image img4 = new ImageIcon(this.getClass().getResource("/gym1.png")).getImage();
				Gym_pic_label.setIcon(new ImageIcon(img4));

			}

			public void mouseExited(MouseEvent e) {
				Image img4 = new ImageIcon(this.getClass().getResource("/gym.png")).getImage();
				Gym_pic_label.setIcon(new ImageIcon(img4));

			}

		});

		HomeTab_Panel.add(Gym_pic_label);

//for Laundry Services
		Image img5 = new ImageIcon(this.getClass().getResource("/laundary1.png")).getImage();
		Laundary_pic_label.setIcon(new ImageIcon(img5));
		Laundary_pic_label.setBounds(820, 450, 400, 250);
		Laundary_pic_label.setToolTipText("Laundry available ");
		Laundary_pic_label.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ae) {

				JOptionPane JOptionPane = new JOptionPane();
				JOptionPane.showMessageDialog(JOptionPane, "Morning at : 6AM - 8AM \n Evening at : 6PM - 8PM ");
			}

			public void mouseEntered(MouseEvent e) {
				Image img5 = new ImageIcon(this.getClass().getResource("/laundary.png")).getImage();
				Laundary_pic_label.setIcon(new ImageIcon(img5));

			}

			public void mouseExited(MouseEvent e) {
				Image img5 = new ImageIcon(this.getClass().getResource("/laundary1.png")).getImage();
				Laundary_pic_label.setIcon(new ImageIcon(img5));

			}

		});
		HomeTab_Panel.add(Laundary_pic_label);

// For Food
		Image img6 = new ImageIcon(this.getClass().getResource("/restaurant.png")).getImage();
		Food_pic_label.setIcon(new ImageIcon(img6));
		Food_pic_label.setBounds(1250, 460, 400, 220);
		Food_pic_label.setToolTipText("Food available ");
		Food_pic_label.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ae) {
				////////// // new ;
			}

			public void mouseEntered(MouseEvent e) {
				Image img6 = new ImageIcon(this.getClass().getResource("/foodd.png")).getImage();
				Food_pic_label.setIcon(new ImageIcon(img6));

			}

			public void mouseExited(MouseEvent e) {
				Image img6 = new ImageIcon(this.getClass().getResource("/restaurant.png")).getImage();
				Food_pic_label.setIcon(new ImageIcon(img6));

			}

		});

		HomeTab_Panel.add(Food_pic_label);

		

		
//billing 
		Bill_info = new JPanel();
		tab.add("Booking Info", Bill_info);
///////////////////////////////////////////////////////////////////////////////////////////////////////
		pnlTable = new JPanel();
		pnlTable.setBounds(5, 60, 1000, 600);
		
		Bill_info.add(pnlTable);
		Bill_info.setBackground(new Color(166, 137, 225));
		table.setPreferredScrollableViewportSize(new Dimension(1000, 600));
		table.setFillsViewportHeight(true);
		pnlTable.add(new JScrollPane(table));
		dtm.addColumn("Booking Id");
		dtm.addColumn("CheckIn");
		dtm.addColumn("CheckOut");
		dtm.addColumn("Room Type");
		dtm.addColumn("Room Price");
		dtm.addColumn("Status");
		dtm.addColumn("Room Number");

		display(); // there is no error to display

	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		private void display() {
			// table = new JTable(dtm);
			try {
				dtm.setRowCount(0);
				Database db = new Database();
				Connection conn;
				conn = db.connect();
				String sql = "SELECT * FROM BookingTest WHERE CustomerId=?  ;";
				 
				PreparedStatement pstat = conn.prepareStatement(sql);
				pstat.setInt(1, userId);
				
				ResultSet rs = pstat.executeQuery();

				while (rs.next()) {
					int bookingId, roomNumber;
					String checkIn, checkOut, roomType, roomPrice, status;
					bookingId = rs.getInt("Id");
					checkIn = rs.getString("CheckIn");
					checkOut = rs.getString("CheckOut");
					roomType = rs.getString("RoomType");
					roomPrice = rs.getString("RoomPrice");
					status = rs.getString("BookingStatus");
					roomNumber = rs.getInt("RoomNumber");
					Object tmpRow[] = { bookingId, checkIn, checkOut, roomType, roomPrice, status, roomNumber };
					dtm.addRow(tmpRow);
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error in display : " + e.getMessage());
			}
		
		
		
		
		
		
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// about Us	
		AboutUs_Panel = new JPanel();
		AboutUs_Panel.setBackground(Color.gray);
		tab.add("About Us", AboutUs_Panel);

		////
		AboutUs_Panel.setLayout(null);

		GroupMember_Label = new JLabel("ADMINS ");
		GroupMember_Label.setBounds(690, 15, 300, 40);
		GroupMember_Label.setFont(new Font("COMIC SANS MS", Font.BOLD, 35));
		AboutUs_Panel.add(GroupMember_Label);

		Ajaya_Label = new JLabel("Ajaya : ");
		Ajaya_Label.setBounds(185, 55, 300, 100);
		Ajaya_Label.setFont(new Font("COMIC SANS MS", Font.BOLD, 25));
		AboutUs_Panel.add(Ajaya_Label);

		Anmol_Label = new JLabel("Anmol : ");
		Anmol_Label.setBounds(512, 55, 300, 100);
		Anmol_Label.setFont(new Font("COMIC SANS MS", Font.BOLD, 25));
		AboutUs_Panel.add(Anmol_Label);

		Anil_Label = new JLabel("Anil : ");
		Anil_Label.setBounds(839, 55, 300, 100);
		Anil_Label.setFont(new Font("COMIC SANS MS", Font.BOLD, 25));
		AboutUs_Panel.add(Anil_Label);

		Atyush_Label = new JLabel("Atyush : ");
		Atyush_Label.setBounds(1165, 55, 300, 100);
		Atyush_Label.setFont(new Font("COMIC SANS MS", Font.BOLD, 25));
		AboutUs_Panel.add(Atyush_Label);

		Ajaya_Pic_btn = new JButton();
		Image Ajaya = new ImageIcon(this.getClass().getResource("/Azzaya.jpg")).getImage();
		Ajaya_Pic_btn.setIcon(new ImageIcon(Ajaya));
		Ajaya_Pic_btn.setBorder(BorderFactory.createLineBorder(new Color(87,68,62), 10));
		Ajaya_Pic_btn.setBounds(185, 135, 255, 245);
		AboutUs_Panel.add(Ajaya_Pic_btn);

		Ajaya_Pic_btn.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				Ajaya_Label.setForeground(Color.RED);
				GroupMember_Label.setForeground(Color.blue);
			}

			public void mouseExited(MouseEvent e) {
				Ajaya_Label.setForeground(Color.BLACK);
				GroupMember_Label.setForeground(Color.blue);
				GroupMember_Label.setForeground(Color.BLACK);

			}

		});

		Anmol_pic_btn = new JButton();
		Image Anmol = new ImageIcon(this.getClass().getResource("/Anmol.jpg")).getImage();
		Anmol_pic_btn.setIcon(new ImageIcon(Anmol));
		Anmol_pic_btn.setBorder(BorderFactory.createLineBorder(new Color(87,68,62), 10));
		Anmol_pic_btn.setBounds(512, 135, 255, 245);
		AboutUs_Panel.add(Anmol_pic_btn);

		Anmol_pic_btn.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				Anmol_Label.setForeground(Color.RED);
				GroupMember_Label.setForeground(Color.blue);
			}

			public void mouseExited(MouseEvent e) {
				Anmol_Label.setForeground(Color.BLACK);
				GroupMember_Label.setForeground(Color.BLACK);

			}

		});

		Anil_pic_btn = new JButton();
		Image Anil = new ImageIcon(this.getClass().getResource("/anil .jpg")).getImage();
		Anil_pic_btn.setIcon(new ImageIcon(Anil));
		Anil_pic_btn.setBorder(BorderFactory.createLineBorder(new Color(87,68,62), 10));
		Anil_pic_btn.setBounds(839, 135, 255, 245);
		AboutUs_Panel.add(Anil_pic_btn);
		Anil_pic_btn.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				Anil_Label.setForeground(Color.RED);
				GroupMember_Label.setForeground(Color.blue);
			}

			public void mouseExited(MouseEvent e) {
				Anil_Label.setForeground(Color.BLACK);
				GroupMember_Label.setForeground(Color.BLACK);

			}

		});

		Atyush_pic_btn = new JButton();
		Image Atyush = new ImageIcon(this.getClass().getResource("/Atyush.jpg")).getImage();
		Atyush_pic_btn.setIcon(new ImageIcon(Atyush));
		Atyush_pic_btn.setBorder(BorderFactory.createLineBorder(new Color(87,68,62), 10));
		Atyush_pic_btn.setBounds(1165, 135, 255, 245);
		AboutUs_Panel.add(Atyush_pic_btn);
		Atyush_pic_btn.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				Atyush_Label.setForeground(Color.RED);
				GroupMember_Label.setForeground(Color.blue);
			}

			public void mouseExited(MouseEvent e) {
				Atyush_Label.setForeground(Color.BLACK);
				GroupMember_Label.setForeground(Color.BLACK);

			}

		});

		Clz_IMG_Label = new JLabel();
	//	Clz_IMG_Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("first1.jpg")));
		
		Clz_IMG_Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/c1.jpg")));
		
		Clz_IMG_Label.setBounds(0, 0, 1600, 700);
		AboutUs_Panel.add(Clz_IMG_Label);

		frame.setVisible(true);
	}

	public void itemStateChanged(ItemEvent ie) {
		if(ie.getSource()==cmbRoomType) {
			int index=cmbRoomType.getSelectedIndex();
			txtPrice.setText(Double.toString(GlobalRoom.ROOM_Price[index]));
		}
		
	}

	
	
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == ProfileBtn) {

			new Profile();
			
		
			
		}
	
	
	}
/*
 * public static void main(String arg[]) { new Customer_dashh(2,"Nitesh"); } }
 */
}