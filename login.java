package Moloto;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


import Moloto.login;

import java.awt.Panel;
import java.awt.Canvas;
import java.awt.Label;

public class login extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	JPanel panel;
	JLabel user_label, password_label, message;
	JTextField userName_text;
	JPasswordField password_text;
	JButton submit, cancel;
	JFrame f; 
	private JFrame frame;

		
	
	
	public String hashMDP(String texte) {
		try {
			byte[] donneeOctet = texte.getBytes();
			MessageDigest monHash = MessageDigest.getInstance("SHA");
			monHash.update(donneeOctet);
			byte[] condenser = monHash.digest();
			texte = new BigInteger(condenser).toString(16);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return texte;
	}
	
	login() {
		// Username Label
		user_label = new JLabel();
		user_label.setForeground(Color.BLACK);
		user_label.setBackground(Color.BLACK);
		user_label.setBounds(88, 230, 132, 22);
		user_label.setHorizontalAlignment(SwingConstants.CENTER);
		user_label.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 20));
		user_label.setText("Email :");


		userName_text = new JTextField();
		userName_text.setBackground(Color.LIGHT_GRAY);
		userName_text.setBounds(248, 218, 336, 47);
		userName_text.setFont(new Font("Tahoma", Font.PLAIN, 18));
		userName_text.setHorizontalAlignment(SwingConstants.CENTER);
		user_label.setLabelFor(userName_text);
		// Password Label
		password_label = new JLabel();
		password_label.setForeground(Color.BLACK);
		password_label.setBounds(88, 334, 132, 22);
		password_label.setHorizontalAlignment(SwingConstants.CENTER);
		password_label.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 20));
		password_label.setText("Password :");


		password_text = new JPasswordField();
		password_text.setBackground(Color.LIGHT_GRAY);
		password_text.setBounds(248, 322, 336, 47);
		password_text.setHorizontalAlignment(SwingConstants.CENTER);
		password_text.setFont(new Font("Tahoma", Font.PLAIN, 18));

		panel = new JPanel();
		panel.setLayout(null);
		panel.add(user_label);
		panel.add(userName_text);
		panel.add(password_label);
		panel.add(password_text);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(panel, BorderLayout.CENTER);

		// Submit
		submit = new JButton("Login");
		submit.setBackground(new Color(227, 227, 227));
		submit.setForeground(SystemColor.desktop);
		submit.setFont(new Font("Corbel", Font.BOLD, 17));
		submit.setBounds(290, 436, 132, 41);
		submit.setBorder(new RoundedBorder(15));
		panel.add(submit);
		message = new JLabel();
		message.setBackground(Color.DARK_GRAY);
		message.setBounds(185, 379, 378, 47);
		message.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(message);
		
		String url = "login.png";
		ImageIcon image = new ImageIcon(url);
		JLabel label = new JLabel();
		label.setBounds(260, 24, 209, 162);
		label.setIcon(new ImageIcon("C:\\Users\\Trilesh\\eclipse-workspace\\MoLoto\\login.png"));
		panel.add(label);
		
		

		// Adding the listeners to components..
		submit.addActionListener(this);
		setTitle("Veuillez Vous Connectez!!");
		setSize(721,580);
		setVisible(true);
	}
	public static void main(String[] args) {
		new login();
	}
	@Override
	public void actionPerformed(ActionEvent ae) {

		// create our mysql database connection
		String myUrl = "jdbc:mysql://localhost/moloto";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(myUrl, "root", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// our SQL SELECT query. 
		// if you only need a few columns, specify them by name instead of using "*"
		
		String userName = userName_text.getText().toLowerCase();
		String password = password_text.getText();
		
		String query = "SELECT * FROM utilisateur WHERE email = '"+userName+"' ";
		
		// create the java statement
		Statement st = null;
		try {
			st = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// execute the query, and get a java resultset
		ResultSet rs = null;
		try {
			rs = st.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		// iterate through the java resultset
		try {
			while (rs.next())
			{
//				int id = rs.getInt("id");
//				String Nom = rs.getString("nom");
//				String Prenom = rs.getString("prenom");
				String Email = rs.getString("email");
				String Mdp = rs.getString("mdp");
				String Role = rs.getString("role");
				
			
				if (userName.trim().equals(Email)&& Role.equals ("Administrateur") && hashMDP(password).trim().equals(Mdp)) {
					admin page = new admin();
					page.getFrame().setVisible(true);
					dispose();
					
					
				} else if(userName.trim().equals(Email)&& Role.equals ("Vendeur") && hashMDP(password).trim().equals(Mdp)){
					accueil_vendeur page = new accueil_vendeur();
					page.getFrame().setVisible(true);
					dispose();
					
				} else if(userName.trim().equals(Email)&& Role.equals ("Manager") && hashMDP(password).trim().equals(Mdp)){
					accueil_manager page = new accueil_manager();
					page.getFrame().setVisible(true);
					dispose();
				}
			
				// print the results
//				System.out.format("%s, %s, %s, %s, %s, %s,\n", id, Nom, Prenom, Email, Mdp, Role);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void hell(){
		JPanel frame;
		frame = new JPanel();
		frame.setLayout(null);
		frame.setBounds(100, 100, 1186, 604);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBackground(Color.GREEN);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Showcard Gothic", Font.PLAIN, 26));
		frame.add(lblNewLabel);
	}

	public JFrame getFrame() {
		return getFrame();
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
