package reG;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ui.LOGIN_DASH;

public class Reg_Dash implements ActionListener {

	JButton btnRegistration;
	JPanel panel, panel1, pnlBody;
	JFrame frame;
	JLabel label, lblFname, lblLname, lblAddress, lblPostCode, lblGender, lblPhoneNumber, lblCreditCard, lblCvcNumber,
			lblEmail, lblUserName, lblPassword, lblConfirmPassword, lblLogIn;
	JTextField txtFname, txtLname, txtAddress, txtPostalCode, txtPhoneNumber, txtCreditCard, txtCvcNumber, txtEmail,
			txtUsername, txtPassword, txtConfirmPassword;
	JComboBox cmbGender;
	JPasswordField p, p1;

	public Reg_Dash() {
		 frame = new JFrame("LOTON");
			frame.setSize(1850, 750);
		//	frame.setLocationRelativeTo(null);
			frame.setLayout(null);
			frame.setExtendedState(frame.MAXIMIZED_BOTH);
			
			pnlBody = new JPanel(); // Panel Body initialize and memory allocation
			pnlBody.setBounds(325, 100, 900, 650); // Set size of the panel
			pnlBody.setBackground(new Color(255,255,255,100)); // Background Color change using the color code
			pnlBody.setLayout(null);
			frame.add(pnlBody);

			
			
			
			label = new JLabel();
			Image img = new ImageIcon(this.getClass().getResource("registration1.jpg")).getImage();
			label.setBounds(0,0,1850,800);
			label.setIcon(new ImageIcon(img));
			frame.add(label);			
			
			
			// JLabel Part

			label = new JLabel("-: REGISTRATION  Here :-");
			label.setForeground(Color.black);
			label.setFont(new Font("Lora", Font.BOLD, 28));
			label.setBounds(270,10, 350, 35);
			pnlBody.add(label);
			
			// First Name Label
			lblFname = new JLabel("First Name : ");
			lblFname.setForeground(Color.black);
			lblFname.setFont(new Font("Lora", Font.BOLD, 18));
			lblFname.setBounds(20, 70, 150, 20);
			pnlBody.add(lblFname);

			// First Name Text Field
			txtFname = new JTextField();
			txtFname.setForeground(Color.BLACK);
			txtFname.setFont(new Font("Lora", Font.BOLD, 18));
			txtFname.setBounds(130, 70, 250, 25);
			pnlBody.add(txtFname);

			// Last Name Label
			lblLname = new JLabel("Last Name : ");
			lblLname.setForeground(Color.black);
			lblLname.setFont(new Font("Lora", Font.BOLD, 18));
			lblLname.setBounds(470, 70, 150, 20);
			pnlBody.add(lblLname);

			// Last Name Text Field
			txtLname = new JTextField();
			txtLname.setForeground(Color.BLACK);
			txtLname.setFont(new Font("Lora", Font.BOLD, 18));
			txtLname.setBounds(630, 70, 250, 25);
			pnlBody.add(txtLname);

			// Address Label
			lblAddress = new JLabel("Address : ");
			lblAddress.setForeground(Color.black);
			lblAddress.setFont(new Font("Lora", Font.BOLD, 18));
			lblAddress.setBounds(20, 140, 150, 20);
			pnlBody.add(lblAddress);

			// Address Text Field
			txtAddress = new JTextField();
			txtAddress.setForeground(Color.BLACK);
			txtAddress.setFont(new Font("Lora", Font.BOLD, 18));
			txtAddress.setBounds(130, 140, 250, 25);
			pnlBody.add(txtAddress);

			// Postal Code Label
			lblPostCode = new JLabel("Postal Code : ");
			lblPostCode.setForeground(Color.black);
			lblPostCode.setFont(new Font("Lora", Font.BOLD, 18));
			lblPostCode.setBounds(470, 140, 150, 20);
			pnlBody.add(lblPostCode);

			// Last Name Text Field
			txtPostalCode = new JTextField();
			txtPostalCode.setForeground(Color.BLACK);
			txtPostalCode.setFont(new Font("Lora", Font.BOLD, 18));
			txtPostalCode.setBounds(630, 140, 250, 25);
			pnlBody.add(txtPostalCode);

			// Gender Label
			lblGender = new JLabel("Gender : ");
			lblGender.setForeground(Color.black);
			lblGender.setFont(new Font("Lora", Font.BOLD, 18));
			lblGender.setBounds(20, 210, 150, 20);
			pnlBody.add(lblGender);

			// Gender Text Field
			cmbGender = new JComboBox();
			for (int i = 0; i < Global.Gender.length; i++) {
				cmbGender.addItem(Global.Gender[i]);
			}
			cmbGender.setForeground(Color.BLACK);
			cmbGender.setFont(new Font("Lora", Font.BOLD, 18));
			cmbGender.setBounds(130, 210, 250, 25);
			pnlBody.add(cmbGender);

			// Phone Number Code Label
			lblPhoneNumber = new JLabel("Phone Number : ");
			lblPhoneNumber.setForeground(Color.black);
			lblPhoneNumber.setFont(new Font("Lora", Font.BOLD, 18));
			lblPhoneNumber.setBounds(470, 210, 150, 20);
			pnlBody.add(lblPhoneNumber);

			// Phone Number Text Field
			txtPhoneNumber = new JTextField();
			txtPhoneNumber.setForeground(Color.BLACK);
			txtPhoneNumber.setFont(new Font("Lora", Font.BOLD, 18));
			txtPhoneNumber.setBounds(630, 210, 250, 25);
			pnlBody.add(txtPhoneNumber);

			// Credit Card Label
			lblCreditCard = new JLabel("Credit Card : ");
			lblCreditCard.setForeground(Color.black);
			lblCreditCard.setFont(new Font("Lora", Font.BOLD, 18));
			lblCreditCard.setBounds(20, 280, 150, 20);
			pnlBody.add(lblCreditCard);

			// Credit Card Field
			txtCreditCard = new JTextField();
			txtCreditCard.setForeground(Color.BLACK);
			txtCreditCard.setFont(new Font("Lora", Font.BOLD, 18));
			txtCreditCard.setBounds(130, 280, 250, 25);
			pnlBody.add(txtCreditCard);

			

			// Email Label
			lblEmail = new JLabel("Email : ");
			lblEmail.setForeground(Color.black);
			lblEmail.setFont(new Font("Lora", Font.BOLD, 18));
			lblEmail.setBounds(20, 350, 150, 20);
			pnlBody.add(lblEmail);

			// Email Field
			txtEmail = new JTextField();
			txtEmail.setForeground(Color.BLACK);
			txtEmail.setFont(new Font("Lora", Font.BOLD, 18));
			txtEmail.setBounds(130, 350, 250, 25);
			pnlBody.add(txtEmail);

			// Username Label
			lblUserName = new JLabel("Username : ");
			lblUserName.setForeground(Color.black);
			lblUserName.setFont(new Font("Lora", Font.BOLD, 18));
			lblUserName.setBounds(470, 350, 150, 20);
			pnlBody.add(lblUserName);

			// Username Text Field
			txtUsername = new JTextField();
			txtUsername.setForeground(Color.BLACK);
			txtUsername.setFont(new Font("Lora", Font.BOLD, 18));
			txtUsername.setBounds(630, 350, 250, 25);
			pnlBody.add(txtUsername);

			// Password Label
			lblPassword = new JLabel("Password : ");
			lblPassword.setForeground(Color.black);
			lblPassword.setFont(new Font("Lora", Font.BOLD, 18));
			lblPassword.setBounds(20, 420, 150, 20);
			pnlBody.add(lblPassword);

			// Password Field
			txtPassword = new JPasswordField();
			txtPassword.setForeground(Color.BLACK);
			txtPassword.setFont(new Font("Lora", Font.BOLD, 18));
			txtPassword.setBounds(130, 420, 250, 25);
			pnlBody.add(txtPassword);

			// Confirm Password Label
			lblConfirmPassword = new JLabel("Confirm Password : ");
			lblConfirmPassword.setForeground(Color.black);
			lblConfirmPassword.setFont(new Font("Lora", Font.BOLD, 18));
			lblConfirmPassword.setBounds(450, 420, 200, 20);
			pnlBody.add(lblConfirmPassword);

			// Confirm Password Field
			txtConfirmPassword = new JPasswordField();
			txtConfirmPassword.setForeground(Color.BLACK);
			txtConfirmPassword.setFont(new Font("Lora", Font.BOLD, 18));
			txtConfirmPassword.setBounds(630, 420, 250, 25);
			pnlBody.add(txtConfirmPassword);

			// JButton Part

			// Registration Button
			btnRegistration = new JButton("Register");
			btnRegistration.setBounds(220, 510, 400,35);
			btnRegistration.setBackground(new Color(255,255,255,200));
			btnRegistration.setFocusable(false);
			btnRegistration.setFont(new Font("Lora", Font.BOLD, 25));
			btnRegistration.addActionListener(this);
			pnlBody.add(btnRegistration);

			// Label For LogIn
			lblLogIn = new JLabel("<<  Already a member  >>");
			
			lblLogIn.setBounds(280, 580, 350, 30);
			lblLogIn.setFont(new Font("Lora", Font.BOLD, 25));
			lblLogIn.setForeground(Color.black);
			pnlBody.add(lblLogIn);
			lblLogIn.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent ae) {
					new LOGIN_DASH();
					frame.dispose();

				}

			});


			frame.setVisible(true);
			
	
	}
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == btnRegistration) {
			regLibs reg = new regLibs();
			if ((txtFname.getText() == "") || (txtLname.getText() == "") || (txtAddress.getText() == "")
					|| (txtPostalCode.getText() == "") || (txtPhoneNumber.getText() == "")
					|| (txtCreditCard.getText() == "") || (txtEmail.getText() == "")
					|| (txtUsername.getText() == "") || (txtPassword.getText() == "")
					|| (txtConfirmPassword.getText() == "")) {
				JOptionPane.showMessageDialog(null, "Please Fill all the fields");
			} else {
				// First Name validation
				String fname = txtFname.getText();
				Validation val = new Validation();
				boolean resultFName = val.fName(fname);
				if (resultFName == true) {
					// Last Name validation
					String lname = txtLname.getText();
					boolean resultLName = val.lName(lname);
					if (resultLName == true) {
						// Postal Code / Zip Code validation
						int postal = Integer.parseInt(txtPostalCode.getText());
						boolean resultPostal = val.Postal(Integer.toString(postal));
						if (resultPostal == true) {
							// Gender Validation
							String Gender = cmbGender.getSelectedItem().toString();
							boolean resultGender = val.Gender(Gender);
							if (resultGender == true) {
								// Phone Number Validation
								String phoneNumber = txtPhoneNumber.getText();
								boolean resultPhone = val.Phone(phoneNumber);
								if (resultPhone == true) {
									// Credit Card Validation
									String creditCard = txtCreditCard.getText();
									boolean resultCredit = val.CreditCard(creditCard);
									if (resultCredit == true) {
										
											// Email Validation Check
											String email = txtEmail.getText();
											boolean resultEmail = val.Email(email);
											if (resultEmail == true) {
												// Username validation check
												String userName = txtUsername.getText();
												boolean resultUser = val.UserName(userName);
												if (resultUser == true) {
													// Password Validation Check
													String password = txtPassword.getText();
													boolean resultPass = val.Password(password);
													if (resultPass == true) {
														String conPassword = txtConfirmPassword.getText();
														if (conPassword.equals(password)) {
															reg.setfName(txtFname.getText());
															reg.setlName(txtLname.getText());
															reg.setAddress(txtAddress.getText());
															reg.setPostalCode(
																	Integer.parseInt(txtPostalCode.getText()));
															reg.setGender(cmbGender.getSelectedItem().toString());
															reg.setPhoneNumber(txtPhoneNumber.getText());
															reg.setCreditCard(txtCreditCard.getText());

															reg.setEmail(txtEmail.getText());
															reg.setUserName(txtUsername.getText());
															reg.setPassword(txtPassword.getText());
															reg.setConfirmPassword(txtConfirmPassword.getText());
															JDBCRegistration jdbcReg = new JDBCRegistration();
															boolean result = jdbcReg.insert(reg);
															if (result == true) {
																JOptionPane.showMessageDialog(null,
																		"Welcome " + reg.getfName() + " "
																				+ reg.getlName() + " to HotelLuton.");

															} else {

															}

														} else {
															JOptionPane.showMessageDialog(null,
																	"Password and confirm password does not match");
														}
													} else {
														JOptionPane.showMessageDialog(null, "Invalid Password format");
													}
													// End of password validation
												} else {
													JOptionPane.showMessageDialog(null, "Invalid Username format");
												}
												// End of username validation
											} else {
												JOptionPane.showMessageDialog(null, "Invalid Email");
											}
											// End of email validation
										
									} else {
										JOptionPane.showMessageDialog(null, "Invalid Credit Card Information");
									}
									// End of credit card validation
								} else {
									JOptionPane.showMessageDialog(null, "Invalid Phone Number");
								}
								// End of Phone number Validation
							} else {
								JOptionPane.showMessageDialog(null, "Please select a gender");
							}
							// End of Gender validation
						} else {
							JOptionPane.showMessageDialog(null, "Invalid Postal Code");
						}
						// Postal Code / Zip Code Validation
					} else {
						JOptionPane.showMessageDialog(null, "Invalid Last Name");
					}
					// End of Last Name Validation
				} else {
					JOptionPane.showMessageDialog(null, "Invalid First Name");
				}
				// End of First Name Validation

			}

		}

}
	
	
    public static void main(String[] args) {
		new Reg_Dash();

	}

}
	

