package service.impl;

import java.util.List;

import dao.Impl.LessonDaoImpl;
import dao.Impl.MemberDaoImpl;
import dao.Impl.OrderDaoImpl;
import model.Lesson;
import model.Member;
import model.Order;
import service.MemberService;

public class MemberServiceImpl implements MemberService {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static MemberDaoImpl mdi = new MemberDaoImpl();
	public static LessonDaoImpl ldi = new LessonDaoImpl();
	public static OrderDaoImpl odi = new OrderDaoImpl();

	@Override
	public void addMember(Member m) {
		// TODO Auto-generated method stub
		mdi.add(m);
	}

	@Override
	public List<Member> queryAll() {
		// TODO Auto-generated method stub
		return mdi.selectAll();
	}
	
	public Member queryByUsername(String username) {
		Member m= mdi.queryUsername(username);
		return m;
	}

	@Override
	public Member queryById(int id) {
		List<Member> l = mdi.selectById(id);
		Member[] m = l.toArray(new Member[1]);

		return m[0];
	}

	@Override
	public Member queryMember(String username, String password) {
		List<Member> l = mdi.selectMember(username, password);
		Member[] m = l.toArray(new Member[1]);

		return m[0];
	}

	@Override
	public void updateMember(int id, String membername) {
		Member m = queryById(id);
		m.setName(membername);
		mdi.update(m);
	}

	@Override
	public void updateMember(int id, String membername, String password, String email) {
		Member m = queryById(id);

		m.setName(membername);
		m.setPassword(password);
		m.setEmail(email);
		mdi.update(m);

	}

	@Override
	public void deleteMember(int id) {
		// TODO Auto-generated method stub
		mdi.delete(id);
	}

	@Override
	public List<Lesson> queryAllLesson() {
		// TODO Auto-generated method stub
		return ldi.selectAll();
	}

//	@Override
//	public List<Order> queryMyLesson(int id) {
//		Member m = queryById(id);	
//		return odi.selectByMemberId(m.getId());
//	}

	@Override
	public List<Order> queryOrder(int id) {
		Member m = queryById(id);
		return odi.selectByMemberId(m.getId());
	}

	@Override
	public void addOrder(Order o) {
		// TODO Auto-generated method stub
		odi.add(o);
	}
	

}
