package Moloto;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Label;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigInteger;
import java.security.MessageDigest;

public class utilisateur {

	private JFrame frame;
	private JTextField txtid;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextField txtmdp;
	private JTable table;
	private JTextField txtemail;
	private JTextField txtsalaire;
	private JTextField txtrole;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					utilisateur window = new utilisateur();
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
	public utilisateur() {
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
		frame.setBounds(100, 100, 1246, 584);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel(" Id :");
		lblNewLabel.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 33, 127, 33);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblFirstname = new JLabel("Mot de Passe :");
		lblFirstname.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		lblFirstname.setBounds(10, 162, 193, 33);
		frame.getContentPane().add(lblFirstname);
		
		JLabel lblLastname = new JLabel("Nom :");
		lblLastname.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		lblLastname.setBounds(10, 76, 127, 33);
		frame.getContentPane().add(lblLastname);
		
		JLabel lblAge = new JLabel("Pr\u00E9nom :");
		lblAge.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		lblAge.setBounds(10, 119, 169, 33);
		frame.getContentPane().add(lblAge);
		
		txtid = new JTextField();
		txtid.setFont(new Font("Arial", Font.PLAIN, 15));
		txtid.setBounds(217, 35, 193, 28);
		frame.getContentPane().add(txtid);
		txtid.setColumns(10);
		
		txtNom = new JTextField();
		txtNom.setFont(new Font("Arial", Font.PLAIN, 15));
		txtNom.setColumns(10);
		txtNom.setBounds(217, 78, 193, 28);
		frame.getContentPane().add(txtNom);
		
		txtPrenom = new JTextField();
		txtPrenom.setFont(new Font("Arial", Font.PLAIN, 15));
		txtPrenom.setColumns(10);
		txtPrenom.setBounds(217, 121, 193, 28);
		frame.getContentPane().add(txtPrenom);
		
		txtmdp = new JPasswordField();
		txtmdp.setFont(new Font("Arial", Font.PLAIN, 15));
		txtmdp.setColumns(10);
		txtmdp.setBounds(217, 164, 193, 28);
		frame.getContentPane().add(txtmdp);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(478, 20, 658, 445);
		frame.getContentPane().add(scrollPane);
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
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model= (DefaultTableModel)table.getModel();
				int selectedRowIndex=table.getSelectedRow();
					txtid.setText(model.getValueAt(selectedRowIndex, 0).toString());
			        txtNom.setText(model.getValueAt(selectedRowIndex,1).toString());
			        txtPrenom.setText(model.getValueAt(selectedRowIndex, 2).toString());
			        txtmdp.setText(model.getValueAt(selectedRowIndex, 4).toString());
			        txtemail.setText(model.getValueAt(selectedRowIndex, 3).toString());
			        txtsalaire.setText(model.getValueAt(selectedRowIndex, 5).toString());
			        txtrole.setText(model.getValueAt(selectedRowIndex, 6).toString());
			}
		});
		String query = "select * from utilisateur";
		java.sql.PreparedStatement pst;
		try {
			pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajouter_utilisateur page = new ajouter_utilisateur();
				page.getFrame().setVisible(true);
				frame.setVisible(false);
				frame.dispose();
			}
		});
		btnNewButton.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 19));
		btnNewButton.setBounds(27, 432, 127, 33);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnUpdate = new JButton("Modifier");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtid.getText();
				String nom = txtNom.getText();
				String prenom = txtPrenom.getText();
				String mdp = hashMDP(txtmdp.getText());
				String email = txtemail.getText();
				String salaire = txtsalaire.getText();
				String role = txtrole.getText();
				
				Statement st;
				Connection conn = null;
				try {
					conn = DriverManager.getConnection(myUrl, "root", "");
					st = (Statement) conn.createStatement();
					st.executeUpdate("UPDATE `utilisateur` SET `id` = '"+id+"', `nom` = '"+nom+"', `prenom` = '"+prenom+"', `mdp` = '"+mdp+"', `email` = '"+email+"', `salaire` = '"+salaire+"', `role` = '"+role+"' WHERE `id` = '"+id+"'");
					
					DefaultTableModel model= (DefaultTableModel)table.getModel();
					String query = "select * from utilisateur";
					java.sql.PreparedStatement pst;
					
						pst = conn.prepareStatement(query);
						ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					conn.close();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				
				
			}
		});
		btnUpdate.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 19));
		btnUpdate.setBounds(174, 432, 127, 33);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Supprimer");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtid.getText();
				
				Statement st;
				Connection conn = null;
				try {
					conn = DriverManager.getConnection(myUrl, "root", "");
					st = (Statement) conn.createStatement();
					st.executeUpdate("DELETE FROM `utilisateur` WHERE `id` = '"+id+"'");
					
					DefaultTableModel model= (DefaultTableModel)table.getModel();
					String query = "select * from utilisateur";
					java.sql.PreparedStatement pst;
					
						pst = conn.prepareStatement(query);
						ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					conn.close();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				
			}
		});
		btnDelete.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 19));
		btnDelete.setBounds(318, 432, 137, 33);
		frame.getContentPane().add(btnDelete);
		
		JLabel lblAdresse = new JLabel("Salaire :");
		lblAdresse.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		lblAdresse.setBounds(10, 248, 169, 33);
		frame.getContentPane().add(lblAdresse);
		
		JLabel lblEmail = new JLabel("E-mail :");
		lblEmail.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		lblEmail.setBounds(10, 205, 169, 33);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblDateDeNaissance = new JLabel("Role :");
		lblDateDeNaissance.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		lblDateDeNaissance.setBounds(10, 291, 193, 33);
		frame.getContentPane().add(lblDateDeNaissance);
		
		txtemail = new JTextField();
		txtemail.setFont(new Font("Arial", Font.PLAIN, 15));
		txtemail.setColumns(10);
		txtemail.setBounds(217, 207, 193, 28);
		frame.getContentPane().add(txtemail);
		
		txtsalaire = new JTextField();
		txtsalaire.setFont(new Font("Arial", Font.PLAIN, 15));
		txtsalaire.setColumns(10);
		txtsalaire.setBounds(217, 250, 193, 28);
		frame.getContentPane().add(txtsalaire);
		
		txtrole = new JTextField();
		txtrole.setFont(new Font("Arial", Font.PLAIN, 15));
		txtrole.setColumns(10);
		txtrole.setBounds(217, 294, 193, 28);
		frame.getContentPane().add(txtrole);
		
		JButton btnretour = new JButton("Menu Principale");
		btnretour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				admin page = new admin();
				page.getFrame().setVisible(true);
				frame.setVisible(false);
				frame.dispose();
			}
		});
		btnretour.setBounds(102, 491, 201, 28);
		btnretour.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 19));
		frame.getContentPane().add(btnretour);
	}
}
