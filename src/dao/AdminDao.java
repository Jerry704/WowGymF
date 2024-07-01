package dao;

import java.util.List;

import model.Admin;



public interface AdminDao {

		//c
		void add(Admin a);
		
		//r
		List<Admin> selectAll();
		List<Admin> selectById(int id);
		List<Admin> selectAdmin(String username,String passowrd);
		// u
		void update(Admin a);
		
		// d
		void delete(int id);
}
