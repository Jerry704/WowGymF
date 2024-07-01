package model;

import java.io.Serializable;

public class Order implements Serializable{
	private Integer order_id;
	private Integer trainer_id;
	private Integer member_id;
	private String time;
	private Integer price;
	private String lessonName;
	
	public String getLessonName() {
		return lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}

	public Order() {}
	
	public Order(Integer trainer_id, Integer member_id, String time, Integer price,String lessonName) {
		super();
		this.trainer_id = trainer_id;
		this.member_id = member_id;
		this.time = time;
		this.price = price;
		this.lessonName = lessonName;
	}
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	public Integer getTrainer_id() {
		return trainer_id;
	}
	public void setTrainer_id(Integer trainer_id) {
		this.trainer_id = trainer_id;
	}
	public Integer getMember_id() {
		return member_id;
	}
	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	
}
