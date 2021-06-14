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

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

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

public class client {

	private JFrame frame;
	private JTextField txtid;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextField txtadresse;
	private JTable table;
	private JTextField txtemail;
	private JTextField txtcontact;
	private JTextField txtdob;
	private JTextField textvoiture;
	

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					client window = new client();
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
	public client() {
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
		
		JLabel lbladresse = new JLabel("Adresse :");
		lbladresse.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		lbladresse.setBounds(10, 162, 193, 33);
		frame.getContentPane().add(lbladresse);
		
		JLabel lblnom = new JLabel("Nom :");
		lblnom.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		lblnom.setBounds(10, 76, 127, 33);
		frame.getContentPane().add(lblnom);
		
		JLabel lblprenom = new JLabel("Pr\u00E9nom :");
		lblprenom.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		lblprenom.setBounds(10, 119, 169, 33);
		frame.getContentPane().add(lblprenom);
		
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
		
		txtadresse = new JTextField();
		txtadresse.setFont(new Font("Arial", Font.PLAIN, 15));
		txtadresse.setColumns(10);
		txtadresse.setBounds(217, 164, 193, 28);
		frame.getContentPane().add(txtadresse);
		
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
			        txtadresse.setText(model.getValueAt(selectedRowIndex, 3).toString());
			        txtemail.setText(model.getValueAt(selectedRowIndex, 4).toString());
			        txtcontact.setText(model.getValueAt(selectedRowIndex, 5).toString());
			        txtdob.setText(model.getValueAt(selectedRowIndex, 6).toString());
			        textvoiture.setText(model.getValueAt(selectedRowIndex, 7).toString());
			        
			}
		});
		String query = "select * from client";
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
				ajouter_client page = new ajouter_client();
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
				String adresse = txtadresse.getText();
				String email = txtemail.getText();
				String contact = txtcontact.getText();
				String dob = txtdob.getText();
				String voiture = textvoiture.getText();
				
				Statement st;
				Connection conn = null;
				try {
					conn = DriverManager.getConnection(myUrl, "root", "");
					st = (Statement) conn.createStatement();
					st.executeUpdate("UPDATE `client` SET `id` = '"+id+"', `nom` = '"+nom+"', `prenom` = '"+prenom+"', `adresse` = '"+adresse+"', `email` = '"+email+"', `contact` = '"+contact+"', `dob` = '"+dob+"', `voiture` = '"+voiture+"' WHERE `id` = '"+id+"'");
					
					DefaultTableModel model= (DefaultTableModel)table.getModel();
					String query = "select * from client";
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
					st.executeUpdate("DELETE FROM `client` WHERE `id` = '"+id+"'");
					
					DefaultTableModel model= (DefaultTableModel)table.getModel();
					String query = "select * from client";
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
		
		JLabel lblAdresse = new JLabel("Date de naissance:");
		lblAdresse.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		lblAdresse.setBounds(10, 291, 193, 33);
		frame.getContentPane().add(lblAdresse);
		
		JLabel lblEmail = new JLabel("Contact :");
		lblEmail.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		lblEmail.setBounds(10, 245, 169, 33);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblDateDeNaissance = new JLabel("E-mail :");
		lblDateDeNaissance.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		lblDateDeNaissance.setBounds(10, 205, 193, 33);
		frame.getContentPane().add(lblDateDeNaissance);
		
		txtemail = new JTextField();
		txtemail.setFont(new Font("Arial", Font.PLAIN, 15));
		txtemail.setColumns(10);
		txtemail.setBounds(217, 207, 193, 28);
		frame.getContentPane().add(txtemail);
		
		txtcontact = new JTextField();
		txtcontact.setFont(new Font("Arial", Font.PLAIN, 15));
		txtcontact.setColumns(10);
		txtcontact.setBounds(217, 250, 193, 28);
		frame.getContentPane().add(txtcontact);
		
  	    txtdob = new JTextField();
		txtdob.setFont(new Font("Arial", Font.PLAIN, 15));
		txtdob.setColumns(10);
		txtdob.setBounds(217, 294, 193, 28);
		frame.getContentPane().add(txtdob);
		
		JLabel lblVoiture = new JLabel("Voiture:");
		lblVoiture.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		lblVoiture.setBounds(10, 334, 193, 33);
		frame.getContentPane().add(lblVoiture);
		
		textvoiture = new JTextField();
		textvoiture.setFont(new Font("Arial", Font.PLAIN, 15));
		textvoiture.setBounds(217, 332, 193, 30);
		frame.getContentPane().add(textvoiture);
		textvoiture.setColumns(10);
		
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
