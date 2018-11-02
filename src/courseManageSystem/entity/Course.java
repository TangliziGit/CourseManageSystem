package courseManageSystem.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;

/**
 * The Course class, which stores information for each Course.
 * 
 * @author Chunxu Zhang
 *
 */
public class Course implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer courseId;
	private String courseName;
	private Date startDate;
	private Date endDate;
	private String place;
	private Integer courseCapacity;
	private Integer personNumber;
	private Integer credit;
	private String holderSchool;
	private String teacherName;
	private HashSet<Integer> gradeNeededSet;
	private HashSet<String> schoolNeededSet;
	
	/**
	 * @param courseId
	 * @param courseName
	 * @param startDate
	 * @param endDate
	 * @param place
	 * @param courseCapacity
	 * @param personNumber
	 * @param credit
	 * @param holderSchool
	 * @param teacherName
	 * @param gradeNeededSet
	 * @param schoolNeededSet
	 */
	public Course(Integer courseId, String courseName, Date startDate, Date endDate, String place,
			Integer courseCapacity, Integer personNumber, Integer credit, String holderSchool, String teacherName,
			HashSet<Integer> gradeNeededSet, HashSet<String> schoolNeededSet) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.place = place;
		this.courseCapacity = courseCapacity;
		this.personNumber = personNumber;
		this.credit = credit;
		this.holderSchool = holderSchool;
		this.teacherName = teacherName;
		this.gradeNeededSet = gradeNeededSet;
		this.schoolNeededSet = schoolNeededSet;
	}

	/**
	 * @return the course information to show
	 */
	public String getCourseInformation() {
		return this.toString();
	}
	
	/**
	 * @return the courseId
	 */
	public Integer getCourseId() {
		return courseId;
	}
	/**
	 * @param courseId the courseId to set
	 */
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	/**
	 * @return the courseName
	 */
	public String getCourseName() {
		return courseName;
	}
	/**
	 * @param courseName the courseName to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the place
	 */
	public String getPlace() {
		return place;
	}
	/**
	 * @param place the place to set
	 */
	public void setPlace(String place) {
		this.place = place;
	}
	/**
	 * @return the courseCapacity
	 */
	public Integer getCourseCapacity() {
		return courseCapacity;
	}
	/**
	 * @param courseCapacity the courseCapacity to set
	 */
	public void setCourseCapacity(Integer courseCapacity) {
		this.courseCapacity = courseCapacity;
	}
	/**
	 * @return the personNumber
	 */
	public Integer getPersonNumber() {
		return personNumber;
	}
	/**
	 * @param personNumber the personNumber to set
	 */
	public void setPersonNumber(Integer personNumber) {
		this.personNumber = personNumber;
	}
	/**
	 * @return the credit
	 */
	public Integer getCredit() {
		return credit;
	}
	/**
	 * @param credit the credit to set
	 */
	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	/**
	 * @return the gradeNeededSet
	 */
	public HashSet<Integer> getGradeNeededSet() {
		return gradeNeededSet;
	}

	/**
	 * @param gradeNeededSet the gradeNeededSet to set
	 */
	public void setGradeNeededSet(HashSet<Integer> gradeNeededSet) {
		this.gradeNeededSet = gradeNeededSet;
	}

	/**
	 * @return the schoolNeededSet
	 */
	public HashSet<String> getSchoolNeededSet() {
		return schoolNeededSet;
	}

	/**
	 * @param schoolNeededSet the schoolNeededSet to set
	 */
	public void setSchoolNeededSet(HashSet<String> schoolNeededSet) {
		this.schoolNeededSet = schoolNeededSet;
	}

	/**
	 * @return the holderSchool
	 */
	public String getHolderSchool() {
		return holderSchool;
	}
	/**
	 * @param holderSchool the holderSchool to set
	 */
	public void setHolderSchool(String holderSchool) {
		this.holderSchool = holderSchool;
	}
	/**
	 * @return the teacherName
	 */
	public String getTeacherName() {
		return teacherName;
	}
	/**
	 * @param teacherName the teacherName to set
	 */
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	/**
	 * override hashCode() method for HashMap and HashSet 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courseCapacity == null) ? 0 : courseCapacity.hashCode());
		result = prime * result + ((courseId == null) ? 0 : courseId.hashCode());
		result = prime * result + ((courseName == null) ? 0 : courseName.hashCode());
		result = prime * result + ((credit == null) ? 0 : credit.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((gradeNeededSet == null) ? 0 : gradeNeededSet.hashCode());
		result = prime * result + ((holderSchool == null) ? 0 : holderSchool.hashCode());
		result = prime * result + ((personNumber == null) ? 0 : personNumber.hashCode());
		result = prime * result + ((place == null) ? 0 : place.hashCode());
		result = prime * result + ((schoolNeededSet == null) ? 0 : schoolNeededSet.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((teacherName == null) ? 0 : teacherName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (courseCapacity == null) {
			if (other.courseCapacity != null)
				return false;
		} else if (!courseCapacity.equals(other.courseCapacity))
			return false;
		if (courseId == null) {
			if (other.courseId != null)
				return false;
		} else if (!courseId.equals(other.courseId))
			return false;
		if (courseName == null) {
			if (other.courseName != null)
				return false;
		} else if (!courseName.equals(other.courseName))
			return false;
		if (credit == null) {
			if (other.credit != null)
				return false;
		} else if (!credit.equals(other.credit))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (gradeNeededSet == null) {
			if (other.gradeNeededSet != null)
				return false;
		} else if (!gradeNeededSet.equals(other.gradeNeededSet))
			return false;
		if (holderSchool == null) {
			if (other.holderSchool != null)
				return false;
		} else if (!holderSchool.equals(other.holderSchool))
			return false;
		if (personNumber == null) {
			if (other.personNumber != null)
				return false;
		} else if (!personNumber.equals(other.personNumber))
			return false;
		if (place == null) {
			if (other.place != null)
				return false;
		} else if (!place.equals(other.place))
			return false;
		if (schoolNeededSet == null) {
			if (other.schoolNeededSet != null)
				return false;
		} else if (!schoolNeededSet.equals(other.schoolNeededSet))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (teacherName == null) {
			if (other.teacherName != null)
				return false;
		} else if (!teacherName.equals(other.teacherName))
			return false;
		return true;
	}

	/**
	 * override toString() method
	 */
	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", startDate=" + startDate + ", endDate="
				+ endDate + ", place=" + place + ", courseCapacity=" + courseCapacity + ", personNumber=" + personNumber
				+ ", credit=" + credit + ", holderSchool=" + holderSchool + ", teacherName=" + teacherName
				+ ", gradeNeededSet=" + gradeNeededSet + ", schoolNeededSet=" + schoolNeededSet + "]";
	}

}
