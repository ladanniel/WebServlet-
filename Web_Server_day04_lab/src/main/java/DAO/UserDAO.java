package DAO;
//���ݷ����߼���װ
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.User;
import utils.DBUtils;
//DAO�ࣺ�����װ���ݷ����߼����������ݿ⣩
public class UserDAO {
	/**
	 * �����û���Ϣ�����ݿ⣨���棩
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
	//ɾ���û��б�
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
	 * ���������û���ѯ������ÿһ���û�����Ϣ����ŵ���Ӧһ��user����Ȼ�󷵻�һ������Щuser������ɵ�һ������
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
				//��ȡ��¼
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String email = rs.getString("email");
				//�����ݷ���User��
				User user = new User();
				user.setId(id);
				user.setUsername(username);
				user.setPassword(password);
				user.setEmail(email);
				//�����ݷ���User������
			    users.add(user);
				
			}
			
		
			
		} catch (SQLException e) {
			/**�ֲ����˼�룺
			 * ���Խ��쳣�׸�servlet
			 */
			e.printStackTrace();
			throw e;
			
		}
		
		return users;
		
		
	}
	/**��֤��¼��ѯ
	 * @throws SQLException 
	 * �����û�����ѯָ���û�����Ϣ������Ҳ������򷵻�null��
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
