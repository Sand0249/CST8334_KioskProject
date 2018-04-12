package dataaccess;

import java.sql.SQLException;
import objects.Professor;
/**
 * This is the Professor DAO interface 
 * @author Jin Lu
 */
public interface ProfessorDAO {

	Professor findByID(String id) throws SQLException;
	
	void insertProfessor(Professor professor) throws SQLException;
	void deleteProfessor(Professor professor) throws SQLException;
}

