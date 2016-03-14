package edu.nju.desserthouse.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.nju.desserthouse.model.Dessert;

@Stateless
public class DessertDaoImpl implements DessertDao {
	@PersistenceContext
	protected EntityManager em;

	@Override
	public Dessert findById(int dessertid) {
		try {
			Query query = em.createQuery("select d from Dessert d where d.dessertid=:id");
			query.setParameter("id", dessertid);
			Dessert dessert = (Dessert) query.getSingleResult();
			em.clear();
			return dessert;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List findDessertList() {
		try {
			List list = new ArrayList();
			Query query = em.createQuery("select d from Dessert d");
			list = query.getResultList();
			em.clear();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Dessert findByName(String name) {
		try {
			Query query = em.createQuery("select d from Dessert d where d.dessertname=:name");
			query.setParameter("name", name);
			Dessert dessert = (Dessert) query.getSingleResult();
			em.clear();
			return dessert;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
