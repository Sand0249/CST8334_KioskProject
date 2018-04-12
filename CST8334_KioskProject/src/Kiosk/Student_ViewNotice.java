/** 
 * Author:      Simranjit Singh Sandhu
 * Modified by: Zachery Organ
 * Date  :      April 2, 2018
 * Description: This class is used to view notice from student side
 */

package Kiosk;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import dataaccess.DataSource;
import net.proteanit.sql.DbUtils;

public class Student_ViewNotice {

	private JFrame frame;                                // instantiating frame
	public String getData;                               // instantiating string variable getData
	public ResultSet result = null; 					 // instantiating Result set
	private JTable txtData;                              // instantiating JTable 
	private JTextField txtname;                          // instantiating textfield for name
	private String student_id;    						 // instantiating string variable student_id
	private JTextField textField;                        // instantiating textfield

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student_ViewNotice window = new Student_ViewNotice();
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
	public Student_ViewNotice() {
		initialize("");
	}
	
	// parameterized constructor
	public Student_ViewNotice(String id) {
		this.student_id = id;
		initialize(id);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String id) 
	{
		// creating frame
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 1625, 870);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.getContentPane().setLayout(null);
		
		//creating label
		JLabel lblViewNotificationOr = new JLabel("View Notification or Announcements");
		lblViewNotificationOr.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblViewNotificationOr.setBounds(551, 81, 520, 72);
		frame.getContentPane().add(lblViewNotificationOr);
		
		// creating scrollpane for adding JTable into it
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setDoubleBuffered(true);
		scrollPane.setAutoscrolls(true);
		scrollPane.setFont(new Font("Times New Roman", Font.BOLD, 16));
		scrollPane.setBounds(466, 294, 779, 377);
		frame.getContentPane().add(scrollPane);
		
		// creating JTable for getting data from database in the form of table
		txtData = new JTable();
		txtData.setDragEnabled(true);
		txtData.setDoubleBuffered(true);
		txtData.setSurrendersFocusOnKeystroke(true);
		txtData.setToolTipText("");
		txtData.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		txtData.setRowMargin(10);
		txtData.setRowHeight(100);
		txtData.setSize(new Dimension(20, 100));
		txtData.setPreferredScrollableViewportSize(new Dimension(486, 800));
		txtData.setSelectionBackground(Color.GRAY);
		txtData.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtData.setShowGrid(true);
		scrollPane.setViewportView(txtData);
		        
				// tectfield for student id
		        txtname = new JTextField();
		        txtname.setBounds(713, 185, 203, 43);
		        frame.getContentPane().add(txtname);
		        txtname.setColumns(10);
		        
		        //label for username's textfield
		        JLabel lblWriteYourUsername = new JLabel("Write your username");
		        lblWriteYourUsername.setFont(new Font("Times New Roman", Font.BOLD, 18));
		        lblWriteYourUsername.setBounds(521, 190, 194, 31);
		        frame.getContentPane().add(lblWriteYourUsername);
		        
		        // button for viewing notifications for user written in textfield
		        JButton btnView = new JButton("View Notifications");
		        btnView.addActionListener(new ActionListener() 
		        {
		        	public void actionPerformed(ActionEvent e) 
		        	{
		        		try 
		        		{
		        			DataSource source = new DataSource();
		        			Connection con = source.getConnection();                   
		        			String query = "select  course_id, Notification, Professor, Time from sturegistry where student_id = ? and Notification is not null";
		        			PreparedStatement prep = con.prepareStatement(query);
		        			prep.setString(1, txtname.getText());		
		        		        result =  prep.executeQuery();	        		
		        		        txtData.setModel(DbUtils.resultSetToTableModel(result));       	
		        	} catch(SQLException ex) 
		        		{
		    			System.out.println(ex.getMessage());
		    	
		        		}
		        }
		        });
		        btnView.setBounds(713, 242, 203, 43);
		        btnView.setForeground(new Color(255, 255, 255));
		        btnView.setBackground(new Color(0, 128, 0));
		        btnView.setFont(new Font("Times New Roman", Font.BOLD, 14));
		        frame.getContentPane().add(btnView);
		        
		        // button for going back to student panel
		        JButton backbutton = new JButton("Go back to Student Portal");
				backbutton.setForeground(new Color(255, 255, 255));
			    backbutton.setBackground(new Color(0, 128, 0));
			    backbutton.setFont(new Font("Times New Roman", Font.BOLD, 14));
			    backbutton.setBounds(713, 704, 203, 43);
			    frame.getContentPane().add(backbutton);
			    backbutton.addActionListener(new ActionListener() 
			    {	
					public void actionPerformed(ActionEvent e) 
					{
						StudentPanel welcome = new StudentPanel(id);
						String[] str1 = new String[]{id};
						welcome.main(str1);	
						frame.dispose();
					}
				});
			    
			    // background design
			    JLabel label3 = new JLabel("");
			    Image image3 = new ImageIcon(this.getClass().getResource("/alc.jpg")).getImage();
			    label3.setIcon(new ImageIcon(image3));
			    label3.setBounds(0, -23, 287, 105);
			    frame.getContentPane().add(label3);
			    
			    // textfield
			    textField = new JTextField();
			    textField.setEditable(false);
			    textField.setColumns(10);
			    textField.setBackground(new Color(0, 102, 51));
			    textField.setBounds(-309, -18, 2500, 100);
			    frame.getContentPane().add(textField);
			
	}
}
