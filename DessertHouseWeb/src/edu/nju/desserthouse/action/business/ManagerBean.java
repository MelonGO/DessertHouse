package edu.nju.desserthouse.action.business;

import java.io.Serializable;

import edu.nju.desserthouse.model.Manager;

public class ManagerBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Manager manager;

	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
}
