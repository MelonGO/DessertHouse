package edu.nju.desserthouse.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "waiter")
public class Waiter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private int waiterid;
	private String waitername;
	private String waiterpassword;
	private int houseid;
	private String gender;
	
	public int getWaiterid() {
		return waiterid;
	}
	public void setWaiterid(int id) {
		this.waiterid = id;
	}

	public String getWaitername() {
		return waitername;
	}
	public void setWaitername(String name) {
		this.waitername = name;
	}

	public String getWaiterpassword() {
		return waiterpassword;
	}
	public void setWaiterpassword(String password) {
		this.waiterpassword = password;
	}
	
	public int getHouseid() {
		return houseid;
	}
	public void setHouseid(int id) {
		this.houseid = id;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

}
