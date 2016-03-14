package edu.nju.desserthouse.service;

import javax.ejb.Remote;

import edu.nju.desserthouse.model.Account;

@Remote
public interface AccountManageService {

	public boolean save(Account account);

	public Account find(int userid);

	public boolean update(Account account);

}
