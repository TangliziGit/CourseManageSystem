package courseManageSystem.service;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

import courseManageSystem.entity.*;
import courseManageSystem.dao.*;

/**
 * The UserService class, which provides lots of service method about users.
 * 
 * @author Chunxu Zhang
 *
 */
public class UserService {
	private Scanner scanner;
	private UserDatabaseOperateWrapper userOperator;
	private Database database;
	private User user;
	
	/**
	 * The constructor.
	 * 
	 * @param database
	 * @param userOperator
	 * @param user
	 * @param scanner
	 */
	public UserService(Database database, UserDatabaseOperateWrapper userOperator, User user, Scanner scanner) {
		this.userOperator=userOperator;
		this.database=database;
		this.user=user;
		this.scanner=scanner;
	}
	
	/**
	 * Register a new user.
	 */
	public void register() {
		// need to split.
		System.out.println("Enter information below:");
		
		System.out.println("Are you a student?or an admin?\n(yes for student, no for admin):");
		String ifStudent=scanner.nextLine();
		
		Integer userId=database.getUserMap().size();
		System.out.println("Your username(not real name):");
		String userName=scanner.nextLine();
		
		System.out.println("Your password:");
		String password=scanner.nextLine();

		System.out.println("Your real name:");
		String realName=scanner.nextLine();
		
		System.out.println("Your school:");
		String school=scanner.nextLine();
		
		System.out.println("Your phoneNumber:");
		String phoneNumber=scanner.nextLine();
		
		User user=null;
		if (ifStudent.equals("yes") || ifStudent.equals("Yes")) {
			System.out.println("Your student ID:");
			Integer studentId=Integer.parseInt(scanner.nextLine());
			
			System.out.println("Your grade(such as 1, 2, 3):");
			Integer grade=Integer.parseInt(scanner.nextLine());
			
			System.out.println("Your class ID:");
			String classId=scanner.nextLine();
			
			StudentInformation information=new StudentInformation(realName, school, phoneNumber, studentId, grade, classId, 0);
			user=new Student(userId, userName, password, information);
		}else if (ifStudent.equals("no") || ifStudent.equals("No")){
			AdminInformation information=new AdminInformation(realName, school, phoneNumber);
			user=new Admin(userId, userName, password, information);
		}else {
			System.err.println("Please enter a correct chioce.");
			return;
		}
		
		if (userOperator.addUser(user)) {
			System.err.println("Register successfully.");
		}else {
			System.err.println("Register unsuccessfully.");
		}
	}
	
	/**
	 * The log in method.
	 */
	public void login() {
		System.out.println("Your userId:");
		Integer userId=Integer.parseInt(scanner.nextLine());
		System.out.println("Your password:");
		String password=scanner.nextLine();
		
		User user=userOperator.getUserByUserId(userId);
		
		if (user==null) {
			System.err.println("Not exsit this user.");
			return;
		}else if (!user.getPassword().equals(password)) {
			System.err.println("Password wrong.");
		}
		
		setUser(user);
		System.err.println("Log in successfully.");
	}
	
	/**
	 * The log out method.
	 */
	public void logout() {
		if (this.user==null) {
			System.err.println("You are not logged in.");
			return;
		}
		this.user=null;
		System.out.println("Log out successfully.");
	}
	
	/**
	 * Update a existed user.
	 */
	public void updateUser() {
		if (this.user==null) {
			System.err.println("You are not logged in.");
			return;
		}
		System.out.println("Enter information below:");
		
		Integer userId=this.user.getUserId();
		System.out.println("Your username(not real name):");
		String userName=scanner.nextLine();
		
		System.out.println("Your password:");
		String password=scanner.nextLine();

		System.out.println("Your real name:");
		String realName=scanner.nextLine();
		
		System.out.println("Your school:");
		String school=scanner.nextLine();
		
		System.out.println("Your phoneNumber:");
		String phoneNumber=scanner.nextLine();
		
		User user=null;
		if (this.user instanceof Student) {
			System.out.println("Your student ID:");
			Integer studentId=Integer.parseInt(scanner.nextLine());
			
			System.out.println("Your grade(such as 1, 2, 3):");
			Integer grade=Integer.parseInt(scanner.nextLine());
			
			System.out.println("Your class ID:");
			String classId=scanner.nextLine();
			
			StudentInformation information=new StudentInformation(realName, school, phoneNumber, studentId, grade, classId, ((Student)this.user).getInformation().getCredit());
			user=new Student(userId, userName, password, information);
		}else {
			AdminInformation information=new AdminInformation(realName, school, phoneNumber);
			user=new Admin(userId, userName, password, information);
		}
		
		if (userOperator.updateUser(user)) {
			System.err.println("Update successfully.");
			setUser(userOperator.getUserByUserId(this.user.getUserId()));
		}else {
			System.err.println("Update unsuccessfully.");
		}
	}
	
	/**
	 * Show all users in the database
	 */
	public void showUsersInDatabase() {
		HashMap<Integer, User> userMap=database.getUserMap();
		
		for (Entry<Integer, User> entry: userMap.entrySet()) {
			System.out.println(entry.getValue().toString());
		}
		
		if (userMap.isEmpty())
			System.err.println("No user in database.");
	}
	
	/**
	 * Show user details of this user.
	 * Need log in.
	 */
	public void showUser() {
		if (alertIfNotExistsUser())
			return;
		
		System.out.println(this.user.toString());
	}
	
	/**
	 * Set user for this user(logged in).
	 * @param user
	 */
	public void setUser(User user) {
		this.user=user;
	}
	
	/**
	 * Alert if not exist user, which logged in.
	 * @return boolean
	 */
	private boolean alertIfNotExistsUser() {
		if (this.user==null) {
			System.err.println("Please log in firstly.");
			return true;
		}
		return false;
	}
	
	/**
	 * check if this user is a student.
	 * @return
	 */
	public boolean isStudent() {
		return this.user instanceof Student;
	}
	
	/**
	 * check if this user is an administer.
	 * @return
	 */
	public boolean isAdmin() {
		return this.user instanceof Admin;
	}

	/**
	 * @return the userOperator
	 */
	public UserDatabaseOperateWrapper getUserOperator() {
		return userOperator;
	}

	/**
	 * @param userOperator the userOperator to set
	 */
	public void setUserOperator(UserDatabaseOperateWrapper userOperator) {
		this.userOperator = userOperator;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
}
