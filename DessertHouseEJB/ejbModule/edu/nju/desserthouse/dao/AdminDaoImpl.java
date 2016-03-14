package edu.nju.desserthouse.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.nju.desserthouse.model.Admin;

@Stateless
public class AdminDaoImpl implements AdminDao {
	@PersistenceContext
	protected EntityManager em;

	@Override
	public Admin find(String name, String password) {
		try {
			Query query = em.createQuery("select a from Admin a where a.adminname=:name and a.adminpassword=:password");
			query.setParameter("name", name);
			query.setParameter("password", password);
			Admin admin = (Admin) query.getSingleResult();
			em.clear();
			return admin;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
}
