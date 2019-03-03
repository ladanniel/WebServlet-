package DAO;
//数据访问逻辑封装
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.User;
import utils.DBUtils;
//DAO类：负责封装数据访问逻辑（访问数据库）
public class UserDAO {
	/**
	 * 插入用户信息到数据库（保存）
	 * @throws SQLException 
	 */
	@SuppressWarnings("null")
	public void Save(User users) throws SQLException{
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs =null;
		try {
			conn=DBUtils.getConn();
			String sql = "insert into t_user values(null,?,?,?)";
			stat = conn.prepareStatement(sql);
			stat.setString(1, users.getUsername());
			stat.setString(2, users.getPassword());
			stat.setString(3, users.getEmail());
			stat.executeUpdate();
		} catch (SQLException e) {
		
			e.printStackTrace();
			throw e;
		}finally{
			DBUtils.close(rs, stat, conn);
			
		}
		
		
	}
	//删除用户列表
	public void Del(User use) throws Exception{
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs =null;
		try {
			conn=DBUtils.getConn();
			String sql = "delete from t_user where id=?";
			stat = conn.prepareStatement(sql);
			stat.setInt(1, use.getId());
			stat.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally {
			DBUtils.close(rs, stat, conn);
		}
		
	}
	
	/**
	 * 用于所有用户查询出来，每一个用户的信息，存放到对应一个user对象，然后返回一个由这些user对象组成的一个集合
	 * @return
	 * @throws SQLException 
	 */
	public List<User> findall() throws SQLException{
		List<User> users = new ArrayList<User>();
		
		   Connection conn = null;
		   PreparedStatement ps =null;
		   ResultSet rs = null;
		   
		   try {
			conn=DBUtils.getConn();
			String sql = "select *from t_user";
			ps=conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				//读取记录
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String email = rs.getString("email");
				//把数据放入User里
				User user = new User();
				user.setId(id);
				user.setUsername(username);
				user.setPassword(password);
				user.setEmail(email);
				//把数据放入User集合里
			    users.add(user);
				
			}
			
		
			
		} catch (SQLException e) {
			/**分层设计思想：
			 * 所以将异常抛给servlet
			 */
			e.printStackTrace();
			throw e;
			
		}
		
		return users;
		
		
	}
	/**验证登录查询
	 * @throws SQLException 
	 * 依据用户名查询指定用户的信息，如果找不到，则返回null；
	 */
	
	public User find(String uname) throws SQLException{
		User user=null;
		Connection conn = null;
	    PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConn();
			String sql = "select *from t_user where username=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, uname);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(uname);
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
			}
			
		} catch (SQLException e) {
	
			e.printStackTrace();
			throw e;
		}finally {
			DBUtils.close(rs, ps, conn);
		}
		return user;
	}
	


}
