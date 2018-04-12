package dataaccess;

import java.sql.SQLException;
/**
 * This is the professor registry DAO interface 
 * @author Jin Lu
 */

import objects.ProfRegistry;

public interface ProfRegistryDAO {
	
	ProfRegistry findProfRegistryByProfID(String id) throws SQLException;
	
	void insertProfRegistry(ProfRegistry profRegistry) throws SQLException;
	
	
	
}
