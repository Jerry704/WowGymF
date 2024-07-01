package service.impl;

import java.util.List;

import dao.Impl.AdminDaoImpl;
import dao.Impl.LessonDaoImpl;
import dao.Impl.MemberDaoImpl;
import dao.Impl.OrderDaoImpl;
import dao.Impl.TrainerDaoImpl;
import model.Admin;
import model.Lesson;
import model.Member;
import model.Order;
import model.Trainer;
import service.AdminService;

public class AdminServiceImpl implements AdminService {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static AdminDaoImpl adi= new AdminDaoImpl();
	public static MemberDaoImpl mdi = new MemberDaoImpl();
	public static TrainerDaoImpl tdi = new TrainerDaoImpl();
	public static LessonDaoImpl ldi = new LessonDaoImpl();
	public static OrderDaoImpl odi = new OrderDaoImpl();
	public static MemberServiceImpl msi = new MemberServiceImpl();
	public static TrainerServiceImpl tsi = new TrainerServiceImpl();
	
	//admin-----------------------
	@Override
	public void addAdmin(Admin a) {
		// TODO Auto-generated method stub
		adi.add(a);
	}

	
	@Override
	public List<Admin> queryAll() {
		// TODO Auto-generated method stub
		return adi.selectAll();
	}

	@Override
	public Admin queryById(int id) {
		List<Admin> l = adi.selectById(id);
		Admin[] a = l.toArray(new Admin[0]);
		return a[0];
	}

	@Override
	public Admin queryAdmin(String username, String password) {
		List<Admin> l = adi.selectAdmin(username, password);
		Admin[] a = l.toArray(new Admin[1]);
		return a[0];
	}

	@Override
	public void updateAdmin(int id, String name) {
		Admin a =queryById(id);
		a.setName(name);
		adi.update(a);
		
	}

	@Override
	public void updateAdmin(int id, String name, String password, String email) {
		Admin a =queryById(id);
		a.setName(name);
		a.setPassword(password);
		a.setEmail(email);
		adi.update(a);
	}

	@Override
	public void deleteAdmin(int id) {
		// TODO Auto-generated method stub
		adi.delete(id);
	}
	
	public Admin queryByUsername(String username) {
		Admin a= adi.queryUsername(username);
		return a;
	}
	
	//admin-----------------------
	
	//查所有課程
	public List<Lesson> queryAllLesson() {
		// TODO Auto-generated method stub
		return ldi.selectAll();
	}
	
	//查所有訂單
	public List<Order> queryAllOrder(){
		return odi.selectAll();
	}
	
	//查詢所有會員
	public List<Member> queryAllMember(){
		return mdi.selectAll();
	}
	
	//查詢所有教練
	public List<Trainer> queryAllTrainer(){
		return tdi.selectAll();
	}
	
	//查詢所有管理員
	public List<Admin> queryAllAdmin(){
		return adi.selectAll();
	}
	
	//修改會員資料
	public void updateMember(int id, String name, String password, String email) {
		Member m =msi.queryById(id);
		m.setName(name);
		m.setPassword(password);
		m.setEmail(email);
		mdi.update(m);
	}
	
	//修改教練資料
		public void updateTrainer(int id, String name, String password, String email) {
			Trainer t =tsi.queryById(id);
			t.setName(name);
			t.setPassword(password);
			t.setEmail(email);
			tdi.update(t);
		}

}
