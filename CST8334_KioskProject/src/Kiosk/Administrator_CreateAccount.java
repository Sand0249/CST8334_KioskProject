/** 
 * Author:      Simranjit Singh Sandhu
 * Modified by: Jin lu
 * Date  :      Feb  23, 2018
 * Description: This class is used to create new accounts for professor and Student
 */

package Kiosk;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Arrays;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import dataaccess.ProfessorDAOImpl;
import dataaccess.StudentDAOImpl;
import objects.Professor;
import objects.Student;


public class Administrator_CreateAccount {

	private JFrame frame;                                    // creating frame
	private JTextField textField;                           // creating textfield
	private JTextField textField_1;                         // initializing textfield
	private JPasswordField passwordField;                   // initializing password field for password
	private JPasswordField passwordField_1;                 // initializing password field for confirm password
	private JTextField textField_2;                         // // initializing Jtextfield

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Administrator_CreateAccount window = new Administrator_CreateAccount();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 * no arg constructor calling initialize method
	 */
	public Administrator_CreateAccount() {
		initialize();
	}

	/*
	 *  Metod for creating account
	 */
	private void initialize() 
	{
		// initializing JFrame
		frame = new JFrame();                                                          
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 1625, 875);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		
		// initializing JLabel for header of page and adding into frame
		JLabel lblAddNewAccount = new JLabel("Add New Account");                        
		lblAddNewAccount.setBackground(new Color(255, 255, 255));
		lblAddNewAccount.setForeground(new Color(0, 128, 0));
		lblAddNewAccount.setFont(new Font("Arial Black", Font.BOLD, 25));
		lblAddNewAccount.setBounds(817, 113, 261, 36);
		frame.getContentPane().add(lblAddNewAccount);
		
		// initializing JTextfield and adding into frame
		textField = new JTextField();
		textField.setBounds(925, 221, 192, 35);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		// Instantiating textfield and adding into frame
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(925, 286, 192, 35);
		frame.getContentPane().add(textField_1);
		
		// Instantiating radio button for selecting user level as student and adding into frame
		JRadioButton btnStudent = new JRadioButton("Student");
		btnStudent.setBackground(SystemColor.controlHighlight);
		btnStudent.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnStudent.setBounds(910, 485, 129, 36);
		frame.getContentPane().add(btnStudent);
		
		// Instantiating radio button for selecting user level as professor and adding into frame
		JRadioButton btnProfessor = new JRadioButton("Professor");
		btnProfessor.setBackground(SystemColor.controlHighlight);
		btnProfessor.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnProfessor.setBounds(1043, 485, 109, 36);
		frame.getContentPane().add(btnProfessor);
		
		// Instantiating ButtonGroup and both buttons are added into it so when one is selected other get disabled
		ButtonGroup group = new ButtonGroup();
		group.add(btnStudent);
		group.add(btnProfessor);
		
		// Instantiating label for email textfield and adding into frame
		JLabel lblEnterYourAlgonquin = new JLabel("Enter your Algonquin Email:");
		lblEnterYourAlgonquin.setBackground(new Color(255, 255, 255));
		lblEnterYourAlgonquin.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblEnterYourAlgonquin.setBounds(641, 220, 230, 35);
		frame.getContentPane().add(lblEnterYourAlgonquin);
		
		// Instantiating label for email textfield and adding into frame
		JLabel lblEnterYourUser = new JLabel("Enter your username:");
		lblEnterYourUser.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblEnterYourUser.setBounds(641, 285, 230, 35);
		frame.getContentPane().add(lblEnterYourUser);
		
		// Instantiating label for password textfield and adding into frame
		JLabel lblEnterYourPassword = new JLabel("Enter your password:");
		lblEnterYourPassword.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblEnterYourPassword.setBounds(641, 349, 230, 35);
		frame.getContentPane().add(lblEnterYourPassword);
		
		// Instantiating label for confirm textfield and adding into frame
		JLabel lblConfirmPassword = new JLabel("Confirm password:");
		lblConfirmPassword.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblConfirmPassword.setBounds(641, 410, 230, 35);
		frame.getContentPane().add(lblConfirmPassword);
		
		// Instantiating label for selecting user level from combo box and adding into frame
		JLabel lblSelectUserLevel = new JLabel("Select user level:");
		lblSelectUserLevel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblSelectUserLevel.setBounds(641, 485, 230, 35);
		frame.getContentPane().add(lblSelectUserLevel);
		
		// Instantiating textfield for password and adding into frame
		passwordField = new JPasswordField();
		passwordField.setBounds(925, 348, 192, 36);
		frame.getContentPane().add(passwordField);
		
		// Instantiating textfield for confirm password and adding into frame
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(925, 411, 192, 35);
		frame.getContentPane().add(passwordField_1);
		
		// Instantiating button for saving all data in database 
		JButton button = new JButton("Submit");
	    button.setForeground(new Color(255, 255, 255));
	    button.setBackground(new Color(0, 128, 0));
	    button.setFont(new Font("Times New Roman", Font.BOLD, 14));
	    button.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
     
        	String p1 = Arrays.toString(passwordField.getPassword());
        	String p2 = Arrays.toString(passwordField_1.getPassword());   	
        	if (p1.equals(p2)){
        	String one = textField.getText();
        	String two = textField_1.getText();
        	
        	// if any field is empty
        	if (one.equals("")||(two.equals("")))
        	{
        		JOptionPane.showMessageDialog(null, "Some Fields are empty");
        	}
        	
        	// when student user level is selected it is going to save in student table
        	else if(btnStudent.isSelected()){        		
        		Student student = new Student();
        		student.setId(textField_1.getText());
        		student.setPw(String.valueOf(passwordField.getPassword()));
        		student.setFirstName("firstName");
        		student.setEmail(textField.getText());
        		
        		StudentDAOImpl sImpl = new StudentDAOImpl();
        		try 
        		{
        			sImpl.insertStudent(student);        			
        			Administrator_AccountConfirmation open =new Administrator_AccountConfirmation();
        			open.main(null);
        			frame.dispose();    			
        		} catch (SQLException e) 
        		{
        			JOptionPane.showMessageDialog(null, "User already exist");
        		}
        	}
        	
        	// when radio button is selected for professor level it is going to save in professor table
        	else if(btnProfessor.isSelected()){
        		Professor professor = new Professor();
        		professor.setId(textField_1.getText());
        		professor.setPw(String.valueOf(passwordField.getPassword()));
        		professor.setEmail(textField.getText());        		
        		ProfessorDAOImpl pImpl = new ProfessorDAOImpl();
        		try 
        		{
        			pImpl.insertProfessor(professor);    			
        			Administrator_AccountConfirmation open =new Administrator_AccountConfirmation();
        			open.main(null);
        			frame.dispose();       			
        		} catch (SQLException e) 
        		{
        			JOptionPane.showMessageDialog(null, "User already exist");
        		}
        	}
        	
        	// when no radio button is selected for user level
        	else{JOptionPane.showMessageDialog(null, " Please select a level!");};
        }
        
        // when password and confirm password does not match each other
        else{JOptionPane.showMessageDialog(null, " Please check password entry!");}
        }
        });	    
	    button.setBounds(863, 599, 103, 43);
		frame.getContentPane().add(button);
		
		// // Instantiating button for go back to login
		JButton btnExit = new JButton("Back to login page");
		btnExit.setForeground(new Color(255, 255, 255));
		btnExit.setBackground(new Color(0, 128, 0));
		btnExit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				MainPage open = new MainPage();
				open.main(null);
				frame.dispose();				
			}
		});
		btnExit.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnExit.setBounds(817, 703, 192, 53);
		frame.getContentPane().add(btnExit);
		
		// background design
		JLabel label = new JLabel("");
		Image image = new ImageIcon(this.getClass().getResource("/acbody.jpg")).getImage();
		
		// background design
		JLabel label2 = new JLabel("");
		Image image2 = new ImageIcon(this.getClass().getResource("/alc.jpg")).getImage();
		label2.setIcon(new ImageIcon(image2));
		label2.setBounds(-1, -12, 287, 105);
		frame.getContentPane().add(label2);
		
		// Initializing textfield and adding into frame
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBackground(new Color(0, 102, 51));
		textField_2.setBounds(-11, -7, 2500, 100);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		// background design
		label.setIcon(new ImageIcon(image));
		label.setBounds(-45, -560, 5919, 3000);
		frame.getContentPane().add(label);
	}
}
