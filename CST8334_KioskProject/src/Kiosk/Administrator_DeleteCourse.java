/**
 * Author: YunFeng Yu
 * Date: April 6,2018
 * Description: This class is used to deleting the Course by Admin side
 * 
 */

package Kiosk;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import dataaccess.CourseDAOImpl;
import objects.Course;

public class Administrator_DeleteCourse {

	private JFrame frame;
	public JComboBox comboBox;
	private JButton btnSubmit;
	private JButton btnGoBack;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Administrator_DeleteCourse window = new Administrator_DeleteCourse();
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
	public Administrator_DeleteCourse() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1625, 875);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		/**
		 * Author: YunFeng Yu
		 * Description: create a Jlabel for this class
		 */
		JLabel Deleteuser = new JLabel("Welcome To Delete Course");
		Deleteuser.setFont(new Font("Times New Roman", Font.BOLD, 22));
		Deleteuser.setBounds(321, 15, 407, 86);
		frame.getContentPane().add(Deleteuser);
		/**
		 * Author: YunFeng Yu
		 * Description: create a Jlabel for admin to choose Course ID
		 */
		JLabel ChooseC = new JLabel("Choose Course ID: ");
		ChooseC.setFont(new Font("Times New Roman", Font.BOLD, 16));
		ChooseC.setBounds(55, 166, 160, 42);
		frame.getContentPane().add(ChooseC);
		
		
		
        Course newCourse = new Course();
        
        CourseDAOImpl cImpl = new CourseDAOImpl();
        List<Course> coursesList = new ArrayList<>(500);
        try {
			coursesList = cImpl.FindAllCourse();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        List<String> list = new ArrayList<String>();
        
        for(int i = 0; i< coursesList.size(); i++){
        	list.add(coursesList.get(i).getCourse_id()+ "," + coursesList.get(i).getTerm());
        }
        
        comboBox = new JComboBox(list.toArray());
		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		comboBox.setBackground(SystemColor.text);
		comboBox.setBounds(346, 162, 191, 50);
		frame.getContentPane().add(comboBox);
		/**
		 * Author: YunFeng Yu
		 * Description: create a JButton for admin to submit which course to deleting
		 */
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(233, 427, 123, 29);
		btnSubmit.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				CourseDAOImpl cImpl = new CourseDAOImpl();				
				Course course = new Course();
				Object selected = comboBox.getSelectedItem();				
				
				//ArrayList<String> coursePartialList = (ArrayList<String>) Arrays.asList(selected.toString().split(","));
				List<String> coursePartialList = Arrays.asList(selected.toString().split(","));
				course.setCourse_id(coursePartialList.get(0));
				course.setTerm(coursePartialList.get(1));
		
				try {
					cImpl.deleteCourse(course);
					JOptionPane.showMessageDialog(null, " This course have been successfully deleted!");
					System.out.println("Sucess");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					
					JOptionPane.showMessageDialog(null, "Invalid Username. Please check username");
				}			   
			}
		});
		frame.getContentPane().add(btnSubmit);
		/**
		 * Author: YunFeng Yu
		 * Description: create a JButton for admin to go back
		 */
		
		btnGoBack = new JButton("Go Back");
		btnGoBack.setBounds(473, 427, 123, 29);
		frame.getContentPane().add(btnGoBack);
		btnGoBack.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				AdministratorPanel open =new AdministratorPanel();
				open.main(null);
				frame.dispose();	   
			}
		});
	}

}
