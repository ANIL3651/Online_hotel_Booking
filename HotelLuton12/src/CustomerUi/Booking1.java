package CustomerUi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.toedter.calendar.JDateChooser;

import BookingLibs.BookingJDBC;
import BookingLibs.GlobalRoom;
import BookingLibs.bookingLibs;
import Database.Database;
import Library.Booking;

public class Booking1 extends JFrame implements ActionListener, ItemListener {

	JPanel pnlMain, pnlBooking, pnlField, pnlTable, pnlNewBooking;
	JLabel label, lblBooking, lblNewBooking, lblArrivalDate, lblDepartureDate, lblRoomNum, lblRoomPrice, lblServices;
	JTextField txtPrice;
	JComboBox cmbRoomType;
	JDateChooser dateArrival, dateDeparture;
	JButton btnAdd, btnUpdate, btnDelete, btnClose, btnClear;

	DefaultTableModel dtm = new DefaultTableModel();
	JTable table = new JTable(dtm);

	int userId;
	String userName;
	bookingLibs book = new bookingLibs();

	public Booking1(int id, String uName) {
		userId = id;
		userName = uName;
		// Form Design Part
		setBounds(100, 250, 1300, 700);
		ImageIcon img = new ImageIcon(getClass().getResource("/Customer Registration & Check IN.png"));
		setTitle("Booking Table");
		setIconImage(img.getImage());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		// JPanel for main panel
		pnlMain = new JPanel();
		pnlMain.setLayout(null);
		pnlMain.setBackground(new Color(200, 244, 255));
		add(pnlMain, BorderLayout.CENTER);
		// End JPanel for main panel

		// Panel Set for the Booking
		pnlBooking = new JPanel();
		pnlBooking.setBounds(0, 10, 1300, 200);
		pnlBooking.setBackground(new Color(0, 0, 0, 0));
		pnlBooking.setLayout(null);

		pnlMain.add(pnlBooking);

		// Label set for the Booking Text
		lblBooking = new JLabel("Booking Window");
		lblBooking.setBounds(0, 10, 1300, 200);
		lblBooking.setHorizontalTextPosition(JLabel.CENTER);
		lblBooking.setHorizontalAlignment(JLabel.CENTER);
		lblBooking.setVerticalAlignment(JLabel.CENTER);
		lblBooking.setFont(new Font("Verdana", Font.BOLD, 35));
		lblBooking.setForeground(Color.white);
		pnlBooking.add(lblBooking);
		// Label set for the Booking Text End

		// Field for the label text-field and Button
		pnlField = new JPanel();
		pnlField.setBounds(225, 160, 450, 400);
		pnlField.setBackground(new Color(132, 66, 4, 150));
		pnlField.setLayout(null);
		pnlMain.add(pnlField);

		Date date = new Date();

		// Check In
		lblArrivalDate = new JLabel("Check In Date : ");
		lblArrivalDate.setBounds(15, 50, 200, 50);
		lblArrivalDate.setFont(new Font("Verdana", Font.BOLD, 18));
		lblArrivalDate.setForeground(Color.white);
		pnlField.add(lblArrivalDate);

		dateArrival = new JDateChooser();
		dateArrival.setBounds(210, 65, 180, 40);
		dateArrival.setMinSelectableDate(date);
		dateArrival.setDateFormatString("yyyy-MM-dd");
		dateArrival.setDateFormatString("yyyy-MM-dd");
		dateArrival.setFont(new Font("Verdana", Font.BOLD, 18));
		pnlField.add(dateArrival);
		// Check In End

		// Check Out Date
		lblDepartureDate = new JLabel("Check Out Date : ");

		lblDepartureDate.setBounds(15, 110, 200, 50);
		lblDepartureDate.setFont(new Font("Verdana", Font.BOLD, 18));
		lblDepartureDate.setForeground(Color.white);
		pnlField.add(lblDepartureDate);

		dateDeparture = new JDateChooser();
		dateDeparture.setMinSelectableDate(date);
		dateDeparture.setDateFormatString("yyyy-MM-dd");
		dateDeparture.setBounds(210, 120, 180, 40);
		dateDeparture.setDateFormatString("yyyy-MM-dd");
		dateDeparture.setFont(new Font("Verdana", Font.BOLD, 18));
		pnlField.add(dateDeparture);
		// Check Out End

		// Room Type combo box
		lblRoomNum = new JLabel("Room Type : ");
		lblRoomNum.setBounds(15, 180, 200, 50);
		lblRoomNum.setFont(new Font("Verdana", Font.BOLD, 18));
		lblRoomNum.setForeground(Color.white);
		pnlField.add(lblRoomNum);

		cmbRoomType = new JComboBox();
		for (int i = 0; i < GlobalRoom.ROOM_TYPES.length; i++) {
			cmbRoomType.addItem(GlobalRoom.ROOM_TYPES[i]);
		}
		cmbRoomType.setBounds(210, 180, 180, 40);
		cmbRoomType.setFont(new Font("Verdana", Font.BOLD, 18));
		cmbRoomType.addItemListener(this);
		pnlField.add(cmbRoomType);
		// Room Type combo box End

		// Room Price
		lblRoomPrice = new JLabel("Room Price : ");
		lblRoomPrice.setBounds(15, 240, 200, 50);
		lblRoomPrice.setFont(new Font("Verdana", Font.BOLD, 18));
		lblRoomPrice.setForeground(Color.white);
		pnlField.add(lblRoomPrice);

		txtPrice = new JTextField();
		txtPrice.setBounds(210, 240, 180, 40);
		txtPrice.setEditable(false);
		txtPrice.setFont(new Font("Verdana", Font.BOLD, 18));
		pnlField.add(txtPrice);
		// Room Price End

		// JButton set

		// Booking Button
		btnAdd = new JButton("Book Now");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAdd.setBounds(20, 310, 200, 40);
		btnAdd.setBackground(new Color(132, 66, 4));
		btnAdd.setFocusable(false);
		btnAdd.setForeground(Color.white);
		btnAdd.setBorder(BorderFactory.createLineBorder(Color.white, 1));
		btnAdd.addActionListener(this);
		pnlField.add(btnAdd);

		// Booking Button End

		// Booking Update Button
		btnUpdate = new JButton("Edit ");
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnUpdate.setBounds(230, 310, 200, 40);
		btnUpdate.setBackground(new Color(132, 66, 4));
		btnUpdate.setFocusable(false);
		btnUpdate.setForeground(Color.white);
		btnUpdate.addActionListener(this);
		pnlField.add(btnUpdate);

		// Booking Update Button End

		// Booking Delete Button
		btnDelete = new JButton("Cancel");

		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDelete.setBounds(100, 360, 200, 40);
		btnDelete.setBackground(new Color(132, 66, 4));
		btnDelete.setFocusable(false);
		btnDelete.setForeground(Color.white);
		btnDelete.setBorder(BorderFactory.createLineBorder(Color.white, 1));
		btnDelete.addActionListener(this);
		pnlField.add(btnDelete);

		// Booking Delete Button End

		// Booking Button End

		// JButton End

		// Field for the label text-field and Button End

		// Panel Set for the Booking End

		pnlTable = new JPanel();
		pnlTable.setBounds(725, 160, 450, 400);
		pnlMain.add(pnlTable);

		table.setPreferredScrollableViewportSize(new Dimension(450, 400));
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

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				TableModel model = table.getModel();
				int bookingID = Integer.parseInt(model.getValueAt(i, 0).toString());
				String arrivalDate = model.getValueAt(i, 1).toString();
				String departureDate = model.getValueAt(i, 2).toString();
				String roomType = model.getValueAt(i, 3).toString();
				double price = Double.parseDouble(model.getValueAt(i, 4).toString());
				book.setBookingStatus(model.getValueAt(i, 5).toString());
				int roomNumber = Integer.parseInt(model.getValueAt(i, 6).toString());
				try {
					Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse((String) arrivalDate.toString());
					dateArrival.setDate(date1);
					Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse((String) departureDate.toString());
					dateDeparture.setDate(date2);
				} catch (ParseException ae) {
					System.out.println("Error : " + ae.getMessage());
				}

				if (roomType.equals("Single")) {
					cmbRoomType.setSelectedIndex(1);
				} else if (roomType.equals("Double")) {
					cmbRoomType.setSelectedIndex(2);
				} else if (roomType.equals("Twin")) {
					cmbRoomType.setSelectedIndex(3);
				}
				txtPrice.setText(Double.toString(price));
				book.setBookingId(bookingID);
				book.setRoomId(roomNumber);
			}
		});

		label = new JLabel();
		Image imgG = new ImageIcon(this.getClass().getResource("/bookingBG.jpg")).getImage();
		label.setIcon(new ImageIcon(imgG));
		label.setBounds(0, 0, 1600, 900);
		pnlMain.add(label);

		setVisible(true);
		// End Design Part
	}

	// Display Table and data
	private void display() {
		// table = new JTable(dtm);
		try {
			dtm.setRowCount(0);
			Database db = new Database();
			Connection conn;
			conn = db.connect();
			String sql = "SELECT * FROM BookingTest WHERE CustomerId=? and BookingStatus != 'Check In'  ;";
			 
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

	}
	// End line of display table and data

	// Button Action listener's Action Perform Part
	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == btnAdd) {
			book = new bookingLibs();
			String arrDate = ((JTextField) dateArrival.getDateEditor().getUiComponent()).getText();
			String depDate = ((JTextField) dateDeparture.getDateEditor().getUiComponent()).getText();
			book.setCheckInDate(arrDate);
			book.setCheckOutDate(depDate);
			book.setRoomType(cmbRoomType.getSelectedItem().toString());
			book.setRoomPrice(Double.parseDouble(txtPrice.getText()));
			book.setUid(userId);
			book.setCustomerName(userName);
			BookingJDBC jdbc = new BookingJDBC();
			boolean result = jdbc.add(book);
			if (result == true) {
				JOptionPane.showMessageDialog(null, "Booking Requested ");
				dateArrival.setDate(null);
				dateDeparture.setDate(null);
				cmbRoomType.setSelectedIndex(0);
				txtPrice.setText(null);
				display();
			} else {
				JOptionPane.showMessageDialog(null, "Failed to insert record");
			}

		} else if (ae.getSource() == btnUpdate) {
			if (book.getBookingStatus().toString().equals("Check In")) {
				JOptionPane.showMessageDialog(null,
						"You cannot Update the booking because you already have check-In a room.");
			} else {
				String arrDate = ((JTextField) dateArrival.getDateEditor().getUiComponent()).getText();
				String depDate = ((JTextField) dateDeparture.getDateEditor().getUiComponent()).getText();
				book.setCheckInDate(arrDate);
				book.setCheckOutDate(depDate);
				book.setRoomType(cmbRoomType.getSelectedItem().toString());
				book.setRoomPrice(Double.parseDouble(txtPrice.getText()));
				BookingJDBC jdbc = new BookingJDBC();
				boolean result = jdbc.update(book);
				if (result == true) {
					JOptionPane.showMessageDialog(null, "Successfully Edited Booking");
					dateArrival.setDate(null);
					dateDeparture.setDate(null);
					cmbRoomType.setSelectedIndex(0);
					txtPrice.setText(null);
					display();
				} else {
					JOptionPane.showMessageDialog(null, "Failed to update record");
				}
			}
		} else if (ae.getSource() == btnDelete) {
			if (book.getBookingStatus().toString().equals("Check In")) {
				JOptionPane.showMessageDialog(null,
						"You cannot Update the booking because you already have check-In a room.");
			} else {
				BookingJDBC jdbc = new BookingJDBC();
				boolean result = jdbc.delete(book);
				if (result == true) {
					JOptionPane.showMessageDialog(null, "Cancled Booking");
					dateArrival.setDate(null);
					dateDeparture.setDate(null);
					cmbRoomType.setSelectedIndex(0);
					txtPrice.setText(null);
					display();
				} else {
					JOptionPane.showMessageDialog(null, "Failed to delete record");
				}
			}

		}
	}

	// Button Action listener's Action Perform Part End

	// Combo Box Item Listener Part
	@Override
	public void itemStateChanged(ItemEvent ae) {
		if (ae.getSource() == cmbRoomType) {
			int index = cmbRoomType.getSelectedIndex();
			txtPrice.setText(Double.toString(GlobalRoom.ROOM_Price[index]));
		}
	}
}

// Combo Box Item Listener Part END
/*
 * public static void main(String args[]) {
 * 
 * new Booking(4, "dipesh"); } }
 * 
 */