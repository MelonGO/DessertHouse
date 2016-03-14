package edu.nju.desserthouse.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.nju.desserthouse.model.House;

@Stateless
public class HouseDaoImpl implements HouseDao {
	@PersistenceContext
	protected EntityManager em;

	@Override
	public boolean save(House house) {
		try {
			em.persist(house);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List findHouseList() {
		try {
			List list = new ArrayList();
			Query query = em.createQuery("select h from House h");
			list = query.getResultList();
			em.clear();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean update(House house) {
		try {
			em.merge(house);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(House house) {
		try {
			em.remove(em.merge(house));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public House findHouseById(int houseid) {
		try {
			Query query = em.createQuery("select h from House h where h.houseid=:id");
			query.setParameter("id", houseid);
			House house = (House) query.getSingleResult();
			return house;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public House findHouseByName(String housename) {
		try {
			Query query = em.createQuery("select h from House h where h.housename=:name");
			query.setParameter("name", housename);
			House house = (House) query.getSingleResult();
			return house;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
