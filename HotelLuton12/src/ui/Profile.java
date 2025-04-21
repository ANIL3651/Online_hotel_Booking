package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import mylibs.User;
import mylibs.UserJDBC;
import reG.Global_Customer_ID;

public class Profile implements ActionListener {


	JButton btn,btn1;
 JPanel panel ,panel1;
 JFrame frame;
 JLabel Profile,label , label1,label2,label3,label4,label5,label6,label7,label8,label9;
 JTextField txt ,txt1,txt2,txt3,txt4,txt6,txt5;
 
	public Profile(){
		 frame = new JFrame("LOTON");
			frame.setSize(850, 650);
			frame.setResizable(false);
			frame.setLocationRelativeTo(null);
			frame.setLayout(null);
			
			panel = new JPanel();
			panel.setBounds(100,80,650,380);
			panel.setBorder(BorderFactory.createLineBorder(Color.black,3));

			panel.setBackground(new Color(38,107,215,210));
			panel.setLayout(null);
			frame.add(panel);
			
			
			
			label1 = new JLabel("Profile");
			label1.setBounds(280,5,200,50);
			label1.setForeground(Color.white);
			label1.setFont(new Font("Verdana",Font.BOLD,27));
			
			panel.add(label1);
			
			label2 = new JLabel("First Name: ");
			label2.setBounds(10,55,180,50);
			label2.setForeground(Color.white);
			
			label2.setFont(new Font("Verdana",Font.BOLD,22));
			panel.add(label2);
			
			
			txt = new JTextField();
			txt.setBounds(170,67,130,35);
			txt.setText(Global_Customer_ID.currentUser.getfName());
			panel.add(txt);
			//txt.setEditable(false);
			txt.setFont(new Font("Verdana",Font.BOLD,18));

			txt.addActionListener(this);
			
			
			
			label8 = new JLabel("Last Name:");
			label8.setBounds(320,55,150,60);
			label8.setFont(new Font("Verdana",Font.BOLD,22));
			panel.add(label8);
			label8.setForeground(Color.white);
			
			
			txt6 = new JTextField();
			txt6.setBounds(479,67,150,35);
			txt6.setText(Global_Customer_ID.currentUser.getlName());
			panel.add(txt6);
			txt6.addActionListener(this);
			//txt6.setEditable(false);
			txt6.setFont(new Font("Verdana",Font.BOLD,18));

			
			
			
//
			label4 = new JLabel("Phone No: ");
			label4.setBounds(10,100,150,60);
			label4.setFont(new Font("Verdana",Font.BOLD,22));
			panel.add(label4);
			label4.setForeground(Color.white);
			
			txt2 = new JTextField();
			txt2.setBounds(170,117,130,35);
			txt2.setFont(new Font("Verdana",Font.BOLD,15));

			txt2.setText(Global_Customer_ID.currentUser.getMobile());

			panel.add(txt2);
			
			
//			
			label5 = new JLabel("Email: ");
			label5.setBounds(320,105,160,50);
			label5.setFont(new Font("Verdana",Font.BOLD,22));
			panel.add(label5);
			label5.setForeground(Color.white);
			
			txt3 = new JTextField();
			txt3.setBounds(479,117,150,35);
			//txt3.setEditable(false);

			txt3.setText(Global_Customer_ID.currentUser.getEmail());

			panel.add(txt3);
//
			
			label6 = new JLabel("User Name:");
			label6.setBounds(10,200,160,50);
			label6.setFont(new Font("Verdana",Font.BOLD,22));
			panel.add(label6);
			label6.setForeground(Color.white);
			
			
			
			
			
			txt4 = new JTextField();
			txt4.setBounds(170,213,130,35);
		//	txt4.setEditable(false);
			txt4.setFont(new Font("Verdana",Font.BOLD,18));

			txt4.setText(Global_Customer_ID.currentUser.getLoginName());

			panel.add(txt4);
			//		
	
	//
			label7 = new JLabel("Password :");
			label7.setBounds(320,200,160,50);
			label7.setFont(new Font("Verdana",Font.BOLD,22));
			panel.add(label7);
			label7.setForeground(Color.white);
			
			txt5 = new JPasswordField();
			txt5.setBounds(479,213,150,35);
//			txt5.setEditable(false);
			txt5.setFont(new Font("Verdana",Font.BOLD,18));

			txt5.setText(Global_Customer_ID.currentUser.getLoginPassword());

			panel.add(txt5);
		
			
			//logout
			btn1 = new JButton("Update");
			btn1.setBounds(260,310, 180, 40);
			btn1.setBackground(new Color(38,107,165));
			btn1.setForeground(Color.black);
			
			btn1.setFont(new Font("Verdana", Font.BOLD, 28));
			btn1.setBorder(BorderFactory.createLineBorder(Color.black,3));

			btn1.setOpaque(true);
			btn1.addActionListener(new ActionListener() {
	@Override 
			public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==btn1) {
			String password=txt5.getText();
			String fname = txt.getText();
			String lname = txt6.getText();
			String phone = txt2.getText();
			String email = txt3.getText();
			User bio=new User();
			bio.setUid(Global_Customer_ID.currentUser.getUid());
			bio.setLoginPassword(password);
			bio.setfName(fname);
			bio.setlName(lname);
			bio.setMobile(phone);
			bio.setEmail(email);
			UserJDBC jdbc=new UserJDBC();
			boolean result=jdbc.updatePassword(bio);
			if(result==true) {
				JOptionPane.showMessageDialog(null, "Data Updated");
			}
			

			
			
		}
		
	}
			
	});
		
			//
			panel.add(btn1);
		
			
			
			
				
			Profile = new JLabel();
			Profile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProFF1.jpg")));
			Profile.setBounds(0, 0, 850, 650);
			frame.add(Profile);	
				frame.setVisible(true);

	}
	public static void main(String[] args) {
		new Profile();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
