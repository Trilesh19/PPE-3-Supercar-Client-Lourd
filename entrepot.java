package Moloto;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Statement;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class entrepot {

	private JFrame frame;
	private JTable table;
	private JTextField txtid;
	private JTextField txtnom;
	private JTextField txtadresse;
	private JTextField txtcontact;
	private JTextField txtemail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					entrepot window = new entrepot();
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
	public entrepot() {
		initialize();
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1136, 587);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(434, 38, 648, 445);
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
		public void mouseClicked(MouseEvent e) {
			DefaultTableModel model= (DefaultTableModel)table.getModel();
			int selectedRowIndex=table.getSelectedRow();
				txtid.setText(model.getValueAt(selectedRowIndex, 0).toString());
				txtnom.setText(model.getValueAt(selectedRowIndex,1).toString());
				txtadresse.setText(model.getValueAt(selectedRowIndex, 2).toString());
				txtcontact.setText(model.getValueAt(selectedRowIndex, 3).toString());
				txtemail.setText(model.getValueAt(selectedRowIndex, 4).toString());
		        
		}
	});
	String query = "select * from entrepot";
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
		
		JLabel lblNewLabel = new JLabel(" Id :");
		lblNewLabel.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 63, 127, 33);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblMarque = new JLabel(" Nom :");
		lblMarque.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		lblMarque.setBounds(10, 128, 127, 33);
		frame.getContentPane().add(lblMarque);
		
		JLabel lblModle = new JLabel(" Adresse :");
		lblModle.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		lblModle.setBounds(10, 197, 127, 33);
		frame.getContentPane().add(lblModle);
		
		JLabel lblPrix = new JLabel(" Contact :");
		lblPrix.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		lblPrix.setBounds(10, 263, 127, 33);
		frame.getContentPane().add(lblPrix);
		
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajouter_entrepot page = new ajouter_entrepot();
				page.getFrame().setVisible(true);
				frame.setVisible(false);
				frame.dispose();
			}
		});
		btnNewButton.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 19));
		btnNewButton.setBounds(10, 435, 127, 33);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnUpdate = new JButton("Modifier");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtid.getText();
				String nom = txtnom.getText();
				String adresse = txtadresse.getText();
				String contact = txtcontact.getText();
				String email = txtemail.getText();
				
				
				Statement st;
				Connection conn = null;
				try {
					conn = DriverManager.getConnection(myUrl, "root", "");
					st = (Statement) conn.createStatement();
					st.executeUpdate("UPDATE `entrepot` SET `id` = '"+id+"', `nom` = '"+nom+"', `adresse` = '"+adresse+"', `contact` = '"+contact+"', `email` = '"+email+"' WHERE `id` = '"+id+"'");
					
					DefaultTableModel model= (DefaultTableModel)table.getModel();
					String query = "select * from entrepot";
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
		btnUpdate.setBounds(147, 435, 127, 33);
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
					st.executeUpdate("DELETE FROM `entrepot` WHERE `id` = '"+id+"'");
					
					DefaultTableModel model= (DefaultTableModel)table.getModel();
					String query = "select * from entrepot";
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
		btnDelete.setBounds(288, 435, 137, 33);
		frame.getContentPane().add(btnDelete);
		
		JLabel lblNewLabel_1_1 = new JLabel(" E-mail :");
		lblNewLabel_1_1.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(10, 330, 184, 33);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		txtid = new JTextField();
		txtid.setFont(new Font("Arial", Font.PLAIN, 18));
		txtid.setColumns(10);
		txtid.setBounds(204, 330, 193, 28);
		frame.getContentPane().add(txtid);
		
		txtnom = new JTextField();
		txtnom.setFont(new Font("Arial", Font.PLAIN, 18));
		txtnom.setColumns(10);
		txtnom.setBounds(204, 263, 193, 28);
		frame.getContentPane().add(txtnom);
		
		txtadresse = new JTextField();
		txtadresse.setFont(new Font("Arial", Font.PLAIN, 18));
		txtadresse.setColumns(10);
		txtadresse.setBounds(204, 197, 193, 28);
		frame.getContentPane().add(txtadresse);
		
		txtcontact = new JTextField();
		txtcontact.setFont(new Font("Arial", Font.PLAIN, 18));
		txtcontact.setColumns(10);
		txtcontact.setBounds(204, 128, 193, 28);
		frame.getContentPane().add(txtcontact);
		
		txtemail = new JTextField();
		txtemail.setFont(new Font("Arial", Font.PLAIN, 18));
		txtemail.setColumns(10);
		txtemail.setBounds(204, 63, 193, 28);
		frame.getContentPane().add(txtemail);
		
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
