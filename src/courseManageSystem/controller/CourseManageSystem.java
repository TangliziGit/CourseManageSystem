package courseManageSystem.controller;

/*
 * TODO:
 * all done.
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import courseManageSystem.exception.*;
import courseManageSystem.dao.*;
import courseManageSystem.entity.*;
import courseManageSystem.service.*;


/**
 * The CourseManageSystem class
 * 
 * @author Chunxu Zhang
 *
 */
public class CourseManageSystem {
	private BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));
	private Scanner scanner=new Scanner(System.in);
	private Database database;
	private User user;
	
	private CourseService courseService;
	private UserService userService;
	
	/**
	 * The CourseManageSystem constructor.
	 * 
	 */
	public CourseManageSystem() {
		CourseDatabaseOperateWrapper courseOperator = new CourseDatabaseOperateWrapper(SerializationFileDatabase.getDatabaseSingleton());
		UserDatabaseOperateWrapper userOperator = new UserDatabaseOperateWrapper(SerializationFileDatabase.getDatabaseSingleton());
		this.database=SerializationFileDatabase.getDatabaseSingleton();
		
		try {
			database.initData();
		} catch (FileNotFoundException e){
			System.err.println("Database files not found.\nCreated database files instead.");
		} catch (CreateFileException e) {
			System.err.println("Data files can not be created.");
		} catch (ClassNotFoundException | IOException e) {
			System.err.println("Data can not be loaded.");
			e.printStackTrace();
		} catch (Exception e) {
			;
		}
		
		this.courseService=new CourseService(this.database, courseOperator, null, this.reader);
		this.userService=new UserService(this.database, userOperator, null, this.scanner);
	}

	/**
	 * The main method for running the whole system
	 */
	public void run() {
		while (true) {
			showMenu();
			int choice=getChoice();
			
			if (choice==-1)
				continue;
			else if (choice==0) {
				System.out.println("Have a nice day!\nBye.");
				break;
			} else if (choice==1)
				userService.register();
			else if (choice==2)
				userService.login();
			else if (choice==3)
				userService.logout();
			else if (choice==4)
				userService.updateUser();
			else if (choice==5)
				userService.showUsersInDatabase();
			else if (choice==6)
				userService.showUser();
			else if (choice==7)
				courseService.showCoursesInDatabase();
			else if (choice==8)
				courseService.showCoursesInCourseList();
			else if (choice==9)
				courseService.selectCourse();
			else if (choice==10)
				courseService.dropCourse();
			else if (choice==11)
				courseService.addCourseFromFile();
			else if (choice==12)
				courseService.removeCourse();
			else if (choice==13)
				commitData();
		}
	}
	
	/**
	 * Show the menu of all valid operator
	 * The '*' sign is for permission 
	 */
	public void showMenu() {
		if (this.user!=null)
			System.out.println("Hi, "+this.user.getUserName()+"!");
		System.out.println("Please make your choice.");
		System.out.println("[ 0] quit");
		System.out.println("[ 1] register");
		System.out.println("[ 2] log in");
		System.out.println("[ 3] log out");
		System.out.println("[ 4] update your information");
		System.out.println("[ 5] show users in database");
		System.out.println("[ 6] show your information");
		System.out.println("[ 7] show courses in database");
		System.out.println("[ 8] show your courses");
		System.out.println("[ 9] select a course");
		System.out.println("[10] drop a course");
		System.out.println("*11] add a course into database from a file");
		System.out.println("*12] remove a course from database");
		System.out.println("[13] save all changes");
		System.out.println("your choice> ");
	}
	
	/**
	 * Get the choice, which user made.
	 * @return choice
	 */
	public int getChoice(){
		int choice;
		try {
			choice = Integer.parseInt(reader.readLine());
			if (choice<0 || choice>13)
				throw new Exception();
		} catch (NumberFormatException | IOException e) {
			System.err.println("Error in reading your choice.");
			System.err.println("Please make a chioce again.");
			return -1;
		} catch (Exception e) {
			System.err.println("Error in reading your choice.");
			System.err.println("Please enter a nonnegative number <= 13.");
			return -1;
		}
		
		return choice;
	}
	
	
	/**
	 * Commit all data into database.
	 * If not commit data, system will not save all changes automatically.
	 */
	public void commitData() {
		try {
			database.commitData();
		} catch (IOException e) {
			System.err.println("Commition failed.");
			System.err.println("Please try again or contact managers");
			e.printStackTrace();
		} catch (Exception e) {
			;
		}

		System.err.println("Commited successfully.");
	}
}
