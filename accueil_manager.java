package Moloto;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class accueil_manager {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					accueil_manager window = new accueil_manager();
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
	public accueil_manager() {
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
		btnNewButton.setBounds(120, 155, 136, 33);
		frame.getContentPane().add(btnNewButton);
		
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
		btnNewButton_4.setBounds(422, 155, 136, 33);
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
		btnNewButton_5.setBounds(422, 301, 136, 33);
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
		btnNewButton_8.setBounds(109, 301, 158, 33);
		frame.getContentPane().add(btnNewButton_8);
	}

	public Window getFrame() {
		// TODO Auto-generated method stub
		return null;
	}
}
