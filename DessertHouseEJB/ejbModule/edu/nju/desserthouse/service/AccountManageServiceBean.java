package edu.nju.desserthouse.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import edu.nju.desserthouse.dao.AccountDao;
import edu.nju.desserthouse.model.Account;

@Stateless
public class AccountManageServiceBean implements AccountManageService {
	@EJB
	AccountDao accountDao;

	@Override
	public boolean save(Account account) {
		// TODO Auto-generated method stub
		return accountDao.save(account);
	}

	@Override
	public Account find(int userid) {
		// TODO Auto-generated method stub
		return accountDao.find(userid);
	}

	@Override
	public boolean update(Account account) {
		// TODO Auto-generated method stub
		return accountDao.update(account);
	}

}
