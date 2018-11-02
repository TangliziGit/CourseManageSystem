package courseManageSystem.entity;

/**
 * The AdminInformation class, which stores information of each admin.
 * 
 * @author Chunxu Zhang
 *
 */
public class AdminInformation extends Information {
	private static final long serialVersionUID = 1L;

	/**
	 * @param realName
	 * @param school
	 * @param phoneNumber
	 */
	public AdminInformation(String realName, String school, String phoneNumber) {
		super(realName, school, phoneNumber);
	}

	/**
	 * override toString() method
	 */
	@Override
	public String toString() {
		return "AdminInformation [" + super.toString() + "]";
	}
}
