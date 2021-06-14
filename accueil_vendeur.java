package Moloto;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class accueil_vendeur {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					accueil_vendeur window = new accueil_vendeur();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public accueil_vendeur() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 713, 484);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Menu Principal");
		lblNewLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 50));
		lblNewLabel.setBounds(167, 10, 361, 99);
		frame.getContentPane().add(lblNewLabel);
		
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
		btnNewButton_4.setBounds(488, 217, 136, 33);
		frame.getContentPane().add(btnNewButton_4);
		
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
		btnNewButton_5.setBounds(277, 217, 136, 33);
		frame.getContentPane().add(btnNewButton_5);
		
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
		btnNewButton_8.setBounds(35, 217, 158, 33);
		frame.getContentPane().add(btnNewButton_8);
		
		JButton btnNewButton = new JButton("Logout");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login page = new login();
				page.getFrame().setVisible(true);
				frame.setVisible(false);
				frame.dispose();
			}
		});
		btnNewButton.setBounds(562, 54, 127, 33);
		frame.getContentPane().add(btnNewButton);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}

