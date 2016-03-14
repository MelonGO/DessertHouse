package edu.nju.desserthouse.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private int userid;
	private String username;
	private String userpassword;
	private String gender;
	private String status;
	private int cardid;
	private String cardtime;
	private Double reward;
	private int level;
	private int age;
	private String address;
	private Double consume;

	public int getUserid() {
		return userid;
	}
	public void setUserid(int id) {
		this.userid = id;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String name) {
		this.username = name;
	}

	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String password) {
		this.userpassword = password;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getCardid() {
		return cardid;
	}
	public void setCardid(int cardid) {
		this.cardid = cardid;
	}
	
	public String getCardtime() {
		return cardtime;
	}
	public void setCardtime(String date) {
		this.cardtime= date;
	}
	
	public Double getReward() {
		return reward;
	}
	public void setReward(Double reward) {
		this.reward = reward;
	}
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Double getConsume() {
		return consume;
	}
	public void setConsume(Double consume) {
		this.consume = consume;
	}

}
