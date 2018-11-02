package courseManageSystem.exception;

/**
 * The CountException for reading the courses information.
 * 
 * @author Chunxu Zhang
 *
 */
public class CountException extends Exception{
	private static final long serialVersionUID = 1L;

	/**
	 * The constructor, override from Exception.
	 * @param message
	 */
	public CountException(String message) {
		super(message);
	}
	
	/**
	 * The constructor, which has not arguments.
	 */
	public CountException() {}
}