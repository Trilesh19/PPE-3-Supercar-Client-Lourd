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

public class commandes {

	private JFrame frame;
	private JTable table;
	private JTextField txtid;
	private JTextField txtmodele;
	private JTextField txtmarque;
	private JTextField txtclient;
	private JTextField textentrepot;
	private JTextField textdate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					commandes window = new commandes();
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
	public commandes() {
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
		        txtmarque.setText(model.getValueAt(selectedRowIndex,1).toString());
		        txtmodele.setText(model.getValueAt(selectedRowIndex, 2).toString());
		        txtclient.setText(model.getValueAt(selectedRowIndex, 3).toString());
		        textentrepot.setText(model.getValueAt(selectedRowIndex, 4).toString());
		        textdate.setText(model.getValueAt(selectedRowIndex, 5).toString());
		        
		}
	});
	String query = "select * from commandes";
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
		
		JLabel lblMarque = new JLabel(" Marque :");
		lblMarque.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		lblMarque.setBounds(10, 111, 127, 33);
		frame.getContentPane().add(lblMarque);
		
		JLabel lblModle = new JLabel(" Mod\u00E8le :");
		lblModle.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		lblModle.setBounds(10, 154, 127, 33);
		frame.getContentPane().add(lblModle);
		
		JLabel lblPrix = new JLabel(" Id Client :");
		lblPrix.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		lblPrix.setBounds(10, 197, 127, 33);
		frame.getContentPane().add(lblPrix);
		
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajouter_voiture page = new ajouter_voiture();
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
				String marque = txtmarque.getText();
				String modele = txtmodele.getText();
				String client = txtclient.getText();
				String entrepot = textentrepot.getText();
				String date = textdate.getText();
				
				Statement st;
				Connection conn = null;
				try {
					conn = DriverManager.getConnection(myUrl, "root", "");
					st = (Statement) conn.createStatement();
					st.executeUpdate("UPDATE `commandes` SET `id` = '"+id+"', `marque` = '"+marque+"', `modele` = '"+modele+"', `id_client` = '"+client+"', `entrepot` = '"+entrepot+"', `date` = '"+date+"' WHERE `id` = '"+id+"'");
					
					DefaultTableModel model= (DefaultTableModel)table.getModel();
					String query = "select * from commandes";
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
					st.executeUpdate("DELETE FROM `commandes` WHERE `id` = '"+id+"'");
					
					DefaultTableModel model= (DefaultTableModel)table.getModel();
					String query = "select * from commandes";
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
		
		JLabel lblNewLabel_1_1 = new JLabel(" Date :");
		lblNewLabel_1_1.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(10, 289, 184, 33);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		txtclient = new JTextField();
		txtclient.setFont(new Font("Arial", Font.PLAIN, 18));
		txtclient.setColumns(10);
		txtclient.setBounds(204, 199, 193, 28);
		frame.getContentPane().add(txtclient);
		
		txtmodele = new JTextField();
		txtmodele.setFont(new Font("Arial", Font.PLAIN, 18));
		txtmodele.setColumns(10);
		txtmodele.setBounds(204, 156, 193, 28);
		frame.getContentPane().add(txtmodele);
		
		txtmarque = new JTextField();
		txtmarque.setFont(new Font("Arial", Font.PLAIN, 18));
		txtmarque.setColumns(10);
		txtmarque.setBounds(204, 113, 193, 28);
		frame.getContentPane().add(txtmarque);
		
		txtid = new JTextField();
		txtid.setFont(new Font("Arial", Font.PLAIN, 18));
		txtid.setColumns(10);
		txtid.setBounds(204, 63, 193, 28);
		frame.getContentPane().add(txtid);
		
		textentrepot = new JTextField();
		textentrepot.setFont(new Font("Arial", Font.PLAIN, 18));
		textentrepot.setColumns(10);
		textentrepot.setBounds(204, 242, 193, 28);
		frame.getContentPane().add(textentrepot);
		
		textdate = new JTextField();
		textdate.setFont(new Font("Arial", Font.PLAIN, 18));
		textdate.setColumns(10);
		textdate.setBounds(204, 291, 193, 28);
		frame.getContentPane().add(textdate);
		
		JLabel lblNewLabel_1_1_1 = new JLabel(" Entrepot :");
		lblNewLabel_1_1_1.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		lblNewLabel_1_1_1.setBounds(10, 240, 184, 33);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
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
