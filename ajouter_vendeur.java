package Moloto;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import Moloto.ajouter_vendeur;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
public class ajouter_vendeur {
	private static final long serialVersionUID = 1L;
	JFrame frame;
	private JTextField textnom;
	private JTextField textprenom;
	private JTextField textcontact;
	private JTextField textemail;
	private JTextField textid;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ajouter_vendeur window = new ajouter_vendeur();
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
	public ajouter_vendeur() {
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

		JLabel lblNewLabel = new JLabel("Vendeur");
		lblNewLabel.setBounds(419, 11, 236, 45);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));

		textnom = new JTextField();
		textnom.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textnom.setBounds(535, 114, 207, 35);
		panel.add(textnom);
		textnom.setColumns(10);

		textprenom = new JTextField();
		textprenom.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textprenom.setBounds(535, 159, 207, 35);
		panel.add(textprenom);
		textprenom.setColumns(10);

		textcontact = new JTextField();
		textcontact.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textcontact.setBounds(535, 211, 207, 35);
		panel.add(textcontact);
		textcontact.setColumns(10);

		textemail = new JTextField();
		textemail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textemail.setBounds(535, 256, 207, 35);
		panel.add(textemail);
		textemail.setColumns(10);

		

		JLabel lblNewLabel_Nom = new JLabel("Nom :");
		lblNewLabel_Nom.setBackground(Color.BLACK);
		lblNewLabel_Nom.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_Nom.setForeground(Color.BLACK);
		lblNewLabel_Nom.setBounds(366, 122, 79, 17);
		panel.add(lblNewLabel_Nom);


		JLabel lblNewLabel_Email = new JLabel("Email :");
		lblNewLabel_Email.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_Email.setForeground(Color.BLACK);
		lblNewLabel_Email.setBounds(366, 261, 159, 22);
		panel.add(lblNewLabel_Email);

		JLabel lblNewLabel_Mdp = new JLabel("Contact :");
		lblNewLabel_Mdp.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_Mdp.setForeground(Color.BLACK);
		lblNewLabel_Mdp.setBounds(366, 216, 148, 22);
		panel.add(lblNewLabel_Mdp);

		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(464, 467, 132, 45);
		panel.add(btnNewButton);


		JLabel lblNewLabel_Nom_1 = new JLabel("Pr\u00E9nom :");
		lblNewLabel_Nom_1.setForeground(Color.BLACK);
		lblNewLabel_Nom_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_Nom_1.setBackground(Color.BLACK);
		lblNewLabel_Nom_1.setBounds(366, 167, 79, 17);
		panel.add(lblNewLabel_Nom_1);

		
		
		JButton btnNewButton_1 = new JButton("Retour");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vendeur page = new vendeur();
				page.getFrame().setVisible(true);
				frame.setVisible(false);
				frame.dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBounds(42, 40, 103, 35);
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel_Email_1 = new JLabel("Entrepot :");
		lblNewLabel_Email_1.setForeground(Color.BLACK);
		lblNewLabel_Email_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_Email_1.setBounds(366, 311, 159, 22);
		panel.add(lblNewLabel_Email_1);
		
		JComboBox comboBox_entrepot = new JComboBox();
		comboBox_entrepot.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_entrepot.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4"}));
		comboBox_entrepot.setBounds(535, 311, 207, 35);
		panel.add(comboBox_entrepot);


		btnNewButton.addActionListener((ActionListener) new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				try { 
					String nom =textnom.getText();
					String prenom =textprenom.getText();
					String contact =textcontact.getText();
					String email =textemail.getText();
					String entrepot =comboBox_entrepot.getSelectedItem().toString();
					

					String myUrl = "jdbc:mysql://localhost/moloto";
					Class.forName("com.mysql.jdbc.Driver");

					Connection conn = (Connection) DriverManager.getConnection(myUrl,"root","");
					Statement st = (Statement) conn.createStatement();

					st.executeUpdate("INSERT INTO `vendeur`(nom,prenom,contact,email,entrepot) VALUE ('"+nom+"','"+prenom+"', '"+contact+"' ,'"+email+"','"+entrepot+"')");
					conn.close();
					vendeur page = new vendeur();
					page.getFrame().setVisible(true);
					frame.setVisible(false);
					frame.dispose();
				} catch (Exception e) { 
					System.err.println(e.getMessage()); 
				}
			}
		});
	} 
}
