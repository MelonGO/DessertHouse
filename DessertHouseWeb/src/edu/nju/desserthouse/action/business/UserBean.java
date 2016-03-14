package edu.nju.desserthouse.action.business;

import java.io.Serializable;
import java.util.List;

import edu.nju.desserthouse.model.User;

public class UserBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private User user;
	private List listuser;

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public List getListuser() {
		return listuser;
	}
	public void setListuser(List list) {
		this.listuser = list;
	}

}
