package Moloto;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class admin {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin window = new admin();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public admin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 701, 484);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Menu Principal");
		lblNewLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 50));
		lblNewLabel.setBounds(151, 10, 361, 99);
		getFrame().getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Client");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client page = new client();
				page.getFrame().setVisible(true);
				frame.setVisible(false);
				frame.dispose();
			}
		});
		btnNewButton.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 20));
		btnNewButton.setBorder(new RoundedBorder(15));
		btnNewButton.setBounds(133, 155, 136, 33);
		getFrame().getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Utilisateur");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				utilisateur page = new utilisateur();
				page.getFrame().setVisible(true);
				frame.setVisible(false);
				frame.dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 20));
		btnNewButton_1.setBorder(new RoundedBorder(15));
		btnNewButton_1.setBounds(37, 253, 158, 33);
		getFrame().getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Fournisseur");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fournisseur page = new fournisseur();
				page.getFrame().setVisible(true);
				frame.setVisible(false);
				frame.dispose();
			}
		});
		btnNewButton_2.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 20));
		btnNewButton_2.setBorder(new RoundedBorder(15));
		btnNewButton_2.setBounds(115, 359, 181, 33);
		getFrame().getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Vendeur");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vendeur page = new vendeur();
				page.getFrame().setVisible(true);
				frame.setVisible(false);
				frame.dispose();
			}
		});
		btnNewButton_3.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 20));
		btnNewButton_3.setBorder(new RoundedBorder(15));
		btnNewButton_3.setBounds(400, 155, 136, 33);
		getFrame().getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Voiture");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				voiture page = new voiture();
				page.getFrame().setVisible(true);
				frame.setVisible(false);
				frame.dispose();
			}
		});
		btnNewButton_4.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 20));
		btnNewButton_4.setBorder(new RoundedBorder(15));
		btnNewButton_4.setBounds(479, 253, 136, 33);
		getFrame().getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Vente");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vente page = new vente();
				page.getFrame().setVisible(true);
				frame.setVisible(false);
				frame.dispose();
			}
		});
		btnNewButton_5.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 20));
		btnNewButton_5.setBorder(new RoundedBorder(15));
		btnNewButton_5.setBounds(400, 359, 136, 33);
		getFrame().getContentPane().add(btnNewButton_5);
		
		JButton btnNewButton_8 = new JButton("Entrepot");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				entrepot page = new entrepot();
				page.getFrame().setVisible(true);
				frame.setVisible(false);
				frame.dispose();
			}
		});
		btnNewButton_8.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 20));
		btnNewButton_8.setBorder(new RoundedBorder(15));
		btnNewButton_8.setBounds(264, 253, 158, 33);
		getFrame().getContentPane().add(btnNewButton_8);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login page = new login();
				page.getFrame().setVisible(true);
				frame.dispose();
				System.exit(0);
			}
		});
		btnLogout.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 20));
		btnLogout.setBorder(new RoundedBorder(15));
		btnLogout.setBounds(541, 48, 136, 33);
		frame.getContentPane().add(btnLogout);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
