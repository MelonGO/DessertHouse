package edu.nju.desserthouse.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "house")
public class House implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private int houseid;
	private String housename;
	private String address;
	private String main;

	public int getHouseid() {
		return houseid;
	}
	public void setHouseid(int id) {
		this.houseid = id;
	}

	public String getHousename() {
		return housename;
	}
	public void setHousename(String name) {
		this.housename = name;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getMain() {
		return main;
	}
	public void setMain(String main) {
		this.main = main;
	}

}
