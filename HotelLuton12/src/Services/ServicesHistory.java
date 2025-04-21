package Services;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ReceptionistLibs.ReceptionistJDBC;
import ReceptionistLibs.ReceptionistLibs;
import ServicesLibs.ServicesJDBC;
import ServicesLibs.ServicesLibs;

public class ServicesHistory extends JFrame implements ActionListener {

	// Declaration
	JPanel contentPane, pnlHead;
	JTextField txtSearch;
	JLabel lblNewLabel;
	JButton btnSearch;
	JScrollPane scrollPane;

	// Declaration initializer and memory allocation
	ServicesJDBC jdbc = new ServicesJDBC();
	ServicesLibs libs = new ServicesLibs();

	// Arraylist declaration
	ArrayList<ServicesLibs> service = new ArrayList<ServicesLibs>();

	// declaration for display Table
	DefaultTableModel dtm = new DefaultTableModel();
	JTable table = new JTable(dtm);
	Object[] obj;
	private JTextField textField;
	// declaration for display Table end

	/**
	 * Create the frame.
	 */
	public ServicesHistory() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		pnlHead = new JPanel();
		pnlHead.setBounds(0, 0, 986, 157);
		pnlHead.setBackground(Color.WHITE);
		contentPane.add(pnlHead);
		pnlHead.setLayout(null);

		lblNewLabel = new JLabel("Services History");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 986, 90);
		pnlHead.add(lblNewLabel);

		txtSearch = new JTextField();
		txtSearch.setBounds(580, 110, 223, 37);
		pnlHead.add(txtSearch);
		txtSearch.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txtSearch.setColumns(10);

		btnSearch = new JButton("Search");
		btnSearch.addActionListener(this);
		btnSearch.setBounds(824, 110, 136, 33);
		pnlHead.add(btnSearch);
		btnSearch.setFocusable(false);
		btnSearch.setFont(new Font("Times New Roman", Font.BOLD, 20));

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 167, 966, 486);
		contentPane.add(scrollPane);
		displayTable();
		scrollPane.setViewportView(table);

		setVisible(true);
	}

	// Method display Table set
	public void displayTable() {
		obj = new Object[7];
		obj[0] = "Booking ID";
		obj[1] = "Room No";
		obj[2] = "Name";
		obj[3] = "Purchase Date";
		obj[4] = "Service Type";
		obj[5] = "Service Name";
		obj[6] = "Price";
		dtm.setColumnIdentifiers(obj);
		refreshTable();
	}
	// Method display Table set

	// Method which retrieve data from database
	public void refreshTable() {
		service = new ServicesJDBC().getServiceData();
		dtm.setRowCount(0);
		for (ServicesLibs libs : service) {
			Object tmpRow[] = { libs.getBookingId(), libs.getRoomNumber(), libs.getCustomerName(), libs.getDate(),
					libs.getServiceType(), libs.getServices(), libs.getPrice() };
			dtm.addRow(tmpRow);
		}
	}
	// Method which retrieve data from database

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnSearch) {
			String pass = txtSearch.getText();
			ServicesJDBC jdbc = new ServicesJDBC();
			service = jdbc.getSearchData(pass);
			dtm.setRowCount(0);
			for (ServicesLibs libs : service) {
				Object tmpRow[] = { libs.getBookingId(), libs.getRoomNumber(), libs.getCustomerName(), libs.getDate(),
						libs.getServiceType(), libs.getServices(), libs.getPrice() };
				dtm.addRow(tmpRow);
			}
		}

	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new ServicesHistory();
	}

}
