package courseManageSystem.dao;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import courseManageSystem.entity.*;
import courseManageSystem.exception.CreateFileException;

/**
 * The Database class, which maintain the whole users and courses.
 * According to singleton instance method
 * 
 * Bacause it is a little hard to configure the environment in teachers PC,
 * 		I implement this database by serialization and file operation.
 * This class can be changed into real database very easily, such as redis or mysql.
 * 
 * Detail: The objects are serialized into String format, and stored into a file.
 * 
 * @author tanglizi
 *
 */
public class SerializationFileDatabase implements Database{
	public static String USER_DATABASE_FILENAME="users.dat";
	public static String COURSE_DATABASE_FILENAME="courses.dat";
	
	private HashMap<Integer, Course> courseMap;
	private HashMap<Integer, User> userMap;
	private static Database singletonInstance;
	
	/**
	 * The constructor of Database
	 * Attend to init member variable.
	 */
	private SerializationFileDatabase() {
		courseMap=new HashMap<Integer, Course>();
		userMap=new HashMap<Integer, User>();
	}
	
	/**
	 * Get a singleton of Database.
	 * @return singleton of Database
	 */
	public static Database getDatabaseSingleton() {
		if (singletonInstance==null)
			singletonInstance=new SerializationFileDatabase();
		return singletonInstance;
	}
	
	/**
	 * the initial method for this Database class.
	 */
	public void initData() throws CreateFileException, FileNotFoundException, ClassNotFoundException, IOException{
		if (!checkDataFileExists()) {
			try{
				createInitialDataFile();	// IOException
			} catch (IOException e) {
				throw new CreateFileException();
			}
			throw new FileNotFoundException();
		}
		
		loadData();						// ClassNotFoundException, IOException 
	}
	
	/**
	 * Check if exist the database files.
	 * @return
	 * 	If exist, return true
	 * 	else return false
	 */
	public boolean checkDataFileExists() {
		File userFile=new File(USER_DATABASE_FILENAME);
		File courseFile=new File(COURSE_DATABASE_FILENAME);
		
		return userFile.exists() && courseFile.exists();
	}
	
	/**
	 * Create initial database files for first running system.
	 * @throws IOException
	 */
	public void createInitialDataFile() throws IOException {
		File userFile=new File(USER_DATABASE_FILENAME);
		File courseFile=new File(COURSE_DATABASE_FILENAME);
		
		if (!userFile.exists())
			userFile.createNewFile();
		if (!courseFile.exists())
			courseFile.createNewFile();
		commitData();
	}
	
	/**
	 * Read all data of courses and users into two file.
	 * The usage of objectInputStream and serializable objects.
	 * 
	 * Attend to first line of code, we need to throw 3 exception.
	 * Attend to second line of code, we need to check cast from Object to XXX.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings("unchecked")
	public void loadData() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream userInputStream=new ObjectInputStream(
				new FileInputStream(USER_DATABASE_FILENAME));
		userMap=(HashMap<Integer, User>)userInputStream.readObject();
		userInputStream.close();
		
		ObjectInputStream courseInputStream=new ObjectInputStream(
				new FileInputStream(COURSE_DATABASE_FILENAME));
		courseMap=(HashMap<Integer, Course>)courseInputStream.readObject();
		courseInputStream.close();
	}
	
	/**
	 * Commit all changes of courses and users into two file.
	 * The usage of objectOutputStream and serializable objects.
	 * 
	 * Attend to first line of code, we need to throw 2 exception.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void commitData() throws FileNotFoundException, IOException {
		ObjectOutputStream userOutputStream=new ObjectOutputStream(
				new FileOutputStream(USER_DATABASE_FILENAME));
		userOutputStream.writeObject(userMap);
		userOutputStream.close();
		
		ObjectOutputStream courseOutputStream=new ObjectOutputStream(
				new FileOutputStream(COURSE_DATABASE_FILENAME));
		courseOutputStream.writeObject(courseMap);
		courseOutputStream.close();
	}

	/**
	 * @return the courseMap
	 */
	public HashMap<Integer, Course> getCourseMap() {
		return courseMap;
	}

	/**
	 * @param courseMap the courseMap to set
	 */
	public void setCourseMap(HashMap<Integer, Course> courseMap) {
		this.courseMap = courseMap;
	}

	/**
	 * @return the userMap
	 */
	public HashMap<Integer, User> getUserMap() {
		return userMap;
	}

	/**
	 * @param userMap the userMap to set
	 */
	public void setUserMap(HashMap<Integer, User> userMap) {
		this.userMap = userMap;
	}
}
