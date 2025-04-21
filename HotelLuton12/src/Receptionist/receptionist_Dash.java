package Receptionist;

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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import Bar.Bar;
import BookingLibs.GlobalRoom;
import ReceptionistLibs.ReceptionistJDBC;
import ReceptionistLibs.ReceptionistLibs;
import Restaurant.Restaurant;
import Services.Services;
import corporateReG.C_Reg_Dash;
import invoice.Invoice;
import ui.LOGIN_DASH;
import ui.Profile;
import ui.RoomManagement;

public class receptionist_Dash implements ActionListener, ItemListener {
	JLabel hotelPicture_Label = new JLabel();
	JLabel Check_IN_lbl = new JLabel();
	JLabel Check_Out_label = new JLabel();
	JLabel Laundary_pic_label = new JLabel();
	JLabel Food_pic_label = new JLabel();
	JFrame frame;
	JPanel panel_Corporate_Customer, Booking_Panel, Bill_info, Time_panel, Headerpanel, AboutUs_Panel,
			Customer_info_panel, panel3, panel4, HomeTab_Panel, Hotel_Booking_panel, Food_Tab, panel7, panelCustomer,
			panelStaf;

	// text field for food management
	JTextField txtId, txtType, txtPrice;

	JLabel HotelName_Label, Booking_Label, label4, GroupMember_Label, Ajaya_Pic_label, MountainPicture_label, label,
			Clz_IMG_Label, Time_label, Foodlbl, Ajaya_Label, Anmol_Label, Anil_Label, Atyush_Label, CheckOut_letter;

	JTabbedPane tab, Manager_tab;
	JButton Setting_btn, ProfileBtn, Ajaya_Pic_btn, Anmol_pic_btn, Anil_pic_btn, Atyush_pic_btn;

//--------For Booking---------//

	JLabel heading, lblDepartureDate, lblArrivalDate, lblRoomNum, lblRoomPrice, BookBg_Lbl, Bar_letter,
			restaurant_letter, Services_pic_label,lblFood, Services_Label;
	JDateChooser dateArrival, dateDeparture;
	JComboBox cmbRoomType;
	JButton btnAdd, btnSearch, btnUpdate, btnDelete, btnClear, btnClose;
	JLabel CReg,Room,BillBtn,BillLbl;

	JTextField txt, txt_search;
//---------Customer managema----------------\\
	JScrollPane scrollPane;

	// declaration for display Table
	DefaultTableModel dtm = new DefaultTableModel();
	JTable table = new JTable(dtm);
	Object[] obj;
	ArrayList<ReceptionistLibs> customerData;
	ArrayList roomNumber;

	TableRowSorter sorter;
//room management
	JPanel Booking_Tab_Panel;

	public receptionist_Dash() {

		frame = new JFrame("Customer_Dashboard");
		frame.setLayout(new BorderLayout());
		frame.setExtendedState(frame.MAXIMIZED_BOTH);
		frame.setSize(250, 250);
		frame.setExtendedState(frame.EXIT_ON_CLOSE);
		;
		// frame.setResizable(false);
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
				if (con == JOptionPane.YES_OPTION) {
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
		Hotel_Booking_panel.setBounds(935, 0, 840, 400);
		HomeTab_Panel.add(Hotel_Booking_panel);
		Hotel_Booking_panel.setLayout(null);

//Booking

		Booking_Label = new JLabel();
		Booking_Label.setLayout(null);

		Image Book = new ImageIcon(this.getClass().getResource("/reception (1).png")).getImage();
		Booking_Label.setIcon(new ImageIcon(Book));
		Booking_Label.setBounds(10, 7, 450, 400);

		Booking_Label.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ae) {

				new BookingConfirmation1();

			}

			public void mouseEntered(MouseEvent e) {
				Image Book = new ImageIcon(this.getClass().getResource("/reception.png")).getImage();
				Booking_Label.setIcon(new ImageIcon(Book));
				Booking_Label.setBounds(10, 0, 500, 400);

			}

			public void mouseExited(MouseEvent e) {
				Image Book = new ImageIcon(this.getClass().getResource("/reception (1).png")).getImage();
				Booking_Label.setIcon(new ImageIcon(Book));
				Booking_Label.setBounds(10, 7, 450, 400);

			}

		});

		Booking_Label.setFont(new Font("Monotype Corsiva", Font.ITALIC, 20));
		Hotel_Booking_panel.add(Booking_Label);
		
		
/////////////////////////////////		
		//----------------------------------Bill panel---------------------------------\\		
		
				BillLbl = new JLabel("     Bill ");
				BillLbl .setBounds(470, 85, 130, 120);
				BillLbl .setBackground(new Color(190, 41, 236));
				BillLbl .setForeground(Color.black);
				BillLbl .setFont(new Font("Calibri", Font.BOLD, 30));
				Hotel_Booking_panel.add(BillLbl);
		
		BillBtn = new JLabel();
		Image bill = new ImageIcon(this.getClass().getResource("/bill (4).png")).getImage();
		BillBtn .setBounds(470, 5, 130, 120);
		BillBtn .setBackground(new Color(190, 41, 236));
		BillBtn .setBorder(BorderFactory.createLineBorder(Color.white, 0));
		BillBtn .setToolTipText("Bill ");
		BillBtn .setFocusable(false);

		BillBtn .setOpaque(false);

		BillBtn .setIcon(new ImageIcon(bill));
		Hotel_Booking_panel.add(BillBtn );
		
		
		BillBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ae) {

				new Invoice();

			}

			public void mouseEntered(MouseEvent e) {
				Image bill = new ImageIcon(this.getClass().getResource("/bill (3).png")).getImage();
				BillBtn .setIcon(new ImageIcon(bill));

			}

			public void mouseExited(MouseEvent e) {
				Image bill = new ImageIcon(this.getClass().getResource("/bill (4).png")).getImage();
				BillBtn .setIcon(new ImageIcon(bill));

			}

		});

		
/// Checkin letter label

		label4 = new JLabel("Check In here : ");
		label4.setBounds(50, 350, 200, 200);
		label4.setFont(new Font("Monotype Corsive", Font.BOLD, 25));
		HomeTab_Panel.add(label4);
/// Check Out letter label		

		CheckOut_letter = new JLabel("Check Out here : ");
		CheckOut_letter.setBounds(280, 350, 250, 200);
		CheckOut_letter.setFont(new Font("Monotype Corsive", Font.BOLD, 25));
		HomeTab_Panel.add(CheckOut_letter);

///// Bar letter Label

		Bar_letter = new JLabel("Bar Services : ");
		Bar_letter.setBounds(550, 350, 250, 200);
		Bar_letter.setFont(new Font("Monotype Corsive", Font.BOLD, 25));
		HomeTab_Panel.add(Bar_letter);

///restaurant  

		restaurant_letter = new JLabel("Restaurant Services : ");
		restaurant_letter.setBounds(850, 350, 280, 200);
		restaurant_letter.setFont(new Font("Monotype Corsive", Font.BOLD, 25));
		HomeTab_Panel.add(restaurant_letter);

//		

		Services_Label = new JLabel("Services :");
		Services_Label.setBounds(1250, 350, 280, 200);
		Services_Label.setFont(new Font("Monotype Corsive", Font.BOLD, 25));
		HomeTab_Panel.add(Services_Label);

// for CheckIN

		Image img3 = new ImageIcon(this.getClass().getResource("/check-in.png")).getImage();
		Check_IN_lbl.setIcon(new ImageIcon(img3));
		Check_IN_lbl.setBounds(35, 480, 250, 200);

		Check_IN_lbl.setToolTipText("You can check in from HERE");

		Check_IN_lbl.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ae) {

				new CheckIn();

			}

			public void mouseEntered(MouseEvent e) {
				Image img3 = new ImageIcon(this.getClass().getResource("/check-in (1).png")).getImage();
				Check_IN_lbl.setIcon(new ImageIcon(img3));
				label4.setForeground(Color.red);
			}

			public void mouseExited(MouseEvent e) {
				Image img3 = new ImageIcon(this.getClass().getResource("/check-in.png")).getImage();
				Check_IN_lbl.setIcon(new ImageIcon(img3));
				label4.setForeground(Color.black);

			}

		});

		HomeTab_Panel.add(Check_IN_lbl);

// for Check Out
		Image img4 = new ImageIcon(this.getClass().getResource("/check-out.png")).getImage();
		Check_Out_label.setIcon(new ImageIcon(img4));
		Check_Out_label.setBounds(330, 480, 250, 200);
		Check_Out_label.setToolTipText("click for check out precess ");

		Check_Out_label.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ae) {
				new CheckOut();
			}

			public void mouseEntered(MouseEvent e) {
				Image img4 = new ImageIcon(this.getClass().getResource("/check-out (2).png")).getImage();
				Check_Out_label.setIcon(new ImageIcon(img4));
				CheckOut_letter.setForeground(Color.red);

			}

			public void mouseExited(MouseEvent e) {
				Image img4 = new ImageIcon(this.getClass().getResource("/check-out.png")).getImage();
				Check_Out_label.setIcon(new ImageIcon(img4));
				CheckOut_letter.setForeground(Color.black);

			}

		});

		HomeTab_Panel.add(Check_Out_label);

//for  Services
		Image img5 = new ImageIcon(this.getClass().getResource("/beer (1).png")).getImage();
		Laundary_pic_label.setIcon(new ImageIcon(img5));
		Laundary_pic_label.setBounds(550, 430, 380, 250);
		Laundary_pic_label.setToolTipText("Laundry available ");
		Laundary_pic_label.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ae) {

				new Bar();
			}

			public void mouseEntered(MouseEvent e) {
				Image img5 = new ImageIcon(this.getClass().getResource("/beer.png")).getImage();
				Laundary_pic_label.setIcon(new ImageIcon(img5));

			}

			public void mouseExited(MouseEvent e) {
				Image img5 = new ImageIcon(this.getClass().getResource("/beer (1).png")).getImage();
				Laundary_pic_label.setIcon(new ImageIcon(img5));

			}

		});
		HomeTab_Panel.add(Laundary_pic_label);

// For Food
		Image img6 = new ImageIcon(this.getClass().getResource("/restaurant.png")).getImage();
		Food_pic_label.setIcon(new ImageIcon(img6));
		Food_pic_label.setBounds(970, 460, 200, 220);
		Food_pic_label.setToolTipText("Food available ");
		Food_pic_label.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ae) {
				new Restaurant();
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
//For  Services
		Services_pic_label = new JLabel();

		Image img77 = new ImageIcon(this.getClass().getResource("/customer-service.png")).getImage();
		Services_pic_label.setIcon(new ImageIcon(img77));
		Services_pic_label.setBounds(1240, 450, 380, 220);
		Services_pic_label.setToolTipText("Laundry available ");
		Services_pic_label.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent aae) {

				new Services();
			}

			public void mouseEntered(MouseEvent aae) {
				Image img77 = new ImageIcon(this.getClass().getResource("/customer-service (1).png")).getImage();
				Services_pic_label.setIcon(new ImageIcon(img77));

			}

			public void mouseExited(MouseEvent aae) {
				Image img77 = new ImageIcon(this.getClass().getResource("/customer-service.png")).getImage();
				Services_pic_label.setIcon(new ImageIcon(img77));

			}

		});
		HomeTab_Panel.add(Services_pic_label);

//CUSTOMER-INFO
		Customer_info_panel = new JPanel();
		tab.add("Management", Customer_info_panel);
		Customer_info_panel.setLayout(null);
		Customer_info_panel.setBackground(Color.white);

	

		// Costumer_INFO tab

		
		// management tab

		panel7 = new JPanel();
		panel7.setBounds(0, 0, 1550, 665);
		panel7.setBackground(Color.gray);
		panel7.setLayout(null);
		Customer_info_panel.add(panel7);
		Customer_info_panel.setBackground(new Color(255, 253, 208));

		

		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(11, 300, 1500, 350);
		panel7.add(scrollPane);
		displayTable();
		scrollPane.setViewportView(table);

		
		label = new JLabel();
		Image imgA= new ImageIcon(this.getClass().getResource("/reg2.jpg")).getImage();
		label.setBounds(0,0,1850,800);
		label.setIcon(new ImageIcon(imgA));
		panel7.add(label);	
		
		
		
		CReg = new JLabel();
		CReg .setOpaque(false);
		Image img13 = new ImageIcon(this.getClass().getResource("/customer-review.png")).getImage();
		CReg.setIcon(new ImageIcon(img13));
		CReg.setBounds(120, 50, 250, 200);

		CReg.setToolTipText("Register Corporate Customer");

		CReg.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ae) {
			new C_Reg_Dash();

	
			}

		});

		JLabel x = new JLabel("Corporate Registration");
		x.setBounds(100,10,350,50);
		x.setForeground(Color.white);
		x.setFont(new Font("Monotype Corsive", Font.BOLD, 25));
		label.add(x);
		
				
		JLabel y = new JLabel("Room Management");
		y.setBounds(1170,10,350,50);
		y.setForeground(Color.white);
		y.setFont(new Font("Monotype Corsive", Font.BOLD, 25));
		label.add(y);
		
		JLabel z = new JLabel("Search :");
		z.setBounds(550,100,150,50);
		z.setForeground(Color.white);
		z.setFont(new Font("Monotype Corsive", Font.BOLD, 25));
		label.add(z);

		JTextField t = new JTextField();
		t.setBounds(680,112,250,30);
		t.setForeground(Color.black);
		t.setFont(new Font("Monotype Corsive", Font.BOLD, 25));
		label.add(t);
		
		t.getDocument().addDocumentListener
        (new DocumentListener() {
     @Override
     public void insertUpdate(DocumentEvent e) {
        search(t.getText());
     }
     @Override
     public void removeUpdate(DocumentEvent e) {
        search(t.getText());
     }
     @Override
     public void changedUpdate(DocumentEvent e) {
        search(t.getText());
     }
     
     
     public void search(String str) {
        if (str.length() == 0) {
           sorter.setRowFilter(null);
        } else {
           sorter.setRowFilter(RowFilter.regexFilter(str));
        }
     }
  });
		
		Room = new JLabel();
		Room.setOpaque(false);
		Image img23 = new ImageIcon(this.getClass().getResource("/living-room.png")).getImage();
		Room.setIcon(new ImageIcon(img23));
		Room.setBounds(1200, 50, 250, 200);

		Room.setToolTipText("Room Management");

		Room.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ae) {
			new RoomManagement();

	
			}

		});
		
		
		label.add(Room);

		label.add(CReg);
	}

	// Method display Table set
	public void displayTable() {
		obj = new Object[11];
		obj[0] = "Booking ID";
//			obj[1] = "Booking Date";
		obj[1] = "First Name";
		obj[2] = "Last Name";
		obj[3] = "Email";
		obj[4] = "Address";
		obj[5] = "Card Details";
		obj[6] = "Arrival Date";
		obj[7] = "Departure Date";
		obj[8] = "Room Type";
		obj[9] = "Status";
		obj[10] = "Room Number";
		dtm.setColumnIdentifiers(obj);
		refreshTable(); // Calling refresh Table to show the database data into the table
		
		sorter = new TableRowSorter<>(dtm);
		table.setRowSorter(sorter);
	}
	// Method display Table set

	// Method which retrieve data from database
	public void refreshTable() {
		customerData = new ReceptionistJDBC().getCheckInGuestData();
		dtm.setRowCount(0);
		for (ReceptionistLibs customerData : customerData) {
			Object tmpRow[] = { customerData.getBookingID(), customerData.getFirstName(), customerData.getLastName(),
					customerData.getEmail(), customerData.getAddress(), customerData.getCreditCard(),
					customerData.getCheckIn(), customerData.getCheckOut(), customerData.getRoomType(),
					customerData.getStatus(), customerData.getRoomNumber() };
			dtm.addRow(tmpRow);
		}
	

		
		
	
		
		
		
		
		
	
		
		

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
		Ajaya_Pic_btn.setBorder(BorderFactory.createLineBorder(new Color(87, 68, 62), 10));
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
		Anmol_pic_btn.setBorder(BorderFactory.createLineBorder(new Color(87, 68, 62), 10));
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
		Anil_pic_btn.setBorder(BorderFactory.createLineBorder(new Color(87, 68, 62), 10));
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
		Atyush_pic_btn.setBorder(BorderFactory.createLineBorder(new Color(87, 68, 62), 10));
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
		// Clz_IMG_Label.setIcon(new
		// javax.swing.ImageIcon(getClass().getResource("first1.jpg")));

		Clz_IMG_Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/c1.jpg")));

		Clz_IMG_Label.setBounds(0, 0, 1600, 700);
		AboutUs_Panel.add(Clz_IMG_Label);

		frame.setVisible(true);
	}

	public void itemStateChanged(ItemEvent ie) {
		if (ie.getSource() == cmbRoomType) {
			int index = cmbRoomType.getSelectedIndex();
			txtPrice.setText(Double.toString(GlobalRoom.ROOM_Price[index]));
		}

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == ProfileBtn) {

			new Profile();

		}

	}

	public static void main(String arg[]) {
		new receptionist_Dash();
	}

}
