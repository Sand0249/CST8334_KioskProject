package dataaccess;

import java.sql.SQLException;
/**
 * This is the Course DAO interface 
 * @author Jin Lu
 */
import java.util.List;

import objects.Course;
public interface CourseDAO{
	void insertCourse(Course course) throws SQLException;
	void deleteCourse(Course course) throws SQLException;
	List<Course> FindAllCourse() throws SQLException;
	
	List<Course> FindCoursesByTerm(String term)throws SQLException;

}


