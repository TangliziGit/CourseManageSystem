package courseManageSystem.entity;

import java.util.ArrayList;

/**
 * The Student class extends User class, which stores information of each student.
 * 
 * @author Chunxu Zhang
 *
 */
public class Student extends User{
	private static final long serialVersionUID = 1L;
	private ArrayList<Course> courseList;
	/**
	 * @param userId
	 * @param userName
	 * @param password
	 * @param information
	 */
	public Student(Integer userId, String userName, String password, StudentInformation information) {
		super(userId, userName, password, information);
		this.courseList = new ArrayList<Course>();
	}
	
	/**
	 * Get the student information.
	 * @return information
	 */
	public StudentInformation getInformation() {
		return (StudentInformation) super.getInformation();
	}
	
	/**
	 * Set the student information.
	 * @param information
	 */
	public void setInformation(StudentInformation information) {
		super.setInformation(information);
	}
	/**
	 * @return the courseList
	 */
	public ArrayList<Course> getCourseList() {
		return courseList;
	}
	/**
	 * @param courseList the courseList to set
	 */
	public void setCourseList(ArrayList<Course> courseList) {
		this.courseList = courseList;
	}
	
	/**
	 * Override hashCode method for HashMap and HashSet.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((courseList == null) ? 0 : courseList.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (courseList == null) {
			if (other.courseList != null)
				return false;
		} else if (!courseList.equals(other.courseList))
			return false;
		return true;
	}
	
	/**
	 * Override toString method.
	 */
	@Override
	public String toString() {
		return "Student [" + super.toString() + ", courseList=" + courseList + "]";
	}
	
}
