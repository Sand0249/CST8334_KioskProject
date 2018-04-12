package objects;
/**
 * This class is the professor registry object class
 * @author Jin Lu
 */
public class ProfRegistry {
	
	private String professor_id;
	private String course_id;
	private String term;
	
	public String getProfessor_id() {
		return professor_id;
	}
	public void setProfessor_id(String professor_id) {
		this.professor_id = professor_id;
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
