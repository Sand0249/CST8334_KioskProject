/** 
 * Author:      Jin lu
 * Modified by: Simranjit Singh Sandhu
 * Date  :      April 2, 2018
 * Description: This class is used to register course to students that they want to study
 */

package Kiosk;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import dataaccess.CourseDAOImpl;
import dataaccess.StuRegistryDAOImpl;
import objects.Course;
import objects.StuRegistry;

public class Student_RegisterCourse {

	private JFrame frame;                              // initializing frame
	private JTextField textFieldusername;			   // initializing textfield
	private JComboBox comboBox; 					   // initializng combo box
	private JComboBox comboBox2;                       // initializing combo box
	private String student_id;                         // initializing student_id as a type of string
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					if(args.length >= 1){
						System.out.println(args[0]);
						Student_RegisterCourse window = new Student_RegisterCourse(args[0]);
						window.frame.setVisible(true);
					}else{
						Student_RegisterCourse window = new Student_RegisterCourse();
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
	public Student_RegisterCourse() {
		initialize("");
	}
	
	// parameterized constructor
	public Student_RegisterCourse(String id) {
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
		frame.setBounds(100, 100, 1625, 875);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);

		//creating label for course registry textfield
		JLabel lblAddNewAccount = new JLabel("Course Registry");
		lblAddNewAccount.setBackground(new Color(255, 255, 255));
		lblAddNewAccount.setForeground(new Color(0, 128, 0));
		lblAddNewAccount.setFont(new Font("Arial Black", Font.BOLD, 25));
		lblAddNewAccount.setBounds(817, 113, 261, 36);
		frame.getContentPane().add(lblAddNewAccount);

		// label for username textfield
		JLabel lblEnterYourUser = new JLabel("Enter your username");
		lblEnterYourUser.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblEnterYourUser.setBounds(641, 220, 230, 35);
		frame.getContentPane().add(lblEnterYourUser);
		
		// textfield for username
		textFieldusername = new JTextField();
		textFieldusername.setBounds(925, 221, 192, 35);
		frame.getContentPane().add(textFieldusername);
		textFieldusername.setColumns(10);
        
		// label for combo box
		JLabel lblChooseterm = new JLabel("Choose a Course from list");
        lblChooseterm.setBackground(new Color(255, 255, 255));
        lblChooseterm.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lblChooseterm.setBounds(641, 285, 230, 35);
        frame.getContentPane().add(lblChooseterm);
             
        // fetching list of courses from course table and saving it into array list
        Course newCourse = new Course();      
        CourseDAOImpl cImpl = new CourseDAOImpl();
        List<Course> coursesList = new ArrayList<>(500);
        try 
        {
			coursesList = cImpl.FindAllCourse();
		} catch (SQLException e) 
        {
						e.printStackTrace();
		}     
        List<String> list = new ArrayList<String>();    
        for(int i = 0; i< coursesList.size(); i++)
        {
        	list.add(coursesList.get(i).getCourse_id()+ "," + coursesList.get(i).getTerm());
        }
        
        // creating combox and adding arraylist into it
        comboBox = new JComboBox(list.toArray());
		comboBox.setBackground(Color.WHITE);
		comboBox.setBounds(925, 285, 192, 35);
		frame.getContentPane().add(comboBox);
		
		// creating button for going back to main student panel when clicked
		JButton backbutton = new JButton("Go back to Student Portal");
		backbutton.setForeground(new Color(255, 255, 255));
	    backbutton.setBackground(new Color(0, 128, 0));
	    backbutton.setFont(new Font("Times New Roman", Font.BOLD, 14));
	    backbutton.setBounds(1063, 599, 203, 43);
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
	    
	    // button for saving data into database
        JButton button = new JButton("Submit");
	    button.setForeground(new Color(255, 255, 255));
	    button.setBackground(new Color(0, 128, 0));
	    button.setFont(new Font("Times New Roman", Font.BOLD, 14));
	    button.setBounds(763, 599, 203, 43);
	    button.addActionListener(new ActionListener() 
	    {	
			public void actionPerformed(ActionEvent e) 
			{
				StuRegistryDAOImpl sImpl = new StuRegistryDAOImpl();				
				StuRegistry stuRegistry = new StuRegistry();
				Object selected = comboBox.getSelectedItem();				
				
				List<String> coursePartialList = Arrays.asList(selected.toString().split(","));
				stuRegistry.setCourse_id(coursePartialList.get(0));
				stuRegistry.setTerm(coursePartialList.get(1));
				stuRegistry.setStudent_id(textFieldusername.getText());
				
				try 
				{
					sImpl.insertStuRegistry(stuRegistry);
					JOptionPane.showMessageDialog(null, "Success Registration!");
					System.out.println("Sucess");
				} catch (SQLException e1) 
				{				
					JOptionPane.showMessageDialog(null, "Invalid Username. Please check username");
				}			   
			}
		});
		frame.getContentPane().add(button);
		
		// background design
		JLabel label = new JLabel("");
		Image image = new ImageIcon(this.getClass().getResource("/acbody.jpg")).getImage();
		
		//background design
		JLabel label2 = new JLabel("");
		Image image2 = new ImageIcon(this.getClass().getResource("/alc.jpg")).getImage();
		label2.setIcon(new ImageIcon(image2));
		label2.setBounds(-1, -12, 287, 105);
		frame.getContentPane().add(label2);
		
		//background design
		label.setIcon(new ImageIcon(image));
		label.setBounds(-45, -560, 5919, 3000);
		frame.getContentPane().add(label);
	}
}
