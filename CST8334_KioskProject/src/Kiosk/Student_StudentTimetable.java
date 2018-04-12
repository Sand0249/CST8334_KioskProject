/** 
 * Author:      Jin lu
 * Modified by: Simranjit Singh Sandhu
 * Date  :      March 27, 2018
 * Description: This class is used to view the student's timetable or view the classes student registered for.
 */

package Kiosk;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ComboBoxModel;
import dataaccess.CourseDAOImpl;
import dataaccess.ProfessorDAOImpl;
import dataaccess.StuRegistryDAOImpl;
import dataaccess.StudentDAOImpl;
import dataaccess.TimetableDAOImpl;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import objects.Course;
import objects.Professor;
import objects.StuRegistry;
import objects.Student;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class Student_StudentTimetable {

	private JFrame frame;                                // instantiating frame
	private JTextField textFieldusername;				 // instantiating textfield forusername
	private JComboBox comboBox;                          // instantiating combobox
	private JComboBox comboBox2;						 // instantiating combo box
	private String student_id;                           // instantiating variable student_id as string type

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student_StudentTimetable window = new Student_StudentTimetable(args[0]);
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
	public Student_StudentTimetable(){
		initialize("");
	}
	
	// parameterized constructor
	public Student_StudentTimetable(String id) {
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

		// creating label 
		JLabel lblAddNewAccount = new JLabel("Student Timetable");
		lblAddNewAccount.setBackground(new Color(255, 255, 255));
		lblAddNewAccount.setForeground(new Color(0, 128, 0));
		lblAddNewAccount.setFont(new Font("Arial Black", Font.BOLD, 25));
		lblAddNewAccount.setBounds(817, 113, 761, 36);
		frame.getContentPane().add(lblAddNewAccount);

		TimetableDAOImpl tImpl = new TimetableDAOImpl();
		List<Course> courseList = new ArrayList<Course>();			
		try 
		{
			System.out.println(id);
			courseList = tImpl.findCoursesbyStuID(id);
			System.out.println(courseList.size());
		} catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
		for(int i=0; i<courseList.size();i++)
		{	
			String courseString = courseList.get(i).getCourse_id() + ","
		            +courseList.get(i).getCourse_name() + ","
					+courseList.get(i).getTerm() + ","
					+courseList.get(i).getCourse_time() + ","
					+courseList.get(i).getDay_of_week() + ","
					+courseList.get(i).getStart_date() + " - "
					+courseList.get(i).getEnd_date() + ","
		     		+courseList.get(i).getRoom() + ",";
	
			// label for username
			JLabel lblEnterYourUser = new JLabel();
			lblEnterYourUser.setFont(new Font("Times New Roman", Font.BOLD, 18));
			lblEnterYourUser.setBounds(641, 220 + i*35, 830, 35);
			lblEnterYourUser.setText(courseString);
			frame.getContentPane().add(lblEnterYourUser);
			System.out.println(courseString);
	
		}
         
		// creating button for going back to main student page when clicked
		JButton backbutton = new JButton("Go back to Student Portal");
		backbutton.setForeground(new Color(255, 255, 255));
	    backbutton.setBackground(new Color(0, 128, 0));
	    backbutton.setFont(new Font("Times New Roman", Font.BOLD, 14));
	    backbutton.setBounds(1063, 999, 203, 43);
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
		JLabel label = new JLabel("");
		Image image = new ImageIcon(this.getClass().getResource("/acbody.jpg")).getImage();
		
		//background design
		JLabel label2 = new JLabel("");
		Image image2 = new ImageIcon(this.getClass().getResource("/alc.jpg")).getImage();
		label2.setIcon(new ImageIcon(image2));
		label2.setBounds(-1, -12, 287, 105);
		frame.getContentPane().add(label2);
		
		// background design
		label.setIcon(new ImageIcon(image));
		label.setBounds(-45, -560, 5919, 3000);
		frame.getContentPane().add(label);
	}

}
