package dataaccess;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import objects.Appointment;
/**
 * This class is the implementation of AppointmentDAO 
 * that has some methods to talk to MySQL database
 * @author Jin Lu
 *
 */
public class AppointmentDAOImpl implements AppointmentDAO{

	private static final String INSERT_APPOINTMENT = "INSERT INTO APPOINTMENT (professor_id, student_id, room, APPOINTMENT_TIME) VALUES(?, ?, ?, ?)";
	private static final String SELECT_BY_PROFID = "SELECT professor_id, student_id, room, APPOINTMENT_TIME FROM APPOINTMENT WHERE professor_id = ?";
	private static final String SELECT_AFTER_DATE = "SELECT professor_id, student_id, room, APPOINTMENT_TIME FROM APPOINTMENT WHERE APPOINTMENT_TIME >= ?";
	/**
	 * This is the insert method when user want to 
	 * insert any appointment object to appointment table 
	 * @author Jin Lu
	 *
	 */
	@Override
	public void insertAppointment(Appointment appointment) throws SQLException {
		
    	DataSource source = new DataSource();
		Connection con = source.getConnection();
		PreparedStatement pstmt = null;
		try{
			pstmt = con.prepareStatement(INSERT_APPOINTMENT);
			pstmt.setString(1, appointment.getProfessor_id());
			pstmt.setString(2, appointment.getStudent_id());
			pstmt.setString(3, appointment.getRoom());	
			pstmt.setDate(4, appointment.getAPPOINTMENT_TIME());
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
	 * find any appointment object from appointment table 
	 * by professor id
	 * @author Jin Lu
	 *
	 */
	@Override
	public List<Appointment> FindAppointsByProfId(String professor_id) throws SQLException{

        List<Appointment> appointments = Collections.emptyList();
        
        Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Appointment appointment = null;
		try{
			DataSource source = new DataSource();
			con = source.getConnection();
			pstmt = con.prepareStatement(SELECT_BY_PROFID);
			pstmt.setString(1, professor_id);
			
			rs = pstmt.executeQuery();
			appointments = new ArrayList<>(500);
            
            while( rs.next()){
            	appointment = new Appointment();
            	appointment.setProfessor_id(rs.getString("professor_id"));
            	appointment.setStudent_id(rs.getString("student_id"));
            	appointment.setRoom(rs.getString("room"));
            	appointment.setAPPOINTMENT_TIME(rs.getDate("APPOINTMENT_TIME"));
            	appointments.add(appointment);
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
        return appointments;
	}
	/**
	 * This is the search method when user want to 
	 * find any appointment object from appointment table 
	 * by date
	 * @author Jin Lu
	 *
	 */
	@Override
	public List<Appointment> FindAppointsAfterDate(Date date) throws SQLException {

        List<Appointment> appointments = Collections.emptyList();
        
        Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Appointment appointment = null;
		try{
			DataSource source = new DataSource();
			con = source.getConnection();
			pstmt = con.prepareStatement(SELECT_AFTER_DATE);
			pstmt.setDate(1, date);
			
			rs = pstmt.executeQuery();
			appointments = new ArrayList<>(500);
            
            while( rs.next()){
            	appointment = new Appointment();
            	appointment.setProfessor_id(rs.getString("professor_id"));
            	appointment.setStudent_id(rs.getString("student_id"));
            	appointment.setRoom(rs.getString("room"));
            	appointment.setAPPOINTMENT_TIME(rs.getDate("APPOINTMENT_TIME"));
            	appointments.add(appointment);
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
        return appointments;
	}

}
