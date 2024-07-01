package dao;

import java.util.List;
import model.Order;

public interface OrderDao {
	// c
		void add(Order o);

		// d
		boolean delete(int id);

		// q
		List<Order> selectAll();
		List<Order> selectByOrderId(int id);
		List<Order> selectByTrainerId(int train_id);
		List<Order> selectByMemberId(int member_id);
		List<Order> selectByTime(String time);

		// u
		void update(Order o);
}
