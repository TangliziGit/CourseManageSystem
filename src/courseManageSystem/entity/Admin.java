package courseManageSystem.entity;

/**
 * The Admin class extends User class, which stores admin information simply.
 * 
 * @author Chunxu Zhang
 *
 */
public class Admin extends User{
	private static final long serialVersionUID = 1L;

	/**
	 * The Admin constructor.
	 * 
	 * @param userId
	 * @param userName
	 * @param password
	 * @param information
	 */
	public Admin(Integer userId, String userName, String password, AdminInformation information) {
		super(userId, userName, password, information);
	}

	/**
	 * @return the information
	 */
	public AdminInformation getInformation() {
		return (AdminInformation) super.getInformation();
	}
	
	/**
	 * @param information
	 */
	public void setInformation(AdminInformation information) {
		super.setInformation(information);
	}
	
	/**
	 * override toString() method
	 */
	@Override
	public String toString() {
		return "Admin [" + super.toString() + "]";
	}
}
