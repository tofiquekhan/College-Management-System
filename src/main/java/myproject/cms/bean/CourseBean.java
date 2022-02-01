package myproject.cms.bean;

/**
 * Course Bean encapsulating Course Attribute
 * @author Tofique Ahmed Khan
 *
 */
public class CourseBean extends BaseBean {

	private String courseName;
	private String description;
	private String duration;
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	@Override
	public String getKey() {
		return id+"";
	}
	
	@Override
	public String getValue() {
		return courseName;
	}
}
