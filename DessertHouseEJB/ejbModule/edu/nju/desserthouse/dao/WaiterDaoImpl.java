package edu.nju.desserthouse.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.nju.desserthouse.model.Waiter;

@Stateless
public class WaiterDaoImpl implements WaiterDao {
	@PersistenceContext
	protected EntityManager em;

	@Override
	public boolean save(Waiter waiter) {
		try {
			em.persist(waiter);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List findWaiterList() {
		try {
			List list = new ArrayList();
			Query query = em.createQuery("select w from Waiter w");
			list = query.getResultList();
			em.clear();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean update(Waiter waiter) {
		try {
			em.merge(waiter);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(Waiter waiter) {
		try {
			em.remove(em.merge(waiter));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Waiter find(String name, String password) {
		try {
			Query query = em.createQuery("select w from Waiter w where w.waitername=:name and w.waiterpassword=:password");
			query.setParameter("name", name);
			query.setParameter("password", password);
			Waiter waiter = (Waiter) query.getSingleResult();
			em.clear();
			return waiter;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Waiter findById(int waiterid) {
		try {
			Query query = em.createQuery("select w from Waiter w where w.waiterid=:id");
			query.setParameter("id", waiterid);
			Waiter waiter = (Waiter) query.getSingleResult();
			return waiter;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
