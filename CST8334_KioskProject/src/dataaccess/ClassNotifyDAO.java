package dataaccess;

import java.sql.SQLException;
import java.util.List;

import objects.Professor;
import objects.Student;
/**
 * This is the Class notification DAO interface 
 * @author Jin Lu
 */
public interface ClassNotifyDAO {
	
	//Return a list of students by course id and term 
	//from registry table and join with student table.
	List<Student> findStudentsbyCourse (String course_id, String term) throws SQLException;
	
}
