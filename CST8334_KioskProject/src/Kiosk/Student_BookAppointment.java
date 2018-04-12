/** 
 * Author:      Simranjit Singh Sandhu
 * Date  :      Feb 23, 2018
 * Description: This class is used to book an appointment with professor using email but college blocks email port so now it is going to save into database
 */

package Kiosk;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import com.sun.xml.internal.fastinfoset.sax.Properties;

import dataaccess.CourseDAOImpl;
import dataaccess.DataSource;
import dataaccess.ProfessorDAOImpl;
import net.proteanit.sql.DbUtils;
import objects.Course;
import objects.Professor;
import sun.swing.SwingUtilities2.Section;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.util.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;

public class Student_BookAppointment 
{
	
	private JFrame frame;                               		// instantiating JFrame
	String reason; 												// instantiating reason as type string
	String mail; 												// instantiating mail as type string
	private JTextField txtDate;									// instantiating textfield for date
	private JTextField txtName;									// instantiating textfield for name 
	public  String str;											// instantiating str as type string
	ResultSet rs = null;           								// instantiating Result set
	private JTextField txtProfId;								// initializing textfield for professor id

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student_BookAppointment window = new Student_BookAppointment();
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
	public Student_BookAppointment() {
		initialize("");
	}
	public Student_BookAppointment(String id) {
		initialize(id);
		
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String id) 
	{
		// creating object for frame
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 1625, 875);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		
		// instantiating label for professor's name field
		JLabel lblSelectUserLevel = new JLabel("Write Professor name");
		lblSelectUserLevel.setForeground(new Color(0, 128, 0));
		lblSelectUserLevel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblSelectUserLevel.setBounds(590, 310, 179, 39);
		frame.getContentPane().add(lblSelectUserLevel);
	
		// initializing label for starting time textfield
		JLabel lblEnterPassword = new JLabel("From:");
		lblEnterPassword.setForeground(new Color(0, 128, 0));
		lblEnterPassword.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblEnterPassword.setBounds(590, 360, 221, 39);
		frame.getContentPane().add(lblEnterPassword);
		
		// initializing label for date textfield
		JLabel lblNewLabel = new JLabel("Date: ");
		lblNewLabel.setForeground(new Color(0, 128, 0));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setBounds(590, 457, 125, 45);
		frame.getContentPane().add(lblNewLabel);
		
		// instantiating button for going back to main student panel when clicked
		JButton backbutton = new JButton("Go back to Student Portal");
		backbutton.setForeground(new Color(255, 255, 255));
	    backbutton.setBackground(new Color(0, 128, 0));
	    backbutton.setFont(new Font("Times New Roman", Font.BOLD, 14));
	    backbutton.setBounds(911, 749, 214, 36);
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
		
	    // initiating label 
		JLabel lblBookAnAppointment = new JLabel("Book an Appointment");
		lblBookAnAppointment.setForeground(new Color(0, 128, 0));
		lblBookAnAppointment.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 36));
		lblBookAnAppointment.setBounds(791, 205, 377, 75);
		frame.getContentPane().add(lblBookAnAppointment);
		
		
		//background design
		JLabel label = new JLabel("");
		Image image2 = new ImageIcon(this.getClass().getResource("/algonquincollege.jpg")).getImage();
		label.setIcon(new ImageIcon(image2));
		label.setBounds(708, 85, 525, 104);
		frame.getContentPane().add(label);
		
		// instantiating label for ending time's textfield
		JLabel lblTo = new JLabel("To:");
		lblTo.setForeground(new Color(0, 128, 0));
		lblTo.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblTo.setBounds(590, 410, 221, 39);
		frame.getContentPane().add(lblTo);
		
		// instantiating textfield for date
		txtDate = new JTextField();
		txtDate.setBackground(new Color(255, 255, 255));
		txtDate.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtDate.setColumns(10);
		txtDate.setBounds(871, 461, 254, 39);
		frame.getContentPane().add(txtDate);
		 Date date = new Date();
		 SimpleDateFormat simple = new SimpleDateFormat("yyyy/MM/dd");
		 txtDate.setText(simple.format(date));
		
		 // initiating label for reason textfield
		JLabel label_1 = new JLabel("Reason: ");
		label_1.setForeground(new Color(0, 128, 0));
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		label_1.setBounds(590, 561, 125, 45);
		frame.getContentPane().add(label_1);
		
		// initializing textarea for message to professor textfield
		JTextArea txtMessage = new JTextArea();
		txtMessage.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtMessage.setBackground(new Color(255, 255, 255));
		txtMessage.setBounds(871, 561, 254, 129);
		frame.getContentPane().add(txtMessage);
		
		// initializing label for student name textfield
		JLabel lblYourName = new JLabel("Your username:");
		lblYourName.setForeground(new Color(0, 128, 0));
		lblYourName.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblYourName.setBounds(590, 513, 125, 45);
		frame.getContentPane().add(lblYourName);
		
		// textfield for student's name
		txtName = new JTextField();
		txtName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtName.setColumns(10);
		txtName.setBackground(new Color(255, 255, 255));
		txtName.setBounds(871, 511, 254, 39);
		frame.getContentPane().add(txtName);
		
		// list for combo box starting time
		String[] timeF = new String[] {"Choose Starting time","8.00 am", "9.00 am", "10.00 am","11.00 am","12.00 pm","1.00 pm", "2.00 pm", "3.00 pm" , "4.00 pm", "5.00 pm"};
		JComboBox comboFrom = new JComboBox(timeF);
		comboFrom.setBounds(871, 363, 254, 36);
		frame.getContentPane().add(comboFrom);
		String from = comboFrom.getSelectedItem().toString();
		
		// list for ending time combo box
		String[] timeT = new String[] {"Choose Ending time","8.00 am", "9.00 am", "10.00 am","11.00 am","12.00 pm","1.00 pm", "2.00 pm", "3.00 pm" , "4.00 pm", "5.00 pm"};
		JComboBox comboTo = new JComboBox(timeT);
		comboTo.setBounds(871, 410, 254, 39);
		frame.getContentPane().add(comboTo);
		String to = comboTo.getSelectedItem().toString();
		
		// instantiating textfield for professor id
		txtProfId = new JTextField();
		txtProfId.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtProfId.setColumns(10);
		txtProfId.setBackground(new Color(255, 255, 255));
		txtProfId.setBounds(871, 310, 254, 39);
		frame.getContentPane().add(txtProfId);
		
		// button for send request to professor. but it is not going to work for sending email now. it will only save in database
		JButton btnSendRequest = new JButton("Send Request");
		btnSendRequest.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{		 
				try 
				{
        			DataSource source = new DataSource();
        			Connection con = source.getConnection();                 
        			String query = "update appoint set  student_id = ?, Apointment = ?, Start_Time = ?, End_Time = ? where professor_id = ?";
        			PreparedStatement prep = con.prepareStatement(query);        			
        			prep.setString(1, txtName.getText());	
        			prep.setString(2, txtMessage.getText());	
        			prep.setString(3, from);	
        			prep.setString(4, to);	
        			prep.setString(5, txtProfId.getText());	
        		     prep.executeUpdate();   		
        		     JOptionPane.showMessageDialog(null, "Appointment booked");
        		     
				} catch(SQLException ex) 
				{
    			ex.printStackTrace();        	
				}
			}
        });
		btnSendRequest.setBounds(610, 760, 214, 36);
		btnSendRequest.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnSendRequest.setForeground(new Color(255, 255, 255));
		btnSendRequest.setBackground(new Color(0, 128, 0));
		frame.getContentPane().add(btnSendRequest);
		
		// background design
		JLabel label2 = new JLabel("");
		Image image = new ImageIcon(this.getClass().getResource("/acbody.jpg")).getImage();
		label2.setIcon(new ImageIcon(image));
		label2.setBounds(-2426, -246, 5919, 3000);
		frame.getContentPane().add(label2);
					
	}

}
