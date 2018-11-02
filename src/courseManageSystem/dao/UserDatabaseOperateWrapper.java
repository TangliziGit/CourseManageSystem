package courseManageSystem.dao;

import java.util.HashMap;
import java.util.Map.Entry;

import courseManageSystem.entity.*;

/**
 * The UserDatabaseOperateWrapper class, which provides user database operations.
 * Using decorator design method to solve static getSingleton method problem.
 * 
 * @author Chunxu Zhang
 *
 */
public class UserDatabaseOperateWrapper {
	private Database database;
	
	/**
	 * The constructor of UserDatabaseOperator.
	 */
	public UserDatabaseOperateWrapper(Database database) {
		this.database=database;
	}
	
	/**
	 * Add a user to user database. 
	 * 
	 * @param user
	 * @return
	 * 	if there exists userId key, return false
	 * 	else return true
	 */
	public boolean addUser(User user) {
		HashMap<Integer, User> userMap=database.getUserMap();
		
		if (userMap.containsKey(user.getUserId()))
			return false;
		userMap.put(user.getUserId(), user);
		return true;
	}
	
	/**
	 * Update a user of database. 
	 * 
	 * @param user
	 * @return
	 * 	if there does not exist userId key, return false
	 * 	else return true
	 */
	public boolean updateUser(User user) {
		HashMap<Integer, User> userMap=database.getUserMap();
		
		if (userMap.replace(user.getUserId(), user)==null)
			return false;
		return true;
	}
	
	/**
	 * Remove a user from database. 
	 * 
	 * @param userId
	 * @return
	 * 	if there does not exist userId key, return false
	 * 	else return true
	 */
	public boolean removeUser(Integer userId) {
		HashMap<Integer, User> userMap=database.getUserMap();
		
		if (userMap.remove(userId)==null)
			return false;
		return true;
	}
	
	/**
	 * Get a user from database by userId.
	 * Time complexity O(1) 
	 * 
	 * @param userId
	 * @return
	 * 	if there does not exist userId key, return null
	 * 	else return user
	 */
	public User getUserByUserId(Integer userId) {
		HashMap<Integer, User> userMap=database.getUserMap();
		
		return userMap.get(userId);
	}
	
	/**
	 * Get a user from database by studentId.
	 * Time complexity O(n) 
	 * 
	 * @param studentId
	 * @return
	 * 	if there does not exist, return null
	 * 	else return user
	 */
	public User getUserByStudentId(Integer studentId) {
		HashMap<Integer, User> userMap=database.getUserMap();
		
		for (Entry<Integer, User> entry: userMap.entrySet()) {
			User user=entry.getValue();
			Information information=user.getInformation();
			if (information instanceof StudentInformation) {
				if (((StudentInformation) information).getStudentId().equals(studentId))
					return user;
			}
		}
		
		return null;
	}
	
	/**
	 * Get a user from database by userName. 
	 * Time complexity O(n)
	 * 
	 * @param userId
	 * @return
	 * 	if there does not exist, return null
	 * 	else return user
	 */
	public User getUserByUserName(String userName) {
		HashMap<Integer, User> userMap=database.getUserMap();
		for (Entry<Integer, User> entry: userMap.entrySet()) {
			User user=entry.getValue();
			if (user.getUserName().equals(userName))
				return user;
		}

		return null;
	}
	
	/**
	 * Get a user from database by realName. 
	 * Time complexity O(n)
	 * 
	 * @param userId
	 * @return
	 * 	if there does not exist, return null
	 * 	else return user
	 */
	public User getUserByRealName(String realName) {
		HashMap<Integer, User> userMap=database.getUserMap();
		for (Entry<Integer, User> entry: userMap.entrySet()) {
			User user=entry.getValue();
			if (user.getInformation().getRealName().equals(realName))
				return user;
		}

		return null;
	}
	
}
