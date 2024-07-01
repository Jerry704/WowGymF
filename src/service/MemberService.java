package service;

import java.util.List;

import model.Lesson;
import model.Member;
import model.Order;

public interface MemberService {
	// create
		void addMember(Member m);
		void addOrder(Order o);

		// read
		List<Member> queryAll();
		List<Lesson> queryAllLesson();
		List<Order> queryOrder(int id);
//		List<Order> queryMyLesson(int id);
		

		Member queryById(int id);
		Member queryMember(String username,String password);
		

		// update
		void updateMember(int id, String membername);

		void updateMember(int id, String membername, String password,String email);

		// delete
		void deleteMember(int id);
}
