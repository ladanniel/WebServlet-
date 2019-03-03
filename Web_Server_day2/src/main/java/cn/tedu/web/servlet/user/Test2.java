package cn.tedu.web.servlet.user;
import java.sql.SQLException;
import utils.DBUtils;
public class Test2 {
	public static void main(String[] args) throws SQLException {
		System.out.println(DBUtils.getConn());
	}

} 
