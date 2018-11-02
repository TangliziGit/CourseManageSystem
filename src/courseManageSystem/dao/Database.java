package courseManageSystem.dao;

import java.util.HashMap;

import courseManageSystem.entity.*;

/**
 * The Database interface for all database connector.
 * Attention:
 * 		static Database getDatabaseSingleton() method should be implement.
 * Static methods can not be writed in an interface.
 * 
 * @author Chunxu Zhang
 *
 */
public interface Database {
	// **public static Database getDatabaseSingleton();**
	void initData() throws Exception;
	void commitData() throws Exception;
	
	public HashMap<Integer, Course> getCourseMap();
	public void setCourseMap(HashMap<Integer, Course> courseMap);
	public HashMap<Integer, User> getUserMap();
	public void setUserMap(HashMap<Integer, User> userMap);
}
