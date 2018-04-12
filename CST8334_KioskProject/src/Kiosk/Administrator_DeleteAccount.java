/**
 * Author: YunFeng Yu
 * Date: April 6,2018
 * Description: This class is used to deleting the student account or professor account by Admin 
 * 
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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import dataaccess.ProfessorDAOImpl;
import dataaccess.StudentDAOImpl;
import objects.Professor;
import objects.Student;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Administrator_DeleteAccount {

	private JFrame frame;
	public JComboBox comboBox;
	private JTextField UID;
	private JButton btnSubmit;
	private JButton btnGoBack;
	private JLabel label;
	private JTextField textField;
	private JLabel label_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Administrator_DeleteAccount window = new Administrator_DeleteAccount();
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
	public Administrator_DeleteAccount() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 1625, 875);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		
		/**
		 * Author: YunFeng Yu
		 * Description: create a Jlabel for this class
		 */
		JLabel Deleteuser = new JLabel("Welcome To Delete User");
		Deleteuser.setForeground(new Color(0, 128, 0));
		Deleteuser.setFont(new Font("Times New Roman", Font.BOLD, 26));
		Deleteuser.setBounds(674, 132, 285, 97);
		frame.getContentPane().add(Deleteuser);
		
		
		/**
		 * Author: YunFeng Yu
		 * Description: create a Jlabel for admin to choose group
		 */
		
		JLabel ChooseG = new JLabel("Choose Group: ");
		ChooseG.setFont(new Font("Times New Roman", Font.BOLD, 18));
		ChooseG.setBounds(558, 305, 134, 42);
		frame.getContentPane().add(ChooseG);
		/**
		 * Author: YunFeng Yu
		 * Description: create a comboBox for admin to select for the group
		 */
		String[] items = new String[] {"Choose one", "Student", "Professor"};
		comboBox = new JComboBox(items);
		comboBox.setFont(new Font("Times New Roman", Font.BOLD, 18));
		comboBox.setBackground(SystemColor.text);
		comboBox.setBounds(707, 301, 160, 50);
		frame.getContentPane().add(comboBox);
		/**
		 * Author: YunFeng Yu
		 * Description: create a Jlabel admin to enter user ID
		 */
		JLabel UserID = new JLabel("Enter User ID :");
		UserID.setFont(new Font("Times New Roman", Font.BOLD, 18));
		UserID.setBounds(558, 445, 134, 45);
		frame.getContentPane().add(UserID);
		
		UID = new JTextField();
		UID.setForeground(Color.BLACK);
		UID.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		UID.setBounds(707, 443, 285, 50);
		frame.getContentPane().add(UID);
		
		/**
		 * Author: YunFeng Yu
		 * Description: create a JButton for admin to submit which account to deleting
		 */
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(688, 546, 123, 29);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object selected = comboBox.getSelectedItem();
				if (selected.toString().equals("Student")){
					//System.out.println("student");
					Student student = new Student();
	        		StudentDAOImpl sImpl = new StudentDAOImpl();
	        		try {
	        			student = sImpl.findByID(UID.getText());
	        			if (student != null){
	        				sImpl.deleteStudent(student);	        				
	        				JOptionPane.showMessageDialog(null, "This Student have been successfully deleted!");	        				
	        			}
	        		        			
	        		} catch (SQLException e1) {
	        			// TODO Auto-generated catch block
	        			e1.printStackTrace();
	        			JOptionPane.showMessageDialog(null, "Invalid Username. Please check username");
	        		}
				}
				else if (selected.toString().equals("Professor")){
					//System.out.println("student");
					Professor prof = new Professor();
					ProfessorDAOImpl pImpl = new ProfessorDAOImpl();
					prof.setId(UID.getText());
	        		
	        		try {
	        			prof = pImpl.findByID(UID.getText());
	        			if(prof != null){
	        			pImpl.deleteProfessor(prof);
	        			JOptionPane.showMessageDialog(null, "This Professor have been successfully deleted!");
	        			}
	        		} catch (SQLException e1) {
	        			// TODO Auto-generated catch block
	        			/**
	        			 * Author: YunFeng Yu
	        			 * Description: check error with error message
	        			 */
	        			e1.printStackTrace();
	        			JOptionPane.showMessageDialog(null, "Invalid Username. Please check username");
	        		}
				}
				else{JOptionPane.showMessageDialog(null, "Please select student or professor to delete");}
				
			}
		});
		
		frame.getContentPane().add(btnSubmit);
		
		/**
		 * Author: YunFeng Yu
		 * Description: create a Jbutton for admin to go back
		 */
		btnGoBack = new JButton("Go Back");
		btnGoBack.setBounds(884, 546, 123, 29);
		frame.getContentPane().add(btnGoBack);
		/**
		 * Author: YunFeng Yu
		 * Description: the style for the outlooking
		 */
		label = new JLabel("");
		Image image = new ImageIcon(this.getClass().getResource("/acbody.jpg")).getImage();
		
		JLabel label2 = new JLabel("");
		Image image2 = new ImageIcon(this.getClass().getResource("/alc.jpg")).getImage();
		label2.setIcon(new ImageIcon(image2));
		label2.setBounds(0, -5, 287, 105);
		frame.getContentPane().add(label2);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBackground(new Color(0, 102, 51));
		textField.setBounds(-420, 0, 2500, 100);
		frame.getContentPane().add(textField);
		label.setIcon(new ImageIcon(image));
		label.setBounds(-2084, -568, 5919, 3000);
		frame.getContentPane().add(label);
		btnGoBack.addActionListener(new ActionListener() {	
				public void actionPerformed(ActionEvent e) {
					AdministratorPanel open =new AdministratorPanel();
					open.main(null);
					frame.dispose();			   
				}
			});
		
		
	}

}
