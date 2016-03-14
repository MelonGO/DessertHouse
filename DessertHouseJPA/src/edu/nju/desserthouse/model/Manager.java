package edu.nju.desserthouse.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "manager")
public class Manager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private int managerid;
	private String managername;
	private String managerpassword;
	
	public int geManagerid() {
		return managerid;
	}
	public void setManagerid(int id) {
		this.managerid = id;
	}

	public String getManagername() {
		return managername;
	}
	public void setManagername(String name) {
		this.managername = name;
	}

	public String getManagerpassword() {
		return managerpassword;
	}
	public void setManagerpassword(String password) {
		this.managerpassword = password;
	}

}
