package Moloto;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import Moloto.ajouter_client;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
public class ajouter_client {
	private static final long serialVersionUID = 1L;
	JFrame frame;
	private JTextField textField_Nom;
	private JTextField textField_PreNom;
	private JTextField textadresse;
	private JTextField textField_Email;
	private JTextField textcontact;
	private JTextField textdob;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ajouter_client window = new ajouter_client();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	/**
	 * Create the application.
	 */
	public ajouter_client() {
		initialize();
	}


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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1064,568);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Ajouter un client");
		lblNewLabel.setBounds(419, 11, 236, 45);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));

		textField_Nom = new JTextField();
		textField_Nom.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_Nom.setBounds(535, 114, 207, 35);
		panel.add(textField_Nom);
		textField_Nom.setColumns(10);

		textField_PreNom = new JTextField();
		textField_PreNom.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_PreNom.setBounds(535, 159, 207, 35);
		panel.add(textField_PreNom);
		textField_PreNom.setColumns(10);

		textadresse = new JTextField();
		textadresse.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textadresse.setBounds(535, 211, 207, 35);
		panel.add(textadresse);
		textadresse.setColumns(10);

		textField_Email = new JTextField();
		textField_Email.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_Email.setBounds(535, 256, 207, 35);
		panel.add(textField_Email);
		textField_Email.setColumns(10);

		textcontact = new JTextField();
		textcontact.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textcontact.setBounds(535, 312, 207, 35);
		panel.add(textcontact);
		textcontact.setColumns(10);

		JLabel lblNewLabel_Nom = new JLabel("Nom");
		lblNewLabel_Nom.setBackground(Color.BLACK);
		lblNewLabel_Nom.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_Nom.setForeground(Color.BLACK);
		lblNewLabel_Nom.setBounds(351, 122, 72, 17);
		panel.add(lblNewLabel_Nom);

		JLabel lblNewLabel_Role = new JLabel("Voiture");
		lblNewLabel_Role.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_Role.setForeground(Color.BLACK);
		lblNewLabel_Role.setBounds(351, 420, 72, 22);
		panel.add(lblNewLabel_Role);

		JLabel lblNewLabel_Email = new JLabel("Email");
		lblNewLabel_Email.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_Email.setForeground(Color.BLACK);
		lblNewLabel_Email.setBounds(351, 261, 72, 22);
		panel.add(lblNewLabel_Email);

		JLabel lblNewLabel_Mdp = new JLabel("Adresse");
		lblNewLabel_Mdp.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_Mdp.setForeground(Color.BLACK);
		lblNewLabel_Mdp.setBounds(351, 214, 174, 22);
		panel.add(lblNewLabel_Mdp);

		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(464, 477, 132, 35);
		panel.add(btnNewButton);

		JComboBox comboBox_voiture = new JComboBox();
		comboBox_voiture.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_voiture.setModel(new DefaultComboBoxModel(new String[] {"Lamborghini", "Jaguar", "Maserati", "Porsche"}));
		comboBox_voiture.setBounds(535, 415, 207, 35);
		panel.add(comboBox_voiture);

		JLabel lblNewLabel_Nom_1 = new JLabel("Prénom");
		lblNewLabel_Nom_1.setForeground(Color.BLACK);
		lblNewLabel_Nom_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_Nom_1.setBackground(Color.BLACK);
		lblNewLabel_Nom_1.setBounds(351, 167, 105, 17);
		panel.add(lblNewLabel_Nom_1);

		JLabel lblNewLabel_Nom_2 = new JLabel("Num\u00E9ro de telephone");
		lblNewLabel_Nom_2.setForeground(Color.BLACK);
		lblNewLabel_Nom_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_Nom_2.setBackground(Color.BLACK);
		lblNewLabel_Nom_2.setBounds(351, 320, 174, 17);
		panel.add(lblNewLabel_Nom_2);
		
		JButton btnNewButton_1 = new JButton("Retour");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client page = new client();
				page.getFrame().setVisible(true);
				frame.setVisible(false);
				frame.dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBounds(42, 40, 103, 35);
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel_Nom_2_1 = new JLabel("Date de naissance");
		lblNewLabel_Nom_2_1.setForeground(Color.BLACK);
		lblNewLabel_Nom_2_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_Nom_2_1.setBackground(Color.BLACK);
		lblNewLabel_Nom_2_1.setBounds(351, 372, 164, 17);
		panel.add(lblNewLabel_Nom_2_1);
		
		textdob = new JTextField();
		textdob.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textdob.setColumns(10);
		textdob.setBounds(535, 370, 207, 35);
		panel.add(textdob);
		


		btnNewButton.addActionListener((ActionListener) new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				try { 
					
					
					
					String nom =textField_Nom.getText();
					String prenom =textField_PreNom.getText();
					String adresse =textadresse.getText();
					String email =textField_Email.getText();
					String contact =textcontact.getText();
					String dob =textdob.getText();
					String voiture =comboBox_voiture.getSelectedItem().toString();
					

					

					String myUrl = "jdbc:mysql://localhost/moloto";
					Class.forName("com.mysql.jdbc.Driver");

					Connection conn = (Connection) DriverManager.getConnection(myUrl,"root","");
					Statement st = (Statement) conn.createStatement();
					if (nom.matches("[\\p{Lu}\\p{M}][\\p{L}\\p{M},.'-]+(?: [\\p{L}\\p{M},.'-]+)*") && prenom.matches("[\\p{Lu}\\p{M}][\\p{L}\\p{M},.'-]+(?: [\\p{L}\\p{M},.'-]+)*") && adresse.matches("[\\p{Lu}\\p{M}][\\p{L}\\p{M},.'-]+(?: [\\p{L}\\p{M},.'-]+)*") && email.matches("^[A-Za-z0-9+_.-]+@(.+)$") && contact.matches("^[0-9, ]+$") && dob.matches("^[0-9, ]+/") && textField_Nom.getText().trim().isEmpty() && textField_PreNom.getText().trim().isEmpty() && textadresse.getText().trim().isEmpty() && textField_Email.getText().trim().isEmpty() && textcontact.getText().trim().isEmpty() && textdob.getText().trim().isEmpty()) { 

					st.executeUpdate("INSERT INTO `client`(nom,prenom,adresse,email,contact,dob,Voiture) VALUE ('"+nom+"','"+prenom+"', '"+adresse+"' ,'"+email+"','"+contact+"','"+dob+"', '"+voiture+"')");
					conn.close();
					client page = new client();
					page.getFrame().setVisible(true);
					frame.setVisible(false);
					frame.dispose();
					}else {
						JFrame f;  
						f=new JFrame();
						 JOptionPane.showMessageDialog(f,"Erreur, Veuillez re-inserer les champs."); 
					}
				} catch (Exception e) { 
					System.err.println(e.getMessage()); 
				}
			}
		});
	} 
}
