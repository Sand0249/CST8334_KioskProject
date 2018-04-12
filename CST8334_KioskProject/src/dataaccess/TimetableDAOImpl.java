package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import objects.Course;

public class TimetableDAOImpl implements TimetableDAO{

	private static final String INSERT_COURSE = "INSERT INTO course (course_id, course_name, term, course_time, day_of_week, start_date, end_date, room) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String Find_Course_Statement = "select * from course join sturegistry on course.course_id = sturegistry.course_id where sturegistry.term = course.term and sturegistry.student_id = ? and sturegistry.term = ?";
	private static final String Find_Course_By_Id_Statement = "select * from course join sturegistry on course.course_id = sturegistry.course_id where sturegistry.term = course.term and sturegistry.student_id = ?";
	
	@Override
	public List<Course> findCoursesbyStu(String student_id, String term) throws SQLException {
        List<Course> courses = Collections.emptyList();
        
        Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Course course = null;
		try{
			DataSource source = new DataSource();
			con = source.getConnection();
			pstmt = con.prepareStatement(Find_Course_Statement);
			pstmt.setString(1, student_id);
			pstmt.setString(2, term);
			
			rs = pstmt.executeQuery();
			courses = new ArrayList<>(500);
            
            while( rs.next()){
            	course = new Course();
            	course.setCourse_id(rs.getString("course_id"));
            	course.setCourse_name(rs.getString("course_name"));
            	course.setTerm(rs.getString("term"));
            	course.setCourse_time(rs.getString("course_time"));
            	course.setDay_of_week(rs.getString("day_of_week"));
            	course.setStart_date(rs.getString("start_date"));
            	course.setEnd_date(rs.getString("end_date"));
            	course.setRoom(rs.getString("room"));
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

	@Override
	public void insertCourse(Course course) throws SQLException {
		
    	DataSource source = new DataSource();
		Connection con = source.getConnection();
		PreparedStatement pstmt = null;
		try{
			pstmt = con.prepareStatement(INSERT_COURSE);
			pstmt.setString(1, course.getCourse_id());
			pstmt.setString(2, course.getCourse_name());
			pstmt.setString(3, course.getTerm());	
			pstmt.setString(4, course.getCourse_time());
			pstmt.setString(5, course.getDay_of_week());
			pstmt.setString(6, course.getStart_date());
			pstmt.setString(7, course.getEnd_date());	
			pstmt.setString(8, course.getRoom());
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

	@Override
	public List<Course> findCoursesbyStuID(String student_id) throws SQLException {
	       List<Course> courses = Collections.emptyList();
	        
	        Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Course course = null;
			try{
				DataSource source = new DataSource();
				con = source.getConnection();
				pstmt = con.prepareStatement(Find_Course_By_Id_Statement);
				pstmt.setString(1, student_id);
				
				rs = pstmt.executeQuery();
				courses = new ArrayList<>(500);
	            
	            while( rs.next()){
	            	course = new Course();
	            	course.setCourse_id(rs.getString("course_id"));
	            	course.setCourse_name(rs.getString("course_name"));
	            	course.setTerm(rs.getString("term"));
	            	course.setCourse_time(rs.getString("course_time"));
	            	course.setDay_of_week(rs.getString("day_of_week"));
	            	course.setStart_date(rs.getString("start_date"));
	            	course.setEnd_date(rs.getString("end_date"));
	            	course.setRoom(rs.getString("room"));
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
