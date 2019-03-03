package Test;
//≤‚ ‘¿‡
import java.sql.SQLException;


import DAO.UserDAO;
import entity.User;

public class TestDao {
	public static void main(String[] args) throws SQLException {
//		UserDAO dao = new UserDAO();
//		dao.find("≥˛««");
		UserDAO dao = new UserDAO();
		User user = new User();
		user.setUsername("Giving");
		user.setPassword("Test");
		user.setEmail("123@163.com");
		dao.Save(user);
		
	}

}
