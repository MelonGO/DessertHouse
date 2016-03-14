package edu.nju.desserthouse.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.nju.desserthouse.model.Manager;

@Stateless
public class ManagerDaoImpl implements ManagerDao {
	@PersistenceContext
	protected EntityManager em;

	@Override
	public Manager find(String name, String password) {
		try {
			Query query = em.createQuery("select m from Manager m  where m.managername=:name and m.managerpassword=:password");
			query.setParameter("name", name);
			query.setParameter("password", password);
			Manager manager = (Manager) query.getSingleResult();
			em.clear();
			return manager;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
