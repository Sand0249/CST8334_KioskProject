 /**
 * Course Info: CST8334 Software Development Project
 * Student Name: Qingwu Liu
 * Date: April 4, 2018
 * Knowledge from roseindia.
 * Retrieved from https://www.roseindia.net/java/example/java/swing/index.shtml
 */

package Kiosk;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class Student_ProfessorTimetable implements ActionListener{
JFrame frame, frame1;
JTextField textbox;
JLabel label;
JButton button;
JPanel panel;
static JTable table;

String driverName = "com.mysql.jdbc.Driver";
String url = "jdbc:mysql://localhost/timetable";
String userName = "timetable";
String password = "password";
String[] columnNames = {"Date", "Section", "Course", "Professor", "Start", "End"};
/**
 * Create Frame, label, button and text box.
 */
public void createUI()
{
frame = new JFrame("Database Search Result");
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setLayout(null);
textbox = new JTextField();
textbox.setBounds(120,30,150,20); 
label = new JLabel("Enter Your Section");
label.setBounds(20, 30, 300, 20);
button = new JButton("search");
button.setBounds(120,130,150,20);
button.addActionListener(this);

frame.add(textbox);
frame.add(label);
frame.add(button);
frame.setVisible(true);
frame.setSize(500, 400); 
frame.setVisible(true);
} 
/**
 * Set action to search button.
 */
public void actionPerformed(ActionEvent ae){
button = (JButton)ae.getSource();
System.out.println("Showing Table Data.......");
showTableData(); 
} 
/**
 * load data from Mysql and show up.
 */
public void showTableData()
{

frame1 = new JFrame("Database Search Result");
frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame1.setLayout(new BorderLayout()); 
DefaultTableModel model = new DefaultTableModel();
model.setColumnIdentifiers(columnNames);
table = new JTable();
table.setModel(model); 
table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
table.setFillsViewportHeight(true);
JScrollPane scroll = new JScrollPane(table);
scroll.setHorizontalScrollBarPolicy(
JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
scroll.setVerticalScrollBarPolicy(
JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
String textvalue = textbox.getText();
String Date= "";
String Section= "";
String Course = "";
String Professor = "";
String Start = "";
String End = "";
/**
 * Write MYSQL query to select searched info.
 */
try
{ 
Class.forName(driverName); 
Connection con = DriverManager.getConnection(url, userName, password);
String sql = "select * from timetable.ptime where Section = "+textvalue;
PreparedStatement ps = con.prepareStatement(sql);
ResultSet rs = ps.executeQuery();
int i =0;
if(rs.next())
{
Course = rs.getString("Course");
Professor = rs.getString("Professor");
Section = rs.getString("Section");
Date = rs.getString("Date"); 
Start = rs.getString("Start"); /**
 * Use ShowMessageDialog to check if there ware no record found in database.
 */
End = rs.getString("End"); 

model.addRow(new Object[]{Date, Section, Course, Professor, Start, End });
i++; 
}
if(i <1)
{
JOptionPane.showMessageDialog(null, "No Record Found","Error",
JOptionPane.ERROR_MESSAGE);
}
if(i ==1)
{
System.out.println(i+" Record Found");
}
else
{
System.out.println(i+" Records Found");
}
}
catch(Exception ex)
{
JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",
JOptionPane.ERROR_MESSAGE);
}
frame1.add(scroll);
frame1.setVisible(true);
frame1.setSize(400,300);
}}


