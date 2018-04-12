/** 
 * Author:      Simranjit Singh Sandhu
 * Modified by: Jin Lu 
 * Date  :      Feb 23, 2018
 * Description: This is Main login page to enter in one of the user level by entering credentials.
 */

package Kiosk;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import dataaccess.ProfessorDAOImpl;
import dataaccess.StudentDAOImpl;
import objects.Professor;
import objects.Student;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JFormattedTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Point;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import java.awt.Window.Type;
import java.awt.Frame;
import java.awt.Image;

public class MainPage {

	private JFrame frame;
	
	private JTextField username;
	private JPasswordField password;
	private JLabel lblLoginPage;
	private JTextField txtEnterUsername;
	private JTextField txtEnterPassword;
	private JLabel label_1;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					MainPage window = new MainPage();
					window.frame.setVisible(true);
					
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// instantiating frame 
		frame = new JFrame();
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.getContentPane().setForeground(SystemColor.info);
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(-16, -31, 1625, 875);		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// instantiating textfield for username
		username = new JTextField();
		username.setBounds(1006, 263, 209, 48);
		username.setForeground(Color.BLACK);
		username.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		username.setColumns(10);
		
		// instantiating textfield for password field
		password = new JPasswordField();
		password.setBounds(1006, 365, 209, 48);
		password.setForeground(Color.BLACK);
		password.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		// instantiating label 
		lblLoginPage = new JLabel("Login Page");
		lblLoginPage.setBounds(903, 164, 141, 47);
		lblLoginPage.setFont(new Font("Times New Roman", Font.BOLD, 22));
		
		// instantiating login button for entering into application
		JButton btnLogin = new JButton("Login");
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setBackground(new Color(0, 128, 0));
		btnLogin.setBounds(889, 605, 155, 48);
		btnLogin.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) 
			{				
				String user = username.getText();
				String pass = String.valueOf(password.getPassword());							
			    Student newStu = new Student();
				Professor newProf = new Professor();				
				Object selected = comboBox.getSelectedItem();	
				
				// when administrator level is selected from combo box
				if(selected.toString().equals("Administrator")) 
				{					
					if(user.equals("Admin") && pass.equals("admin")) 
					{						
						AdministratorPanel open = new AdministratorPanel();
						open.main(null);
						frame.dispose();						
					}else
					{
						JOptionPane.showMessageDialog(null, "Invalid Password or username. Please check.");
					}
				}
				
				// when student level is selected from combo box
				else if(selected.toString().equals("Student")) 
				{					
		           	StudentDAOImpl sImpl = new StudentDAOImpl();
		           	try 
		           	{
						newStu = sImpl.findByID(user);
						System.out.println("User real password in database "+newStu.getPw());
						System.out.println("User input password "+pass);						
					} catch (SQLException e1) 
		           	{
						JOptionPane.showMessageDialog(null, "Invalid Username. Please check username");
					}
		           	if(newStu.getPw().equals(pass))
		           	{    	
		           		System.out.println(newStu.getId());
						StudentPanel welcome = new StudentPanel(newStu.getId());
						String[] str1 = new String[]{newStu.getId()};
						welcome.main(str1);	
					}
		           	else {System.out.println("worng pass");
		           	JOptionPane.showMessageDialog(null, "Invalid Password. Please check password");
		           	}			
				}
				
				// when professor user level is selected from combo box
				else if(selected.toString().equals("Professor")) 
				{					
					ProfessorDAOImpl pImpl = new ProfessorDAOImpl();
		           	try 
		           	{
						newProf = pImpl.findByID(user);
						System.out.println("User real password in database "+newProf.getPw());
						System.out.println("User input password "+pass);					
					} catch (SQLException e1) 
		           	{
						JOptionPane.showMessageDialog(null, "Invalid Username. Please check username");
					}
		           	if(newProf.getPw().equals(pass))
		           	{    	
		           		ProfessorPanel welcome = new ProfessorPanel(newProf.getId());
						String[] str1 = new String[]{newProf.getId()};
						welcome.main(str1);	
					}
		           	else {System.out.println("worng pass");
		           	JOptionPane.showMessageDialog(null, "Invalid Password. Please check password");
		           	}	
				}
				
				// if no level is selected from combo box
				else
				{
					JOptionPane.showMessageDialog(null, " Please choose a user level");
				}				   
			}
		});
		btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 16));
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(lblLoginPage);
		frame.getContentPane().add(username);
		frame.getContentPane().add(password);
		frame.getContentPane().add(btnLogin);
		
		// Creating string for combo box items
		String[] items = new String[] {"Choose one", "Student", "Professor","Administrator"};	
		
		// Instantiating combo box for selecting a user level
		comboBox = new JComboBox(items);
		comboBox.setBackground(Color.WHITE);
		comboBox.setBounds(1006, 467, 209, 54);
		frame.getContentPane().add(comboBox);
		
		// Instantiating textfield 
		JTextField lblSelectUserLevel = new JTextField();
		lblSelectUserLevel.setEditable(false);
		lblSelectUserLevel.setText("Select User level");
		lblSelectUserLevel.setBackground(new Color(0, 128, 0));
		lblSelectUserLevel.setForeground(Color.white);
		lblSelectUserLevel.setFont(new Font("Times New Roman", Font.BOLD, 20));		
		lblSelectUserLevel.setBounds(783, 467, 155, 48);
		frame.getContentPane().add(lblSelectUserLevel);
		
		// background design
		JLabel label = new JLabel("");
		Image image = new ImageIcon(this.getClass().getResource("/algonquincollege.jpg")).getImage();
		label.setIcon(new ImageIcon(image));
		label.setBounds(705, 27, 490, 103);
		frame.getContentPane().add(label);
		
		// instantiating textfield for username
		txtEnterUsername = new JTextField();
		txtEnterUsername.setEditable(false);
		txtEnterUsername.setText("Enter Username:");
		txtEnterUsername.setBackground(new Color(0, 128, 0));
		txtEnterUsername.setForeground(Color.WHITE);
		txtEnterUsername.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txtEnterUsername.setBounds(783, 263, 155, 48);
		frame.getContentPane().add(txtEnterUsername);
		txtEnterUsername.setColumns(10);
		
		// instantiating textfield for password
		txtEnterPassword = new JTextField();
		txtEnterPassword.setEditable(false);
		txtEnterPassword.setText("Enter Password:");
		txtEnterPassword.setForeground(Color.WHITE);
		txtEnterPassword.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txtEnterPassword.setColumns(10);
		txtEnterPassword.setBackground(new Color(0, 128, 0));
		txtEnterPassword.setBounds(783, 365, 155, 48);
		frame.getContentPane().add(txtEnterPassword);
		
		// background design
		JLabel label2 = new JLabel("");
		Image image2 = new ImageIcon(this.getClass().getResource("/acbody.jpg")).getImage();
		label2.setIcon(new ImageIcon(image2));
		label2.setBounds(-4730, -102, 6776, 3000);
		frame.getContentPane().add(label2);
		
	}
}
