package invoice;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import invoiceLibs.InvoiceJDBC;
import invoiceLibs.InvoiceLibs;

public class Invoice extends JFrame implements ActionListener {

	JPanel contentPane, panel, pnlDetails, pnlButton;
	JLabel BG,lblName, lblLogo, lblAddress, lblTax, lblBookingId, lblCustomerName, lblCheckinDate, lblRoomNumber,
			lblBillDate, lblSubTotal, lblDiscount, lblServiceCharge, lblVat, lblTotal, lblStatus;
	JTextField txtBookingId, txtCustName, txtCheckIn, txtBillDate, txtSubTotal, txtDiscountPercent, txtDiscount,
			txtServiceCharge, txtVat, txtTotal;
	JComboBox cmbRoomNumber, cmbStatus;

	ArrayList<InvoiceLibs> arrLib = new ArrayList<InvoiceLibs>();
	ArrayList arrLibs = new ArrayList();
	InvoiceJDBC jdbc = new InvoiceJDBC();

	// declaration for display Table
	DefaultTableModel dtm = new DefaultTableModel();
	JTable table = new JTable(dtm);
	Object[] obj;

	int rowCount;
	double sum = 0.0, discount = 0.0;

	/**
	 * Create the frame.
	 */
	public Invoice() {
		setTitle("Billing Window");
		setBounds(150, 20, 1300, 785);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
//	panel.setBackground(new Color(166, 137, 225,150));
		
		panel.setBounds(0, 0, 1300, 800);
		contentPane.add(panel);
		panel.setLayout(null);

		lblName = new JLabel("Hotel Luton");
		lblName.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblName.setForeground(Color.white);
		lblName.setBounds(542, 0, 974, 33);
		panel.add(lblName);

		lblLogo = new JLabel();
		Image imgA= new ImageIcon(this.getClass().getResource("/hotel (1).png")).getImage();
		lblLogo.setBounds(30,0,100,100);
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(new ImageIcon(imgA));
		lblLogo.setLayout(null);
		
		panel.add(lblLogo);

		lblAddress = new JLabel("The Mall, Library Rd, Luton LU1 2TR, United Kingdom");
		lblAddress.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblAddress.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddress.setBounds(150, 30, 979, 43);
		lblAddress.setForeground(Color.white);
		panel.add(lblAddress);

		lblTax = new JLabel("VAT INVOICE");
		lblTax.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblTax.setBounds(532, 81, 974, 33);
		lblTax.setForeground(Color.white);
		panel.add(lblTax);

		pnlDetails = new JPanel();
		pnlDetails.setBackground(new Color(166, 137, 225,150));
		pnlDetails.setBounds(60, 115, 1166, 133);
		panel.add(pnlDetails);
		pnlDetails.setLayout(null);

		lblBookingId = new JLabel("Booking Id :");
		lblBookingId.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblBookingId.setBounds(10, 12, 150, 35);
		lblBookingId.setForeground(Color.white);
		pnlDetails.add(lblBookingId);

		txtBookingId = new JTextField();
		txtBookingId.setEditable(false);
		txtBookingId.setBounds(170, 12, 169, 35);
		pnlDetails.add(txtBookingId);
		txtBookingId.setColumns(10);

		lblCustomerName = new JLabel("Customer Name :");
		lblCustomerName.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblCustomerName.setBounds(422, 10, 150, 35);
		lblCustomerName.setForeground(Color.white);
		pnlDetails.add(lblCustomerName);

		txtCustName = new JTextField();
		txtCustName.setEditable(false);
		txtCustName.setColumns(10);
		txtCustName.setBounds(595, 10, 169, 35);
		pnlDetails.add(txtCustName);

		lblCheckinDate = new JLabel("CheckIn Date :");
		lblCheckinDate.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblCheckinDate.setBounds(422, 86, 133, 35);
		lblCheckinDate.setForeground(Color.white);
		pnlDetails.add(lblCheckinDate);

		txtCheckIn = new JTextField();
		txtCheckIn.setEditable(false);
		txtCheckIn.setColumns(10);
		txtCheckIn.setBounds(565, 88, 169, 35);
		pnlDetails.add(txtCheckIn);

		lblRoomNumber = new JLabel("Room Number :");
		lblRoomNumber.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblRoomNumber.setBounds(812, 10, 150, 35);
		lblRoomNumber.setForeground(Color.white);
		pnlDetails.add(lblRoomNumber);

		lblBillDate = new JLabel("Bill Date :");
		lblBillDate.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblBillDate.setBounds(10, 86, 150, 35);
		lblBillDate.setForeground(Color.white);
		pnlDetails.add(lblBillDate);

		txtBillDate = new JTextField();
		txtBillDate.setEditable(false);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		txtBillDate.setText(formatter.format(date));
		txtBillDate.setColumns(10);
		txtBillDate.setBounds(170, 88, 169, 35);
		pnlDetails.add(txtBillDate);

		cmbRoomNumber = new JComboBox();
		cmbRoomNumber.addActionListener(this);
		cmbRoomNumber.removeAllItems();
		arrLibs = jdbc.showRoomNumber();
		cmbRoomNumber.addItem("Select");
		for (int i = 0; i < arrLibs.size(); i++) {
			cmbRoomNumber.addItem(arrLibs.get(i));
		}
		cmbRoomNumber.setBounds(954, 10, 169, 35);
		pnlDetails.add(cmbRoomNumber);

		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InvoiceLibs libs = new InvoiceLibs();
				libs.setBillDate(txtBillDate.getText());
				libs.setStatus(cmbStatus.getSelectedItem().toString());
				libs.setSubTotal(Double.parseDouble(txtSubTotal.getText()));
				libs.setDiscount(Double.parseDouble(txtDiscount.getText()));
				libs.setServiceCharge(Double.parseDouble(txtServiceCharge.getText()));
				libs.setVatCharge(Double.parseDouble(txtVat.getText()));
				libs.setTotal(Double.parseDouble(txtTotal.getText()));
				boolean result = jdbc.insertIntoInvoice(libs);
				if(result==true) {
					JOptionPane.showMessageDialog(null, "Bill saved successfully !!");
				}else {
					JOptionPane.showMessageDialog(null, "Failed to save bill !!");
				}
		
				
				
				PrinterJob job = PrinterJob.getPrinterJob();
	            job.setJobName("Print Data");
	            
	            job.setPrintable(new Printable(){
	            public int print(Graphics pg,PageFormat pf, int pageNum){
	                    pf.setOrientation(PageFormat.LANDSCAPE);
	                 if(pageNum > 0){
	                    return Printable.NO_SUCH_PAGE;
	                }
	                
	                Graphics2D g2 = (Graphics2D)pg;
	                g2.translate(pf.getImageableX(), pf.getImageableY());
	                g2.scale(0.47,0.47);
	                
	                panel.print(g2);
	         
	               
	                return Printable.PAGE_EXISTS;
	                         
	                
	            }
	    });
	            boolean ok = job.printDialog();
	        if(ok){
	        try{
	            
	        job.print();
	        }
	        catch (PrinterException ex){
		ex.printStackTrace();
	}
	        }
			
			
			}
			
			
			});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton.setBounds(1038, 86, 85, 35);
		pnlDetails.add(btnNewButton);

		lblStatus = new JLabel("Status :");
		lblStatus.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblStatus.setForeground(Color.white);
		lblStatus.setBounds(764, 86, 79, 35);
		pnlDetails.add(lblStatus);

		cmbStatus = new JComboBox();
		String type[] = { "Pending", "Paid" };
		for (int i = 0; i < type.length; i++) {
			cmbStatus.addItem(type[i]);
		}
		cmbStatus.setBounds(842, 86, 169, 35);
		pnlDetails.add(cmbStatus);

		pnlButton = new JPanel();
		pnlButton.setBackground(new Color(166, 137, 225,150));
		pnlButton.setBounds(60, 558, 1166, 190);
		panel.add(pnlButton);
		pnlButton.setLayout(null);

		lblSubTotal = new JLabel("Sub Total :");
		lblSubTotal.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblSubTotal.setForeground(Color.white);
		lblSubTotal.setBounds(519, 10, 108, 31);
		pnlButton.add(lblSubTotal);

		txtSubTotal = new JTextField();
		txtSubTotal.setBounds(619, 12, 125, 31);
		
		pnlButton.add(txtSubTotal);
		
		txtSubTotal.setColumns(10);

		lblDiscount = new JLabel("Discount :");
		lblDiscount.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblDiscount.setBounds(819, 19, 87, 31);
		lblDiscount.setForeground(Color.white);
		pnlButton.add(lblDiscount);

		txtDiscountPercent = new JTextField();
		txtDiscountPercent.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					calculateDiscount();
					calculateServiceCharge();
					calculateVat();
				}
			}
		});
		txtDiscountPercent.setColumns(10);
		txtDiscountPercent.setBounds(954, 15, 55, 31);
		pnlButton.add(txtDiscountPercent);

		txtDiscount = new JTextField();
		txtDiscount.setColumns(10);
		txtDiscount.setBounds(1019, 15, 125, 31);
		pnlButton.add(txtDiscount);

		lblServiceCharge = new JLabel("Service Charge 10%  :");
		lblServiceCharge.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblServiceCharge.setBounds(819, 53, 190, 31);
		lblServiceCharge.setForeground(Color.white);
		pnlButton.add(lblServiceCharge);

		txtServiceCharge = new JTextField();
		txtServiceCharge.setBounds(1019, 55, 125, 31);
		pnlButton.add(txtServiceCharge);
		txtServiceCharge.setColumns(10);

		lblVat = new JLabel("VAT 13% :");
		lblVat.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblVat.setBounds(819, 90, 190, 31);
		lblVat.setForeground(Color.white);
		pnlButton.add(lblVat);

		txtVat = new JTextField();
		txtVat.setColumns(10);
		txtVat.setBounds(1019, 92, 125, 31);
		pnlButton.add(txtVat);

		lblTotal = new JLabel("Total :");
		lblTotal.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTotal.setForeground(Color.white);
		lblTotal.setBounds(819, 130, 190, 31);
		pnlButton.add(lblTotal);

		txtTotal = new JTextField();
		txtTotal.setColumns(10);
		txtTotal.setBounds(1019, 132, 125, 31);
		pnlButton.add(txtTotal);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(60, 258, 1166, 290);
		panel.add(scrollPane);
		displayTable();
		scrollPane.setViewportView(table);

		
		
		
		
		BG = new JLabel();
		Image img1 = new ImageIcon(this.getClass().getResource("/b1.jpg")).getImage();
		BG.setIcon(new ImageIcon(img1));
		BG.setBounds(0, 0,1400,750);
		panel.add(BG);
		
		setVisible(true);
		}

		// Method display Table set
		public void displayTable() {
			obj = new Object[6];
			obj[0] = "Particulars";
			obj[1] = "Purchase Date";
			obj[2] = "Description";
			obj[3] = "Rate";
			obj[4] = "Quantity";
			obj[5] = "Total";

			dtm.setColumnIdentifiers(obj);
		}
		// Method display Table set

		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			new Invoice();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == cmbRoomNumber) {
				dtm.setRowCount(0);
				if (cmbRoomNumber.getSelectedItem().equals("Select")) {
					txtCustName.setText(null);
					txtCheckIn.setText(null);
				} else {
					int roomNo = Integer.parseInt(cmbRoomNumber.getSelectedItem().toString());
					arrLib = jdbc.showCustomerDetails(roomNo);
					for (InvoiceLibs libs : arrLib) {
						txtBookingId.setText(Integer.toString(libs.getBookingId()));
						txtCustName.setText(libs.getCustName());
						txtCheckIn.setText(libs.getCheckInDate());
					}

					arrLib = jdbc.getRoomDetailBill(Integer.parseInt(txtBookingId.getText()));
					for (InvoiceLibs invoice : arrLib) {
						Object obj[] = { invoice.getRoomType(), invoice.getRoomLease(),
								"Total stay days : " + invoice.getDays()+" days", invoice.getRoomprice(), "",
								invoice.getRoomprice() * invoice.getDays() };
						dtm.addRow(obj);
					}

					arrLib = jdbc.getServiceDetails(Integer.parseInt(txtBookingId.getText()));
					for (InvoiceLibs invoice : arrLib) {
						Object obj[] = { invoice.getParticulars(), invoice.getPurchaseDate(),
								invoice.getDescription() + " Service", invoice.getRate(), 1, invoice.getRate() * 1 };
						dtm.addRow(obj);
					}

					arrLib = jdbc.getRestaurantDetails(Integer.parseInt(txtBookingId.getText()));
					for (InvoiceLibs invoice : arrLib) {
						Object obj[] = { invoice.getParticulars(), invoice.getPurchaseDate(),
								invoice.getDescription() + " Service", invoice.getRate(), invoice.getQuantity(),
								invoice.getRate() * invoice.getQuantity() };
						dtm.addRow(obj);
					}

					arrLib = jdbc.getBarDetails(Integer.parseInt(txtBookingId.getText()));
					for (InvoiceLibs invoice : arrLib) {
						Object obj[] = { invoice.getParticulars(), invoice.getPurchaseDate(),
								invoice.getDescription() + " Service", invoice.getRate(), invoice.getQuantity(),
								invoice.getRate() * invoice.getQuantity() };
						dtm.addRow(obj);
					}
					calculateTotal();
				}
			}

		}

		public void calculateTotal() {
			for (int i = 0; i < dtm.getRowCount(); i++) {
				sum += Double.parseDouble(dtm.getValueAt(i, 5).toString());
			}
			txtSubTotal.setText(Double.toString(sum));
			sum = 0;
		}

		public void calculateDiscount() {
			int temp = Integer.parseInt(txtDiscountPercent.getText());
			double tem = Double.parseDouble(txtSubTotal.getText());
			discount = tem * temp / 100;
			txtDiscount.setText(Double.toString(discount));
		}

		public void calculateServiceCharge() {
			double tmpsubTotal = Double.parseDouble(txtSubTotal.getText());
			double tmpDiscount = Double.parseDouble(txtDiscount.getText());
			double amount = tmpsubTotal - tmpDiscount;
			double serviceAmount = amount * 10 / 100;
			txtServiceCharge.setText(Double.toString(serviceAmount));
		}

		public void calculateVat() {
			double tmpsubTotal = Double.parseDouble(txtSubTotal.getText());
			double tmpDiscount = Double.parseDouble(txtDiscount.getText());
			double tmpServiceAmt = Double.parseDouble(txtServiceCharge.getText());
			double vatAmount = tmpsubTotal - tmpDiscount + tmpServiceAmt;
			double vatAmt = vatAmount * 13 / 100;
			double total = tmpsubTotal - tmpDiscount + tmpServiceAmt + vatAmt;
			txtVat.setText(Double.toString(vatAmt));
			txtTotal.setText(Double.toString(total));
		}

	}
