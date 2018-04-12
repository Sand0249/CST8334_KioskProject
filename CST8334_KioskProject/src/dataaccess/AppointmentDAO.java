package dataaccess;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import objects.Appointment;
/**
 * This is the appointment DAO interface 
 * @author Jin Lu
 */
public interface AppointmentDAO {
	
	void insertAppointment(Appointment appointment) throws SQLException;
	
	//Return a list of appointments by professor id.
	List<Appointment> FindAppointsByProfId(String professor_id) throws SQLException;
	
	//Return a list of appointments after a date 
	List<Appointment> FindAppointsAfterDate(Date date) throws SQLException;
	
	
	
}
