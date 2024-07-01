package dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DbConnection;
import dao.LessonDao;
import model.Lesson;


public class LessonDaoImpl implements LessonDao {
	public static void main(String[] args) {
//		Lesson l = new Lesson(2,"戰繩","2024-06-26 16:00");
//		new LessonDaoImpl().add(l);
		
//		List<Lesson> ll =new ArrayList(new LessonDaoImpl().selectAll());
//		for(Lesson o : ll) {show(o);}
//		List<Lesson> ll = new ArrayList(new LessonDaoImpl().selectByLessonId(1));
//		List<Lesson> ll = new ArrayList(new LessonDaoImpl().selectByTrainerId(1));
//		List<Lesson> ll = new ArrayList(new LessonDaoImpl().selectByLessonName("有氧拳擊"));
//		List<Lesson> ll = new ArrayList(new LessonDaoImpl().selectByTime("2024-06-24 18:00"));
//		for(Lesson o : ll) {show(o);}
//		Lesson l = new Lesson(1,"戰繩","2024-06-26 16:00");
//		l.setLesson_id(7);
//		new LessonDaoImpl().delete(7);
	}

	public static void show(Lesson l) {
		System.out.println(
				l.getLesson_id() + "\t" + l.getName() + "\t\t教練id:" + l.getTrainer_id() + "\t\t" + l.getTime()+"\t\t"+l.getPrice());
	}
	
	@Override //ok
	public void add(Lesson l) {
		Connection conn = DbConnection.getDb();
		String sql = "insert into `Lesson` (trainer_id, name,time,price) values(?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, l.getTrainer_id());
			ps.setString(2, l.getName());
			ps.setString(3, l.getTime());
			ps.setInt(4, l.getPrice());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	@Override //ok
	public List<Lesson> selectAll() {
		Connection conn = DbConnection.getDb();
		String sql = "select * from `lesson`";
		List<Lesson> ll = new ArrayList();
		Lesson l = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs =ps.executeQuery();
			while(rs.next()) {
				l = new Lesson();
				l.setLesson_id(rs.getInt("lesson_id"));
				l.setTrainer_id(rs.getInt("trainer_id"));
				l.setName(rs.getString("name"));
				l.setTime(rs.getString("time"));
				l.setPrice(rs.getInt("price"));
				ll.add(l);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ll;
	}

	
	@Override //ok
	public List<Lesson> selectByLessonId(int id) {
		Connection conn = DbConnection.getDb();
		String sql= "select * from `Lesson` where lesson_id =?";
		List<Lesson> ll = new ArrayList();
		Lesson l = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				l = new Lesson();
				l.setLesson_id(rs.getInt("Lesson_id"));
				l.setTrainer_id(rs.getInt("Trainer_id"));
				l.setName(rs.getString("name"));
				l.setTime(rs.getString("time"));
				l.setPrice(rs.getInt("price"));
				ll.add(l);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ll;
	}

	@Override //ok
	public List<Lesson> selectByTrainerId(int train_id) {
		Connection conn = DbConnection.getDb();
		String sql= "select * from `Lesson` where trainer_id =?";
		List<Lesson> ll = new ArrayList();
		Lesson l = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, train_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				l = new Lesson();
				l.setLesson_id(rs.getInt("Lesson_id"));
				l.setTrainer_id(rs.getInt("Trainer_id"));
				l.setName(rs.getString("name"));
				l.setTime(rs.getString("time"));
				l.setPrice(rs.getInt("price"));
				ll.add(l);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ll;
	}

	@Override //ok
	public List<Lesson> selectByLessonName(String name) {
		Connection conn = DbConnection.getDb();
		String sql = "select * from `Lesson` where name=?";
		List<Lesson> ll = new ArrayList();
		Lesson l =null;
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				l = new Lesson();
				l.setLesson_id(rs.getInt("Lesson_id"));
				l.setTrainer_id(rs.getInt("trainer_id"));
				l.setName(rs.getString("name"));
				l.setTime(rs.getString("time"));
				l.setPrice(rs.getInt("price"));
				ll.add(l);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ll;
	}
	
	@Override //ok
	public List<Lesson> selectByTime(String time) {
		Connection conn = DbConnection.getDb();
		String sql = "select * from `lesson` where time =?";
		List<Lesson> ll = new ArrayList();
		Lesson l =null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,time);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				l = new Lesson();
				l.setLesson_id(rs.getInt("lesson_id"));
				l.setTrainer_id(rs.getInt("trainer_id"));
				l.setName(rs.getString("name"));
				l.setTime(rs.getString("Time"));
				l.setPrice(rs.getInt("price"));
				ll.add(l);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ll;
	}

	@Override //ok
	public void update(Lesson l) {
		Connection conn = DbConnection.getDb();
		String sql = "update `lesson` set trainer_id=? , name =?, time =?,price=? where lesson_id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, l.getTrainer_id());
			ps.setString(2, l.getName());
			ps.setString(3, l.getTime());
			ps.setInt(4, l.getPrice());
			ps.setInt(5, l.getLesson_id());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override //ok
	public void delete(int id) {
		Connection conn = DbConnection.getDb();
		String sql = "delete from `lesson` where lesson_id=?";
		boolean b =false;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	

	

}
