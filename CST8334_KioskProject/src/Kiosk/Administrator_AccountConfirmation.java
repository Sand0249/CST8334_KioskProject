/** 
 * Author: Simranjit Singh Sandhu
 * Date  : Feb 25, 2018
 * Description: This class displays a confirmation message when new account is created
 */

package Kiosk;                                

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Administrator_AccountConfirmation {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Administrator_AccountConfirmation window = new Administrator_AccountConfirmation();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 *  No arg constructor calling initialize method
	 */
	public Administrator_AccountConfirmation() {
		initialize();
	}

	/*
	 * method handling all GUI elements and and shows confirmation message when new account is created
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.getContentPane().setForeground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 1625, 870);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Your account has been created");
		lblNewLabel.setForeground(new Color(0, 128, 0));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel.setBounds(922, 369, 289, 82);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewl = new JLabel("Congratulations!");
		lblNewl.setForeground(new Color(0, 128, 0));
		lblNewl.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
		lblNewl.setBackground(SystemColor.textHighlight);
		lblNewl.setBounds(971, 245, 181, 62);
		frame.getContentPane().add(lblNewl);
		
		JButton btnBackToLogin = new JButton("Back to Login");
		btnBackToLogin.setBackground(new Color(0, 128, 0));
		btnBackToLogin.setForeground(new Color(255, 255, 255));
		btnBackToLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MainPage open = new MainPage();
				open.main(null);
				frame.dispose();
				
			}
		});
		btnBackToLogin.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnBackToLogin.setBounds(993, 532, 159, 52);
		frame.getContentPane().add(btnBackToLogin);
		
		JLabel label = new JLabel("");
		Image image = new ImageIcon(this.getClass().getResource("/algonquincollege.jpg")).getImage();
		label.setIcon(new ImageIcon(image));
		label.setBounds(799, 53, 559, 103);
		frame.getContentPane().add(label);
	}
}
