package edu.nju.desserthouse.action.business;

import java.io.Serializable;

import edu.nju.desserthouse.model.Admin;

public class AdminBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Admin admin;

	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

}
