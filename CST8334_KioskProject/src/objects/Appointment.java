package objects;

import java.sql.Date;
/**
 * This class is the appointment object class
 * @author Jin Lu
 */
public class Appointment {
	
	private String professor_id;
	private String student_id;
	private String room;
	private Date APPOINTMENT_TIME;
	
	public String getProfessor_id() {
		return professor_id;
	}
	public void setProfessor_id(String professor_id) {
		this.professor_id = professor_id;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public Date getAPPOINTMENT_TIME() {
		return APPOINTMENT_TIME;
	}
	public void setAPPOINTMENT_TIME(Date aPPOINTMENT_TIME) {
		APPOINTMENT_TIME = aPPOINTMENT_TIME;
	}

}
