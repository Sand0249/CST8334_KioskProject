/** 
 * Author:      Jin lu
 * Modified by: Haixia Feng
 * Date  :      April 2, 2018
 * Description: This class is used to register course to professor which they want to teach
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
import dataaccess.ProfRegistryDAOImpl;
import objects.Course;
import objects.ProfRegistry;

public class Professor_RegisterCourse {

	private JFrame frame;                                         // initializing frame
	private JTextField textFieldusername;                         // initializing textfield for username
	private JComboBox comboBox;									  // initializing combo box 
	private JComboBox comboBox2;								  // initializing other combo box  
	private String professor_id;							      // initializing string as professor_id

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Professor_RegisterCourse window = new Professor_RegisterCourse();
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
	public Professor_RegisterCourse() {
		initialize("");
	}
	
	public Professor_RegisterCourse(String id) {
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
		frame.setBounds(100, 100, 1625, 875);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);

		// instantiating label
		JLabel lblAddNewAccount = new JLabel("Professor Course Registry");
		lblAddNewAccount.setBackground(new Color(255, 255, 255));
		lblAddNewAccount.setForeground(new Color(0, 128, 0));
		lblAddNewAccount.setFont(new Font("Arial Black", Font.BOLD, 25));
		lblAddNewAccount.setBounds(817, 113, 461, 36);
		frame.getContentPane().add(lblAddNewAccount);

		// instantiating label for username textfield
		JLabel lblEnterYourUser = new JLabel("Enter your username");
		lblEnterYourUser.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblEnterYourUser.setBounds(641, 220, 230, 35);
		frame.getContentPane().add(lblEnterYourUser);
		
		// instantiating textfield for username
		textFieldusername = new JTextField();
		textFieldusername.setBounds(925, 221, 192, 35);
		frame.getContentPane().add(textFieldusername);
		textFieldusername.setColumns(10);
        
		// instantiating label for combo box
		JLabel lblChooseterm = new JLabel("Choose a Course from list");
        lblChooseterm.setBackground(new Color(255, 255, 255));
        lblChooseterm.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lblChooseterm.setBounds(641, 285, 230, 35);
        frame.getContentPane().add(lblChooseterm);
        
        // array list for courses list from database
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
        	list.add(coursesList.get(i).getCourse_id()+ "," + coursesList.get(i).getTerm());
        }
        
        // instantiating combo box 
        comboBox = new JComboBox(list.toArray());
		comboBox.setBackground(Color.WHITE);
		comboBox.setBounds(925, 285, 192, 35);
		frame.getContentPane().add(comboBox);
		
		// instantiating button that will bring back to professor panel
		JButton backbutton = new JButton("Go back to Professor Portal");
		backbutton.setForeground(new Color(255, 255, 255));
	    backbutton.setBackground(new Color(0, 128, 0));
	    backbutton.setFont(new Font("Times New Roman", Font.BOLD, 14));
	    backbutton.setBounds(1063, 599, 203, 43);
	    frame.getContentPane().add(backbutton);
	    backbutton.addActionListener(new ActionListener() 
	    {	
			public void actionPerformed(ActionEvent e) 
			{
				ProfessorPanel open =new ProfessorPanel(id);
				String[] str1 = new String[]{id};
				open.main(str1);	
				frame.dispose();			   
			}
		});
	    
	    // instantiating submit button that will assign course to professor they want to teach, when clicked
        JButton button = new JButton("Submit");
	    button.setForeground(new Color(255, 255, 255));
	    button.setBackground(new Color(0, 128, 0));
	    button.setFont(new Font("Times New Roman", Font.BOLD, 14));
	    button.setBounds(763, 599, 203, 43);
	    button.addActionListener(new ActionListener() 
	    {	
			public void actionPerformed(ActionEvent e) 
			{
				ProfRegistryDAOImpl pImpl = new ProfRegistryDAOImpl();				
				ProfRegistry proRegistry = new ProfRegistry();
				Object selected = comboBox.getSelectedItem();				
				List<String> coursePartialList = Arrays.asList(selected.toString().split(","));
				proRegistry.setCourse_id(coursePartialList.get(0));
				proRegistry.setTerm(coursePartialList.get(1));
				proRegistry.setProfessor_id(textFieldusername.getText());				
				try 
				{
					pImpl.insertProfRegistry(proRegistry);
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
		
		// background design
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

