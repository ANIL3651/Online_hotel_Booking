package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Receptionist.receptionist_Dash;
import mylibs.User;
import mylibs.UserJDBC;
import reG.Global_Customer_ID;
import reG.Reg_Dash;

public class LOGIN_DASH implements ActionListener, KeyListener {
	JFrame frame;
	JLabel label, label1, label2, label3;
	JPanel panel, panel1, panel2, panel3;
	JTextField idtxt;
	JPasswordField PasswordTxt;
	JButton btnX, btnY, btnZ, btn1, btn2;
	JRadioButton r1;

	public LOGIN_DASH() {
		frame = new JFrame("LOTON");
		frame.setSize(800, 650);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null); 
		frame.setExtendedState(frame.MAXIMIZED_BOTH);

		panel1 = new JPanel();
		panel1.setBounds(570, 240, 370, 330);

//
		panel1.setBackground(new Color(10, 10, 10, 200));

		//////////

		frame.add(panel1);

		panel1.setLayout(null);

		///////////////////////////////////////////////////////////////////////////////////////////////////////

		label1 = new JLabel("        LOGIN      ");
		label1.setFont(new Font("Chiller", Font.BOLD, 55));
		label1.setBounds(0, 0, 1520, 60);
		label1.setForeground(Color.white);
		panel1.add(label1);
		panel1.setBorder(BorderFactory.createLineBorder(Color.black, 3));

		label2 = new JLabel("USER : ");
		label2.setFont(new Font("Lora", Font.BOLD, 18));
		label2.setBounds(20, 90, 90, 30);
		label2.setForeground(Color.white);

		panel1.add(label2);

		idtxt = new JTextField();
		idtxt.setBounds(140, 93, 170, 30);
		idtxt.setFont(new Font("Congenial", Font.BOLD, 25));
		panel1.add(idtxt);

		label3 = new JLabel("Password : ");
		label3.setFont(new Font("Lora", Font.BOLD, 18));

		label3.setBounds(20, 154, 170, 30);

		label3.setForeground(Color.white);

		panel1.add(label3);

		PasswordTxt = new JPasswordField();
		PasswordTxt.setFont(new Font("Congenial", Font.BOLD, 25));
		PasswordTxt.setBounds(140, 155, 170, 30);
		PasswordTxt.addKeyListener(this);
		panel1.add(PasswordTxt);

		btnX = new JButton("LOGIN");
		btnX.setBounds(20, 260, 150, 40);
		btnX.setOpaque(true);
		btnX.setBorder(BorderFactory.createLineBorder(Color.black, 3));
		btnX.setFocusable(false);
		btnX.setFont(new Font("lora", Font.BOLD, 22));
		btnX.setBackground(new Color(60, 60, 60));
		btnX.setForeground(Color.white);

		btnX.addActionListener(this);
		panel1.add(btnX);

		btnY = new JButton("Register");
		btnY.setBounds(180, 260, 150, 40);
		btnY.addActionListener(this);
		btnY.setBorder(BorderFactory.createLineBorder(Color.black, 3));
		btnY.setFont(new Font("lora", Font.BOLD, 22));
		btnY.setOpaque(true);
		panel1.add(btnY);
		btnY.setBackground(new Color(60, 60, 60));
		btnY.setFocusable(false);
		btnY.setForeground(Color.white);

		frame.setLayout(null);

		panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setBounds(0, 0, 1850, 850);
		frame.add(panel2);

		label = new JLabel("Welcome to hotel luton");
		label.setFont(new Font("Chiller", Font.BOLD, 50));
		label.setBounds(560, 190, 1520, 60);

		label.setForeground(Color.white);
		panel2.add(label);

		r1 = new JRadioButton("Show Password");
		r1.setBounds(140, 205, 120, 20);
		panel1.add(r1);

		r1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				// show password chars
				if (r1.isSelected()) {
					PasswordTxt.setEchoChar((char) 0);
				}
				// hide password chars
				else {
					PasswordTxt.setEchoChar('*');
				}
			}
		});

		label = new JLabel();
		Image img = new ImageIcon(this.getClass().getResource("/red.jpg")).getImage();
		label.setBounds(0, 0, 1850, 900);
		label.setIcon(new ImageIcon(img));
		panel2.add(label);

		frame.setVisible(true);

	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == btnX) {

			if (idtxt.getText() == null && PasswordTxt.getText() == null) {
				JOptionPane.showMessageDialog(null, "Please Enter your UserName and Password.");
			} else {
				User user = new User();
				user.setLoginName(idtxt.getText());
				user.setLoginPassword(PasswordTxt.getText());

				Global_Customer_ID.currentUser = user; // stores the user name and password on Global_Customer_ID

				user = new UserJDBC().login(user);

				if (user.getUid() > 0) {
					if (user.getRole().equals("admin")) {
						// display manager window
						JOptionPane.showMessageDialog(null, "Successfully LogIn as a Manager.");
						new receptionist_Dash();
						frame.dispose();

					} else if (user.getRole().equals("staff")) {
						JOptionPane.showMessageDialog(null, "Successfully LogIn as a Staff.");

					} else if (user.getRole().equals("Customer")) {
						JOptionPane.showMessageDialog(null, "Successfully LogIn as a Customer.");
						int uId = user.getUid();
						String uName = user.getLoginName();
						new Customer_dashh(uId,uName);

						frame.dispose();


					} else if (user.getRole().equals("corpcustomer")) {
						JOptionPane.showMessageDialog(null, "Successfully LogIn as a Corporate Customer.");

					}
				} else {
					JOptionPane.showMessageDialog(null, "Please Enter Proper User_Name and Password.");
				}
			
			
			

			}} else if (ae.getSource() == btnY) {
			new Reg_Dash();
			frame.dispose();
		}
	}
//////////////////////////////////------------------------EnterButton ___Code-----------------------------//////////////////////////////////////////////////////////////////////////
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {

			if (idtxt.getText() == null && PasswordTxt.getText() == null) {
				JOptionPane.showMessageDialog(null, "Please Enter your UserName and Password.");
			} else {
				User user = new User();
				user.setLoginName(idtxt.getText());
				user.setLoginPassword(PasswordTxt.getText());

				Global_Customer_ID.currentUser = user; // stores the user name and password on Global_Customer_ID

				user = new UserJDBC().login(user);

				if (user.getUid() > 0) {
					if (user.getRole().equals("admin")) {
						// display manager window
						JOptionPane.showMessageDialog(null, "Successfully LogIn as a Manager.");
						new receptionist_Dash();
						frame.dispose();

					} else if (user.getRole().equals("staff")) {
						JOptionPane.showMessageDialog(null, "Successfully LogIn as a Staff.");

					} else if (user.getRole().equals("Customer")) {
						JOptionPane.showMessageDialog(null, "Successfully LogIn as a Customer.");
						int uId = user.getUid();
						String uName = user.getLoginName();
						new Customer_dashh(uId,uName);

						frame.dispose();


					} else if (user.getRole().equals("corpcustomer")) {
						JOptionPane.showMessageDialog(null, "Successfully LogIn as a Corporate Customer.");
						int uId = user.getUid();
						String uName = user.getLoginName();
						new Customer_dashh(uId,uName);
						frame.dispose();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please Enter Proper User_Name and Password.");
				}
			
			
			
			
		
			
			}}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	  public static void main(String[] args) {
		  new LOGIN_DASH();
	  }
	
}
