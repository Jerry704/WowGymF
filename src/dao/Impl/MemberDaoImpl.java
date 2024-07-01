package dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DbConnection;
import dao.MemberDao;
import model.Member;

public class MemberDaoImpl implements MemberDao {
	public static void main(String[] args) {
		
//		Member m = new Member("小c","bb01","ccccc","cc01@gmail.com");
		
//		new MemberDaoImpl().add(m);
//		List<Member> l = new MemberDaoImpl().selectAll();
//		for(Member  m: l) {
//			show(m);
//		}
//		List<Member> l = new MemberDaoImpl().selectById(6);
//		Member[] m = l.toArray(new Member[1]); 
//		show(m[0]);
//		m[0].setName("小c");
//		m[0].setPassword("ccccc");
//		m[0].setEmail("cc01@gmail.com");
//		new MemberDaoImpl().update(m[0]);
		
//		List<Member> l = new MemberDaoImpl().selectMember("aaa01", "111");
//		for(Member  m: l) {
//			show(m);
//		}
//		new MemberDaoImpl().delete(6);
	}
	
	public static void show(Member m) {
		System.out.println(
				m.getId() + "\t" + m.getName() + "\t" + m.getUsername() + "\t" + m.getPassword() + "\t" + m.getEmail());
	}

	@Override //ok
	public void add(Member m) {
		Connection conn = DbConnection.getDb();
		String SQL = "insert into `member`(Name, username,password, email) values(?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(SQL);
			ps.setString(1, m.getName());
			ps.setString(2, m.getUsername());
			ps.setString(3, m.getPassword());
			ps.setString(4, m.getEmail());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override //ok
	public List<Member> selectAll() {
		String sql = "select * from `member`";
		List<Member> mlist = new ArrayList();
		Connection conn = DbConnection.getDb();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
			Member m = new Member();
			m.setId(rs.getInt("member_id"));
			m.setName(rs.getString("name"));
			m.setUsername(rs.getString("username"));
			m.setPassword(rs.getString("password"));
			m.setEmail(rs.getString("email"));
			mlist.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mlist;
	}

	@Override //ok
	public List<Member> selectById(int id) {
		String sql = "select * from `member` where member_id=?";
		List<Member> mlist = new ArrayList();
		Connection conn = DbConnection.getDb();
		Member m = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
			m = new Member();
			m.setId(rs.getInt("member_id"));
			m.setName(rs.getString("name"));
			m.setUsername(rs.getString("username"));
			m.setPassword(rs.getString("password"));
			m.setEmail(rs.getString("email"));
			mlist.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mlist;
	}

	@Override //ok
	public List<Member> selectMember(String username, String password) {
		String sql = "select * from `member` where username=? and password=?";
		List<Member> mlist = new ArrayList();
		Connection conn = DbConnection.getDb();
		Member m = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
			m = new Member();
			m.setId(rs.getInt("member_id"));
			m.setName(rs.getString("name"));
			m.setUsername(rs.getString("username"));
			m.setPassword(rs.getString("password"));
			m.setEmail(rs.getString("email"));
			mlist.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mlist;
	}

	@Override //ok
	public void update(Member m) {
		Connection conn = DbConnection.getDb();
		String sql = "update `member` set name=?, password=?, email=? where member_id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, m.getName());
			ps.setString(2, m.getPassword());
			ps.setString(3, m.getEmail());
			ps.setInt(4, m.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override //ok
	public void delete(int id) {
		Connection conn = DbConnection.getDb();
		String sql = "delete from `member` where member_id=? ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	public Member queryUsername(String username) {
		Connection conn = DbConnection.getDb();
		String SQL = "select * from `member` where username=?";
		Member m = null;
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				m = new Member();
				m.setId(rs.getInt("id"));
				m.setName(rs.getString("name"));
				m.setUsername(rs.getString("username"));
				m.setPassword(rs.getString("password"));
				m.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}

}
