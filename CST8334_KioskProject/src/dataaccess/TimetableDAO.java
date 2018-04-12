package dataaccess;

import java.sql.SQLException;
import java.util.List;
import objects.Course;
/**
 * This is the time table DAO interface 
 * @author Jin Lu
 */
public interface TimetableDAO {
	
	List<Course> findCoursesbyStu (String student_id, String term) throws SQLException;
	
	//List<Course> findCoursesbyProf (String professor_id, String term) throws SQLException;
	
	void insertCourse(Course course) throws SQLException;
	
	List<Course> findCoursesbyStuID (String student_id) throws SQLException;
}
