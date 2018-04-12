/** 
 * Author:      Simranjit Singh Sandhu
 * Date  :      Feb 20, 2018
 * Description: This class is main page for student panel. Different operations can be performed by clicking buttons 
 */

package Kiosk;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class StudentPanel {

	private JFrame frame;                                    // instantiating frame
	private JTextField textField;							 // instantiating textfield
	private String student_id;								 // instantiating string variable student_id
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					if(args.length >= 1){
						StudentPanel window = new StudentPanel(args[0]);						
						window.frame.setVisible(true);
					}else{
						StudentPanel window = new StudentPanel();
						window.frame.setVisible(true);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StudentPanel() {
		initialize("");
	}
	
	//parameterized constructor
	public StudentPanel(String id) {
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
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.getContentPane().setForeground(Color.BLACK);
		frame.setBounds(25, 25,1625, 875);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		frame.getContentPane().setLayout(null);
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		
		//label
		JLabel lblWelcomeToAlgonquin = new JLabel("Welcome to Algonquin College");
		lblWelcomeToAlgonquin.setBackground(Color.CYAN);
		lblWelcomeToAlgonquin.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblWelcomeToAlgonquin.setBounds(893, 171, 351, 31);
		frame.getContentPane().add(lblWelcomeToAlgonquin);
		
		//button for viewing student's timetable or courses student is studying
		JButton btnNewButton = new JButton("View Timetable");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(0, 128, 0));
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnNewButton.setBounds(951, 368, 257, 37);
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				Student_StudentTimetable open = new Student_StudentTimetable(id);
				String[] str1 = new String[]{id};
				open.main(str1);	
				frame.dispose();			
			}
		});
		frame.getContentPane().add(btnNewButton);
		
		// button for viewing professsor's timetable
		JButton btnNewButton2 = new JButton("View Professor Timetable");
		btnNewButton2.setForeground(new Color(255, 255, 255));
		btnNewButton2.setBackground(new Color(0, 128, 0));
		btnNewButton2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnNewButton2.setBounds(951, 580, 257, 37);
		frame.getContentPane().add(btnNewButton);
		btnNewButton2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Student_ProfessorTimetable ec = new Student_ProfessorTimetable();
				ec.createUI();
			}
		});
		frame.getContentPane().add(btnNewButton2);
		
		// button for registering course
		JButton btnNewButton_4 = new JButton("Course Register");
		btnNewButton_4.setForeground(new Color(255, 255, 255));
		btnNewButton_4.setBackground(new Color(0, 128, 0));
		btnNewButton_4.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnNewButton_4.setBounds(951, 510, 257, 37);
		frame.getContentPane().add(btnNewButton_4);
		btnNewButton_4.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				Student_RegisterCourse open = new Student_RegisterCourse(id);
				String[] str1 = new String[]{id};
				open.main(str1);	
				frame.dispose();			
			}
		});
		
		// button for opening room finder using college website
		JButton btnNewButton_1 = new JButton("Find a Room");
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(0, 128, 0));
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnNewButton_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{		
				try 
				{
					java.awt.Desktop.getDesktop().browse(java.net.URI.create("http://lyceum.algonquincollege.com/roomfinder/"));
				} catch (IOException e1) 
				{				
					e1.printStackTrace();
				}		
			}
		});
		btnNewButton_1.setBounds(951, 438, 257, 37);
		frame.getContentPane().add(btnNewButton_1);
		
		// button for opening book appointment class
		JButton btnNewButton_2 = new JButton("Book an Appointment");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Student_BookAppointment open = new Student_BookAppointment();
				open.main(null);
			}
		});
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setBackground(new Color(0, 128, 0));
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnNewButton_2.setBounds(951, 232, 257, 38);
		frame.getContentPane().add(btnNewButton_2);
		
		// button for viewing notifications of professor for class cancellation 
		JButton btnNewButton_3 = new JButton("Notifications or Announcements");
		btnNewButton_3.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{			
				Student_ViewNotice open = new Student_ViewNotice(id);
				String[] str1 = new String[]{id};
				open.main(str1);	
				frame.dispose();		
			}
		});
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		btnNewButton_3.setBackground(new Color(0, 128, 0));
		btnNewButton_3.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnNewButton_3.setBounds(951, 299, 257, 37);
		frame.getContentPane().add(btnNewButton_3);
		
		// button for going back to login page
		JButton btnBack = new JButton("Back");
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setBackground(new Color(0, 128, 0));
		btnBack.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				frame.dispose();
			}
		});
		btnBack.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnBack.setBounds(734, 625, 99, 38);
		frame.getContentPane().add(btnBack);
		
		// background design
		JLabel label2 = new JLabel("");
		Image image2 = new ImageIcon(this.getClass().getResource("/alc.jpg")).getImage();
		label2.setIcon(new ImageIcon(image2));
		label2.setBounds(0, -13, 299, 102);
		frame.getContentPane().add(label2);
		
		// textfield
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBackground(new Color(0, 102, 51));
		textField.setBounds(-177, -11, 2166, 100);
		frame.getContentPane().add(textField);
		
		//background design
		JLabel label = new JLabel("");
		Image image = new ImageIcon(this.getClass().getResource("/acbody.jpg")).getImage();
		label.setIcon(new ImageIcon(image));
		label.setBounds(-2326, -260, 5919, 3000);
		frame.getContentPane().add(label);
		
	}
}
