package courseManageSystem.entity;

import java.io.Serializable;

/**
 * The abstract class stores user data for each user, need be extended.
 * 
 * @author Chunxu Zhang
 *
 */
public abstract class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String userName;
	private String password;
	private Information information;
	
	/**
	 * @param userId
	 * @param userName
	 * @param password
	 * @param information
	 */
	public User(Integer userId, String userName, String password, Information information) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.information=information;
	}

	/**
	 * Get password.
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set password
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the information
	 */
	public Information getInformation() {
		return information;
	}

	/**
	 * @param information the information to set
	 */
	public void setInformation(Information information) {
		this.information = information;
	}

	
	/**
	 * Override hashCode method for HashMap and HashSet.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((information == null) ? 0 : information.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		if (information == null) {
			if (other.information != null) {
				return false;
			}
		} else if (!information.equals(other.information)) {
			return false;
		}
		if (password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!password.equals(other.password)) {
			return false;
		}
		if (userId == null) {
			if (other.userId != null) {
				return false;
			}
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (userName == null) {
			if (other.userName != null) {
				return false;
			}
		} else if (!userName.equals(other.userName)) {
			return false;
		}
		return true;
	}

	/**
	 * Override toString method.
	 */
	@Override
	public String toString() {
		return "userId=" + userId + ", userName=" + userName + ", information=" + information;
	}
}
