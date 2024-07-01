package dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DbConnection;
import dao.TrainerDao;
import model.Trainer;

public class TrainerDaoImpl implements TrainerDao {
	
	public static void main(String[] args) {
//		Trainer m = new Trainer("小c","bb01","ccccc","cc01@gmail.com");
//		
//		new TrainerDaoImpl().add(m);
		
//		List<Trainer> tl= new ArrayList(new TrainerDaoImpl().selectAll());
//		for(Trainer t : tl) {
//			show(t);
//		}
//		List<Trainer> tl= new ArrayList(new TrainerDaoImpl().selectById(3));
//		List<Trainer> tl= new ArrayList(new TrainerDaoImpl().selectTrainer("bb01", "ccccc"));
//		Trainer[] t = tl.toArray(new Trainer[1]);
//		t[0].setName("大明");
//		t[0].setPassword("mmmmm");
//		t[0].setEmail("mm01@gmail.com");
//		new TrainerDaoImpl().update(t[0]);
//		show(t[0]);
		new TrainerDaoImpl().delete(3);
	}

	public static void show(Trainer t) {
		System.out.println(
				t.getId() + "\t" + t.getName() + "\t" + t.getUsername() + "\t" + t.getPassword() + "\t" + t.getEmail());
	}
	@Override //ok
	public void add(Trainer t) {
		Connection conn =DbConnection.getDb();
		String sql = "insert into `Trainer`(Name, username,password, email) values(?,?,?,?) ";
		try {
			PreparedStatement ps =conn.prepareStatement(sql);
			ps.setString(1, t.getName());
			ps.setString(2, t.getUsername());
			ps.setString(3, t.getPassword());
			ps.setString(4, t.getEmail());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override //ok
	public List<Trainer> selectAll() {
		Connection conn = DbConnection.getDb();
		String sql = "select * from `Trainer`";
		List<Trainer> tl= new ArrayList();
		Trainer t =null;
		try {
			PreparedStatement ps =conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				t = new Trainer();
				t.setId(rs.getInt("Trainer_id"));
				t.setName(rs.getString("name"));
				t.setUsername(rs.getString("username"));
				t.setPassword(rs.getString("password"));
				t.setEmail(rs.getString("email"));
				tl.add(t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tl;
	}

	@Override  //ok
	public List<Trainer> selectById(int id) {
		Connection conn = DbConnection.getDb();
		String sql = "select * from `Trainer` where trainer_id=?";
		List<Trainer> tl = new ArrayList();
		Trainer t = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				t = new Trainer();
				t.setId(rs.getInt("trainer_id"));
				t.setName(rs.getString("name"));
				t.setUsername(rs.getString("username"));
				t.setPassword(rs.getString("password"));
				t.setEmail(rs.getString("email"));
				tl.add(t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tl;
	}

	@Override //ok
	public List<Trainer> selectTrainer(String username, String password) {
		Connection conn = DbConnection.getDb();
		String sql = "select * from `trainer` where username=? and password=?";
		List<Trainer> tl = new ArrayList();
		Trainer t = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				t = new Trainer();
				t.setId(rs.getInt("trainer_id"));
				t.setName(rs.getString("name"));
				t.setUsername(rs.getString("username"));
				t.setPassword(rs.getString("password"));
				t.setEmail(rs.getString("email"));
				tl.add(t);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tl;
	}

	@Override //ok
	public void update(Trainer t) {
		Connection conn = DbConnection.getDb();
		String sql = "update  `trainer` set name=?, password=?, email=? where trainer_id=? ";
		try {
			PreparedStatement ps =conn.prepareStatement(sql);
			ps.setString(1, t.getName());
			ps.setString(2, t.getPassword());
			ps.setString(3, t.getEmail());
			ps.setInt(4, t.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override //ok
	public void delete(int id) {
		Connection conn = DbConnection.getDb();
		String sql = "delete from `trainer` where trainer_id =?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public Trainer queryUsername(String username) {
		Connection conn = DbConnection.getDb();
		String SQL = "select * from `trainer` where username=?";
		Trainer t = null;
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				t = new Trainer();
				t.setId(rs.getInt("id"));
				t.setName(rs.getString("name"));
				t.setUsername(rs.getString("username"));
				t.setPassword(rs.getString("password"));
				t.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}

}
