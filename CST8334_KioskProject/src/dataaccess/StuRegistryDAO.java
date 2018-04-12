package dataaccess;

import java.sql.SQLException;
import objects.StuRegistry;
/**
 * This is the student registry DAO interface 
 * @author Jin Lu
 */
public interface StuRegistryDAO {
	
	StuRegistry findStuRegistryByStuID(String id) throws SQLException;
	
	void insertStuRegistry(StuRegistry stuRegistry) throws SQLException;
}
