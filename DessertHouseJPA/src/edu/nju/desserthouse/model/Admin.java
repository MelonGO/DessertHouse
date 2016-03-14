package edu.nju.desserthouse.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private int adminid;
	private String adminname;
	private String adminpassword;

	public int geAdminid() {
		return adminid;
	}
	public void setAdminid(int id) {
		this.adminid = id;
	}

	public String getAdminname() {
		return adminname;
	}
	public void setAdminname(String name) {
		this.adminname = name;
	}

	public String getAdminpassword() {
		return adminpassword;
	}
	public void setAdminpassword(String password) {
		this.adminpassword = password;
	}
}
