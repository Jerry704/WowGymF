package dao;

import java.util.List;

import model.Trainer;

public interface TrainerDao {

	//c
	void add(Trainer t);
	
	//r
	List<Trainer> selectAll();
	List<Trainer> selectById(int id);
	List<Trainer> selectTrainer(String username,String password);
	// u
	void update(Trainer t);
	
	// d
	void delete(int id);
}
