/** 
 * Author:      Simranjit Singh Sandhu
 * Date  :      Feb 20, 2018
 * Description: This class is Main page for professor panel which contains different buttons to perform several operations.
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


public class ProfessorPanel {

	private JFrame frame;
	private JTextField textField;
	private String professor_id;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					if(args.length >= 1){						
						ProfessorPanel window = new ProfessorPanel(args[0]);
						window.frame.setVisible(true);
					}else{
						ProfessorPanel window = new ProfessorPanel();
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
	public ProfessorPanel() {
		initialize("");
	}
	
	//parametererized constructor
	public ProfessorPanel(String id) {
		this.professor_id = id;
		initialize(id);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String id) 
	{
		// instantiating frame
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.getContentPane().setLayout(null);
		frame.setBounds(100, 100, 1625, 875);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		
		// instantiating label
		JLabel lblWelcomeToAlgonquin = new JLabel("Welcome to Algonquin College");
		lblWelcomeToAlgonquin.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblWelcomeToAlgonquin.setBounds(710, 171, 351, 31);
		frame.getContentPane().add(lblWelcomeToAlgonquin);
		
		// instantiating button for add timetable which will open Add timetable class when clicked
		JButton btnAddTimetable = new JButton("Add Timetable");
		btnAddTimetable.setForeground(new Color(255, 255, 255));
		btnAddTimetable.setBackground(new Color(0, 128, 0));
		btnAddTimetable.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Professor_AddTimetable lc = new Professor_AddTimetable();
				lc.setVisible(true);
			}
		});
		btnAddTimetable.setBounds(796, 267, 153, 43);
		frame.getContentPane().add(btnAddTimetable);
		
		// instantiating button for put a notice by professor 
		JButton btnNewButton = new JButton("Put a Notice");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(0, 128, 0));
		btnNewButton.setBounds(796, 342, 153, 43);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
			Professor_PutNotice open = new Professor_PutNotice();
			open.main(null);
			}
		});
		frame.getContentPane().add(btnNewButton);
		
		// instantiating button for open course register class
		JButton btnNewButton_4 = new JButton("Course Register");
		btnNewButton_4.setForeground(new Color(255, 255, 255));
		btnNewButton_4.setBackground(new Color(0, 128, 0));
		btnNewButton_4.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnNewButton_4.setBounds(796, 510, 153, 43);
		frame.getContentPane().add(btnNewButton_4);
		btnNewButton_4.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				Professor_RegisterCourse open = new Professor_RegisterCourse(id);
				String[] str1 = new String[]{id};
				open.main(str1);	
				frame.dispose();
			}
		});
		
		// button for opening room finder using Algonquin college website
		JButton btnNewButton_1 = new JButton("Find a Room");
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(0, 128, 0));
		btnNewButton_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					java.awt.Desktop.getDesktop().browse(java.net.URI.create("http://lyceum.algonquincollege.com/roomfinder/"));
				} catch (IOException e1) {}
			}
		});
		btnNewButton_1.setBounds(796, 428, 153, 43);
		frame.getContentPane().add(btnNewButton_1);
		
		// instantiating button for exit from professor panel when clicked
		JButton btnBack = new JButton("Back");
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setBackground(new Color(0, 128, 0));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnBack.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnBack.setBounds(544, 594, 107, 43);
		frame.getContentPane().add(btnBack);
		
		// background design
		JLabel label = new JLabel("");
		Image image = new ImageIcon(this.getClass().getResource("/acbody.jpg")).getImage();
		
		// background design
		JLabel label2 = new JLabel("");
		Image image2 = new ImageIcon(this.getClass().getResource("/alc.jpg")).getImage();
		label2.setIcon(new ImageIcon(image2));
		label2.setBounds(0, 0, 299, 100);
		frame.getContentPane().add(label2);
		
		// instantiating textfield 
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBackground(new Color(0, 102, 51));
		textField.setBounds(-228, 0, 2166, 100);
		frame.getContentPane().add(textField);
		label.setIcon(new ImageIcon(image));
		label.setBounds(-124, -443, 5919, 3000);
		frame.getContentPane().add(label);
		
	}
}
