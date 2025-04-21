package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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

import mylibs.Global;
import mylibs.JDBCRoom;
import mylibs.Room;

//Main Class
public class RoomManagement extends JFrame implements ActionListener, ItemListener {

	// Declaration
	JPanel pnlHead, pnlMain, pnlBody, pnlObjects, pnlBtn, pnlBodyTable; // JPanel
	JLabel lblHead, lblRoomId1, lblRoomType1, lblRoomPrice, lblRoomStatus,BG; // JLabel
	JTextField txtId1, txtPrice; // JTextArea
	JComboBox cmbType, cmbStatus1; // JComboBox
	JButton btnAdd1, btnSearch1, btnUpdate1, btnClear1 ;
	
	// declaration for display Table
		DefaultTableModel dtm1 = new DefaultTableModel();
		JTable table1 = new JTable(dtm1);
		Object[] obj1;
		ArrayList<Room> roomGet;
		// declaration for display Table end

	// Extended class
	public RoomManagement() {
		setTitle("Room Management"); // Title
		setBounds(200, 120, 1000, 500); // Axis and size of the window x-axis,y-axis,width,height
		setLayout(null); // Frame Layout

		// Panel Head
		pnlHead = new JPanel(); // Initialize and memory allocation
		pnlHead.setBounds(0, 0, 1000, 50); // Axis and size of the window x-axis,y-axis,width,height
		pnlHead.setBackground(new Color(0, 0, 0)); // Set background color of a header
		pnlHead.setOpaque(true); // Set opaque of a background

		lblHead = new JLabel(); // Initialize and memory allocation
		lblHead.setText("Room Information"); // Set Title of a Label
		lblHead.setFont(new Font("Sanserif", Font.BOLD, 30)); // Change the font style type and size
		lblHead.setForeground(new Color(255, 255, 255)); // Set the color of text
		pnlHead.add(lblHead, CENTER_ALIGNMENT); // Adding label head to the panel head

		// Panel Main
		pnlMain = new JPanel();
		pnlMain.setBounds(0, 50, 1000, 450);
		pnlMain.setBackground(Color.RED);
		pnlMain.setLayout(new GridLayout(1, 2));

		pnlBody = new JPanel(); // Initialize and memory allocation
		pnlBody.setSize(500, 450);
		pnlBody.setBackground(new Color(0, 204, 255));
		pnlBody.setLayout(null);
		pnlMain.add(pnlBody);

		pnlBodyTable = new JPanel(); // Initialize and memory allocation
		pnlBodyTable.setSize(500, 450);
		pnlMain.add(pnlBodyTable);

		pnlObjects = new JPanel(); // Initialize and memory allocation
		pnlObjects.setBounds(100, 20, 300, 270); // Axis and size of the window x-axis,y-axis,width,height
		pnlObjects.setBackground(new Color(166, 137, 225,150)); // Set the background color of the panel
		pnlObjects.setLayout(null); // Set the layout of panel to null
		pnlBody.add(pnlObjects); // adding object panel to the panel body

		pnlBtn = new JPanel(); // Initialize and memory allocation
		pnlBtn.setBounds(100, 300, 300, 100); // Axis and size of the window x-axis,y-axis,width,height
		pnlBtn.setBackground(new Color(255, 255, 255, 80)); // Set the background color of the panel
		pnlBtn.setLayout(null); // Set the layout of panel to null
		pnlBody.add(pnlBtn); // adding object panel to the panel body

		// ID
		lblRoomId1 = new JLabel(); // Initialize and memory allocation
		lblRoomId1.setText("Id : "); // Set Text
		lblRoomId1.setBounds(20, 30, 200, 30); // Set axis and size of the window x-axis,y-axis,width,height
		lblRoomId1.setForeground(new Color(255, 255, 255)); // Font Color Change
		lblRoomId1.setFont(new Font("Times New Roman", Font.BOLD, 16)); // Change the font name, type, size
		pnlObjects.add(lblRoomId1); // Adding room id to the panel objects

		txtId1 = new JTextField(); // Initialize and memory allocation
		txtId1.setBounds(120, 30, 150, 30); // Set axis and size of the window x-axis,y-axis,width,height
		txtId1.setFont(new Font("Times New Roman", Font.BOLD, 20)); // Change the font name , type , size
		pnlObjects.add(txtId1); // Addimg Id to objects panel

		// Room Type
		lblRoomType1 = new JLabel(); // Initialize and memory allocation
		lblRoomType1.setText("Room Type : "); // Set Text
		lblRoomType1.setBounds(20, 80, 200, 30); // Set axis and size of the window x-axis,y-axis,width,height
		lblRoomType1.setForeground(new Color(255, 255, 255)); // Change the color of font
		lblRoomType1.setFont(new Font("Times New Roman", Font.BOLD, 16)); // Change font name, type, size
		pnlObjects.add(lblRoomType1); // Adding types to the onjects

		cmbType = new JComboBox(); // Initialize and memory allocation
		for (int i = 0; i < Global.ROOM_TYPES.length; i++) { // For loop to display all the array items
			cmbType.addItem(Global.ROOM_TYPES[i]);
		}
		cmbType.setBounds(120, 80, 150, 30); // Set axis and size of the window x-axis,y-axis,width,height
		cmbType.setFont(new Font("Times New Roman", Font.BOLD, 16)); // Change the font name, type and size
		cmbType.addItemListener(this); // Adding Actionlistener to the button
		pnlObjects.add(cmbType); // Adding type to the objects panel

		// Room Price
		lblRoomPrice = new JLabel(); // Initialize and memory allocation
		lblRoomPrice.setText("Room Price : "); // Set text
		lblRoomPrice.setBounds(20, 140, 200, 30); // Set axis and size of the window x-axis,y-axis,width,height
		lblRoomPrice.setForeground(new Color(255, 255, 255)); // Set the font color
		lblRoomPrice.setFont(new Font("Times New Roman", Font.BOLD, 16)); // Set the font name, type and size
		pnlObjects.add(lblRoomPrice); // Adding room price to the object panel

		txtPrice = new JTextField(); // Initialize and memory allocation
		txtPrice.setBounds(120, 140, 150, 30); // Set
		txtPrice.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txtPrice.setEditable(false);
		pnlObjects.add(txtPrice);

		// Room Status
		lblRoomStatus = new JLabel(); // Initialize and memory allocation
		lblRoomStatus.setText("Room Status : ");
		lblRoomStatus.setBounds(20, 200, 200, 30);
		lblRoomStatus.setForeground(new Color(255, 255, 255));
		lblRoomStatus.setFont(new Font("Times New Roman", Font.BOLD, 16));
		pnlObjects.add(lblRoomStatus);

		cmbStatus1 = new JComboBox(); // Initialize and memory allocation
		for (int i = 0; i < Global.ROOM_STATUS.length; i++) {
			cmbStatus1.addItem(Global.ROOM_STATUS[i]);
		}
		cmbStatus1.setBounds(120, 200, 150, 30);
		cmbStatus1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		pnlObjects.add(cmbStatus1);

		// Button
		btnAdd1 = new JButton("Add"); // Initialize and memory allocation
		btnAdd1.setBounds(10, 10, 80, 30);
		btnAdd1.setFocusable(false);
		btnAdd1.addActionListener(this);
		pnlBtn.add(btnAdd1);

		btnSearch1 = new JButton("Search"); // Initialize and memory allocation
		btnSearch1.setBounds(110, 10, 80, 30);
		btnSearch1.setFocusable(false);
		btnSearch1.addActionListener(this);
		pnlBtn.add(btnSearch1);

		btnUpdate1 = new JButton("Update"); // Initialize and memory allocation
		btnUpdate1.setBounds(210, 10, 80, 30);
		btnUpdate1.setFocusable(false);
		btnUpdate1.addActionListener(this);
		pnlBtn.add(btnUpdate1);

	
		btnClear1 = new JButton("Clear"); // Initialize and memory allocation
		btnClear1.setBounds(110, 60, 80, 30);
		btnClear1.setFocusable(false);
		btnClear1.addActionListener(this);
		pnlBtn.add(btnClear1);

		
		// Table
		

		// End Table

		add(pnlHead); // Adding panel head to Frame
		add(pnlMain);
		
		table1.setPreferredScrollableViewportSize(new Dimension(450,350));
		table1.setFillsViewportHeight(true);
		displayTable();
		pnlBodyTable.add(new JScrollPane(table1));
		
		table1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent ae) {
				int i = table1.getSelectedRow();
				TableModel model = table1.getModel();
				txtId1.setText(model.getValueAt(i, 0).toString());
				
				String type = model.getValueAt(i, 1).toString();
				if(type.equals("Single")) {
					cmbType.setSelectedIndex(0);
				}else if(type.equals("Double")) {
					cmbType.setSelectedIndex(1);
				}else if(type.equals("Twin")) {
					cmbType.setSelectedIndex(2);
				}
				
				txtPrice.setText(model.getValueAt(i, 2).toString());
				
				String status = model.getValueAt(i, 3).toString();
				if(status.equals("Available")) {
					cmbStatus1.setSelectedIndex(0);
				}else if(status.equals("Booked")) {
					cmbStatus1.setSelectedIndex(1);
				}else if(status.equals("Occupied")) {
					cmbStatus1.setSelectedIndex(2);
				}
				
			}
		});
		
		
		BG = new JLabel();
		Image img1 = new ImageIcon(this.getClass().getResource("/b1.jpg")).getImage();
		BG.setIcon(new ImageIcon(img1));
		BG.setBounds(0, 0,900,750);
		pnlBody.add(BG);
		

		setVisible(true); // Frame visibility
		
	}
	
	// Method display Table set
	public void displayTable() {
		obj1 = new Object[4];
		obj1[0] = "Room Number";
		obj1[1] = "Type";
		obj1[2] = "Price";
		obj1[3] = "Status";
		dtm1.setColumnIdentifiers(obj1);
		refreshTable(); // Calling refresh Table to show the database data into the table
	}
	// Method display Table set

	// Method which retrieve data from database
	public void refreshTable() {
		roomGet = new JDBCRoom().searchAll();
		dtm1.setRowCount(0);
		for (Room room : roomGet) {
			Object tmpRow[] = {room.getId(),room.getType(),room.getRate(),room.getStatus()};
			dtm1.addRow(tmpRow);
		}
	}
	// Method which retrieve data from database
	
	

	// Action Perform
	@Override
	public void actionPerformed(ActionEvent ae) {
	if (ae.getSource() == btnClear1) {
			txtId1.setText(null);
			cmbType.setSelectedIndex(0);
			txtPrice.setText(null);
			cmbStatus1.setSelectedIndex(0);
		} else if (ae.getSource() == btnAdd1) {
			// save record
			Room room = new Room();
			room.setId(Integer.parseInt(txtId1.getText()));
			room.setType(cmbType.getSelectedItem().toString());
			room.setRate(Double.parseDouble(txtPrice.getText()));
			room.setStatus(cmbStatus1.getSelectedItem().toString());

			JDBCRoom jdbc = new JDBCRoom();
			boolean result = jdbc.insert(room);
			if (result == true) {
				JOptionPane.showMessageDialog(null, "Room Added Successfully !!");
				refreshTable();
				txtId1.setText(null);
				cmbType.setSelectedIndex(0);
				txtPrice.setText(null);
				cmbStatus1.setSelectedIndex(0);
			} else {
				JOptionPane.showMessageDialog(null, "Failed to Add Room !!");
			}

		} else if (ae.getSource() == btnSearch1) {
			// Search record
			boolean result = false;
			Room room = new Room();
			int rid = Integer.parseInt(txtId1.getText());

			JDBCRoom jdbc = new JDBCRoom();
			room = jdbc.search(rid);

			if (room.getId() > 0) {
				// display room info
				String type = room.getType();
				if (type.equals("Single")) {
					cmbType.setSelectedIndex(0);
				} else if (type.equals("Double")) {
					cmbType.setSelectedIndex(1);
				} else if (type.equals("Deluxe")) {
					cmbType.setSelectedIndex(2);
				}

				double rate = room.getRate();
				txtPrice.setText(Double.toString(rate));

				String status = room.getStatus();
				// CW
				if (status.equals("Available")) {
					cmbStatus1.setSelectedIndex(0);
				} else {
					cmbStatus1.setSelectedIndex(1);
				}
				result = true;
			}

			if (result == true) {
				JOptionPane.showMessageDialog(null, "Room Found");
			} else {
				JOptionPane.showMessageDialog(null, "Room Not Found");
				cmbType.setSelectedIndex(0);
				txtPrice.setText(null);
				cmbStatus1.setSelectedIndex(0);
			}
		} else if (ae.getSource() == btnUpdate1) {
			// Edit
			// get room info
			Room room = new Room();
			room.setId(Integer.parseInt(txtId1.getText()));
			room.setType(cmbType.getSelectedItem().toString());
			room.setRate(Double.parseDouble(txtPrice.getText()));
			room.setStatus(cmbStatus1.getSelectedItem().toString());

			// send to middle-ware to edit
			JDBCRoom jdbc = new JDBCRoom();
			boolean result = jdbc.update(room); // send to middle_ware
			if (result == true) {
				JOptionPane.showMessageDialog(null, "Room Updated Successfully !!");
				refreshTable();
				txtId1.setText(null);
				cmbType.setSelectedIndex(0);
				txtPrice.setText(null);
				cmbStatus1.setSelectedIndex(0);
			} else {
				JOptionPane.showMessageDialog(null, "Failed to update room !!");
			}
		} 

			txtId1.setText(null);
			cmbType.setSelectedIndex(0);
			txtPrice.setText(null);
			cmbStatus1.setSelectedIndex(0);
		}

	

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cmbType) {
			int index = cmbType.getSelectedIndex();
			txtPrice.setText(Double.toString(Global.ROOM_RATE[index]));
		}

	}
	

	// Main Function
	public static void main(String[] args) {
		new RoomManagement(); // Memory Allocation
	}

}
