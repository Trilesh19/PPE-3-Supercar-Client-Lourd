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

public class vendeur {

	private JFrame frame;
	private JTable table;
	private JTextField txtemail;
	private JTextField txtcontact;
	private JTextField txtprenom;
	private JTextField txtnom;
	private JTextField txtid;
	private JTextField textentrepot;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vendeur window = new vendeur();
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
	public vendeur() {
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
				txtemail.setText(model.getValueAt(selectedRowIndex, 4).toString());
		        txtcontact.setText(model.getValueAt(selectedRowIndex,3).toString());
		        txtprenom.setText(model.getValueAt(selectedRowIndex, 2).toString());
		        txtnom.setText(model.getValueAt(selectedRowIndex, 1).toString());
		        txtid.setText(model.getValueAt(selectedRowIndex, 0).toString());
		        textentrepot.setText(model.getValueAt(selectedRowIndex, 5).toString());
		}
	});
	String query = "select * from vendeur";
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
		lblNewLabel.setBounds(10, 61, 127, 33);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblMarque = new JLabel(" Nom :");
		lblMarque.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		lblMarque.setBounds(10, 111, 127, 33);
		frame.getContentPane().add(lblMarque);
		
		JLabel lblModle = new JLabel(" Pr\u00E9nom :");
		lblModle.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		lblModle.setBounds(10, 164, 127, 33);
		frame.getContentPane().add(lblModle);
		
		JLabel lblPrix = new JLabel(" Contact :");
		lblPrix.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		lblPrix.setBounds(10, 220, 138, 33);
		frame.getContentPane().add(lblPrix);
		
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajouter_vendeur page = new ajouter_vendeur();
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
				String email = txtemail.getText();
				String contact = txtcontact.getText();
				String prenom = txtprenom.getText();
				String nom = txtnom.getText();
				String id = txtid.getText();
				String entrepot = textentrepot.getText();
				
				
				Statement st;
				Connection conn = null;
				try {
					conn = DriverManager.getConnection(myUrl, "root", "");
					st = (Statement) conn.createStatement();
					st.executeUpdate("UPDATE `vendeur` SET `id` = '"+id+"', `email` = '"+email+"', `contact` = '"+contact+"', `prenom` = '"+prenom+"', `nom` = '"+nom+"', `entrepot` = '"+entrepot+"' WHERE `id` = '"+id+"'");
					
					DefaultTableModel model= (DefaultTableModel)table.getModel();
					String query = "select * from vendeur";
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
				String id = txtemail.getText();
				
				Statement st;
				Connection conn = null;
				try {
					conn = DriverManager.getConnection(myUrl, "root", "");
					st = (Statement) conn.createStatement();
					st.executeUpdate("DELETE FROM `vendeur` WHERE `id` = '"+id+"'");
					
					DefaultTableModel model= (DefaultTableModel)table.getModel();
					String query = "select * from vendeur";
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
		
		JLabel lblNewLabel_1_1 = new JLabel(" Email :");
		lblNewLabel_1_1.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(10, 271, 184, 33);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		txtemail = new JTextField();
		txtemail.setFont(new Font("Arial", Font.PLAIN, 18));
		txtemail.setColumns(10);
		txtemail.setBounds(204, 273, 193, 28);
		frame.getContentPane().add(txtemail);
		
		txtcontact = new JTextField();
		txtcontact.setFont(new Font("Arial", Font.PLAIN, 18));
		txtcontact.setColumns(10);
		txtcontact.setBounds(204, 222, 193, 28);
		frame.getContentPane().add(txtcontact);
		
		txtprenom = new JTextField();
		txtprenom.setFont(new Font("Arial", Font.PLAIN, 18));
		txtprenom.setColumns(10);
		txtprenom.setBounds(204, 166, 193, 28);
		frame.getContentPane().add(txtprenom);
		
		txtnom = new JTextField();
		txtnom.setFont(new Font("Arial", Font.PLAIN, 18));
		txtnom.setColumns(10);
		txtnom.setBounds(204, 113, 193, 28);
		frame.getContentPane().add(txtnom);
		
		txtid = new JTextField();
		txtid.setFont(new Font("Arial", Font.PLAIN, 18));
		txtid.setColumns(10);
		txtid.setBounds(204, 63, 193, 28);
		frame.getContentPane().add(txtid);
		
		JLabel lblNewLabel_1_1_1 = new JLabel(" Entrepot :");
		lblNewLabel_1_1_1.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		lblNewLabel_1_1_1.setBounds(10, 324, 184, 33);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		textentrepot = new JTextField();
		textentrepot.setFont(new Font("Arial", Font.PLAIN, 18));
		textentrepot.setColumns(10);
		textentrepot.setBounds(204, 324, 193, 28);
		frame.getContentPane().add(textentrepot);
		
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
