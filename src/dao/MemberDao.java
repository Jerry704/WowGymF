package dao;

import java.util.List;


import model.Member;

public interface MemberDao {

			//c
			void add(Member m);
			
			//r
			List<Member> selectAll();
			List<Member> selectById(int id);
			List<Member> selectMember(String username,String password);
			// u
			void update(Member a);
			
			// d
			void delete(int id);
}
