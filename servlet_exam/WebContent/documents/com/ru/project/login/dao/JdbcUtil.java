/**
 * 版本信息：Copyright ru Corporation 2013 
 * 版权所有
 *
 */
package com.ru.project.login.dao;

import org.junit.Test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * 
 * 项目名称：ssh0605
 * 类描述：数据库查询帮助类
 * 创建人：ru	
 * 创建时间：2013-8-22 上午11:47:45
 * 修改人：ru
 * 修改时间：2013-8-22 上午11:47:45
 * 修改备注：
 * @since jdk1.7
 * @version 1.0
 */
public class JdbcUtil{
	
	static String classDriver = "com.mysql.jdbc.Driver";
	static String dbConnection = "jdbc:mysql://localhost:3306/ssh";
	static String userName = "root";
	static String password = "123456";
	
	
	private static Connection connection = null;
	private static PreparedStatement statement = null;
	private static CallableStatement callStatement = null;
	private static ResultSet resultSet = null;
	
	private String callName = "{call count_test(?)}";
	
	@Test
	public void test(){
		//增删改
		//String sql = "insert into stuname(id,name) values(?,?)";
		//String sql = "update stuname set name = ? where id = ?";
		/*String sql = "delete from stuname";
		boolean b  = insertOrUpdateDB(sql);*/
		
		//查询操作
		String sql = "select * from user";
		Object[] obj = this.sqlQueryUniqueObj(sql);
		System.out.println(obj[1]);
		
		//调用存储过程
		//String value = getCallableResult(callName);
		//System.out.println("value = " + value);
	}
	
	/**
	 * (1、查询帮助类,得到一个list<Map<String, Object>>)
	 * @param  
	 * @return 
	 * @throws
	 */
	public static List<Map<String, Object>> queryTest(String sql,String... args){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try {
			//1.加载驱动
			Class.forName(classDriver);
			
			//2.连接数据库
			connection = DriverManager.getConnection(dbConnection,userName,password);
			
			//3.创建表达式
			statement = connection.prepareStatement(sql);
			if(args != null && args.length != 0){
				for(int i = 0; i < args.length; i++){
					statement.setString(i+1, args[i]);
				}
			}

			//4.执行指令
			resultSet = statement.executeQuery();
			
			//5、对resultSet结果进行处理
			//（1）得到resultSet对象中列的类型和属性信息
			ResultSetMetaData rsmd = resultSet.getMetaData();
			//（2）得到一共有多少列
			int columCount = rsmd.getColumnCount();
			while(resultSet.next()){
				Map<String, Object> map = new HashMap<String, Object>();
				for(int i = 0; i < columCount; i++){
					String columName = rsmd.getColumnName(i+1);
					Object value = resultSet.getObject(i + 1);
					map.put(columName, value);
				}
				list.add(map);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			exceptionHandling();
		}
		
		return list;
	}
	
	/**
	 * 
	 * sqlQueryUniqueObj(只有一个对象时，使用这个方法得到Object[])
	 * @param sql
	 * @param args
	 * @return
	 * @return Object[]
	 */
	public static Object[] sqlQueryUniqueObj(String sql,String... args){
		
		Object[] obj = null;
		try {
			getStatement(sql,args);

			//4.执行指令
			resultSet = statement.executeQuery();
			
			//5、对resultSet结果进行处理
			//（1）得到resultSet对象中列的类型和属性信息
			ResultSetMetaData rsmd = resultSet.getMetaData();
			//（2）得到一共有多少列
			int columCount = rsmd.getColumnCount();
			while(resultSet.next()){
				obj = new Object[columCount];
				for(int i = 0; i < columCount; i++){
					Object value = resultSet.getObject(i + 1);
					obj[i] = value;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			exceptionHandling();
		}
		return obj;
	}
	
	
	
	/**
	 * 
	 * (2、更新 插入 删除操作帮助类)
	 * @param  
	 * @return 
	 * @throws
	 */
	public static boolean insertOrUpdateDB(String sql,String... args){
		boolean b = false;
		int i = -1;
		try {
			getStatement(sql,args);
			
			i = statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			exceptionHandling();
		}
		
		System.out.println("i = " + i);
		
		if(i > 0){
			b = true;
		}
		return b;
	} 
	
	/**
	 * 
	 * (3、批量插入)
	 * @param  
	 * @return 
	 * @throws
	 */
	public static boolean insertBatch(String sql,long size,String... args){
		boolean isInsertSucess = false;
		int[] result = null;
		
		try {
			//1.加载驱动
			Class.forName(classDriver);
			
			//2.连接数据库
			connection = DriverManager.getConnection(dbConnection,userName,password);
			connection.setAutoCommit(false);
			//3.创建表达式
			statement = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			for(int m = 0; m < size; m++){
				if(args != null && args.length != 0){
					for(int i = 0; i < args.length; i++){
						statement.setString(i+1, args[i]);
					}
				}
				statement.addBatch();
			}
			
			result = statement.executeBatch();
			
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			exceptionHandling();
		}
		
		if(result !=null && result.length == size){
			isInsertSucess = true;
		}
		
		return isInsertSucess;
	}
	
	/**
	 * 
	 * (4、调用存储过程--查询操作)
	 * @param  
	 * @return 
	 * @throws
	 */
	public static String getCallableResult(String sql, String... args){
		
		String value = null;
		try {
			getStatementForCallable(sql);
			resultSet = callStatement.executeQuery();
			//取值
			while(resultSet.next()){
				value = resultSet.getObject(1).toString();
			}
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			exceptionHandling();
		}
		return value;
	}
	
	//公共创建表达式方法部分
	public static void getStatement(String sql,String... args) throws SQLException, ClassNotFoundException{
		
		
		//1.加载驱动
		Class.forName(classDriver);
		
		//2.连接数据库
		connection = DriverManager.getConnection(dbConnection,userName,password);
		
		//3.创建表达式
		statement = connection.prepareStatement(sql);
		if(args != null && args.length != 0){
			for(int i = 0; i < args.length; i++){
				statement.setString(i+1, args[i]);
			}
		}
	} 
	
	//公共创建表达式方法部分--调用存储过程
		public static void getStatementForCallable(String sql,String... args) throws SQLException, ClassNotFoundException{
			
			
				//1.加载驱动
				Class.forName(classDriver);
				
				//2.连接数据库
				connection = DriverManager.getConnection(dbConnection,userName,password);
				
				//3.创建表达式
				callStatement = connection.prepareCall(sql);
				if(args != null && args.length != 0){
					for(int i = 0; i < args.length; i++){
						callStatement.setString(i+1, args[i]);
					}
				}
		} 
	
	//关闭资源
	public static void exceptionHandling(){
		//关闭资源
		if(resultSet != null){
			try {
				resultSet.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		if(statement !=null){
			try {
				statement.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		if(connection != null){
			try {
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
}
