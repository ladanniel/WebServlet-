package utils;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
//封装方法
public class DBUtils{
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	private static BasicDataSource dataSource;
	static{
		/**读取文件信息
		 */
		Properties prop = new Properties();
		InputStream ips = DBUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
		
		try {
			prop.load(ips);
			 driver = prop.getProperty("driver");
			 System.out.println(driver);
			 url = prop.getProperty("url");
			 username = prop.getProperty("username");
			 password = prop.getProperty("password");
			String initSize = prop.getProperty("initSize");
			String maxSize  = prop.getProperty("maxSize"); 
			/** 创建连接池及配置信息
			 */
		    dataSource = new BasicDataSource();
			dataSource.setDriverClassName(driver);
			dataSource.setUrl(url);
			dataSource.setUsername(username);
			dataSource.setPassword(password);
			dataSource.setInitialSize(Integer.parseInt(initSize));
			dataSource.setMaxActive(Integer.parseInt(maxSize));
			
			
			
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
				
	}
	public static Connection getConn() throws SQLException{
		return  dataSource.getConnection();
	}
	public static void close(ResultSet rs,Statement stat,Connection conn){
		try {
			if (rs!=null) {
				rs.close();
			}

			if (stat!=null) {
				stat.close();
			}

			if (conn!=null) {
				conn.close();
			}


		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}

