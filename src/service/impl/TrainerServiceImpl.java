package service.impl;

import java.util.List;

import dao.Impl.LessonDaoImpl;
import dao.Impl.OrderDaoImpl;
import dao.Impl.TrainerDaoImpl;
import model.Lesson;

import model.Order;
import model.Trainer;
import service.TrainerService;

public class TrainerServiceImpl implements TrainerService{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static TrainerDaoImpl tdi = new TrainerDaoImpl();
	public static LessonDaoImpl ldi = new LessonDaoImpl();
	public static OrderDaoImpl odi = new OrderDaoImpl();
	
	
	//trainer---------------------------------
	
	@Override
	public void addTrainer(Trainer t) {
		// TODO Auto-generated method stub
		tdi.add(t);
	}

	@Override
	public List<Trainer> queryAll() {
		// TODO Auto-generated method stub
		return tdi.selectAll();
	}

	@Override
	public Trainer queryById(int id) {
		List<Trainer> l =tdi.selectById(id);
		Trainer[] t = l.toArray(new Trainer[0]);
		return t[0];
	}
	
	public Trainer queryByUsername(String username) {
		Trainer t= tdi.queryUsername(username);
		return t;
	}

	@Override
	public Trainer queryTrainer(String username, String password) {
		List<Trainer> l =tdi.selectTrainer(username, password);
		Trainer[] t = l.toArray(new Trainer[1]);
		return t[0];
	}

	@Override
	public void updateTrainer(int id, String name) {
		// TODO Auto-generated method stub
		Trainer t =this.queryById(id);
		t.setName(name);
		tdi.update(t);
	}

	@Override
	public void updateTrainer(int id, String name, String password, String email) {
		Trainer t =this.queryById(id);
		t.setName(name);
		t.setPassword(password);
		t.setEmail(email);
		tdi.update(t);
		
	}

	@Override
	public void deleteTrainer(int id) {
		// TODO Auto-generated method stub
		tdi.delete(id);
	}
	//trainer---------------------------------
	
	//order-----------------------------------
	public List<Order> queryOrder(int id) {
		Trainer t = queryById(id);
		return odi.selectByTrainerId(t.getId());
	}
	//order-----------------------------------
	
	
	//Lesson---------------------------------------------
	public List<Lesson> queryAllLesson() {
		// TODO Auto-generated method stub
		return ldi.selectAll();
	}
	
	public void addLesson(Lesson l) {
		ldi.add(l);
	}
	//Lesson---------------------------------------------
}
