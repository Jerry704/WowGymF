package dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import dao.AdminDao;

import dao.DbConnection;
import model.Admin;
import model.Trainer;

public class AdminDaoImpl implements AdminDao {

	public static void main(String[] args) {
//		Admin a = new Admin("阿明","ming02","xxxxx","ming22@gmail.com");
//		a.setId(4);
//		new AdminDaoImpl().add(a);
//		List<Admin> alist = new AdminDaoImpl().selectAll();
//		List<Admin> alist = new AdminDaoImpl().selectById(1);
//		List<Admin> alist = new AdminDaoImpl().selectAdmin("admin","admin");
//		List<Admin> alist = new AdminDaoImpl().selectById(4);
//		for(Admin b : alist) {
//			show(b);
//	}
//		new AdminDaoImpl().update(a);
//		List<Admin> alist2 = new AdminDaoImpl().selectById(4);
//		for(Admin b : alist2) {
//				show(b);
//		}
		new AdminDaoImpl().delete(4);
		

	}

	public static void show(Admin a) {
		System.out.println(
				a.getId() + "\t" + a.getName() + "\t" + a.getUsername() + "\t" + a.getPassword() + "\t" + a.getEmail());
	}

	@Override // ok
	public void add(Admin a) {
		Connection conn = DbConnection.getDb();
		String SQL = "insert into `admin`(Name, username,password, email) values(?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(SQL);
			ps.setString(1, a.getName());
			ps.setString(2, a.getUsername());
			ps.setString(3, a.getPassword());
			ps.setString(4, a.getEmail());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override // ok
	public List<Admin> selectAll() {
		Connection conn = DbConnection.getDb();
		String sql = "select * from `admin`";
		List<Admin> alist = new ArrayList();

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Admin a = new Admin();
				a.setId(rs.getInt("admin_id"));
				a.setName(rs.getString("name"));
				a.setUsername(rs.getString("username"));
				a.setPassword(rs.getString("password"));
				a.setEmail(rs.getString("email"));
				alist.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alist;
	}

	@Override //ok
	public List<Admin> selectById(int id) {
		Connection conn = DbConnection.getDb();
		String sql = "select * from `admin` where admin_id=?";
		List<Admin> alist = new ArrayList();
		Admin a = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				a = new Admin();
				a.setId(rs.getInt("admin_id"));
				a.setName(rs.getString("name"));
				a.setUsername(rs.getString("username"));
				a.setPassword(rs.getString("password"));
				a.setEmail(rs.getString("email"));
				alist.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alist;
	}

	@Override //ok
	public List<Admin> selectAdmin(String username, String password) {
		Connection conn = DbConnection.getDb();
		String sql = "select * from `admin` where username=? and password=?";
		List<Admin> alist = new ArrayList();
		Admin a = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				a = new Admin();
				a.setId(rs.getInt("admin_id"));
				a.setName(rs.getString("name"));
				a.setUsername(rs.getString("username"));
				a.setPassword(rs.getString("password"));
				a.setEmail(rs.getString("email"));
				alist.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alist;
	}

	@Override //ok
	public void update(Admin a) {
		Connection conn = DbConnection.getDb();
		String SQL = "update `admin` set name=?, password=?, email=?  where admin_id=?;";
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setInt(4, a.getId());
			ps.setString(1, a.getName());
			ps.setString(2, a.getPassword());
			ps.setString(3, a.getEmail());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override //ok
	public void delete(int id) {
			Connection conn = DbConnection.getDb();
			String SQL = "delete from `admin` where admin_id=?";
			try {
				PreparedStatement ps = conn.prepareStatement(SQL);
				ps.setInt(1, id);
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	
	public Admin queryUsername(String username) {
		Connection conn = DbConnection.getDb();
		String SQL = "select * from `admin` where username=?";
		Admin a = null;
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				a = new Admin();
				a.setId(rs.getInt("id"));
				a.setName(rs.getString("name"));
				a.setUsername(rs.getString("username"));
				a.setPassword(rs.getString("password"));
				a.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}
	

}
