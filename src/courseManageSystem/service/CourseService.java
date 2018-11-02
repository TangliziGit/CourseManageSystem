package courseManageSystem.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.Map.Entry;

import courseManageSystem.dao.*;
import courseManageSystem.entity.*;
import courseManageSystem.exception.CountException;

/**
 * The CourseService class, which provides lots of service method about courses.
 * 
 * @author Chunxu Zhang
 *
 */
public class CourseService {
	private BufferedReader reader;
	private CourseDatabaseOperateWrapper courseOperator;
	private Database database;
	private User user;
	
	/**
	 * The constructor.
	 * 
	 * @param database
	 * @param courseOperator
	 * @param user
	 * @param reader
	 */
	public CourseService(Database database, CourseDatabaseOperateWrapper courseOperator, User user, BufferedReader reader) {
		this.database=database;
		this.user=user;
		this.reader=reader;
	}
	
	/**
	 * Show all courses in the database
	 */
	public void showCoursesInDatabase() {
		HashMap<Integer, Course> courseMap=database.getCourseMap();
		
		for (Entry<Integer, Course> entry: courseMap.entrySet()) {
			System.out.println(entry.getValue().toString());
		}
		
		if (courseMap.isEmpty())
			System.err.println("No course in database.");
	}
	
	/**
	 * Show all courses from user courses list.
	 */
	public void showCoursesInCourseList() {
		if (alertIfNotExistsUser() || alertIfNotAdmin())
			return;
		
		for (Course course: ((Student)user).getCourseList()) {
			System.out.println(course.toString());
		}
	}
	
	/**
	 * Select course for user.
	 */
	public void selectCourse() {
		if (alertIfNotExistsUser() || alertIfNotStudent())
			return;
		
		Integer courseId;
		System.out.println("Enter the courseId > ");
		try {
			courseId = Integer.parseInt(reader.readLine());
		} catch (NumberFormatException | IOException e) {
			System.err.println("Unvaild input.");
			return;
		}
		
		Student me=(Student)this.user;
		Course course=courseOperator.getCourseByCourseId(courseId);
		if (course==null) {
			System.err.println("Error in selecting course.");
			System.err.println("There does not exsits this course.");
			return;
		}
		
		if (!course.getGradeNeededSet().contains(me.getInformation().getGrade())) {
			System.err.println("Error in selecting course.");
			System.err.println("Your grade need be"+course.getGradeNeededSet().toString());
			return;
		}
		
		if (!course.getSchoolNeededSet().contains(me.getInformation().getSchool())) {
			System.err.println("Error in selecting course.");
			System.err.println("Your school should be "+course.getSchoolNeededSet().toString());
			return;
		}
		
		if (course.getPersonNumber() >= course.getCourseCapacity()) {
			System.err.println("Error in selecting course.");
			System.err.println("This course has been already full.");
			return;
		}
		
		// check if confliction
		Date thisStartTime=course.getStartDate();
		Date thisEndTime=course.getEndDate();
		for (Course myCourse: me.getCourseList()) {
			Date myStartTime=myCourse.getStartDate();
			Date myEndTime=myCourse.getEndDate();
			if ((myStartTime.before(thisStartTime) && thisStartTime.before(myEndTime)) ||
				(myStartTime.before(thisEndTime)   && thisStartTime.before(myEndTime))){
				System.err.println("Error in selecting course.");
				System.err.println("This course has time confliction with" + myCourse.toString());
				return;
			}
		}
		
		me.getCourseList().add(course);
		me.getInformation().setCredit(me.getInformation().getCredit()+course.getCredit());
		course.setPersonNumber(course.getPersonNumber()+1);
		System.err.println("Select a course successfully.");
	}
	
	/**
	 * Drop a course for this user.
	 * Need log in.
	 */
	public void dropCourse() {
		if (alertIfNotExistsUser() || alertIfNotStudent())
			return;
		
		Integer courseId;
		System.out.println("Enter the courseId > ");
		try {
			courseId = Integer.parseInt(reader.readLine());
		} catch (NumberFormatException | IOException e) {
			System.err.println("Unvaild input.");
			return;
		}
		
		ArrayList<Course> courseList=((Student)this.user).getCourseList();
		Course tmpCourse=courseOperator.getCourseByCourseId(courseId);
		if (courseList.removeIf(course -> course.getCourseId().equals(courseId))) {
			Student me=((Student)this.user);
			me.getInformation().setCredit(me.getInformation().getCredit()-tmpCourse.getCredit());
			System.out.println("Drop the course successfully.");
		} else
			System.err.println("There does not exsits this course.");
	}
	
	/**
	 * Add new courses from a file into database.
	 * The formation is:
	 * 		courseId_courseName_yyyy-MM-dd_yyyy-MM-dd_place_capacity_personNumber_allowedGrade_allowedSchool
	 * 		...
	 * 
	 */
	@SuppressWarnings("resource")
	public void addCourseFromFile() {
		if (alertIfNotExistsUser() || alertIfNotAdmin())
			return;
		
		System.out.println("Please enter the course file > ");
		BufferedReader fileReader=null;
		try {
			String filename=this.reader.readLine();
			fileReader=new BufferedReader(new FileReader(filename));
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			
			String line=fileReader.readLine();
			while (line!=null) {
				StringTokenizer tokenizor=new StringTokenizer(line, "_");
				
				if (tokenizor.countTokens()!=12)
					throw new CountException("count not right?");
				
				Integer courseId=Integer.parseInt(tokenizor.nextToken());
				String courseName=tokenizor.nextToken();
				Date startDate=sdf.parse(tokenizor.nextToken());
				Date endDate=sdf.parse(tokenizor.nextToken());
				String place=tokenizor.nextToken();
				Integer courseCapacity=Integer.parseInt(tokenizor.nextToken());
				Integer personNumber=Integer.parseInt(tokenizor.nextToken());
				Integer credit=Integer.parseInt(tokenizor.nextToken());
				String holderSchool=tokenizor.nextToken();
				String teacherName=tokenizor.nextToken();
				
				HashSet<Integer> gradeNeededSet=new HashSet<Integer>();
				HashSet<String> schoolNeededSet=new HashSet<String>();
				
				StringTokenizer gradeTokenizor=new StringTokenizer(tokenizor.nextToken(), ",");
				while (gradeTokenizor.hasMoreTokens())
					gradeNeededSet.add(Integer.parseInt(gradeTokenizor.nextToken()));
				
				StringTokenizer schoolTokenizor=new StringTokenizer(tokenizor.nextToken(), ",");
				while (schoolTokenizor.hasMoreTokens())
					schoolNeededSet.add(schoolTokenizor.nextToken());
				
				courseOperator.addCourse(new Course(courseId, courseName,
						startDate, endDate, place, courseCapacity, personNumber, credit, holderSchool, teacherName,
						gradeNeededSet, schoolNeededSet));
			
				line=fileReader.readLine();
			}
		} catch (FileNotFoundException e) { 
			System.err.println("File not found, please enter a correct filename.");
		} catch (CountException e) {
			System.err.println("Count of input items in file is not right.");
			System.err.println("Please check the file, or contact managers");
		} catch (ParseException e) {
			System.err.println("Input can not be parsed rightly.");
			System.err.println("Please check the file, or contact managers");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Remove a course from database.
	 * Need permission.
	 */
	public void removeCourse() {
		if (alertIfNotExistsUser() || alertIfNotAdmin())
			return;
		
		Integer courseId;
		System.out.println("Enter the courseId > ");
		try {
			courseId = Integer.parseInt(reader.readLine());
		} catch (NumberFormatException | IOException e) {
			System.err.println("Unvaild input.");
			return;
		}
		
		if (courseOperator.removeCourse(courseId))
			System.out.println("Remove the course successfully.");
		else
			System.err.println("There does not exsits this course.");
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
	 * Alert if this user is not a student.
	 * @return boolean
	 */
	private boolean alertIfNotStudent() {
		if (!isStudent() || this.user==null) {
			System.err.println("Your are not a student.");
			return true;
		}
		return false;
	}
	
	/**
	 * Alert if this user is not an administer.
	 * @return boolean
	 */
	private boolean alertIfNotAdmin() {
		if (!isAdmin() || this.user==null) {
			System.err.println("Your are not an administer.");
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
	 * @return the courseOperator
	 */
	public CourseDatabaseOperateWrapper getCourseOperator() {
		return courseOperator;
	}

	/**
	 * @param courseOperator the courseOperator to set
	 */
	public void setCourseOperator(CourseDatabaseOperateWrapper courseOperator) {
		this.courseOperator = courseOperator;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
}
