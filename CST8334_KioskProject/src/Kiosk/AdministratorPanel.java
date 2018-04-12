/** 
 * Author:      Simranjit Singh Sandhu
 * Modified by: Jin Lu
 * Date  :      Feb 23, 2018
 * Description: This is admin panel in which different operations can be performed.
 */

package Kiosk;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import dataaccess.ProfessorDAOImpl;
import dataaccess.StudentDAOImpl;
import objects.Professor;
import objects.Student;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

public class AdministratorPanel {

	private JFrame frame;                                                // Instantiating JFrame 
	private JTextField textField;                                        // Instantiating textfield

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdministratorPanel window = new AdministratorPanel();
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
	public AdministratorPanel() {
		initialize();
	}

	/**
	 * method for all GUI elements and buttons performing actions when clicked
	 */
	private void initialize() 
	{
		// Instantiating frame
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 128, 0));
		frame.setBounds(100, 100, 1225, 870);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.getContentPane().setLayout(null);
		
		// instantiating button for opening the create account class
		JButton btnCreateAnAccount = new JButton("Create an account");
		btnCreateAnAccount.setForeground(new Color(0, 100, 0));
		btnCreateAnAccount.setBackground(new Color(255, 255, 255));
		btnCreateAnAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Administrator_CreateAccount open = new Administrator_CreateAccount();
				open.main(null);
				frame.dispose();			
			}
		});
		btnCreateAnAccount.setBounds(874, 307, 166, 49);
		frame.getContentPane().add(btnCreateAnAccount);
		
		// instantiating button for opening the delete account class
		JButton btnDeleteAccount = new JButton("Delete account");
		btnDeleteAccount.setForeground(new Color(0, 128, 0));
		btnDeleteAccount.setBackground(new Color(255, 255, 255));
		btnDeleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Administrator_DeleteAccount open = new Administrator_DeleteAccount();
				open.main(null);				
			}
		});		
		btnDeleteAccount.setBounds(874, 417, 166, 49);
		frame.getContentPane().add(btnDeleteAccount);
		
		// instantiating button for opening the Delete course class
		JButton btnDeleteCourse = new JButton("Delete Course");
		btnDeleteCourse.setForeground(new Color(0, 128, 0));
		btnDeleteCourse.setBackground(new Color(255, 255, 255));
		btnDeleteCourse.setBounds(874, 530, 166, 49);
		btnDeleteCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Administrator_DeleteCourse open = new Administrator_DeleteCourse();
				open.main(null);			
			}
		});
		frame.getContentPane().add(btnDeleteCourse);
		
		// instantiating button for loading bulk data in the form of .csv
		JButton loadStu = new JButton("Load Student Data");
		loadStu.setForeground(new Color(0, 128, 0));
		loadStu.setBackground(new Color(255, 255, 255));
		loadStu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
					// file chooser for opening a file dialog
					JFileChooser chooser = new JFileChooser();
					chooser.showOpenDialog(null);
					File file = chooser.getSelectedFile();
					try 
					{ 
						// bufferedreader for reading values from csv
				        BufferedReader bReader = new BufferedReader(new FileReader(file));
				        String line = ""; 
				        try 
				        {
							while ((line = bReader.readLine()) != null) 
							{
							    try 
							    {
							        if (line != null) 
							        {
							        	// reader for csv file and reading columns using delimiter
							            String[] students = line.split(",+");         
							            Student newStudent = new Student();
							            StudentDAOImpl sImpl = new StudentDAOImpl();
							            newStudent.setId(students[0]);
							            newStudent.setPw(students[1]);
							            newStudent.setEmail(students[4]);							            
							            try 
							            {
											sImpl.insertStudent(newStudent);
											
											
										} catch (SQLException e1) 
							            	{
											JOptionPane.showMessageDialog(null, "Error, please check your csv file");								
							            	}								
							        } 					        
							    }
							    finally
							    {
							       if (bReader == null) 
							        {
							            bReader.close();
							        }
							    }
							}
						} catch (IOException e1) 
				        {
							JOptionPane.showMessageDialog(null, "Error, please check your csv file");
						}
				    } catch (FileNotFoundException ex) 
					{
				        JOptionPane.showMessageDialog(null, "Error, please check your csv file");
				    }	
					JOptionPane.showMessageDialog(null, "Students database build successfully done!");
			}
		});
		loadStu.setBounds(874, 637, 166, 49);
		frame.getContentPane().add(loadStu);
		
		// instantiating button for loading bulk number of professors in database using .csv file
		JButton loadProf = new JButton("Load Professor Data");
		loadProf.setForeground(new Color(0, 128, 0));
		loadProf.setBackground(new Color(255, 255, 255));
		loadProf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				// file chooser for opening window to choose file from computer
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);				
				File file = chooser.getSelectedFile();				
				try 
				{ 
			        BufferedReader bReader = new BufferedReader(new FileReader(file));
			        String line = ""; 
			        try 
			        {
						while ((line = bReader.readLine()) != null) 
						{
						    try 
						    {
						        if (line != null) 
						        {
						            String[] profs = line.split(",+");         
						            Professor newProfessor = new Professor();
						            ProfessorDAOImpl pImpl = new ProfessorDAOImpl();
						            newProfessor.setId(profs[0]);
						            newProfessor.setPw(profs[1]);
						            newProfessor.setEmail(profs[4]);							            
						            try 
						            {
										pImpl.insertProfessor(newProfessor);
										
									} catch (SQLException e1) 
						            {
										JOptionPane.showMessageDialog(null, "Error, please check your csv file");
									}							
						        } 						        
						    }
						    finally
						    {
						       if (bReader == null) 
						        {
						            bReader.close();
						        }
						    }
						}
					} catch (IOException e1) 
			        {
						JOptionPane.showMessageDialog(null, "Error, please check your csv file");
					}
			    } catch (FileNotFoundException ex) 
				{
			        JOptionPane.showMessageDialog(null, "Error, please check your csv file");
			    }	
				JOptionPane.showMessageDialog(null, "Professors database build successfully done!");
		}
	});
		loadProf.setBounds(874, 737, 166, 49);
		frame.getContentPane().add(loadProf);
		
		// instantiating button for exiting the Administartor panel and go back to Main login page
		JButton btnBack = new JButton("Back to Login");
		btnBack.setForeground(new Color(0, 128, 0));
		btnBack.setBackground(new Color(255, 255, 255));
		btnBack.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{			
				MainPage open = new MainPage();
				open.main(null);			
			}
		});
		btnBack.setBounds(874, 837, 166, 49);
		frame.getContentPane().add(btnBack);
		
		// instantiating label
		JLabel lblNewLabel = new JLabel("For Administrator purposes only!");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblNewLabel.setBounds(764, 256, 405, 33);
		frame.getContentPane().add(lblNewLabel);
		
		// background design
		JLabel label = new JLabel("");
		Image image = new ImageIcon(this.getClass().getResource("/ac2.jpg")).getImage();
		label.setIcon(new ImageIcon(image));
		label.setBounds(0, -34, 235, 165);
		frame.getContentPane().add(label);
		
		// instantiating textfield
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBackground(new Color(255, 255, 255));
		textField.setBounds(-11, -11, 2500, 147);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
	}
					///
					///            <<<<References>>>>
					///       https://docs.oracle.com/javase/tutorial/uiswing/components/filechooser.html 
}
