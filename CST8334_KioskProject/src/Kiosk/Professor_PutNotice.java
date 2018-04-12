/** 
 * Author:      Simranjit Singh Sandhu
 * Modified by: Zachery Organ
 * Date  :      April 6, 2018
 * Description: This class is used to put a class cancellation notice by professor
 */

package Kiosk;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import dataaccess.CourseDAOImpl;
import dataaccess.DataSource;
import objects.Course;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JSpinner;

public class Professor_PutNotice {

	private JFrame frame;

	String courses;
	private JTextField txtName;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Professor_PutNotice window = new Professor_PutNotice();
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
	public Professor_PutNotice() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		
		// instantiating frame
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 1800, 870);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.getContentPane().setLayout(null);
		
		// instantiating label
		JLabel lblNoticeForClass = new JLabel("Notice for Class cancellation");
		lblNoticeForClass.setForeground(new Color(0, 128, 0));
		lblNoticeForClass.setFont(new Font("Times New Roman", Font.BOLD, 32));
		lblNoticeForClass.setBounds(634, 73, 483, 102);
		frame.getContentPane().add(lblNoticeForClass);
		
		// instantiating label for notice to students textfield
		JLabel lblReasonOfClass = new JLabel("Notice to students:");
		lblReasonOfClass.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblReasonOfClass.setBounds(534, 303, 173, 56);
		frame.getContentPane().add(lblReasonOfClass);
		
		// text area for notice to students
		JTextArea txtArea = new JTextArea();
		txtArea.setBounds(738, 303, 428, 235);
		frame.getContentPane().add(txtArea);
		
		// instantiating label for course name textfield
		JLabel lblCourse = new JLabel("Course:");
		lblCourse.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblCourse.setBounds(534, 217, 103, 60);
		frame.getContentPane().add(lblCourse);
		
			// making array list of number of courses
			Course newCourse = new Course();	        
	        CourseDAOImpl cImpl = new CourseDAOImpl();
	        List<Course> coursesList = new ArrayList<>(500);
	        try 
	        {
				coursesList = cImpl.FindAllCourse();
			} catch (SQLException e) 
	        {
			}      
	        List<String> list = new ArrayList<String>();	        
	        for(int i = 0; i< coursesList.size(); i++)
	        {
	        	list.add(coursesList.get(i).getCourse_id());
	        }
		
	    // Instantiating combo box for courses list
		JComboBox comboBox = new JComboBox(list.toArray());
		comboBox.setBounds(738, 220, 428, 56);
		frame.getContentPane().add(comboBox);
		
		// initializing button submit that will sent notice when clicked
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setForeground(new Color(255, 255, 255));
		btnSubmit.setBackground(new Color(0, 128, 0));
		btnSubmit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				Object selected = comboBox.getSelectedItem();	
				String id = selected.toString();
				String message = txtArea.getText();
				String name = txtName.getText();
				
					 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd \nHH:mm:ss");
					 LocalDateTime now = LocalDateTime.now();
				     String dateTime = dtf.format(now);				
				try {
					DataSource source = new DataSource();
					Connection con = source.getConnection();
					String query = "update sturegistry set Notification = ?, Professor = ?, Time = ? where course_id = ?";
					PreparedStatement prep = con.prepareStatement(query);    
					prep.setString(1, message);
					prep.setString(2, name);
					prep.setString(3, dateTime);
					prep.setString(4, id);
					prep.executeUpdate();	      
					} catch(SQLException ex) 
					{
					System.out.println(ex.getMessage());			
					}			
			}
			});
		btnSubmit.setBounds(765, 694, 173, 56);
		frame.getContentPane().add(btnSubmit);
		
		// instantiating label for professor's full name
		JLabel lblEnterYourFull = new JLabel("Enter your full name: ");
		lblEnterYourFull.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblEnterYourFull.setBounds(534, 575, 192, 34);
		frame.getContentPane().add(lblEnterYourFull);
		
		// instantiating textfield for professor name textfield
		txtName = new JTextField();
		txtName.setBounds(738, 572, 428, 43);
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		// background design
		JLabel label = new JLabel("");
		Image image = new ImageIcon(this.getClass().getResource("/acbody.jpg")).getImage();
		
		// background design
		JLabel label2 = new JLabel("");
		Image image2 = new ImageIcon(this.getClass().getResource("/alc.jpg")).getImage();
		label2.setIcon(new ImageIcon(image2));
		label2.setBounds(0, -5, 287, 105);
		frame.getContentPane().add(label2);
		
		// instantiating textfield
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBackground(new Color(0, 102, 51));
		textField.setBounds(-475, 0, 2500, 100);
		frame.getContentPane().add(textField);
		label.setIcon(new ImageIcon(image));
		label.setBounds(-2444, -328, 5919, 3000);
		frame.getContentPane().add(label);
					
	}
	
	////
    ////           <<<<  References         >>>>
    ////    https://www.javatpoint.com/java-get-current-date 
	
}


                          
