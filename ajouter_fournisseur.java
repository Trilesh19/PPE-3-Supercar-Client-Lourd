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
import Moloto.ajouter_fournisseur;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
public class ajouter_fournisseur {
	private static final long serialVersionUID = 1L;
	JFrame frame;
	private JTextField textmarque;
	private JTextField textmodele;
	private JTextField textnom;
	private JTextField textadresse;
	private JTextField textid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ajouter_fournisseur window = new ajouter_fournisseur();
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
	public ajouter_fournisseur() {
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

		JLabel lblNewLabel = new JLabel("Fournisseur");
		lblNewLabel.setBounds(419, 11, 236, 45);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));

		textmarque = new JTextField();
		textmarque.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textmarque.setBounds(535, 114, 207, 35);
		panel.add(textmarque);
		textmarque.setColumns(10);

		textmodele = new JTextField();
		textmodele.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textmodele.setBounds(535, 159, 207, 35);
		panel.add(textmodele);
		textmodele.setColumns(10);

		textnom = new JTextField();
		textnom.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textnom.setBounds(535, 211, 207, 35);
		panel.add(textnom);
		textnom.setColumns(10);

		textadresse = new JTextField();
		textadresse.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textadresse.setBounds(535, 256, 207, 35);
		panel.add(textadresse);
		textadresse.setColumns(10);

		

		JLabel lblNewLabel_Nom = new JLabel("Marque :");
		lblNewLabel_Nom.setBackground(Color.BLACK);
		lblNewLabel_Nom.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_Nom.setForeground(Color.BLACK);
		lblNewLabel_Nom.setBounds(366, 122, 79, 17);
		panel.add(lblNewLabel_Nom);


		JLabel lblNewLabel_Email = new JLabel("Adresse :");
		lblNewLabel_Email.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_Email.setForeground(Color.BLACK);
		lblNewLabel_Email.setBounds(366, 261, 159, 22);
		panel.add(lblNewLabel_Email);

		JLabel lblNewLabel_Mdp = new JLabel("Nom :");
		lblNewLabel_Mdp.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_Mdp.setForeground(Color.BLACK);
		lblNewLabel_Mdp.setBounds(366, 216, 148, 22);
		panel.add(lblNewLabel_Mdp);

		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(464, 467, 132, 45);
		panel.add(btnNewButton);


		JLabel lblNewLabel_Nom_1 = new JLabel("Mod\u00E8le :");
		lblNewLabel_Nom_1.setForeground(Color.BLACK);
		lblNewLabel_Nom_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_Nom_1.setBackground(Color.BLACK);
		lblNewLabel_Nom_1.setBounds(366, 167, 79, 17);
		panel.add(lblNewLabel_Nom_1);

		
		
		JButton btnNewButton_1 = new JButton("Retour");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fournisseur page = new fournisseur();
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
					String marque =textmarque.getText();
					String modele =textmodele.getText();
					String nom =textnom.getText();
					String adresse =textadresse.getText();
					

					

					String myUrl = "jdbc:mysql://localhost/moloto";
					Class.forName("com.mysql.jdbc.Driver");

					Connection conn = (Connection) DriverManager.getConnection(myUrl,"root","");
					Statement st = (Statement) conn.createStatement();

					st.executeUpdate("INSERT INTO `fournisseur`(marque,modele,nom,adresse) VALUE ('"+marque+"','"+modele+"', '"+nom+"' ,'"+adresse+"')");
					conn.close();
					fournisseur page = new fournisseur();
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
