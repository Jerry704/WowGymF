package service;

import java.util.List;

import model.Trainer;



public interface TrainerService {
	// create
			void addTrainer(Trainer t);

			// read
			List<Trainer> queryAll();

			Trainer queryById(int id);
			Trainer queryTrainer(String username,String password);
			

			// update
			void updateTrainer(int id, String name);

			void updateTrainer(int id, String name, String password,String email);

			// delete
			void deleteTrainer(int id);
}
