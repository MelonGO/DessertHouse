package edu.nju.desserthouse.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.nju.desserthouse.model.Account;

@Stateless
public class AccountDaoImpl implements AccountDao {
	@PersistenceContext
	protected EntityManager em;

	@Override
	public boolean save(Account account) {
		try {
			em.persist(account);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Account find(int userid) {
		try {
			Query query = em.createQuery("select a from Account a where a.userid=:id");
			query.setParameter("id", userid);
			Account account = (Account) query.getSingleResult();
			em.clear();
			return account;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean update(Account account) {
		try {
			em.merge(account);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
