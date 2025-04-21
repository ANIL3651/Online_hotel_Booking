package Restaurant;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import RestaurantLibs.RestaurantJDBC;
import RestaurantLibs.RestaurantLibs;
import ServicesLibs.ServicesJDBC;
import ServicesLibs.ServicesLibs;

public class Restaurant extends JFrame implements ItemListener, FocusListener, KeyListener {

	// Declaration
	JPanel contentPane;
	JTextField txtCustomerName, txtCheckIn, txtDate, txtBookingId, txtPrice, txtQuantity;
	JPanel pnlHead, pnlMiddle;
	JLabel lblRoom, lblCustomerName, lblCheckIn, lblDate, lblBookingId, lblNewLabel, lblPrice, lblQuantity, lblTotal;
	JButton btnAdd, btnClear;
	JComboBox cmbRoom, cmbItems;
	JButton btnHistory;

	// Declaration initializer and memory allocation
	ServicesJDBC jdbc = new ServicesJDBC();
	ServicesLibs libs = new ServicesLibs();
	RestaurantJDBC jdbcRes = new RestaurantJDBC();
	RestaurantLibs libsRes = new RestaurantLibs();

	// Arraylist declaration
	ArrayList array;
	ArrayList<ServicesLibs> arrayList = new ArrayList<ServicesLibs>();
	ArrayList<RestaurantLibs> arrayListRes = new ArrayList<RestaurantLibs>();

	// declaration for display Table
	DefaultTableModel dtm = new DefaultTableModel();
	JTable table = new JTable(dtm);
	Object[] obj;
	private JTextField txtTotal;
	// declaration for display Table end

	/**
	 * Create the frame.
	 */
	public Restaurant() {
		setTitle("Restaurant Window");
		setBounds(200, 20, 1093, 840);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		pnlHead = new JPanel();
		pnlHead.setBorder(new LineBorder(Color.BLACK));
		pnlHead.setBackground(new Color(166, 137, 225));
		pnlHead.setBounds(10, 0, 1051, 390);
		contentPane.add(pnlHead);
		pnlHead.setLayout(null);

		lblRoom = new JLabel("Room :");
		lblRoom.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblRoom.setBounds(75, 70, 166, 35);
		pnlHead.add(lblRoom);

		cmbRoom = new JComboBox();
		showRoomNumber();
		cmbRoom.setFont(new Font("Times New Roman", Font.BOLD, 20));
		cmbRoom.setBounds(251, 70, 157, 35);
		cmbRoom.addItemListener(this);
		pnlHead.add(cmbRoom);

		lblCustomerName = new JLabel("Customer Name :");
		lblCustomerName.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblCustomerName.setBounds(75, 125, 166, 35);
		pnlHead.add(lblCustomerName);

		txtCustomerName = new JTextField();
		txtCustomerName.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txtCustomerName.setEditable(false);
		txtCustomerName.setBounds(251, 125, 157, 35);
		pnlHead.add(txtCustomerName);
		txtCustomerName.setColumns(10);

		lblCheckIn = new JLabel("Check In Date :");
		lblCheckIn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblCheckIn.setBounds(75, 192, 166, 35);
		pnlHead.add(lblCheckIn);

		txtCheckIn = new JTextField();
		txtCheckIn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txtCheckIn.setEditable(false);
		txtCheckIn.setColumns(10);
		txtCheckIn.setBounds(251, 192, 157, 35);
		pnlHead.add(txtCheckIn);

		lblDate = new JLabel("Date :");
		lblDate.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblDate.setBounds(500, 70, 166, 35);
		pnlHead.add(lblDate);

		txtDate = new JTextField();
		txtDate.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txtDate.setEditable(false);
		txtDate.setColumns(10);
		txtDate.setBounds(690, 70, 157, 35);
		pnlHead.add(txtDate);

		lblBookingId = new JLabel("Booking ID :");
		lblBookingId.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblBookingId.setBounds(500, 125, 166, 35);
		pnlHead.add(lblBookingId);

		txtBookingId = new JTextField();
		txtBookingId.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txtBookingId.setEditable(false);
		txtBookingId.setColumns(10);
		txtBookingId.setBounds(690, 125, 157, 35);
		pnlHead.add(txtBookingId);

		btnHistory = new JButton("History");
		btnHistory.setFocusable(false);
		btnHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnHistory.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnHistory.setBounds(882, 65, 146, 41);
		pnlHead.add(btnHistory);
/////////////////////////////////////////////////////////////////////////////////////////
		

		lblNewLabel = new JLabel("Items :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(21, 275, 95, 35);
		pnlHead.add(lblNewLabel);

		lblPrice = new JLabel("Price :");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPrice.setBounds(370, 275, 70, 35);
		pnlHead.add(lblPrice);

		txtPrice = new JTextField();
		txtPrice.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txtPrice.setEditable(false);
		txtPrice.setColumns(10);
		txtPrice.setBounds(450, 275, 201, 35);
		pnlHead.add(txtPrice);

		lblQuantity = new JLabel("Quantity :");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblQuantity.setBounds(773, 275, 100, 35);
		pnlHead.add(lblQuantity);

		txtQuantity = new JTextField();
//		txtQuantity.addFocusListener(this);
		txtQuantity.addKeyListener(this);
		txtQuantity.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txtQuantity.setColumns(10);
		txtQuantity.setBounds(883, 275, 139, 35);
		pnlHead.add(txtQuantity);

		cmbItems = new JComboBox();
		showMenuItem();
		cmbItems.setFont(new Font("Times New Roman", Font.BOLD, 20));
		cmbItems.setBounds(120, 275, 201, 35);
		cmbItems.addItemListener(this);
		pnlHead.add(cmbItems);

		btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object obj[] = { txtBookingId.getText(), cmbRoom.getSelectedItem(), txtCustomerName.getText(),
						txtDate.getText(), cmbItems.getSelectedItem().toString(), txtQuantity.getText(),
						txtPrice.getText(), txtTotal.getText() };
				dtm.addRow(obj);
			}
		});
		btnAdd.setFocusable(false);
		btnAdd.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnAdd.setBounds(757, 330, 122, 35);
		pnlHead.add(btnAdd);

		btnClear = new JButton("CANCEL");
		btnClear.setFocusable(false);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmbRoom.setSelectedIndex(0);
			}
		});
		btnClear.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnClear.setBounds(905, 330, 122, 35);
		pnlHead.add(btnClear);

		lblTotal = new JLabel("Total :");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTotal.setBounds(21, 330, 129, 35);
		pnlHead.add(lblTotal);

		txtTotal = new JTextField();
		txtTotal.setEditable(false);
		txtTotal.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txtTotal.setColumns(10);
		txtTotal.setBounds(120, 330, 201, 35);
		pnlHead.add(txtTotal);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 396, 1051, 397);
		contentPane.add(scrollPane);
		displayTable();
		scrollPane.setViewportView(table);

		setVisible(true);
	}

	// Method display Table set
	public void displayTable() {
		obj = new Object[8];
		obj[0] = "Booking ID";
		obj[1] = "Room No";
		obj[2] = "Name";
		obj[3] = "Date";
		obj[4] = "Item";
		obj[5] = "Quantity";
		obj[6] = "Price";
		obj[7] = "Total Price";
		dtm.setColumnIdentifiers(obj);
	}
	// Method display Table set

	// Getting room data from middleware
	public void showMenuItem() {
		cmbItems.addItem("Select");
		arrayListRes = jdbcRes.getMenuItem();
		for (RestaurantLibs res : arrayListRes) {
			cmbItems.addItem(res.getMenu());
		}
	}
	// Getting room data from middleware end

	// Getting room data from middleware
	public void showRoomNumber() {
		cmbRoom.addItem("Select");
		array = jdbc.showRoomNumber();
		for (int i = 0; i < array.size(); i++) {
			cmbRoom.addItem(array.get(i));
		}
	}
	// Getting room data from middleware end

	// Fetching the data of customer by passing room number to middleware
	public void showUserInfo() {
		array = jdbc.getCustomerDetails(libs);
		for (int i = 0; i < array.size(); i++) {
			txtDate.setText(LocalDate.now().toString());
			txtCustomerName.setText(libs.getCustomerName());
			txtBookingId.setText(Integer.toString(libs.getBookingId()));
			txtCheckIn.setText(libs.getCheckInDate());
		}
	}
	// Fetching the data of customer by passing room number end

	// Fetching user data while selecting the room number
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cmbRoom) {
			libs.setRoomNumber(Integer.parseInt(cmbRoom.getSelectedItem().toString()));
			showUserInfo();
		} else if (e.getSource() == cmbItems) {
			txtPrice.setText(null);
			if (cmbItems.getSelectedIndex() > 0) {
				libsRes.setMenu(cmbItems.getSelectedItem().toString());
				getItemPrice();
				getMenuId();
			}
		}
	}

	// Getting restaurant menu from middleware
	public void showServicesType() {
		cmbItems.addItem("Select");
		array = jdbc.showServiceType();
		for (int i = 0; i < array.size(); i++) {
			cmbItems.addItem(array.get(i));
		}
	}
	// Getting menu data from middleware end

	// Requesting service by sending services type to middleware
	public void showService() {
		cmbItems.removeAllItems();
		cmbItems.addItem("Select");
		array = jdbc.showService(libs);
		for (int i = 0; i < array.size(); i++) {
			cmbItems.addItem(array.get(i));
		}
	}
	// Requesting service by sending services type to middleware end

	// Requesting service by sending services type to middleware
	public void showServicePrice() {
		double rate = jdbc.showServicePrice(libs);
		txtPrice.setText(Double.toString(rate));
	}
	// Requesting service by sending services type to middleware end

	// Requesting service by sending services type to middleware
	public void getServiceId() {
		int id = jdbc.getServiceId(libs);
		libs.setServiceId(id);
	}
	// Requesting service by sending services type to middleware end

	// Requesting service by sending services type to middleware
	public void getMenuId() {
		int id = jdbcRes.getMenuId(libsRes);
		libsRes.getMenuId();
		System.out.println(libsRes.getMenuId());
	}
	// Requesting service by sending services type to middleware end

	public void getItemPrice() {
		arrayListRes = jdbcRes.getItemPrice(libsRes);
		for (RestaurantLibs libsRes : arrayListRes) {
			txtPrice.setText(Integer.toString((int) libsRes.getPrice()));
		}

	}

	// FocusListener
	@Override
	public void focusGained(FocusEvent e) {
		libsRes.setQuantity(Integer.parseInt(txtQuantity.getText()));
		libsRes.setPrice(Double.parseDouble(txtPrice.getText()));
		jdbcRes.calculateTotal(libsRes);
		txtTotal.setText(Double.toString(libsRes.getTotal()));
	}

	@Override
	public void focusLost(FocusEvent e) {
		libsRes.setQuantity(Integer.parseInt(txtQuantity.getText()));
		libsRes.setPrice(Double.parseDouble(txtPrice.getText()));
		jdbcRes.calculateTotal(libsRes);
		txtTotal.setText(Double.toString(libsRes.getTotal()));
	}
	// FocusListener end

	// KeyListener

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (cmbItems.getSelectedIndex() > 0) {
			libsRes.setQuantity(Integer.parseInt(txtQuantity.getText()));
			libsRes.setPrice(Double.parseDouble(txtPrice.getText()));
			jdbcRes.calculateTotal(libsRes);
			txtTotal.setText(Double.toString(libsRes.getTotal()));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (cmbItems.getSelectedIndex() > 0) {
			libsRes.setQuantity(Integer.parseInt(txtQuantity.getText()));
			libsRes.setPrice(Double.parseDouble(txtPrice.getText()));
			jdbcRes.calculateTotal(libsRes);
			txtTotal.setText(Double.toString(libsRes.getTotal()));
		}
	}

	// KeyListener end

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new Restaurant(); // Memory allocation
	}

}
