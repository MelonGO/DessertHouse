package edu.nju.desserthouse.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.nju.desserthouse.model.Plan;

@Stateless
public class PlanDaoImpl implements PlanDao {

	@PersistenceContext
	protected EntityManager em;

	@Override
	public boolean save(Plan plan) {
		try {
			em.persist(plan);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Plan plan) {
		try {
			em.merge(plan);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List findByHouseId(int houseid) {
		try {
			List list = new ArrayList();
			Query query = em.createQuery("select p from Plan p where p.houseid=:id");
			query.setParameter("id", houseid);
			list = query.getResultList();
			em.clear();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List findPlanList() {
		try {
			List list = new ArrayList();
			Query query = em.createQuery("select p from Plan p");
			list = query.getResultList();
			em.clear();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean delete(Plan plan) {
		try {
			em.remove(em.merge(plan));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Plan findById(int planid) {
		try {
			Query query = em.createQuery("select p from Plan p where p.planid=:id");
			query.setParameter("id", planid);
			Plan plan = (Plan) query.getSingleResult();
			em.clear();
			return plan;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List findByHouseIdAndDay(int houseid, String day) {
		try {
			List list = new ArrayList();
			Query query = em.createQuery("select p from Plan p where p.houseid=:id and p.day=:day");
			query.setParameter("id", houseid);
			query.setParameter("day", day);
			list = query.getResultList();
			em.clear();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List findByDay(String day) {
		try {
			List list = new ArrayList();
			Query query = em.createQuery("select p from Plan p where p.day=:day");
			query.setParameter("day", day);
			list = query.getResultList();
			em.clear();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
