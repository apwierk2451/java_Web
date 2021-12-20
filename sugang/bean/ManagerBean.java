package sugang.bean;



public class ManagerBean {
	private int subject_id; //강의의 등록번호
	private String subject_kind; //강의의 분류
	private String subject_title; //강의이름
	private String professor; //교수
	private String room; //출판사
	private String subject_content; //책의내용
	private int subject_count; //신청 학생 수
	public int getSubject_id() {
		return subject_id;
	}
	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}
	public String getSubject_kind() {
		return subject_kind;
	}
	public void setSubject_kind(String subject_kind) {
		this.subject_kind = subject_kind;
	}
	public String getSubject_title() {
		return subject_title;
	}
	public void setSubject_title(String subject_title) {
		this.subject_title = subject_title;
	}
	public int getSubject_count() {
		return subject_count;
	}
	public void setSubject_count(int subject_count) {
		this.subject_count = subject_count;
	}
	public String getProfessor() {
		return professor;
	}
	public void setProfessor(String professor) {
		this.professor = professor;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getSubject_content() {
		return subject_content;
	}
	public void setSubject_content(String subject_content) {
		this.subject_content = subject_content;
	}
}
