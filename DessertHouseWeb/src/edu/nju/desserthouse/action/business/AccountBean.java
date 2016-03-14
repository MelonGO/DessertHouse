package edu.nju.desserthouse.action.business;

import java.io.Serializable;

import edu.nju.desserthouse.model.Account;

public class AccountBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Account account;

	public Account getAccount() {
		return account;
	}
	
	public void setAccount(Account account) {
		this.account = account;
	}

}
