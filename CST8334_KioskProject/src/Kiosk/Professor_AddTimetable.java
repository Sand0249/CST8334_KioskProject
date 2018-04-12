/**
 * Course Info: CST8334 Software Development Project
 * Student Name: Qingwu Liu
 * Date: April 4, 2018
 * Knowledge from Thaicreate.
 * Retrieved from http://www.thaicreate.com/java.html
 */

package Kiosk;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class Professor_AddTimetable extends JFrame {
	
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Professor_AddTimetable form = new Professor_AddTimetable();
				form.setVisible(true);
			}
		});
	}
	/**
	 * Load CSV file, Crate frame, label result, table, ScrollPane, Button Open JFileChooser.
	 */
	public Professor_AddTimetable() {
		super("Timetable");
		setSize(668, 345);
		setLocation(500, 280);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		final JLabel lblResult = new JLabel("Result", JLabel.CENTER);
		lblResult.setBounds(150, 22, 370, 14);
		getContentPane().add(lblResult);

		table = new JTable();
		getContentPane().add(table);
		/**
		 * create table for csv file
		 */
		final DefaultTableModel model = (DefaultTableModel)table.getModel();
		model.addColumn("Section");
		model.addColumn("Course");
		model.addColumn("Professor");
		model.addColumn("Start");
		model.addColumn("End");
		model.addColumn ("Date");

		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(84, 98, 506, 79);
		getContentPane().add(scroll);

		JButton btnButton = new JButton("Open File Chooser");
		btnButton.setBounds(268, 47, 135, 23);
		btnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser fileopen = new JFileChooser();
				FileFilter filter = new FileNameExtensionFilter(
						"Text/CSV file", "txt", "csv");
				fileopen.addChoosableFileFilter(filter);

				int ret = fileopen.showDialog(null, "Choose file");

				if (ret == JFileChooser.APPROVE_OPTION) {
					/**
					 * Read Text file
					 */
					
					File file = fileopen.getSelectedFile();

					try {
						BufferedReader br = new BufferedReader(new FileReader(
								file));
						String line;
						int row = 0;
						while ((line = br.readLine()) != null) {
							String[] arr = line.split(",");
							model.addRow(new Object[0]);
							model.setValueAt(arr[0], row, 0);
							model.setValueAt(arr[1], row, 1);
							model.setValueAt(arr[2], row, 2);
							model.setValueAt(arr[3], row, 3);
							model.setValueAt(arr[4], row, 4);
							model.setValueAt(arr[5], row, 5);
							row++;
						}
						br.close();
					} catch (IOException ex) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,
								"CSV FILE ONLY");
						ex.printStackTrace();
					}

					lblResult.setText(fileopen.getSelectedFile().toString());
				}

			}
		});
		getContentPane().add(btnButton);
		/**
		 * create save button ,and set action-event.
		 */
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveData(); // save Data
			}
		});
		btnSave.setBounds(292, 228, 89, 23);
		getContentPane().add(btnSave);

	}
	/**
	 * Save the data into MYSQL.
	 */
	
	private void SaveData()
	{
		Connection connect = null;
		Statement s = null;
		/**
		 * Connect to MYSQL with password and username
		 */
		try {
			Class.forName("com.mysql.jdbc.Driver");

			connect = DriverManager.getConnection(""
					+ "jdbc:mysql://localhost/timetable"
					+ "?user=timetable&password=password");

			s = connect.createStatement();
			
			for(int i = 0; i<table.getRowCount();i++)
			{
				String Date= table.getValueAt(i, 0).toString();
				String Section = table.getValueAt(i, 1).toString();
				String Course = table.getValueAt(i, 2).toString();
				String Professor = table.getValueAt(i, 3).toString();
				String Start = table.getValueAt(i, 4).toString();
				String End = table.getValueAt(i, 5).toString();
				
				/**
				 * Insert SQl
				 */
				String sql = "INSERT INTO ptime "
						+ "(Date,Section,Course,Professor,Start,End) "
						+ "VALUES ('" + Date + "','"
						+ Section + "','"
						+ Course + "'" + ",'"
						+ Professor + "','"
						+ Start + "','"
						+ End + "') ";
				s.execute(sql);
			}
				
			JOptionPane.showMessageDialog(null,
					"Import Data Successfully");


		} catch (Exception ex) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, ex.getMessage());
			ex.printStackTrace();
		}

		try {
			if (s != null) {
				s.close();
				connect.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}