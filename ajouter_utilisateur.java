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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import Moloto.ajouter_utilisateur;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
public class ajouter_utilisateur {
	private static final long serialVersionUID = 1L;
	JFrame frame;
	private JTextField textField_Nom;
	private JTextField textField_PreNom;
	private JTextField textField_Mdp;
	private JTextField textField_Email;
	private JTextField textField_Salaire;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ajouter_utilisateur window = new ajouter_utilisateur();
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
	public ajouter_utilisateur() {
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

		JLabel lblNewLabel = new JLabel("Cre\u00E9 un utilisateur");
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

		textField_Mdp = new JPasswordField();
		textField_Mdp.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_Mdp.setBounds(535, 211, 207, 35);
		panel.add(textField_Mdp);
		textField_Mdp.setColumns(10);

		textField_Email = new JTextField();
		textField_Email.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_Email.setBounds(535, 256, 207, 35);
		panel.add(textField_Email);
		textField_Email.setColumns(10);

		textField_Salaire = new JTextField();
		textField_Salaire.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_Salaire.setBounds(535, 312, 207, 35);
		panel.add(textField_Salaire);
		textField_Salaire.setColumns(10);

		JLabel lblNewLabel_Nom = new JLabel("Nom");
		lblNewLabel_Nom.setBackground(Color.BLACK);
		lblNewLabel_Nom.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_Nom.setForeground(Color.BLACK);
		lblNewLabel_Nom.setBounds(377, 122, 46, 17);
		panel.add(lblNewLabel_Nom);

		JLabel lblNewLabel_Role = new JLabel("Role");
		lblNewLabel_Role.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_Role.setForeground(Color.BLACK);
		lblNewLabel_Role.setBounds(377, 372, 46, 22);
		panel.add(lblNewLabel_Role);

		JLabel lblNewLabel_Email = new JLabel("Email");
		lblNewLabel_Email.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_Email.setForeground(Color.BLACK);
		lblNewLabel_Email.setBounds(377, 261, 46, 22);
		panel.add(lblNewLabel_Email);

		JLabel lblNewLabel_Mdp = new JLabel("Mot de Passe");
		lblNewLabel_Mdp.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_Mdp.setForeground(Color.BLACK);
		lblNewLabel_Mdp.setBounds(377, 214, 148, 22);
		panel.add(lblNewLabel_Mdp);

		JButton btnNewButton = new JButton("Crée");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(464, 467, 132, 45);
		panel.add(btnNewButton);

		JComboBox comboBox_Role = new JComboBox();
		comboBox_Role.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_Role.setModel(new DefaultComboBoxModel(new String[] {"Administrateur", "Vendeur", "Manager", "Resource Humaine"}));
		comboBox_Role.setBounds(535, 367, 207, 35);
		panel.add(comboBox_Role);

		JLabel lblNewLabel_Nom_1 = new JLabel("Prénom");
		lblNewLabel_Nom_1.setForeground(Color.BLACK);
		lblNewLabel_Nom_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_Nom_1.setBackground(Color.BLACK);
		lblNewLabel_Nom_1.setBounds(377, 167, 79, 17);
		panel.add(lblNewLabel_Nom_1);

		JLabel lblNewLabel_Nom_2 = new JLabel("Salaire");
		lblNewLabel_Nom_2.setForeground(Color.BLACK);
		lblNewLabel_Nom_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_Nom_2.setBackground(Color.BLACK);
		lblNewLabel_Nom_2.setBounds(377, 320, 103, 17);
		panel.add(lblNewLabel_Nom_2);
		
		JButton btnNewButton_1 = new JButton("Retour");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				utilisateur page = new utilisateur();
				page.getFrame().setVisible(true);
				frame.setVisible(false);
				frame.dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBounds(42, 40, 103, 35);
		panel.add(btnNewButton_1);


		btnNewButton.addActionListener((ActionListener) new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				try { 
					
					String nom =textField_Nom.getText();
					String prenom =textField_PreNom.getText();
					String mdp =hashMDP(textField_Mdp.getText());
					
					String email =textField_Email.getText();
					
					String salaire =textField_Salaire.getText();
					String role =comboBox_Role.getSelectedItem().toString();

					System.out.println(nom);
					System.out.println(prenom);
					System.out.println(mdp);
					System.out.println(email);
					System.out.println(salaire);
					System.out.println(role);

					String myUrl = "jdbc:mysql://localhost/moloto";
					Class.forName("com.mysql.jdbc.Driver");

					Connection conn = (Connection) DriverManager.getConnection(myUrl,"root","");
					Statement st = (Statement) conn.createStatement();
					if (nom.matches("[\\p{Lu}\\p{M}][\\p{L}\\p{M},.'-]+(?: [\\p{L}\\p{M},.'-]+)*") && prenom.matches("[\\p{Lu}\\p{M}][\\p{L}\\p{M},.'-]+(?: [\\p{L}\\p{M},.'-]+)*") && salaire.matches("^[0-9, ]+$") && email.matches("^[A-Za-z0-9+_.-]+@(.+)$") && textField_Nom.getText().trim().isEmpty() && textField_PreNom.getText().trim().isEmpty() && textField_Mdp.getText().trim().isEmpty() && textField_Email.getText().trim().isEmpty() && textField_Salaire.getText().trim().isEmpty()) { 
					st.executeUpdate("INSERT INTO `utilisateur`(Nom,Prenom,Mdp,Email,Salaire,Role) VALUE ('"+nom+"','"+prenom+"', '"+mdp+"' ,'"+email+"','"+salaire+"','"+role+"')");
					conn.close();
					utilisateur page = new utilisateur();
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
