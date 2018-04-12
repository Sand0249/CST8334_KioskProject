package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import objects.Student;
/**
 * This class is the implementation of ClassNotifyDAO 
 * that has some methods to talk to MySQL database
 * @author Jin Lu
 *
 */
public class ClassNotifyDAOImpl implements ClassNotifyDAO{

	private static final String Find_Students_Statement = "select student.student_id,student.student_password,student.student_fname,student.student_lname,student.student_email from student join sturegistry on student.student_id = sturegistry.student_id where sturegistry.course_id = ? and term = ?";
	@Override
	
	/**
	 * This is the search method when user want to 
	 * find a list of students object from course table 
	 * by course id and term
	 * @author Jin Lu
	 *
	 */
	public List<Student> findStudentsbyCourse(String course_id, String term) throws SQLException {

        List<Student> students = Collections.emptyList();
        
        Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Student student = null;
		try{
			DataSource source = new DataSource();
			con = source.getConnection();
			pstmt = con.prepareStatement(Find_Students_Statement);
			pstmt.setString(1, course_id);
			pstmt.setString(2, term);
			
			rs = pstmt.executeQuery();
            students = new ArrayList<>(500);
            
            while( rs.next()){
    			student = new Student();
    			student.setId(rs.getString("student_id"));
    			student.setPw(rs.getString("student_password"));
    			student.setFirstName(rs.getString("student_fname"));
    			student.setLastName(rs.getString("student_lname"));
    			student.setEmail(rs.getString("student_email"));
                students.add(student);

            }
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
        return students;
	}

}
