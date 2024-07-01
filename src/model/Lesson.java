package model;

import java.io.Serializable;

public class Lesson  implements Serializable{
	private Integer lesson_id;
	private Integer trainer_id;
	private String name;
	private String time;
	private Integer price;
	
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Lesson() {}
	public Lesson( Integer trainer_id, String name, String time,Integer price) {
		super();
		this.trainer_id = trainer_id;
		this.name = name;
		this.time = time;
		this.price = price;
	}
	public Integer getLesson_id() {
		return lesson_id;
	}
	public void setLesson_id(Integer lesson_id) {
		this.lesson_id = lesson_id;
	}
	public Integer getTrainer_id() {
		return trainer_id;
	}
	public void setTrainer_id(Integer trainer_id) {
		this.trainer_id = trainer_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
