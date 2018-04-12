package dataaccess;

import java.sql.SQLException;
import objects.Student;
/**
 * This is the student DAO interface 
 * @author Jin Lu
 */
public interface StudentDAO {

	Student findByID(String id) throws SQLException;
	
	void insertStudent(Student student) throws SQLException;
	
	void deleteStudent(Student student) throws SQLException;
}
