package courseManageSystem;

import courseManageSystem.controller.CourseManageSystem;

/**
 * The Driver class, which driver the whole system.
 * 
 * @author tanglizi
 *
 */
public class Driver {
	/**
	 * Driver main method, run the whole system.
	 * @param args
	 */
	public static void main(String[] args) {
		CourseManageSystem cms=new CourseManageSystem();
		cms.run();
	}
}
