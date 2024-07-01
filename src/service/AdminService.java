package service;

import java.util.List;

import model.Admin;
import model.Lesson;
import model.Order;


public interface AdminService {
				// create
				void addAdmin(Admin a);

				// read
				List<Admin> queryAll();
				

				Admin queryById(int id);
				Admin queryAdmin(String username,String password);
				

				// update
				void updateAdmin(int id, String name);

				void updateAdmin(int id, String name, String password,String email);

				// delete
				void deleteAdmin(int id);
}
