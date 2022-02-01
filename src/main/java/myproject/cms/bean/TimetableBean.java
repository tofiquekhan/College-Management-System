package myproject.cms.bean;

import java.time.LocalDate;

public class TimetableBean extends BaseBean{

	private long subId;
	private String subName;
	private long courseId;
	private String courseName;
	private String semester;
	private LocalDate examDate;
	private String examTime;
	private String description;
	public long getSubId() {
		return subId;
	}
	public void setSubId(long subId) {
		this.subId = subId;
	}
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
	public long getCourseId() {
		return courseId;
	}
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public LocalDate getExamDate() {
		return examDate;
	}
	public void setExamDate(LocalDate examDate) {
		this.examDate = examDate;
	}
	public String getExamTime() {
		return examTime;
	}
	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String getKey() {
		return id+"";
	}
	
	@Override
	public String getValue() {
		return subName;
	}
}
