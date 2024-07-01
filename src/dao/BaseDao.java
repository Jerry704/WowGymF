package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import model.Member;

public class BaseDao {
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/wowgym";
	private static String user = "root";
	private static String password = "1234";
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	public static void main(String[] args) {
//		Member m = new Member("aaa","aaa01","111","aaa01@gmail.com");
//		String sql = "insert into member(name,username,password,email) values(?,?,?,?)";
//		System.out.println(executeUpdate(sql,m.getName(),m.getUsername(),m.getPassword(),m.getEmail())); //連線測試
//		BaseDao.executeUpdate(sql, null);
//		BaseDao.executeQuery("select * from `member`");
		
	}

	public static Connection getConnection() {

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * releaseAll
	 * 
	 * @param rs   返回集對象
	 * @param ps   預處裡對象
	 * @param conn 連接對象
	 */
	public static void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 執行增刪改
	 * 
	 * @param sql     傳入增刪改sql語句
	 * @param objects 傳入sql需要的數據，位置要跟sql表位置一致
	 * @return 返回影響的行數
	 */
	public static int executeUpdate(String sql, Object... objects) {
		int row = 0;
		conn = getConnection();
		try {
			ps = conn.prepareStatement(sql);
			// 傳入的參數
			for (int i = 0; i < objects.length; i++) {
				ps.setObject(i + 1, objects[i]);
			}
			row = ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeAll(rs, ps, conn);
		}
		return row;
	}

	
	public static Map executeQuery(String sql) {
		// 创建一个Map集合，用于存储每一行的数据
		Map<String, Object> resultMap = new HashMap<>();
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			ResultSetMetaData metaData;
				metaData = rs.getMetaData();

				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
					// 遍历每一列，根据列名将值存入Map
					for (int i = 1; i <= columnCount; i++) {
						String columnName = metaData.getColumnName(i);
						Object columnValue = rs.getObject(i);
						resultMap.put(columnName, columnValue);
					}
					// 输出当前行的Map
					//System.out.println(resultMap.keySet());
					// 或者可以将resultMap存入List，用于后续处理
				}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			closeAll(rs, ps, conn);
		} 
		return resultMap;
	}

	public static ResultSet executeQuery(String sql, Object... objects) {
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < objects.length; i++) {
				ps.setObject(i + 1, objects[i]);
			}

			rs = ps.executeQuery();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeAll(rs, ps, conn);
		}
		return rs;
	}
}
