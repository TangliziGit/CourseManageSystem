package courseManageSystem.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import courseManageSystem.entity.*; 

/**
 * The CourseDatabaseOperateWrapper class, which provides user database operations.
 * Using decorator design method to solve static getSingleton method problem.
 * 
 * @author Chunxu Zhang
 *
 */
public class CourseDatabaseOperateWrapper {
	private Database database;

	/**
	 * @param database
	 */
	public CourseDatabaseOperateWrapper(Database database) {
		this.database = database;
	}
	
	/**
	 * Add a course to database. 
	 * 
	 * @param course
	 * @return
	 * 	if there exists, return false
	 * 	else return true
	 */
	public boolean addCourse(Course course) {
		HashMap<Integer, Course> courseMap=database.getCourseMap();
		
		if (courseMap.containsKey(course.getCourseId()))
			return false;
		courseMap.put(course.getCourseId(), course);
		return true;
	}
	
	/**
	 * Update a course of database. 
	 * 
	 * @param course
	 * @return
	 * 	if there does not exist courseId key, return false
	 * 	else return true
	 */
	public boolean updateCourse(Course course) {
		HashMap<Integer, Course> courseMap=database.getCourseMap();
		
		if (courseMap.replace(course.getCourseId(), course)==null)
			return false;
		return true;
	}
	
	/**
	 * Remove a course from database. 
	 * 
	 * @param courseId
	 * @return
	 * 	if there does not exist courseId key, return false
	 * 	else return true
	 */
	public boolean removeCourse(Integer courseId) {
		HashMap<Integer, Course> courseMap=database.getCourseMap();
		
		if (courseMap.remove(courseId)==null)
			return false;
		return true;
	}
	
	/**
	 * Get a course from database by courseId.
	 * Time complexity O(1) 
	 * 
	 * @param courseId
	 * @return
	 * 	if there does not exist courseId key, return null
	 * 	else return course
	 */
	public Course getCourseByCourseId(Integer courseId) {
		HashMap<Integer, Course> courseMap=database.getCourseMap();
		
		return courseMap.get(courseId);
	}
	
	/**
	 * Get a course from database by courseName. 
	 * Time complexity O(n)
	 * 
	 * @param courseName
	 * @return
	 * 	if there does not exist, return null
	 * 	else return course
	 */
	public Course getCourseByCourseName(String courseName) {
		HashMap<Integer, Course> courseMap=database.getCourseMap();
		for (Entry<Integer, Course> entry: courseMap.entrySet()) {
			Course course=entry.getValue();
			if (course.getCourseName().equals(courseName))
				return course;
		}

		return null;
	}
	
	/**
	 * Get a course list from database by teacherName. 
	 * Time complexity O(n)
	 * 
	 * @param teacherName
	 * @return
	 * 	if there does not exist, return null
	 * 	else return a course list
	 */
	public ArrayList<Course> getCourseByTeacherName(String teacherName) {
		HashMap<Integer, Course> courseMap=database.getCourseMap();
		ArrayList<Course> courseList=new ArrayList<Course>();
		
		for (Entry<Integer, Course> entry: courseMap.entrySet()) {
			Course  course=entry.getValue();
			if (course.getTeacherName().equals(teacherName))
				courseList.add(course);
		}

		return courseList;
	}
	
	/**
	 * Get a course list from database by holderSchool. 
	 * Time complexity O(n)
	 * 
	 * @param holderSchool
	 * @return
	 * 	if there does not exist, return null
	 * 	else return a course list
	 */
	public ArrayList<Course> getCourseByHolderSchool(String holderSchool) {
		HashMap<Integer, Course> courseMap=database.getCourseMap();
		ArrayList<Course> courseList=new ArrayList<Course>();
		
		for (Entry<Integer, Course> entry: courseMap.entrySet()) {
			Course  course=entry.getValue();
			if (course.getHolderSchool().equals(holderSchool))
				courseList.add(course);
		}

		return courseList;
	}
}
