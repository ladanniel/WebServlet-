package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.User;
import utils.DBUtils;

public class UserDAO {
	//插入数据
	@SuppressWarnings("null")
	public void Save(User user) throws SQLException{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DBUtils.getConn();
			String sql = "insert into t_user values(null,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			DBUtils.close(rs, ps, conn);
		}
		
	}
    //删除数据
	public void Del(User user) throws SQLException{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DBUtils.getConn();
			String sql = "delete from t_user where id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, user.getId());
		    ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
			
		}finally {
			DBUtils.close(rs, ps, conn);
		}
		
	}
	//查询数据
	public List<User> findall() throws SQLException{
		List<User> users = new ArrayList<User>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConn();
			String sql = "select *from t_user";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String email = rs.getString("email");
				User user = new User();
				user.setId(id);
				user.setUsername(username);
				user.setPassword(password);
				user.setEmail(email);
				users.add(user);	
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return users;
		
	}
	//登录验证
	public  User find(String uname) throws SQLException{
		User user=null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DBUtils.getConn();
			String sql = "select *from t_user where username=?";
			ps= conn.prepareStatement(sql);
			ps.setString(1, uname);
			rs = ps.executeQuery();
			while(rs.next()){
			     user = new User();
				 user.setId(rs.getInt("id"));
				 user.setUsername(uname);
				 user.setPassword(rs.getString("password"));
				 user.setEmail(rs.getString("email"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			DBUtils.close(rs, ps, conn);
		}
		
		return user;
		
	}
	
	
	

}
