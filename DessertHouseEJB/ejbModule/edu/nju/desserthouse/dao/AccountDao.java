package edu.nju.desserthouse.dao;

import javax.ejb.Remote;

import edu.nju.desserthouse.model.Account;

@Remote
public interface AccountDao {

	public boolean save(Account account);

	public Account find(int userid);

	public boolean update(Account account);

}
