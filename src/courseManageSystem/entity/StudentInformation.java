package courseManageSystem.entity;

/**
 * The StudentInformation class extends User class, which stores information of each student.
 * 
 * @author Chunxu Zhang
 *
 */
public class StudentInformation extends Information{
	private static final long serialVersionUID = 1L;
	private Integer studentId;
	private Integer grade;
	private String classId;
	private Integer credit;
	/**
	 * @param realName
	 * @param school
	 * @param phoneNumber
	 * @param password
	 * @param studentId
	 * @param grade
	 * @param classId
	 * @param credit
	 */
	public StudentInformation(String realName, String school, String phoneNumber, Integer studentId, Integer grade,
			String classId, Integer credit) {
		super(realName, school, phoneNumber);
		this.studentId = studentId;
		this.grade = grade;
		this.classId = classId;
		this.credit = credit;
	}
	/**
	 * @return the studentId
	 */
	public Integer getStudentId() {
		return studentId;
	}
	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	/**
	 * @return the grade
	 */
	public Integer getGrade() {
		return grade;
	}
	/**
	 * @param grade the grade to set
	 */
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	/**
	 * @return the classId
	 */
	public String getClassId() {
		return classId;
	}
	/**
	 * @param classId the classId to set
	 */
	public void setClassId(String classId) {
		this.classId = classId;
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
	
	@Override
	public String toString() {
		return "StudentInformation [" + super.toString() + ", studentId=" + studentId + ", grade=" + grade + ", classId=" + classId + ", credit="
				+ credit + "]";
	}

}
