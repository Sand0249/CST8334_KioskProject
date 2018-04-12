package dataaccess;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import objects.Course;

/**
 * This class is the implementation of courseDAO 
 * that has some methods to talk to MySQL database
 * @author Jin Lu
 *
 */
public class CourseDAOImpl implements CourseDAO{
	/**
	 * This is the search method when user want to 
	 * find a list of courses object from course table 
	 * @author Jin Lu
	 *
	 */
	@Override
	public List<Course> FindAllCourse() throws SQLException {
		List<Course> courses = Collections.emptyList();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Course course = null;
		try{
			DataSource source = new DataSource();
			con = source.getConnection();
			pstmt = con.prepareStatement(
					"SELECT * FROM course");
			
			rs = pstmt.executeQuery();
			courses = new ArrayList<>(500);
			while( rs.next()){
				course = new Course();
				course.setCourse_id(rs.getString("course_id"));
				course.setCourse_name(rs.getString("course_name"));
				course.setCourse_time(rs.getString("course_time"));
				course.setDay_of_week(rs.getString("day_of_week"));
				course.setEnd_date(rs.getString("end_date"));
				course.setRoom(rs.getString("room"));
				course.setStart_date(rs.getString("start_date"));
				course.setTerm(rs.getString("term"));
				courses.add(course);
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
		return courses;
	}
	/**
	 * This is the insert method when user want to 
	 * insert any course object to course table 
	 * @author Jin Lu
	 *
	 */
	@Override
	public void insertCourse(Course Course) throws SQLException {
    	DataSource source = new DataSource();
		Connection con = source.getConnection();
		PreparedStatement pstmt = null;
		try{
			pstmt = con.prepareStatement(
					"INSERT INTO Course (course_id, course_name, term, course_time, day_of_week,start_date,end_date,room) " +
					"VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, Course.getCourse_id());
			pstmt.setString(2, Course.getCourse_name());
			pstmt.setString(3, Course.getTerm());	
			pstmt.setString(4,  Course.getCourse_time());
			pstmt.setString(5,  Course.getDay_of_week());
			pstmt.setString(6,  Course.getStart_date());
			pstmt.setString(7,  Course.getEnd_date());
			pstmt.setString(8,  Course.getRoom());
			
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
	 * delete any course object to course table 
	 * by course id and term
	 * @author Jin Lu
	 *
	 */
	@Override
	public void deleteCourse(Course Course) throws SQLException {
    	DataSource source = new DataSource();
		Connection con = source.getConnection();
		PreparedStatement pstmt = null;
		try{
			pstmt = con.prepareStatement(
			"DELETE FROM course WHERE course_id = ? AND term = ?");
					
			pstmt.setString(1, Course.getCourse_id());
			
			pstmt.setString(2, Course.getTerm());	
				
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
	 * This is the search method when user want to 
	 * find a list of course object from course table 
	 * by term
	 * @author Jin Lu
	 *
	 */

	@Override
	public List<Course> FindCoursesByTerm(String term) throws SQLException {
		List<Course> courses = Collections.emptyList();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Course course = null;
		try{
			DataSource source = new DataSource();
			con = source.getConnection();
			pstmt = con.prepareStatement(
					"SELECT * FROM course where term = ?");
			pstmt.setString(1, term);
			
			rs = pstmt.executeQuery();
			courses = new ArrayList<>(500);
			while( rs.next()){
				course = new Course();
				course.setCourse_id(rs.getString("course_id"));
				course.setCourse_name(rs.getString("course_name"));
				course.setCourse_time(rs.getString("course_time"));
				course.setDay_of_week(rs.getString("day_of_week"));
				course.setEnd_date(rs.getString("end_date"));
				course.setRoom(rs.getString("room"));
				course.setStart_date(rs.getString("start_date"));
				course.setTerm(rs.getString("term"));
				courses.add(course);
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
		return courses;
	}

}