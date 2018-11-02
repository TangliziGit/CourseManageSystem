package courseManageSystem.entity;

import java.io.Serializable;

/**
 * The abstract class stores information for each user, need be extended.
 * 
 * @author Chunxu Zhang
 *
 */
public abstract class Information implements Serializable{
	private static final long serialVersionUID = 1L;
	private String realName;
	private String school;
	private String phoneNumber;
	
	/**
	 * @param realName
	 * @param school
	 * @param phoneNumber
	 */
	public Information(String realName, String school, String phoneNumber) {
		this.realName = realName;
		this.school = school;
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the realName
	 */
	public String getRealName() {
		return realName;
	}
	
	/**
	 * @param realName the realName to set
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	/**
	 * @return the school
	 */
	public String getSchool() {
		return school;
	}
	
	/**
	 * @param school the school to set
	 */
	public void setSchool(String school) {
		this.school = school;
	}
	
	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Override hashCode method for HashMap and HashSet.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((realName == null) ? 0 : realName.hashCode());
		result = prime * result + ((school == null) ? 0 : school.hashCode());
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
		Information other = (Information) obj;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (realName == null) {
			if (other.realName != null)
				return false;
		} else if (!realName.equals(other.realName))
			return false;
		if (school == null) {
			if (other.school != null)
				return false;
		} else if (!school.equals(other.school))
			return false;
		return true;
	}

	/**
	 * Override toString method.
	 */
	@Override
	public String toString() {
		return "realName=" + realName + ", school=" + school + ", phoneNumber=" + phoneNumber;
	}
}
