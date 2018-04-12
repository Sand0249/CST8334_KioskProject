package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import objects.Student;
/**
 * This class is the implementation of StudentDAO 
 * that has some methods to talk to MySQL database
 * @author Jin Lu
 *
 */
public class StudentDAOImpl implements StudentDAO{
	/**
	 * This is the search method when user want to 
	 * find a student object from student table 
	 * by student id
	 * @author Jin Lu
	 *
	 */
	@Override
	public Student findByID(String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Student student = null;
		try{
			DataSource source = new DataSource();
			con = source.getConnection();
			pstmt = con.prepareStatement(
					"SELECT * FROM student WHERE student_id = ?");
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			rs.next();
			student = new Student();
			student.setId(rs.getString("student_id"));
			student.setPw(rs.getString("student_password"));
			student.setFirstName(rs.getString("student_fname"));
			student.setLastName(rs.getString("student_lname"));
			student.setEmail(rs.getString("student_email"));
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
			throw ex;
		}
		finally{
			try{ if(rs != null){ rs.close(); }}
			catch(SQLException ex){System.out.println(ex.getMessage());}
			try{ if(pstmt != null){ pstmt.close(); }}
			catch(SQLException ex){System.out.println(ex.getMessage());}
			try{ if(con != null){ con.close(); }}
			catch(SQLException ex){System.out.println(ex.getMessage());}
		}
		return student;
	}
	/**
	 * This is the insert method when user want to 
	 * insert any student object to student table 
	 * @author Jin Lu
	 *
	 */
	@Override
	public void insertStudent(Student student) throws SQLException {
    	DataSource source = new DataSource();
		Connection con = source.getConnection();
		PreparedStatement pstmt = null;
		try{
			pstmt = con.prepareStatement(
					"INSERT INTO student (student_id, student_password, student_fname, student_lname, student_email) " +
					"VALUES(?, ?, ?, ?, ?)");
			pstmt.setString(1, student.getId());
			pstmt.setString(2, student.getPw());
			pstmt.setString(3, student.getFirstName());	
			pstmt.setString(4,  student.getLastName());
			pstmt.setString(5,  student.getEmail());
			pstmt.executeUpdate();
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
			throw ex;
		}
		finally{
			try{ if(pstmt != null){ pstmt.close(); }}
			catch(SQLException ex){System.out.println(ex.getMessage());}
			try{ if(con != null){ con.close(); }}
			catch(SQLException ex){System.out.println(ex.getMessage());}
		}
		
	}
	/**
	 * This is the delete method when user want to 
	 * delete any student object to student table
	 * by student id 
	 * @author Jin Lu
	 *
	 */
	@Override
	public void deleteStudent(Student student) throws SQLException {
    	DataSource source = new DataSource();
		Connection con = source.getConnection();
		PreparedStatement pstmt = null;
		try{
			pstmt = con.prepareStatement("delete from student where student_id = ?");
			pstmt.setString(1, student.getId());

			pstmt.executeUpdate();
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
			throw ex;
		}
		finally{
			try{ if(pstmt != null){ pstmt.close(); }}
			catch(SQLException ex){System.out.println(ex.getMessage());}
			try{ if(con != null){ con.close(); }}
			catch(SQLException ex){System.out.println(ex.getMessage());}
		}
	}

}
