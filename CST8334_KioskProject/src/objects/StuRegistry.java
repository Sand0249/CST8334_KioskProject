package objects;
/**
 * This class is the student registry object class
 * @author Jin Lu
 */
public class StuRegistry {
	
	private String student_id;
	private String course_id;
	private String term;
	
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
}
