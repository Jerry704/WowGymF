package dao;

import java.util.List;

import model.Lesson;

public interface LessonDao {

	// c
	void add(Lesson l);

	// d
	void delete(int id);

	// q
	List<Lesson> selectAll();

	List<Lesson> selectByLessonId(int id);

	List<Lesson> selectByTrainerId(int train_id);
	
	List<Lesson> selectByLessonName(String name);
	List<Lesson> selectByTime(String time);

	// u
	void update(Lesson l);

}
