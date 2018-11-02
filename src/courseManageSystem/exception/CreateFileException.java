package courseManageSystem.exception;

/**
 * The CreateFileException for reading the courses information.
 * 
 * @author Chunxu Zhang
 *
 */
public class CreateFileException extends Exception{
	private static final long serialVersionUID = 1L;

	/**
	 * The constructor, override from Exception.
	 * @param message
	 */
	public CreateFileException(String message) {
		super(message);
	}
	
	/**
	 * The constructor, which has not arguments.
	 */
	public CreateFileException() {}
}
