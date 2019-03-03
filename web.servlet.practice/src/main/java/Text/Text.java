package Text;

import java.sql.SQLException;

import DAO.UserDAO;
import entity.User;


public class Text {
	public static void main(String[] args) throws SQLException {
//		DBUtils db = new DBUtils();
//		System.out.println(db);
		UserDAO dao = new UserDAO();
		User u = new User();
		
		u.setUsername("简章");
		u.setPassword("1233330505");
		u.setEmail("tedu@163.com");
		dao.Save(u);
		System.out.println("检测完成");
		System.out.print(dao.findall()+"\t");
		System.out.println("\t");
//		dao.Del(u);
//		System.out.println("检测完成");
//		
		
	}

}
