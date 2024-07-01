package dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DbConnection;
import dao.OrderDao;
import model.Order;

public class OrderDaoImpl implements OrderDao {
	
	public static void main(String[] args) {
//		Order o = new Order(1,5,"2024-06-19 19:59:59",2999);
//		new OrderDaoImpl().add(o);
		List<Order> ol2 = new ArrayList(new OrderDaoImpl().selectAll());
//		List<Order> ol = new ArrayList(new OrderDaoImpl().selectByOrderId(7));
//		List<Order> ol = new ArrayList(new OrderDaoImpl().selectByTrainerId(1));
//		List<Order> ol = new ArrayList(new OrderDaoImpl().selectByMemberId(1));
//		List<Order> ol = new ArrayList(new OrderDaoImpl().selectByTime("2024-06-23 16:33:22"));
//		Order[] o = ol.toArray(new Order[1]);
//		o[0].setTime("2024-99-99 99999");
//		o[0].setPrice(9999);
//		new OrderDaoImpl().update(o[0]);
//		List<Order> ol2 = new ArrayList(new OrderDaoImpl().selectByOrderId(7));
		for(Order o1 : ol2) {
			show(o1);
		}
//		System.out.println(new OrderDaoImpl().delete(7));
	}
	
	public static void show(Order o) {
		System.out.println(
				"orderid="+o.getOrder_id() + "\ttrainerid=" + o.getTrainer_id() + "\tmemberid=" + o.getMember_id() + "\ttime=" + o.getTime()+ "\tprice=" +o.getPrice()+"\t"+o.getLessonName());
	}

	@Override //ok
	public void add(Order o) {
		Connection conn = DbConnection.getDb();
		String sql = "insert into `order`(trainer_id,member_id,time,price,lessonName) values(?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, o.getTrainer_id());
			ps.setInt(2, o.getMember_id());
			ps.setString(3, o.getTime());
			ps.setInt(4, o.getPrice());
			ps.setString(5, o.getLessonName());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	@Override //ok
	public List<Order> selectAll() {
		Connection conn = DbConnection.getDb();
		String sql = "select * from `order`";
		List<Order> ol = new ArrayList();
		Order o = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				o = new Order();
				o.setOrder_id(rs.getInt("order_id"));
				o.setTrainer_id(rs.getInt("trainer_id"));
				o.setMember_id(rs.getInt("member_id"));
				o.setTime(rs.getString("time"));
				o.setPrice(rs.getInt("price"));
				o.setLessonName(rs.getString("lessonName"));
				ol.add(o);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ol;
	}

	@Override //ok
	public List<Order> selectByOrderId(int id) {
		Connection conn = DbConnection.getDb();
		String sql ="select * from `order` where order_id=?";
		List<Order> ol = new ArrayList();
		Order o = null;
		try {
			PreparedStatement ps =conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				o = new Order();
				o.setOrder_id(rs.getInt("order_id"));
				o.setTrainer_id(rs.getInt("trainer_id"));
				o.setMember_id(rs.getInt("member_id"));
				o.setTime(rs.getString("time"));
				o.setPrice(rs.getInt("price"));
				o.setLessonName(rs.getString("lessonName"));
				ol.add(o);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ol;
	}

	@Override //ok
	public List<Order> selectByTrainerId(int trainer_id) {
		Connection conn = DbConnection.getDb();
		String sql = "select * from `order` where trainer_id=?";
		List<Order> ol = new ArrayList();
		Order o = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, trainer_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				o = new Order();
				o.setOrder_id(rs.getInt("order_id"));
				o.setTrainer_id(rs.getInt("trainer_id"));
				o.setMember_id(rs.getInt("member_id"));
				o.setTime(rs.getString("time"));
				o.setPrice(rs.getInt("price"));
				o.setLessonName(rs.getString("lessonName"));
				ol.add(o);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ol;
	}

	@Override //ok
	public List<Order> selectByMemberId(int member_id) {
		Connection conn = DbConnection.getDb();
		String sql = "select * from `order` where member_id=?";
		List<Order> ol= new ArrayList();
		Order o =null;
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, member_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				o = new Order();
				o.setOrder_id(rs.getInt("order_id"));
				o.setTrainer_id(rs.getInt("trainer_id"));
				o.setMember_id(rs.getInt("member_id"));
				o.setTime(rs.getString("time"));
				o.setPrice(rs.getInt("price"));
				o.setLessonName(rs.getString("lessonName"));
				ol.add(o);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ol;
	}

	@Override //ok
	public List<Order> selectByTime(String time) {
		Connection conn = DbConnection.getDb();
		String sql = "select * from `order` where time = ?";
		List<Order> ol = new ArrayList();
		Order o = null;
		try {
			PreparedStatement ps =conn.prepareStatement(sql);
			ps.setString(1, time);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				o = new Order();
				o.setOrder_id(rs.getInt("order_id"));
				o.setTrainer_id(rs.getInt("trainer_id"));
				o.setMember_id(rs.getInt("member_id"));
				o.setTime(rs.getString("time"));
				o.setPrice(rs.getInt("price"));
				o.setLessonName(rs.getString("lessonName"));
				ol.add(o);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ol;
	}

	public List<Order> selectByLessonName(String lessonName) {
		Connection conn = DbConnection.getDb();
		String sql = "selete * from `order`where lessonName=?";
		List<Order> ol= new ArrayList();
		Order o = null;
		try {
			PreparedStatement ps= conn.prepareStatement(sql);
			ps.setString(1, lessonName);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				o = new Order();
				o.setOrder_id(rs.getInt("order_id"));
				o.setTrainer_id(rs.getInt("trainer_id"));
				o.setMember_id(rs.getInt("member_id"));
				o.setTime(rs.getString("time"));
				o.setPrice(rs.getInt("price"));
				o.setLessonName(rs.getString("lessonName"));
				ol.add(o);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ol;
	}
	
	
	
	//orderUpdate--------------------------------------------------
	@Override //ok
	public void update(Order o) {
		Connection conn = DbConnection.getDb();
		String sql = "update `order` set trainer_id=?,member_id=?,time=?,price=? where order_id=?";
		try {
			PreparedStatement ps =conn.prepareStatement(sql);
			ps.setInt(1, o.getTrainer_id());
			ps.setInt(2, o.getMember_id());
			ps.setString(3, o.getTime());
			ps.setInt(4, o.getPrice());
			ps.setInt(5, o.getOrder_id());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override //ok
	public boolean delete(int id) {
		Connection conn = DbConnection.getDb();
		String sql = "delete from `Order` where order_id=?";
		boolean flag = false;
		try {
			PreparedStatement ps =conn.prepareStatement(sql);
			ps.setInt(1, id);
			if(ps.executeUpdate()>0) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	

}
